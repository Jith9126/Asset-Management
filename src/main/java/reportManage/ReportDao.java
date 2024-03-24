package reportManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.ConnectionClass;
import util.Constants;

public class ReportDao {
	
	
	
	public boolean createreport(Report report, int empId) throws Exception {
		
		
		try {
		
		Connection conn = ConnectionClass.CreateCon().getConnection();
		
		int AdminId = 0;
		PreparedStatement SelectAdmin = conn.prepareStatement(Constants.selectAdminWhoHasLowestWorks);
		
		ResultSet adminRS = SelectAdmin.executeQuery();
		
		if(adminRS.next()) {
			AdminId = adminRS.getInt("VendorID");
		}
		
		
		if(report.getReportType().equalsIgnoreCase("issue")) {
			
			
			PreparedStatement issueInsert = conn.prepareStatement(Constants.insertReport);
			
			issueInsert.setInt(1, AdminId);
			issueInsert.setInt(2, empId);
			issueInsert.setInt(3, report.getAsset().getAssetID());
			issueInsert.setDate(4, report.getReportDate());
			issueInsert.setString(5, report.getNotes());
			issueInsert.setString(6, "Pending");
			issueInsert.setString(7, report.getReportType());
			issueInsert.setString(8, report.getTitle());
			int afRows = issueInsert.executeUpdate();
			if(afRows>0) {
				return true;
			}
			else {
				throw new Exception("Something Went Wrong Try again later");
			}
			
			
		}
		else {
			return true;
		}
		
		
		
			
		}
		catch(SQLException e) {
			throw new Exception("Something Went Wrong Contact the admin");
		}
		
	}
}
