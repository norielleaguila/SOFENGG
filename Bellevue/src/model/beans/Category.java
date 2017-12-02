package model.beans;

import javafx.beans.property.*;

public class Category {

	public static final String TABLE_NAME = "Category";
	public static final String COL_CATEGORY_ID = "categoryID";
	public static final String COL_MIN_RANGE = "minRange";
	public static final String COL_MAX_RANGE = "maxRange";

	private final IntegerProperty categoryID;
	private final IntegerProperty minRange;
	private final IntegerProperty maxRange;

	public Category () {
		categoryID = new SimpleIntegerProperty ();
		minRange = new SimpleIntegerProperty ();
		maxRange = new SimpleIntegerProperty ();
	}

	public Category (int categoryID, int minRange, int maxRange) {
		this.categoryID = new SimpleIntegerProperty (categoryID);
		this.minRange = new SimpleIntegerProperty (minRange);
		this.maxRange = new SimpleIntegerProperty (maxRange);
	}

	public int getCategoryID () {
		return categoryID.get ();
	}

	public IntegerProperty categoryIDProperty () {
		return categoryID;
	}

	public void setCategoryID (int categoryID) {
		this.categoryID.set (categoryID);
	}

	public int getminRange () {
		return minRange.get ();
	}

	public IntegerProperty minRangeProperty () {
		return minRange;
	}

	public void setMinRange (int minRange) {
		this.minRange.set (minRange);
	}
	
	public int getMaxRange () {
		return maxRange.get ();
	}
	
	public IntegerProperty maxRangeProperty () {
		return maxRange;
	}
	
	public void setMaxRange (int maxRange) {
		this.maxRange.set (maxRange);
	}

}
