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

        		if(isBill)
        		{
        			expensesFrame.showAddBillForm();
        		}
        		else
        		{
        			expensesFrame.showAddPurchaseForm();
        		}
    		}
		}
	}
	
	public void saveExpense() {
		
		JOptionPane.showMessageDialog(null, "To be implemented!");
		expensesFrame.hideExpenseForm();
		
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
				saveExpense();
			}
		});
		
		billExpensePanel.cancel(new ActionListener() { // on-click of cancel
			public void actionPerformed(ActionEvent e) {
				viewExpenses();
			}
		});
		
		purchaseExpensePanel.save(new ActionListener() { // on-click of save
			public void actionPerformed(ActionEvent e) {
				saveExpense();
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