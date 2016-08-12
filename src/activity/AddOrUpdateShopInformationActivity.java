package activity;

import java.util.ArrayList;
import java.util.UUID;

import model.Shop;
import model.ShopLab;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warehouse.R;

public class AddOrUpdateShopInformationActivity extends Activity {

	private EditText shopName;
	private EditText danwei;
	private Button saveButton;
	private Button backButton;
	
	public static final String SHOP_OBJECT = "com_example_warehouse_shop_object";
	private Shop shop;
	private ArrayList<Shop> mShops;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shop_information);
		
		shop = (Shop) getIntent().getSerializableExtra(SHOP_OBJECT);
		mShops = ShopLab.get(AddOrUpdateShopInformationActivity.this).getShops();
		
		shopName = (EditText)findViewById(R.id.add_shop_information_name);
		danwei = (EditText)findViewById(R.id.add_shop_information_danwei);
		
		shopName.setText(shop.getShopName());
		danwei.setText(shop.getDanwei());
		
		saveButton = (Button)findViewById(R.id.add_shop_information_save);
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
					Toast.makeText(AddOrUpdateShopInformationActivity.this, "商品名称不能为空。", 1000).show();
				}else {
					shop.setShopName(shopName.getText().toString());
					shop.setDanwei(danwei.getText().toString());
		//			Log.d("wangbin", shopName.getText().toString()+"!"+mShops.size()+"!"+shop.getShopName());
					int i=0,j=0;
					for(i=0; i<mShops.size(); i++){
						if(mShops.get(i).getId().equals(shop.getId())){
					//		Log.d("wangbin", "一样就显示"+i);
							j=i;
						}
					}
					mShops.set(j, shop);
			//		Log.d("wangbin", mShops.get(0).getShopName()+"!"+mShops.get(0).getDanwei()+"&"+mShops.size());
			//		Log.d("wangbin", mShops.get(1).getShopName()+"!"+mShops.get(1).getDanwei());
					finish();
				}
			}
		});
		backButton = (Button)findViewById(R.id.add_shop_information_back);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
				//	mShops.remove(mShops.get(mShops.size()-1));这样逻辑不严谨
			//		Log.d("wangbin", "空的，要删除");
			//		Log.d("wangbin", mShops.size()+"!");
					ShopLab.get(AddOrUpdateShopInformationActivity.this).deleteShop(shop);
			//		Log.d("wangbin", mShops.size()+"!");
				}
				finish();
			}
		});
	}
	
}
