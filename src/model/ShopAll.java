package model;

import java.util.UUID;

public class ShopAll {

	private String mShopName;
	private String mDanwei;
	private String mPeopleOut;
	private String mPeopleIn;
	private String mPhoneOut;
	private String mPhoneIn;
	private String mCompany;
	private String mAmountOut;
	private String mAmountIn;
	private String mPriceOut;
	private String mPriceIn;
	private String mDateOut;
	private String mDateIn;
	private String mCustomer;
	
	private UUID mId;
	
	public ShopAll(){
		mId = UUID.randomUUID();
	}
	
	public UUID getId() {
		return mId;
	}

	public String getShopName() {
		return mShopName;
	}

	public void setShopName(String mShopName) {
		this.mShopName = mShopName;
	}

	public String getDanwei() {
		return mDanwei;
	}

	public void setDanwei(String mDanwei) {
		this.mDanwei = mDanwei;
	}

	public String getPeopleOut() {
		return mPeopleOut;
	}

	public void setPeopleOut(String mPeopleOut) {
		this.mPeopleOut = mPeopleOut;
	}

	public String getPeopleIn() {
		return mPeopleIn;
	}

	public void setPeopleIn(String mPeopleIn) {
		this.mPeopleIn = mPeopleIn;
	}

	public String getPhoneOut() {
		return mPhoneOut;
	}

	public void setmPhoneOut(String mPhoneOut) {
		this.mPhoneOut = mPhoneOut;
	}

	public String getPhoneIn() {
		return mPhoneIn;
	}

	public void setPhoneIn(String mPhoneIn) {
		this.mPhoneIn = mPhoneIn;
	}

	public String getCompany() {
		return mCompany;
	}

	public void setCompany(String mCompany) {
		this.mCompany = mCompany;
	}

	public String getAmountOut() {
		return mAmountOut;
	}

	public void setAmountOut(String mAmountOut) {
		this.mAmountOut = mAmountOut;
	}

	public String getAmountIn() {
		return mAmountIn;
	}

	public void setAmountIn(String mAmountIn) {
		this.mAmountIn = mAmountIn;
	}

	public String getPriceOut() {
		return mPriceOut;
	}

	public void setPriceOut(String mPriceOut) {
		this.mPriceOut = mPriceOut;
	}

	public String getPriceIn() {
		return mPriceIn;
	}

	public void setPriceIn(String mPriceIn) {
		this.mPriceIn = mPriceIn;
	}

	public String getDateOut() {
		return mDateOut;
	}

	public void setDateOut(String mDateOut) {
		this.mDateOut = mDateOut;
	}

	public String getDateIn() {
		return mDateIn;
	}

	public void setDateIn(String mDateIn) {
		this.mDateIn = mDateIn;
	}

	public String getCustomer() {
		return mCustomer;
	}

	public void setCustomer(String mCustomer) {
		this.mCustomer = mCustomer;
	}

}
