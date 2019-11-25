package com.ao.androidchallengejob.Api;

import com.ao.androidchallengejob.repo.RepositoryData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiCompetitions {

	@GET("competitions/2019/teams")
	Call<RepositoryData> repositoryDataCall (@Header("X-Auth-Token")  String auth);
}
