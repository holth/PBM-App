package controllers;

import models.Expense_BLL;

/**
 * Class extending an Expense object of Purchase type
 */

public class PurchaseExpense extends Expense
{
	
	private String date;

	
	public PurchaseExpense(String pcategory, String pname, String paddress, String pdate, Float pamount, 
			String pmode, String pstatus, String pdue_date)
	{
		this.expenseType = "Purchase";
		this.category = pcategory;
		this.providerName = pname;
		this.providerAddress = paddress;
		this.date = pdate;
		this.amount = pamount;
		this.paymentMode = pmode;
		this.status = pstatus;
		this.dueDate = pdue_date;
	}
	
	public boolean save(String userName) throws Exception
	{
		Expense_BLL expenseModel = new Expense_BLL();
		
		boolean success = expenseModel.addExpense(userName, paymentMode, providerName, expenseType, providerAddress,
				amount, category, status, "N/A", date, dueDate);	//purchase recurrence is N/A
		
		return success;
	}
	
	
	public String toString()
	{
		return "Expense Type: " + this.expenseType + " | " +
				"Provider Name: " + this.providerName + " | " +
				"Provider Address: " + this.providerAddress + " | " +
				"Data: " + this.date + " | " +
				"Amount: " + this.amount + " | " +
				"Payment Mode: " + this.paymentMode + " | " +
				"Status: " + this.status + " | " +
				"Due Date: " + this.dueDate;
	}

}