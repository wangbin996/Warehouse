package activity;

import java.util.ArrayList;

import model.Company;
import model.CompanyLab;
import model.Customer;
import model.CustomerLab;
import model.Shop;
import model.ShopLab;
import model.StockIn;
import model.StockInLab;
import model.StockOut;
import model.StockOutLab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.warehouse.R;

public class QueryInformationActivity extends Activity {

	private ListView listView;
	private ArrayList<Object> queryLists = new ArrayList<Object>();
	private MyQueryListAdapter adapter;
	private int number_shop;
	private int number_customer;
	private int number_company;
	private int number_stockin;
	private int number_stockout;
	ArrayList<Boolean> selecteds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_activity);
		
		listView = (ListView)findViewById(R.id.listView_querry);
		
		queryLists.addAll(ShopLab.get(QueryInformationActivity.this).getShops());
		queryLists.addAll(CustomerLab.get(QueryInformationActivity.this).getCustomers());
		queryLists.addAll(CompanyLab.get(QueryInformationActivity.this).getCompanys());
		queryLists.addAll(StockInLab.get(QueryInformationActivity.this).getStockIns());
		queryLists.addAll(StockOutLab.get(QueryInformationActivity.this).getStockOuts());

		number_shop = ShopLab.get(QueryInformationActivity.this).getShops().size();
		number_customer = CustomerLab.get(QueryInformationActivity.this).getCustomers().size();
		number_company = CompanyLab.get(QueryInformationActivity.this).getCompanys().size();
		number_stockin = StockInLab.get(QueryInformationActivity.this).getStockIns().size();
		number_stockout = StockOutLab.get(QueryInformationActivity.this).getStockOuts().size();
		
		adapter = new MyQueryListAdapter(QueryInformationActivity.this, R.layout.query_activity_list,queryLists);
		
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Log.d("wangbin", position+"!!!"+number_shop+","+number_customer+","+number_company+","+number_stockin+","+number_stockout);
				if(position<=number_shop-1){
					Shop shop = ShopLab.get(QueryInformationActivity.this).getShops().get(position);
					Intent intent = new Intent(QueryInformationActivity.this, AddOrUpdateShopInformationActivity.class);
					intent.putExtra(AddOrUpdateShopInformationActivity.SHOP_OBJECT, shop);
					startActivity(intent);
				}else if(position<=number_shop-1+number_customer){
					Customer customer = CustomerLab.get(QueryInformationActivity.this).getCustomers()
							.get(position-number_shop);
					Intent intent = new Intent(QueryInformationActivity.this, AddOrUpdateCustomerInformationActivity.class);
					intent.putExtra(AddOrUpdateCustomerInformationActivity.CUSTOMER_OBJECT, customer);
					startActivity(intent);
				}else if(position<=number_shop-1+number_customer+number_company){
					Company company = CompanyLab.get(QueryInformationActivity.this).getCompanys()
							.get(position-number_shop-number_customer);
					Intent intent = new Intent(QueryInformationActivity.this, AddOrUpdateCompanyInformationActivity.class);
					intent.putExtra(AddOrUpdateCompanyInformationActivity.COMPANY_OBJECT, company);
					startActivity(intent);
				}else if(position<=number_shop-1+number_customer+number_company+number_stockin){
					StockIn stockIn = StockInLab.get(QueryInformationActivity.this).getStockIns()
							.get(position-number_shop-number_customer-number_company);
					Intent intent = new Intent(QueryInformationActivity.this, AddOrUpdateShopInStockActivity.class);
					intent.putExtra(AddOrUpdateShopInStockActivity.STOCKIN_OBJECT, stockIn);
					startActivity(intent);
				}else if(position<=number_shop-1+number_customer+number_company+number_stockin+number_stockout){
					StockOut stockOut = StockOutLab.get(QueryInformationActivity.this).getStockOuts()
							.get(position-number_shop-number_customer-number_company-number_stockin);
					Intent intent = new Intent(QueryInformationActivity.this, AddOrUpdateShopOutStockActivity.class);
					intent.putExtra(AddOrUpdateShopOutStockActivity.STOCKOUT_OBJECT, stockOut);
					startActivity(intent);
				}
				
			}
		});
	
	}

	
	class MyQueryListAdapter extends ArrayAdapter<Object>{

		int resource;
		
		public MyQueryListAdapter(Context context, int textViewResourceId,
				ArrayList<Object> objects) {
			super(context, textViewResourceId, objects);
			resource = textViewResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			View view = LayoutInflater.from(getContext()).inflate(resource, null);
			TextView name = (TextView)view.findViewById(R.id.query_activity_list_name);
			TextView danwei = (TextView)view.findViewById(R.id.query_activity_list_danwei);
			TextView shuxing = (TextView)view.findViewById(R.id.shuxing);
			
			if(position<=number_shop-1){
				Shop shop = (Shop) queryLists.get(position);
				name.setText("商品名称：" + shop.getShopName());
				shuxing.setText("商品信息");
				if(shop.getDanwei().isEmpty()){
					danwei.setText("计量单位：" + "暂无");
				}else{
					danwei.setText("计量单位：" + shop.getDanwei());
				}
			}else if(position<=number_shop-1+number_customer){
				Customer customer = (Customer) queryLists.get(position);
				name.setText("商品名称：" + customer.getCustomer());
				shuxing.setText("客户信息");
				if(customer.getShop().isEmpty()){
					danwei.setText("欲购商品：" + "暂无");
				}else{
					danwei.setText("欲购商品：" + customer.getShop());
				}
			}else if(position<=number_shop-1+number_customer+number_company){
				Company company = (Company) queryLists.get(position);
				name.setText("商品名称：" + company.getCompany());
				shuxing.setText("供应商信息");
				if(company.getShop().isEmpty()){
					danwei.setText("可提供商品：" + "暂无");
				}else{
					danwei.setText("可提供商品：" + company.getShop());
				}
			}else if(position<=number_shop-1+number_customer+number_company+number_stockin){
				StockIn stockIn = (StockIn) queryLists.get(position);
				name.setText("商品名称：" + stockIn.getShopName());
				shuxing.setText("商品入库信息");
				if(stockIn.getDanwei().isEmpty()){
					danwei.setText("计量单位：" + "暂无");
				}else{
					danwei.setText("计量单位：" + stockIn.getDanwei());
				}
			}else if(position<=number_shop-1+number_customer+number_company+number_stockin+number_stockout){
				StockOut stockOut = (StockOut) queryLists.get(position);
				name.setText("商品名称：" + stockOut.getShopName());
				shuxing.setText("商品出库信息");
				if(stockOut.getDanwei().isEmpty()){
					danwei.setText("计量单位：" + "暂无");
				}else{
					danwei.setText("计量单位：" + stockOut.getDanwei());
				}
			}
			
			return view;
		}
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		number_shop = ShopLab.get(QueryInformationActivity.this).getShops().size();
		number_customer = CustomerLab.get(QueryInformationActivity.this).getCustomers().size();
		number_company = CompanyLab.get(QueryInformationActivity.this).getCompanys().size();
		number_stockin = StockInLab.get(QueryInformationActivity.this).getStockIns().size();
		number_stockout = StockOutLab.get(QueryInformationActivity.this).getStockOuts().size();
	}
	
}
