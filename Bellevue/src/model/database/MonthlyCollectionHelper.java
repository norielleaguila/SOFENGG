package model.database;

import model.beans.*;

import java.sql.*;

public class MonthlyCollectionHelper extends MySQLHelper {

	public MonthlyCollection getCollection (String unitNo, String dateBilled) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (MonthlyCollection.TABLE_NAME)
				.append (" where ")
				.append (MonthlyCollection.COL_UNIT_NO).append (" = ? and ")
				.append (MonthlyCollection.COL_DATE_BILLED).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo, dateBilled});
		MonthlyCollection monthlyCollection = null;

		try {
			if (rs.next ()) {
				monthlyCollection = createCollection (rs);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return monthlyCollection;
	}

	public boolean addCollection (MonthlyCollection monthlyCollection) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("insert into ").append (MonthlyCollection.TABLE_NAME)
				.append (" (")
				.append (MonthlyCollection.COL_UNIT_NO).append (", ")
				.append (MonthlyCollection.COL_DATE_BILLED).append (", ")
				.append (MonthlyCollection.COL_DATE_DUE).append (", ")
				.append (MonthlyCollection.COL_DATE_PAID).append (", ")
				.append (MonthlyCollection.COL_MONTHLY_DUE).append (", ")
				.append (MonthlyCollection.COL_MONTHLY_PAID).append (", ")
				.append (MonthlyCollection.COL_MONTHLY_OVERDUE).append (") ")
				.append ("values (?,?,?,?,?,?,?);");

		String query = sb.toString ();

		int result = database.executeUpdate (query, new Object[] {
				monthlyCollection.getUnitNo (),
				monthlyCollection.getDateBilled (),
				monthlyCollection.getDateDue (),
				monthlyCollection.getDatePaid (),
				monthlyCollection.getMonthlyDue (),
				monthlyCollection.getMonthlyPaid (),
				monthlyCollection.getMonthlyOverdue ()
		});

		return result != -1;
	}

	private MonthlyCollection createCollection (ResultSet rs) throws SQLException {
		int transactionID = rs.getInt (MonthlyCollection.COL_T_ID);
		String unitNo = rs.getString (MonthlyCollection.COL_UNIT_NO);
		String dateBilled = rs.getString (MonthlyCollection.COL_DATE_BILLED);
		String dateDue = rs.getString (MonthlyCollection.COL_DATE_DUE);
		String datePaid = rs.getString (MonthlyCollection.COL_DATE_PAID);
		double monthlyDue = rs.getDouble (MonthlyCollection.COL_MONTHLY_DUE);
		double monthlyPaid = rs.getDouble (MonthlyCollection.COL_MONTHLY_PAID);
		double monthlyOverdue = rs.getDouble (MonthlyCollection.COL_MONTHLY_OVERDUE);

		MonthlyCollection monthlyCollection = new MonthlyCollection ();
		monthlyCollection.setTransactionID (transactionID);
		monthlyCollection.setUnitNo (unitNo);
		monthlyCollection.setDateBilled (dateBilled);
		monthlyCollection.setDateDue (dateDue);
		monthlyCollection.setDatePaid (datePaid);
		monthlyCollection.setMonthlyDue (monthlyDue);
		monthlyCollection.setMonthlyPaid (monthlyPaid);
		monthlyCollection.setMonthlyOverdue (monthlyOverdue);

		return monthlyCollection;
	}

}
