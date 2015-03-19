package controllers;

/**
 * Abstract class to be extended by PurchaseExpense or BillExpense
 */

public abstract class Expense
{
	
	protected String expenseType;
	protected String category;

	protected String providerName;
	protected String providerAddress;
	
	protected Float amount;
	
	protected String paymentMode;		//Cash, Debit or Credit card
	protected String status;			//Paid or Unpaid
	protected String dueDate;
	
	
	public abstract boolean save(String userName) throws Exception;
	
	public abstract String toString();
	
	/**
	 * @return the expenseType
	 */
	public String getExpenseType() {
		return expenseType;
	}
	
}