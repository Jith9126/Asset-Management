package servlets;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.*;

import employeePkg.EmpEssential;
import employeePkg.Employee;
import employeePkg.EmployeeManager;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.http.Cookie;


import util.CommonLogger;
//import jakarta.servlet.http.Cookie;
import java.io.InputStream;

/**
 * Servlet implementation class LoginServ
 */
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public LoginServ() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		
		
		
		Logger log = CommonLogger.getCommon().getLogger(LoginServ.class);
		
		

		
		JSONObject json = new JSONObject();
		
		
		
		try {
			
			log.info("In servlet");
//			
//			StringBuilder jsonLoad = new StringBuilder();
//			BufferedReader br = request.getReader();
//			for (String line = br.readLine();line != null;line = br.readLine()) {
//				jsonLoad.append(line);
//			}
//			
//			json = new JSONObject(jsonLoad.toString());
			
		
		String fromParam = (String) request.getAttribute("details");
		
		Map<String, String[]> det = request.getParameterMap();
		
		
		HashMap<String, String[]> hashMap = new HashMap<String, String[]>(det);
		
		System.out.println(hashMap);
		
		
		
		
//		log.info("Json from param"+fromParam);
		json = new JSONObject(fromParam);
		log.info("jsonCreated");
		EmpEssential currEmp = EmployeeManager.getInstance().login(json.getString("userID"),json.getString("passwd"));
		log.info("Object Created");
		String sessionID = UUID.randomUUID().toString();
		
		Cookie cookie = new Cookie("sessionId",sessionID);
		
		
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		
		
		response.addCookie(cookie);
		EmployeeManager.getInstance().addSessionId(sessionID, json.getString("userID"));
		json.put("status",200);
		json.put("userObj", currEmp.toString());
		json.put("sessionID",sessionID);
		response.getWriter().write(json.toString());
		
		
		
		}
		catch (Exception e) {
			try {
				json.put("status", 500);
				json.put("error", e.getMessage());
				log.error("someThingWentWrong"+e.getMessage());
				response.getWriter().write(json.toString());
				System.out.println("done");
				response.getWriter().write("Vanakkam");
				
			} catch (JSONException e1) {
				log.error("someThingWentWrong"+e.getMessage());
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

}
