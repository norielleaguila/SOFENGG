package model;

import model.beans.*;
import model.database.*;

import javax.crypto.*;
import javax.crypto.spec.*;
import java.security.*;
import java.security.spec.*;

public class AccountModel extends Model {

	private static final AccountModel instance = new AccountModel ();
	private AccountHelper accountHelper;
	private AccountTypeHelper accountTypeHelper;
	private AccountContainer accountContainer;

	private AccountModel () {
		accountHelper = new AccountHelper ();
		accountTypeHelper = new AccountTypeHelper ();
		accountContainer = new AccountContainer ();
	}

	public Account getAccount () {
		return accountContainer.account;
	}

	public Permissions getAccessLevel () {
		if (accountContainer.accountType.getTypeName ()
				.equalsIgnoreCase ("secretary")) {
			return Permissions.SECRETARY;
		}
		else if (accountContainer.accountType.getTypeName ()
				.equalsIgnoreCase ("admin")) {
			return Permissions.ADMIN;
		}

		return Permissions.NO_ACCESS;
	}

	public static AccountModel getInstance () {
		return instance;
	}

	public boolean createAccount (String username, String password, int type) {
		try {
			byte[] salt = generateSalt ();
			byte[] encryptedPass = getEncryptedPassword (password, salt);

			return accountHelper.addAccount (username, encryptedPass, type, salt);
		} catch (Exception e) {
			e.printStackTrace ();
		}

		return false;
	}

	public boolean editAccount (int id, String username, String password, int type) {
		try {
			byte[] salt = generateSalt ();
			byte[] encryptedPass = getEncryptedPassword (password, salt);

			return accountHelper.editAccount (id, username, encryptedPass, type, salt);
		} catch (Exception e) {
			e.printStackTrace ();
		}
		return false;
	}

	public boolean removeAccount () {
		int id = getAccount ().getAccountID ();
		logout ();
		return accountHelper.deleteAccount (id);
	}

	public boolean login (String username, String password) {
		byte[] salt = accountHelper.getSalt (username);
		if (salt == null)
			return false;

		int id = -1;

		try {
			id = accountHelper.verifyAccount (username, getEncryptedPassword (password, salt));
		} catch (Exception e) {
			e.printStackTrace ();
		}

		if (id == -1)
			return false;

		accountContainer.account = accountHelper.getAccount (id);
		if (getAccount () == null)
			return false;

		accountContainer.accountType =
				accountTypeHelper.getAccountType (getAccount ().getTypeID ());

		return true;
	}

	public void logout () {
		accountContainer.clear ();
	}

	private byte[] getEncryptedPassword (String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
		String algorithm = "PBKDF2WithHmacSHA1";
		int derivedKeyLength = 1024;
		int iterations = 20000;

		KeySpec spec = new PBEKeySpec (password.toCharArray (), salt, iterations, derivedKeyLength);

		SecretKeyFactory f = SecretKeyFactory.getInstance (algorithm);

		byte[] encryptedPass = f.generateSecret (spec).getEncoded ();

		return encryptedPass;
	}

	private byte[] generateSalt () throws NoSuchAlgorithmException {
		SecureRandom random = SecureRandom.getInstance ("SHA1PRNG");

		byte[] salt = new byte[16];
		random.nextBytes (salt);

		return salt;
	}

	private class AccountContainer {
		private Account account;
		private AccountType accountType;

		private void clear () {
			account = null;
			accountType = null;
		}
	}

	public static enum Permissions {
		ADMIN, SECRETARY, NO_ACCESS;
	}

}
