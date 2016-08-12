package model;

import java.io.Serializable;
import java.util.UUID;

public class StockOut implements Serializable{

	private String shopName;
	private String customer;
	private String amount;
	private String danwei;
	private String people;
	private String phone;
	private String pirceOut;
	private String dateOut;
	private UUID mId;
	
	public StockOut(){
		mId = UUID.randomUUID();
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
