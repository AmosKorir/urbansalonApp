package com.app.remote.salon.urban.di.activity;

import com.app.remote.salon.urban.di.adapter.AdapterComponent;
import com.app.remote.salon.urban.di.fragment.FragmentComponent;
import com.app.remote.salon.urban.ui.activities.BaseActivity;
import com.app.remote.salon.urban.ui.activities.MainDashBoadActivity;
import com.app.remote.salon.urban.ui.activities.SalonDetailsActivity;
import dagger.Subcomponent;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@ActivityScope @Subcomponent(modules = { ActivityModule.class })
public interface ActivityComponent {
  FragmentComponent.Builder fragmentBuilder();

  AdapterComponent.Builder adapterBuilder();

  void baseInject(BaseActivity baseActivity);

  void inject(MainDashBoadActivity mainDashBoadActivity);

  void inject(SalonDetailsActivity salonDetailsActivity);

  @Subcomponent.Builder interface Builder {
    Builder activityModule(ActivityModule activityModule);

    ActivityComponent build();
  }
}
