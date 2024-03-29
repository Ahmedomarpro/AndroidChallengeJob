package com.ao.androidchallengejob.repo;

import java.util.List;
 import com.google.gson.annotations.SerializedName;

 public class RepositoryData{

	@SerializedName("teams")
	private List<TeamsItem> teams;

	@SerializedName("count")
	private int count;

	@SerializedName("season")
	private Season season;

	@SerializedName("competition")
	private Competition competition;

	@SerializedName("filters")
	private Filters filters;

	public void setTeams(List<TeamsItem> teams){
		this.teams = teams;
	}

	public List<TeamsItem> getTeams(){
		return teams;
	}

	public void setCount(int count){
		this.count = count;
	}

	public int getCount(){
		return count;
	}

	public void setSeason(Season season){
		this.season = season;
	}

	public Season getSeason(){
		return season;
	}

	public void setCompetition(Competition competition){
		this.competition = competition;
	}

	public Competition getCompetition(){
		return competition;
	}

	public void setFilters(Filters filters){
		this.filters = filters;
	}

	public Filters getFilters(){
		return filters;
	}

	@Override
 	public String toString(){
		return 
			"RepositoryData{" + 
			"teams = '" + teams + '\'' + 
			",count = '" + count + '\'' + 
			",season = '" + season + '\'' + 
			",competition = '" + competition + '\'' + 
			",filters = '" + filters + '\'' + 
			"}";
		}
}