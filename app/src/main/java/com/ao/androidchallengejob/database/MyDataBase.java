package com.ao.androidchallengejob.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ao.androidchallengejob.repo.TeamsItem;

@Database(exportSchema = false, entities = {TeamsItem.class}, version = 5)
public abstract class MyDataBase extends RoomDatabase {

	private static final String NAME_DADABASE = "AddDataBase";
	private static MyDataBase myDataBase;

	public abstract database sourcesDao();

	public static MyDataBase getInstance() {
		if (myDataBase == null)

			throw new NullPointerException("database is null try to call init first");


		return myDataBase;

	}

	public static void initData(Context context) {

		myDataBase = Room.databaseBuilder(context, MyDataBase.class,
				NAME_DADABASE)
				.fallbackToDestructiveMigration()
				//.allowMainThreadQueries()
				.build();


	}


}
