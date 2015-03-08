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
	
	public void saveExpense(PurchaseExpense expense) {
		
		String accountType = expense.paymentMode;
		String providerName = expense.providerName;
		String providerType = expense.expenseType;
		String address = expense.providerAddress;
		Float amount = expense.amount;
		String type = expense.expenseType;
		String status = expense.status;
		String duration = "";
		String dateTime = expense.date;
		String dueDate = expense.dueDate;
		
		
		try {
			Expense_BLL expenseModel = new Expense_BLL();
			
			boolean success = expenseModel.addExpense(username, accountType,
					providerName, providerType, address,
					amount, type, status, duration,
					dateTime, dueDate);
			
			if(success) {
				// purchaseExpensePanel.clearAllFields();
				expensesFrame.hideExpenseForm();
				refreshData();
				JOptionPane.showMessageDialog(null, "Expense saved successfully!");
			}
			
		} catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Unable to save expense!");
			e.printStackTrace();
		}
		
	}
	
	public void saveExpense(BillExpense expense) {
		
		String accountType = expense.paymentMode;
		String providerName = expense.providerName;
		String providerType = expense.expenseType;
		String address = "";
		Float amount = expense.amount;
		String type = expense.expenseType;
		String status = expense.status;
		String duration = expense.interval;
		String dateTime = "";
		String dueDate = expense.dueDate;
		
		
		try {
			Expense_BLL expenseModel = new Expense_BLL();
			
			boolean success = expenseModel.addExpense(username, accountType,
					providerName, providerType, address,
					amount, type, status, duration,
					dateTime, dueDate);
			
			if(success) {
				// billExpensePanel.clearAllFields();
				expensesFrame.hideExpenseForm();
				refreshData();
				JOptionPane.showMessageDialog(null, "Expense saved successfully!");
			}
			
		} catch(Exception e) {
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
				
				BillExpense bill = new BillExpense(billExpensePanel.getProvider(),
						"", billExpensePanel.getAmount(),
						billExpensePanel.getInterval(), billExpensePanel.getMode(),
						billExpensePanel.getStatus(), billExpensePanel.getDueDate());
				
				saveExpense(bill);
			}
		});
		
		billExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				viewExpenses();
			}
		});
		
		purchaseExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				
				PurchaseExpense purchase = new PurchaseExpense(purchaseExpensePanel.getProvider(),
						purchaseExpensePanel.getAddress(), purchaseExpensePanel.getDate(), 
						purchaseExpensePanel.getAmount(), purchaseExpensePanel.getMode(),
						purchaseExpensePanel.getStatus(), purchaseExpensePanel.getDueDate());
				
				saveExpense(purchase);
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
}