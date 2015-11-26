package com.example.gsnwapp;

import android.app.Application;

/*
 * 为了能在多个Activity之间共享数据（即让每个Activity都知道当前是哪个用户）
 * 使用getApplicationContext()的方法即可获取用户状态类。
 * 需要修改 AndroidManiFest文件
 */
public class UserState extends Application{

	String userId=null;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
