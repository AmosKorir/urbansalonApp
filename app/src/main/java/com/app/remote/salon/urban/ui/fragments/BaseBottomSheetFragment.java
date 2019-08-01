package com.app.remote.salon.urban.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.salon.urban.di.activity.ActivityComponent;
import com.app.remote.salon.urban.di.fragment.FragmentComponent;
import com.app.remote.salon.urban.di.fragment.FragmentModule;
import com.app.remote.salon.urban.ui.activities.BaseActivity;
import io.reactivex.disposables.CompositeDisposable;
import java.net.UnknownHostException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class BaseBottomSheetFragment extends BottomSheetDialogFragment {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  private ProgressDialog progressDialog;

  private static final int NO_LAYOUT = -1;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().baseInject(this);
  }

  public int getLayoutId() {
    return NO_LAYOUT;
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable
      ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    if (getLayoutId() != NO_LAYOUT) {
      View view = inflater.inflate(getLayoutId(), container, false);
      ButterKnife.bind(this, view);
      return view;
    } else {
      return super.onCreateView(inflater, container, savedInstanceState);
    }
  }

  public void handleError(Throwable throwable) {
    dismissProgressDialog();
    if (isAdded()) {
      if (!(throwable instanceof UnknownHostException)) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
      }
    }
  }

  @Override public void onStart() {
    super.onStart();
  }



  //base   methods
  protected FragmentComponent injector() {
    return activityInjector().fragmentBuilder().fragmentModule(new FragmentModule(this)).build();
  }

  protected ActivityComponent activityInjector() {
    return ((BaseActivity) getActivity()).injector();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    dispose();
  }
  public void showProgress(String message) {
    progressDialog = new ProgressDialog(context);
    progressDialog.setMessage(message + "....");
    progressDialog.show();
  }

  public void dismissProgressDialog() {
    if (progressDialog != null) {
      progressDialog.dismiss();
    }
  }


  protected void dispose() {
    RxUtil.dispose(compositeDisposable);
  }
}
