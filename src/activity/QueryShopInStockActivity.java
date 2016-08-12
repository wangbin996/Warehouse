package activity;

import java.util.ArrayList;

import model.Shop;
import model.ShopLab;
import model.StockIn;
import model.StockInLab;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.warehouse.R;

public class QueryShopInStockActivity extends Activity {

	private ListView listView;
	private ArrayList<StockIn> stockIns;
	private StockInAdapter adapter;
	private ArrayList<Boolean> selecteds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_shop_in_stock);
		
		stockIns = StockInLab.get(QueryShopInStockActivity.this).getStockIns();
		
		listView = (ListView)findViewById(R.id.list_query_stock_in);
		adapter = new StockInAdapter(QueryShopInStockActivity.this,
				R.layout.query_scc_information_list, stockIns);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				StockIn stockIn = StockInLab.get(QueryShopInStockActivity.this).getStockIns().get(position);
				Intent intent = new Intent(QueryShopInStockActivity.this, AddOrUpdateShopInStockActivity.class);
				intent.putExtra(AddOrUpdateShopInStockActivity.STOCKIN_OBJECT, stockIn);
				startActivity(intent);
			}
		});
		
		listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		listView.setMultiChoiceModeListener(new MultiChoiceModeListener() {
			
			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
				return false;
			}
			
			@Override
			public void onDestroyActionMode(ActionMode mode) {
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
					for(int i=0; i<stockIns.size(); i++){
						if(selecteds.get(i)){
							StockInLab.get(QueryShopInStockActivity.this).deleteStockIn(stockIns.get(i));
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
			}
		});
		
		selecteds = new ArrayList<Boolean>();
		for(int i=0; i<stockIns.size();i++){
			selecteds.add(i, false);
		}
		
	}
	
	class StockInAdapter extends ArrayAdapter<StockIn>{

		int resource;
		
		public StockInAdapter(Context context, int textViewResourceId,
				ArrayList<StockIn> objects) {
			super(context, textViewResourceId, objects);
			resource = textViewResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			StockIn stockIn = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resource, null);
			TextView name = (TextView)view.findViewById(R.id.query_scc_information_list_name);
			TextView danwei = (TextView)view.findViewById(R.id.query_scc_information_list_danwei);
			name.setText("商品名称：" + stockIn.getShopName());
			if(stockIn.getDanwei().isEmpty()){
				danwei.setText("计量单位：" + "暂无");
			}else{
				danwei.setText("计量单位：" + stockIn.getDanwei());
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
