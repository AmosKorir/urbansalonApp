package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class ServicesPresenter implements BasePresenter {
  @Inject SharedPreferences sharedPreferences;
  @Inject @Named(DIConstants.API) SalonRepository salonApiRepository;
  private CompositeDisposable compositeDisposable;
  private MyView view;

  @Inject
  public ServicesPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_SALON, null);
  }

  public void createOrder(String name, String price,File file) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonApiRepository.createService(getAccessToken(), name, price)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s->{
          uploadServiceImage(file,s.getServiceid());
        }, view::handleError);
    compositeDisposable.add(disposable);
  }

  public void uploadServiceImage(File imageFile,String serviceId){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.uploadServicImage(imageFile,serviceId)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::sucess,view::handleError);
             compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void getAllServices() {
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonApiRepository.getAllServices(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::services,view::handleError);
             compositeDisposable.add(disposable);
  }

  public interface MyView extends View {

    void sucess(Service service);

    void services(List<Service> services);

    void sucess(Sucess sucess);
  }
}
