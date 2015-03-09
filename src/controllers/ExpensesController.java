package controllers;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

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

	public void viewExpenses() {
		
		expensesFrame.hideExpenseForm();
	}
	
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
	
	public void saveExpense(String type, String category, String name, String address, String date, 
			Float amount, String interval, String mode, String status, String due_date) {
		
		try {
			Expense_BLL expenseModel = new Expense_BLL();
			
			boolean success = expenseModel.addExpense(username, mode, name, type, address,
					amount, category, status, interval, date, due_date);
			
			if(success) {
				if(type.equals("Purchase"))
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
	
	
	public void updateStatus() {
		JTable table = viewExpensesPanel.getTable();

		int row = table.getSelectedRow();	// get current selected row
		
		if(row == -1) 						// no row selected
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 0));
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
				
				refreshData();
				
				System.out.println("Status has been changed to " + newstatus +".");
				
				JOptionPane.showMessageDialog(null, "Status has been changed to " + newstatus +".");
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public void deleteExpense() {
		JTable table = viewExpensesPanel.getTable();

		int row = table.getSelectedRow();	// get current selected row
		
		if(row == -1) 						// no row selected
			JOptionPane.showMessageDialog(null, 
					"Please select the expense you want to update!", "ERROR", JOptionPane.ERROR_MESSAGE);
		else {
			int expenseID = Integer.parseInt((String) table.getValueAt(row, 0));
											
			try {
				
				Expense_BLL expense = new Expense_BLL();
				expense.deleteExpense(expenseID);
				
				refreshData();
				
				System.out.println("Expense deleted successfully!");
				
				JOptionPane.showMessageDialog(null, "Expense deleted successfully!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
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
				updateStatus();
			}
		});
		
		expensesFrame.deleteExpense(new ActionListener() { // on-click of delete
			public void actionPerformed(ActionEvent e) {
				System.out.println("Delete expenses?");
				deleteExpense();
			}
		});
		
		billExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				
				String type = "Bill";
				String category = billExpensePanel.getCategory();
				String bname = billExpensePanel.getProvider();
				String baddress = "NA";
				String bdate = "NA";
				String bamount = billExpensePanel.getAmount();
				String binterval = billExpensePanel.getInterval();
				String bmode = billExpensePanel.getMode();
				String bstatus = billExpensePanel.getStatus();
				String bdue_date = billExpensePanel.getDueDate();
				
				if(bmode == "Cash" || bmode == "Debit")
				{
					bstatus = "Paid";
					bdue_date = "NA";
				}
				if(bstatus == "Paid")
					bdue_date = "NA";
				if(category.equals("") || bname.equals("") || bamount.equals("") || bmode.equals("") || bstatus.equals("")|| bdue_date.equals("") ||
						binterval.equals(""))
					errormessages("ERROR: Bill Fields are empty!");
				else if(!isNumeric(bamount)){
					errormessages("ERROR: Amount should be numeric!");
				}
				else 
				{
					Float bamountFloat = Float.valueOf(bamount);
					saveExpense(type, category, bname, baddress, bdate, bamountFloat, binterval, bmode, bstatus, bdue_date);
				}
			}
		});
		
		billExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				viewExpenses();
			}
		});
		
		purchaseExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				
				String type = "Purchase";
				String category = purchaseExpensePanel.getCategory();
				String pname = purchaseExpensePanel.getProvider();
				String paddress = purchaseExpensePanel.getAddress();
				String pdate = "";
				if(purchaseExpensePanel.getDate() != null)
					pdate = purchaseExpensePanel.getDate().toString();
				String pamount = purchaseExpensePanel.getAmount();
				String pinterval = "N/A";
				String pmode = purchaseExpensePanel.getMode();
				String pstatus = purchaseExpensePanel.getStatus();
				String pdue_date = purchaseExpensePanel.getDueDate();
				
				if(pmode == "Cash" || pmode == "Debit")
				{
					pstatus = "Paid";
					pdue_date = "NA";
				}
				if(pstatus == "Paid")
					pdue_date = "NA";
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
					saveExpense(type, category, pname, paddress, pdate, pamountFloat, pinterval, pmode, pstatus, pdue_date);
				}
			
			}
		});
		
		purchaseExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				viewExpenses();
			}
		});
		
	}
	
	/**
	 * Refresh data table
	 */
	private void refreshData() {
		
		viewExpensesPanel = new ViewExpensesPanel(username);
		
		expensesFrame.getMiddlePanel().removeAll();
		expensesFrame.getMiddlePanel().add(viewExpensesPanel, BorderLayout.CENTER);
		expensesFrame.getMiddlePanel().revalidate();
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
			float d = Float.valueOf(str);
		}
		catch(NumberFormatException e1)
		{
			return false;
		}
		return true;
	}//end of numeric method
	
}
