package com.app.remote.presentation.customerpresenters;

import com.app.remote.data.utils.RxUtil;
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
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class CustomerRegisterPresenter implements BasePresenter {
  @Inject @Named(DIConstants.API) CustomerRepository customerApiRepository;
  private CompositeDisposable compositeDisposable;
  private MyView view;

  @Inject
  public CustomerRegisterPresenter() {

  }

  public void registerCustomer(String name, String phone, String password) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = customerApiRepository.registerCustomer(name, phone, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::sussess, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void setView(MyView view) {
    this.view = view;
  }


  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends BasePresenter.View {

    void sussess(CustomerModel customerModel);
  }
}
