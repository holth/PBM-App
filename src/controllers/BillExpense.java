package controllers;

import models.Expense_BLL;

/**
 * Class extending an Expense object of Bill type
 */

public class BillExpense extends Expense
{
	
	private String interval;			//weekly, monthly or yearly

	
	public BillExpense(String bcategory, String bname, String baddress, Float bamount, String binterval, 
			String bmode, String bstatus, String bdue_date)
	{
		this.expenseType = "Bill";
		this.category = bcategory;
		this.providerName = bname;
		this.providerAddress = baddress;
		this.amount = bamount;
		this.interval = binterval;
		this.paymentMode = bmode;
		this.status = bstatus;
		this.dueDate = bdue_date;
	}
	
	public boolean save(String userName) throws Exception
	{
		Expense_BLL expenseModel = new Expense_BLL();
		
		boolean success = expenseModel.addExpense(userName, paymentMode, providerName, expenseType, providerAddress,
				amount, category, status, interval, "N/A", dueDate);	//bill payment date is N/A
		
		return success;
	}
	

	public String toString()
	{
		return "Expense Type: " + this.expenseType + " | " +
				"Provider Name: " + this.providerName + " | " +
				"Provider Address: " + this.providerAddress + " | " +
				"Amount: " + this.amount + " | " +
				"Interval: " + this.interval + " | " +
				"Payment Mode: " + this.paymentMode + " | " +
				"Status: " + this.status + " | " +
				"Due Date: " + this.dueDate;
	}
}