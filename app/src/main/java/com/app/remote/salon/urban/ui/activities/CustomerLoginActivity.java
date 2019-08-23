package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.presentation.customerpresenters.CustomerPresenter;
import com.app.remote.salon.urban.R;
import java.util.List;
import javax.inject.Inject;

public class CustomerLoginActivity extends BaseActivity implements CustomerPresenter.MyView {
  @BindView(R.id.phone) TextInputEditText phoneEd;
  @BindView(R.id.password) TextInputEditText passwordEd;
  @Inject CustomerPresenter customerPresenter;
  private String password, phone;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_customer_login);
    injector().inject(this);
  }

  @OnClick(R.id.loginBtn) public void loginClicked() {
    phone = phoneEd.getText().toString();
    password = passwordEd.getText().toString();
    if (!phone.isEmpty() && !password.isEmpty()) {
      customerPresenter.loginUser(phone, password);
    } else {
      customToast(getResources().getString(R.string.all_fields_required));
    }
  }

  @OnClick(R.id.registerBtn) public void regiterCkicked() {
    startActivity(new Intent(this, CustomerRegisterActivity.class));
    finish();
  }

  @Override protected void onStart() {
    super.onStart();
    customerPresenter.setView(this);
  }

  @Override public void requestLogin() {

  }

  @Override public void loginedIn() {
    customToast(getString(R.string.loggedin));
    finish();
  }

  @Override public void bookedStatus(OrderModel orderModel) {

  }

  @Override public void orders(List<CustomerOrder> customerOrders) {

  }
}
