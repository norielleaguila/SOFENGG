package model;

import model.beans.*;
import model.database.*;

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

	private static enum BillingMonth {
		JANUARY ("1-1", "2-1"),
		FEBRUARY ("2-1", "3-1"),
		MARCH ("3-1", "4-1"),
		APRIL ("4-1", "5-1"),
		MAY ("5-1", "6-1"),
		JUNE ("6-1", "7-1"),
		JULY ("7-1", "8-1"),
		AUGUST ("8-1", "9-1"),
		SEPTEMBER ("9-1", "10-1"),
		OCTOBER ("10-1", "11-1"),
		NOVEMBER ("11-1", "12-1"),
		DECEMBER ("12-1", "1-1");

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
