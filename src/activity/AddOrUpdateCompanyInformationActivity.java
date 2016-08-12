package activity;

import java.util.ArrayList;

import model.Company;
import model.CompanyLab;
import model.Customer;
import model.CustomerLab;

import com.example.warehouse.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddOrUpdateCompanyInformationActivity extends Activity {

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
	
	public static final String COMPANY_OBJECT = "com_example_warehouse_company_object";
	private Company company;
	private ArrayList<Company> mCompanys;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_company_information);
		
		name = (EditText)findViewById(R.id.add_company_information_name);
		city = (EditText)findViewById(R.id.add_company_information_city);
		address = (EditText)findViewById(R.id.add_company_information_address);
		chuangzhen = (EditText)findViewById(R.id.add_company_information_chuangzhen);
		phone = (EditText)findViewById(R.id.add_company_information_phone);
		youbian = (EditText)findViewById(R.id.add_company_information_youbian);
		shop = (EditText)findViewById(R.id.add_company_information_shop);
		gongsi = (EditText)findViewById(R.id.add_company_information_company);
		
		company = (Company) getIntent().getSerializableExtra(COMPANY_OBJECT);
		mCompanys = CompanyLab.get(AddOrUpdateCompanyInformationActivity.this).getCompanys();
		
		name.setText(company.getCompany());
		city.setText(company.getCity());
		address.setText(company.getAddress());
		chuangzhen.setText(company.getChuangzhen());
		phone.setText(company.getPhone());
		youbian.setText(company.getYoubain());
		shop.setText(company.getShop());
		gongsi.setText(company.getGongsi());
		
		save = (Button)findViewById(R.id.add_company_information_save);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if(name.getText().toString().isEmpty()){
					Toast.makeText(AnythingActivity.anythingActivity, "供应商名称不能为空。", 1000).show();
				}else {
					company.setCustomer(name.getText().toString());
					company.setCity(city.getText().toString());
					company.setAddress(address.getText().toString());
					company.setChuangzhen(chuangzhen.getText().toString());
					company.setPhone(phone.getText().toString());
					company.setYoubain(youbian.getText().toString());
					company.setShop(shop.getText().toString());
					company.setGongsi(gongsi.getText().toString());
					
					int i,j=0;
					for(i=0; i<mCompanys.size(); i++){
						if(mCompanys.get(i).getId().equals(company.getId())){
							j=i;
						}
					}
					mCompanys.set(j, company);
					
					finish();
				}
			}
		});
		back = (Button)findViewById(R.id.add_company_information_back);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(name.getText().toString().isEmpty()){
					mCompanys.remove(mCompanys.get(mCompanys.size()-1));
				}
				finish();
			}
		});
	}
	
}
