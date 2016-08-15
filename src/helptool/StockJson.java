package helptool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import model.StockIn;
import model.StockOut;

import org.json.JSONArray;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class StockJson {

	private Context context;
	private String filename;
	
	public StockJson(Context context, String filename){
		this.context = context;
		this.filename = filename;
	}
	
	//----------------------------入库---------------------------------
		public void saveStockIns(ArrayList<StockIn> stockIns) throws Exception{
	//		Log.d("wangbin", "进入调用json方法"+stockIns.size()+".\\");
			JSONArray array = new JSONArray();
			Writer writer = null;
			try {
				OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
				writer = new OutputStreamWriter(out);
				for(StockIn stockIn:stockIns){
					array.put(stockIn.toJson());
				}
				writer.write(array.toString());
	//			Log.d("wangbin", "进入调用方法"+array.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(writer != null){
					writer.close();
				}
			}
		}
		
		public ArrayList<StockIn> loadStockIns() throws Exception{
			ArrayList<StockIn> stockIns = new ArrayList<StockIn>();
			BufferedReader reader = null;
			try {
				InputStream in = context.openFileInput(filename);
				reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder builder = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null){
					builder.append(line);
				}
	//			Log.d("wangbin", "进入调用方法"+line+"..");
				JSONArray array = (JSONArray) new JSONTokener(builder.toString()).nextValue();
	//			Log.d("wangbin", "进入调用方法"+array.length()+".****.");
				for(int i=0; i<array.length(); i++){
					stockIns.add(new StockIn(array.getJSONObject(i)));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(reader != null){
					reader.close();
				}
			}
			return stockIns;
		}
		
		//-----------------------------出库--------------------------------
		public void saveStockOuts(ArrayList<StockOut> stockOuts)throws Exception{
			JSONArray array = new JSONArray();
			for(StockOut stockOut : stockOuts){
				array.put(stockOut.toJson());
			}
			Writer writer = null;
			try {
				OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
				writer = new OutputStreamWriter(out);
				writer.write(array.toString());
				Log.d("wangbin", "进入调用方法"+array.toString()+"\n存储成功");
			} catch (Exception e) {
				e.printStackTrace();
				Log.d("wangbin", "存储出库，抛出异常");
			}finally{
				if(writer!= null){
					writer.close();
				}
			}
			Log.d("wangbin", "现在开始解析");
			ArrayList<StockOut>stockOuts1 = new ArrayList<StockOut>();
			BufferedReader reader1 = null;
			try {
				InputStream in1 = context.openFileInput(filename);
				reader1 = new BufferedReader(new InputStreamReader(in1));
				StringBuilder builder1 = new StringBuilder();
				String line1;
				while((line1 = reader1.readLine()) != null){
					builder1.append(line1);
				}
				Log.d("wangbin", "现在开始解析"+builder1.toString()+"!!!!");
				JSONArray array1 = (JSONArray) new JSONTokener(builder1.toString()).nextValue();
				for (int i = 0; i < array1.length(); i++) {
					Log.d("wangbin", "第几次进入");
					stockOuts1.add(new StockOut(array.getJSONObject(i)));
				}
				Log.d("wangbin", "现在开始解析"+stockOuts1.size()+"@@");
			} catch (Exception e) {
				Log.d("wangbin", "读取抛出异常");
			}finally{
				if(reader1 != null){
					reader1.close();
				}
			}
			
			
			
		}
		
		public ArrayList<StockOut> loadStockOuts() throws Exception{
			ArrayList<StockOut>stockOuts = new ArrayList<StockOut>();
			BufferedReader reader = null;
			try {
				InputStream in = context.openFileInput(filename);
				reader = new BufferedReader(new InputStreamReader(in));
				StringBuilder builder = new StringBuilder();
				String line;
				while((line = reader.readLine()) != null){
					builder.append(line);
				}
				JSONArray array = (JSONArray) new JSONTokener(builder.toString()).nextValue();
				for (int i = 0; i < array.length(); i++) {
					stockOuts.add(new StockOut(array.getJSONObject(i)));
				}
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				if(reader != null){
					reader.close();
				}
			}
			return stockOuts;
		}
	
}
