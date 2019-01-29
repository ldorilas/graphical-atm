package data;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import model.BankAccount;

public class Database {
	
	/*
	 * Field names for database table: accounts.
	 */
	
	public static final String ACCOUNT_NUMBER = "account_number";
	public static final String PIN = "pin";
	public static final String BALANCE = "balance";
	public static final String LAST_NAME = "last_name";
	public static final String FIRST_NAME = "first_name";
	public static final String DOB = "dob";
	public static final String PHONE = "phone";
	public static final String STREET_ADDRESS = "street_address";
	public static final String CITY = "city";
	public static final String STATE = "state";
	public static final String ZIP = "zip";
	public static final String STATUS = "status";
	
	private Connection connection;			// a connection to the database
	private Statement statement;				// the statement used to build inserts, updates and selects
	private ResultSet result;				// result set used for selects
	private DatabaseMetaData database;		// metadata about the database
	
	/**
	 * Constructs an instance (or object) of the Database class.
	 */
	
	public Database() {
		try {
			this.connect();
			this.setup();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Retrieves an existing account by account number and PIN.
	 * 
	 * @param accountNumber
	 * @param pin
	 * @return
	 */
	
	public BankAccount getAccount(long accountNumber, int pin) {
		try {
			statement = connection.createStatement();
			
			PreparedStatement selectStatementt = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ? AND pin = ?");
			selectStmt.setLong(1, accountNumber);
			selectStmt.setInt(2, pin);
			
			result = selectStatement.executeQuery();
			if (result.next()) {
				return new BankAccount(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Retrieves an existing account by account number.
	 * 
	 * @param accountNumber
	 * @return
	 */
	
	public BankAccount getAccount(long accountNumber) {
		try {
			statement = connection.createStatement();
			
			PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM accounts WHERE account_number = ?");
			selectStatement.setLong(1, accountNumber);
			
			result = selectStatement.executeQuery();
			if (result.next()) {
				return new BankAccount(result);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Inserts an account into the database.
	 * 
	 * @param account
	 * @return true if the insert is successful; false otherwise.
	 */
	
	public boolean insertAccount(BankAccount account) {
		try {
			statement = connection.createStatement();
			
			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");		
			insertStatement.setLong(1, account.getAccountNumber());
			insertStatement.setInt(2, account.getUser().getPin());
			insertStatement.setDouble(3, account.getBalance());
			insertStatement.setString(4, account.getUser().getLastName());
			insertStatement.setString(5, account.getUser().getFirstName());
			insertStatement.setInt(6, account.getUser().getBirthdate());
			insertStatement.setLong(7, account.getUser().getPhoneNumber());
			insertStatement.setString(8, account.getUser().getAddress());
			insertStatement.setString(9, account.getUser().getCity());
			insertStatement.setString(10, account.getUser().getState());
			insertStatement.setString(11, account.getUser().getZip());
			insertStatement.setString(12, String.valueOf(account.getStatus()));
			
			insertStatement.executeUpdate();
			insertStatement.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Performs a soft delete of an account by setting the status to closed.
	 * 
	 * @param account
	 * @return true if the transaction is successful; false otherwise.
	 */
	
	public boolean closeAccount(BankAccount account) {
		try {
			statement = connection.createStatement();
			
			PreparedStatement insertStatement = connection.prepareStatement("UPDATE accounts SET status = ? WHERE account_number = ?");		
			insertStatement.setString(1, "N");
			insertStatement.setLong(2, account.getAccountNumber());
			
			insertStatement.executeUpdate();
			insertStatement.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * Updates all potentially edited fields (i.e., PIN, account balance, phone number,
	 * street address, city, state, and zip code).
	 * 
	 * @param account
	 * @return true if the transaction is successful; false otherwise.
	 */
	
	public boolean updateAccount(BankAccount account) {
		try {
			statement = conn.createStatement();
			
			// all editable fields are included in this update statement
			
			PreparedStatement insertStatement = connection.prepareStatement(
				"UPDATE accounts SET " +
					"pin = ?, " +
					"balance = ?, " +
					"phone = ?, " +
					"street_address = ?, " +
					"city = ?, " +
					"state = ?, " +
					"zip = ? " +
				"WHERE account_number = ?"
			);		
			insertStatement.setInt(1, account.getUser().getPin());
			insertStatement.setDouble(2, account.getBalance());
			insertStatement.setLong(3, account.getUser().getPhone());
			insertStatement.setString(4, account.getUser().getStreetAddress());
			insertStatement.setString(5, account.getUser().getCity());
			insertStatement.setString(6, account.getUser().getState());
			insertStatement.setString(7, account.getUser().getZip());
			insertStatement.setLong(8, account.getAccountNumber());
			
			insertStatement.executeUpdate();
			insertStatement.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace(); 
		}
		
		return false;
	}
	
	/**
	 * Shuts down the database, releasing all allocated resources.
	 * 
	 * @throws SQLException
	 */
	
	public void shutdown() throws SQLException {
		if (result != null) result.close();
		if (connection != null) conn.close();
	}
	
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Establishes a connection to the database.
	 * 
	 * @throws SQLException
	 */
	
	private void connect() throws SQLException {
		Properties props = new Properties();
        props.put("user", "user1");
        props.put("password", "user1");
	}
	
	/*
	 * Performs initial database setup.
	 * 
	 * @throws SQLException
	 */
	
	private void setup() throws SQLException {
		createAccountsTable();
		insertDefaultAccount();
	}
	
	/*
	 * Creates the initial accounts table. This will only be done once during initial setup.
	 * 
	 * @throws SQLException
	 */
	
	private void createAccountsTable() throws SQLException {
		database = conn.getMetaData();
		result = database.getTables(null, "USER1", "ACCOUNTS", null);
		
		if (!result.next()) {
			statement = connection.createStatement();
			
			statement.execute(
				"CREATE TABLE accounts (" +
					"account_number BIGINT PRIMARY KEY, " +
					"pin INT, " +
					"balance FLOAT, " +
					"last_name VARCHAR(20), " +
					"first_name VARCHAR(15), " +
					"birthdate INT, " +
					"phone BIGINT, " +
					"street_address VARCHAR(30), " +
					"city VARCHAR(30), " +
					"state VARCHAR(2), " +
					"zip VARCHAR(5), " +
					"status CHAR(1)" +
				")"
			);
		}
	}
	
	/*
	 * Inserts a default account into the database. This will only be done once during initial setup.
	 * 
	 * @throws SQLException
	 */
	
	private void insertDefaultAccount() throws SQLException {
		statement = connection.createStatement();
		result = statement.executeQuery("SELECT COUNT(*) FROM accounts");
		if (result.next() && result.getInt(1) == 0) {
			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO accounts VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			insertStatement.setLong(1, 100000001L);
			insertStatement.setInt(2, 1234);
			insertStatement.setDouble(3, 0.00);
			insertStatement.setString(4, "Wilson");
			insertStatement.setString(5, "Ryan");
			insertStatement.setInt(6, 19700707);
			insertStatement.setLong(7, 55555555555L);
			insertStatement.setString(8, "1776 Raritan Road");
			insertStatement.setString(9, "Scotch Plains");
			insertStatement.setString(10, "NJ");
			insertStatement.setString(11, "07065");
			insertStatement.setString(12, "Y");
			
			insertStatement.executeUpdate();
			insertStatement.close();
		}
	}
}
