package model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class StockOut implements Serializable{

	private String shopName="";
	private String customer="";
	private String amount="";
	private String danwei="";
	private String people="";
	private String phone="";
	private String pirceOut="";
	private String dateOut="";
	private UUID mId;
	
	public StockOut(){
		mId = UUID.randomUUID();
	}
	
	public StockOut(JSONObject json) throws JSONException{
	//	Log.d("wangbin", "第几次进入。。。");
		mId = UUID.fromString(json.getString("id"));
	//	Log.d("wangbin", "第几次进入。。。1");
		shopName = json.getString("shopName");
	//	Log.d("wangbin", "第几次进入。。。2"+shopName);
		customer = json.getString("customer");
	//	Log.d("wangbin", "第几次进入。。。2"+customer);
		amount = json.getString("amount");
		danwei = json.getString("danwei");
		phone = json.getString("phone");
		people = json.getString("people");
		pirceOut = json.getString("pirceOut");
		dateOut = json.getString("dateOut");
	//	Log.d("wangbin", shopName+","+customer+",");
	}
	
	public JSONObject toJson() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", mId.toString());
		json.put("shopName", shopName);
		json.put("customer", customer);
		json.put("amount", amount);
		json.put("danwei", danwei);
		json.put("phone", phone);
		json.put("people", people);
		json.put("pirceOut", pirceOut);
		json.put("dateOut", dateOut);
	//	Log.d("wangbin", "tojson");
		return json;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getCompany() {
		return customer;
	}

	public void setCompany(String company) {
		this.customer = company;
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

	public String getPirceOut() {
		return pirceOut;
	}

	public void setPirceOut(String pirceIn) {
		this.pirceOut = pirceIn;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setDateOut(String dateIn) {
		this.dateOut = dateIn;
	}

	public UUID getId() {
		return mId;
	}
	
}
