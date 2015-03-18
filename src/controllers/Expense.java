package controllers;

/**
 * Interface to be implemented by PurchaseExpense or BillExpense
 */

public interface Expense
{
	
	public boolean save(String userName) throws Exception;
	public String getExpenseType();
	public String toString();
	
}