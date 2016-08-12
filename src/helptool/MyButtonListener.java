package helptool;

import java.util.ArrayList;

import activity.AnythingActivity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.example.warehouse.R;

public class MyButtonListener implements OnClickListener{

	ArrayList<Button> mButtons = AnythingActivity.mButtons;
	ArrayList<Context>mContexts;
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.information:
			AnythingActivity.viewPager.setCurrentItem(0);
			break;
		case R.id.stock:
			AnythingActivity.viewPager.setCurrentItem(1);
			break;
		case R.id.query:
			AnythingActivity.viewPager.setCurrentItem(2);
			break;
		case R.id.user:
			AnythingActivity.viewPager.setCurrentItem(3);
			break;
		case R.id.we:
			AnythingActivity.viewPager.setCurrentItem(4);
			break;

		default:
			break;
		}
	}

}
