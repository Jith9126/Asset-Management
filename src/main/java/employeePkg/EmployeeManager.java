package employeePkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.mindrot.jbcrypt.BCrypt;

import assetManage.Asset;
import servlets.LoginServ;
import util.Constants;
import util.CommonLogger;
import util.ConnectionClass;

public class EmployeeManager {
	Logger log;
	private static EmployeeManager instance;

    private EmployeeManager() {
//    	log = Logger.getLogger(EmployeeManager.class);
//		PropertyConfigurator.configure(getClass().getResource("/log4.properties"));
    	
    }

    public static EmployeeManager getInstance() {
        if (instance == null) {
            synchronized (EmployeeManager.class) {
                if (instance == null) {
                    instance = new EmployeeManager();
                }
            }
        }
        return instance;
    }

	
	
	public EmpEssential login(String EmpID, String passwd) throws Exception {
		log = CommonLogger.getCommon().getLogger(EmployeeManager.class);
		
		try {
			Connection conn = ConnectionClass.CreateCon().getConnection();
			PreparedStatement loginStatement = conn.prepareStatement(Constants.loginStatement);
			loginStatement.setString(1, EmpID);
			ResultSet rs = loginStatement.executeQuery();
			log.info("loginCheckQurryExucuted");
			
			if(rs.next()) {
				String hassedPass = rs.getString("Password");
				Employee currEmployee;
				Sysadmin currAdSysadmin;
				log.info("founded value"+rs.getString("UserType"));
				System.out.println(BCrypt.checkpw(passwd, hassedPass));
				if(BCrypt.checkpw(passwd, hassedPass)) {
					if(rs.getString("UserType").equals("Employee")) {
						PreparedStatement detailPs = conn.prepareStatement(Constants.getDeatilaStmt);
						detailPs.setString(1, EmpID);
						ResultSet drs = detailPs.executeQuery();
						drs.next();
						currEmployee = new Employee(drs.getString("ID"), drs.getString("Name"), drs.getString("Email"), drs.getString("Role"));
						log.info("Loged as employee");
						return currEmployee;
					}
					else if(rs.getString("UserType").equals("Vendor")){
						PreparedStatement detailPs = conn.prepareStatement(Constants.getDetailsOfAdmin);
						detailPs.setString(1, EmpID);
						ResultSet drs = detailPs.executeQuery();
						drs.next();
						currAdSysadmin = new Sysadmin(drs.getString("VendorID"), drs.getString("Name"), drs.getString("Email"), drs.getString("Role"));
						log.info("Loged as employee");
						return currAdSysadmin;
					}
					
				}
				else {
					log.error("Wrong Password");
					throw new Exception("Wrong password");
				}
			}
			else {
				log.error("User name not found");
				throw new Exception("User name not found");
			}
			
		}catch(SQLException e) {
			log.error(e.getMessage());
			throw new Exception("Some Unexpected Error Occured Please contact the admin!");
		}
		catch(Exception e) {
			log.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return null;
	}
	
	
	public EmpEssential sessionLogIn(String sessionId) throws Exception {
		
		log = CommonLogger.getCommon().getLogger(EmployeeManager.class);
		try {
			
			Connection conn = ConnectionClass.CreateCon().getConnection();
			PreparedStatement sessionStmt = conn.prepareStatement(Constants.getFromSessionId);
			sessionStmt.setString(1, sessionId);
			ResultSet sessionUser = sessionStmt.executeQuery();
			log.info("session to user id created");
			
			if(sessionUser.next()) {
				log.info("there is value"+sessionUser.getString("userID"));
				String UserId = sessionUser.getString("userID");
				log.info("User Id:"+UserId);
				PreparedStatement empst = conn.prepareStatement(Constants.loginStatement);
				empst.setString(1, UserId);
				ResultSet emprs = empst.executeQuery();
				emprs.next();
				log.info("user id created"+emprs.getString("UserType"));
				
				if(emprs.getString("UserType").equals("Employee")) {
					log.info("in create query");
					PreparedStatement detailPs = conn.prepareStatement(Constants.getDeatilaStmt);
					detailPs.setString(1, UserId);
					ResultSet drs = detailPs.executeQuery();
					drs.next();
					
					log.info("in User qury");
					String name = drs.getString("Name");
					String role = emprs.getString("UserType");
					String email = drs.getString("Email");

//					PreparedStatement userAssetStmt = conn.prepareStatement(Constants.getAssetFromUserID);
					
					EmpEssential currEmp = new Employee(UserId, name, email, role);
					return currEmp;
				}				
			}
			
			
		}catch(SQLException e){
			log.error(e.getMessage().toString());
			throw new Exception("Some Unexpected Error Occured Please contact the admin!");
		}
		
		return null;
		
	}
	public void addSessionId(String sessionId,String userId) throws Exception {
		
		log = CommonLogger.getCommon().getLogger(EmployeeManager.class);
		
		
		try {
		long threeDaysInMilliseconds = 3 * 24 * 60 * 60 * 1000;
		long currTime = System.currentTimeMillis();
		//Date curDate = new Date();
		
		
		Connection conn = ConnectionClass.CreateCon().getConnection();
		
		PreparedStatement sessionInst = conn.prepareStatement(Constants.insertIntoSession);
		sessionInst.setString(1, sessionId);
		sessionInst.setString(2, userId);
		sessionInst.setDate(3, new java.sql.Date(currTime));
		sessionInst.setDate(4, new java.sql.Date(currTime+threeDaysInMilliseconds));
		int sessionExeuc = sessionInst.executeUpdate();
		
		log.info("value Inserted "+sessionExeuc);
		
		}
		catch(SQLException e) {
			log.error("Cannot Inset Values"+e.getMessage());
		}
				
	}
	
}
