package helptool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.WriteAbortedException;
import java.io.Writer;
import java.util.ArrayList;

import model.Company;
import model.Customer;
import model.Shop;
import model.StockIn;
import model.StockOut;

import org.json.JSONArray;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;

public class SCCJson {

	private Context context;
	private String filename;
	
	public SCCJson(Context context, String filename){
		this.context = context;
		this.filename = filename;
	}
	
	//---------------------------shop存储于加载---------------------------
	public void saveShops(ArrayList<Shop> shops)throws Exception{
		
		JSONArray array = new JSONArray();
		for(Shop shop:shops){
			array.put(shop.toJson());
		}
		Writer writer = null;
		try {
			OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
		
		ArrayList<Shop> shops1 = new ArrayList<Shop>();
		BufferedReader reader1 = null;
		try {
			InputStream in1 = context.openFileInput(filename);
			reader1 = new BufferedReader(new InputStreamReader(in1));
			StringBuilder builder1 = new StringBuilder();
			String line1;
			while((line1=reader1.readLine())!=null){
				builder1.append(line1);
			}
			JSONArray array1 = (JSONArray)new JSONTokener(builder1.toString()).nextValue();
			Log.d("wangbing", "!!!!!"+builder1.toString());
			for(int i=0; i<array1.length(); i++){
				shops1.add(new Shop(array1.getJSONObject(i)));
			}
		} catch (Exception e) {
			Log.d("wangbing", "读取shop抛出异常");
		}finally{
			if(reader1!=null){
				reader1.close();
			}
		}
		
		
		
		
		
		
	}
	
	public ArrayList<Shop> loadShops()throws Exception{
		
		ArrayList<Shop> shops = new ArrayList<Shop>();
		BufferedReader reader = null;
		try {
			InputStream in = context.openFileInput(filename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder builder = new StringBuilder();
			String line;
			while((line=reader.readLine())!=null){
				builder.append(line);
			}
			JSONArray array = (JSONArray)new JSONTokener(builder.toString()).nextValue();
			
			for(int i=0; i<array.length(); i++){
				shops.add(new Shop(array.getJSONObject(i)));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		return shops;
	}
	
	//----------------------------Customer存储与加载------------------------------
	public void saveCustomers(ArrayList<Customer> customers)throws Exception{
		
		JSONArray array = new JSONArray();
		for(Customer customer:customers){
			array.put(customer.toJson());
		}
		Writer writer = null;
		try {
			OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
	}
	public ArrayList<Customer> loadCustomers()throws Exception{
		
		ArrayList<Customer> customers = new ArrayList<Customer>();
		BufferedReader reader = null;
		try {
			InputStream in = context.openFileInput(filename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder builder = new StringBuilder();
			String line;
			while((line=reader.readLine())!=null){
				builder.append(line);
			//	Log.d("wangbin", "!!555!"+line);
			}
			JSONArray array = (JSONArray)new JSONTokener(builder.toString()).nextValue();
			for(int i=0; i<array.length(); i++){
				customers.add(new Customer(array.getJSONObject(i)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		return customers;
	}
	
	//----------------------------Company存储与加载-------------------------------------
public void saveCompanys(ArrayList<Company> companys)throws Exception{
		
		JSONArray array = new JSONArray();
		for(Company company:companys){
			array.put(company.toJson());
		}
		Writer writer = null;
		try {
			OutputStream out = context.openFileOutput(filename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(writer != null){
				writer.close();
			}
		}
		
	}
	public ArrayList<Company> loadCompanys()throws Exception{
		
		ArrayList<Company> companys = new ArrayList<Company>();
		BufferedReader reader = null;
		try {
			InputStream in = context.openFileInput(filename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder builder = new StringBuilder();
			String line;
			while((line=reader.readLine())!=null){
				builder.append(line);
			}
			JSONArray array = (JSONArray)new JSONTokener(builder.toString()).nextValue();
			for(int i=0; i<array.length(); i++){
				companys.add(new Company(array.getJSONObject(i)));
			}
			
		} catch (Exception e) {
		}finally{
			if(reader!=null){
				reader.close();
			}
		}
		return companys;
		
	}
	
	
	
}
