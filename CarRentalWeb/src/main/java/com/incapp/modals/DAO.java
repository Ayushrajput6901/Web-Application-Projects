package com.incapp.modals;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;

public class DAO {
	private Connection c;
	public DAO() throws ClassNotFoundException,SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		c=DriverManager.getConnection("jdbc:mysql://localhost:3306/carrental","root","Aayu@0110");
	}
	public void closeConnection() throws SQLException {
		c.close();
	}
	
	public String adminLogin(String id,String password) throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from admin where id=? and password=?");
		p.setString(1, id);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			return rs.getString("name");
		}else {
			return null;
		}
	}
	public String carOwnerLogin(String email,String password) throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from car_owners where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String status=rs.getString("status");
			if(status.equalsIgnoreCase("Pending")) {
				return "Pending";
			}
			return rs.getString("name");
		}else {
			return null;
		}
	}
	public String userLogin(String email,String password) throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from users where email=? and password=?");
		p.setString(1, email);
		p.setString(2, password);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			String status=rs.getString("status");
			if(status.equalsIgnoreCase("Pending")) {
				return "Pending";
			}
			return rs.getString("name");
		}else {
			return null;
		}
	}
	public void addEnquiry(String name,String phone) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into enquiries (name,phone,status) values(?,?,'Pending')");
		p.setString(1, name);
		p.setString(2, phone);
		p.executeUpdate();
	}
	public ArrayList<HashMap> getAllEnquiries()  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from enquiries order by id DESC");
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> enquiries=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> enquiry=new HashMap<>();
			enquiry.put("id", rs.getString("id"));
			enquiry.put("name", rs.getString("name"));
			enquiry.put("phone", rs.getString("phone"));
			enquiry.put("status", rs.getString("status"));
			
			enquiries.add(enquiry);
		}
		return enquiries;
	}
	public void changeEnquiryStatus(int id) throws SQLException{
		PreparedStatement p=c.prepareStatement("update enquiries set status='Done' where id=?");
		p.setInt(1, id);
		p.executeUpdate();
	}

	public void changeCarOwnerStatus(String email,String status) throws SQLException{
		PreparedStatement p;
		if(status.equalsIgnoreCase("Accept")) {
			p=c.prepareStatement("update car_owners set status='Accepted' where email=?");
		}else {
			p=c.prepareStatement("delete from car_owners where email=?");
		}
		p.setString(1, email);
		p.executeUpdate();
	}
	public boolean registerCarOwner(HashMap< String, String> carOwner,InputStream idproof) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into car_owners (email,password,name,phone,address,idproof,status) values(?,?,?,?,?,?,'Pending')");
		p.setString(1, carOwner.get("email"));
		p.setString(2, carOwner.get("password"));
		p.setString(3, carOwner.get("name"));
		p.setString(4, carOwner.get("phone"));
		p.setString(5, carOwner.get("address"));
		p.setBinaryStream(6, idproof);
		try {
			p.executeUpdate();
			return true;
		}catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
	public boolean registerUser(HashMap< String, String> user) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into users (email,password,name,phone,address,otp,status) values(?,?,?,?,?,?,'Pending')");
		p.setString(1, user.get("email"));
		p.setString(2, user.get("password"));
		p.setString(3, user.get("name"));
		p.setString(4, user.get("phone"));
		p.setString(5, user.get("address"));
		//generate OTP code
		int otp=(int)(Math.random()*9000+1000);
		p.setInt(6, otp);
		try {
			p.executeUpdate();
			SendMail.sendMail(user.get("email"),"Congrats, You have been Registration!", "Registration Accepted, Your OTP is "+otp);
			return true;
		}catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}

	public boolean checkOTP(String email,int otp) throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from users where email=? and otp=?");
		p.setString(1, email);
		p.setInt(2, otp);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			p=c.prepareStatement("update users set status='Active' where email=?");
			p.setString(1, email);
			p.executeUpdate();
			return true;
		}else {
			return false;
		}
	}
	public void bookCar(String reg_no,String user_email,String owner_email,int days,int total) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into bookings (reg_no,user_email,owner_email,days,total,book_date) values(?,?,?,?,?,CURRENT_DATE)");
		p.setString(1, reg_no);
		p.setString(2, user_email);
		p.setString(3, owner_email);
		p.setInt(4, days);
		p.setInt(5, total);
		p.executeUpdate();
		
		p=c.prepareStatement("update cars set status='Booked' where reg_no=?");
		p.setString(1, reg_no);
		p.executeUpdate();
	}
	public boolean addCar(HashMap< String, Object> car) throws SQLException{
		PreparedStatement p=c.prepareStatement("insert into cars (reg_no,name,owner_email,fuel_type,seats,rate_per_day,location,photo,status) values(?,?,?,?,?,?,?,?,'Available')");
		p.setString(1, (String)car.get("reg_no"));
		p.setString(2, (String)car.get("name"));
		p.setString(3, (String)car.get("owner_email"));
		p.setString(4, (String)car.get("fuel_type"));
		p.setInt(5, (int)car.get("seats"));
		p.setInt(6, (int)car.get("rate_per_day"));
		p.setString(7, (String)car.get("location"));
		p.setBinaryStream(8, (InputStream)car.get("photo"));
		try {
			p.executeUpdate();
			return true;
		}catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
	}
	public ArrayList<HashMap> getAllCarOwners()  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from car_owners order by status DESC");
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> carOwners=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> carOwner=new HashMap<>();
			carOwner.put("email", rs.getString("email"));
			carOwner.put("name", rs.getString("name"));
			carOwner.put("phone", rs.getString("phone"));
			carOwner.put("status", rs.getString("status"));
			carOwner.put("address", rs.getString("address"));
			
			carOwners.add(carOwner);
		}
		return carOwners;
	}
	public ArrayList<HashMap> getCarsByOwner(String owner_email)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from cars where owner_email=?");
		p.setString(1, owner_email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> cars=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> car=new HashMap<>();
			car.put("reg_no", rs.getString("reg_no"));
			car.put("name", rs.getString("name"));
			car.put("fuel_type", rs.getString("fuel_type"));
			car.put("rate_per_day", rs.getString("rate_per_day"));
			car.put("seats", rs.getString("seats"));
			car.put("location", rs.getString("location"));
			car.put("status", rs.getString("status"));
			
			cars.add(car);
		}
		return cars;
	}
	public ArrayList<HashMap> getCarsByLocation(String location)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from cars where location like ? and status='Available'");
		p.setString(1, "%"+location+"%");
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> cars=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> car=new HashMap<>();
			car.put("reg_no", rs.getString("reg_no"));
			car.put("name", rs.getString("name"));
			car.put("fuel_type", rs.getString("fuel_type"));
			car.put("rate_per_day", rs.getString("rate_per_day"));
			car.put("seats", rs.getString("seats"));
			car.put("location", rs.getString("location"));
			
			cars.add(car);
		}
		return cars;
	}

	public ArrayList<HashMap> getBookedCarsByUser(String email)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from bookings where user_email=?");
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> bookings=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> carBooking=new HashMap<>();
			carBooking.put("reg_no", rs.getString("reg_no"));
			carBooking.put("user_email", rs.getString("user_email"));
			carBooking.put("owner_email", rs.getString("owner_email"));
			carBooking.put("days", rs.getString("days"));
			carBooking.put("total", rs.getString("total"));
			carBooking.put("id", rs.getString("id"));
			carBooking.put("book_date", rs.getString("book_date"));
			
			bookings.add(carBooking);
		}
		return bookings;
	}
	public ArrayList<HashMap> getBookedCarsByOwner(String email)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from bookings where owner_email=?");
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		ArrayList<HashMap> bookings=new ArrayList<HashMap>();
		while(rs.next()) {
			HashMap< String, String> carBooking=new HashMap<>();
			carBooking.put("reg_no", rs.getString("reg_no"));
			carBooking.put("user_email", rs.getString("user_email"));
			carBooking.put("owner_email", rs.getString("owner_email"));
			carBooking.put("days", rs.getString("days"));
			carBooking.put("total", rs.getString("total"));
			carBooking.put("id", rs.getString("id"));
			carBooking.put("book_date", rs.getString("book_date"));
			
			bookings.add(carBooking);
		}
		return bookings;
	}
	public HashMap< String, String> getCarByReg(String reg_no)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select * from cars where reg_no=?");
		p.setString(1, reg_no);
		ResultSet rs=p.executeQuery();
		if(rs.next()) {
			HashMap< String, String> car=new HashMap<>();
			car.put("reg_no", rs.getString("reg_no"));
			car.put("name", rs.getString("name"));
			car.put("owner_email", rs.getString("owner_email"));
			car.put("fuel_type", rs.getString("fuel_type"));
			car.put("rate_per_day", rs.getString("rate_per_day"));
			car.put("seats", rs.getString("seats"));
			car.put("location", rs.getString("location"));
			car.put("status", rs.getString("status"));
			
			return car;
		}
		return null;
	}
	public byte[] getIDProof(String email)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select idproof from car_owners where email=?");
		p.setString(1, email);
		ResultSet rs=p.executeQuery();
		rs.next();
		return rs.getBytes("idproof");
	}
	public byte[] getCarPhoto(String reg_no)  throws SQLException{
		PreparedStatement p=c.prepareStatement("select photo from cars where reg_no=?");
		p.setString(1, reg_no);
		ResultSet rs=p.executeQuery();
		rs.next();
		return rs.getBytes("photo");
	}

	public void deleteCar(String reg_no)  throws SQLException{
		PreparedStatement p=c.prepareStatement("delete from cars where reg_no=?");
		p.setString(1, reg_no);
		p.executeUpdate();
	}
	public void makeAvailableCar(String reg_no)  throws SQLException{
		PreparedStatement p=c.prepareStatement("update cars set status='Available' where reg_no=?");
		p.setString(1, reg_no);
		p.executeUpdate();
	}
}
