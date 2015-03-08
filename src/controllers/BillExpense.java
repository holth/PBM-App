package controllers;

/**
 * Class implementing an Expense object of Bill type
 */

public class BillExpense implements Expense
{
	public String expenseType;
	
	public String providerName;
	public String providerAddress;
	
	public Float amount;
	public String interval;			//weekly, monthly or yearly
	
	public String paymentMode;		//Cash, Debit or Credit card
	public String status;			//Paid or Unpaid
	public String dutDate;
	
	public BillExpense(String bname, String baddress, Float bamount, String binterval, 
			String bmode, String bstatus, String bdue_date)
	{
		this.expenseType = "Bill";
		this.providerName = bname;
		this.providerAddress = baddress;
		this.amount = bamount;
		this.interval = binterval;
		this.paymentMode = bmode;
		this.status = bstatus;
		this.dutDate = bdue_date;
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
				"Due Date: " + this.dutDate;
	}
}