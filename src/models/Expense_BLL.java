package models;

import java.util.ArrayList;

public class Expense_BLL {
	private Expense_Service expense_service;
	private User_Service user_service;
	private Account_Service account_service;

	public Expense_BLL() throws Exception {
		expense_service = new Expense_Service();
		user_service = new User_Service();
		account_service = new Account_Service();

	}

	public ArrayList<ArrayList<String>> viewExpenseByUsername(String username)
			throws Exception {
		int userId = this.user_service.getUserIdByUsername(username);
		return this.expense_service.getTransactionByUsername(userId);
	}

	public ArrayList<ArrayList<String>> viewExpenseByUsernameAndType(
			String username, String type) throws Exception {
		int userId = this.user_service.getUserIdByUsername(username);
		return this.expense_service.getTransactionByUsernameAndType(userId,
				type.toUpperCase().trim());
	}
	
	public ArrayList<ArrayList<String>> viewExpenseBy(
			String username, String category, String providerType, String expenseStatus) 
			throws Exception {
		int userId = this.user_service.getUserIdByUsername(username);
		return this.expense_service.getTransactionBy(userId, category, providerType, expenseStatus);
	}
	
	public ArrayList<String> getCategories() throws Exception {
		return this.expense_service.getCategories();
	}
	
	public ArrayList<String> getCategoriesByUsername(String username) throws Exception {
		int userId = this.user_service.getUserIdByUsername(username);
		return this.expense_service.getCategoriesByUserId(userId);
	}
	
	public ArrayList<String> getCategoriesBy(String username, String providerType, String status) throws Exception {
		int userId = this.user_service.getUserIdByUsername(username);
		return this.expense_service.getCategoriesBy(userId, providerType, status);
	}

	public boolean deleteExpense(int transactionId) throws Exception {
		return this.expense_service.deleteTransaction(transactionId);
	}

	public void updateExpense(int transactionId, String attribute, String value)
			throws Exception {
		this.expense_service.updateTransaction(transactionId, attribute, value);
	}



	public boolean addExpense(String username, String accountType,
			String providerName, String providerType, String address,
			float amount, String category, String status, String duration,
			String dateTime, String dueDate) throws Exception {
		int providerId = 0;
		int userId = this.user_service.getUserIdByUsername(username);
		int accountId = this.account_service.getAccountIdByUsernameAndType(
				userId, accountType);
		if (this.expense_service.isProviderExist(providerName.toUpperCase()
				.trim(), providerType.toUpperCase().trim(), address
				.toUpperCase().trim()) == false) {
			providerId = this.expense_service.addProvider(providerName,
					providerType, address);
		} else {
			providerId = this.expense_service
					.getProviderIdByNameTypeAndAddress(providerName,
							providerType, address);
		}
		return this.expense_service.makeTransaction(providerId, accountType
				.toUpperCase().trim(), accountId, address.toUpperCase().trim(),
				amount, category.toUpperCase().trim(), status.toUpperCase().trim(),
				duration, dateTime, dueDate);
	}
}
