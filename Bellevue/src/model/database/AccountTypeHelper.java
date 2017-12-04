package model.database;

import model.beans.*;

import java.sql.*;

public class AccountTypeHelper extends MySQLHelper {

	public AccountType getAccountType (int id) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select ")
				.append (AccountType.COL_TYPE_ID).append (", ")
				.append (AccountType.COL_TYPE_NAME)
				.append (" from ").append (AccountType.TABLE_NAME)
				.append (" where ").append (AccountType.COL_TYPE_ID)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {id});
		AccountType accountType = null;

		try {
			if (rs.next ()) {
				int typeID = rs.getInt (AccountType.COL_TYPE_ID);
				String typeName = rs.getString (AccountType.COL_TYPE_NAME);

				accountType = new AccountType ();
				accountType.setTypeID (typeID);
				accountType.setTypeName (typeName);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return accountType;
	}

}
