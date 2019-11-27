package com.ao.androidchallengejob.database;

import androidx.room.Dao;
 import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.ao.androidchallengejob.repo.TeamsItem;
import java.util.List;

@Dao
public interface  database {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	void  addItem(List<TeamsItem> itemList);


	@Query("select * from teamsitem")
	  List<TeamsItem> getAllitem();

/*

	@Query("SELECT EXISTS (SELECT 1 FROM teamsitem WHERE id=:id)")
	public int isFavorite(int id);
*/

}
