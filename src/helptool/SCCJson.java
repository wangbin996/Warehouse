package helptool;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import model.Shop;

import org.json.JSONArray;
import org.json.JSONTokener;

import android.content.Context;

public class SCCJson {

	private Context context;
	private String filename;
	
	public SCCJson(Context context, String filename){
		this.context = context;
		this.filename = filename;
	}
	
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
			// TODO: handle exception
		}finally{
			if(writer != null){
				writer.close();
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
	
}
