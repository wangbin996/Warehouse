package model;

import helptool.SCCJson;
import helptool.StockJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class StockOutLab {

	private ArrayList<StockOut> mStockOuts;
	private static StockOutLab sStockOut;
	private StockJson stockJson;
	private String FILENAME = "stockouts.json";
	
	private StockOutLab(Context context){
		stockJson = new StockJson(context, FILENAME);
		try {
			mStockOuts = stockJson.loadStockOuts();
		} catch (Exception e) {
			if(mStockOuts == null){
				mStockOuts = new ArrayList<StockOut>();
			}
		}
	}

	public static StockOutLab get(Context context){
		if(sStockOut == null){
			sStockOut = new StockOutLab(context);
		}
		return sStockOut;
	}
	
	public ArrayList<StockOut> getStockOuts(){
		return mStockOuts;
	}
	
	public StockOut getStockIn(UUID id){
		for(StockOut stockOut:mStockOuts){
			if(stockOut.getId().equals(id)){
				return stockOut;
			}
		}
		return null;
	}
	
	public void addStockOut(StockOut stockOut){
		mStockOuts.add(stockOut);
	}
	
	public void deleteStockOut(StockOut stockOut){
		int i=0,j=0;
		for(i=0; i<mStockOuts.size(); i++){
			if(mStockOuts.get(i).getId().equals(stockOut.getId())){
		//		Log.d("wangbin", "一样就显示"+i);
				j=i;
			}
		}
		mStockOuts.remove(j);
	}
	
	
	public void saveStockOuts(ArrayList<StockOut> stockOuts){
		try {
			Log.d("wangbin", "进入调用存储的方法"+stockOuts.size());
			stockJson.saveStockOuts(stockOuts);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<StockOut> loadStockOuts(){
		try {
			return stockJson.loadStockOuts();
		} catch (Exception e) {
			e.printStackTrace();
			return mStockOuts;
		}
	}
	
}
