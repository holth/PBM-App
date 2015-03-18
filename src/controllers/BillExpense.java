package controllers;

import models.Expense_BLL;

/**
 * Class implementing an Expense object of Bill type
 */

public class BillExpense implements Expense
{
	private String expenseType;
	private String category;
	
	private String providerName;
	private String providerAddress;
	
	private Float amount;
	private String interval;			//weekly, monthly or yearly
	
	private String paymentMode;		//Cash, Debit or Credit card
	private String status;			//Paid or Unpaid
	private String dueDate;
	
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
	
	/**
	 * @return the expenseType
	 */
	public String getExpenseType() {
		return expenseType;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	
	/**
	 * @return the providerName
	 */
	public String getProviderName() {
		return providerName;
	}

	/**
	 * @return the providerAddress
	 */
	public String getProviderAddress() {
		return providerAddress;
	}

	/**
	 * @return the amount
	 */
	public Float getAmount() {
		return amount;
	}

	/**
	 * @return the interval
	 */
	public String getInterval() {
		return interval;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return the dutDate
	 */
	public String getDutDate() {
		return dueDate;
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