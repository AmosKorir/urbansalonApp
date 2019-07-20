package com.app.remote.presentation.customerpresenters;

import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 7/17/19.
 * amoskrr@gmail.com
 */
public class CustomerSalonPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject @Named(DIConstants.API) SalonRepository salonRepository;
  private MyView view;

  @Inject
  public CustomerSalonPresenter() {

  }

  public void getSalons() {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonRepository.getSalons()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(view::setSalons, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void setView(MyView view) {
    this.view = view;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void getSalonServices(String accesstoken) {
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonRepository.getAllServices(accesstoken)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::salonServices,view::handleError);
             compositeDisposable.add(disposable);
  }

  public interface MyView extends BasePresenter.View {

    void setSalons(List<SalonModel> salonModels);

    void salonServices(List<Service> services);
  }
}
