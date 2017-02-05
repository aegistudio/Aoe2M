package net.aegistudio.aoe2m.drs.viewer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class HexDumpView extends JPanel {
	public static final Font SELECTED_FONT;
	static {
		Font[] allFonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
		Font candidate = allFonts[0];
		for(Font font : allFonts) {
			if(font.getFontName().toLowerCase().contains("courier")) {
				candidate = font.deriveFont(Font.BOLD, 14);
				break;
			}
		}
		SELECTED_FONT = candidate;
	}
	
	private static final long serialVersionUID = 1L;
	public final JTextArea textArea;
	public final int heximalPerLine;
	public final int innerSpacing;
	public final byte[] content;
	
	public HexDumpView(byte[] content, int heximalPerLine, int innerSpacing) {
		super();
		super.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		super.add(BorderLayout.CENTER, new JScrollPane(textArea));
		
		textArea.setFont(SELECTED_FONT);
		textArea.setEditable(false);
		
		textArea.setForeground(Color.GREEN);
		textArea.setBackground(Color.BLACK);
		
		this.heximalPerLine = heximalPerLine;
		this.innerSpacing = innerSpacing;
		this.content = content;
		
		JPanel arrangerBar = new JPanel();
		super.add(BorderLayout.NORTH, arrangerBar);
		
		int heap = Math.min(0x0200, content.length);
		
		Dimension dimension = new Dimension(80, 30);
		String[] radixStrings = new String[] {"H", "D"};
		int[] radices = new int[]{ 16, 10 };
		
		JTextField from = new JTextField("00000000");
		from.setHorizontalAlignment(JTextField.RIGHT);		
		from.setPreferredSize(dimension);
		
		JComboBox<String> fromType = new JComboBox<>(radixStrings);
		
		String toContent = Integer.toHexString(heap);
		toContent = "00000000" + toContent;
		toContent = toContent.substring(toContent.length() - 8);
		
		JTextField to = new JTextField(toContent);
		to.setHorizontalAlignment(JTextField.RIGHT);
		to.setPreferredSize(dimension);
		
		JComboBox<String> toType = new JComboBox<>(radixStrings);
		
		arrangerBar.add(new JLabel("<html><b>Base:</b> </html>"));
		arrangerBar.add(from);
		arrangerBar.add(fromType);
		
		arrangerBar.add(new JLabel("<html><b>Length:</b> </html>"));
		arrangerBar.add(to);
		arrangerBar.add(toType);
		
		JButton trim = new JButton("Trim");
		trim.addActionListener(a -> {
			try {
				int base = Integer.parseInt(from.getText(), 
						radices[fromType.getSelectedIndex()]);
				int length = Integer.parseInt(to.getText(), 
						radices[toType.getSelectedIndex()]);
				dumpInterval(base, base + length);
			}
			catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid heximal number format!");
			}
		});
		arrangerBar.add(trim);
		
		dumpInterval(0, heap);
	}
	
	public void dumpInterval(int floor, int ceil) {
		if(floor > ceil) {
			int temp = floor;
			floor = ceil;
			ceil = temp;
		}
		
		floor = Math.max(0, floor);
		ceil = Math.min(content.length, ceil);
		
		byte[] real = new byte[ceil - floor];
		System.arraycopy(content, floor, real, 0, real.length);
		dump(real, floor);
	}
	
	public void dump(byte[] content, int base) {
		StringBuilder dump = new StringBuilder();
		int lineCount = content.length + heximalPerLine - 1;
		lineCount /= heximalPerLine;
		
		for(int i = 0; i < lineCount; i ++) {
			StringBuilder heximal = new StringBuilder();
			StringBuilder ascii = new StringBuilder();
			
			int lineOffset = i * heximalPerLine;
			String lineString = Integer.toHexString(lineOffset + base);
			lineString = "00000000" + lineString;
			lineString = lineString.substring(lineString.length() - 8);
			dump.append(lineString + ": ");
			
			for(int j = 0; j < heximalPerLine; j ++) {
				int currentPointer = lineOffset + j;
				
				if(currentPointer < content.length) {
					int target = content[currentPointer] & 0x00ff;
					String hexString = "00" + Integer.toHexString(target);
					heximal.append(hexString.substring(hexString.length() - 2));
					
					if(target >= 0x20 && target < 0x080)
						ascii.append((char)target);
					else ascii.append('.');
				}
				else heximal.append("  ");
				
				if(((j + 1) % innerSpacing) == 0)
					heximal.append(' ');
			}
			
			dump.append(heximal.toString());
			dump.append('\t');
			dump.append(ascii.toString());
			dump.append('\n');
			
			textArea.setText(dump.toString());
		}
	}
}
