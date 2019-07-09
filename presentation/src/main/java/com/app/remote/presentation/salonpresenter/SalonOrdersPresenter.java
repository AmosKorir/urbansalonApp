package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
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
    Disposable disposable =salonApiRepository.getActiveOrders(getAccessToken())
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
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.setOrderStatus(getAccessToken(),orderid,i)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::statusResponse,view::handleError);
             compositeDisposable.add(disposable);
  }

  public interface MyView extends View {

    void setOrders(List<CustomerOrder> customerOrders);

    void statusResponse(Sucess sucess);
  }
}
