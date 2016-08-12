package activity;

import java.util.ArrayList;

import model.Company;
import model.CompanyLab;
import model.Customer;
import model.CustomerLab;

import com.example.warehouse.R;

import activity.QueryCustomerInformationActivity.CustomerAdapter;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.OnItemClickListener;

public class QueryCompanyInformationActivity extends Activity {

	private ListView listView;
	private ArrayList<Company> mCompanys;
	private CompanyAdapter adapter;
	private ArrayList<Boolean> selecteds;
	private boolean flag = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_scc_information);
		
	//	View view = LayoutInflater.from(QueryCustomerActivity.this).inflate(R.layout.query_shop_information, null);
		listView = (ListView)findViewById(R.id.listView_querry_scc_information);
		mCompanys = CompanyLab.get(QueryCompanyInformationActivity.this).getCompanys();
		adapter = new CompanyAdapter(QueryCompanyInformationActivity.this, 
				R.layout.query_scc_information_list, mCompanys);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Company company = CompanyLab.get(QueryCompanyInformationActivity.this).getCompanys().get(position);
				Intent intent = new Intent(QueryCompanyInformationActivity.this, AddOrUpdateCompanyInformationActivity.class);
				intent.putExtra(AddOrUpdateCompanyInformationActivity.COMPANY_OBJECT, company);
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
					for(int i=0; i<mCompanys.size(); i++){
						if(selecteds.get(i)){
							CompanyLab.get(QueryCompanyInformationActivity.this).deleteCompany(mCompanys.get(i));
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
		for(int i=0; i<mCompanys.size();i++){
			selecteds.add(i, false);
		}
		
	}
	
	class CompanyAdapter extends ArrayAdapter<Company>{

		int resource;
		
		public CompanyAdapter(Context context, int textViewResourceId,
				ArrayList<Company> objects) {
			super(context, textViewResourceId, objects);
			resource = textViewResourceId;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Company company = getItem(position);
			View view = LayoutInflater.from(getContext()).inflate(resource, null);
			TextView name = (TextView)view.findViewById(R.id.query_scc_information_list_name);
			TextView danwei = (TextView)view.findViewById(R.id.query_scc_information_list_danwei);
			name.setText("供应商名称：" + company.getCompany());
			if(company.getShop().isEmpty()){
				danwei.setText("可提供商品：" + "暂无");
			}else {
				danwei.setText("可提供商品：" + company.getShop());
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
