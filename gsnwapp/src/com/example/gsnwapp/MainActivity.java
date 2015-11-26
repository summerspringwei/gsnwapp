package com.example.gsnwapp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.network.BascActivity;
import com.example.network.HttpUtils;
import com.example.network.network;

import android.os.Bundle;
import android.util.Log;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends BascActivity {
	//将URL写到config配置文件当中
	//get the url from the config-file which is in the values/config.xml
    String url=null;
    Intent intent =null;
    String menu;
    EditText userid = null;
    EditText pssd = null;
    Button denglu = null;
    Button zhuce=null;
    
    UserState userState=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		url=getString(R.string.ip_server)+getString(R.string.url_login);
		//url=getString(R.string.mvc_server)+getString(R.string.mvc_login);
		userid =(EditText)findViewById(R.id.user);
		pssd =(EditText)findViewById(R.id.pssd);
		denglu =(Button)findViewById(R.id.button1);
		zhuce = (Button)findViewById(R.id.button2);
		denglu.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				//检查书否输入为空
				String s_userId=userid.getText().toString();
				String s_userPassword=pssd.getText().toString();
				if(s_userId==null || "".equals(s_userId)){
					Toast.makeText(getApplicationContext(), 
							getString(R.string.userid_null_notation),
							Toast.LENGTH_SHORT).show();
					return;
				}
				if(s_userPassword==null || "".equals(s_userPassword)){
					Toast.makeText(getApplicationContext(), 
							getString(R.string.user_password_null_notation),
							Toast.LENGTH_SHORT).show();
					return;
				}
				
				NameValuePair usernameid = new BasicNameValuePair("userid",s_userId);
				NameValuePair password = new BasicNameValuePair("pssd",s_userPassword);
				List<NameValuePair> mlist = new ArrayList<NameValuePair>();
				mlist.add(usernameid);
				mlist.add(password);
				
				//需要添加访问网络状态的权限
				ConnectivityManager connMgr = (ConnectivityManager)
				        getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			    if (networkInfo == null || !networkInfo.isConnected()) {
			        // display error
			    	Toast.makeText(getApplicationContext(), getString(R.string.network_disconnect), Toast.LENGTH_LONG).show();
			    	return;
			    }

				String out1 = network.connection(url,mlist);//�����෽��ʵ����������
				//netHelper.execute(url,mlist);
				Log.v("login_result", out1);
				String s_menu=getString(R.string.write_log)+","+getString(R.string.query_log);
				//解析返回的字符串，如果登录成功
				if(out1!=null && out1.charAt(0)=='1'){
					userState=(UserState)getApplicationContext();
					userState.setUserId(s_userId);
					Toast.makeText(getApplicationContext(), getString(R.string.login_success), Toast.LENGTH_SHORT).show();
					intent = new Intent(MainActivity.this,CdActivity.class);
					Bundle bundle=new Bundle();
					bundle.putString("key", s_menu);
					intent.putExtras(bundle);
					startActivity(intent);
				}
				else{
					Toast.makeText(getApplicationContext(), getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
				}
			}
		});
		zhuce.setOnClickListener(new BtnZhuceClickListener());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public class BtnZhuceClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			intent = new Intent(MainActivity.this,RegActivity.class);
			startActivity(intent);
		}
	}
}
