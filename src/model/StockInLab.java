package model;

import helptool.SCCJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class StockInLab {

	private ArrayList<StockIn> mStockIns;
	private static StockInLab sStockIn;
	
	private StockInLab(Context context){
		if(mStockIns == null){
			mStockIns = new ArrayList<StockIn>();
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
	
}
