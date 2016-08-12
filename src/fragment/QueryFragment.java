package fragment;

import activity.AnythingActivity;
import activity.QueryInformationActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.warehouse.R;

public class QueryFragment extends Fragment {

	private Button queryButton;
	
    public QueryFragment(){
        super();
    }

    /**
     * 覆盖此函数，先通过inflater inflate函数得到view最后返回
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.query_fragment, container, false);
        queryButton = (Button)v.findViewById(R.id.query_button);
        queryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(AnythingActivity.anythingActivity, QueryInformationActivity.class);
				startActivity(intent);
			}
		});
        return v;
    }
	
}
