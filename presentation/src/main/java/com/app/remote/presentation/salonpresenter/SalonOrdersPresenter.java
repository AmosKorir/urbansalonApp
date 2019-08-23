package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import android.util.Log;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 6/24/19.
 * amoskrr@gmail.com
 */
public class SalonOrdersPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) SalonRepository salonApiRepository;
  @Inject SharedPreferences sharedPreferences;
  private MyView view;

  @Inject
  public SalonOrdersPresenter() {
  }

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_SALON, null);
  }

  public void getActiviOrders() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonApiRepository.getActiveOrders(getAccessToken())
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setOrders, view::handleError);

    compositeDisposable.add(disposable);
  }
  public void getClosedOrders(){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.getClosedOrders(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::setOrders,view::handleError);
             compositeDisposable.add(disposable);
  }

  public void getPendingOrders(){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.getPendingOrders(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::setOrders,view::handleError);
             compositeDisposable.add(disposable);
  }

  public void getRejectedOrders(){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.getRejectedOrders(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::setOrders,view::handleError);
             compositeDisposable.add(disposable);
  }

  public void setView(MyView view) {
    this.view = view;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void setOrderStatus(Integer orderid, int i) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonApiRepository.setOrderStatus(getAccessToken(), orderid, i)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::statusResponse, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void filter(List<CustomerOrder> customerOrders, int type) {
    List<CustomerOrder> result = new ArrayList<>();
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = Single.just(customerOrders)
        .flatMapPublisher(Flowable::fromIterable)
        .map(r -> {
          if (r.getStatus() == type) {
            result.add(r);
            Log.d("mmm",r.getStatus()+"");
          }
          return r;
        })
        .toList()
        .map(m -> result)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setFilteredOrders, view::handleError);

    compositeDisposable.add(disposable);
  }

  public interface MyView extends View {

    void setOrders(List<CustomerOrder> customerOrders);

    void statusResponse(Sucess sucess);

    void setFilteredOrders(List<CustomerOrder> customerOrders);
  }
}
