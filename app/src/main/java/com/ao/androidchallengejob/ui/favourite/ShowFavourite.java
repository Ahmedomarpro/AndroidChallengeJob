package com.ao.androidchallengejob.ui.favourite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.ao.androidchallengejob.R;
import com.ao.androidchallengejob.database.MyDataBase;
import com.ao.androidchallengejob.repo.TeamsItem;
import com.ao.androidchallengejob.ui.AdapterView.FavouriteAdapter;

import java.util.ArrayList;
import java.util.List;

public class ShowFavourite extends AppCompatActivity {

	RecyclerView showRecyc ;
	FavouriteAdapter favouriteAdapter;
	List<TeamsItem> showAddfavourite = new ArrayList<>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_favourite);

		initView();
	}
 public void  initView(){

	  showRecyc = findViewById(R.id.showFavourite);
	  showRecyc.setLayoutManager(new LinearLayoutManager(this));
	 showRecyc.setItemAnimator(new DefaultItemAnimator());
	 showRecyc.setNestedScrollingEnabled(false);

	 favouriteAdapter = new FavouriteAdapter(ShowFavourite.this,showAddfavourite);
	 showRecyc.setAdapter(favouriteAdapter);
	 showRecyc.setVisibility(View.VISIBLE);

	 favouriteAdapter.changeDataShow(showAddfavourite);







 }
	public void getAll() {
		Thread threadAll = new Thread() {
			@Override
			public void run() {
				  List<TeamsItem> sourcesItem = MyDataBase.getInstance().sourcesDao().getAllitem();
				favouriteAdapter.changeDataShow(sourcesItem);
			}
		};
		threadAll.start();

	}

}
