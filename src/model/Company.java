package model;

import java.io.Serializable;
import java.util.UUID;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Company implements Serializable{

	private String company="";
	private String city="";
	private String address="";
	private String chuangzhen="";
	private String phone="";
	private String youbain="";
	private String shop="";
	private String gongsi="";
	private UUID mId;
	
	public Company(){
		mId = UUID.randomUUID();
	}
	
	public Company(JSONObject json) throws JSONException{
		mId = UUID.fromString(json.getString("id"));
		company = json.getString("company");
		city = json.getString("city");
		address = json.getString("address");
		chuangzhen = json.getString("chuangzhen");
		phone = json.getString("phone");
		youbain = json.getString("youbain");
		shop = json.getString("shop");
		gongsi = json.getString("gongsi");
	//	Log.d("wangbin", company+","+shop+","+gongsi);
	}
	
	public JSONObject toJson() throws JSONException{
		JSONObject json = new JSONObject();
		json.put("id", mId.toString());
		json.put("company", company);
		json.put("city", city);
		json.put("address", address);
		json.put("chuangzhen", chuangzhen);
		json.put("phone", phone);
		json.put("youbain", youbain);
		json.put("shop", shop);
		json.put("gongsi", gongsi);
		return json;
	}
	
	public UUID getId(){
		return mId;
	}

	public String getCompany() {
		return company;
	}

	public void setCustomer(String company) {
		this.company = company;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getChuangzhen() {
		return chuangzhen;
	}

	public void setChuangzhen(String chuangzhen) {
		this.chuangzhen = chuangzhen;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getYoubain() {
		return youbain;
	}

	public void setYoubain(String youbain) {
		this.youbain = youbain;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getGongsi() {
		return gongsi;
	}

	public void setGongsi(String gongsi) {
		this.gongsi = gongsi;
	}
	
}
