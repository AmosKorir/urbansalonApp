package com.app.remote.salon.urban.di.fragment;

import com.app.remote.salon.urban.ui.fragments.BaseBottomSheetFragment;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import com.app.remote.salon.urban.ui.fragments.HomeFragment;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerHistoryFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerOrderFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerProfileFragment;
import com.app.remote.salon.urban.ui.fragments.customer.OrderDialogFragment;
import com.app.remote.salon.urban.ui.fragments.sallon.OrderSFragment;
import com.app.remote.salon.urban.ui.fragments.sallon.ServicesFragment;
import dagger.Subcomponent;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Subcomponent(modules = FragmentModule.class) public interface FragmentComponent {

  void baseInject(BaseFragment baseFragment);

  void inject(HomeFragment homeFragment);

  void baseInject(BaseDialogFragment baseDialogFragment);

  void inject(OrderDialogFragment orderDialogFragment);

  void inject(CustomerProfileFragment customerProfileFragment);

  void inject(ServicesFragment servicesFragment);

  void inject(OrderSFragment orderSFragment);

  void inject(CustomerOrderFragment customerOrderFragment);

  void baseInject(BaseBottomSheetFragment baseBottomSheetFragment);

  void inject(CustomerHistoryFragment customerHistoryFragment);

  @Subcomponent.Builder interface Builder {
    Builder fragmentModule(FragmentModule fragmentModule);

    FragmentComponent build();
  }
}
