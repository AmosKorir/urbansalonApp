package com.app.remote.salon.urban.ui.fragments.customer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.presentation.customerpresenters.CustomerSalonPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.SalonRecyclerAdapter;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonsFragments extends BaseFragment implements CustomerSalonPresenter.MyView {
  @Inject CustomerSalonPresenter customerSalonPresenter;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  private View view;
  @BindView(R.id.salonRecyclerView) RecyclerView salonRecyclerView;

  public SalonsFragments() {

  }

  @Override public void onStart() {
    super.onStart();
    customerSalonPresenter.setView(this);
    customerSalonPresenter.getSalons();

  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_salons_fragments, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void setSalons(List<SalonModel> salonModels) {
    salonRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    salonRecyclerView.setAdapter(new SalonRecyclerAdapter(context,salonModels));
  }

  @Override public void salonServices(List<Service> services) {

  }
}
