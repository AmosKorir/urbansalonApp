package com.app.remote.salon.urban.ui.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.presentation.customerpresenters.CustomerSalonPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.ServicesRecyclerViewAdapter;
import com.app.remote.salon.urban.ui.fragments.customer.OrderDialogFragment;
import com.bumptech.glide.Glide;
import java.util.List;
import javax.inject.Inject;
import jp.wasabeef.glide.transformations.BlurTransformation;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

public class SalonDetailsActivity extends BaseActivity
    implements CustomerSalonPresenter.MyView, ServicesRecyclerViewAdapter.OrderInterface {
  @BindView(R.id.serviceRecyclerView) RecyclerView serviceRecyclerView;
  @Inject CustomerSalonPresenter customerSalonPresenter;
  @Inject FragmentManager fragmentManager;
  private SalonModel salonModel;
  @BindView(R.id.salonName) TextView salonName;
  @BindView(R.id.status) TextView status;
  @BindView(R.id.imageback) ImageView backImage;
  @BindView(R.id.profile) ImageView profileImage;
  @BindView(R.id.opening) TextView openingTv;
  @BindView(R.id.closing) TextView closingTv;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salon_details);
    injector().inject(this);
    try {
      salonModel = (SalonModel) getIntent().getExtras().get(Constants.SALON_MODE);
    } catch (Exception e) {
      customToast("Error Occurred");
    }
  }

  @Override protected void onStart() {
    super.onStart();
    customerSalonPresenter.setView(this);
    initRecyclerView();
    customerSalonPresenter.getSalonServices(salonModel.getAccesstoken());
    setSalonData();
  }

  private void setSalonData() {
    Glide.with(this)
        .load(Constants.IMAGE_URL + salonModel.getAvatar())
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(profileImage);
    Glide.with(this)
        .load(Constants.IMAGE_URL + salonModel.getAvatar())
        .apply(bitmapTransform(new BlurTransformation(25, 3)))
        .centerCrop()
        .placeholder(R.drawable.image_holder)
        .into(backImage);
    openingTv.setText(salonModel.getOpeningtime());
    closingTv.setText(salonModel.getClosingtime());
    if (salonModel.getStatus() != null) {
      if (salonModel.getStatus().equals("0")) {
       status.setText("Open");
      } else {
       status.setText("Closed");
      }
    }else {
      status.setText("uknown");
    }
  }

  private void initRecyclerView() {
    serviceRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    //serviceRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(this));
  }

  @Override public void setSalons(List<SalonModel> salonModels) {

  }

  @Override public void salonServices(List<Service> services) {
    serviceRecyclerView.setAdapter(
        new ServicesRecyclerViewAdapter(this, services, this, R.layout.item_recommended_layout));
  }

  @Override public void serviceId(String serviceid) {
    OrderDialogFragment orderDialogFragment = OrderDialogFragment.newInstance(serviceid);
    orderDialogFragment.show(fragmentManager, "Order the Service");
  }
}
