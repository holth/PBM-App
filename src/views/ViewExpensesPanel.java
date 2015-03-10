package views;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import org.jdesktop.swingx.JXTreeTable;

import models.Expense_BLL;

public class ViewExpensesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JXTreeTable table;
	
	public ViewExpensesPanel(String username, String category, String expenseType, String status) {
		
        this.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
        this.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(0,0));
		scrollPane.setBounds(8, 117, 800, 411);
		this.add(scrollPane);
		
		try {
			Expense_BLL exp = new Expense_BLL();
			List<String[]> content = new ArrayList<>();
			
			if(category.equalsIgnoreCase("ALL")) {
				ArrayList<String> categories = exp.getCategoriesBy(username, expenseType, status);
				
				for(int catIndex = 0; catIndex < categories.size(); catIndex++) {
					String header = categories.get(catIndex);
					ArrayList<ArrayList<String>> expenses = exp.viewExpenseBy(username, header, expenseType, status);
					
					if(expenses.size() > 0) {
						List<String[]> tempList = new ArrayList<>();
						float totalAmount = 0;
						String rootStatus = "PAID";
						
						for(int expIndex = 0; expIndex < expenses.size(); expIndex++){
							String[] expense = new String[11];
							
							for(int col = 0; col < 11; col++)
								expense[col] = expenses.get(expIndex).get(col);
							
							if(expenses.get(expIndex).get(9).equalsIgnoreCase("UNPAID"))
								rootStatus = "UNPAID";
							
							totalAmount += Float.parseFloat(expenses.get(expIndex).get(5));
							tempList.add(expense);
						}
						
						content.add(new String[] {header,"","","","",String.format("%.2f", totalAmount),"","","",rootStatus});
						content.addAll(tempList);
					}
				}
			} else {
				ArrayList<ArrayList<String>> expenses = exp.viewExpenseBy(username, category, expenseType, status);
				
				if(expenses.size() > 0) {
					List<String[]> tempList = new ArrayList<>();
					float totalAmount = 0;
					String rootStatus = "PAID";
					
					for(int expIndex = 0; expIndex < expenses.size(); expIndex++){
						String[] expense = new String[11];
						
						for(int col = 0; col < 11; col++)
							expense[col] = expenses.get(expIndex).get(col);
						
						if(expenses.get(expIndex).get(9).equalsIgnoreCase("UNPAID"))
							rootStatus = "UNPAID";
						
						totalAmount += Float.parseFloat(expenses.get(expIndex).get(5));
						tempList.add(expense);
					}
					
					content.add(new String[] {category,"","","","",String.format("%.2f", totalAmount),"","","",rootStatus});
					content.addAll(tempList);
				}
			}
			
			table = new DataTable(content).createTreeTable();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		scrollPane.setViewportView(table);
		
		this.validate();
	}
	
	/// Getter methods ///
	
	public JXTreeTable getTable() {
		
		return table;
	}
	
}
