package views;

import java.util.*;

import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.treetable.AbstractMutableTreeTableNode;
import org.jdesktop.swingx.treetable.DefaultTreeTableModel;

public class DataTable {
	
	private String[] colName = {
			"Category",
			"Expense ID",
			"Expense Type",
            "Provider Name",
            "Location",
            "Amount",
            "DateTime",
            "Interval",
            "Payment Mode",
            "Status",
            "Due Date",
	};
	private Node root;
	private DefaultTreeTableModel model;
	private JXTreeTable table;
	private List<String[]> content = new ArrayList<>();

	/**
	 * Constructor
	 * @param content
	 */
	public DataTable(List<String[]> content) {
		this.content = content;
	}

	/**
	 * Create a 'tree table' based on the provided content
	 * @return
	 */
	public JXTreeTable createTreeTable() {
		root = new RootNode("Root");

		ChildNode myChild = null;
		
		for (String[] data : this.content) {
			ChildNode child = new ChildNode(data);
			if (data.length <= 10) {
				root.add(child);
				myChild = child;
			} else {
				myChild.add(child);
			}
		}

		model = new DefaultTreeTableModel(root, Arrays.asList(colName));
		table = new JXTreeTable(model);
		table.setShowGrid(true, true);
		table.setColumnControlVisible(true);
		
		table.getColumn("Expense ID").setPreferredWidth(0);
		table.getColumn("Expense ID").setMinWidth(0);
		table.getColumn("Expense ID").setMaxWidth(0);
		
		table.expandAll();

		return table;
	}
	
	/// Node classes ///
	
	class Node extends AbstractMutableTreeTableNode {

		public Node(Object[] data) {
			super(data);
		}

		@Override
		public int getColumnCount() {
			return getData().length;
		}

		@Override
		public Object getValueAt(int columnIndex) {
			return getData()[columnIndex];
		}

		public Object[] getData() {
			return (Object[]) getUserObject();
		}
	}
	
	class RootNode extends Node {

		public RootNode(String key) {
			super(new Object[] { key });
		}

	}
	
	class ChildNode extends Node {

		public ChildNode(Object[] data) {
			super(data);
		}

	}

}
