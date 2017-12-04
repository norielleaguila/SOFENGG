package model.database;

import model.beans.*;

import java.sql.*;

/**
 * class AccountHelper
 * parent class MySQLHelper
 * purpose: performs all crud operations for Account table
 */
public class AccountHelper extends MySQLHelper {

	/**
	 * adds a new account to database
	 * @param username account username
	 * @param password encrypted password using PBKDF2WithHmacSHA1 algorithm
	 * @param type account type
	 * @param salt salt for password encryption
	 * @return (1) true if successful (2) false if failed
	 */
	public boolean addAccount (String username, byte[] password, int type, byte[] salt) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("insert into ").append (Account.TABLE_NAME).append (" (")
				.append (Account.COL_USER).append (", ")
				.append (Account.COL_PASS).append (", ")
				.append (Account.COL_TYPE_ID).append (", ")
				.append (Account.COL_SALT).append (")")
				.append (" values (?,?,?,?);");

		String query = sb.toString ();

		int result = database.executeUpdate (query, new Object[] {username, password, type, salt});

		return result != -1;
	}

	/**
	 * edits an existing account in database
	 * @param id account id, must not be changed
	 * @param username account username can be replaced
	 * @param password new encrypted password to replace old password
	 * @param type account type can be replaced
	 * @param salt new salt for new encrypted password
	 * @return (1) true if successful (2) false if failed
	 */
	public boolean editAccount (int id, String username, byte[] password, int type, byte[] salt) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("update ").append (Account.TABLE_NAME).append (" set ")
				.append (Account.COL_USER).append (" = ?, ")
				.append (Account.COL_PASS).append (" = ?, ")
				.append (Account.COL_TYPE_ID).append (" = ?, ")
				.append (Account.COL_SALT).append (" = ? ")
				.append (" where ").append (Account.COL_ACCOUNT_ID).append (" = ?;");

		String query = sb.toString ();

		int result = database.executeUpdate (query, new Object[] {username, password, type, salt, id});

		return result != -1;
	}

	/**
	 * deletes an existing account
	 * @param id account id to be deleted
	 * @return (1) true if successful (2) false if failed
	 */
	public boolean deleteAccount (int id) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("delete from ").append (Account.TABLE_NAME)
				.append (" where ").append (Account.COL_ACCOUNT_ID).append (" = ?;");

		String query = sb.toString ();

		int result = database.executeUpdate (query, new Object[] {id});

		return result != -1;
	}

	/**
	 * retrieves an existing account assuming password has been verified.
	 * this function can only be called after verifying account.
	 * the proper steps to login is to
	 * 		1. get salt of a user (see getSalt)
	 * 		2. get the account id by verifying the account (see verifyAccount)
	 * 		3. call this function to get the account details (id, username, account type)
	 * @param id account id of an account
	 * @return Account object containing account information
	 */
	public Account getAccount (int id) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select ")
				.append (Account.COL_ACCOUNT_ID).append (", ")
				.append (Account.COL_USER).append (", ")
				.append (Account.COL_TYPE_ID)
				.append (" from ").append (Account.TABLE_NAME)
				.append (" where ")
				.append (Account.COL_ACCOUNT_ID).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {id});
		Account account = null;

		try {
			if (rs.next ()) {
					int accountID = rs.getInt (Account.COL_ACCOUNT_ID);
					int accountType = rs.getInt (Account.COL_TYPE_ID);
					String username = rs.getString (Account.COL_USER);

					account = new Account ();
					account.setAccountID (accountID);
					account.setTypeID (accountType);
					account.setUsername (username);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return account;
	}

	/**
	 * retrieves the salt of an account which will be used to verify password
	 * @param username account username
	 * @return account salt
	 */
	public byte[] getSalt (String username) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select ").append (Account.COL_SALT)
				.append (" from ").append (Account.TABLE_NAME)
				.append (" where ").append (Account.COL_USER).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[]{username});
		byte[] salt = null;

		try {
			if (rs.next ()) {
				salt = rs.getBytes (Account.COL_SALT);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return salt;
	}

	/**
	 * retrieves the id of an account which will be used to get the account of a user
	 * @param username account username
	 * @param password encrypted password
	 * @return account id
	 */
	public int verifyAccount (String username, byte[] password) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select ").append (Account.COL_ACCOUNT_ID)
				.append (" from ").append (Account.TABLE_NAME)
				.append (" where ")
				.append (Account.COL_USER).append (" = ? and ")
				.append (Account.COL_PASS).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {username, password});
		int id = -1;

		try {
			if (rs.next ()) {
				id = rs.getInt (Account.COL_ACCOUNT_ID);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return id;
	}

}
