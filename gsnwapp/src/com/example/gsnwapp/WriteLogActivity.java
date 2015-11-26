package com.example.gsnwapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.network.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class WriteLogActivity extends Activity {

	private EditText editText_prepareWork=null;
	private EditText edit_text_doneWork=null;
	private Spinner spinner_state=null;
	private Button btn_save=null;
	private Button btn_submit=null;
	private TextView text_preparedHours=null;
	private TextView text_doneHours=null;
	private SeekBar seekBar_prepared=null;
	private SeekBar seekBar_done=null;
	
	private String mPrepareWork=null;
	private String mDoneWork=null;
	private String[] spinnerState=null;
	private List<String> spinner_list=new ArrayList<String>();
	private int preparedWorkHours=0;
	private int doneWorkHours=0;
	private boolean editable=true;
	private String logState=null;
	private String url=null;
	UserState userState=null;
	
	private final float TOTAL_HOURS=10;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_write_log);
		

		url=getString(R.string.ip_server)+getString(R.string.url_write_log);
		//获取当前是哪个用户
		userState=(UserState)getApplicationContext();
		//初始化
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	//通过findviewById初始化控件
	@SuppressWarnings("rawtypes")
	public void initView(){
		editText_prepareWork=(EditText)findViewById(R.id.editText_prepareWork);
		edit_text_doneWork=(EditText)findViewById(R.id.editText_doneWork);
		btn_save=(Button)findViewById(R.id.btn_save);
		btn_submit=(Button)findViewById(R.id.btn_submit);
		text_preparedHours=(TextView)findViewById(R.id.text_prepareWork);
		text_doneHours=(TextView)findViewById(R.id.text_doneWord);
		seekBar_prepared=(SeekBar)findViewById(R.id.seekBar_prepareWork);
		seekBar_done=(SeekBar)findViewById(R.id.seekBar_doneWork);
		
		spinner_state=(Spinner)findViewById(R.id.spinner_state);
		spinnerState=getResources().getStringArray(R.array.log_states);
		if(spinnerState!=null){
			for(int i=0;i<spinnerState.length;i++){
				spinner_list.add(spinnerState[i]);
			}
			ArrayAdapter spinnerAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinner_list);
			spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spinner_state.setAdapter(spinnerAdapter);
		}
		//设置文本框是否可编辑
		if(!editable){
			editText_prepareWork.setFocusable(false);
			edit_text_doneWork.setFocusable(false);
		}
		
		seekBar_prepared.setOnSeekBarChangeListener(new OnPreparedSeekBarListener());
		
		seekBar_done.setOnSeekBarChangeListener(new OnDoneSeekBarListener());
		//保存按钮的事件监听
		btn_save.setOnClickListener(new BtnSaveClickListener());
		//提交按钮
		btn_submit.setOnClickListener(new BtnSubmitClickListener());
		
	}
	
	public class OnPreparedSeekBarListener implements OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
			preparedWorkHours=(int)(progress*TOTAL_HOURS/100);
			text_preparedHours.setText(preparedWorkHours+getString(R.string.hours));
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class OnDoneSeekBarListener implements OnSeekBarChangeListener{

		@Override
		public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			// TODO Auto-generated method stub
			doneWorkHours=(int)(progress*TOTAL_HOURS/100);
			text_doneHours.setText(doneWorkHours+getString(R.string.hours));
		}

		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	//在此重写保存按钮的时间监听函数
	public class BtnSaveClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

		}
		
	}
	//在此提交保存按钮的时间监听函数
	public class BtnSubmitClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			mPrepareWork=editText_prepareWork.getText().toString();
			mDoneWork=edit_text_doneWork.getText().toString();
			logState=spinner_state.getSelectedItem().toString();
			long time=System.currentTimeMillis();
			
			//检查应该填写的项目有没有填写
			if(mPrepareWork==null || "".equals(mPrepareWork)){
				Toast.makeText(getApplicationContext(), getString(R.string.prepare_work_not_null), Toast.LENGTH_SHORT);
				return;
			}
			if(mDoneWork==null || "".equals(mDoneWork)){
				Toast.makeText(getApplicationContext(), getString(R.string.done_work_not_null), Toast.LENGTH_SHORT);
				return;
			}
			
			List<NameValuePair> logList=new ArrayList<NameValuePair>();
			NameValuePair nvLogId=new BasicNameValuePair("logId", userState.getUserId()+time); 
			NameValuePair nvUserId=new BasicNameValuePair("userId", userState.getUserId());
			NameValuePair nvPrepareWork=new BasicNameValuePair("prepareWork", mPrepareWork);
			NameValuePair nvDoneWork=new BasicNameValuePair("doneWork", mDoneWork);
			NameValuePair nvLogState=new BasicNameValuePair("logState", logState);
			NameValuePair nvPrepareWorkHours=new BasicNameValuePair("prepareWorkHour", String.valueOf(preparedWorkHours));
			NameValuePair nvDoneWorkHours=new BasicNameValuePair("doneWorkHour", String.valueOf(doneWorkHours));
			logList.add(nvLogId);logList.add(nvUserId);logList.add(nvPrepareWork);logList.add(nvDoneWork);
			logList.add(nvLogState);logList.add(nvPrepareWorkHours);logList.add(nvDoneWorkHours);
			
			String result=network.connection(url, logList);
			
			Log.v("log result", result);
			
			if("1".equals(result)){
				Toast.makeText(getApplicationContext(), getString(R.string.submit_log_success), Toast.LENGTH_LONG).show();
				editText_prepareWork.setText("");
				edit_text_doneWork.setText("");
				seekBar_prepared.setProgress(0);
				seekBar_done.setProgress(0);
			}else{
				Toast.makeText(getApplicationContext(), getString(R.string.submit_log_failed), Toast.LENGTH_LONG).show();
			}
		}
	}
	/*
	 * 
	 * back to CdActivity
	 */
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  {  
        if (keyCode == KeyEvent.KEYCODE_BACK ){  
        	Intent intent=new Intent(this,CdActivity.class);
        	startActivity(intent);
        	return true;
        }
        return false;
    }
}
