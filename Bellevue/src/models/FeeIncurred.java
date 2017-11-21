package models;

public class FeeIncurred {
	private static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private Fee fee;
	private String dateIncurred;
	
	
	public FeeIncurred(){
		dateIncurred = java.time.LocalDateTime.now().toString().replace("T", " ").split(" ")[0];
	}
	
	public FeeIncurred(Fee fee){
		this();
		this.fee = fee;
	}
	
	public FeeIncurred(Fee fee, String dateIncurred){
		this(fee);
		this.dateIncurred = dateIncurred;
	}
	
	/**
	 * gets the month that the fee was incurred
	 * @return	int as the month number
	 * 			Ex. 1 = January 
	 */
	public int getMonthIncurred(){
		return Integer.parseInt(dateIncurred.split("-")[1]);
	}
	
	/**
	 * gets the month that the fee was incurred but as a string
	 * @return	String from the list of months
	 */
	public String getMonthIncurredAsString(){
		return months[getMonthIncurred() - 1];
	}
	
	public int getDayIncurred(){
		return Integer.parseInt(dateIncurred.split("-")[2]);
	}
	
	public int getYearIncurred(){
		return Integer.parseInt(dateIncurred.split("-")[0]);
	}
	
	public Fee getFee(){
		return fee;
	}
	
	public int getFeeID(){
		return fee.getFeeID();
	}
}
