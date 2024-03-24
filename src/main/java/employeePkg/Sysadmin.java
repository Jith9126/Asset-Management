package employeePkg;

import java.util.List;

import assetManage.Asset;
import notificationManage.Notification;
import reportManage.Report;

public class Sysadmin implements AssetManageable, EmpEssential {
	String Id,name,email,Role;
	List<Report> unCompletedReports;
	List<Report> completedReports;
	List<Notification> notifications;
	
	
	public Sysadmin(String id, String name, String email, String role) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		Role = role;
	}
	
	
	@Override
	public String toString() {
		return "{Id:" + Id + ", name:" + name + ", email:" + email + ", Role:" + Role + "}";
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
	public void login() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addAsset() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean removeAsset() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
