package model.beans;

import javafx.beans.property.*;

public class Unit {

	public static final String TABLE_NAME = "Unit";
	public static final String COL_UNIT_NO = "unitNo";
	public static final String COL_LOT_AREA = "lotArea";
	public static final String COL_CATEGORY = "categoryID";
	public static final String COL_PHASE_NO = "phaseNo";

	private final StringProperty unitNo;
	private final DoubleProperty lotArea;
	private final StringProperty category;
	private final IntegerProperty phaseNo;

	public Unit () {
		unitNo = new SimpleStringProperty ();
		lotArea = new SimpleDoubleProperty ();
		category = new SimpleStringProperty ();
		phaseNo = new SimpleIntegerProperty ();
	}

	public Unit (String unitNo, double lotArea, String category, int phaseNo) {
		this.unitNo = new SimpleStringProperty (unitNo);
		this.lotArea = new SimpleDoubleProperty (lotArea);
		this.category = new SimpleStringProperty (category);
		this.phaseNo = new SimpleIntegerProperty (phaseNo);
	}

	public String getUnitNo () {
		return unitNo.get ();
	}

	public StringProperty unitNoProperty () {
		return unitNo;
	}

	public void setUnitNo (String unitNo) {
		this.unitNo.set (unitNo);
	}

	public double getLotArea () {
		return lotArea.get ();
	}

	public DoubleProperty lotAreaProperty () {
		return lotArea;
	}

	public void setLotArea (double lotArea) {
		this.lotArea.set (lotArea);
	}

	public String getCategory () {
		return category.get ();
	}

	public StringProperty categoryProperty () {
		return category;
	}

	public void setCategory (String category) {
		this.category.set (category);
	}

	public int getPhaseNo () {
		return phaseNo.get ();
	}

	public IntegerProperty phaseNoProperty () {
		return phaseNo;
	}

	public void setPhaseNo (int phaseNo) {
		this.phaseNo.set (phaseNo);
	}

}