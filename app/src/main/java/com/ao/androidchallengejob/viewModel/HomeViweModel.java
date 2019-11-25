package com.ao.androidchallengejob.viewModel;

import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ao.androidchallengejob.Api.FootballDataClint;
import com.ao.androidchallengejob.repo.RepositoryData;
import com.ao.androidchallengejob.repo.TeamsItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViweModel extends ViewModel {

	public static final String key = "d2f43d9ec9f34ed694dee62ad699e242";

	public MutableLiveData <List<TeamsItem>> teamsItem = new MutableLiveData<>();
	MutableLiveData<String> message = new MutableLiveData<>();


	public void getFootballData(){
		FootballDataClint.getServiceApi().repositoryDataCall(key).enqueue(new Callback<RepositoryData>() {
			@Override
			public void onResponse(Call<RepositoryData> call, Response<RepositoryData> response) {

				if (response.body() != null && response.isSuccessful()){
					teamsItem.setValue(response.body().getTeams());
				}
			}


			@Override
			public void onFailure(Call<RepositoryData> call, Throwable t) {
				Log.e("ee",t.getLocalizedMessage());
				message.setValue(t.getLocalizedMessage());


			}
		});
	}
}
