package controllers;

import models.Expense_BLL;

/**
 * Class implementing an Expense object of Purchase type
 */

public class PurchaseExpense implements Expense
{
	private String expenseType;
	private String category;

	private String providerName;
	private String providerAddress;
	private String date;
	
	private Float amount;
	
	private String paymentMode;		//Cash, Debit or Credit card
	private String status;			//Paid or Unpaid
	private String dueDate;
	
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
	 * @return the date
	 */
	public String getDate() {
		return date;
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
				"Data: " + this.date + " | " +
				"Amount: " + this.amount + " | " +
				"Payment Mode: " + this.paymentMode + " | " +
				"Status: " + this.status + " | " +
				"Due Date: " + this.dueDate;
	}

}