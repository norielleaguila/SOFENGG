package model.database;

import model.beans.*;

import java.sql.*;
import java.util.*;

public class FeeHelper extends MySQLHelper {

	public Fee getFee (int id) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select * from ").append (Fee.TABLE_NAME)
				.append (" where ").append (Fee.COL_FEE_ID)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {id});

		Fee fee = null;

		try {
			if (rs.next ()) {
				fee = createFee (rs);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return fee;
	}
	
	public Fee getFee (String id) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select * from ").append (Fee.TABLE_NAME)
				.append (" where ").append (Fee.COL_FEE_NAME)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {id});

		Fee fee = null;

		try {
			if (rs.next ()) {
				fee = createFee (rs);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return fee;
	}

	public List<Fee> getAllFees () {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select * from ")
				.append (Fee.TABLE_NAME).append (";");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {});

		List<Fee> fees = new ArrayList<> ();

		try {
			while (rs.next ()) {
				fees.add (createFee (rs));
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return fees;
	}

	private Fee createFee (ResultSet rs) throws SQLException {
		int feeID = rs.getInt (Fee.COL_FEE_ID);
		String feeName = rs.getString (Fee.COL_FEE_NAME);
		int feeType = rs.getInt (Fee.COL_FEE_TYPE_ID);
		double feePrice = rs.getDouble (Fee.COL_FEE_PRICE);

		Fee fee = new Fee (feeID, feeName, feeType, feePrice);

		return fee;
	}

}
