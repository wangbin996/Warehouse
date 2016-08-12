package model;

import java.io.Serializable;
import java.util.UUID;

public class StockIn implements Serializable{

	private String shopName;
	private String company;
	private String amount;
	private String danwei;
	private String people;
	private String phone;
	private String pirceIn;
	private String dateIn;
	private UUID mId;
	
	public StockIn(){
		mId = UUID.randomUUID();
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
