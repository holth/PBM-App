package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import models.Expense_BLL;

import com.toedter.calendar.JDateChooser;

public class AddExpensePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField textFieldCategory;
	private JTextField textFieldProvider;
	private JTextField textFieldLocation;
	private JTextField textFieldAmount;
	private JDateChooser chooserDate;
	private JDateChooser chooserDueDate;
	private JSpinner spinner;
	private JComboBox<String> selectCategory;
	private JComboBox<String> selectMode;
	private JComboBox<String> selectStatus;
	private JComboBox<String> selectInterval;
	
	private JButton btnSave;
	private JButton btnCancel;
	
	private String type;

	/**
	 * Constructor
	 * @param type, 'PURCHASE' or 'BILL', to use to display fields
	 */
	public AddExpensePanel(String type) {
		this.type = type;
		loadExpenseFields(type);
	}
	
	/// Listener methods ///
	
	public void save(ActionListener listener) {
		btnSave.addActionListener(listener);
	}
	
	public void cancel(ActionListener listener) {
		btnCancel.addActionListener(listener);
	}
	
	/// Get methods ///
	
	public String getCategory() {
		if(selectCategory.getSelectedItem().toString() == "NEW CATEGORY")
			return textFieldCategory.getText();
		else
			return selectCategory.getSelectedItem().toString();
	}
	
	public String getMode() {
		return selectMode.getSelectedItem().toString();
	}
	
	public String getStatus() {
		return selectStatus.getSelectedItem().toString();
	}
	
	public String getInterval() {
		return selectInterval.getSelectedItem().toString();
	}
	
	public String getProvider() {
		return textFieldProvider.getText();
	}
	
	public String getAddress() { // ?? unable to use 'getLocation()'
		return textFieldLocation.getText();
	}
	
	public String getAmount() {
		return textFieldAmount.getText();
	}
	
	public String getDate() {
		String date;
		String time;
		
		if(chooserDate.getDate() != null)
			date = new SimpleDateFormat("yyyy-MM-dd").format(chooserDate.getDate());
		else
			date = "";
		
		if(spinner.getValue() != null)
			time = new SimpleDateFormat("h:mm a").format(spinner.getValue());
		else
			time = "";
		
		return date + " " + time;
	}
	
	public String getDueDate() {
		if(chooserDueDate.getDate() != null)
			return new SimpleDateFormat("yyyy-MM-dd").format(chooserDueDate.getDate());
		else
			return "";
	}
	
	/// Set methods ///
	
	public void clearAllFields() {

        selectCategory.setVisible(false);
        selectCategory.removeAllItems();
        // Get the list of categories
        ArrayList<String> categoryList = new ArrayList<>();
        try{
        	Expense_BLL exp = new Expense_BLL();
        	categoryList.addAll(exp.getCategories());
        	categoryList.add("...");
        	categoryList.add("NEW CATEGORY");
        } catch(Exception e) {
        	e.printStackTrace();
        }
        // end
        
		for(int i = 0; i < categoryList.size(); i++) {
			selectCategory.addItem(categoryList.get(i).toString());
		}
		
		selectCategory.setSelectedIndex(0);
		selectCategory.setVisible(true);
		
		textFieldCategory.setText("");
		textFieldCategory.setVisible(false);
		
		textFieldProvider.setText("");
		
		if(type.toLowerCase().equals("purchase")){
			textFieldLocation.setText("");
			chooserDate.setCalendar(null);
			
		}
		
		textFieldAmount.setText("");
		
		selectMode.setSelectedIndex(0);
		
		selectStatus.setSelectedIndex(0);
		
		if(type.toLowerCase().equals("bill"))
			selectInterval.setSelectedIndex(0);
		
		chooserDueDate.setCalendar(null);
	}
	
	/// Design methods ///
	
	public void loadExpenseFields(String type) {
		
		Font lblFont = new Font("Tacoma", 0, 12); 			// Default font for label
		Font fieldFont = new Font("Tacoma", 0, 13); 		// Default font for fields

		Dimension lblSize = new Dimension(65, 32);			// Default dimension for label
		Dimension lblSizeSM = new Dimension(45, 32);		// Default dimension for label
		Dimension fieldSize = new Dimension(305, 32);		// Default dimension for fields
		Dimension fieldSizeSM = new Dimension(120, 32);		// Default dimension for fields
		
		this.setLayout(new GridBagLayout());				// Set layout to GidBagLayout
		GridBagConstraints gbc = new GridBagConstraints();	// the constraints
		gbc.insets = new Insets(5,5,5,5);					// Set the spacing between fields
		
		// Form title
		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4;
		
		JLabel lblTitle;
		if(type.toLowerCase().equals("bill")) { // Title for add new bill
			lblTitle = new JLabel("New bill");
		} else {								// Title for add new purchase
			lblTitle = new JLabel("New purchase expense");
		}
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tacoma", Font.BOLD, 18));
        lblTitle.setPreferredSize(new Dimension(370,39));
        this.add(lblTitle, gbc);
        // end ...
		
		// Label & fields for expense category
		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
		
        JLabel lblCategory = new JLabel("Category");
        lblCategory.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategory.setFont(lblFont);
        lblCategory.setPreferredSize(lblSize);
		this.add(lblCategory, gbc);

		gbc.gridx = 1; gbc.gridwidth = 3;
        
        // Get the list of categories
        ArrayList<String> categoryList = new ArrayList<>();
        try{
        	Expense_BLL exp = new Expense_BLL();
        	categoryList.addAll(exp.getCategories());
        } catch(Exception e) {
        	e.printStackTrace();
        }
        categoryList.add("...");
        categoryList.add("NEW CATEGORY");
        // end
		
		selectCategory = new JComboBox<String>();
		for(int i = 0; i < categoryList.size(); i++) {
			selectCategory.addItem(categoryList.get(i));
		}
		selectCategory.setFont(lblFont);
		selectCategory.setPreferredSize(fieldSize);
		this.add(selectCategory, gbc);
		
        textFieldCategory = new JTextField();
        textFieldCategory.setFont(fieldFont);
        textFieldCategory.setPreferredSize(fieldSize);
        textFieldCategory.setVisible(false); // hide on start
		this.add(textFieldCategory, gbc);
		// end...
		
		// Label & fields for provider
		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
		
        JLabel lblName = new JLabel("Description");
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setFont(lblFont);
        lblName.setPreferredSize(lblSize);
		this.add(lblName, gbc);

		gbc.gridx = 1; gbc.gridwidth = 3;
		
        textFieldProvider = new JTextField();
        textFieldProvider.setFont(fieldFont);
        textFieldProvider.setPreferredSize(fieldSize);
		this.add(textFieldProvider, gbc);
		// end...
		
		if(!type.toLowerCase().equals("bill")){	// Hide location for bill
			
			// Label & field for location
			gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
			
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(lblFont);
			lblLocation.setPreferredSize(lblSize);
			this.add(lblLocation, gbc);

			gbc.gridx = 1; gbc.gridwidth = 3;
	        
	        textFieldLocation = new JTextField();
	        textFieldLocation.setFont(fieldFont);
	        textFieldLocation.setPreferredSize(fieldSize);
			this.add(textFieldLocation, gbc);
			// end...
		}
		
		// Label & fields for amount
		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(lblFont);
		lblAmount.setPreferredSize(lblSize);
		this.add(lblAmount, gbc);

		gbc.gridx = 1; gbc.gridwidth = 3;
        
        textFieldAmount = new JTextField();
        textFieldAmount.setFont(fieldFont);
        textFieldAmount.setPreferredSize(fieldSize);
		this.add(textFieldAmount, gbc);
		// end...

		if(!type.toLowerCase().equals("bill")) {	// Hide date & time for bill
			// Label & field for date
			gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
			
			JLabel lblDate = new JLabel("Date");
			lblDate.setFont(lblFont);
			lblDate.setPreferredSize(lblSize);
			this.add(lblDate, gbc);
			
			gbc.gridx = 1; gbc.gridwidth = 1;
			
			chooserDate = new JDateChooser();
			chooserDate.setFont(fieldFont);
			chooserDate.setPreferredSize(fieldSizeSM);
			this.add(chooserDate, gbc);
			// end...
			
			// Label & field for time
			gbc.gridx = 2; gbc.gridwidth = 1;
			
			JLabel lblTime = new JLabel("Time");
			lblTime.setFont(lblFont);
			lblTime.setPreferredSize(lblSizeSM);
			this.add(lblTime, gbc);
			
			gbc.gridx = 3; gbc.gridwidth = 1;
			
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY, 24); 	// 24 == 12 PM == 00:00:00
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);

			SpinnerDateModel model = new SpinnerDateModel();
			model.setValue(calendar.getTime());
			
			spinner = new JSpinner(model);

			JSpinner.DateEditor editor = new JSpinner.DateEditor(spinner, "HH:mm:ss");
			DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
			formatter.setAllowsInvalid(false);
			formatter.setOverwriteMode(true);

			spinner.setEditor(editor);
			spinner.setFont(lblFont);
			spinner.setPreferredSize(fieldSizeSM);
			this.add(spinner, gbc);
			// end...
		}
		
		if(type.toLowerCase().equals("bill")) {	// Show recurence only for bill
			// Label & fields for interval or recurence
			gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
			
			JLabel lblInterval = new JLabel("Recurrence");
			lblInterval.setFont(lblFont);
			lblInterval.setPreferredSize(lblSize);
			this.add(lblInterval, gbc);

			gbc.gridx = 1; gbc.gridwidth = 3;
	        
			selectInterval = new JComboBox<String>();
			selectInterval.setModel(new DefaultComboBoxModel<String>(new String[] {"Weekly", "Monthly", "Annually"}));
			selectInterval.setFont(lblFont);
			selectInterval.setPreferredSize(fieldSize);
			this.add(selectInterval, gbc);
			// end ...	
		}
		
		// Label & fields for account type
		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 1;
		
		JLabel lblMode = new JLabel("Pay by");
		lblMode.setFont(lblFont);
		lblMode.setPreferredSize(lblSize);
		this.add(lblMode, gbc);
		
		gbc.gridx = 1; gbc.gridwidth = 1;
        
		selectMode = new JComboBox<String>();
		selectMode.setModel(new DefaultComboBoxModel<String>(new String[] {"Credit Card", "Cash", "Debit"}));
		selectMode.setFont(lblFont);
		selectMode.setPreferredSize(fieldSizeSM);
		this.add(selectMode, gbc);
		// end ...
		
		// Label & field for status
		gbc.gridx = 2; gbc.gridwidth = 1;
		
		final JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(lblFont);
		lblStatus.setPreferredSize(lblSizeSM);
		this.add(lblStatus, gbc);
		
		gbc.gridx = 3; gbc.gridwidth = 1;
        
		selectStatus = new JComboBox<String>();
		selectStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Unpaid", "Paid"}));
		selectStatus.setFont(fieldFont);
		selectStatus.setPreferredSize(fieldSizeSM);
		this.add(selectStatus, gbc);
		// end...
		
		// Label & field for due date
		gbc.gridx = 0; gbc.gridy++;  gbc.gridwidth = 1;
		
		final JLabel lblDueDate = new JLabel("Due date");
		lblDueDate.setFont(lblFont);
		lblDueDate.setPreferredSize(lblSize);
		this.add(lblDueDate, gbc);
		
		gbc.gridx = 1; gbc.gridwidth = 1;
		
		chooserDueDate = new JDateChooser();
		chooserDueDate.setFont(fieldFont);
		chooserDueDate.setPreferredSize(fieldSizeSM);
		this.add(chooserDueDate, gbc);
		// end...
		
		// Empty panel for layout only
		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 4;

		final JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(165,32));
		this.add(emptyPanel, gbc);

		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 4;

		final JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setPreferredSize(new Dimension(370,32));
		emptyPanel2.setVisible(false);
		this.add(emptyPanel2, gbc);
		// end...
		
		// Buttons
		gbc.gridx = 1; gbc.gridy++; gbc.gridwidth = 1;
		
		btnSave = new JButton("Save");
		btnSave.setFont(fieldFont);
		btnSave.setPreferredSize(fieldSizeSM);
		this.add(btnSave, gbc);
		
		gbc.gridx = 2; gbc.gridwidth = 2;
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(fieldFont);
		btnCancel.setPreferredSize(fieldSizeSM);
		this.add(btnCancel, gbc);
		// end...
        
        this.validate();
        
        /// Listeners ///
        
        selectCategory.addActionListener (new ActionListener () { // on-change of select category
            public void actionPerformed(ActionEvent e) {
            	if(selectCategory.isVisible() && selectCategory.getSelectedItem().toString().equals("NEW CATEGORY")) {
            		selectCategory.setVisible(false);
            		textFieldCategory.setVisible(true);
                } else {
            		selectCategory.setVisible(true);
            		textFieldCategory.setVisible(false);
            	}
            }
        });
        
        selectMode.addActionListener (new ActionListener () { // on-change of payment mode
            public void actionPerformed(ActionEvent e) {
            	if(selectMode.getSelectedItem().toString() == "Cash" || selectMode.getSelectedItem().toString() =="Debit") {
            		// mode is cash or debit
            		
            		lblStatus.setVisible(false);		// hide status, assume to be paid
            		selectStatus.setVisible(false);	
            		
            		lblDueDate.setVisible(false);		// hide due date
            		chooserDueDate.setVisible(false);
            		
            		emptyPanel2.setVisible(true);		// show empty panel for design purpose only
                } else {
                	// mode is credit
                	
            		lblStatus.setVisible(true);			// show status
            		selectStatus.setVisible(true);
            		
            		lblDueDate.setVisible(true);		// show due date
            		chooserDueDate.setVisible(true);
            		
            		emptyPanel2.setVisible(false);		// hide empty panel for design purpose only
            	}
            }
        });
        
        selectStatus.addActionListener (new ActionListener () { // on-change of status
            public void actionPerformed(ActionEvent e) {
            	if(selectStatus.getSelectedItem().toString() == "Paid") {
            		// status is paid
            		
            		lblDueDate.setVisible(false);		// hide due date
            		chooserDueDate.setVisible(false);
            		
            		emptyPanel2.setVisible(true);		// show empty panel for design purpose only
                } else {
            		// status is unpaid
                	
            		lblDueDate.setVisible(true);		// show due date
            		chooserDueDate.setVisible(true);
            		
            		emptyPanel2.setVisible(false);		// hide empty panel for design purpose only
            	}
            }
        });
	}
}
