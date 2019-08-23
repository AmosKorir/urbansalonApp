package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.AnalyticModel;
import com.app.remote.domain.models.CpieData;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
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
        .subscribe(view::setSevenDay, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void getPieData() {
    ArrayList<Service> rejected = new ArrayList<>();
    ArrayList<Service> active = new ArrayList<>();
    ArrayList<Service> pending = new ArrayList<>();
    ArrayList<Service> closed = new ArrayList<>();
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonRepository.getPiData(getAccessToken())
        .subscribeOn(Schedulers.io())
        .flatMapPublisher(Flowable::fromIterable)
        .map(item -> {
          switch (item.getStatus()) {
            case "0":
              pending.add(item);
              break;
            case "1":
              active.add(item);
              break;
            case "2":
              closed.add(item);
              break;
            case "3":
              rejected.add(item);
              break;
          }
          return item;
        })
        .toList()
        .map(l -> {
          int count[] = { pending.size(), active.size(), closed.size(), rejected.size() };
          String label[] = { "Pending", "Active", "Closed", "Cancelled" };
          return CpieData.newBuilder()
              .withCount(count)
              .withLabels(label)
              .build();
        })
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setPieData, view::handleError);
    compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends BasePresenter.View {

    void setSevenDay(List<AnalyticModel> analyticModels);

    void setPieData(CpieData pieData);
  }
}
