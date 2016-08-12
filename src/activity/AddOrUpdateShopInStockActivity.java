package activity;

import java.util.ArrayList;
import java.util.Calendar;

import model.Shop;
import model.ShopLab;
import model.StockIn;
import model.StockInLab;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.warehouse.R;

public class AddOrUpdateShopInStockActivity extends Activity {

	public static final String STOCKIN_OBJECT = "com_example_warehouse_stockin_object";
	private StockIn stockIn;
	private ArrayList<StockIn> mStockIns;
	
	private EditText shopName;
	private EditText company;
	private EditText amount;
	private EditText danwei;
	private EditText people;
	private EditText phone;
	private EditText priceIn;
	private Button dateIn;
	private Button saveButton;
	private Button backButton;
	
	private String timeString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shop_in_stock);
		
		stockIn = (StockIn) getIntent().getSerializableExtra(STOCKIN_OBJECT);
		mStockIns = StockInLab.get(AddOrUpdateShopInStockActivity.this).getStockIns();
		
		shopName = (EditText)findViewById(R.id.add_shop_in_stock_name);
		company = (EditText)findViewById(R.id.add_shop_in_stock_company);
		amount = (EditText)findViewById(R.id.add_shop_in_stock_number);
		danwei = (EditText)findViewById(R.id.add_shop_in_stock_danwei);
		people = (EditText)findViewById(R.id.add_shop_in_stock_people);
		phone = (EditText)findViewById(R.id.add_shop_in_stock_phone);
		priceIn = (EditText)findViewById(R.id.add_shop_in_stock_price);
		dateIn = (Button)findViewById(R.id.add_shop_in_stock_date);
		saveButton = (Button)findViewById(R.id.add_shop_in_stock_save);
		backButton = (Button)findViewById(R.id.add_shop_in_stock_back);
		
		shopName.setText(stockIn.getShopName());
		company.setText(stockIn.getCompany());
		amount.setText(stockIn.getAmount());
		danwei.setText(stockIn.getDanwei());
		people.setText(stockIn.getPeople());
		phone.setText(stockIn.getPhone());
		priceIn.setText(stockIn.getPirceIn());
		dateIn.setText(stockIn.getDateIn());
		
		dateIn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
		        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
		        final int minute = calendar.get(Calendar.MINUTE);
		        DatePickerDialog dialog_date = new DatePickerDialog(AddOrUpdateShopInStockActivity.this, 
		        		new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						
						 timeString = year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日";
						TimePickerDialog timePickerDialog = new TimePickerDialog(AddOrUpdateShopInStockActivity.this,
								new TimePickerDialog.OnTimeSetListener(){
		                    @Override
		                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
								//Log.d("wangbin", "你设置的时间是：" + hourOfDay + "," + minute);
		                    	timeString += "  " + hourOfDay + ":" + minute;
		                    	if(minute == 0){
		                    		timeString += "0";
		                    	}
		                    	stockIn.setDateIn(timeString);
		            			dateIn.setText(timeString);
		                    }
		                }, hour, minute, true);
		                
		                timePickerDialog.show();
					}
				}, year, month, day);
		//		dialog_date.setTitle("请设置日期：");
				dialog_date.show();
			}
		});
		
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
					Toast.makeText(AddOrUpdateShopInStockActivity.this, "商品名称不能为空。", 1000).show();
				}else {
					stockIn.setShopName(shopName.getText().toString());
					stockIn.setDanwei(danwei.getText().toString());
					stockIn.setAmount(amount.getText().toString());
					stockIn.setCompany(company.getText().toString());
					stockIn.setPeople(people.getText().toString());
					stockIn.setPhone(phone.getText().toString());
					stockIn.setPirceIn(priceIn.getText().toString());
					int i=0,j=0;
					for(i=0; i<mStockIns.size(); i++){
						if(mStockIns.get(i).getId().equals(stockIn.getId())){
							j=i;
						}
					}
					mStockIns.set(j, stockIn);
					finish();
				}
			}
		});
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
					StockInLab.get(AddOrUpdateShopInStockActivity.this).deleteStockIn(stockIn);
				}
				finish();
			}
		});
		
	}
	
}
