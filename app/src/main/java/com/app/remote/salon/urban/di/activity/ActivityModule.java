package com.app.remote.salon.urban.di.activity;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;

import com.app.remote.domain.constants.DIConstants;
import com.app.remote.salon.urban.di.adapter.AdapterComponent;
import com.app.remote.salon.urban.ui.activities.BaseActivity;
import com.app.remote.presentation.di.PresenterModule;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Module(includes = PresenterModule.class, subcomponents = { AdapterComponent.class })
public class ActivityModule {
    private final BaseActivity baseActivity;
    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @Named(DIConstants.ACTIVITY) public Context provideActivityContext() {
        return baseActivity;
    }

    @Provides public BaseActivity provideActivity() {
        return baseActivity;
    }

    @Provides public LayoutInflater provideLayoutInflater(@Named(DIConstants.ACTIVITY) Context context) {
        return LayoutInflater.from(context);
    }

    @Provides public FragmentManager provideFragmentManager(BaseActivity activity) {
        return activity.getSupportFragmentManager();
    }
}