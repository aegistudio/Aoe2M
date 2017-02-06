package net.aegistudio.aoe2m.drs.viewer;

import java.io.File;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import net.aegistudio.aoe2m.drs.Archive;
import net.aegistudio.aoe2m.drs.TableEntry;
import net.aegistudio.aoe2m.drs.TableHeader;
import net.aegistudio.aoe2m.pal.Palette;

public class DrsTableModel extends DefaultTreeModel {
	private static final long serialVersionUID = 1L;
	
	protected final DefaultMutableTreeNode archive;
	
	public DrsTableModel(File file, Archive archive, Palette palette) {
		super(new DefaultMutableTreeNode(
				new ArchiveViewObject(file, archive)));
		
		this.archive = (DefaultMutableTreeNode) super.getRoot();
		
		for(int i = 0; i < archive.numTables(); i ++) {
			TableHeader table = archive.getTable(i);
			TableViewObject tableVo = new TableViewObject(i, table);
			DefaultMutableTreeNode tableNode = 
					new DefaultMutableTreeNode(tableVo);
			this.archive.add(tableNode);
			for(TableEntry entry : table.entries.list()) 
				tableNode.add(new DefaultMutableTreeNode(
						new EntryViewObject(archive, tableVo, palette, entry)));
		}
	}
}
