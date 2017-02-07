package net.aegistudio.aoe2m.drs.viewer;

import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.tree.DefaultMutableTreeNode;

import org.junit.Test;

import net.aegistudio.aoe2m.CorruptionException;
import net.aegistudio.aoe2m.drs.Archive;
import net.aegistudio.aoe2m.ra.RandomFileAdapter;

/**
 * A stand-alone viewer that is designed to open
 * and read drs archive.
 * 
 * @author aegistudio
 *
 */

public class DrsViewer {
	public static final String LOOK_AND_FEEL = 
				"com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
	
	public @Test void test() throws CorruptionException, IOException, InterruptedException {
		try { UIManager.setLookAndFeel(LOOK_AND_FEEL); } catch(Exception e) {}
		
		// Initialize file chooser.
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new FileFilter() {
			@Override
			public boolean accept(File arg0) {
				if(arg0.isDirectory()) return true;
				return arg0.getName().endsWith(".drs");
			}

			@Override
			public String getDescription() {
				return ".DRS Archive (*.drs)";
			}
		});
		
		// Open file.
		int status = fileChooser.showOpenDialog(null);
		if(status != JFileChooser.APPROVE_OPTION) return;
		
		File file = fileChooser.getSelectedFile();
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		Archive archive = Archive.open(new RandomFileAdapter(randomFile));
		
		// Open required palette (for slp render).
		RandomAccessFile interfaceRandom = new RandomAccessFile(
				new File(file.getParentFile(), "interfac.drs"), "r");
		Archive interfac = Archive.open(new RandomFileAdapter(interfaceRandom));
		
		// Open viewer.
		JFrame jframe = new JFrame("DRS Viewer");
		jframe.setLocationRelativeTo(null);
		jframe.setSize(600, 480);
		
		JTabbedPane tabPane = new JTabbedPane();
		jframe.add(BorderLayout.CENTER, tabPane);
		
		JTree contentTable = new JTree();
		contentTable.setModel(new DrsTableModel(file, archive, interfac));
		contentTable.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent arg0) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) 
						arg0.getPath().getLastPathComponent();
				Object object = treeNode.getUserObject();
				
				if(object instanceof ViewObject) {
					ViewObject vo = (ViewObject)object;
					tabPane.removeAll();

					vo.openView().forEach(entry ->
							tabPane.add(entry.name, entry.component));
				}
			}
		});
		jframe.add(BorderLayout.WEST, new JScrollPane(contentTable));
		
		jframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jframe.setVisible(true);
		
		while(true) { 
			if(!jframe.isDisplayable()) return;
			Thread.sleep(1000);
		}
	}
}
