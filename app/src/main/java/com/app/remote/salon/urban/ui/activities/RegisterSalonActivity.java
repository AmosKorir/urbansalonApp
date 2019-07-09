package com.app.remote.salon.urban.ui.activities;

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

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_register_salon);
    injector().inject(this);
  }

  @OnClick(R.id.registerBtn) public void registerCicked() {
    getUserInput();
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
          salonRegisterPresenter.createSalon(nameStr,phoneStr,passwordStr,locationStr,"0.0","0.0");
      } else {
        customToast(getString(R.string.password_not_match));
      }
    } else {
      customToast(getString(R.string.all_fields_required));
    }
  }

  @Override protected void onStart() {
    super.onStart();
    salonRegisterPresenter.setView(this);
  }

  @Override public void success(SalonModel salonModel) {
    customToast("Successful");
    finish();
  }
}
