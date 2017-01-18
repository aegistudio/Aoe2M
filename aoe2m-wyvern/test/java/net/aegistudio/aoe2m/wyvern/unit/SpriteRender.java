package net.aegistudio.aoe2m.wyvern.unit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.lwjgl.LWJGLException;

import net.aegistudio.aoe2m.assetdba.EnumLayer;
import net.aegistudio.aoe2m.assetdba.GraphicsDelta;
import net.aegistudio.aoe2m.assetdba.GraphicsGamedata;
import net.aegistudio.aoe2m.wyvern.terrain.BlendingRender;

public class SpriteRender extends BlendingRender {
	protected final GraphicsManager graphicsManager;
	protected final SpriteRenderer spriteRenderer;
	
	public SpriteRender() throws IOException, LWJGLException {
		super();
		graphicsManager = new GraphicsManager(connection);
		spriteRenderer = new SpriteRenderer(outline, textureManager);
	}
	
	public List<GraphicsInstruction[]> instructions = Collections.synchronizedList(new ArrayList<>());
	
	// To place unit from user instruction.
	public Thread placementThread = new Thread() {
		public void run() {
			try {
				System.out.println("Please entry the graphic id to render.");
				System.out.println("Format: <id> <x> <y> <z> <frame> <angle>");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				for(String line = reader.readLine(); line != null; line = reader.readLine()) try {
					String[] p = line.split(" ");
					GraphicsInstruction[] result = make(Integer.parseInt(p[0]), 
							Double.parseDouble(p[1]), Double.parseDouble(p[2]));
					for(GraphicsInstruction item : result) {
						if(item.sprite != null && item.sprite.gamedata.layer.ordinal() >= EnumLayer.UNIT.ordinal())
							item.z = Integer.parseInt(p[3]);
						
						item.frame = Integer.parseInt(p[4]);
						item.angle = Integer.parseInt(p[5]);
					}
					instructions.add(result);
					instructions.sort((inst1, inst2) -> {
						double sig1 = inst1[0].x - inst1[0].y;
						double sig2 = inst2[0].x - inst2[0].y;
						if(sig1 > sig2) return +1;
						if(sig1 < sig2) return -1;
						return 0;
					});
				} catch (RuntimeException e) {
					e.printStackTrace(System.out);
				}
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	};
	
	public void prepare() throws LWJGLException {
		super.prepare();
		placementThread.start();
	}
	
	public void render() throws LWJGLException {
		super.render();
		
		spriteRenderer.prepare();
			for(GraphicsInstruction[] insts : instructions)
				for(GraphicsInstruction inst : insts) {
					spriteRenderer.draw(terrain, inst);
					if(inst.sprite != null)
						inst.frame += inst.sprite.gamedata.frameRate;
				}
		spriteRenderer.cleanup();
	}
	
	public GraphicsInstruction[] make(int graphics, double unitX, double unitY) {
		GraphicsGamedata unit = connection.graphics().query(graphics);
		GraphicsDelta[] populate = new GraphicsDelta[unit.deltas.length + 1];
		populate[0] = new GraphicsDelta(unit.id, 0, 0);
		System.arraycopy(unit.deltas, 0, populate, 1, unit.deltas.length);
		populate = Arrays.stream(populate)
			.filter(item -> connection.graphics().query(item.deltaGraphic) != null)
			.sorted((item1, item2) -> {
				int ly1 = connection.graphics().query(item1.deltaGraphic).layer.ordinal();
				int ly2 = connection.graphics().query(item2.deltaGraphic).layer.ordinal();
				return ly1 - ly2;
			}).toArray(GraphicsDelta[]::new);
		
		GraphicsInstruction[] instructions = Arrays.stream(populate)
				.map(delta -> {
					GraphicsInstruction instruction = new GraphicsInstruction();
					instruction.sprite = graphicsManager.require(delta.deltaGraphic);
					instruction.angle = 0;
					instruction.frame = 0;
					instruction.x = unitX + delta.directionX;
					instruction.y = unitY + delta.directionY;
					instruction.z = 0;
					return instruction;
				}).toArray(GraphicsInstruction[]::new);
		return instructions;
	}

	public void dispose() throws LWJGLException {
		super.dispose();
		placementThread.interrupt();
	}
}
