package model;

import javafx.beans.property.*;
import javafx.collections.*;
import model.beans.*;
import model.database.*;

import java.time.*;
import java.util.*;

public class CollectionModel extends Model {

	private FeeHelper feeHelper;
	private FeeTypeHelper feeTypeHelper;
	private IncurredFeeHelper incurredFeeHelper;
	private MonthlyCollectionHelper monthlyCollectionHelper;

	public CollectionModel () {
		feeHelper = new FeeHelper ();
		feeTypeHelper = new FeeTypeHelper ();
		incurredFeeHelper = new IncurredFeeHelper ();
		monthlyCollectionHelper = new MonthlyCollectionHelper ();
	}

	public String getFeeTypeName (int feeTypeID) {
		return feeTypeHelper.getFeeType (feeTypeID).getFeeType ();
	}

	public List<Fee> getAllFees () {
		return feeHelper.getAllFees ();
	}
	
	public double getTotalFees(String unitNo){
		double total = 0;
		
		LocalDate todaydate = LocalDate.now();
		String date = todaydate.withDayOfMonth (1).toString ();
		int year = Integer.parseInt (date.substring (0, 4));
		BillingMonth billingMonth = BillingMonth.getMonthByStart (date.substring (5));
		
		List<IncurredFee> incurredFees = incurredFeeHelper.getUnitFeesByRange (unitNo, billingMonth.getStart (year), billingMonth.getEnd (year));
		
		for(IncurredFee fee : incurredFees){
			total += fee.getTotal();
		}
		
		return total;
	}

	public ObservableList<UnitFeeContainer> getUnitFee (String unitNo) {
		ObservableList<UnitFeeContainer> observableList = FXCollections.observableArrayList ();

		LocalDate todaydate = LocalDate.now();
		String date = todaydate.withDayOfMonth (1).toString ();
		int year = Integer.parseInt (date.substring (0, 4));
		BillingMonth billingMonth = BillingMonth.getMonthByStart (date.substring (5));
		
		List<IncurredFee> incurredFees = incurredFeeHelper.getUnitFeesByRange (unitNo, billingMonth.getStart (year), billingMonth.getEnd (year));

		if (incurredFees != null && !incurredFees.isEmpty ()) {
			for (IncurredFee incurredFee : incurredFees) {
				Fee fee = feeHelper.getFee (incurredFee.getFeeID ());
				UnitFeeContainer unitFeeContainer = new UnitFeeContainer (fee, incurredFee);

				observableList.add (unitFeeContainer);
			}
		}

		return observableList;
	}

	public CollectionContainer getCollection (String unitNo, int year, String date) {
		CollectionContainer collectionContainer = new CollectionContainer ();
		List<IncurredFeeContainer> incurredFeeContainers = new ArrayList<> ();

		BillingMonth billingMonth = BillingMonth.getMonthByStart (date);

		MonthlyCollection monthlyCollection =
				monthlyCollectionHelper
						.getCollection (unitNo,
								billingMonth.getStart (year));
		collectionContainer.monthlyCollection = monthlyCollection;

		List<IncurredFee> incurredFees =
				incurredFeeHelper
						.getUnitFeesByRange (unitNo,
								billingMonth.getStart (year),
								billingMonth.getEnd (year));

		for (IncurredFee i: incurredFees) {
			IncurredFeeContainer ifc = new IncurredFeeContainer ();

			Fee fee = feeHelper.getFee (i.getFeeID ());
			FeeType feeType = feeTypeHelper.getFeeType (fee.getFeeType ());

			ifc.incurredFee = i;
			ifc.fee = fee;
			ifc.feeType = feeType;

			incurredFeeContainers.add (ifc);
		}

		incurredFeeContainers.sort ((i1, i2) -> {
			int c1 = i1.feeType.getFeeType ().compareToIgnoreCase (i2.feeType.getFeeType ());
			if (c1 != 0)
				return c1;

			return i1.fee.getFeeName ().compareToIgnoreCase (i2.fee.getFeeName ());
		});

		collectionContainer.incurredFeeContainers = incurredFeeContainers;

		return collectionContainer;
	}

	public class CollectionContainer {
		private MonthlyCollection monthlyCollection;
		private List<IncurredFeeContainer> incurredFeeContainers;

		public MonthlyCollection getMonthlyCollection () {
			return monthlyCollection;
		}

		public List<IncurredFeeContainer> getIncurredFeeContainers () {
			return incurredFeeContainers;
		}
	}

	public class IncurredFeeContainer {
		private IncurredFee incurredFee;
		private Fee fee;
		private FeeType feeType;

		public IncurredFee getIncurredFee () {
			return incurredFee;
		}

		public Fee getFee () {
			return fee;
		}

		public FeeType getFeeType () {
			return feeType;
		}
	}

	public class UnitFeeContainer {
		private StringProperty feeName;
		private IntegerProperty count;
		private DoubleProperty total;

		private Fee fee;
		private IncurredFee incurredFee;

		public UnitFeeContainer (Fee fee, IncurredFee incurredFee) {
			this.fee = fee;
			this.incurredFee = incurredFee;
			feeName = fee.feeNameProperty ();
			count = incurredFee.countProperty ();
			total = incurredFee.totalProperty ();
		}

		public String getFeeName () {
			return feeName.get ();
		}

		public StringProperty feeNameProperty () {
			return feeName;
		}

		public void setFeeName (String feeName) {
			this.feeName.set (feeName);
		}

		public int getCount () {
			return count.get ();
		}

		public IntegerProperty countProperty () {
			return count;
		}

		public void setCount (int count) {
			this.count.set (count);
		}

		public double getTotal () {
			return total.get ();
		}

		public DoubleProperty totalProperty () {
			return total;
		}

		public void setTotal (double total) {
			this.total.set (total);
		}
		public IncurredFee getIncurredFee(){
			return incurredFee;
		}
	}

	private static enum BillingMonth {
		JANUARY ("01-01", "02-01"),
		FEBRUARY ("02-01", "03-01"),
		MARCH ("03-01", "04-01"),
		APRIL ("04-01", "05-01"),
		MAY ("05-01", "06-01"),
		JUNE ("06-01", "07-01"),
		JULY ("07-01", "08-01"),
		AUGUST ("08-01", "09-01"),
		SEPTEMBER ("09-01", "10-01"),
		OCTOBER ("10-01", "11-01"),
		NOVEMBER ("11-01", "12-01"),
		DECEMBER ("12-01", "01-01");

		private final String start;
		private final String end;

		BillingMonth (String start, String end) {
			this.start = start;
			this.end = end;
		}

		public static BillingMonth getMonthByStart (String s) {
			for (BillingMonth bm : BillingMonth.values ())
				if (bm.start.equals (s))
					return bm;

			return null;
		}

		public static BillingMonth getMonthByEnd (String e) {
			for (BillingMonth bm : BillingMonth.values ())
				if (bm.end.equals (e))
					return bm;

			return null;
		}

		public String getStart (int year) {
			StringBuilder sb = new StringBuilder ();
			sb.append (year).append ("-").append (start);

			return sb.toString ();
		}

		public String getEnd (int year) {
			StringBuilder sb = new StringBuilder ();

			if (this == DECEMBER)
				year += 1;

			sb.append (year).append ("-").append (end);
			return sb.toString ();
		}
	}

}
