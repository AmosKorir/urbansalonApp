package com.app.remote.salon.urban;

import android.app.Application;

import com.app.remote.salon.urban.di.activity.ActivityComponent;
import com.app.remote.salon.urban.di.activity.ActivityModule;
import com.app.remote.salon.urban.di.app.AndroidModule;
import com.app.remote.salon.urban.di.app.ApplicationComponent;
import com.app.remote.salon.urban.di.app.ApplicationModule;
import com.app.remote.salon.urban.di.app.DaggerApplicationComponent;
import com.app.remote.salon.urban.ui.activities.BaseActivity;
import com.facebook.stetho.Stetho;


/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class MyApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .androidModule(new AndroidModule(this))
                .build();
        applicationComponent.inject(this);

        Stetho.initializeWithDefaults(this);


    }

    public ActivityComponent getActivityInjector(BaseActivity baseActivity) {
        return applicationComponent.activityComponentBuilder().activityModule(new ActivityModule(baseActivity)).build();
    }
}
