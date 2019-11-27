package com.ao.androidchallengejob;

import android.app.Application;

import com.ao.androidchallengejob.database.MyDataBase;

public class App extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		MyDataBase.initData(this);


	}
}
