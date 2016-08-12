package activity;

import java.util.ArrayList;

import model.Shop;
import model.ShopLab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.warehouse.R;

public class QueryShopInformationActivity extends Activity {

	private ListView listView;
	private ArrayList<Shop> mShops;
	public ShopAdapter adapter;
	private ArrayList<Boolean> selecteds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_scc_information);
		
		listView = (ListView)findViewById(R.id.listView_querry_scc_information);
		
		mShops = ShopLab.get(QueryShopInformationActivity.this).getShops();
		
		adapter = new ShopAdapter(QueryShopInformationActivity.this, 
				R.layout.query_scc_information_list, mShops);
		listView.setAdapter(adapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Shop shop = ShopLab.get(QueryShopInformationActivity.this).getShops().get(position);
			//	Shop shop2 = (Shop)adapter.getItem(position);
				Intent intent = new Intent(QueryShopInformationActivity.this, AddOrUpdateShopInformationActivity.class);
				intent.putExtra(AddOrUpdateShopInformationActivity.SHOP_OBJECT, shop);
				startActivity(intent);
			}
		});
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode mode) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.list_item_context, menu);
				return true;
			}
			
			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				switch (item.getItemId()) {
				case R.id.item_delete:
					for(int i=0; i<mShops.size(); i++){
						if(selecteds.get(i)){
							ShopLab.get(QueryShopInformationActivity.this).deleteShop(mShops.get(i));
							selecteds.remove(i);
							i--;
						}
					}
					mode.finish();
					adapter.notifyDataSetChanged();
					return true;
				default:
					return false;
				}
				
			}
			
			@Override
			public void onItemCheckedStateChanged(ActionMode mode, int position,
					long id, boolean checked) {
				selecteds.set(position, !selecteds.get(position));
				
		//		Log.d("wangbin", "被选中的"+position+selecteds.get(position));
				
			}
		});
		
		selecteds = new ArrayList<Boolean>();
	//	Log.d("wangbin", mShops.size()+"!");
		for(int i=0; i<mShops.size();i++){
			selecteds.add(i, false);
		}
	}
	
	class ShopAdapter extends ArrayAdapter<Shop>{

		int resource;
		
		public ShopAdapter(Context context, int textViewResourceId,
				ArrayList<Shop> objects) {
			super(context, textViewResourceId, objects);
			resource = textViewResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Shop shop = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resource, null);
			TextView name = (TextView)view.findViewById(R.id.query_scc_information_list_name);
			TextView danwei = (TextView)view.findViewById(R.id.query_scc_information_list_danwei);
			name.setText("商品名称：" + shop.getShopName());
			if(shop.getDanwei().isEmpty()){
				danwei.setText("计量单位：" + "暂无");
			}else{
				danwei.setText("计量单位：" + shop.getDanwei());
			}
			return view;
		}
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		listView.setAdapter(adapter);
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		adapter.notifyDataSetChanged();
	}
	
}
