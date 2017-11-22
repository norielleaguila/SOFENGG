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
import models.Unit;
public class DBaccess {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://localhost:3306/bellevuedb?zeroDateTimeBehavior=convertToNull";
	static final String USER = "root";
	static final String PASS = "0825";
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
				retval.add(new Collection(rs.getInt("UnitNo"),rs.getString("DatePaid"),rs.getString("BillingDate")));
			}
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
	/*
	public static String updateStatus(){
		
	}
	public static void checkupdateDues(Unit u){
		//Date date = new Date();
		LocalDate date = new LocalDate(0, 0, 0);
		//int year = date.getYear();
	}*/
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
	/*
	private Date getcurrentDate(){
		
		Date date = new Date();
		return dateFormat.format(date);
	}*/
	/*
	public static String getStatus(int UnitNo){
		try {
			connect();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM collection where UnitNo='"+UnitNo;
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()){
				retval = new Account();
				retval.setType(rs.getInt(1));
				retval.setUsername(rs.getString(2));
			}
			connect();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}*/
	
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
