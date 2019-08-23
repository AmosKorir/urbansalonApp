package com.app.remote.salon.urban.ui.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import butterknife.ButterKnife;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.salon.urban.MyApplication;
import com.app.remote.salon.urban.di.activity.ActivityComponent;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class BaseActivity extends AppCompatActivity {
  private static final int PERMISSION_ACCESS_COARSE_LOCATION = 100;
  protected CompositeDisposable compositeDisposable;
  ProgressDialog progressDialog;
  @Inject SharedPreferences sharedPreferences;
  @Inject @Named(DIConstants.ACTIVITY) Context context;

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
    Toast.makeText(context, "Error" + throwable.getMessage(), Toast.LENGTH_LONG).show();
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

  public void logout() {
    sharedPreferences.edit().clear().commit();
    startActivity(new Intent(this, MainDashBoadActivity.class));
    finish();
  }

  public void loginCustomerUser() {
    startActivity(new Intent(this, CustomerLoginActivity.class));
  }

  public void switchAccount(int mode) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    switch (mode) {
      case 1:
        startActivity(new Intent(this, MainDashBoadActivity.class));
        editor.putInt(Constants.MODE, 1);
        editor.commit();
        finish();
        break;
      case 2:
        startActivity(new Intent(this, SalonDashBoard.class));

        editor.putInt(Constants.MODE, 2);
        editor.commit();
        finish();
        break;
    }
  }

  protected void dispose() {
    RxUtil.dispose(compositeDisposable);
  }

  //location services
  public boolean isLocationEnabled() {
    int locationMode = 0;
    String locationProviders;

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
      try {
        locationMode =
            Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
      } catch (Settings.SettingNotFoundException e) {
        e.printStackTrace();
        return false;
      }

      return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    } else {
      locationProviders = Settings.Secure.getString(context.getContentResolver(),
          Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
      return !TextUtils.isEmpty(locationProviders);
    }
  }

  public void redireTosetting() {
    LocationManager locationManager =
        (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
      AlertDialog alertDialog = new AlertDialog.Builder(context)
          .setTitle("Location is turned off")  // GPS not found
          .setMessage("Ur needs you location to suggest nearest Salons") // Want to enable?
          .setPositiveButton("yes",
              (dialogInterface, i) -> {
                dialogInterface.dismiss();
                context.startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
              })
          .setNegativeButton("No", (dialog, which) -> {
            finish();
          })
          .setCancelable(false)
          .show();
    }
  }

  public void checkPermission() {
    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
        == PackageManager.PERMISSION_GRANTED) {
      //      listenLocation();
    } else {
      // Show rationale and request permission.
      ActivityCompat.requestPermissions(this,
          new String[] { Manifest.permission.ACCESS_COARSE_LOCATION },
          PERMISSION_ACCESS_COARSE_LOCATION);
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode, String[] permissions,
      int[] grantResults) {
    switch (requestCode) {
      case PERMISSION_ACCESS_COARSE_LOCATION:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          listenForLocation(this);
        } else {
          permissionDialog();
        }

        break;
    }
  }

  @SuppressLint("MissingPermission")
  public void listenForLocation(Context context) {
    FusedLocationProviderClient mFusedLocationClient =
        LocationServices.getFusedLocationProviderClient(context);
    mFusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, location -> {
          // Got last known location. In some rare situations this can be null.
          if (location != null) {
            updateRetrievedLocation(location, true);
          } else {
            updateRetrievedLocation(location, false);
          }
        });
  }

  public void updateRetrievedLocation(Location location, boolean b) {

  }

  private void permissionDialog() {
    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
    alertDialogBuilder.setMessage(
        "Zuru Can not work without Place Location. Please grant zuru location Permission");
    alertDialogBuilder.setTitle("Sorry , Permission is required");
    alertDialogBuilder.setPositiveButton("Allow Permission", (dialog, which) -> {
      checkPermission();
    });
    alertDialogBuilder.setNegativeButton("QUIT", ((dialog, which) -> {
      finish();
    }));

    alertDialogBuilder.show();
  }

  //
}