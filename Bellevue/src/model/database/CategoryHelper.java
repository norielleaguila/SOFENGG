package model.database;

import model.beans.*;

import java.sql.*;

public class CategoryHelper extends MySQLHelper {

	public Category getCategory (String categoryID) {
		StringBuilder sb = new StringBuilder ();

		sb.append ("select * from ").append (Category.TABLE_NAME)
				.append (" where ").append (Category.COL_CATEGORY_ID)
				.append (" = ?;");

		String query = sb.toString ();

		ResultSet rs = database.executeQuery (query, new Object[]{categoryID});

		Category category = null;

		try {
			if (rs.next ()) {
				String cID = rs.getString (Category.COL_CATEGORY_ID);
				int minRange = rs.getInt (Category.COL_MIN_RANGE);
				int maxRange = rs.getInt (Category.COL_MAX_RANGE);

				category = new Category (cID, minRange, maxRange);
			}
		} catch (SQLException e) {
			e.printStackTrace ();
		}

		return category;
	}

}
