package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
 
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class CreateView extends JPanel implements ActionListener {
	
	private String tempAbbreviation;
	private String tempMonth;
	private String tempDay;
	private String tempYear;
	
	private JButton finishButton = new JButton("Finish");
	private JButton logOutButton = new JButton("Exit");
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JComboBox yearDropdown;
	private JComboBox monthDropdown;
	private JComboBox dayDropdown;
	private JTextField phoneNumField;
	private JTextField strAddressField;
	private JTextField cityField;
	private JComboBox stateDropdown;
	private JTextField zipField;
	private JTextField pinField;
	private JButton createAccount;
	
	private ViewManager manager;		// manages interactions between the views, model, and database
	
	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */
	
	public CreateView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the CreateView components.
	 */
	private void initFirstNameField() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(firstNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		firstNameField = new JTextField(20);
		firstNameField.setBounds(205, 100, 200, 35);
		firstNameField.addActionListener(this);
		
		this.add(label);
		this.add(firstNameField);
	}
	
	private void initLastNameField() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(lastNameField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		lastNameField = new JTextField(20);
		lastNameField.setBounds(205, 100, 200, 35);
		lastNameField.addActionListener(this);
		
		this.add(label);
		this.add(lastNameField);
	}
	
	private void initYearDropdown() {
		JLabel label = new JLabel("Year", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(yearDropdown);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] years = { "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979", "1980", "1981", "1982", "1983", "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", "1999", "2000" };
		JComboBox<String> dropdown = new JComboBox<String>(years);
		JComboBox yearDropdown = dropdown;
		yearDropdown.addActionListener(this);
		
		this.add(label);
		this.add(yearDropdown);
	}
	
	private void initMonthDropdown() {
		JLabel label = new JLabel("Month", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(monthDropdown);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] months = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" };
		JComboBox<String> dropdown = new JComboBox<String>(months);
		JComboBox monthDropdown = dropdown;
		monthDropdown.addActionListener(this);
		
		this.add(label);
		this.add(monthDropdown);
	}
	
	private void initDayDropdown() {
		JLabel label = new JLabel("Day", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(dayDropdown);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
		JComboBox<String> dropdown = new JComboBox<String>(days);
		JComboBox dayDropdown = dropdown;
		dayDropdown.addActionListener(this);
		
		this.add(label);
		this.add(dayDropdown);
	}

	
	private void initPinField() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(100, 140, 95, 35);
		label.setLabelFor(pinField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		pinField = new JPasswordField(20);
		pinField.setBounds(205, 140, 200, 35);
		pinField.addActionListener(this);
		
		this.add(label);
		this.add(pinField);
	}
	
	private void initPhoneNumberField() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(phoneNumberField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		phoneNumberField = new JTextField(20);
		phoneNumberField.setBounds(205, 100, 200, 35);
		phoneNumberField.addActionListener(this);
		
		this.add(label);
		this.add(phoneNumberField);
	}
	
	private void initStrAddressField() {
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(strAddressField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		strAddressField = new JTextField(20);
		strAddressField.setBounds(205, 100, 200, 35);
		strAddressField.addActionListener(this);
		
		this.add(label);
		this.add(strAddressField);
	}
	
	private void initCityField() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(cityField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		cityField = new JTextField(20);
		cityField.setBounds(205, 100, 200, 35);
		cityField.addActionListener(this);
		
		this.add(label);
		this.add(cityField);
	}
	
	private void initStateDropdown() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(monthDropdown);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DC",  
		    "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN", "KS", "KY", "LA",  
		    "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE",  
		    "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC",  
		    "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"};
		JComboBox<String> dropdown = new JComboBox<String>(states);
		JComboBox stateDropdown = dropdown;
		stateDropdown.addActionListener(this);
		
		this.add(label);
		this.add(stateDropdown);
	
	}
	
	private void initZipField() {
		JLabel label = new JLabel("Zip Code", SwingConstants.RIGHT);
		label.setBounds(100, 100, 95, 35);
		label.setLabelFor(zipField);
		label.setFont(new Font("DialogInput", Font.BOLD, 14));
		
		zipField = new JTextField(20);
		zipField.setBounds(205, 100, 200, 35);
		zipField.addActionListener(this);
		
		this.add(label);
		this.add(zipField);
	}
	
	private void initialize() {
	
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the CreateView.
		
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		initFirstNameField();
		initLastNameField();
		initYearDropdown();
		initMonthDropdown();
		initDayDropdown();
		initPinField();
		initPhoneNumberField();
		initStrAddressField();
		initCityField();
		initStateDropdown();
		initZipField();
		
		
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}
	
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The CreateView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */
	
		public void finishForm() throws InterruptedException {
		
		String tempBirthdate = tempYear + tempMonth + tempDay;
		if (tempBirthdate.equals(null)) {
			tempMonth = (String) monthField.getSelectedItem();
			switch(tempMonth) {
			case "Jan":
				tempMonth = "01";
				break;
			case "Feb":
				tempMonth = "02";
				break;
			case "Mar":
				tempMonth = "03";
				break;
			case "Apr":
				tempMonth = "04";
				break;
			case "May":
				tempMonth = "05";
				break;
			case "Jun":
				tempMonth = "06";
				break;
			case "Jul":
				tempMonth = "07";
				break;
			case "Aug":
				tempMonth = "08";
				break;
			case "Sep":
				tempMonth = "09";
				break;
			case "Oct":
				tempMonth = "10";
				break;
			case "Nov":
				tempMonth = "11";
				break;
			case "Dec":
				tempMonth = "12";
				break;
			}
		}
		if (FNameField.getText().equals(null) || LNameField.getText().equals(null) || tempBirthdate.equals(null) || addressField.getText().equals(null) || cityField.getText().equals(null) || stateDropdown.getSelectedItem().equals(null) || zipField.getText().equals(null) || phoneNumberField.getText().equals(null) || pinField.getPassword().equals(null)) {
			updateErrorMessage("Please fill out all fields.");
		} else {
			manager.accountCreate('Y', 0.00, Integer.valueOf(new String(pinField.getPassword())), Integer.valueOf(tempBirthdate), Long.valueOf(phoneNumberField.getText()), firstNameField.getText(), lastNameField.getText(), addressField.getText(), cityField.getText(), tempAbbr, zipField.getText());
			updateErrorMessage("");
			manager.logOut();
		}
	}
///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
/*
* Responds to button clicks and other actions performed in the CreateView.
* 
* @param e
*/
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(logOutButton)) {
			manager.logOut();
			manager.switchTo(ATM.LOGIN_VIEW);
		}
		else if (source.equals(stateDropdown)) {
			String selection = (String) stateDropdown.getSelectedItem();
			tempAbbreviation = selection;
		}
		else if (source.equals(monthDropdown)) {
			tempMonth = (String) monthDropdown.getSelectedItem();
			switch(tempMonth) {
			case "Jan":
				tempMonth = "01";
				break;
			case "Feb":
				tempMonth = "02";
				break;
			case "Mar":
				tempMonth = "03";
				break;
			case "Apr":
				tempMonth = "04";
				break;
			case "May":
				tempMonth = "05";
				break;
			case "Jun":
				tempMonth = "06";
				break;
			case "Jul":
				tempMonth = "07";
				break;
			case "Aug":
				tempMonth = "08";
				break;
			case "Sep":
				tempMonth = "09";
				break;
			case "Oct":
				tempMonth = "10";
				break;
			case "Nov":
				tempMonth = "11";
				break;
			case "Dec":
				tempMonth = "12";
				break;
			}
		}
		else if (source.equals(dayDropdown)) {
			tempDay = (String) dayField.getSelectedItem();
		}
		else if (source.equals(yearDropdown)) {
			tempYear = (String) yearField.getSelectedItem();
		}
		else if (source.equals(finishButton)){
			try {
				finishForm();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				updateErrorMessage("Please enter valid information.");
			}
		}
		else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}

	public static JTextField getFirstNameField() {
		return firstNameField;
	}

	public static JTextField getLNameField() {
		return lastNameField;
	}
	public static JTextField getAddressField() {
		return addressField;
	}
	public static JTextField getCityField() {
		return cityField;
	}
	public static JTextField getZipField() {
		return zipField;
	}
	public static JTextField getPhoneField() {
		return phoneField;
	}
	public static JPasswordField getPinField() {
		return pinField;
	}
}
