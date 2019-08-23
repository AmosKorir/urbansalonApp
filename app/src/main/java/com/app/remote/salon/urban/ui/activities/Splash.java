package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import com.app.remote.domain.constants.Constants;
import com.app.remote.salon.urban.R;
import javax.inject.Inject;

public class Splash extends BaseActivity {
  @Inject SharedPreferences sharedPreferences;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    injector().inject(this);
    Runnable runnable = () -> navigate();

    new Handler().postDelayed(runnable, 2000);
  }

  private void navigate() {
    String customer = sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
    String salon = sharedPreferences.getString(Constants.ACCESS_SALON, null);
    int customerMode = sharedPreferences.getInt(Constants.MODE, 0);

    if (customer!=null | salon!=null) {
      if (customerMode == 0) {
        if (salon!=null) {
          switchAccount(2);
        } else {
          switchAccount(1);
        }
      } else {
        switchAccount(customerMode);
      }
    } else {
      startActivity(new Intent(Splash.this, IntroActivity.class));
    }
    finish();
  }
}
