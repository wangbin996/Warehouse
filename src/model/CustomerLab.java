package model;

import helptool.SCCJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class CustomerLab {

	private ArrayList<Customer> mCustomers;
	private static CustomerLab sCustomer;
	private SCCJson sccJson;
	private String FILENAME = "customers.json";
	
	private CustomerLab(Context context){
		sccJson = new SCCJson(context, FILENAME);
		try {
			mCustomers = sccJson.loadCustomers();
		} catch (Exception e) {
			if(mCustomers == null){
				mCustomers = new ArrayList<Customer>();
			}
		}
	}

	public static CustomerLab get(Context context){
		if(sCustomer == null){
			sCustomer = new CustomerLab(context);
		}
		return sCustomer;
	}
	
	public ArrayList<Customer> getCustomers(){
		return mCustomers;
	}
	
	public Customer getCustomer(UUID id){
		for(Customer customer:mCustomers){
			if(customer.getId().equals(id)){
				return customer;
			}
		}
		return null;
	}
	
	public void addCustomer(Customer customer){
		mCustomers.add(customer);
	}

	public void deleteCustomer(Customer customer){
		int i=0,j=0;
		for(i=0; i<mCustomers.size(); i++){
			if(mCustomers.get(i).getId().equals(customer.getId())){
		//		Log.d("wangbin", "一样就显示"+i);
				j=i;
			}
		}
		mCustomers.remove(j);
	}
	
	public void saveCustomers(ArrayList<Customer> customers){
		try {
			sccJson.saveCustomers(customers);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Customer> loadCustomers(){
		try {
			return sccJson.loadCustomers();
		} catch (Exception e) {
			e.printStackTrace();
			return mCustomers;
		}
	}
	
}
