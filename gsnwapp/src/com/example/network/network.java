package com.example.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;
import android.util.Log;

/*
 * 如果直接在主线程上进行网络连接，非常可能因为执行网络而使得UI线程阻塞
 * 因此，network类应该继承AsyncTask类，在后台执行网络连接和数据发送
 * 
 */

public class network extends AsyncTask{
	//直接让connection返回string
	public static String connection(String url,List<NameValuePair> mlist){
    	    HttpParams params = new BasicHttpParams();//����һ��������������洢���õĸ�������  
	        HttpProtocolParams.setContentCharset(params, "UTF-8");// ������Ϣ���õ��ַ�  
	        HttpProtocolParams.setVersion(params,HttpVersion.HTTP_1_1);//����httpЭ��汾  
	        ConnManagerParams.setTimeout(params, 1000);//�����ӳ���ȡ���ӵĳ�ʱʱ��  
	        //ConnManagerParams.setMaxTotalConnections(params, 200);//����������ӳ���������� ,���ܲ���������������Լ��������  
	              
	        //ConnPerRoute connPerRoute = new ConnPerRouteBean(100);//ÿ̨�������������������Լ��������  
	        //ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);//ÿ̨�������������������Լ��������  
	        HttpConnectionParams.setConnectionTimeout(params, 5000);// �������ӳ�ʱʱ��  
	        HttpConnectionParams.setSoTimeout(params, 5000);// ���÷�������Ӧ��ʱʱ��  
	        //����HttpClient֧��HTTp��HTTPS����Э��    
	        SchemeRegistry schReg = new SchemeRegistry();    
	        schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));//httpЭ��Ĭ�϶˿���80    
	        schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));//httpsЭ��Ĭ�϶˿���443    
	        //ʹ���̰߳�ȫ�����ӹ���������HttpClient    
	        ClientConnectionManager conman = new ThreadSafeClientConnManager(params, schReg);  
	        HttpClient client = new DefaultHttpClient(conman, params);//����һ����������Ŀͻ���  
	        HttpPost post = new HttpPost(url);//����Post�������  
	        
	        try {
				post.setEntity(new UrlEncodedFormEntity(mlist,"UTF-8"));
				Log.v("in class network connection method", url);
				try {
					HttpResponse response =client.execute(post);
					HttpEntity entity = response.getEntity();
					return EntityUtils.toString(entity, "UTF-8");
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return null;
       }

	@Override
	protected Object doInBackground(Object... params) {
		// TODO Auto-generated method stub
		if(params!=null && params.length>1){
			String url=(String)params[0];
			List<NameValuePair> mList=(List<NameValuePair>)params[1];
			return connection(url, mList);
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}
}
