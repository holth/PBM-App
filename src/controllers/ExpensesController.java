package controllers;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
		expensesFrame.addExpense(new ActionListener() { // on-click of sign up
			public void actionPerformed(ActionEvent e) {
				
				addExpense();
			}
		});
		
		expensesFrame.updateStatus(new ActionListener() { // on-click of update status
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// change the status of an item from paid -> unpaid, unpaid -> paid
			}
		});
		
		expensesFrame.deleteExpense(new ActionListener() { // on-click of delete
			public void actionPerformed(ActionEvent e) {
				// TODO:
				// delete an item
				// reload 'ViewExpensesPanel' with all data
			}
		});
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
}