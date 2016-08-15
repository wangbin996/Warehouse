package fragment;

import java.util.ArrayList;

import model.Company;
import model.CompanyLab;
import model.Customer;
import model.CustomerLab;
import model.ModelUI;
import model.Shop;
import model.ShopLab;

import activity.AddOrUpdateCompanyInformationActivity;
import activity.AddOrUpdateCustomerInformationActivity;
import activity.AddOrUpdateShopInformationActivity;
import activity.AnythingActivity;
import activity.QueryCompanyInformationActivity;
import activity.QueryCustomerInformationActivity;
import activity.QueryShopInformationActivity;
import activity.QueryShopInStockActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.warehouse.R;


public class InformationFragment extends Fragment {

	private Button shopButton,customerButton,companyButton;
	private Button addShopButton,deleteShopButton,updateShopButton,queryShopButton;
	private Button addCustomerButton,deleteCustomerButton,updateCustomerButton,queryCustomerButton;
	private Button addCompanyButton,deleteCompanyButton,updateCompanyButton,queryCompanyButton;
	private LinearLayout linear_shop,linear_customer,linear_company;
	
	private static final int UPDATE_SHOP = 1;
	private static final int UPDATE_CUSTOMER = 2;
	private static final int UPDATE_COMPANY = 3;	
	private static final int DELETE_SHOP = 4;
	private static final int DELETE_CUSTOMER = 5;
	private static final int DELETE_COMPANY = 6;
	
    public InformationFragment(){
        super();
    }

    /**
     * 覆盖此函数，先通过inflater inflate函数得到view最后返回
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        View v = inflater.inflate(R.layout.information_fragment, container, false);
        
        linear_shop = (LinearLayout)v.findViewById(R.id.linear_shop);
        linear_customer = (LinearLayout)v.findViewById(R.id.linear_customer);
        linear_company = (LinearLayout)v.findViewById(R.id.linear_company);
        
        shopButton = (Button)v.findViewById(R.id.information_shop);
        shopButton.setOnClickListener(listener);
        customerButton = (Button)v.findViewById(R.id.information_customer);
        customerButton.setOnClickListener(listener);
        companyButton = (Button)v.findViewById(R.id.information_company);
        companyButton.setOnClickListener(listener);
        
        addShopButton = (Button)v.findViewById(R.id.add_information_shop);
        addShopButton.setOnClickListener(listener);
        deleteShopButton = (Button)v.findViewById(R.id.delete_information_shop);
        deleteShopButton.setOnClickListener(listener);
        updateShopButton = (Button)v.findViewById(R.id.update_information_shop);
        updateShopButton.setOnClickListener(listener);
        queryShopButton = (Button)v.findViewById(R.id.query_information_shop);
        queryShopButton.setOnClickListener(listener);

        addCustomerButton = (Button)v.findViewById(R.id.add_information_customer);
        addCustomerButton.setOnClickListener(listener);
        deleteCustomerButton = (Button)v.findViewById(R.id.delete_information_customer);
        deleteCustomerButton.setOnClickListener(listener);
        updateCustomerButton = (Button)v.findViewById(R.id.update_information_customer);
        updateCustomerButton.setOnClickListener(listener);
        queryCustomerButton = (Button)v.findViewById(R.id.query_information_customer);
        queryCustomerButton.setOnClickListener(listener);
        
        addCompanyButton = (Button)v.findViewById(R.id.add_information_company);
        addCompanyButton.setOnClickListener(listener);
        deleteCompanyButton = (Button)v.findViewById(R.id.delete_information_company);
        deleteCompanyButton.setOnClickListener(listener);
        updateCompanyButton = (Button)v.findViewById(R.id.update_information_company);
        updateCompanyButton.setOnClickListener(listener);
        queryCompanyButton = (Button)v.findViewById(R.id.query_information_company);
        queryCompanyButton.setOnClickListener(listener);
        
        return v;
    }
    
    Handler handler = new Handler(){

		public void handleMessage(Message message) {
			switch (message.what) {
			case UPDATE_SHOP:
				LayoutInflater flater = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout = flater.inflate(R.layout.update_scc_dialog, null);
				AlertDialog.Builder builder  = new Builder(AnythingActivity.anythingActivity);
				builder.setTitle("提示" ) ;
				builder.setView(layout);
				builder.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryShopInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder.show();
				break;
			case UPDATE_CUSTOMER:
				LayoutInflater flater1 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout1 = flater1.inflate(R.layout.update_scc_dialog, null);
				AlertDialog.Builder builder1  = new Builder(AnythingActivity.anythingActivity);
				builder1.setTitle("提示" ) ;
				builder1.setView(layout1);
				builder1.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryCustomerInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder1.show();
				break;
			case UPDATE_COMPANY:
				LayoutInflater flater2 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout2 = flater2.inflate(R.layout.update_scc_dialog, null);
				AlertDialog.Builder builder2  = new Builder(AnythingActivity.anythingActivity);
				builder2.setTitle("提示" ) ;
				builder2.setView(layout2);
				builder2.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryCompanyInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder2.show();
				break;
			case DELETE_SHOP:
				LayoutInflater flater3 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout3 = flater3.inflate(R.layout.delete_scc_dialog, null);
				AlertDialog.Builder builder3  = new Builder(AnythingActivity.anythingActivity);
				builder3.setTitle("提示" ) ;
				builder3.setView(layout3);
				builder3.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryShopInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder3.show();
				break;
			case DELETE_CUSTOMER:
				LayoutInflater flater4 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout4 = flater4.inflate(R.layout.delete_scc_dialog, null);
				AlertDialog.Builder builder4  = new Builder(AnythingActivity.anythingActivity);
				builder4.setTitle("提示" ) ;
				builder4.setView(layout4);
				builder4.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryCustomerInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder4.show();
				break;
			case DELETE_COMPANY:
				LayoutInflater flater5 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout5 = flater5.inflate(R.layout.delete_scc_dialog, null);
				AlertDialog.Builder builder5  = new Builder(AnythingActivity.anythingActivity);
				builder5.setTitle("提示" ) ;
				builder5.setView(layout5);
				builder5.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent = new Intent(AnythingActivity.anythingActivity, QueryCompanyInformationActivity.class);
						startActivity(intent);
					}
				} );
				builder5.show();
				break;

			default:
				break;
			}
		}
    };

    OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.information_shop:
				linear_shop.setVisibility(View.VISIBLE);
				linear_customer.setVisibility(View.INVISIBLE);
				linear_company.setVisibility(View.INVISIBLE);
				addShopButton.setVisibility(View.VISIBLE);
				deleteShopButton.setVisibility(View.VISIBLE);
				updateShopButton.setVisibility(View.VISIBLE);
				queryShopButton.setVisibility(View.VISIBLE);
				addCustomerButton.setVisibility(View.INVISIBLE);
				deleteCustomerButton.setVisibility(View.INVISIBLE);
				updateCustomerButton.setVisibility(View.INVISIBLE);
				queryCustomerButton.setVisibility(View.INVISIBLE);
				addCompanyButton.setVisibility(View.INVISIBLE);
				deleteCompanyButton.setVisibility(View.INVISIBLE);
				updateCompanyButton.setVisibility(View.INVISIBLE);
				queryCompanyButton.setVisibility(View.INVISIBLE);
				break;
			case R.id.information_customer:
				linear_shop.setVisibility(View.INVISIBLE);
				linear_customer.setVisibility(View.VISIBLE);
				linear_company.setVisibility(View.INVISIBLE);
				addShopButton.setVisibility(View.INVISIBLE);
				deleteShopButton.setVisibility(View.INVISIBLE);
				updateShopButton.setVisibility(View.INVISIBLE);
				queryShopButton.setVisibility(View.INVISIBLE);
				addCustomerButton.setVisibility(View.VISIBLE);
				deleteCustomerButton.setVisibility(View.VISIBLE);
				updateCustomerButton.setVisibility(View.VISIBLE);
				queryCustomerButton.setVisibility(View.VISIBLE);
				addCompanyButton.setVisibility(View.INVISIBLE);
				deleteCompanyButton.setVisibility(View.INVISIBLE);
				updateCompanyButton.setVisibility(View.INVISIBLE);
				queryCompanyButton.setVisibility(View.INVISIBLE);
				break;
			case R.id.information_company:
				linear_shop.setVisibility(View.INVISIBLE);
				linear_customer.setVisibility(View.INVISIBLE);
				linear_company.setVisibility(View.VISIBLE);
				addShopButton.setVisibility(View.INVISIBLE);
				deleteShopButton.setVisibility(View.INVISIBLE);
				updateShopButton.setVisibility(View.INVISIBLE);
				queryShopButton.setVisibility(View.INVISIBLE);
				addCustomerButton.setVisibility(View.INVISIBLE);
				deleteCustomerButton.setVisibility(View.INVISIBLE);
				updateCustomerButton.setVisibility(View.INVISIBLE);
				queryCustomerButton.setVisibility(View.INVISIBLE);
				addCompanyButton.setVisibility(View.VISIBLE);
				deleteCompanyButton.setVisibility(View.VISIBLE);
				updateCompanyButton.setVisibility(View.VISIBLE);
				queryCompanyButton.setVisibility(View.VISIBLE);
				break;
			case R.id.add_information_shop:
				Intent intent3 = new Intent(AnythingActivity.anythingActivity, AddOrUpdateShopInformationActivity.class);
				Shop shop = new Shop();
				ArrayList<Shop> shops = ShopLab.get(getActivity()).getShops();
	//			Log.d("wangbin", shops.size()+"!!!添加这里上面");
				shops.add(shop);
				intent3.putExtra(AddOrUpdateShopInformationActivity.SHOP_OBJECT, shop);
				startActivity(intent3);
				break;
			case R.id.delete_information_shop:
				Message message3 = new Message();
				message3.what = DELETE_SHOP;
				handler.sendMessage(message3);
				break;
			case R.id.update_information_shop:
				Message message = new Message();
				message.what = UPDATE_SHOP;
				handler.sendMessage(message);
				break;
			case R.id.query_information_shop:
				Intent intent = new Intent(AnythingActivity.anythingActivity, QueryShopInformationActivity.class);
				startActivity(intent);
				break;
			case R.id.add_information_customer:
				Intent intent4 = new Intent(AnythingActivity.anythingActivity, AddOrUpdateCustomerInformationActivity.class);
				Customer customer = new Customer();
				ArrayList<Customer> customers = CustomerLab.get(getActivity()).getCustomers();
				customers.add(customer);
				intent4.putExtra(AddOrUpdateCustomerInformationActivity.CUSTOMER_OBJECT, customer);
				startActivity(intent4);
				break;
			case R.id.delete_information_customer:
				Message message4 = new Message();
				message4.what = DELETE_CUSTOMER;
				handler.sendMessage(message4);
				break;
			case R.id.update_information_customer:
				Message message1 = new Message();
				message1.what = UPDATE_CUSTOMER;
				handler.sendMessage(message1);
				break;
			case R.id.query_information_customer:
				Intent intent1 = new Intent(AnythingActivity.anythingActivity, QueryCustomerInformationActivity.class);
				startActivity(intent1);
				break;
			case R.id.add_information_company:
				Intent intent5 = new Intent(AnythingActivity.anythingActivity, AddOrUpdateCompanyInformationActivity.class);
				Company company = new Company();
				ArrayList<Company> companys = CompanyLab.get(getActivity()).getCompanys();
				companys.add(company);
				intent5.putExtra(AddOrUpdateCompanyInformationActivity.COMPANY_OBJECT, company);
				startActivity(intent5);
				break;
			case R.id.delete_information_company:
				Message message5 = new Message();
				message5.what = DELETE_COMPANY;
				handler.sendMessage(message5);
				break;
			case R.id.update_information_company:
				Message message2 = new Message();
				message2.what = UPDATE_COMPANY;
				handler.sendMessage(message2);
				break;
			case R.id.query_information_company:
				Intent intent2 = new Intent(AnythingActivity.anythingActivity, QueryCompanyInformationActivity.class);
				startActivity(intent2);
				break;

			default:
				break;
			}
		}
	};
	
	@Override
	public void onPause() {
		super.onPause();
		
	}
	
}
