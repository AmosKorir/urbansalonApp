package com.app.remote.presentation.salonpresenter;

import android.content.SharedPreferences;
import com.app.remote.data.utils.RxUtil;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.repositories.SalonRepository;
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
public class SalonRegisterPresenter implements BasePresenter {
  @Inject @Named(DIConstants.API) SalonRepository salonRepository;
  @Inject SharedPreferences sharedPreferences;
  private CompositeDisposable compositeDisposable;
  private MyView view;

  @Inject
  public SalonRegisterPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  public void createSalon(String name, String phone, String password, String location,
      String latitude, String longitude) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable =
        salonRepository.registerSalon(name, phone, password, location, latitude, longitude)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(s -> {
              saveAccessToken(s.getAccesstoken());
              view.success(s);
            }, view::handleError);

    compositeDisposable.add(disposable);
  }

  private String saveAccessToken(String token) {
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString(Constants.ACCESS_SALON, token);
    editor.commit();
    return token;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public void loginUser(String phone, String password) {
    compositeDisposable = RxUtil.initDisposables(compositeDisposable);
    Disposable disposable = salonRepository.loginUser(phone, password)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(s -> {
          saveAccessToken(s.getAccesstoken());
          view.success(s);
        }, view::handleError);
    compositeDisposable.add(disposable);
  }

  public interface MyView extends View {

    void success(SalonModel salonModel);
  }
}
