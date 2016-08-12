package fragment;

import java.util.ArrayList;

import activity.AnythingActivity;
import activity.LoginActivity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.warehouse.R;

public class UserFragment extends Fragment {

	private ArrayList<Button> mButtons;
	
	private Button chooseButton;
	private Button deleteButton;
	private Button passwordButton;
	
	private static final int USER_DELETE = 1;
	private static final int USER_PASSWORD = 2;
	
	private String account;
	private String password;
	
    public UserFragment(){
        super();
    }

    /**
     * 覆盖此函数，先通过inflater inflate函数得到view最后返回
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        
        View v = inflater.inflate(R.layout.user_fragment, container, false);
        
        chooseButton = (Button)v.findViewById(R.id.user_choose);
        chooseButton.setOnClickListener(listener);
        deleteButton = (Button)v.findViewById(R.id.user_delete);
        deleteButton.setOnClickListener(listener);
        passwordButton = (Button)v.findViewById(R.id.user_password);
        passwordButton.setOnClickListener(listener);
        
        LayoutInflater flater = LayoutInflater.from(AnythingActivity.anythingActivity);
		SharedPreferences sharedPreferences = AnythingActivity.anythingActivity
				.getSharedPreferences(LoginActivity.FILENAME, 0);
        account = sharedPreferences.getString("account", "nothing");
        password = sharedPreferences.getString("password", "nothing");
        Log.d("wangbin", account+".."+password);
        
        return v;
    }
    
    Handler handler = new Handler(){

		public void handleMessage(Message message) {
			switch (message.what) {
			case USER_DELETE:
				
				AlertDialog.Builder builder1  = new Builder(AnythingActivity.anythingActivity);
				final TextView text = new TextView(AnythingActivity.anythingActivity);
				text.setText("  是否删除账号为："+"\""+ account +"\"的相关用户信息");
				builder1.setTitle("删除用户信息？" ) ;
				builder1.setView(text);
				builder1.setPositiveButton("删除" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						final EditText editText_title_dialog;
						editText_title_dialog = new EditText(AnythingActivity.anythingActivity);
						
						AlertDialog.Builder builder2  = new Builder(AnythingActivity.anythingActivity);
						builder2.setTitle("输入密码确认：" ) ;
						builder2.setView(editText_title_dialog);
						builder2.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								if(editText_title_dialog.getText().toString().equals(password)){
									SharedPreferences pref1 = AnythingActivity.anythingActivity.getSharedPreferences(LoginActivity.FILENAME, 0);
									pref1.edit().clear().commit();
									Toast.makeText(AnythingActivity.anythingActivity, "删除成功!", 1500).show();
								}else {
									Toast.makeText(AnythingActivity.anythingActivity, "密码错误!", 1500).show();
								}
							}
						} );
						builder2.show();
					}
				} );
				builder1.show();
				break;
			case USER_PASSWORD:
				LayoutInflater flater2 = LayoutInflater.from(AnythingActivity.anythingActivity);
				final View layout2 = flater2.inflate(R.layout.userpassword_dialog, null);
				final EditText account_userpassword = (EditText)layout2.findViewById(R.id.account_userpassword);
				final EditText old_userpassword = (EditText)layout2.findViewById(R.id.old_userpassword);
				final EditText new_userpassword = (EditText)layout2.findViewById(R.id.new_userpassword);;
				AlertDialog.Builder builder  = new Builder(AnythingActivity.anythingActivity);
				builder.setTitle("修改密码：" ) ;
				builder.setView(layout2);
				builder.setPositiveButton("确认" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(account.equals(account_userpassword.getText().toString()) && 
								password.equals(old_userpassword.getText().toString()) && 
								!(new_userpassword.getText().toString().isEmpty())){
							SharedPreferences.Editor editor = AnythingActivity.anythingActivity.getSharedPreferences(LoginActivity.FILENAME,0).edit();
							editor.putString("password", new_userpassword.getText().toString());	
							editor.commit();
							Toast.makeText(getContext(), "修改密码成功。", 1000).show();
						}
						else{
							Toast.makeText(getContext(), "修改密码失败，填写出错。。", 1000).show();
						}
					}
				} );
				builder.show();
				break;

			default:
				break;
			}
		}
    };
    
    OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.user_choose:
				deleteButton.setVisibility(View.VISIBLE);
				passwordButton.setVisibility(View.VISIBLE);
				break;
			case R.id.user_delete:
				Message message = new Message();
				message.what = USER_DELETE;
				handler.sendMessage(message);
				break;
			case R.id.user_password:
				Message message1 = new Message();
				message1.what = USER_PASSWORD;
				handler.sendMessage(message1);
				break;

			default:
				break;
			}
		}
	};

}
