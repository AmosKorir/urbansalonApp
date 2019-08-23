package com.app.remote.salon.urban.di.fragment;

import com.app.remote.salon.urban.ui.fragments.BaseBottomSheetFragment;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import dagger.Module;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Module public class FragmentModule {
  private BaseFragment baseFragment;

  public FragmentModule(BaseFragment baseFragment) {
    this.baseFragment = baseFragment;
  }

  public FragmentModule(BaseDialogFragment baseDialogFragment) {
  }

  public FragmentModule(BaseBottomSheetFragment baseBottomSheetFragment) {
  }
}
