package com.app.remote.presentation.customerpresenters;

import com.app.remote.presentation.BasePresenter;
import io.reactivex.disposables.CompositeDisposable;
import javax.inject.Inject;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class DashBoardPresenter implements BasePresenter {
  private CompositeDisposable compositeDisposable;
  private MyView view;

  @Inject
  public DashBoardPresenter() {
  }

  public void setView(MyView view) {
    this.view = view;
  }

  @Override public void dispose() {
    compositeDisposable.add(compositeDisposable);
  }

  public interface MyView extends View {

  }
}
