package com.app.remote.salon.urban.ui.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.salon.urban.MyApplication;
import com.app.remote.salon.urban.di.activity.ActivityComponent;
import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class BaseActivity extends AppCompatActivity {
  protected CompositeDisposable compositeDisposable;
  ProgressDialog progressDialog;

  @Override public void onCreate(@Nullable Bundle savedInstanceState,
      @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
    injector().baseInject(this);
  }

  public ActivityComponent injector() {
    return ((MyApplication) getApplicationContext()).getActivityInjector(this);
  }

  public void handleError(Throwable throwable) {
    dismissProgressDialog();
    Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show();
  }

  @Override protected void onStart() {
    super.onStart();
    ButterKnife.bind(this);
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
  }

  public void customToast(String message) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  @Override protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
  }

  public void showProgress(String message) {
    progressDialog = new ProgressDialog(this);
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