package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.presentation.salonpresenter.SalonRegisterPresenter;
import com.app.remote.salon.urban.R;
import javax.inject.Inject;

public class SalonLogin extends BaseActivity implements SalonRegisterPresenter.MyView {
  @BindView(R.id.phone) TextInputEditText phoneEd;
  @BindView(R.id.password) TextInputEditText passwordEd;
  @Inject SalonRegisterPresenter salonRegisterPresenter;
  private String password, phone;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salon_login);
    injector().inject(this);
  }

  @OnClick(R.id.loginBtn) public void loginClicked() {
    phone = phoneEd.getText().toString();
    password = passwordEd.getText().toString();
    if (!phone.isEmpty() && !password.isEmpty()) {
      showProgress("Login...");
      salonRegisterPresenter.loginUser(phone, password);
    } else {
      customToast(getResources().getString(R.string.all_fields_required));
    }
  }

  @OnClick(R.id.registerBtn) public void regiterCkicked() {
    startActivity(new Intent(this, RegisterSalonActivity.class));
    finish();
  }

  @Override protected void onStart() {
    super.onStart();
    salonRegisterPresenter.setView(this);
  }

  @Override public void success(SalonModel salonModel) {
    finish();
  }

  public void handleError(Throwable throwable) {
    dismissProgressDialog();
    customToast("please check your credentials");
  }
}
