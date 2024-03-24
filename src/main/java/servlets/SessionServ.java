package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.CommonLogger;

//import util.CommonLogger;
//
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONException;
import org.json.JSONObject;

import employeePkg.EmpEssential;
import employeePkg.EmployeeManager;
import jakarta.servlet.http.Cookie;

/**
 * Servlet implementation class SessionServ
 */
public class SessionServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);
		
		Logger log = CommonLogger.getCommon().getLogger(SessionServ.class);
//		PropertyConfigurator.configure(getClass().getResource("/log4.properties"));
		
		
		JSONObject json = new JSONObject();
		log.info("In Session");
		JSONObject resJson = new JSONObject();
		try {
			StringBuilder jsonLoad = new StringBuilder();
			BufferedReader br = request.getReader();
			for (String line = br.readLine();line != null;line = br.readLine()) {
				jsonLoad.append(line);
			}
			

			json = new JSONObject(jsonLoad.toString());
//			System.out.println("jsonCreated"+json.toString());
			String sessionId = "";
			Cookie[] cookie = request.getCookies();
			
			
			if(cookie == null) {
				throw new Exception("Sessoid Not found");
			}
			for(Cookie cokie:cookie) {
				System.out.println("hereCookie");
				if(cokie.getName().equals("sessionId")) {
					sessionId =  cokie.getValue();
				}
			}
			
			
			
			EmpEssential curEmp = EmployeeManager.getInstance().sessionLogIn(sessionId);
			resJson.put("status", "200");
			resJson.put("user", curEmp.toString());
			
			
			
			response.getWriter().write(resJson.toString());
			
		}
		catch (Exception e) {
			try {
				resJson.put("status",500);
				resJson.put("error", e.getMessage());
				response.getWriter().write(resJson.toString());
			}catch (JSONException el) {
				
			}
			
		}
		
	}

}
