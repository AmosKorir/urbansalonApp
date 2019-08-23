package com.app.remote.data.di;

import android.util.Log;
import com.app.remote.data.BuildConfig;
import com.app.remote.data.api.CustomerApi;
import com.app.remote.data.api.SalonApi;
import com.app.remote.domain.constants.DIConstants;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Module public class ApiModule {

/*provide all elements to be used in all api calls and iterfaces*/

    @Provides
    @Named(DIConstants.DEFAULT) public OkHttpClient provideDefaultOkHttpClient() {
        return new OkHttpClient.Builder().connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
            .addInterceptor(new Interceptor() {
              @Override public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Response response = chain.proceed(original);
                Log.d("MyApp", "Code : " + response.code());
                ResponseBody responseBody = response.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // request the entire body.
                Buffer buffer = source.buffer();
                // clone buffer before reading from it
                String responseBodyString = buffer.clone().readString(Charset.forName("UTF-8"));
                Log.d("Code", responseBodyString);
                if (response.code() == 422) {
                  Log.d("MyApp", "Code : " + response.body().toString());
                  return response;
                }
                return response;
              }
            })
                .build();
    }

    @Provides @Named(DIConstants.DEFAULT)
    public Retrofit provideDefaultRetrofit(Gson gson, @Named(DIConstants.DEFAULT) OkHttpClient okHttpClient) {
        return new Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

  @Provides CustomerApi providesCustomerApi(@Named(DIConstants.DEFAULT)Retrofit retrofit){
    return retrofit.create(CustomerApi.class);
  }
  @Provides SalonApi providesSalonApi(@Named(DIConstants.DEFAULT)Retrofit retrofit){
    return retrofit.create(SalonApi.class);
  }

}
