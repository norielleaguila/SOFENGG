package model.database;

import model.beans.*;

import java.sql.*;

public class AddressHelper extends MySQLHelper {

	public Address getAddress (String unitNo) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (Address.TABLE_NAME)
				.append (" where ").append (Address.COL_UNIT_NO)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo});

		Address address = null;

		try {
			if (rs.next ()) {
				String uNo = rs.getString (Address.COL_UNIT_NO);
				String addressNo = rs.getString (Address.COL_ADDRESS_NO);
				int streetID = rs.getInt (Address.COL_STREET_ID);

				address = new Address (uNo, addressNo, streetID);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return address;
	}

}
