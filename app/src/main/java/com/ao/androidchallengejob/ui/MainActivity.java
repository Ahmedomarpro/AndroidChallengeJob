package com.ao.androidchallengejob.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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
import com.ao.androidchallengejob.R;
import com.ao.androidchallengejob.repo.RepositoryData;
import com.ao.androidchallengejob.repo.TeamsItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

 	FootballAdapter footballAdapter;
	public static final String key = "d2f43d9ec9f34ed694dee62ad699e242";
	List<TeamsItem> listTeams = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		subscribeToLiveData();


	}


	private void subscribeToLiveData() {
		RecyclerView recycler = findViewById(R.id.recyc);
		recycler.setLayoutManager(new LinearLayoutManager(this));
		recycler.setItemAnimator(new DefaultItemAnimator());
		recycler.setNestedScrollingEnabled(false);


		FootballDataClint.getServiceApi().repositoryDataCall(key).enqueue(new Callback<RepositoryData>() {
			@Override
			public void onResponse(Call<RepositoryData> call, Response<RepositoryData> response) {

				if (response.isSuccessful() && response.body().getTeams() != null) {

					if (listTeams.isEmpty()) {
						listTeams.clear();
					}
					listTeams = response.body().getTeams();

					footballAdapter = new FootballAdapter(listTeams,MainActivity.this);
					recycler.setAdapter(footballAdapter);
					recycler.setVisibility(View.VISIBLE);
					footballAdapter.changeData(listTeams);
					footballAdapter.notifyDataSetChanged();



  				}
				initListener();

			}

			@Override
			public void onFailure(Call<RepositoryData> call, Throwable t) {
				Toast.makeText(MainActivity.this, "Network failure, Please Try Again!!!", Toast.LENGTH_SHORT).show();

			}
		});
  	}

	private void initListener() {


		footballAdapter.setOnItemClickListener(new FootballAdapter.OnItemClickListenerView() {
			@Override
			public void onItemClick(int position, TeamsItem item) {
				Intent intent = new Intent(MainActivity.this, DetailActivity.class);

				//TeamsItem teamsItem = listTeams.get(position);


				intent.putExtra("teamName",item.getName());
				intent.putExtra("venue", item.getVenue());
				intent.putExtra("nameColor",item.getClubColors());
				intent.putExtra("shortName",item.getShortName());
				intent.putExtra("img_Color",item.getCrestUrl());
				intent.putExtra("getWebsite",item.getWebsite());

				startActivity(intent);


			}
		});


	}


	private void testLog(String log) {
		Log.e("ooo", log);
	}
}
