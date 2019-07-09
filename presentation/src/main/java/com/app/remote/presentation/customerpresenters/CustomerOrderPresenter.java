package com.app.remote.presentation.customerpresenters;

import android.content.SharedPreferences;
import com.app.remote.data.repositories.CustomerApiRepository;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.repositories.CustomerRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class CustomerOrderPresenter implements BasePresenter {
  @Inject @Named(DIConstants.API) CustomerRepository customerApiRepository;
  @Inject SharedPreferences sharedPreferences;
  private CompositeDisposable compositeDisposable;
  private MyView view;

  @Inject
  public CustomerOrderPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
  }

  public void cancelOrder(String orderId) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = customerApiRepository.cancelOrder(getAccessToken(),getAccessToken())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::cancelOrder,view::handleError);

    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends BasePresenter.View {

    void cancelOrder(OrderModel orderModel);
  }
}
