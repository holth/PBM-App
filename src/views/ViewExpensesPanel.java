package views;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import models.Expense_BLL;

public class ViewExpensesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ViewExpensesPanel(String username) {
		
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
        this.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0,0));
		scrollPane.setBounds(8, 117, 800, 411);
		this.add(scrollPane);
		
		String[] colNames = {
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
		
		DefaultTableModel model = null;
		try
		{
			Expense_BLL exp = new Expense_BLL();
			ArrayList<ArrayList<String>> expenses = exp.viewExpenseByUsername(username);
			String[][] data = new String[expenses.size()][10];
			for(int i = 0; i < expenses.size(); ++i)
			{
				for(int j = 0; j < 10; ++j)
				{
					data[i][j] = expenses.get(i).get(j);
				}
			}
			model = new DefaultTableModel(data, colNames);
		}catch(Exception e1){
			e1.printStackTrace();
		}
		
		JTable table = new JTable(model);
		table.setColumnSelectionAllowed(false);
	    table.setRowSelectionAllowed(true);
	    
	    table.getColumn("Expense ID").setPreferredWidth(0);
	    table.getColumn("Expense ID").setMinWidth(0);
	    table.getColumn("Expense ID").setMaxWidth(0);
		scrollPane.setViewportView(table);
		
		this.validate();
	}
	
}
