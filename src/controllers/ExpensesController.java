package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import models.*;
import views.*;

public class ExpensesController {
	
	private ExpensesFrame expensesFrame;
	private ViewExpensesPanel viewExpensesPanel;
	private AddExpensePanel addExpensePanel;
	
	private CardLayout cLayout = new CardLayout();
	
	private String username;
	private String expenseType = "unknown";
	
	/**
	 * 
	 * @param username, to load expenses data
	 */
	public ExpensesController(String username) {
		
		this.username = username;
		
		expensesFrame = new ExpensesFrame(); 						// load frame
		expensesFrame.middlePanel.setLayout(cLayout);				// set layout to CardLayout
		
		viewExpensesPanel = new ViewExpensesPanel(username);		// load view expenses
		addExpensePanel = new AddExpensePanel(expenseType);			// load add expenses
		
		expensesFrame.middlePanel.add(viewExpensesPanel, "view"); 	// add view expenses to layout
		expensesFrame.middlePanel.add(addExpensePanel, "add");		// add add expenses to layout
		
	}

	public void viewExpenses() {
		
		expensesFrame.btnsForAddExpense.setVisible(false);	// hide buttons for add expense
		expensesFrame.btnsForViewExpenses.setVisible(true);	// show buttons for view expenses
		
		cLayout.show(expensesFrame.middlePanel, "view");	// show view expenses layout
		
		/// Listener methods ///
		
		expensesFrame.filterExpenses(new ActionListener() { // on-click of filter
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// filter expenses by type, category, provider from the combobox
				// reload 'ViewExpensesPanel' with new data
			}
		});
		
		expensesFrame.clearFilter(new ActionListener() { // on-click of clear filter
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// clear combobox for filters
				// reload 'ViewExpensesPanel' with all data
			}
		});
		
		expensesFrame.addExpense(new ActionListener() { // on-click of add expense
			public void actionPerformed(ActionEvent e) {
				addExpense();
				return;
			}
		});
		
		expensesFrame.updateStatus(new ActionListener() { // on-click of update status
			public void actionPerformed(ActionEvent e) {
				updateStatus();
			}
		});
		
		expensesFrame.deleteExpense(new ActionListener() { // on-click of delete
			public void actionPerformed(ActionEvent e) {
				deleteExpense();
			}
		});
		
		// need updateAll...
		// need deleteAll...
	}
	
	public void addExpense() {
		
		expensesFrame.topPanel.setVisible(false);				// hide top panel
		expensesFrame.btnsForViewExpenses.setVisible(false);	// hide buttons for view expenses
		expensesFrame.btnsForAddExpense.setVisible(true);		// show buttons for add expense
		
		cLayout.show(expensesFrame.middlePanel, "add");			// show add expense layout
		
		/// Listener methods ///
		
		expensesFrame.viewExpenses(new ActionListener() { // on-click of view expense
			public void actionPerformed(ActionEvent e) {
				
				expensesFrame.topPanel.setVisible(true);	// put back the top panel
				viewExpenses();
				return;
			}
		});
		
		expensesFrame.saveExpense(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// get value from fields, see 'Get method' section of 'AddExpensePanel.java'
				// e.g. AddExpensePanel.getProvider()
				// use Expense_BLL.addExpense() to add expense
				// reload 'ViewExpensesPanel' with new data
			}
		});
	}
	
	public void updateStatus() {
		JTable table = viewExpensesPanel.table;

		int row = table.getSelectedRow();	// get current selected row
		
		if(row == -1) 						// no row selected
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 0));
			String mode = (String) table.getValueAt(row, 7);
			String oldstatus = (String) table.getValueAt(row, 8);
			String newstatus;
			
			if(oldstatus.equalsIgnoreCase("Paid"))
				newstatus = "UNPAID";
			else
				newstatus = "PAID";
			
			if((mode.equalsIgnoreCase("Cash") || mode.equalsIgnoreCase("Debit")) && newstatus.equalsIgnoreCase("Unpaid")) {
				JOptionPane.showMessageDialog(null, "ERROR: \"UNPAID\" is only available for expenses paid by Credit Card");
				return;	
			}
											
			try {
				Expense_BLL expense = new Expense_BLL();
				expense.updateExpense(expenseID, "status", newstatus);
				JOptionPane.showMessageDialog(null, "Expense has been changed!");
				expensesFrame.middlePanel.remove(viewExpensesPanel);;
				viewExpensesPanel = new ViewExpensesPanel(username);
				expensesFrame.middlePanel.add(viewExpensesPanel, "view");
				cLayout.show(expensesFrame.middlePanel, "view");
				return;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		}
	}
	
	public void deleteExpense() {
		JTable table = viewExpensesPanel.table;

		int row = table.getSelectedRow();	// get current selected row
		
		if(row == -1) 						// no row selected
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 0));
											
			try {
				Expense_BLL expense = new Expense_BLL();
				expense.deleteExpense(expenseID);
				JOptionPane.showMessageDialog(null, "Expense has been deleted!");
				expensesFrame.middlePanel.remove(viewExpensesPanel);
				viewExpensesPanel = new ViewExpensesPanel(username);
				expensesFrame.middlePanel.add(viewExpensesPanel, "view");
				cLayout.show(expensesFrame.middlePanel, "view");
				return;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return;
			}
		}
	}
	
}