package com.ao.androidchallengejob.ui.AdapterView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder> {

	private static final String TAG = FavouriteAdapter.class.getSimpleName();

	private Context context;
	protected List<TeamsItem> items;
	public OnItemClickListenerView onItemClickListener;


	public FavouriteAdapter(Context context, List<TeamsItem> items) {
		this.context = context;
		this.items = items;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_view_favourite, parent, false);
		ViewHolder viewHolder = new ViewHolder(view);
		return viewHolder;
	}


	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {

		TeamsItem item = items.get(position);

		holder.teamName_Favourite.setText(item.getName());
		holder.theirVenue_Favourite.setText(item.getVenue());
		holder.teamPlayers_Favourite.setText(item.getShortName());
		holder.nameColors_Favourite.setText(item.getClubColors());
		holder.teamWebsite_Favourite.setText(item.getWebsite());

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
					public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
						holder.progressBar.setVisibility(View.GONE);
						return false;
					}
				})
 				.transition(DrawableTransitionOptions.withCrossFade())
				.into(holder.img_Color_Favourite);


		if (onItemClickListener != null) {
			holder.itemView.setOnClickListener(v -> {

				onItemClickListener.onItemClick(position);
			});
		}


	}
	public void changeDataShow(List<TeamsItem> lists) {
		this.items = lists;
		notifyDataSetChanged();
	}

	@Override
	public int getItemCount() {
		//return iterator
		return items == null ? 0 : items.size();

	}

	public void setOnItemClickListener(OnItemClickListenerView onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public interface OnItemClickListenerView {
		void onItemClick(int position);
	}


	public static class ViewHolder extends RecyclerView.ViewHolder {
		//ViewHolder
		TextView teamName_Favourite, teamWebsite_Favourite, theirVenue_Favourite, teamPlayers_Favourite, nameColors_Favourite;
		ImageView img_Color_Favourite;


		ProgressBar progressBar;


		public ViewHolder(View itemView) {
			super(itemView);

			teamName_Favourite = itemView.findViewById(R.id.TeamName_favour);
			teamWebsite_Favourite = itemView.findViewById(R.id.TeamWebsite_favourite);
			theirVenue_Favourite = itemView.findViewById(R.id.TheirVenue_favourite);
			teamPlayers_Favourite = itemView.findViewById(R.id.TeamPlayers_favourite);
			nameColors_Favourite = itemView.findViewById(R.id.Colors_favourite);

			img_Color_Favourite = itemView.findViewById(R.id.img_Color_favourite);

			progressBar = itemView.findViewById(R.id.prograss_load_photo_F);

		}
	}
}