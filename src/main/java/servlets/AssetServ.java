package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import assetManage.Asset;
import assetManage.AssetAccess;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.CommonLogger;

/**
 * Servlet implementation class AssetServ
 */
public class AssetServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AssetServ() {
        super();
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
//		Logger log = CommonLogger.getCommon().getLogger(LoginServ.class);
		
		
		
		JSONObject json = new JSONObject();
		JSONObject res = new JSONObject();
		Cookie ck = new Cookie("isWorking", "working");
		
		response.addCookie(ck);
		
		try {
			
			
			StringBuilder jsonLoad = new StringBuilder();
			BufferedReader br = request.getReader();
			for (String line = br.readLine();line != null;line = br.readLine()) {
				jsonLoad.append(line);
			}
//			log.info("working in servlet");
			json = new JSONObject(jsonLoad.toString());
			List <Asset> userAsset = AssetAccess.getInstence().getAsstsFromUser(json.getString("Id"));
			JSONArray asstArray = new JSONArray(userAsset.toString());
			res.put("status", 200);
			res.put("assets", asstArray.toString());
			response.getWriter().write(res.toString());
		}
		catch (Exception e) {
//			log.error(e.getMessage());
			try {
				res.put("status", 500);
				res.put("error", e.getMessage());
				response.getWriter().write(res.toString());
			} catch (JSONException e1) {
				// TODO Auto-s.printStackTrace();
				System.out.println(e1.getMessage());
			}
			
		}
	}

}
