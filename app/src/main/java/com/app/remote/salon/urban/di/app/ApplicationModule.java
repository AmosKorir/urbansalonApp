package com.app.remote.salon.urban.di.app;

import android.content.Context;

import com.app.remote.domain.constants.DIConstants;
import com.app.remote.salon.urban.MyApplication;
import com.app.remote.salon.urban.di.activity.ActivityComponent;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Module(includes = AndroidModule.class, subcomponents = { ActivityComponent.class }) public class ApplicationModule {
    private final MyApplication myApplication;

    public ApplicationModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Named(DIConstants.APP) public Context provideAppContext() {
        return myApplication;
    }

    @Provides public Gson provideGson() {
        return new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
    }
}