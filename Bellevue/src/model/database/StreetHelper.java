package model.database;

import model.beans.*;

import java.sql.*;

public class StreetHelper extends MySQLHelper {

	public Street getStreet (int streetId) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (Street.TABLE_NAME)
				.append (" where ").append (Street.COL_STREET_ID)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {streetId});
		Street street = null;

		try {
			if (rs.next ()) {
				int sID = rs.getInt (Street.COL_STREET_ID);
				String streetName = rs.getString (Street.COL_STREET_NAME);

				street = new Street (sID, streetName);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return street;
	}

}
