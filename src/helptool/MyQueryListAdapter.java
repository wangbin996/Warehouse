package helptool;

import java.util.ArrayList;
import java.util.List;

import model.QueryList;
import android.content.Context;
import android.widget.ArrayAdapter;

public class MyQueryListAdapter extends ArrayAdapter<QueryList>{

	public MyQueryListAdapter(Context context, int id,ArrayList<QueryList> queryLists) {
		super(context, id, queryLists);
		// TODO Auto-generated constructor stub
	}

}
