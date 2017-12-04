package model.beans;

import javafx.beans.property.*;

public class Category {

	public static final String TABLE_NAME = "Category";
	public static final String COL_CATEGORY_ID = "categoryID";
	public static final String COL_MIN_RANGE = "minRange";
	public static final String COL_MAX_RANGE = "maxRange";

	private final StringProperty categoryID;
	private final IntegerProperty minRange;
	private final IntegerProperty maxRange;

	public Category () {
		categoryID = new SimpleStringProperty ();
		minRange = new SimpleIntegerProperty ();
		maxRange = new SimpleIntegerProperty ();
	}

	public Category (String categoryID, int minRange, int maxRange) {
		this.categoryID = new SimpleStringProperty (categoryID);
		this.minRange = new SimpleIntegerProperty (minRange);
		this.maxRange = new SimpleIntegerProperty (maxRange);
	}

	public String getCategoryID () {
		return categoryID.get ();
	}

	public StringProperty categoryIDProperty () {
		return categoryID;
	}

	public void setCategoryID (String categoryID) {
		this.categoryID.set (categoryID);
	}

	public int getMinRange () {
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
