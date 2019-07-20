package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.api.SalonApi;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.presentation.BasePresenter;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Korir on 7/17/19.
 * amoskrr@gmail.com
 */
public class SalonProfilePresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  @Inject SharedPreferences sharedPreferences;
  @Inject @Named(DIConstants.API) SalonRepository salonRepository;

  private MyView view;
  @Inject
  public SalonProfilePresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }
  public String getAccessToken() {
    return sharedPreferences.getString(Constants.ACCESS_SALON, null);
  }
  public void getSalonSelf(){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonRepository.getsalonSelf(getAccessToken())
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::setSalon,view::handleError);
             compositeDisposable.add(disposable);
  }

  public void update(String opening,String closing,String avilabity){
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonRepository.updateSalon(getAccessToken(),opening,closing,avilabity)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::suceess,view::handleError);
             compositeDisposable.add(disposable);
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void uploadProfile(File imageFile) {
     compositeDisposable= RxUtil.initDisposables(compositeDisposable);
         Disposable disposable= salonRepository.uploadProfile(getAccessToken(),imageFile)
             .subscribeOn(Schedulers.io())
             .observeOn(AndroidSchedulers.mainThread())
             .subscribe(view::suceess,view::handleError);
             compositeDisposable.add(disposable);
  }

  public interface MyView extends BasePresenter.View {

    void setSalon(SalonModel salonModel);

    void suceess(Sucess sucess);
  }
}
