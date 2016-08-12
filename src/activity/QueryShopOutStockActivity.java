package activity;

import java.util.ArrayList;

import model.StockIn;
import model.StockInLab;
import model.StockOut;
import model.StockOutLab;
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
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.warehouse.R;

public class QueryShopOutStockActivity extends Activity {

	private ListView listView;
	private ArrayList<StockOut> stockOuts;
	private StockOutAdapter adapter;
	private ArrayList<Boolean> selecteds;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_shop_out_stock);
		
		stockOuts = StockOutLab.get(QueryShopOutStockActivity.this).getStockOuts();
		
		listView = (ListView)findViewById(R.id.list_query_stock_out);
		adapter = new StockOutAdapter(QueryShopOutStockActivity.this,
				R.layout.query_scc_information_list, stockOuts);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				StockOut stockOut = StockOutLab.get(QueryShopOutStockActivity.this).getStockOuts().get(position);
				//	Shop shop2 = (Shop)adapter.getItem(position);
				Intent intent = new Intent(QueryShopOutStockActivity.this, AddOrUpdateShopOutStockActivity.class);
				intent.putExtra(AddOrUpdateShopOutStockActivity.STOCKOUT_OBJECT, stockOut);
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
					for(int i=0; i<stockOuts.size(); i++){
						if(selecteds.get(i)){
							StockOutLab.get(QueryShopOutStockActivity.this).deleteStockOut(stockOuts.get(i));
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
		for(int i=0; i<stockOuts.size();i++){
			selecteds.add(i, false);
		}
		
	}
	
	class StockOutAdapter extends ArrayAdapter<StockOut>{

		int resource;
		
		public StockOutAdapter(Context context, int textViewResourceId,
				ArrayList<StockOut> objects) {
			super(context, textViewResourceId, objects);
			resource = textViewResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			StockOut stockOut = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resource, null);
			TextView name = (TextView)view.findViewById(R.id.query_scc_information_list_name);
			TextView danwei = (TextView)view.findViewById(R.id.query_scc_information_list_danwei);
			name.setText("商品名称：" + stockOut.getShopName());
			if(stockOut.getDanwei().isEmpty()){
				danwei.setText("计量单位：" + "暂无");
			}else{
				danwei.setText("计量单位：" + stockOut.getDanwei());
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
