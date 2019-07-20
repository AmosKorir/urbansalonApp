package com.app.remote.salon.urban.ui.activities;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.presentation.customerpresenters.CustomerSalonPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.ServicesRecyclerViewAdapter;
import com.app.remote.salon.urban.ui.fragments.customer.OrderDialogFragment;
import java.util.List;
import javax.inject.Inject;

public class SalonDetailsActivity extends BaseActivity implements CustomerSalonPresenter.MyView , ServicesRecyclerViewAdapter.OrderInterface{
  @BindView(R.id.serviceRecyclerView) RecyclerView serviceRecyclerView;
  @Inject CustomerSalonPresenter customerSalonPresenter;
  @Inject FragmentManager fragmentManager;
  private SalonModel salonModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salon_details);
    injector().inject(this);
    try {
      salonModel= (SalonModel) getIntent().getExtras().get(Constants.SALON_MODE);
    }catch (Exception e){
      customToast("Error Occurred");
    }
  }

  @Override protected void onStart() {
    super.onStart();
    customerSalonPresenter.setView(this);
    initRecyclerView();
    customerSalonPresenter.getSalonServices(salonModel.getAccesstoken());
  }

  private void initRecyclerView() {
    serviceRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
    //serviceRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(this));
  }

  @Override public void setSalons(List<SalonModel> salonModels) {

  }

  @Override public void salonServices(List<Service> services) {
    serviceRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(this,services,this,R.layout.item_recommended_layout));
  }

  @Override public void serviceId(String serviceid) {
    OrderDialogFragment orderDialogFragment = OrderDialogFragment.newInstance(serviceid);
    orderDialogFragment.show(fragmentManager, "Order the Service");
  }
}
