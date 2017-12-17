import model.*;
import model.database.*;

public class Test {

	public static void main (String[] args) {
		Driver.login ();
		AccountHelper accountHelper = new AccountHelper ();
//		accountHelper.addAccount ("wot", new byte[] {0}, 1, new byte[] {1});
//		accountHelper.editAccount (1,"wot", new byte[] {0}, 1, new byte[] {1});
//		accountHelper.deleteAccount (1);
//		accountHelper.getSalt ("user");
//		accountHelper.getAccount (1);
//		accountHelper.verifyAccount ("user", new byte[] {1,2,3,4});

		AccountModel am = AccountModel.getInstance ();
		//accountHelper.deleteAccount (1);
//		am.createAccount ("admin", "1234", 1);
//		System.out.println (am.login ("hello", "1234"));
//		System.out.println (accountHelper.getAccount (1));

//		try {
//			am.getEncryptedPassword ("1234", am.generateSalt ());
//		} catch (Exception e) {
//			e.printStackTrace ();
//		}

		am.login ("nry", "jasper");
		System.out.println (am.getAccessLevel ());
		MySQLDatabase.getInstance ().close ();
	}

}
