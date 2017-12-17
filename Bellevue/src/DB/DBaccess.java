package DB;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.Label;
import models.Account;
import models.Collection;
import models.Fee;
import models.FeeIncurred;
import models.FeeList;
import models.FeesIncurred;
import models.Unit;
public class DBaccess {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/bellevuedb?zeroDateTimeBehavior=convertToNull&useSSL=false";
	static final String USER = "root";
	static final String PASS = "none";
	public static Account UserAccount=null;
	private static Connection conn = null;
	private static Statement stmt = null;

	
	public static ArrayList<Unit> getUnitsData(){
		ArrayList<Unit> retval= new ArrayList<Unit>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * from unit";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(new Unit(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),rs.getString(5),rs.getFloat(6),rs.getInt(8)));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return retval;
	}
	
	public static ArrayList<Collection> getCollectionData(){
		ArrayList<Collection> retval= new ArrayList<Collection>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * from collection";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(new Collection(rs.getInt("CollectionID"),rs.getInt("UnitNo"),rs.getString("DatePaid"),rs.getString("BillingDate")));
			}
			connect();
			for(Collection collection:retval){
				collection.addFees();
				for(FeeIncurred f:collection.getAllFee()){
					//System.out.println("notice me -- "+collection.getUnitNo()+" "+
						//	collection.getCollectionID()+" "+
						//	f.getFeeID()+" "+f.getName());
					
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return retval;
	}
	public static ArrayList<FeeIncurred> getFeesIncurred(int CollectionID){
		ArrayList<FeeIncurred> retval = new ArrayList<FeeIncurred>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM feesincurred where CollectionID="+CollectionID+";" ;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(new FeeIncurred(FeeList.getFeeByID(rs.getInt("FeeID")),rs.getInt("NoOfIncurs"),
						rs.getString("DateIncurred"),rs.getInt("CollectionID"),rs.getInt("UnitNo")));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return retval;
	}
	public static boolean addFeeIncurred(FeeIncurred fee){
		boolean retval=true;
		try {
			connect();
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO feesincurred (`UnitNo`, `FeeID`, `NoOfIncurs`"
					+ ", `DateIncurred`, `CollectionID`) VALUES ('"+fee.getUnitNo()+"', '"
					+fee.getFeeID() +"', '"+fee.getTimes()+"', '"+fee.getDateIncurred()+"', '"
					+fee.getCollectionID()+"');" ;

			int rs = stmt.executeUpdate(sql);
			if(rs>0)
				retval=true;
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retval;
	}
	public static boolean addToExistingFee(FeeIncurred fee){
		boolean retval=true;
		try {
			connect();
			stmt = conn.createStatement();
			System.out.println("added times is "+fee.getTimes());
			String sql = "UPDATE feesincurred SET `NoOfIncurs`='"+fee.getTimes()
				+"' WHERE `UnitNo`='"+fee.getUnitNo()+"' and`FeeID`='"+fee.getFeeID()
				+"' and`CollectionID`='"+fee.getCollectionID()+"';" ;
			

			int rs = stmt.executeUpdate(sql);
			if(rs>0)
				retval=true;
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retval;
	}
	public static Account login(String username,String password){
		Account retval=null;
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT AccountNo,Type,Username FROM account where Username='"+username
					+"' and Password='"+password+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				retval = new Account();
				retval.setType(rs.getInt(1));
				retval.setUsername(rs.getString(2));
			
				UserAccount=retval;
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return retval;
	}
	public static ArrayList<Fee> getFees(){
		ArrayList<Fee> retval= new ArrayList<Fee>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * from fee";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(new Fee(rs.getInt("FeeID"),rs.getString("FeeName"),rs.getString("Type"),rs.getInt("price")));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return retval;
	}
	public static void removeFee(FeeIncurred fee){
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "Delete from feesincurred where UnitNo="+fee.getUnitNo()+" and FeeID="+
					fee.getFeeID()+" and CollectionID="+fee.getCollectionID()+";";
			stmt.executeUpdate(sql);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static void globalEditCategory(String category, String name){
		String afct=affectedFees(category);
		System.out.println("notice me hahaha"+afct);
		try {
			connect();
			stmt = conn.createStatement();
			String sql2 = "Update fee SET Type='"+name+"' where FeeID in "+
					afct+";";
			String sql3 = "Update category SET type='"+name+"' where type='"+category+"' ;";
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static void editFeeType(String category, String Ncategory, String name, String nameN, float price){
		System.out.println(category+" "+Ncategory+" "+name+" "+nameN+" "+price);
		try {
			connect();
			stmt = conn.createStatement();
			
			String sql = "Update fee SET Type='"+Ncategory+"' , FeeName='"+nameN+"' , price="+price+
					"  where FeeName='"+name+"';";
			System.out.println(sql);
			stmt.executeUpdate(sql);
			//stmt.executeUpdate(sql3);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	public static void deleteFeeType(String FeeName){
		int id=getFeeID(FeeName);
		try {
			connect();
			stmt = conn.createStatement();
			String sql2="Delete from feesincurred where FeeID="+id+";";
			String sql = "Delete from fee where FeeName='"+FeeName+"'";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	private static int getFeeID(String FeeName){
		int a=0;
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT FeeID from fee where FeeName='"+FeeName+"'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next())
				a=rs.getInt("FeeID");
			
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return a;
	}
	public static void globalRemoveCategory(String category){
		String afct=affectedFees(category);
		System.out.println("notice me hahaha"+afct);
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "Delete from feesincurred where FeeID in "+
					afct+";";
			String sql2 = "Delete from fee where FeeID in "+
					afct+";";
			String sql3 = "Delete from category where type='"+
					category+"' ;";
			stmt.executeUpdate(sql);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	private static String affectedFees(String category){
		String finval="(";
		ArrayList<Integer> retval= new ArrayList<Integer>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT FeeID from fee where Type='"+category+"'";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(rs.getInt("FeeID"));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		System.out.println("notice me hahaha score of "+retval.size());
		for(int i:retval){
			String temp=finval;
			finval=temp+i+",";
		}
		String temp=finval;
		finval=temp+0+")";
		return finval;
	}
	
	public static void editFee(FeeIncurred prev,FeeIncurred post){
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "Update feesincurred SET FeeID="+post.getFeeID()+", NoOfIncurs="+post.getTimes()+" where UnitNo="+prev.getUnitNo()+" and FeeID="+
					prev.getFeeID()+" and CollectionID="+prev.getCollectionID()+";";
			stmt.executeUpdate(sql);
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Fee> getUnitUnpaidList(Unit u){
		ArrayList<Fee> retval = new ArrayList<Fee>();
		
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * from  where UnitNo="+u.getUnitNo()+" and DatePaid="+null+";";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(new Fee(rs.getInt("FeeID"),rs.getString("FeeName"),rs.getString("Type"),rs.getInt("price")));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return retval;
	}
	public static int getFeeID(){
		int retval =0;
		
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT MAX(FeeID) FROM fee;" ;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				retval=rs.getInt(1)+1;
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return retval;
	}
	public static ArrayList<String> getTypes(){
		ArrayList<String> retval = new ArrayList<String>();
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT type FROM category;" ;
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				retval.add(rs.getString("Type"));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
		return retval;
	}
	public static boolean changeStatus(Collection c){
		boolean retval=false;
		try {
			connect();
			stmt = conn.createStatement();
			String sql="";
			if(c.getDatePaid()!=null){
				sql = "UPDATE collection SET DatePaid= '"+ c.getDatePaid()  +"' WHERE CollectionID="+c.getCollectionID()+ ";";
			}else{
				sql = "UPDATE collection SET DatePaid=null WHERE CollectionID="+c.getCollectionID()+ ";";
			}
		
			int num=stmt.executeUpdate(sql);
			if(num>0)
				retval =true;
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retval;
	}
	public static Boolean addType(String type){
		if(Fee.FEETYPE.contains(type))
			return false;
		boolean retval=false;
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "INSERT INTO category (`type`) VALUES ('"+type+"');" ;
			int rs = stmt.executeUpdate(sql);
			if(rs>0)
				retval=true;
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return retval;
	}
	public static boolean addFee(Fee f){
		boolean retval=false;
		try {
			connect();
			stmt = conn.createStatement();
			
			String sql = "INSERT INTO fee (`FeeName`, `price`, `Type`) VALUES ('"+f.getFeeName()
						+"',"+f.getPrice()+", '"+f.getType()+"');";
			int num=stmt.executeUpdate(sql);
			
			if(num>0)
				retval =true;
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return retval;
	}
	
	private static String getcurrentDate(){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		return dateFormat.format(date);
	}
	
	private static void connect() throws ClassNotFoundException, SQLException{
		if(conn==null){
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		}else{
			stmt.close();
		    conn.close();
		    stmt=null;
		    conn=null;
		}
			
	}
	
}
