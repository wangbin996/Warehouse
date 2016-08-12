package model;

import java.util.ArrayList;
import java.util.List;

import activity.AnythingActivity;
import android.content.Context;
import android.widget.Button;


public class ModelUI {

	private static ModelUI sModelUI;
	private Context mContext;
	
	private ArrayList<Button> mButtons;
	
	private ModelUI(Context context){
		mContext = context;
		mButtons = new ArrayList<Button>();
	}
	
	public static ModelUI get(Context c){
		sModelUI = new ModelUI(c);
		return sModelUI;
	}



	public ArrayList<Button> getButtons() {
		return mButtons;
	}

	public void setButtons(ArrayList<Button> Buttons) {
		mButtons = Buttons;
	}
	
}
