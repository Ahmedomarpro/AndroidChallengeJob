package com.ao.androidchallengejob.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ao.androidchallengejob.R;
import com.ao.androidchallengejob.repo.TeamsItem;
import com.ao.androidchallengejob.utils.Utils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;
import java.util.Random;

public class FootballAdapter extends RecyclerView.Adapter<FootballAdapter.ViewHolder> {


	//private Context context;
	public List<TeamsItem> list;
	private Context context;

	public FootballAdapter(List<TeamsItem> list, Context context) {
		this.list = list;
		this.context = context;
	}

	public OnItemClickListenerView onItemClickListener;


	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}


 	@SuppressLint("CheckResult")
	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		TeamsItem item = list.get(position);

		holder.teamName.setText(item.getName());
		holder.theirVenue.setText(item.getVenue());
		holder.teamPlayers.setText(item.getShortName());
		holder.nameColors.setText(item.getClubColors());
		holder.teamWebsite.setText(item.getWebsite());

		/*holder.saveBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(context, ""+item, Toast.LENGTH_SHORT).show();
			}
		});*/

		//Image
		RequestOptions requestOptions = new RequestOptions();
		requestOptions.placeholder(Utils.getRandomDrawbleColor());
		requestOptions.error(Utils.getRandomDrawbleColor());
		requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
		requestOptions.centerCrop();

		Glide.with(context)
				.load(item.getCrestUrl())
				.apply(requestOptions)
				.listener(new RequestListener<Drawable>() {
					@Override
					public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {

						holder.progressBar.setVisibility(View.GONE);

						return false;
					}

					@Override
					public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource 						dataSource, boolean isFirstResource) {
						holder.progressBar.setVisibility(View.GONE);
						return false;
					}
				})
				/*.transition(DrawableTransitionOptions.withCrossFade())
				.into(holder.imageView);*/
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(holder.img_Color);


				holder.itemView.setOnClickListener(v->{
					if (onItemClickListener != null){
						onItemClickListener.onItemClick(position,item);
					}
				});

	}


	@Override
	public int getItemCount() {
		//return iterator
		return list == null ? 0 : list.size();

	}

	public void setOnItemClickListener(OnItemClickListenerView onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public interface OnItemClickListenerView {
		void onItemClick(int position, TeamsItem item);
	}

	public void changeData(List<TeamsItem>lists){
		this.list=lists;
		notifyDataSetChanged();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		//ViewHolder
		TextView teamName, teamWebsite, theirVenue, teamPlayers,nameColors;
		ImageView img_Color;

		Button saveBtn;

		ProgressBar progressBar;

		public ViewHolder(View itemView) {
			super(itemView);

			saveBtn =itemView.findViewById(R.id.saveBtn);

			teamName = itemView.findViewById(R.id.TeamName);
			teamWebsite = itemView.findViewById(R.id.TeamWebsite);
			theirVenue = itemView.findViewById(R.id.TheirVenue);
			teamPlayers = itemView.findViewById(R.id.TeamPlayers);
			nameColors = itemView.findViewById(R.id.Colors);

			img_Color = itemView.findViewById(R.id.img_Color);

			progressBar = itemView.findViewById(R.id.prograss_load_photo);


		}
	}


}