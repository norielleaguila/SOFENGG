package model.beans;

import javafx.beans.property.*;

public class Category {

	public static final String TABLE_NAME = "Category";
	public static final String COL_CATEGORY_ID = "categoryID";
	public static final String COL_RANGE = "range";

	private final StringProperty categoryID;
	private final StringProperty range;

	public Category () {
		categoryID = new SimpleStringProperty ();
		range = new SimpleStringProperty ();
	}

	public Category (String categoryID, String range) {
		this.categoryID = new SimpleStringProperty (categoryID);
		this.range = new SimpleStringProperty (range);
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

	public String getRange () {
		return range.get ();
	}

	public StringProperty rangeProperty () {
		return range;
	}

	public void setRange (String range) {
		this.range.set (range);
	}

}
