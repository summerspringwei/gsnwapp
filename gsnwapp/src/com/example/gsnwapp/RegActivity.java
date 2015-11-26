package com.example.gsnwapp;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.example.network.network;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegActivity extends Activity{
	String url=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regis);

		//read URL from config file
		url=getString(R.string.ip_server).concat(getString(R.string.url_regis));
		Button submit = (Button)findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				EditText zybh = (EditText)findViewById(R.id.editText1);
				String zyb = zybh.getText().toString();
				EditText zyxm = (EditText)findViewById(R.id.editText4);
				String zyx = zyxm.getText().toString();
				EditText psd = (EditText)findViewById(R.id.editText3);
				String pssd = psd.getText().toString();
				EditText yxdj = (EditText)findViewById(R.id.editText5);
				String yxd = yxdj.getText().toString();
				EditText byyx = (EditText)findViewById(R.id.editText2);
				String byy = byyx.getText().toString();
				EditText zyjb = (EditText)findViewById(R.id.editText6);
				String zyj = zyjb.getText().toString();
				EditText zyzw = (EditText)findViewById(R.id.editText7);
				String zyz = zyzw.getText().toString();
				EditText xmjl = (EditText)findViewById(R.id.editText8);
				String xmj = xmjl.getText().toString();
				EditText zy = (EditText)findViewById(R.id.editText9);
				String zyy = zy.getText().toString();
				EditText sfbz = (EditText)findViewById(R.id.editText10);
				String sfb = sfbz.getText().toString();
				EditText zc = (EditText)findViewById(R.id.editText11);
				String zcc = zc.getText().toString();
				EditText rzrq = (EditText)findViewById(R.id.editText12);
				String rzr = rzrq.getText().toString();
				EditText zzrq = (EditText)findViewById(R.id.editText13);
				String zzr = zzrq.getText().toString();
				EditText jdr = (EditText)findViewById(R.id.editText14);
				String jd = jdr.getText().toString();
				EditText zjhm = (EditText)findViewById(R.id.editText15);
				String zjh = zjhm.getText().toString();
				EditText cwcs = (EditText)findViewById(R.id.editText16);
				String cwc = cwcs.getText().toString();
				EditText yxbz = (EditText)findViewById(R.id.editText17);
				String yxb = yxbz.getText().toString();
				EditText zjlb = (EditText)findViewById(R.id.editText18);
				String zjl = zjlb.getText().toString();
				EditText cjsj = (EditText)findViewById(R.id.editText19);
				String cjs = cjsj.getText().toString();
				EditText ygzh = (EditText)findViewById(R.id.editText20);
				String ygz = ygzh.getText().toString();
				NameValuePair zybh1 = new BasicNameValuePair("zybh",zyb);
				NameValuePair zyxm1 = new BasicNameValuePair("zyxm",zyx);
				NameValuePair psd1 = new BasicNameValuePair("psd",pssd);
				NameValuePair yxdj1 = new BasicNameValuePair("yxdj",yxd);
				NameValuePair byyx1 = new BasicNameValuePair("byyx",byy);
				NameValuePair zyjb1 = new BasicNameValuePair("zyjb",zyj);
				NameValuePair zyzw1 = new BasicNameValuePair("zyzw",zyz);
				NameValuePair xmjl1 = new BasicNameValuePair("xmjl",xmj);
				NameValuePair zy1 = new BasicNameValuePair("zy",zyy);
				NameValuePair sfbz1 = new BasicNameValuePair("sfbz",sfb);
				NameValuePair zc1 = new BasicNameValuePair("zc",zcc);
				NameValuePair rzrq1 = new BasicNameValuePair("rzrq",rzr);
				NameValuePair zzrq1 = new BasicNameValuePair("zzrq",zzr);
				NameValuePair jdr1 = new BasicNameValuePair("jdr",jd);
				NameValuePair zjhm1 = new BasicNameValuePair("zjhm",zjh);
				NameValuePair cwcs1 = new BasicNameValuePair("cwcs",cwc);
				NameValuePair yxbz1 = new BasicNameValuePair("yxbz",yxb);
				NameValuePair zjlb1 = new BasicNameValuePair("zjlb",zjl);
				NameValuePair cjsj1 = new BasicNameValuePair("cjsj",cjs);
				NameValuePair ygzh1 = new BasicNameValuePair("ygzh",ygz);
				List<NameValuePair> mlist = new ArrayList<NameValuePair>();
				mlist.add(zybh1);
				mlist.add(zyxm1);
				mlist.add(psd1);
				mlist.add(yxdj1);
				mlist.add(byyx1);
				mlist.add(zyjb1);
				mlist.add(zyzw1);
				mlist.add(xmjl1);
				mlist.add(zy1);
				mlist.add(sfbz1);
				mlist.add(zc1);
				mlist.add(rzrq1);
				mlist.add(zzrq1);
				mlist.add(jdr1);
				mlist.add(zjhm1);
				mlist.add(cwcs1);
				mlist.add(yxbz1);
				mlist.add(zjlb1);
				mlist.add(cjsj1);
				mlist.add(ygzh1);
				String response = network.connection(url, mlist);
				
				if(response.equals("01"));
				{
					
				}
				
			}
		});
}}
