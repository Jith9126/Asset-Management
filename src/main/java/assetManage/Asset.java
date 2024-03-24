package assetManage;

import org.json.*;

public class Asset {
	private int assetID;
	private String assetName;
	private String assetDetails;
	private Type type;
	public Asset(String assetName, String assetDetails, String type, int assetId) {
		this.assetName = assetName;
		this.assetID = assetId;
		this.assetDetails = assetDetails;
		this.type = new Type(type);
	}
	
	public String toString() {
		JSONObject asst = new JSONObject();
		try {
			asst.put("assetName", assetName);
			asst.put("asseDetails", assetDetails);
			asst.put("type", type.getType());
			asst.put("assetId", assetID);
			return asst.toString();
		}
		catch(JSONException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int getAssetID() {
		return assetID;
	}

	public String getAssetName() {
		return assetName;
	}

	public String getAssetDetails() {
		return assetDetails;
	}

	public Type getType() {
		return type;
	}
	
	
	
	
	
	
	
}
