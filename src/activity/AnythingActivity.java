package activity;

import fragment.InformationFragment;
import fragment.QueryFragment;
import fragment.StockFragment;
import fragment.UserFragment;
import fragment.WeFragment;
import helptool.MyButtonListener;

import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.CompanyLab;
import model.Customer;
import model.CustomerLab;
import model.ModelUI;
import model.Shop;
import model.ShopLab;
import model.StockIn;
import model.StockInLab;
import model.StockOut;
import model.StockOutLab;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.Window;
import android.widget.Button;

import com.example.warehouse.R;

public class AnythingActivity extends FragmentActivity {

	private static final String TAG = "wangbin";
	
	private Button informationButton;
	private Button stockButton;
	private Button queryButton;
	private Button userButton;
	private Button weButton;
	
	public static ArrayList<Button> mButtons = new ArrayList<Button>();
	
	public static ViewPager viewPager;
	private List<Fragment> fragmentList = new ArrayList<Fragment>();
	
	public static AnythingActivity anythingActivity;
	
	private int flag = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.anything_activity);
		
		anythingActivity = this;
		
		informationButton = (Button)findViewById(R.id.information);
		stockButton = (Button)findViewById(R.id.stock);
		queryButton = (Button)findViewById(R.id.query);
		userButton = (Button)findViewById(R.id.user);
		weButton = (Button)findViewById(R.id.we);
		informationButton.setOnClickListener(new MyButtonListener());
		stockButton.setOnClickListener(new MyButtonListener());
		queryButton.setOnClickListener(new MyButtonListener());
		userButton.setOnClickListener(new MyButtonListener());
		weButton.setOnClickListener(new MyButtonListener());
		
		mButtons.add(informationButton);
		mButtons.add(stockButton);
		mButtons.add(queryButton);
		mButtons.add(userButton);
		mButtons.add(weButton);
//		Log.d(TAG, ""+mButtons.size());
		
		ModelUI modelUI = ModelUI.get(AnythingActivity.this);
		modelUI.setButtons(mButtons);
		
		viewPager = (ViewPager)findViewById(R.id.viewPager);

		fragmentList.add(new InformationFragment());
		fragmentList.add(new StockFragment());
		fragmentList.add(new QueryFragment());
		fragmentList.add(new UserFragment());
		fragmentList.add(new WeFragment());

		viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), fragmentList, null));
		viewPager.setOnPageChangeListener(listener);
		//初始化
        mButtons.get(0).setBackgroundResource(R.drawable.yes);
		mButtons.get(1).setBackgroundResource(R.drawable.no);
		mButtons.get(2).setBackgroundResource(R.drawable.no);
		mButtons.get(3).setBackgroundResource(R.drawable.no);
		mButtons.get(4).setBackgroundResource(R.drawable.no);
	}

	OnPageChangeListener listener = new OnPageChangeListener() {
		
		@Override
		public void onPageSelected(int arg0) {
			switch (arg0) {
			case 0:
				flag = 0;
		        mButtons.get(0).setBackgroundResource(R.drawable.yes);
				mButtons.get(1).setBackgroundResource(R.drawable.no);
				mButtons.get(2).setBackgroundResource(R.drawable.no);
				mButtons.get(3).setBackgroundResource(R.drawable.no);
				mButtons.get(4).setBackgroundResource(R.drawable.no);
				break;
			case 1:
				flag = 1;
		        mButtons.get(1).setBackgroundResource(R.drawable.yes);
				mButtons.get(0).setBackgroundResource(R.drawable.no);
				mButtons.get(2).setBackgroundResource(R.drawable.no);
				mButtons.get(3).setBackgroundResource(R.drawable.no);
				mButtons.get(4).setBackgroundResource(R.drawable.no);
				break;
			case 2:
				flag = 2;
		        mButtons.get(2).setBackgroundResource(R.drawable.yes);
				mButtons.get(1).setBackgroundResource(R.drawable.no);
				mButtons.get(0).setBackgroundResource(R.drawable.no);
				mButtons.get(3).setBackgroundResource(R.drawable.no);
				mButtons.get(4).setBackgroundResource(R.drawable.no);
				break;
			case 3:
				flag = 3;
				mButtons.get(3).setBackgroundResource(R.drawable.yes);
				mButtons.get(1).setBackgroundResource(R.drawable.no);
				mButtons.get(2).setBackgroundResource(R.drawable.no);
				mButtons.get(0).setBackgroundResource(R.drawable.no);
				mButtons.get(4).setBackgroundResource(R.drawable.no);
				break;
			case 4:
				flag = 4;
				mButtons.get(4).setBackgroundResource(R.drawable.yes);
				mButtons.get(1).setBackgroundResource(R.drawable.no);
				mButtons.get(2).setBackgroundResource(R.drawable.no);
				mButtons.get(3).setBackgroundResource(R.drawable.no);
				mButtons.get(0).setBackgroundResource(R.drawable.no);
				break;

			default:
				break;
			}
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	};

	
    class MyPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragmentList;

        public MyPagerAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> titleList){
            super(fm);
            this.fragmentList = fragmentList;
        }

		/**
         * 得到每个页面
         */
        @Override
        public Fragment getItem(int arg0) {
            return (fragmentList == null || fragmentList.size() == 0) ? null : fragmentList.get(arg0);
        }

        /**
         * 每个页面的title
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

        /**
         * 页面的总个数
         */
        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }
    }
	
    
    @Override
    protected void onStart() {
    	super.onStart();
    	mButtons.get(4).setBackgroundResource(R.drawable.no);
		mButtons.get(1).setBackgroundResource(R.drawable.no);
		mButtons.get(2).setBackgroundResource(R.drawable.no);
		mButtons.get(3).setBackgroundResource(R.drawable.no);
		mButtons.get(0).setBackgroundResource(R.drawable.no);
		switch (flag) {
		case 0:
			mButtons.get(0).setBackgroundResource(R.drawable.yes);
			break;
		case 1:
			mButtons.get(1).setBackgroundResource(R.drawable.yes);
			break;
		case 2:
			mButtons.get(2).setBackgroundResource(R.drawable.yes);
			break;
		case 3:
			mButtons.get(3).setBackgroundResource(R.drawable.yes);
			break;
		case 4:
			mButtons.get(4).setBackgroundResource(R.drawable.yes);
			break;

		default:
			break;
		}
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    	//---------------------------------------------------------------------------------------
		ArrayList<Shop> shops = ShopLab.get(AnythingActivity.this).getShops();
		ShopLab.get(AnythingActivity.this).saveShops(shops);
		
		ArrayList<Customer> customers = CustomerLab.get(AnythingActivity.this).getCustomers();
		CustomerLab.get(AnythingActivity.this).saveCustomers(customers);
		
		ArrayList<Company>companies = CompanyLab.get(AnythingActivity.this).getCompanys();
		CompanyLab.get(AnythingActivity.this).saveCompanys(companies);
    	//---------------------------------------------------------------------------------------
		ArrayList<StockIn> stockIns = StockInLab.get(AnythingActivity.this).getStockIns();
		StockInLab.get(AnythingActivity.this).saveStockIns(stockIns);
		
		ArrayList<StockOut> stockOuts = StockOutLab.get(AnythingActivity.this).getStockOuts();
		StockOutLab.get(AnythingActivity.this).saveStockOuts(stockOuts);
    }
}
