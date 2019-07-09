package com.app.remote.salon.urban.ui.fragments.sallon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.salonpresenter.ServicesPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.salon.SalonServicesAdapter;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends BaseFragment implements ServicesPresenter.MyView ,SalonServicesAdapter.SalonServiceAdapterInterface{
  private View view;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @Inject ServicesPresenter servicesPresenter;
  @BindView(R.id.servicesRecycler) RecyclerView serviceRecyclerView;
  @Inject FragmentManager fragmentManager;

  public ServicesFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_services, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    servicesPresenter.setView(this);
    servicesPresenter.getAllServices();
    initRecyclers();

  }

  public void initRecyclers() {
    serviceRecyclerView.setLayoutManager(new LinearLayoutManager(context));
  }

  @Override public void sucess(Service service) {

  }

  @Override public void services(List<Service> services) {
    serviceRecyclerView.setAdapter(new SalonServicesAdapter(context,services,this));

  }

  @Override public void sucess(Sucess sucess) {

  }

  @Override public void setAvailability(String i) {

  }
}
