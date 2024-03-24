package assetManage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import employeePkg.Employee;
import util.ConnectionClass;
import util.Constants;

public class AssetAccess {
	private static AssetAccess asstAc;
	
	private AssetAccess() {
		
	}
	
	public static AssetAccess getInstence() {
		if(asstAc == null) {
			asstAc = new AssetAccess();
		}
		return asstAc;
	}
	
	
	public List<Asset> getAsstsFromUser(String UserId) throws Exception{
		
		try {
		Connection coon = ConnectionClass.CreateCon().getConnection();
		PreparedStatement asstStmt = coon.prepareStatement(Constants.getAssetFromUserID);
		asstStmt.setString(1, UserId);
		
		ResultSet assetSet = asstStmt.executeQuery();
		List <Asset> assets = new ArrayList<Asset>();
		while(assetSet.next()) {
			assets.add(new Asset(assetSet.getString("AssetName"), assetSet.getString("AssetDetails"), assetSet.getString("TypeName"),assetSet.getInt("AssetID")));
		}
		return assets;
		
		}
		catch (SQLException e) {
			throw new Exception("Connot get asset");
		}
	}
}
