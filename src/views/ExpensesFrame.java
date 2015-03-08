package views;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ExpensesFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel rightPanel;
	
	private ViewExpensesPanel expensesPanel;
	private AddExpensePanel purchaseExpensePanel = new AddExpensePanel("purchase");
	private AddExpensePanel billExpensePanel = new AddExpensePanel("bill");
	
	private CardLayout cLayout = new CardLayout();
	
	private String username;
	
	private JButton btnAddExpense;
	private JButton btnUpdateStatus;
	private JButton btnDeleteExpense;

	public ExpensesFrame(String username) {
		
		this.username = username;
		this.expensesPanel = new ViewExpensesPanel(username);
		
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
		
	}
	
	/// Listener methods ///
	
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
	
	/// Setter methods ///
	
	public void hideExpenseForm() {
		rightPanel.setVisible(false);
	}
	
	public void showAddPurchaseForm() {
		
		rightPanel.setVisible(true);
		cLayout.show(rightPanel, "add purchase");
	}
	
	public void showAddBillForm() {
		
		rightPanel.setVisible(true);
		cLayout.show(rightPanel, "add bill");
	}
	
	public void refreshExpenses() {
		middlePanel.removeAll();
		expensesPanel = new ViewExpensesPanel(username);
		middlePanel.add(expensesPanel, BorderLayout.CENTER);
	}
	
	/// Design methods ///
	
	private void loadTopPanel(){
		
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout(0,0));
        topPanel.setBackground(new Color(224, 224, 224));
        topPanel.setPreferredSize(new Dimension(0, 46));
        
        JPanel btnsPanel = new JPanel();
        btnsPanel.setOpaque(false);
        topPanel.add(btnsPanel, BorderLayout.WEST);
        
		btnAddExpense = new JButton("Add expense  ", new ImageIcon("assets/appbar.add.png"));
		btnAddExpense.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnAddExpense);
        
		btnUpdateStatus = new JButton("Update status  ", new ImageIcon("assets/appbar.draw.pencil.png"));
		btnUpdateStatus.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnUpdateStatus);
        
		btnDeleteExpense = new JButton("Delete expense  ", new ImageIcon("assets/appbar.close.png"));
		btnDeleteExpense.setFont(new Font("Tacoma", 0, 13));
		btnsPanel.add(btnDeleteExpense);
        
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
	
}
