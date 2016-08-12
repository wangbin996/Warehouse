package model;

import helptool.SCCJson;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class StockOutLab {

	private ArrayList<StockOut> mStockOuts;
	private static StockOutLab sStockOut;
	
	private StockOutLab(Context context){
		if(mStockOuts == null){
			mStockOuts = new ArrayList<StockOut>();
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
	
}
