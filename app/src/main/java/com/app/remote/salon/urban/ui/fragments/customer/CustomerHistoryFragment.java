package com.app.remote.salon.urban.ui.fragments.customer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.presentation.customerpresenters.CustomerOrderPresenter;
import com.app.remote.presentation.customerpresenters.CustomerPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.CustomerOrderRecyclerAdapter;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class CustomerHistoryFragment extends BaseFragment implements CustomerPresenter.MyView,
    CustomerOrderRecyclerAdapter.customerOrderAdapterInterface ,CustomerOrderPresenter.MyView{
  private View view;
  @Inject CustomerPresenter presenter;
  @Inject CustomerOrderPresenter customerOrderPresenter;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @BindView(R.id.customerRecyclerView) RecyclerView recyclerView;
  private CustomerOrderRecyclerAdapter customerOrderRecyclerAdapter;

  public CustomerHistoryFragment() {
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
      view = inflater.inflate(R.layout.fragment_customer_order, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    presenter.setView(this);
    customerOrderPresenter.setView(this);
    presenter.getHistory();
    recyclerView.setLayoutManager(new LinearLayoutManager(context));
  }

  @Override public void requestLogin() {

  }

  @Override public void loginedIn() {

  }

  @Override public void bookedStatus(OrderModel orderModel) {
    customToast("Booked successfull");
  }

  @Override public void orders(List<CustomerOrder> customerOrders) {
    customerOrderRecyclerAdapter = new CustomerOrderRecyclerAdapter(customerOrders, context);
    customerOrderRecyclerAdapter.setCustomerOrderAdapterInterface(this);
    recyclerView.setAdapter(customerOrderRecyclerAdapter);
  }

  @Override public void cancelOrder(CustomerOrder customerOrder) {
    customerOrderPresenter.cancelOrder(customerOrder.getOrderid().toString());
  }

  @Override public void cancelOrder(OrderModel orderModel) {
   customToast("Cancellation Successful");
  }


}
