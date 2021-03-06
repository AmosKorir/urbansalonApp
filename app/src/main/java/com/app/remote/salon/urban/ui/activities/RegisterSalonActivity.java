package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.presentation.salonpresenter.SalonRegisterPresenter;
import com.app.remote.salon.urban.R;
import javax.inject.Inject;

public class RegisterSalonActivity extends BaseActivity implements SalonRegisterPresenter.MyView {
  @Inject SalonRegisterPresenter salonRegisterPresenter;
  @BindView(R.id.name) TextInputEditText nameEd;
  @BindView(R.id.phone) TextInputEditText phoneEd;
  @BindView(R.id.password) TextInputEditText passwordEd;
  @BindView(R.id.location) TextInputEditText locationEd;
  @BindView(R.id.confirmpassword) TextInputEditText confirmEd;
  private String nameStr, passwordStr, confirmStr, locationStr, phoneStr;
  private String latitude = "1.0891";
  private String longitude = "37.0105";

  //just using the above as default location. Should not be the case

  @Override

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register_salon);
    injector().inject(this);
  }

  @OnClick(R.id.registerBtn) public void registerCicked() {
    getUserInput();
  }

  @OnClick(R.id.login) public void loginClicked() {
    startActivity(new Intent(this, SalonLogin.class));
    finish();
  }



  private void getUserInput() {
    nameStr = nameEd.getText().toString().trim();
    phoneStr = phoneEd.getText().toString().trim();
    passwordStr = passwordEd.getText().toString().trim();
    locationStr = locationEd.getText().toString().trim();
    confirmStr = confirmEd.getText().toString().trim();

    if (!nameStr.isEmpty()
        && !phoneStr.isEmpty()
        && !passwordStr.isEmpty()
        && !confirmStr.isEmpty()
        && !locationStr.isEmpty()) {
      if (confirmStr.equals(passwordStr)) {
        salonRegisterPresenter.createSalon(nameStr, phoneStr, passwordStr, locationStr, latitude,
            longitude);
      } else {
        customToast(getString(R.string.password_not_match));
      }
    } else {
      customToast(getString(R.string.all_fields_required));
    }
  }

  @Override protected void onStart() {
    super.onStart();
    if (isLocationEnabled()) {
      checkPermission();
      listenForLocation(this);
    } else {
      redireTosetting();
    }

    salonRegisterPresenter.setView(this);
  }

  @Override public void success(SalonModel salonModel) {
    customToast("Successful");
    finish();
  }

  @Override public void onBackPressed() {

    super.onBackPressed();
    switchAccount(1);
  }

  public void updateRetrievedLocation(Location location, boolean b) {
    if (b) {
      latitude = String.valueOf(location.getLatitude());
      longitude = String.valueOf(location.getLongitude());
    }
  }
}
