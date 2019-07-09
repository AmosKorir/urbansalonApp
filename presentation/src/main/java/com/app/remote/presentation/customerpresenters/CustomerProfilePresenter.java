package com.app.remote.presentation.customerpresenters;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.repositories.CustomerRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class CustomerProfilePresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) CustomerRepository customerRepository;
  @Inject SharedPreferences sharedPreferences;
  private MyView view;

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
  }

  @Inject
  public CustomerProfilePresenter() {

  }

  public void getProfile() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = customerRepository.getProfile(getAccessToken())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setProfile, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void setView(MyView view) {
    this.view = view;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends View {

    void setProfile(CustomerModel customerModel);
  }
}
