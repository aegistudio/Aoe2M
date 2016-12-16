package net.aegistudio.aoe2m.scx.input;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import net.aegistudio.aoe2m.scx.Scenario;
import net.aegistudio.aoe2m.scx.ScenarioDirector;
import net.aegistudio.aoe2m.scx.map.EnumTerrian;
import net.aegistudio.aoe2m.scx.map.MapPo;
import net.aegistudio.aoe2m.scx.map.UnitPo;
import net.aegistudio.aoe2m.scx.meta.GlobalVictoryPo;
import net.aegistudio.aoe2m.scx.meta.MetadataPo;
import net.aegistudio.aoe2m.scx.msg.Message;
import net.aegistudio.aoe2m.scx.trigger.TriggerListPo;

public class ScenarioInputStream extends InputStream {
	private final InputStream inputStream;
	private final String charset;
	
	public ScenarioInputStream(InputStream inputStream, String charset) {
		this.inputStream = inputStream;
		this.charset = charset;
	}
	
	@Override
	public int read() throws IOException {
		return this.inputStream.read();
	}
	
	public Scenario readScenario() throws Exception {
		Scenario scenario = new Scenario();
		FieldInputStream fieldInputStream = new FieldInputStream(inputStream, charset);
		scenario.getMetadataBuilder().readUncompressedHeader(fieldInputStream);
		
		Inflater inflater = new Inflater(true);
		InflaterInputStream inflateInput = new InflaterInputStream(fieldInputStream, inflater);
		fieldInputStream = new FieldInputStream(inflateInput, charset);
		
		FieldInputTranslator translator = new FieldInputTranslator(fieldInputStream);
		new ScenarioDirector().build(scenario, translator);
		return scenario;
	}
	
	@SuppressWarnings("serial")
	public static void main(String[] arguments) throws Exception {
		FileInputStream fileInputStream = new FileInputStream("/home/luohaoran/age.of.empire.2.conqueror/Scenario/stub.scx");
		ScenarioInputStream sceneInputStream = new ScenarioInputStream(fileInputStream, "gbk");
		Scenario sceneInput = sceneInputStream.readScenario();
		
		MetadataPo metadata = sceneInput.getMetadata();
		System.out.println("***************** Metadata *****************");
		System.out.printf("Version: %s\n", metadata.version);
		System.out.printf("Last saved: %s\n", new Date(metadata.lastSavedTimestamp));
		System.out.printf("Instruction: %s\n", metadata.scenarioInstruction);
		System.out.printf("Player count: %s\n", metadata.playerCount);
		System.out.printf("Next unit id: %s\n", metadata.nextUnitId);
		System.out.printf("Original file name: %s\n", metadata.originalFileName.getValue());
		System.out.println();

		for(int i = 0; i < metadata.playerCount; i ++) {
			System.out.printf("***************** Player[%d] *****************\n", i);
			System.out.println(sceneInput.getPlayerTable()[i]);
			System.out.println();
		}
		
		System.out.println("***************** Messages *****************");
		Message message = sceneInput.getMessage();
		System.out.printf("Instruction(@%d): %s\n", message.instructionsIndex.getValue(), message.instructions);
		System.out.printf("Hints(@%d): %s\n", message.hintsIndex.getValue(), message.hints);
		System.out.printf("History(@%d): %s\n", message.historyIndex.getValue(), message.history);
		System.out.printf("Victory(@%d): %s\n", message.victoryIndex.getValue(), message.victory);
		System.out.printf("Loss(@%d): %s\n", message.lossIndex.getValue(), message.loss);
		System.out.printf("Scouts(@%d): %s\n",  message.scoutsIndex.getValue(), message.scouts);
		System.out.println();
		
		System.out.println("***************** GlobalVictory *****************");
		GlobalVictoryPo victory = sceneInput.getGlobalVictory();
		System.out.printf("Victory mode: %s\n", victory.victoryMode);
		System.out.printf("Custom param: (%s)\n", victory.allCustomCondition.getValue()? "all": "some");
		System.out.printf("- Conquer: %s\n", victory.customConquer.getValue()?"Y":"N");
		System.out.printf("- Relics: %d\n", victory.customMinRelic.getValue());
		System.out.printf("- Explore percentage: %d %%\n", victory.customExplorePercent.getValue());
		System.out.printf("Required score: %d\n", victory.requiredScore.getValue());
		System.out.printf("Required countdown (1 = 10yr): %d\n", victory.requiredTime.getValue());
		System.out.println();

		System.out.println("***************** Map *****************");
		MapPo map = sceneInput.getMap();
		BufferedImage image = new BufferedImage((int)(long)(map.mapWidth.getValue()), 
				(int)(long)(map.mapHeight.getValue()), BufferedImage.TYPE_3BYTE_BGR);
		
		WritableRaster raster = image.getRaster();
		
		for(int i = 0; i < map.mapWidth.getValue(); i ++) {
			for(int j = 0; j < map.mapHeight.getValue(); j ++) {
				EnumTerrian terrian = EnumTerrian.getTerrian(map.terrianId.terrianValue[i][j]);
				System.out.printf("%d/%d ", map.terrianId.terrianValue[i][j], map.elevation.terrianValue[i][j]);
				if(terrian != null) {
					raster.setSample(i, j, 0, terrian.r);
					raster.setSample(i, j, 1, terrian.g);
					raster.setSample(i, j, 2, terrian.b);
				}
			}
			System.out.println();
		}
		System.out.println();

		int fold = 7;
		Image newImage = image.getScaledInstance(fold * image.getWidth(), fold * image.getHeight(), Image.SCALE_FAST);
		
		JFrame terrianFrame = new JFrame("Terrian");
		terrianFrame.setSize(fold * image.getWidth(), fold * image.getHeight());
		terrianFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel jlabel = new JLabel() {
			public void paint(Graphics g) {
				super.paint(g);
				String labelText = this.getText();
				g.setColor(Color.WHITE);
				g.fillRect(0, 0, g.getFontMetrics().stringWidth(labelText), g.getFontMetrics().getHeight());
				g.setColor(Color.BLACK);
				g.drawString(labelText, 0, g.getFontMetrics().getHeight() - 1);
			}
		};
		jlabel.setIcon(new ImageIcon(newImage));
		jlabel.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent me) {
				Point location = jlabel.getLocationOnScreen();
				int width = Math.round(-0.5f + 1.0f * (me.getXOnScreen() - location.x) * image.getWidth() / jlabel.getWidth());
				int height = Math.round(-0.5f + 1.0f * (me.getYOnScreen() - location.y) * image.getHeight() / jlabel.getHeight());
				int terrian = map.terrianId.terrianValue[width][height];
				
				jlabel.setText(String.format("(%d, %d, %d): %s(%d)", (int)width, (int)height, 
						map.elevation.terrianValue[width][height], EnumTerrian.getTerrian((byte) terrian), terrian));
				jlabel.updateUI();
			}
		});
		terrianFrame.add(jlabel);
		terrianFrame.setVisible(true);
		
		System.out.printf("Units for gaia: %d\n", map.gaia.getValue());
		for(int i = 0; i < map.gaia.getValue(); i ++) {
			UnitPo unit = map.gaia.unitPo[i];
			System.out.printf("- Unit#%d: %d @ (%f, %f, %f) %s \n", unit.unitId.getValue(), unit.unitType.getValue(), 
					unit.positionX.getValue(), unit.positionY.getValue(), unit.positionHeight.getValue(), unit.indicator);
		}
		
		for(int n = 0; n < map.playerCount.getValue() - 1; n ++) {
			System.out.printf("Units for player[%d]: %d\n", n, map.units[n].getValue());
			for(int i = 0; i < map.units[n].getValue(); i ++) {
				UnitPo unit = map.units[n].unitPo[i];
				System.out.printf("- Unit#%d: %d @ (%f, %f, %f) %s\n", unit.unitId.getValue(), unit.unitType.getValue(), 
						unit.positionX.getValue(), unit.positionY.getValue(), unit.positionHeight.getValue(), unit.indicator);
			}
		}
		
		System.out.println("***************** Triggers *****************");
		TriggerListPo triggerList = sceneInput.getTriggerList();
		System.out.printf("%d triggers found." ,triggerList.element.length);
		
		sceneInputStream.close();
	}
}
