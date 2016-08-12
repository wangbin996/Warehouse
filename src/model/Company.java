package model;

import java.io.Serializable;
import java.util.UUID;

public class Company implements Serializable{

	private String company;
	private String city;
	private String address;
	private String chuangzhen;
	private String phone;
	private String youbain;
	private String shop;
	private String gongsi;
	private UUID mId;
	
	public Company(){
		mId = UUID.randomUUID();
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
