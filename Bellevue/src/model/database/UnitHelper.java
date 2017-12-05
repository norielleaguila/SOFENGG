package model.database;

import model.beans.*;

import java.sql.*;
import java.util.*;

public class UnitHelper extends MySQLHelper {

	public Unit getUnit (String unitNo) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("select * from ").append (Unit.TABLE_NAME)
				.append (" where ")
				.append (Unit.COL_UNIT_NO).append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {unitNo});
		Unit unit = null;

		try {
			if (rs.next ()) {
				String uNo = rs.getString (Unit.COL_UNIT_NO);
				double lotArea = rs.getDouble (Unit.COL_LOT_AREA);
				String category = rs.getString (Unit.COL_CATEGORY);
				int phaseNo = rs.getInt (Unit.COL_PHASE_NO);

				unit = new Unit ();
				unit.setUnitNo (uNo);
				unit.setLotArea (lotArea);
				unit.setCategory (category);
				unit.setPhaseNo (phaseNo);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return unit;
	}

	public List<Unit> searchUnits (String input) {
		StringBuilder sb = new StringBuilder ();

		String key = appendWildcards (input.toCharArray ());

		sb.append ("select * from ").append (Unit.TABLE_NAME)
				.append (" where ").append (Unit.COL_UNIT_NO)
				.append (" like ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[] {key});

		return createUnits (rs);
	}

	public List<Unit> getAllUnits () {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ")
				.append (Unit.TABLE_NAME).append (";");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[]{});

		return createUnits (rs);
	}

	private List<Unit> createUnits (ResultSet rs) {
		List<Unit> units = new ArrayList<> ();
		try {
			while (rs.next ()) {
				String unitNo = rs.getString (Unit.COL_UNIT_NO);
				double lotArea = rs.getDouble (Unit.COL_LOT_AREA);
				String category = rs.getString (Unit.COL_CATEGORY);
				int phaseNo = rs.getInt (Unit.COL_PHASE_NO);

				Unit u = new Unit (unitNo, lotArea, category, phaseNo);

				units.add (u);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return units;
	}

	private String appendWildcards (char[] input) {
		StringBuilder sb = new StringBuilder ();
		sb.append ("%");

		for (int i = 0; i < input.length; i++) {
			sb.append (input[i]).append ("%");
		}

		return sb.toString ();
	}

}
