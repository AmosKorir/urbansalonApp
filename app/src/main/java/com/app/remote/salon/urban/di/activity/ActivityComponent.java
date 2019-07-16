package com.app.remote.salon.urban.di.activity;

import com.app.remote.salon.urban.di.adapter.AdapterComponent;
import com.app.remote.salon.urban.di.fragment.FragmentComponent;
import com.app.remote.salon.urban.ui.activities.AddSearvicActivity;
import com.app.remote.salon.urban.ui.activities.BaseActivity;
import com.app.remote.salon.urban.ui.activities.CustomerLoginActivity;
import com.app.remote.salon.urban.ui.activities.CustomerRegisterActivity;
import com.app.remote.salon.urban.ui.activities.MainDashBoadActivity;
import com.app.remote.salon.urban.ui.activities.RegisterSalonActivity;
import com.app.remote.salon.urban.ui.activities.SalonDashBoard;
import com.app.remote.salon.urban.ui.activities.SalonDetailsActivity;
import com.app.remote.salon.urban.ui.activities.SalonLogin;
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

  void inject(CustomerRegisterActivity customerRegisterActivity);

  void inject(RegisterSalonActivity registerSalonActivity);

  void inject(SalonDashBoard salonDashBoard);

  void inject(CustomerLoginActivity customerLoginActivity);

  void inject(AddSearvicActivity addSearvicActivity);

  void inject(SalonLogin salonLogin);

  @Subcomponent.Builder interface Builder {
    Builder activityModule(ActivityModule activityModule);

    ActivityComponent build();
  }
}
