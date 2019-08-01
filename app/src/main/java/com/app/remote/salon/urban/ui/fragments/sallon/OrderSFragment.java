package com.app.remote.salon.urban.ui.fragments.sallon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
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
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.presentation.salonpresenter.SalonOrdersPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.salon.SalonOrderRecyclerAdapter;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderSFragment extends BaseFragment implements SalonOrdersPresenter.MyView,
    SalonOrderRecyclerAdapter.SalonOrderInterface {
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @Inject SalonOrdersPresenter salonOrdersPresenter;
  @BindView(R.id.salonOrderRecyclerView) RecyclerView serviceRecyclerView;
  @Inject FragmentManager fragmentManager;
  @BindView(R.id.tabLayout) TabLayout tabLayout;
  private View view;
  private int type = 0;

  public OrderSFragment() {
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
      view = inflater.inflate(R.layout.fragment_order, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    salonOrdersPresenter.setView(this);
    salonOrdersPresenter.getPendingOrders();
    tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        type = tab.getPosition();
        switchType(type);
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void switchType(int type) {
    showProgress("Please wait");

    switch (type) {
      case 0:
        salonOrdersPresenter.getPendingOrders();
        break;
      case 1:
        salonOrdersPresenter.getActiviOrders();
        break;
      case 2:
        salonOrdersPresenter.getClosedOrders();
        break;
      case 3:
        salonOrdersPresenter.getRejectedOrders();
        break;
    }
  }

  @Override public void setOrders(List<CustomerOrder> customerOrders) {
    dismissProgressDialog();
    salonOrdersPresenter.filter(customerOrders, type);
    serviceRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    serviceRecyclerView.setAdapter(new SalonOrderRecyclerAdapter(this, context, customerOrders));
  }

  @Override public void statusResponse(Sucess sucess) {
    customToast("Updated");
    switchType(type);
  }

  @Override public void setFilteredOrders(List<CustomerOrder> customerOrders) {
    //serviceRecyclerView.setLayoutManager(new LinearLayoutManager(context));
    //serviceRecyclerView.setAdapter(new SalonOrderRecyclerAdapter(this, context, customerOrders));
  }

  @Override public void repondOrder(String status) {

  }

  @Override public void setOrderStatus(CustomerOrder customerOrder, int i) {
    salonOrdersPresenter.setOrderStatus(customerOrder.getOrderid(), i);
  }
}
