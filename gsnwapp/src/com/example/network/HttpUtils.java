package com.example.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import android.util.Log;

public class HttpUtils{

	private static String LOGIN_PATH="";
	private URL url=null;
	
	public HttpUtils(){
		super();
		
	}
	/*
	 * 
	 * return the string
	 */
	public String sendPostMessage(Map<String, String> params, String encode, String str_url){
		InputStream is = null;
		OutputStream os=null;
		InputStream keyIs=null;
		try {
		StringBuilder stringBuilder=new StringBuilder();
		if(params!=null && !params.isEmpty()){
			for (Map.Entry<String, String> entry : params.entrySet()) {  
                    stringBuilder  
                            .append(entry.getKey())  
                            .append("=")  
                            .append(URLEncoder.encode(entry.getValue(), encode))  
                            .append("&");  
            }  
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);  
            Log.v("StringBuilder", stringBuilder.toString());
			byte[]buffer=stringBuilder.toString().getBytes();
	        URL url = new URL(str_url);
	        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setDoOutput(true);
	        conn.setDoInput(true);
	        //conn.setRequestProperty("Content-type", "application/x-java-serialized-object");   
	        conn.setRequestMethod("POST");
	        Log.v("connect", conn.toString());
	        
	        conn.connect();
	        
	        keyIs=conn.getInputStream();
	        byte[] mPublicKeyBytes=new byte[1024];
	        
	        os=conn.getOutputStream();
	        os.write(buffer);
	        os.flush();
	        os.close();
	        
	        int responseCode=conn.getResponseCode();
	        
	        if(responseCode!=-1){
	        	return getInputStream(conn.getInputStream(), encode);
	        }
			}
		}catch (IOException ioe){
			Log.v("ioe", ioe.getMessage());
		}
		return null;
	}
	
	/*
	public String sendPostMessage(Map<String, String> params, String encode, String str_url) throws MalformedURLException{
		url=new URL(str_url);
		StringBuilder stringBuilder=new StringBuilder();
		if(params!=null && !params.isEmpty()){
			for (Map.Entry<String, String> entry : params.entrySet()) {  
                try {  
                    stringBuilder  
                            .append(entry.getKey())  
                            .append("=")  
                            .append(URLEncoder.encode(entry.getValue(), encode))  
                            .append("&");  
                } catch (UnsupportedEncodingException e) {  
                    e.printStackTrace();  
                }  
            }  
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);  
            Log.v("StringBuilder", stringBuilder.toString());
		}
		 try {  
             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();  
             urlConnection.setConnectTimeout(3000);  
             urlConnection.setRequestMethod("POST"); // 以post请求方式提交  
             urlConnection.setDoInput(true); // 读取数据  
             urlConnection.setDoOutput(true); // 向服务器写数据  
             // 获取上传信息的大小和长度  
             byte[] myData = stringBuilder.toString().getBytes();  
             // 设置请求体的类型是文本类型,表示当前提交的是文本数据  
             urlConnection.setRequestProperty("Content-Type",  
                     "application/x-www-form-urlencoded");
             urlConnection.setRequestProperty("Content-Length",  
                     String.valueOf(myData.length));  
             // 获得输出流，向服务器输出内容  
             OutputStream outputStream = urlConnection.getOutputStream();  
             // 写入数据  
             outputStream.write(myData, 0, myData.length);  
             outputStream.close();
             
          // 获得服务器响应结果和状态码  
             int responseCode = urlConnection.getResponseCode();  
             Log.v("responseCode", ""+responseCode);
             if (responseCode == 200) {  
                 // 取回响应的结果  
                 return getInputStream(urlConnection.getInputStream(), encode);  
             }  
		 }catch(IOException ioe){
			 Log.v("ioe", ioe.getMessage());
			 Log.v("sendPostMessage", "Error");
		 }
		return null;
	}
	*/
	public String getInputStream(InputStream inputStream, String encode){
		// 内存流  
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024];
        int len = 0;  
        String result = null;  
        if (inputStream != null) {  
            try {  
                while ((len = inputStream.read(data)) != -1) {  
                    byteArrayOutputStream.write(data, 0, len);  
                }  
                result = new String(byteArrayOutputStream.toByteArray(), encode);  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;
	}
}
