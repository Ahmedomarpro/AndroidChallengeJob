package com.ao.androidchallengejob.ui;

   import android.annotation.SuppressLint;
   import android.content.Intent;
   import android.os.Bundle;
   import android.view.View;
   import android.webkit.WebView;
   import android.webkit.WebViewClient;
 import android.widget.ImageView;
 import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


 import com.ao.androidchallengejob.R;
 import com.ao.androidchallengejob.utils.Utils;
 import com.bumptech.glide.Glide;
 import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
 import com.bumptech.glide.request.RequestOptions;
 import com.google.android.material.appbar.AppBarLayout;
 import com.google.android.material.appbar.CollapsingToolbarLayout;

public class DetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {
	private TextView title_T, venue, nameColor, shortName, appbar_title, appbar_subtitle;
	private ImageView show_image;

	private String i_title, i_venue, i_nameColor, i_shortName, i_url, i_color_Image;

	private Toolbar toolbar;


	private AppBarLayout appBarLayout;
	private boolean isHideToolbarView = false;

 	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);




		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setTitle("");
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
		collapsingToolbarLayout.setTitle("");

		appBarLayout = findViewById(R.id.appbar);
		appBarLayout.addOnOffsetChangedListener(this);



		initFind();

		getIntentView();





	}
	private void initFind(){
		title_T = findViewById(R.id.titleTop);
		venue = findViewById(R.id.nameVenue);
		nameColor = findViewById(R.id.clubColor);
		shortName = findViewById(R.id.shortName);

		show_image = findViewById(R.id.backdrop);

		appbar_title = findViewById(R.id.title_on_appbar);

		appbar_subtitle =findViewById(R.id.subtitle_on_appbar);


	}
	@SuppressLint("CheckResult")
	private void getIntentView(){

		Intent intent= getIntent();


		i_title = intent.getStringExtra("teamName");
		i_shortName = intent.getStringExtra("shortName");
		i_venue = intent.getStringExtra("venue");
		i_nameColor = intent.getStringExtra("nameColor");

		i_color_Image = intent.getStringExtra("img_Color");

		RequestOptions requestOptions = new RequestOptions();
		requestOptions.error(Utils.getRandomDrawbleColor());

		Glide.with(this)
				.load(i_color_Image)
				.apply(requestOptions)
				.transition(DrawableTransitionOptions.withCrossFade())
				.into(show_image);



		title_T.setText(i_title);
		venue.setText(i_venue);
		nameColor.setText(i_nameColor);
		shortName.setText(i_shortName);

		appbar_subtitle.setText(i_shortName);
		appbar_title.setText(i_title);

		i_url = intent.getStringExtra("getWebsite");
		initWebView(i_url);

	}

	@SuppressLint("SetJavaScriptEnabled")
	private void initWebView(String url) {

		WebView webView = findViewById(R.id.webView);

		webView.getSettings().setLoadsImagesAutomatically(true);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setDomStorageEnabled(true);
		webView.getSettings().setSupportZoom(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setDisplayZoomControls(false);
		webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
		webView.setWebViewClient(new WebViewClient());

		webView.requestFocusFromTouch();

		webView.loadUrl(url);

	}


	@Override
	public void onOffsetChanged(AppBarLayout appBarLayout, int i) {

		int maxScroll = appBarLayout.getTotalScrollRange();
		float percentage = (float) Math.abs(i) / (float) maxScroll;

		if (percentage == 1f && isHideToolbarView) {
			isHideToolbarView = !isHideToolbarView;

		} else if (percentage < 1f && !isHideToolbarView) {
			isHideToolbarView = !isHideToolbarView;
		}


	}


}
