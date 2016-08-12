package activity;

import java.util.ArrayList;
import java.util.Calendar;

import model.StockIn;
import model.StockInLab;
import model.StockOut;
import model.StockOutLab;

import com.example.warehouse.R;

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

public class AddOrUpdateShopOutStockActivity extends Activity {

	public static final String STOCKOUT_OBJECT = "com_example_warehouse_stockout_object";
	private StockOut stockOut;
	private ArrayList<StockOut> mStockOuts;
	
	private EditText shopName;
	private EditText customer;
	private EditText amount;
	private EditText danwei;
	private EditText people;
	private EditText phone;
	private EditText priceOut;
	private Button dateOut;
	private Button saveButton;
	private Button backButton;
	private String timeString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_shop_out_stock);
		
		stockOut = (StockOut) getIntent().getSerializableExtra(STOCKOUT_OBJECT);
		mStockOuts = StockOutLab.get(AddOrUpdateShopOutStockActivity.this).getStockOuts();
		
		shopName = (EditText)findViewById(R.id.add_shop_out_stock_name);
		customer = (EditText)findViewById(R.id.add_shop_out_stock_company);
		amount = (EditText)findViewById(R.id.add_shop_out_stock_number);
		danwei = (EditText)findViewById(R.id.add_shop_out_stock_danwei);
		people = (EditText)findViewById(R.id.add_shop_out_stock_people);
		phone = (EditText)findViewById(R.id.add_shop_out_stock_phone);
		priceOut = (EditText)findViewById(R.id.add_shop_out_stock_price);
		dateOut = (Button)findViewById(R.id.add_shop_out_stock_date);
		saveButton = (Button)findViewById(R.id.add_shop_out_stock_save);
		backButton = (Button)findViewById(R.id.add_shop_out_stock_back);
		
		shopName.setText(stockOut.getShopName());
		customer.setText(stockOut.getCompany());
		amount.setText(stockOut.getAmount());
		danwei.setText(stockOut.getDanwei());
		people.setText(stockOut.getPeople());
		phone.setText(stockOut.getPhone());
		priceOut.setText(stockOut.getPirceOut());
		dateOut.setText(stockOut.getDateOut());
		
		dateOut.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = calendar.get(Calendar.DAY_OF_MONTH);
		        final int hour = calendar.get(Calendar.HOUR_OF_DAY);
		        final int minute = calendar.get(Calendar.MINUTE);
		        DatePickerDialog dialog_date = new DatePickerDialog(AddOrUpdateShopOutStockActivity.this, 
		        		new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						timeString = year + "年" + (monthOfYear+1) + "月" + dayOfMonth + "日";
						TimePickerDialog timePickerDialog = new TimePickerDialog(AddOrUpdateShopOutStockActivity.this,
								new TimePickerDialog.OnTimeSetListener(){
		                    @Override
		                    public void onTimeSet(TimePicker view, int hourOfDay, int minute){
		                    	timeString += "  " + hourOfDay + ":" + minute;
		                    	if(minute == 0){
		                    		timeString += "0";
		                    	}
		                    	stockOut.setDateOut(timeString);
		            			dateOut.setText(timeString);
		                    }
		                }, hour, minute, true);
		                
		                timePickerDialog.show();
					}
				}, year, month, day);
				dialog_date.show();
			}
		});
		
		saveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
					Toast.makeText(AddOrUpdateShopOutStockActivity.this, "商品名称不能为空。", 1000).show();
				}else {
					stockOut.setShopName(shopName.getText().toString());
					stockOut.setDanwei(danwei.getText().toString());
					stockOut.setAmount(amount.getText().toString());
					stockOut.setCompany(customer.getText().toString());
					stockOut.setPeople(people.getText().toString());
					stockOut.setPhone(phone.getText().toString());
					stockOut.setPirceOut(priceOut.getText().toString());
					int i=0,j=0;
					for(i=0; i<mStockOuts.size(); i++){
						if(mStockOuts.get(i).getId().equals(stockOut.getId())){
							j=i;
						}
					}
					mStockOuts.set(j, stockOut);
					finish();
				}
			}
		});
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(shopName.getText().toString().isEmpty()){
					StockOutLab.get(AddOrUpdateShopOutStockActivity.this).deleteStockOut(stockOut);
				}
				finish();
			}
		});
		
	}
	
}
