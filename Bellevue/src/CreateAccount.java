import model.*;
import model.database.*;

import java.io.*;

public class CreateAccount {
	public static void main (String[] args) {
		Driver.login ();

		AccountModel am = AccountModel.getInstance ();

		Console console = System.console ();

		System.out.print ("Enter username: ");
		String username = console.readLine ();
		System.out.print ("Enter password: ");
		String password = new String (console.readPassword ());
		System.out.print ("Enter access level: ");
		String type = console.readLine ();

		am.createAccount (username, password, Integer.parseInt (type));

		MySQLDatabase.getInstance ().close ();
	}

}
