package model.database;

import model.beans.*;

import java.sql.*;

public class FeeTypeHelper extends MySQLHelper {

	public FeeType getFeeType (int feeTypeID) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (FeeType.TABLE_NAME)
				.append (" where ").append (FeeType.COL_FEE_TYPE_ID)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {feeTypeID});

		FeeType feeType = null;

		try {
			if (rs.next ()) {
				int id = rs.getInt (FeeType.COL_FEE_TYPE_ID);
				String type = rs.getString (FeeType.COL_FEE_TYPE);

				feeType = new FeeType (id, type);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return feeType;
	}

}
