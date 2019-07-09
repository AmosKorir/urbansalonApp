package com.app.remote.salon.urban.ui.activities;

import android.os.Bundle;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.presentation.customerpresenters.CustomerRegisterPresenter;
import com.app.remote.salon.urban.R;
import javax.inject.Inject;

public class CustomerRegisterActivity extends BaseActivity
    implements CustomerRegisterPresenter.MyView {
  @Inject CustomerRegisterPresenter customerRegisterPresenter;
  @BindView(R.id.name) TextView name;
  @BindView(R.id.password) TextView password;
  @BindView(R.id.confirmpassword) TextView confirmPassword;
  @BindView(R.id.phoneNumber) TextView phoneNumber;

  private String nameStr, phoneStr, paswordStr, confirmStr;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_register);
    injector().inject(this);
  }

  @OnClick(R.id.registerBtn) public void registerClicked() {
    getUSerInput();
  }

  private void getUSerInput() {
    nameStr = name.getText().toString().trim();
    paswordStr = password.getText().toString().trim();
    confirmStr = confirmPassword.getText().toString().trim();
    phoneStr = phoneNumber.getText().toString().trim();
    if (!nameStr.isEmpty()
        && !paswordStr.isEmpty()
        && !phoneStr.isEmpty()
        && !confirmStr.isEmpty()) {
      if (paswordStr.equals(confirmStr)) {
        customerRegisterPresenter.registerCustomer(nameStr, phoneStr, paswordStr);
      }else {
        customToast(getString(R.string.password_not_match));
      };
    }else {
      customToast(getString(R.string.all_fields_required));
    }
  }

  @Override protected void onStart() {
    super.onStart();
    customerRegisterPresenter.setView(this);
  }

  @Override public void sussess(CustomerModel customerModel) {
    customToast("susse");
  }
}
