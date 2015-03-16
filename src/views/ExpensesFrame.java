package views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import models.Expense_BLL;

public class ExpensesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	private JPanel bottomPanel;
	
	private ViewExpensesPanel expensesPanel;
	private AddExpensePanel purchaseExpensePanel = new AddExpensePanel("purchase");
	private AddExpensePanel billExpensePanel = new AddExpensePanel("bill");
	
	private CardLayout cLayout = new CardLayout();
	
	private String username;
	
	private JComboBox<String> selectFilterCategory;
	private JComboBox<String> selectFilterType;
	private JComboBox<String> selectFilterStatus;
	
	private JButton btnFilter;
	private JButton btnClearFilter;
	
	private JButton btnAddExpense;
	private JButton btnUpdateStatus;
	private JButton btnDeleteExpense;

	public ExpensesFrame(String username) {
		
		this.username = username;
		this.expensesPanel = new ViewExpensesPanel(username, "all", "all", "all");
		
		this.getContentPane().setBackground(new Color(248, 248, 248));
		this.setBounds(100, 100, 1000, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Personal Budget Manager");
		this.setVisible(true);
        
        mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 1000, 600);
		mainPanel.setLayout(new BorderLayout(0, 0));
		this.getContentPane().add(mainPanel);
		
		loadTopPanel();
		mainPanel.add(topPanel, BorderLayout.NORTH);
		
		loadMiddlePanel();
		middlePanel.setLayout(new BorderLayout());
		middlePanel.add(expensesPanel, BorderLayout.CENTER);
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
		loadRightPanel();
		mainPanel.add(rightPanel, BorderLayout.EAST);
		rightPanel.setVisible(false);
		
		loadBottomPanel();
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		
	}
	
	/// Listener methods ///
	
	public void filterExpenses(ActionListener listener) {
		btnFilter.addActionListener(listener);
	}
	
	public void clearFilter(ActionListener listener) {
		btnClearFilter.addActionListener(listener);
	}
	
	public void addExpense(ActionListener listener) {
		btnAddExpense.addActionListener(listener);
	}
	
	public void updateStatus(ActionListener listener) {
		btnUpdateStatus.addActionListener(listener);
	}
	
	public void deleteExpense(ActionListener listener) {
		btnDeleteExpense.addActionListener(listener);
	}
	
	/// Getter methods ///
	
	public String getUsername() {
		return username;
	}
	
	public JPanel getMiddlePanel() {
		return middlePanel;
	}
	
	public ViewExpensesPanel getExpensesPanel() {
		return expensesPanel;
	}
	
	public AddExpensePanel getPurchasePanel() {
		return purchaseExpensePanel;
	}
	
	public AddExpensePanel getBillPanel() {
		return billExpensePanel;
	}
	
	public String getFilterCategory() {
		return selectFilterCategory.getSelectedItem().toString();
	}
	
	public String getFilterType() {
		return selectFilterType.getSelectedItem().toString();
	}
	
	public String getFilterStatus() {
		return selectFilterStatus.getSelectedItem().toString();
	}
	
	/// Setter methods ///
	
	public void hideExpenseForm() {
		rightPanel.setVisible(false);
	}
	
	public void showAddPurchaseForm() {
		
		cLayout.show(rightPanel, "add purchase");
		rightPanel.setVisible(true);
	}
	
	public void showAddBillForm() {
		
		cLayout.show(rightPanel, "add bill");
		rightPanel.setVisible(true);
	}
	
	public void clearFilter() {
		// Get the list of all category
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Category");
        try{
        	Expense_BLL exp = new Expense_BLL();
        	categoryList.addAll(exp.getCategoriesByUsername(username));
        } catch(Exception e) {
        	e.printStackTrace();
        }
        // end
        selectFilterCategory.removeAllItems();
		for(int i = 0; i < categoryList.size(); i++){
			selectFilterCategory.addItem(categoryList.get(i).toString());
		}
		
		selectFilterCategory.setSelectedIndex(0);
		selectFilterType.setSelectedIndex(0);
		selectFilterStatus.setSelectedIndex(0);
	}
	
	/// Design methods ///
	
	private void loadTopPanel(){
		
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(0,0));
        topPanel.setBackground(new Color(224, 224, 224));
        topPanel.setPreferredSize(new Dimension(0, 40));
        
        JPanel filterPanel = new JPanel();
        filterPanel.setOpaque(false);
        topPanel.add(filterPanel, BorderLayout.WEST);
        
        // Get the list of all category
        ArrayList<String> categoryList = new ArrayList<>();
        categoryList.add("Category");
        try{
        	Expense_BLL exp = new Expense_BLL();
        	categoryList.addAll(exp.getCategoriesByUsername(username));
        } catch(Exception e) {
        	e.printStackTrace();
        }
        // end
		
        JLabel lblFilter = new JLabel("Filter by: ");
        lblFilter.setHorizontalAlignment(SwingConstants.LEFT);
		filterPanel.add(lblFilter);
		
		selectFilterCategory = new JComboBox<String>();
		for(int i = 0; i < categoryList.size(); i++){
			selectFilterCategory.addItem(categoryList.get(i).toString());
		}
		filterPanel.add(selectFilterCategory);
		
		selectFilterType = new JComboBox<String>();
		selectFilterType.setModel(new DefaultComboBoxModel<String>(new String[] {"Type", "PURCHASE", "BILL"}));
		filterPanel.add(selectFilterType);
		
		selectFilterStatus = new JComboBox<String>();
		selectFilterStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Status", "PAID", "UNPAID"}));
		filterPanel.add(selectFilterStatus);
		
		btnFilter = new JButton("Apply");
		filterPanel.add(btnFilter);
		
		btnClearFilter = new JButton("Clear");
		filterPanel.add(btnClearFilter);
        
        JLabel lblUsername = new JLabel("Hi, " + username);
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setFont(new Font("Tacoma", Font.BOLD, 12));
        lblUsername.setPreferredSize(new Dimension(140,40));
        topPanel.add(lblUsername, BorderLayout.EAST);
	}

	private void loadMiddlePanel() {
		
		middlePanel = new JPanel();
        middlePanel.setBackground(new Color(0, 0, 0));
	}
	
	private void loadRightPanel() {
		
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(224, 224, 224));
		
		rightPanel.setLayout(cLayout);	// set middle panel as container
		rightPanel.add(purchaseExpensePanel, "add purchase");
		rightPanel.add(billExpensePanel, "add bill");
	}
	
	private void loadBottomPanel() {
		
        bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout(0,0));
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.setPreferredSize(new Dimension(0, 48));
        
        JPanel btnsPanel = new JPanel();
        btnsPanel.setOpaque(false);
        bottomPanel.add(btnsPanel, BorderLayout.WEST);
        
		btnAddExpense = new JButton("Add expense  ", new ImageIcon("assets/appbar.add.png"));
		btnAddExpense.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnAddExpense);
        
		btnUpdateStatus = new JButton("Update status  ", new ImageIcon("assets/appbar.draw.pencil.png"));
		btnUpdateStatus.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnUpdateStatus);
        
		btnDeleteExpense = new JButton("Delete expense  ", new ImageIcon("assets/appbar.close.png"));
		btnDeleteExpense.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnDeleteExpense);
		
	}
	
}
