package model;

import model.beans.*;
import model.database.*;

import java.util.*;

public class UnitModel extends Model {

	private UnitHelper unitHelper;
	private AddressHelper addressHelper;
	private BillingInfoHelper billingInfoHelper;
	private CategoryHelper categoryHelper;
	private StreetHelper streetHelper;
	private List <Unit> units;

	public UnitModel () {
		unitHelper = new UnitHelper ();
		addressHelper = new AddressHelper ();
		billingInfoHelper = new BillingInfoHelper ();
		categoryHelper = new CategoryHelper ();
		streetHelper = new StreetHelper ();
		units = new ArrayList<> ();
		setUnits ();
	}

	public void setUnits () {
		units.clear ();
		units = unitHelper.getAllUnits ();
		notifyViews ();
	}

	public void searchUnits (String key) {
		units.clear ();
		units = unitHelper.searchUnits (key);
		notifyViews ();
	}

	public UnitContainer getUnit (String unitNo) {
		UnitContainer unitContainer = new UnitContainer ();

		Unit unit = unitHelper.getUnit (unitNo);
		Address address = addressHelper.getAddress (unitNo);
		BillingInfo billingInfo = billingInfoHelper.getBillingInfo (unitNo);
		Category category = categoryHelper.getCategory (unit.getCategory ());
		Street street = streetHelper.getStreet (address.getStreetID ());

		unitContainer.setUnit (unit);
		unitContainer.setAddress (address);
		unitContainer.setBillingInfo (billingInfo);
		unitContainer.setCategory (category);
		unitContainer.setStreet (street);

		return unitContainer;
	}
	
	public List<Unit> getUnits(){
		return units;
	}
	
	public ArrayList<UnitContainer> getAllUnits(){
		ArrayList<UnitContainer> unitList = new ArrayList<>();
		
		for(Unit unit : units){
			unitList.add(getUnit(unit.getUnitNo()));
		}
		
		return unitList;
	}

	public class UnitContainer {
		private Unit unit;
		private Address address;
		private BillingInfo billingInfo;
		private Category category;
		private Street street;

		private UnitContainer () {}

		public Unit getUnit () {
			return unit;
		}

		public void setUnit (Unit unit) {
			this.unit = unit;
		}

		public Address getAddress () {
			return address;
		}

		public void setAddress (Address address) {
			this.address = address;
		}

		public BillingInfo getBillingInfo () {
			return billingInfo;
		}

		public void setBillingInfo (BillingInfo billingInfo) {
			this.billingInfo = billingInfo;
		}

		public Category getCategory () {
			return category;
		}

		public void setCategory (Category category) {
			this.category = category;
		}

		public Street getStreet () {
			return street;
		}

		public void setStreet (Street street) {
			this.street = street;
		}
	}

}
