package employeePkg;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import assetManage.Asset;
import reportManage.Report;
import util.CommonLogger;
import util.ConnectionClass;
import util.Constants;
import notificationManage.Notification;
import org.json.*;
import org.apache.log4j.Logger;



public class Employee implements EmpEssential, Reportable {
	String Id,name,email,Role;
	List <Asset> asset;
	List<Report> reportedReports;
	List<Notification> notifications;
	Logger log;
	
	
	public Employee(String id, String name, String email, String role) {
//		super();
		log = CommonLogger.getCommon().getLogger(Employee.class);
		Id = id;
		this.name = name;
		this.email = email;
		Role = role;
		this.asset = new ArrayList<Asset>();
		this.reportedReports = new ArrayList<Report>();
	}
	
	public Employee(String id, String name, String email, String role, List<Asset> asset,
			List<Notification> notifications) {
		
		log = CommonLogger.getCommon().getLogger(Employee.class);
		Id = id;
		this.name = name;
		this.email = email;
		Role = role;
		this.asset = asset;
		this.notifications = notifications;
	}
	
	public List getAssets() {
		return asset;
	}
	
	
	@Override
	public String showId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getContactDetails() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getRole() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			
			json.put("Id", Id);
			json.put("name", name);
			json.put("email", email);
			json.put("Role", Role);
			log.info("Workin in employee");
		} catch (JSONException e) {
			
			log.error("Error in employeee to string");
			
		}
		
		
		
		return json.toString();
	}
	
	@Override
	public void login() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addReport(String title,String notes, String reportType, int assetId) {
		try {
		Connection conn = ConnectionClass.CreateCon().getConnection();
		PreparedStatement selectAdmin = conn.prepareStatement(Constants.selectAdminWhoHasLowestWorks);
		ResultSet adminRs = selectAdmin.executeQuery();
		adminRs.next();
		String admin = adminRs.getString(1);
		

		PreparedStatement reInsrstmpt = conn.prepareStatement(Constants.insertReport);
		reInsrstmpt.setString(1,admin);
		reInsrstmpt.setString(2,this.Id);
		reInsrstmpt.setInt(3, assetId);
		reInsrstmpt.setDate(4, new Date(System.currentTimeMillis()));
		reInsrstmpt.setString(5, notes);
		reInsrstmpt.setString(6, "Pending");
		reInsrstmpt.setString(7, reportType);
		reInsrstmpt.setString(8, title);
		
		
		
		
		
		
		
		
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	
}
