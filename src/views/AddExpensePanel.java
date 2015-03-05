package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import com.toedter.calendar.JDateChooser;

public class AddExpensePanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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

	/**
	 * 
	 * @param type, 'PURCHASE' or 'BILL', to use to display fields
	 */
	public AddExpensePanel(String type) {
		loadExpenseFields(type);
	}
	
	/// Listener methods ///
	
	/// Get methods ///
	
	public String getCategory() {
		return null; // TODO: return value of selectCategory
	}
	
	public String getMode() {
		return null; // TODO: return value of selectCategory
	}
	
	public String getStatus() {
		return null; // TODO: return value of selectCategory
	}
	
	public String getInterval() {
		return null; // TODO: return value of selectCategory
	}
	
	public String getProvider() {
		return textFieldProvider.getText();
	}
	
	public String getAddress() { // ?? unable to use 'getLocation()'
		return textFieldLocation.getText();
	}
	
	public Float getAmount() {
		return Float.valueOf(textFieldAmount.getText());
	}
	
	public String getDate() {
		return chooserDate.getDateFormatString();
	}
	
	public String getDueDate() {
		return chooserDueDate.getDateFormatString();
	}
	
	/// Design methods ///
	
	public void loadExpenseFields(String type) {
		
		Font lblFont = new Font("Tacoma", 0, 12); 		// Default font for label
		Font fieldFont = new Font("Tacoma", 0, 13); 	// Default font for fields

		Dimension lblSize = new Dimension(110, 32);		// Default dimension for label
		Dimension lblSizeSM = new Dimension(55, 32);		// Default dimension for label
		Dimension fieldSize = new Dimension(240, 32);		// Default dimension for label
		Dimension fieldSizeSM = new Dimension(120, 32);		// Default dimension for label
		
		this.setLayout(new GridBagLayout());				// Set layout to GidBagLayout
		GridBagConstraints gbc = new GridBagConstraints();	// the constraints
		gbc.insets = new Insets(5,5,5,5);					// Set the spacing between fields
		
		// Form title
		gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
		
		JLabel lblTitle;
		if(type.toLowerCase().equals("bill")) { // Title for add new bill
			lblTitle = new JLabel("New bill");
		} else {								// Title for add new purchase
			lblTitle = new JLabel("New purchase expense");
		}
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setFont(new Font("Tacoma", Font.BOLD, 18));
        lblTitle.setPreferredSize(new Dimension(350,39));
        this.add(lblTitle, gbc);
        // end ...
		
		// Label & fields for expense category
		gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
		
        JLabel lblCategory = new JLabel("Category");
        lblCategory.setHorizontalAlignment(SwingConstants.LEFT);
        lblCategory.setFont(lblFont);
        lblCategory.setPreferredSize(lblSize);
		this.add(lblCategory, gbc);

		gbc.gridx = 1; gbc.gridy = 1;
        
		selectCategory = new JComboBox<String>();
		selectCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Food", "Entertainment", "Insurance"}));
		selectCategory.setFont(lblFont);
		selectCategory.setPreferredSize(fieldSize);
		this.add(selectCategory, gbc);
		// end...
		
		// Label & fields for provider
		gbc.gridx = 0; gbc.gridy++;
		
        JLabel lblName = new JLabel("Description");
        lblName.setHorizontalAlignment(SwingConstants.LEFT);
        lblName.setFont(lblFont);
        lblName.setPreferredSize(lblSize);
		this.add(lblName, gbc);

		gbc.gridx = 1;
		
        textFieldProvider = new JTextField();
        textFieldProvider.setFont(fieldFont);
        textFieldProvider.setPreferredSize(fieldSize);
		this.add(textFieldProvider, gbc);
		// end...
		
		if(!type.toLowerCase().equals("bill")){	// Hide location for bill
			
			// Label & field for location
			gbc.gridx = 0; gbc.gridy++;
			
			JLabel lblLocation = new JLabel("Location");
			lblLocation.setFont(lblFont);
			lblLocation.setPreferredSize(lblSize);
			this.add(lblLocation, gbc);

			gbc.gridx = 1;
	        
	        textFieldLocation = new JTextField();
	        textFieldLocation.setFont(fieldFont);
	        textFieldLocation.setPreferredSize(fieldSize);
			this.add(textFieldLocation, gbc);
			// end...
		}
		
		// Label & fields for amount
		gbc.gridx = 0; gbc.gridy++;
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(lblFont);
		lblAmount.setPreferredSize(lblSize);
		this.add(lblAmount, gbc);
		
		gbc.gridx = 1;
        
        textFieldAmount = new JTextField();
        textFieldAmount.setFont(fieldFont);
        textFieldAmount.setPreferredSize(fieldSize);
		this.add(textFieldAmount, gbc);
		// end...

		if(!type.toLowerCase().equals("bill")) {	// Hide date & time for bill
			// Label & field for date
			gbc.gridx = 0; gbc.gridy++;
			
			JLabel lblDate = new JLabel("Date");
			lblDate.setFont(lblFont);
			lblDate.setPreferredSize(lblSize);
			this.add(lblDate, gbc);
			
			gbc.gridx = 1;
			
			chooserDate = new JDateChooser();
			chooserDate.setFont(fieldFont);
			chooserDate.setPreferredSize(fieldSize);
			this.add(chooserDate, gbc);
			// end...
			
			// Label & field for time
			gbc.gridx = 2;
			
			JLabel lblTime = new JLabel("Time");
			lblTime.setFont(lblFont);
			lblTime.setPreferredSize(lblSizeSM);
			this.add(lblTime, gbc);
			
			gbc.gridx = 3;
			
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
			gbc.gridx = 0; gbc.gridy++;
			
			JLabel lblInterval = new JLabel("Recurrence");
			lblInterval.setFont(lblFont);
			lblInterval.setPreferredSize(lblSize);
			this.add(lblInterval, gbc);
			
			gbc.gridx = 1;
	        
			selectInterval = new JComboBox<String>();
			selectInterval.setModel(new DefaultComboBoxModel<String>(new String[] {"Weekly", "Monthly", "Annually"}));
			selectInterval.setFont(lblFont);
			selectInterval.setPreferredSize(fieldSize);
			this.add(selectInterval, gbc);
			// end ...	
		}
		
		// Label & fields for account type
		gbc.gridx = 0; gbc.gridy++;
		
		JLabel lblMode = new JLabel("Pay by");
		lblMode.setFont(lblFont);
		lblMode.setPreferredSize(lblSize);
		this.add(lblMode, gbc);
		
		gbc.gridx = 1;
        
		selectMode = new JComboBox<String>();
		selectMode.setModel(new DefaultComboBoxModel<String>(new String[] {"Credit Card", "Cash", "Debit"}));
		selectMode.setFont(lblFont);
		selectMode.setPreferredSize(fieldSize);
		this.add(selectMode, gbc);
		// end ...
		
		// Label & field for status
		gbc.gridx = 2;
		
		final JLabel lblStatus = new JLabel("Status");
		lblStatus.setFont(lblFont);
		lblStatus.setPreferredSize(lblSizeSM);
		this.add(lblStatus, gbc);
		
		gbc.gridx = 3; 
        
		selectStatus = new JComboBox<String>();
		selectStatus.setModel(new DefaultComboBoxModel<String>(new String[] {"Unpaid", "Paid"}));
		selectStatus.setFont(fieldFont);
		selectStatus.setPreferredSize(fieldSizeSM);
		this.add(selectStatus, gbc);
		// end...
		
		// Label & field for due date
		gbc.gridx = 0; gbc.gridy++;
		
		final JLabel lblDueDate = new JLabel("Due date");
		lblDueDate.setFont(lblFont);
		lblDueDate.setPreferredSize(lblSize);
		this.add(lblDueDate, gbc);
		
		gbc.gridx = 1;
		
		chooserDueDate = new JDateChooser();
		chooserDueDate.setFont(fieldFont);
		chooserDueDate.setPreferredSize(fieldSize);
		this.add(chooserDueDate, gbc);
		// end...
		
		// Empty panel for layout only
		gbc.gridx = 0; gbc.gridy++;

		final JPanel emptyPanel = new JPanel();
		emptyPanel.setPreferredSize(new Dimension(110,32));
		this.add(emptyPanel, gbc);

		gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 4;

		final JPanel emptyPanel2 = new JPanel();
		emptyPanel2.setPreferredSize(new Dimension(555,32));
		emptyPanel2.setVisible(false);
		this.add(emptyPanel2, gbc);
		// end...
        
        this.validate();
        
        /// Listeners ///
        
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
