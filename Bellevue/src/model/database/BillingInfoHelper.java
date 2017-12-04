package model.database;

import model.beans.*;

import java.sql.*;

public class BillingInfoHelper extends MySQLHelper {

	public BillingInfo getBillingInfo (String unitNo) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (BillingInfo.TABLE_NAME)
				.append (" where ").append (BillingInfo.COL_UNIT_NO)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo});
		BillingInfo billingInfo = null;

		try {
			if (rs.next ()) {
				String uNo = rs.getString (BillingInfo.COL_UNIT_NO);
				String billedTo = rs.getString (BillingInfo.COL_BILLED_TO);
				String TCT = rs.getString (BillingInfo.COL_TCT);

				billingInfo = new BillingInfo (uNo, billedTo, TCT);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return billingInfo;
	}

}
