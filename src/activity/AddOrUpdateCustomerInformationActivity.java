package activity;

import java.util.ArrayList;

import model.Customer;
import model.CustomerLab;
import model.Shop;
import model.ShopAll;
import model.ShopLab;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.warehouse.R;

public class AddOrUpdateCustomerInformationActivity extends Activity {

	private EditText name;
	private EditText city;
	private EditText address;
	private EditText chuangzhen;
	private EditText phone;
	private EditText youbian;
	private EditText shop;
	private EditText gongsi;
	private Button save;
	private Button back;
	
	public static final String CUSTOMER_OBJECT = "com_example_warehouse_customer_object";
	private Customer customer;
	private ArrayList<Customer> mCustomers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_customer_information);
		
		name = (EditText)findViewById(R.id.add_customer_information_name);
		city = (EditText)findViewById(R.id.add_customer_information_city);
		address = (EditText)findViewById(R.id.add_customer_information_address);
		chuangzhen = (EditText)findViewById(R.id.add_customer_information_chuangzhen);
		phone = (EditText)findViewById(R.id.add_customer_information_phone);
		youbian = (EditText)findViewById(R.id.add_customer_information_youbian);
		shop = (EditText)findViewById(R.id.add_customer_information_shop);
		gongsi = (EditText)findViewById(R.id.add_customer_information_company);
		
		customer = (Customer) getIntent().getSerializableExtra(CUSTOMER_OBJECT);
		mCustomers = CustomerLab.get(AddOrUpdateCustomerInformationActivity.this).getCustomers();
		
		name.setText(customer.getCustomer());
		city.setText(customer.getCity());
		address.setText(customer.getAddress());
		chuangzhen.setText(customer.getChuangzhen());
		phone.setText(customer.getPhone());
		youbian.setText(customer.getYoubain());
		shop.setText(customer.getShop());
		gongsi.setText(customer.getGongsi());
		
		save = (Button)findViewById(R.id.add_customer_information_save);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(name.getText().toString().isEmpty()){
					Toast.makeText(AnythingActivity.anythingActivity, "客户名称不能为空。", 1000).show();
				}else {
					customer.setCustomer(name.getText().toString());
					customer.setCity(city.getText().toString());
					customer.setAddress(address.getText().toString());
					customer.setChuangzhen(chuangzhen.getText().toString());
					customer.setPhone(phone.getText().toString());
					customer.setYoubain(youbian.getText().toString());
					customer.setShop(shop.getText().toString());
					customer.setGongsi(gongsi.getText().toString());
					
					int i,j=0;
					for(i=0; i<mCustomers.size(); i++){
						if(mCustomers.get(i).getId().equals(customer.getId())){
							j=i;
						}
					}
					mCustomers.set(j, customer);
					
					finish();
				}
			}
		});
		back = (Button)findViewById(R.id.add_customer_information_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(name.getText().toString().isEmpty()){
					mCustomers.remove(mCustomers.get(mCustomers.size()-1));
				}
				finish();
			}
		});
	}
	
}
