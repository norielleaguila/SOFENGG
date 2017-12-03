package models;

/**
 * 
 * @author AGUILA, Norielle
 *
 */

public class FeeIncurred {
	private static String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
	private Fee fee;
	private String dateIncurred;
	private int times;
	private Double price;
	private String name;
	private int CollectionID;
	private int UnitNo;
	
	
	public FeeIncurred(){
		dateIncurred = java.time.LocalDateTime.now().toString().replace("T", " ").split(" ")[0];
	}
	
	public FeeIncurred(Fee fee,int times){
		this();
		this.fee = fee;
		this.times=times;
		this.fee = fee;
		this.price=fee.getPrice();
		this.name=fee.getFeeName();
	}
	
	public FeeIncurred(Fee fee,int times, String dateIncurred,int CollectionID,int UnitNo){
		this(fee,times);
		this.dateIncurred = dateIncurred;
		this.CollectionID=CollectionID;
		this.UnitNo=UnitNo;
	}
	
	/**
	 * gets the month that the fee was incurred
	 * @return	int as the month number
	 * 			Ex. 1 = January 
	 */
	public int getMonthIncurred(){
		return Integer.parseInt(dateIncurred.split("-")[1]);
	}
	
	public String getDateIncurred() {
		return dateIncurred;
	}

	public int getCollectionID() {
		return CollectionID;
	}
	public void addTimes(int times){
		this.times+=times;
	}
	public int getUnitNo() {
		return UnitNo;
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
	public Double getPrice(){
		System.out.println("name of fee is "+this.name+"and price"+this.price);

		return this.price;
	}
	public int getTimes(){
		return this.times;
	}
	public String getName(){
		System.out.println("name of fee is "+this.name);
		return this.name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getFeeID(){
		return fee.getFeeID();
	}
}
