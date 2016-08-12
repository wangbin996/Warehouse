package model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

public class Shop implements Serializable{

	private String shopName;
	private String danwei;
	private UUID mId;
	
	public Shop(){
		mId = UUID.randomUUID();
	}
	
	public Shop(JSONObject json) throws JSONException{
		mId = UUID.fromString(json.getString("id"));
		shopName = json.getString("shopName");
		danwei = json.getString("danwei");
	}

	public UUID getId(){
		return mId;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public JSONObject toJson() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", mId.toString());
		json.put("shopName", shopName);
		json.put("danwei", danwei);
		return json;
	}

	
}
