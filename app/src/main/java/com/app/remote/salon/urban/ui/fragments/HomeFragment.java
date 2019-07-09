package com.app.remote.salon.urban.ui.fragments;

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
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.salonpresenter.ServicesPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.ServicesRecyclerViewAdapter;
import com.app.remote.salon.urban.ui.fragments.customer.OrderDialogFragment;
import java.util.List;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment
    implements ServicesPresenter.MyView, ServicesRecyclerViewAdapter.OrderInterface {
  private View view;
  @Inject ServicesPresenter servicesPresenter;
  @BindView(R.id.serviceslist) RecyclerView serviceRecyclerView;
  @BindView(R.id.recomended)RecyclerView recommendedRecyclerView;
  @Inject FragmentManager fragmentManager;

  public HomeFragment() {
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
      view = inflater.inflate(R.layout.fragment_home, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    servicesPresenter.setView(this);
    initRecyclers();
    servicesPresenter.getAllServices();
  }

  private void initRecyclers() {

  }

  @Override public void sucess(Service service) {

  }

  @Override public void services(List<Service> services) {
    serviceRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    recommendedRecyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false));
    serviceRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(context, services, this,
        R.layout.item_salon_service));
    recommendedRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(context,services,this,R.layout.item_recommended_layout));
  }

  @Override public void sucess(Sucess sucess) {

  }

  @Override public void serviceId(String serviceid) {
    OrderDialogFragment orderDialogFragment = OrderDialogFragment.newInstance(serviceid);
    //orderDialogFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);

    orderDialogFragment.show(fragmentManager, "Order the Service");
  }
}
