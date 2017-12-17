package model.database;

import model.beans.*;

import java.sql.*;
import java.util.*;

public class IncurredFeeHelper extends MySQLHelper {

	public IncurredFee getIncurredFee (String unitNo, int feeID, String date) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (IncurredFee.TABLE_NAME)
				.append (" where ")
				.append (IncurredFee.COL_UNIT_NO).append (" = ? and ")
				.append (IncurredFee.COL_FEE_ID).append (" = ? and ")
				.append (IncurredFee.COL_DATE).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo, feeID, date});

		IncurredFee feesIncurred = null;

		try {
			if (rs.next ()) {
				feesIncurred = createIncurredFee (rs);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return feesIncurred;
	}

	public List<IncurredFee> getUnitFeesByRange (String unitNo, String dateStart, String dateEnd) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (IncurredFee.TABLE_NAME)
				.append (" where ").append (IncurredFee.COL_UNIT_NO)
				.append (" = ? and ").append (IncurredFee.COL_DATE)
				.append (" >= ? and ").append (IncurredFee.COL_DATE)
				.append (" < ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo, dateStart, dateEnd});

		List <IncurredFee> incurredFees = new ArrayList<> ();

		try {
			while (rs.next ()) {
				IncurredFee incurredFee = createIncurredFee (rs);

				incurredFees.add (incurredFee);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		} catch (NullPointerException e){}

		return incurredFees;
	}

	public boolean addIncurredFee (IncurredFee incurredFee) {
		StringBuilder sb = new StringBuilder ();
		
		sb.append ("insert into ").append (IncurredFee.TABLE_NAME)
				.append (" (")
				.append (IncurredFee.COL_UNIT_NO).append (", ")
				.append (IncurredFee.COL_FEE_ID).append (", ")
				.append (IncurredFee.COL_DATE).append (", ")
				.append (IncurredFee.COL_COUNT).append (", ")
				.append (IncurredFee.COL_TOTAL).append (", ")
				.append (IncurredFee.COL_PAYMENT).append (") ")
				.append ("values (?,?,?,?,?,?);");
		
		String query = sb.toString ();
		
		int result = database.executeUpdate (query,
					new Object[] {
						incurredFee.getUnitNo (),
						incurredFee.getFeeID (),
						incurredFee.getDate (),
						incurredFee.getCount (),
						incurredFee.getTotal (),
						incurredFee.getPayment ()
				});

		return result != -1;
	}

	private IncurredFee createIncurredFee (ResultSet rs) throws SQLException {
		String unitNo = rs.getString (IncurredFee.COL_UNIT_NO);
		int feeID = rs.getInt (IncurredFee.COL_FEE_ID);
		String date = rs.getString (IncurredFee.COL_DATE);
		int count = rs.getInt (IncurredFee.COL_COUNT);
		double total = rs.getDouble (IncurredFee.COL_TOTAL);
		double payment = rs.getDouble (IncurredFee.COL_PAYMENT);

		IncurredFee incurredFee =
				new IncurredFee (unitNo, feeID, date, count, total, payment);

		return incurredFee;
	}

}
