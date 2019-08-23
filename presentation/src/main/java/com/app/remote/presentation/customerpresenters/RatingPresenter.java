package com.app.remote.presentation.customerpresenters;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.repositories.CustomerRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 8/1/19.
 * amoskrr@gmail.com
 */
public class RatingPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) CustomerRepository customerApiRepository;

  @Inject
  public RatingPresenter() {
  }




  private MyView view;
  @Inject SharedPreferences sharedPreferences;

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_TOKEN, null);
  }

  public void setView(MyView view) {
    this.view = view;
  }

  public void rateService(String serviceId, String rating) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = customerApiRepository.rate(getAccessToken(),serviceId,rating)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::success,view::handleError);

    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends View {


    void success(Sucess sucess);
  }
}
