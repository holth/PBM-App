package views;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ExpensesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JPanel mainPanel;
	public JPanel topPanel;
	public JPanel middlePanel;
	public JPanel bottomPanel;
	
	public JPanel btnsForViewExpenses;
	public JPanel btnsForAddExpense;
	
	public JComboBox<String> selectType;
	public JComboBox<String> selectCategory;
	public JComboBox<String> selectProvider;
	
	public JButton btnFilter;
	public JButton btnClearFilter;
	public JButton btnDeleteAll;
	
	public JButton btnShowExpenses;
	public JButton btnAddExpense;
	public JButton btnUpdateStatus;
	public JButton btnDeleteExpense;
	public JButton btnSaveExpense;
	

	public ExpensesFrame() {
		
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
		mainPanel.add(middlePanel, BorderLayout.CENTER);
		
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

	public void deleteAll(ActionListener listener) {
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
	
	public void saveExpense(ActionListener listener) {
		btnSaveExpense.addActionListener(listener);
	}

	public void viewExpenses(ActionListener listener) {
		btnShowExpenses.addActionListener(listener);
	}
	
	/// Design methods ///
	
	private void loadTopPanel(){
		
        topPanel = new JPanel();
        topPanel.setBackground(new Color(224, 224, 224));
        topPanel.setPreferredSize(new Dimension(0, 40));
		
        // Label & combo-boxes to filter data
		JLabel lblFilter = new JLabel("Filter by:");
		topPanel.add(lblFilter);
        
		selectType = new JComboBox<String>();
		selectType.setModel(new DefaultComboBoxModel<String>(new String[] {"Expense type", "Purchase items", "Bills"}));
		topPanel.add(selectType);
        
		selectCategory = new JComboBox<String>();
		selectCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Category", "Food", "Internet", "Insurance"}));
		topPanel.add(selectCategory);
        
		selectProvider = new JComboBox<String>();
		selectProvider.setModel(new DefaultComboBoxModel<String>(new String[] {"Service provider", "Videotron", "Tim horton", "Maxie et cie"}));
		topPanel.add(selectProvider);
        
		btnFilter = new JButton("Filter");
		btnFilter.setFont(new Font("Tacoma", 0, 13));
		topPanel.add(btnFilter);
        
		btnDeleteAll = new JButton("Delete");
		btnDeleteAll.setFont(new Font("Tacoma", 0, 13));
		topPanel.add(btnDeleteAll);
        
		btnClearFilter = new JButton("Clear");
		btnClearFilter.setFont(new Font("Tacoma", 0, 13));
		topPanel.add(btnClearFilter);
		// end...
	}

	private void loadMiddlePanel() {
		middlePanel = new JPanel();
        middlePanel.setBackground(new Color(0, 0, 0));
	}
	
	private void loadBottomPanel() {
		
        bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(224, 224, 224));
        bottomPanel.setPreferredSize(new Dimension(0, 50));
        
        // buttons visible when on view expenses
        btnsForViewExpenses = new JPanel();
        btnsForViewExpenses.setBackground(new Color(224, 224, 224));
        
		btnAddExpense = new JButton("Add expense");
		btnAddExpense.setFont(new Font("Tacoma", 0, 13));
		btnsForViewExpenses.add(btnAddExpense);
        
		btnUpdateStatus = new JButton("Update status");
		btnUpdateStatus.setFont(new Font("Tacoma", 0, 13));
		btnsForViewExpenses.add(btnUpdateStatus);
        
		btnDeleteExpense = new JButton("Delete expense");
		btnDeleteExpense.setFont(new Font("Tacoma", 0, 13));
		btnsForViewExpenses.add(btnDeleteExpense);
		// end...
		
		bottomPanel.add(btnsForViewExpenses);
		
		// buttons visible when on add expense only
		btnsForAddExpense = new JPanel();
		btnsForAddExpense.setBackground(new Color(224, 224, 224));
        
		btnSaveExpense = new JButton("Save expense");
		btnSaveExpense.setFont(new Font("Tacoma", 0, 13));
		btnsForAddExpense.add(btnSaveExpense);
        
		btnShowExpenses = new JButton("Cancel");
		btnShowExpenses.setFont(new Font("Tacoma", 0, 13));
		btnsForAddExpense.add(btnShowExpenses);
		// end ...
		
		bottomPanel.add(btnsForAddExpense);
	}
}
