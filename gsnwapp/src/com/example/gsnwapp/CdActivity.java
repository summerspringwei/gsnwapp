package com.example.gsnwapp;

import java.util.ArrayList;

import com.example.network.network;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CdActivity extends Activity{
	
	Intent intent=null;
	private ArrayList<String> list = new ArrayList<String>();
	private ListView mlistview ;
	private static String[] menulist ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cd);
		
		Bundle bundle = this.getIntent().getExtras();
		if(bundle!=null){
			String muen = bundle.getString("key");
			menulist = muen.split(",");
		}
		
		//ListView mlistview =(ListView)findViewById(R.id.listView1);
		
		mlistview = (ListView)findViewById(R.id.listView1);
		for(int i=0;i<menulist.length;i++){
		       list.add(menulist[i]);
		}
		mlistview.setAdapter(new ArrayAdapter<String>(this,  
                android.R.layout.simple_list_item_1, list));
		mlistview.setOnItemClickListener(new OnItemClickListener(){
			public void onItemClick(AdapterView<?> arg0,android.view.View arg1,int arg2,long arg3)
			{
			   	if(list.get(arg2).equals("�û���ɫ����")){
				  
			   	}
			    if(list.get(arg2).equals("�û�Ȩ�޹���")){
			    	
			    }
                if(list.get(arg2).equals(getString(R.string.write_log))){
                	intent = new Intent(CdActivity.this,RegActivity.class);
    			   	startActivity(intent);
			    }
                if(list.get(arg2).equals("�û���Ϣ��ѯ")){
			    	
			    }
                if(list.get(arg2).equals("�û���Ϣ�޸�")){
			    	
			    }
                if(list.get(arg2).equals("�û�����/ע��")){
			    	
			    }
                if(list.get(arg2).equals("�û���������")){
			    	
			    }
                if(list.get(arg2).equals(getString(R.string.write_log))){
                	intent = new Intent(CdActivity.this,WriteLogActivity.class);
                	startActivity(intent);
                }
		    }
		});	
	}
	@Override  
    public boolean onKeyDown(int keyCode, KeyEvent event)  
    {  
		Toast.makeText(getApplicationContext(), "key down", Toast.LENGTH_LONG).show();;
        if (keyCode == KeyEvent.KEYCODE_BACK )  
        {  
        	new AlertDialog.Builder(CdActivity.this).setTitle("确认退出吗？") 
            .setIcon(android.R.drawable.ic_dialog_info) 
            .setPositiveButton("确定", new DialogInterface.OnClickListener() { 
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                // 点击“确认”后的操作 
                	System.exit(0);
                	finish(); 
                } 
            }) 
            .setNegativeButton("返回", new DialogInterface.OnClickListener() { 
                @Override 
                public void onClick(DialogInterface dialog, int which) { 
                // 点击“返回”后的操作,这里不设置没有任何操作 
                } 
            }).show(); 
  
        }  
          
        return false;  
          
    }  
}
