package com.app.remote.salon.urban.di.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;

import com.app.remote.domain.constants.DIConstants;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Module
public class AndroidModule {
    private final Context context;

    public AndroidModule(Context context) {
        this.context = context;
    }


    @Provides
    public SharedPreferences provideSharedPreferences(@Named(DIConstants.APP) Context context) {
        return context.getSharedPreferences("hacker_news_sharedprefs", Context.MODE_PRIVATE);
    }

    @Provides public Resources provideResources(@Named(DIConstants.APP) Context context) {
        return context.getResources();
    }
}
