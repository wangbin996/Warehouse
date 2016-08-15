package model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class StockIn implements Serializable{

	private String shopName="";
	private String company="";
	private String amount="";
	private String danwei="";
	private String people="";
	private String phone="";
	private String pirceIn="";
	private String dateIn="";
	private UUID mId;
	
	public StockIn(){
		mId = UUID.randomUUID();
	}

	public StockIn(JSONObject json) throws JSONException{
		mId = UUID.fromString(json.getString("id"));
		shopName = json.getString("shopName");
		company = json.getString("company");
		amount = json.getString("amount");
		danwei = json.getString("danwei");
		people = json.getString("people");
		phone = json.getString("phone");
		pirceIn = json.getString("pirceIn");
		dateIn = json.getString("dateIn");
//		Log.d("wangbin", shopName+","+amount+"...");
	}
	
	public JSONObject toJson() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", mId.toString());
		json.put("shopName", shopName);
		json.put("company", company);
		json.put("amount", amount);
		json.put("danwei", danwei);
		json.put("people", people);
		json.put("phone", phone);
		json.put("pirceIn", pirceIn);
		json.put("dateIn", dateIn);
		return json;
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDanwei() {
		return danwei;
	}

	public void setDanwei(String danwei) {
		this.danwei = danwei;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPirceIn() {
		return pirceIn;
	}

	public void setPirceIn(String pirceIn) {
		this.pirceIn = pirceIn;
	}

	public String getDateIn() {
		return dateIn;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	public UUID getId() {
		return mId;
	}
	
}
