package fragment;

import java.util.ArrayList;

import model.ModelUI;
import model.Shop;
import model.ShopLab;
import model.StockIn;
import model.StockInLab;
import model.StockOut;
import model.StockOutLab;
import activity.AddOrUpdateShopInStockActivity;
import activity.AddOrUpdateShopInformationActivity;
import activity.AddOrUpdateShopOutStockActivity;
import activity.AnythingActivity;
import activity.QueryCompanyInformationActivity;
import activity.QueryCustomerInformationActivity;
import activity.QueryShopInStockActivity;
import activity.QueryShopInformationActivity;
import activity.QueryShopOutStockActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.warehouse.R;

public class StockFragment extends Fragment {

	private Button inshopButton;
	private Button outshopButton;
	private Button addInShopButton,deleteInShopButton,updateInShopButton,queryInShopButton;
	private Button addOutShopButton,deleteOutShopButton,updateOutShopButton,queryOutShopButton;
	private LinearLayout linearLayout_in,linearLayout_out;
	

	private static final int DELETE_SHOP_IN_STOCK = 1;
	private static final int QUERY_SHOP_IN_STOCK = 2;
	private static final int DELETE_SHOP_OUT_STOCK = 3;
	private static final int QUERY_SHOP_OUT_STOCK = 4;
	
    public StockFragment(){
        super();
    }

    /**
     * 覆盖此函数，先通过inflater inflate函数得到view最后返回
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
        View v = inflater.inflate(R.layout.stock_fragment, container, false);
        
        linearLayout_in = (LinearLayout)v.findViewById(R.id.linear_in);
        linearLayout_out = (LinearLayout)v.findViewById(R.id.linear_out);
        
        inshopButton = (Button)v.findViewById(R.id.stock_inshop);
        inshopButton.setOnClickListener(listener);
        outshopButton = (Button)v.findViewById(R.id.stock_outshop);
        outshopButton.setOnClickListener(listener);
        
        addInShopButton = (Button)v.findViewById(R.id.add_stock_inshop);
        addInShopButton.setOnClickListener(listener);
        deleteInShopButton = (Button)v.findViewById(R.id.delete_stock_inshop);
        deleteInShopButton.setOnClickListener(listener);
        updateInShopButton = (Button)v.findViewById(R.id.update_stock_inshop);
        updateInShopButton.setOnClickListener(listener);
        queryInShopButton = (Button)v.findViewById(R.id.query_stock_inshop);
        queryInShopButton.setOnClickListener(listener);

        addOutShopButton = (Button)v.findViewById(R.id.add_stock_outshop);
        addOutShopButton.setOnClickListener(listener);
        deleteOutShopButton = (Button)v.findViewById(R.id.delete_stock_outshop);
        deleteOutShopButton.setOnClickListener(listener);
        updateOutShopButton = (Button)v.findViewById(R.id.update_stock_outshop);
        updateOutShopButton.setOnClickListener(listener);
        queryOutShopButton = (Button)v.findViewById(R.id.query_stock_outshop);
        queryOutShopButton.setOnClickListener(listener);
        
        return v;
    }
	
    Handler handler = new Handler(){

		public void handleMessage(Message message) {
			switch (message.what) {
			case DELETE_SHOP_IN_STOCK:
				LayoutInflater flater1 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout1 = flater1.inflate(R.layout.delete_scc_dialog, null);
				AlertDialog.Builder builder1  = new Builder(AnythingActivity.anythingActivity);
				builder1.setTitle("提示" ) ;
				builder1.setView(layout1);
				builder1.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent1 = new Intent(AnythingActivity.anythingActivity, QueryShopInStockActivity.class);
						startActivity(intent1);
					}
				} );
				builder1.show();
				break;
			case QUERY_SHOP_IN_STOCK:
				LayoutInflater flater2 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout2 = flater2.inflate(R.layout.update_scc_dialog, null);
				AlertDialog.Builder builder2  = new Builder(AnythingActivity.anythingActivity);
				builder2.setTitle("提示" ) ;
				builder2.setView(layout2);
				builder2.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent2 = new Intent(AnythingActivity.anythingActivity, QueryShopInStockActivity.class);
						startActivity(intent2);
					}
				} );
				builder2.show();
				break;
			case DELETE_SHOP_OUT_STOCK:
				LayoutInflater flater3 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout3 = flater3.inflate(R.layout.delete_scc_dialog, null);
				AlertDialog.Builder builder3  = new Builder(AnythingActivity.anythingActivity);
				builder3.setTitle("提示" ) ;
				builder3.setView(layout3);
				builder3.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent3 = new Intent(AnythingActivity.anythingActivity, QueryShopOutStockActivity.class);
						startActivity(intent3);
					}
				} );
				builder3.show();
				break;
			case QUERY_SHOP_OUT_STOCK:
				LayoutInflater flater4 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout4 = flater4.inflate(R.layout.update_scc_dialog, null);
				AlertDialog.Builder builder4  = new Builder(AnythingActivity.anythingActivity);
				builder4.setTitle("提示" ) ;
				builder4.setView(layout4);
				builder4.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						//model.setTitle(editText_title_dialog.getText().toString());
						Intent intent4 = new Intent(AnythingActivity.anythingActivity, QueryShopOutStockActivity.class);
						startActivity(intent4);
					}
				} );
				builder4.show();
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
			case R.id.stock_inshop:
				linearLayout_in.setVisibility(View.VISIBLE);
				linearLayout_out.setVisibility(View.INVISIBLE);
				addInShopButton.setVisibility(View.VISIBLE);
				deleteInShopButton.setVisibility(View.VISIBLE);
				updateInShopButton.setVisibility(View.VISIBLE);
				queryInShopButton.setVisibility(View.VISIBLE);
				addOutShopButton.setVisibility(View.INVISIBLE);
				deleteOutShopButton.setVisibility(View.INVISIBLE);
				updateOutShopButton.setVisibility(View.INVISIBLE);
				queryOutShopButton.setVisibility(View.INVISIBLE);
				break;
			case R.id.stock_outshop:
				linearLayout_in.setVisibility(View.INVISIBLE);
				linearLayout_out.setVisibility(View.VISIBLE);
				addInShopButton.setVisibility(View.INVISIBLE);
				deleteInShopButton.setVisibility(View.INVISIBLE);
				updateInShopButton.setVisibility(View.INVISIBLE);
				queryInShopButton.setVisibility(View.INVISIBLE);
				addOutShopButton.setVisibility(View.VISIBLE);
				deleteOutShopButton.setVisibility(View.VISIBLE);
				updateOutShopButton.setVisibility(View.VISIBLE);
				queryOutShopButton.setVisibility(View.VISIBLE);
				break;
			case R.id.add_stock_inshop:
				Intent intent = new Intent(AnythingActivity.anythingActivity, AddOrUpdateShopInStockActivity.class);
				StockIn stockIn = new StockIn();
				ArrayList<StockIn> stockIns = StockInLab.get(getActivity()).getStockIns();
				stockIns.add(stockIn);
				intent.putExtra(AddOrUpdateShopInStockActivity.STOCKIN_OBJECT, stockIn);
				startActivity(intent);
				break;
			case R.id.delete_stock_inshop:
				Message message = new Message();
				message.what = DELETE_SHOP_IN_STOCK;
				handler.sendMessage(message);
				break;
			case R.id.update_stock_inshop:
				Message message2 = new Message();
				message2.what = QUERY_SHOP_IN_STOCK;
				handler.sendMessage(message2);
				break;
			case R.id.query_stock_inshop:
				Intent intent2 = new Intent(AnythingActivity.anythingActivity, QueryShopInStockActivity.class);
				startActivity(intent2);
				break;
			case R.id.add_stock_outshop:
				Intent intent1 = new Intent(AnythingActivity.anythingActivity, AddOrUpdateShopOutStockActivity.class);
				StockOut stockOut = new StockOut();
				ArrayList<StockOut> stockOuts = StockOutLab.get(getActivity()).getStockOuts();
				stockOuts.add(stockOut);
				intent1.putExtra(AddOrUpdateShopOutStockActivity.STOCKOUT_OBJECT, stockOut);
				startActivity(intent1);
				break;
			case R.id.delete_stock_outshop:
				Message message3 = new Message();
				message3.what = DELETE_SHOP_OUT_STOCK;
				handler.sendMessage(message3);
				break;
			case R.id.update_stock_outshop:
				Message message4 = new Message();
				message4.what = QUERY_SHOP_OUT_STOCK;
				handler.sendMessage(message4);
				break;
			case R.id.query_stock_outshop:
				Intent intent3 = new Intent(AnythingActivity.anythingActivity, QueryShopOutStockActivity.class);
				startActivity(intent3);
				break;

			default:
				break;
			}
		}
	};
    
}
