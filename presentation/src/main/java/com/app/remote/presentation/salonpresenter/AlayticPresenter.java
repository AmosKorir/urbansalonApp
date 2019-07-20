package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.AnalyticModel;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 7/16/19.
 * amoskrr@gmail.com
 */
public class AlayticPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) SalonRepository salonRepository;
  @Inject SharedPreferences sharedPreferences;
  private MyView view;

  @Inject
  public AlayticPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_SALON, null);
  }

  public void getBookingSevenDays() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonRepository.getSevenDays(getAccessToken())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setSevenDay,view::handleError);
    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends BasePresenter.View {

    void setSevenDay(List<AnalyticModel> analyticModels);
  }
}
