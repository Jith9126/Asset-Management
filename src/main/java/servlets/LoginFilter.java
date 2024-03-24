package servlets;

import jakarta.servlet.http.HttpFilter;


//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.FilterConfig;
//import jakarta.servlet.ReadListener;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletInputStream;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpFilter;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletRequestWrapper;
//import jakarta.servlet.http.HttpServletResponse;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import util.CommonLogger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.*;

/**
 * Servlet Filter implementation class LoginFilter
 */
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	
	

	

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		
		
		
		
		HttpServletResponse rep = (HttpServletResponse) response;
		
		
			
			
			Logger log = CommonLogger.getCommon().getLogger(LoginFilter.class);
//			Logger log = Logger.getLogger(LoginFilter.class);
//			PropertyConfigurator.configure(getClass().getResource("/log4.properties"));
//			chain.doFilter(request, response);
			
			
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			
			StringBuilder jsonLoad = new StringBuilder();
			JSONObject jsonData;
			try {
				BufferedReader br = httpRequest.getReader();
				for (String line = br.readLine();line != null;line = br.readLine()) {
					jsonLoad.append(line);
				}
				
				log.info("jsonCreated");
				jsonData = new JSONObject(jsonLoad.toString());
				
//				request.he
				String userId = jsonData.getString("userID");
				String pass = jsonData.getString("passwd");
				if(userId != null & pass != null) {
					httpRequest.setAttribute("details", jsonData.toString());
					log.info("Value for Servlet is readey"+jsonData.toString());
					chain.doFilter(httpRequest, httpResponse);
					System.out.println("one");
					
					

					log.info("send to servlet");
				}
				
				
			}
			catch(JSONException e){
				try {
					jsonData = new JSONObject();
					jsonData.put("status", 500);
					jsonData.put("error", "Something Went Wrong");
					log.error("Error in Filter"+e.getMessage());
					response.getWriter().write(jsonData.toString());
				} catch (Exception e2) {
					
				}
				
			} 
			
			
			
	}
	

        


	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

