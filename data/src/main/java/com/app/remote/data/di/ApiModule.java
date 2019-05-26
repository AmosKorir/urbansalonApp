package com.app.remote.data.di;

import com.app.remote.data.BuildConfig;
import com.app.remote.domain.constants.DIConstants;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
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
        //provide api interface ...api calls//

}
