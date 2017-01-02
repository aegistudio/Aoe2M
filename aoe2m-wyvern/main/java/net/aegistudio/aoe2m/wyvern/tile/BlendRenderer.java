package net.aegistudio.aoe2m.wyvern.tile;

import java.util.TreeMap;
import java.util.TreeSet;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.GL11;

import net.aegistudio.aoe2m.wyvern.asset.BlendShaderProgram;
import net.aegistudio.aoe2m.wyvern.asset.Blendomatic;
import net.aegistudio.aoe2m.wyvern.render.SlpTexture;
import net.aegistudio.aoe2m.wyvern.render.TextureManager;
import net.aegistudio.aoe2m.wyvern.terrain.Terrain;

public class BlendRenderer implements TileRenderer {
	public final TextureManager textureManager;
	public final TileManager tile;
	public final TileOutline outline;
	public final BlendShaderProgram program;
	public BlendRenderer(TileManager tileManagger, TextureManager textureManager, TileOutline outline, BlendShaderProgram program) {
		this.tile = tileManagger;
		this.textureManager = textureManager;
		this.outline = outline;
		this.program = program;
	}

	@Override
	public void prepare() throws LWJGLException {
		program.use();
	}
	
	private class BlendingObject {
		int id;	TileMetadata metadata;
		
		public BlendingObject(int id) {
			this.id = id;
			this.metadata = tile.require(id);
		}
	}
	
	@Override
	public void render(Terrain terrain, int x, int y) throws LWJGLException {
		TileMetadata current = tile.require(terrain.tile(x, y));
		if(current == null) return;
		
		TreeMap<Integer, Integer> adjacentMap = new TreeMap<>();
		TreeMap<Integer, Integer> diagonalMap = new TreeMap<>();
		
		TileNeighbour.adjacentBits(x, y, terrain, adjacentMap);
		TileNeighbour.diagonalBits(x, y, terrain, diagonalMap, adjacentMap);
		
		TreeSet<Integer> candidate = new TreeSet<>();
		candidate.addAll(adjacentMap.keySet());
		candidate.addAll(diagonalMap.keySet());
		
		BlendingObject[] objects = candidate.stream()
			.map(BlendingObject::new)
			.filter(obj -> obj.metadata != null)
			.filter(obj -> obj.metadata.overlay > current.overlay)
			.sorted((obj1, obj2) -> obj1.metadata.overlay - obj2.metadata.overlay)
			.toArray(BlendingObject[]::new);
		
		for(BlendingObject object : objects) {
			int adjacentMask = adjacentMap.getOrDefault(object.id, 0);
			if(adjacentMask != 0) {	
				int adjacenceId = Blendomatic.getAdjacenceBlend(adjacentMask, x, y);
				renderTile(object, x, y, terrain, adjacenceId);
			}
			
			int diagonalMask = diagonalMap.getOrDefault(object.id, 0);
			if(diagonalMask != 0) 
				Blendomatic.getDiagonalBlend(diagonalMask, mask -> 
					renderTile(object, x, y, terrain, mask));
		}
	}
	
	private void renderTile(BlendingObject object, int x, int y, Terrain terrain, int mask) throws LWJGLException {
		textureManager.bind(object.metadata.texture, program::neighbour);
		SlpTexture neighbourTexture = (SlpTexture) object.metadata.getTexture(x, y);
		
		textureManager.bind(object.metadata.blendomatic, program::mask);
		SlpTexture maskTexture = (SlpTexture) object.metadata.blendomatic.get(mask);
		
		GL11.glBegin(GL11.GL_QUADS);
			neighbourTexture.left(program::neighbourCoord);
			maskTexture.left(program::maskCoord); 
			outline.left(GL11::glVertex2d, terrain, x, y);

			neighbourTexture.bottom(program::neighbourCoord);
			maskTexture.bottom(program::maskCoord);
			outline.bottom(GL11::glVertex2d, terrain, x, y);

			neighbourTexture.right(program::neighbourCoord);
			maskTexture.right(program::maskCoord);
			outline.right(GL11::glVertex2d, terrain, x, y);
			
			neighbourTexture.top(program::neighbourCoord);
			maskTexture.top(program::maskCoord);
			outline.top(GL11::glVertex2d, terrain, x, y);
		GL11.glEnd();
	}

	@Override
	public void cleanup() throws LWJGLException {
		program.unuse();
	}
}
