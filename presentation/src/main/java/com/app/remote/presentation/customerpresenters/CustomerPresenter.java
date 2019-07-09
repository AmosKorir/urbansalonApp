package com.app.remote.presentation.customerpresenters;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.repositories.CustomerRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class CustomerPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) CustomerRepository customerApiRepository;
  @Inject SharedPreferences sharedPreferences;
  private MyView view;

  @Inject
  public CustomerPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void loginUser(String phone, String password) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = customerApiRepository.loginCustomer(phone, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(user -> {
          saveAccessToken(user.getAccesstoken());
          view.loginedIn();
        }, view::handleError);
    compositeDisposable.add(disposable);
  }

  private String saveAccessToken(String token) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(Constants.ACCESS_TOKEN, token);
    editor.commit();
    return token;
  }

  public void getOrders() {
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= customerApiRepository.getCustomerOrder(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::orders,view::handleError);
         compositeDisposable.add(disposable);
  }
  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
  }

  public void bookService(String serviceId, String date, String time) {
    if (getAccessToken()!=null) {
      compositeDisposable = RxUtil.initDisposables(compositeDisposable);
      Disposable disposable =
          customerApiRepository.bookService(getAccessToken(), serviceId, date, time)
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribe(view::bookedStatus, view::handleError);
      compositeDisposable.add(disposable);
    }
    else {
      view.requestLogin();
    }
  }


  public interface MyView extends View {
    void requestLogin();

    void loginedIn();

    void bookedStatus(OrderModel orderModel);

    void orders(List<CustomerOrder> customerOrders);
  }
}
