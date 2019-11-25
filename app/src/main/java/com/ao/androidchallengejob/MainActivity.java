package com.ao.androidchallengejob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ao.androidchallengejob.Api.FootballDataClint;
import com.ao.androidchallengejob.repo.RepositoryData;
import com.ao.androidchallengejob.repo.TeamsItem;
import com.ao.androidchallengejob.ui.FootballAdapter;
import com.ao.androidchallengejob.viewModel.HomeViweModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
	HomeViweModel homeViweModel;
	List<TeamsItem> listTeams = new ArrayList<>();
	FootballAdapter footballAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		homeViweModel = ViewModelProviders.of(this).get(HomeViweModel.class);


		homeViweModel.getFootballData();
		subscribeToLiveData();
initView();
initListener();


	}

	private void initView() {
		RecyclerView recycler = findViewById(R.id.recyc);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.setItemAnimator(new DefaultItemAnimator());
		recycler.setNestedScrollingEnabled(false);

		footballAdapter = new FootballAdapter(listTeams,this);
		recycler.setAdapter(footballAdapter);
		recycler.setVisibility(View.VISIBLE);

		footballAdapter.notifyDataSetChanged();



	}

	private void subscribeToLiveData() {
		homeViweModel.teamsItem.observe(this, new Observer<List<TeamsItem>>() {
			@Override
			public void onChanged(List<TeamsItem> teamsItems) {
				/*Toast.makeText(MainActivity.this, "ddd  "+teamsItems, Toast.LENGTH_SHORT).show();
 				testLog(teamsItems.toString());
 				*/
				if (teamsItems != null) {

				footballAdapter.changeData(teamsItems);
			}
 			}
		});
	}
	private void initListener(){
		footballAdapter.setOnItemClickListener(new FootballAdapter.OnItemClickListenerView() {
			@Override
			public void onItemClick(int position, TeamsItem item) {
				Toast.makeText(MainActivity.this, " "+position, Toast.LENGTH_SHORT).show();

				Intent intent = new Intent(MainActivity.this, DetailActivity.class);

				TeamsItem teamsItem = listTeams.get(position);

				intent.putExtra("teamName",teamsItem.getName());
				intent.putExtra("webSite",teamsItem.getWebsite());
				intent.putExtra("venue", teamsItem.getVenue());
				intent.putExtra("nameColor",teamsItem.getClubColors());
				intent.putExtra("shortName",teamsItem.getShortName());
				intent.putExtra("color_Image",teamsItem.getCrestUrl());

				startActivity(intent);

			}
		});
	}


	private void testLog(String log) {
		Log.e("ooo", log);
	}
}
