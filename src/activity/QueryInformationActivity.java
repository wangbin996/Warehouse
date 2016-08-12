package activity;

import helptool.MyQueryListAdapter;

import java.util.ArrayList;

import model.QueryList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.warehouse.R;

public class QueryInformationActivity extends Activity {

	private ListView listView;
	private ArrayList<QueryList> queryLists;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.query_activity);
		
		listView = (ListView)findViewById(R.id.listView_querry);
		
		MyQueryListAdapter adapter = new MyQueryListAdapter(QueryInformationActivity.this, R.layout.query_list_item,queryLists);
	}
	
}
