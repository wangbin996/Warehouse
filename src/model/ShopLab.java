package model;

import helptool.SCCJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class ShopLab {

	private ArrayList<Shop> mShops;
	private static ShopLab sShop;
	private SCCJson sccJson;
	private String FILENAME = "shops.json";
	
	private ShopLab(Context context){
		
		sccJson = new SCCJson(context, FILENAME);
		try {
			mShops = sccJson.loadShops();
	//		Log.d("wangbin", "22222222");
		} catch (Exception e) {
			if(mShops == null){
				mShops = new ArrayList<Shop>();
			}
		}
	}

	public static ShopLab get(Context context){
		if(sShop == null){
			sShop = new ShopLab(context);
		}
		return sShop;
	}
	
	public ArrayList<Shop> getShops(){
		return mShops;
	}
	
	public Shop getShop(UUID id){
		for(Shop shop:mShops){
			if(shop.getId().equals(id)){
				return shop;
			}
		}
		return null;
	}
	
	public void addShop(Shop shop){
		mShops.add(shop);
	}
	
	public void deleteShop(Shop shop){
		int i=0,j=0;
		for(i=0; i<mShops.size(); i++){
			if(mShops.get(i).getId().equals(shop.getId())){
				j=i;
			}
		}
		mShops.remove(j);
	}
	
	public void saveShops(ArrayList<Shop> shops){
		try {
			sccJson.saveShops(shops);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Shop> loadShops(){
		try {
			return sccJson.loadShops();
		} catch (Exception e) {
			e.printStackTrace();
			return mShops;
		}
	}
	
}
