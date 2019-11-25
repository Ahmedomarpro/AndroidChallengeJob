package com.ao.androidchallengejob.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FootballDataClint {

	public static final String BASE_URL = "https://api.football-data.org/v2/";
	private static Retrofit retrofit;

	public FootballDataClint() {
		//Singleton pattern

	}


	public static Retrofit getInstance() {
		if (retrofit == null) {

			retrofit = new Retrofit.Builder()

					//.client(okHttpClient)
					.baseUrl(BASE_URL)
					.addConverterFactory(GsonConverterFactory.create())
					.build();


		}
		return retrofit;
	}

	public static ApiCompetitions getServiceApi() {
		return getInstance().create(ApiCompetitions.class);
	}


			/*OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
			@NotNull
			@Override
			public Response intercept(@NotNull Chain chain) throws IOException {
				Request original  = chain.request();
				Request.Builder builder = original.newBuilder()
						.addHeader("X-Auth-Token",token)
						.method(original.method(),original.body());
				Request request = builder.build();
				Log.d("kkkkkl",request.toString());
				return chain.proceed(request);
			}
		}).build();
*/

}
