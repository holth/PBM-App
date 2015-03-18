package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.jdesktop.swingx.JXTreeTable;

import models.*;
import views.*;

public class ExpensesController {
	
	private ExpensesFrame expensesFrame;
	private ViewExpensesPanel viewExpensesPanel;
	private AddExpensePanel purchaseExpensePanel;
	private AddExpensePanel billExpensePanel;
	
	private String username;
	
	/**
	 * Constructor
	 * @param ExpensesFrame
	 */
	public ExpensesController(ExpensesFrame expensesFrame) {
		
		this.username = expensesFrame.getUsername();
		
		this.expensesFrame = expensesFrame;
		this.viewExpensesPanel = expensesFrame.getExpensesPanel();
		this.purchaseExpensePanel = expensesFrame.getPurchasePanel();
		this.billExpensePanel = expensesFrame.getBillPanel();
		
		this.listener();
	}

	/**
	 * View the expenses table in full and hide the 'add expense' form
	 */
	public void viewExpenses() {
		
		expensesFrame.hideExpenseForm();
	}
	
	/**
	 * Show the 'add expense' form for user to create new expense
	 */
	public void addExpense() {
		
		JRadioButton radioPurchase = new JRadioButton("Purchase");
		JRadioButton radioBill = new JRadioButton("Bill");
		radioPurchase.setSelected(true);
		
		final ButtonGroup group = new ButtonGroup();
        group.add(radioPurchase);
        group.add(radioBill);
        
        JPanel panel = new JPanel();
        panel.add(radioPurchase);
        panel.add(radioBill);
		
		int selection = JOptionPane.showOptionDialog(null, panel, 
				"Select Expense Type", JOptionPane.OK_CANCEL_OPTION, 
				JOptionPane.QUESTION_MESSAGE, null, null, null);
		
		if(selection == JOptionPane.OK_OPTION)
		{
			boolean isPurchase = radioPurchase.isSelected();
    		boolean isBill = radioBill.isSelected();
    		if(isPurchase || isBill)
    		{

        		if(isPurchase)
        		{
        			expensesFrame.showAddPurchaseForm();
        		}
        		else
        		{
        			expensesFrame.showAddBillForm();
        		}
    		}
		}
	}
	
	
	/**
	 * Save the 'new expense' in DB
	 * @param Expense object
	 */
	public void saveExpense(Expense newExpense) {
		
		try {
			if(newExpense.save(username)) {
				if(newExpense.getExpenseType().equals("Purchase"))
					purchaseExpensePanel.clearAllFields();
				else
					billExpensePanel.clearAllFields();
				expensesFrame.hideExpenseForm();
				refreshData();
				System.out.println("Expense save successfully!");
				JOptionPane.showMessageDialog(null, "Expense saved successfully!");
			}
			
		} catch(Exception e) {
			System.out.println("Unable to save expense!");
			JOptionPane.showMessageDialog(null, "Unable to save expense!");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Update status of expense(s) based on user selection
	 * If user select a category, the expenses in that category will be updated.
	 */
	public void updateStatus(JXTreeTable table, int row) {
		
		if(row < 0) {
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(table.getValueAt(row, 1).equals("")) {
			String rootCategory = (String) table.getValueAt(row, 0);
			String oldstatus = (String) table.getValueAt(row, 9);
			String newstatus;
			
			if(oldstatus.equalsIgnoreCase("paid"))
				newstatus = "UNPAID";
			else
				newstatus = "PAID";
			
			row++;
			
			String mode = (String) table.getValueAt(row, 8);
			int cashOrDebitExpenses = 0;
			int creditExpenses = 0;
			
			while(row < table.getRowCount() && table.getValueAt(row, 0).equals(rootCategory)) {
				int expenseID = Integer.parseInt((String) table.getValueAt(row, 1));
				
				if((mode.equalsIgnoreCase("Cash") || mode.equalsIgnoreCase("Debit")) 
						&& newstatus.equalsIgnoreCase("Unpaid")) {
					cashOrDebitExpenses++;
					System.out.println("Status of " + expenseID + "-" + table.getValueAt(row, 3)
							+ " cannot be changed!"
							+ " \"UNPAID\" status is only available for expenses paid by Credit Card");
				} else {
					try {
						Expense_BLL expense = new Expense_BLL();
						expense.updateExpense(expenseID, "status", newstatus);
						System.out.println("Status of " + expenseID + "-" + table.getValueAt(row, 3)
								+ " changed to " + newstatus +".");
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					creditExpenses++;
				}
				row++;
			}
			refreshData();
			
			String msgDialog = "";
			
			if(creditExpenses > 0)
				msgDialog += "Status of " + creditExpenses + " expense(s) changed to " + newstatus + ".";
			
			if(creditExpenses > 0 && cashOrDebitExpenses > 0)
				msgDialog += "\n\n";
			
			if(cashOrDebitExpenses > 0)
				msgDialog += "Status of " + cashOrDebitExpenses + " expense(s) cannot be changed "
						+ " since they were paid by \"CASH\" or \"DEBIT\"."
						+ "\n\"UNPAID\" status is only available for expenses paid by Credit Card.";
				
			JOptionPane.showMessageDialog(null, msgDialog);
			
		} else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 1));
			String mode = (String) table.getValueAt(row, 8);
			String oldstatus = (String) table.getValueAt(row, 9);
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
				System.out.println("Expense #" + expenseID + " changed to " + newstatus +".");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			refreshData();
			JOptionPane.showMessageDialog(null, "Status has been changed to " + newstatus +".");
		}
		
	}
	
	/**
	 * Delete expense(s) based on user selection
	 * If user select a category, the expenses in that category will deleted.
	 */
	public void deleteExpense(JXTreeTable table, int row) {
		
		if(row < 0) {					// no row selected
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		if(table.getValueAt(row, 1).equals("")) {
			String rootCategory = (String) table.getValueAt(row, 0);
			int expensesCount = 0;
			row++;
			
			while(row < table.getRowCount() && table.getValueAt(row, 0).equals(rootCategory)) {
				int expenseID = Integer.parseInt((String) table.getValueAt(row, 1));
				
				try {
					Expense_BLL expense = new Expense_BLL();
					expense.deleteExpense(expenseID);
					System.out.println("Expense #" + expenseID + " deleted successfully!");
					expensesCount++;
				} catch(Exception e) {
					e.printStackTrace();
				}
				row++;
			}

			refreshData();
			if(expensesCount > 0)
				JOptionPane.showMessageDialog(null, expensesCount + " expense(s) deleted successfully!");
		} else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 1));
			
			try {
				Expense_BLL expense = new Expense_BLL();
				expense.deleteExpense(expenseID);
				System.out.println("Expense #" + expenseID + " deleted successfully!");
				refreshData();
				JOptionPane.showMessageDialog(null, "Expense #" + expenseID + " deleted successfully!");
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void filterExpenses() {
		String category = expensesFrame.getFilterCategory();
		String type = expensesFrame.getFilterType();
		String status = expensesFrame.getFilterStatus();
		
		if(category.equalsIgnoreCase("category"))
			category = "all";
		if(type.equalsIgnoreCase("type"))
			type = "all";
		if(status.equalsIgnoreCase("status"))
			status = "all";
		
		System.out.println("Filter by: " + category + ", " + type + ", " + status + ".");
		
		viewExpensesPanel = new ViewExpensesPanel(username, category, type, status);
		
		expensesFrame.getMiddlePanel().removeAll();
		expensesFrame.getMiddlePanel().add(viewExpensesPanel, BorderLayout.CENTER);
		expensesFrame.getMiddlePanel().revalidate();
	}
	
	/**
	 * Listen to buttons
	 */
	private void listener() {
		
		expensesFrame.addExpense(new ActionListener() { // on-click of add expense
			public void actionPerformed(ActionEvent e) {
				System.out.println("Add new expenses:");
				addExpense();
			}
		});
		
		expensesFrame.updateStatus(new ActionListener() { // on-click of update status
			public void actionPerformed(ActionEvent e) {
				System.out.println("Update status:");
				JXTreeTable table = viewExpensesPanel.getTable();
				int row = table.getSelectedRow();
				updateStatus(table, row);	//move parameters here for the convenience of test
			}
		});
		
		expensesFrame.deleteExpense(new ActionListener() { // on-click of delete
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete expenses?");
				JXTreeTable table = viewExpensesPanel.getTable();
				int row = table.getSelectedRow();	// get current selected row
				deleteExpense(table, row); //move parameters here for the convenience of test
			}
		});
		
		billExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				
				//String type = "Bill";
				String category = billExpensePanel.getCategory();
				String bname = billExpensePanel.getProvider();
				String baddress = "Headquaters";
				//String bdate = "N/A";
				String bamount = billExpensePanel.getAmount();
				String binterval = billExpensePanel.getInterval();
				String bmode = billExpensePanel.getMode();
				String bstatus = billExpensePanel.getStatus();
				String bdue_date = billExpensePanel.getDueDate();
				
				if(bmode == "Cash" || bmode == "Debit")
				{
					bstatus = "Paid";
					bdue_date = "N/A";
				}
				if(bstatus == "Paid")
					bdue_date = "N/A";
				if(category.equals("") || bname.equals("") || bamount.equals("") || bmode.equals("") || bstatus.equals("")|| bdue_date.equals("") ||
						binterval.equals(""))
					errormessages("ERROR: Bill Fields are empty!");
				else if(!isNumeric(bamount)){
					errormessages("ERROR: Amount should be numeric!");
				}
				else 
				{
					Float bamountFloat = Float.valueOf(bamount);
					Expense billExpense = new BillExpense(category, bname, baddress, bamountFloat, binterval,
							bmode, bstatus, bdue_date);

					saveExpense(billExpense);
				}
			}
		});
		
		billExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				billExpensePanel.clearAllFields();
				viewExpenses();
			}
		});
		
		purchaseExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				
				//String type = "Purchase";
				String category = purchaseExpensePanel.getCategory();
				String pname = purchaseExpensePanel.getProvider();
				String paddress = purchaseExpensePanel.getAddress();
				String pdate = "";
				if(purchaseExpensePanel.getDate() != null)
					pdate = purchaseExpensePanel.getDate().toString();
				String pamount = purchaseExpensePanel.getAmount();
				//String pinterval = "N/A";
				String pmode = purchaseExpensePanel.getMode();
				String pstatus = purchaseExpensePanel.getStatus();
				String pdue_date = purchaseExpensePanel.getDueDate();
				
				if(pmode == "Cash" || pmode == "Debit")
				{
					pstatus = "Paid";
					pdue_date = "N/A";
				}
				if(pstatus == "Paid")
					pdue_date = "N/A";
				if(category.equals("") || pname.equals("") ||paddress.equals("") || pdate.equals("") ||pamount.equals("") ||pmode.equals("") 
						|| pstatus.equals("") || pdue_date.equals(""))
				{
					errormessages("ERROR: Purchase Fields are empty!");
				}
				else if(!isNumeric(pamount))
					errormessages("ERROR: Amount should be numeric!");
				else
				{
					float pamountFloat = Float.valueOf(pamount);
					Expense purchaseExpense = new PurchaseExpense(category, pname, paddress, pdate, pamountFloat, 
							pmode, pstatus, pdue_date);
		
					saveExpense(purchaseExpense);
				}
			
			}
		});
		
		purchaseExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				purchaseExpensePanel.clearAllFields();
				viewExpenses();
			}
		});
		
		expensesFrame.filterExpenses(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				filterExpenses();
			}
		});
		
		expensesFrame.clearFilter(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				expensesFrame.clearFilter();
				refreshData();
			}
		});
		
	}
	
	/**
	 * Refresh data table
	 */
	private void refreshData() {
		viewExpensesPanel = new ViewExpensesPanel(username, "all", "all", "all");
		
		expensesFrame.getMiddlePanel().removeAll();
		expensesFrame.getMiddlePanel().add(viewExpensesPanel, BorderLayout.CENTER);
		expensesFrame.getMiddlePanel().revalidate();
		
		expensesFrame.clearFilter();
	}
	
	/**
	 * Error Message Dialog
	 * @param errormessage
	 */
	private boolean errormessages(String errormessage)
	{
		JOptionPane.showMessageDialog(null, 
				errormessage, "ERROR", JOptionPane.ERROR_MESSAGE);
		return false;
	}
	
	/**
	 * Check if a string is numeric
	 * @param str
	 */
	public boolean isNumeric(String str)
	{
		try
		{
			Float.valueOf(str);
		}
		catch(NumberFormatException e1)
		{
			return false;
		}
		return true;
	}//end of numeric method
	
}
