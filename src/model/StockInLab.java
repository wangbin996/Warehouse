package model;

import helptool.SCCJson;
import helptool.StockJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class StockInLab {

	private ArrayList<StockIn> mStockIns;
	private static StockInLab sStockIn;
	private StockJson stockJson;
	private String FILENAME = "stockins.json";
	
	private StockInLab(Context context){
		stockJson = new StockJson(context, FILENAME);
		try {

	//		Log.d("wangbin", "进入jiazai"+"555%%%%");
			mStockIns = stockJson.loadStockIns();
		} catch (Exception e) {
			if(mStockIns == null){
				mStockIns = new ArrayList<StockIn>();
			}
		}
	}

	public static StockInLab get(Context context){
		if(sStockIn == null){
			sStockIn = new StockInLab(context);
		}
		return sStockIn;
	}
	
	public ArrayList<StockIn> getStockIns(){
		return mStockIns;
	}
	
	public StockIn getStockIn(UUID id){
		for(StockIn stockIn:mStockIns){
			if(stockIn.getId().equals(id)){
				return stockIn;
			}
		}
		return null;
	}
	
	public void addStockIn(StockIn stockIn){
		mStockIns.add(stockIn);
	}
	
	public void deleteStockIn(StockIn stockIn){
		int i=0,j=0;
		for(i=0; i<mStockIns.size(); i++){
			if(mStockIns.get(i).getId().equals(stockIn.getId())){
		//		Log.d("wangbin", "一样就显示"+i);
				j=i;
			}
		}
		mStockIns.remove(j);
	}
	
	public void saveStockIns(ArrayList<StockIn> stockIns){
		try {
		//	Log.d("wangbin", "进入调用方法"+stockIns.size()+"..");
			stockJson.saveStockIns(stockIns);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StockIn> loadStockIns(){
		try {
			return stockJson.loadStockIns();
		} catch (Exception e) {
			e.printStackTrace();
			return mStockIns;
		}
	}
	
}
