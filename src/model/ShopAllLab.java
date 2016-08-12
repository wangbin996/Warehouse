package model;

import java.util.ArrayList;
import java.util.UUID;

public class ShopAllLab {

	private ArrayList<ShopAll> mShops;
	private static ShopAllLab sShopLab;
	
	private ShopAllLab(){
		mShops = new ArrayList<ShopAll>();
	}

	public static ShopAllLab get(){
		if(sShopLab == null){
			sShopLab = new ShopAllLab();
		}
		return sShopLab;
	}
	
	public ArrayList<ShopAll> getShops(){
		return mShops;
	}
	
	public ShopAll getShop(UUID id){
		for(ShopAll shop:mShops){
			if(shop.getId().equals(id)){
				return shop;
			}
		}
		return null;
	}
	
	public void addShop(ShopAll shop){
		mShops.add(shop);
	}
	
}
