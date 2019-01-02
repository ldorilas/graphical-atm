package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;

@SuppressWarnings("serial")
public class DepositView extends JPanel implements ActionListener {
		
	private ViewManager manager;			// manages interactions between the views, model, and database
	private JButton depositView;			// button that redirects users to the HomeView (if credentials match)
	private JButton withdrawView;
	private JButton transferView;
	private JButton logoutButton;
	
	/**
	 * Constructs an instance (or objects) of the LoginView class.
	 * 
	 * @param manager
	 */
	
	public DepositView(ViewManager manager) {
		super();
		
		this.manager = manager;
		this.errorMessageLabel = new JLabel("", SwingConstants.CENTER);
		initialize();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Updates the error message label.
	 * 
	 * @param errorMessage
	 */
	
	public void updateErrorMessage(String errorMessage) {
		errorMessageLabel.setText(errorMessage);
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the LoginView components.
	 */
	
	private void initialize() {
		this.setLayout(null);
		
	}
	
	/*
	 * Initializes the components needed for the account number textfield.
	 */
	private void initDepositView() {	
		depositView = new JButton("Deposit");
		depositView.setBounds(205, 180, 200, 35);
		depositView.addActionListener(this);
		
		this.add(depositView);
	}
	
	
	
	/*
	 * LoginView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The LoginView class is not serializable.");
	}

	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks performed in the LoginView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		if (source.equals(loginButton)) {
			manager.login(accountField.getText(), pinField.getPassword());
		} else if (source.equals(createButton)) {
			manager.switchTo(ATM.CREATE_VIEW);
		} else if (source.equals(powerButton)) {
			manager.shutdown();
		} else {
			System.err.println("ERROR: Action command not found (" + e.getActionCommand() + ")");
		}
	}
}
