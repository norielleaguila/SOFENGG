package model;

import model.beans.*;
import model.database.*;

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
