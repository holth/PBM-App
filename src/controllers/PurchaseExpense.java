package controllers;

/**
 * Class implementing an Expense object of Purchase type
 */

public class PurchaseExpense implements Expense
{
	public String expenseType;
	
	public String providerName;
	public String providerAddress;
	public String date;
	//public String time;
	
	public Float amount;
	
	public String paymentMode;		//Cash, Debit or Credit card
	public String status;			//Paid or Unpaid
	public String dueDate;
	
	public PurchaseExpense(String pname, String paddress, String pdate, /*String ptime,*/ 
			Float pamount, String pmode, String pstatus, String pdue_date)
	{
		this.expenseType = "Purchase";
		this.providerName = pname;
		this.providerAddress = paddress;
		this.date = pdate;
		//this.time = ptime;
		this.amount = pamount;
		this.paymentMode = pmode;
		this.status = pstatus;
		this.dueDate = pdue_date;
	}
	
	public String toString()
	{
		return "Expense Type: " + this.expenseType + " | " +
				"Provider Name: " + this.providerName + " | " +
				"Provider Address: " + this.providerAddress + " | " +
				"Data&Time: " + this.date + /*" " + this.time + */" | " +
				"Amount: " + this.amount + " | " +
				"Payment Mode: " + this.paymentMode + " | " +
				"Status: " + this.status + " | " +
				"Due Date: " + this.dueDate;
	}

}