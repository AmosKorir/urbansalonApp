package com.app.remote.salon.urban.ui.fragments.customer;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.customerpresenters.CustomerProfilePresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.BaseBottomSheetFragment;
import com.bumptech.glide.Glide;
import java.io.File;
import java.net.UnknownHostException;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerProfileFragment extends BaseBottomSheetFragment
    implements CustomerProfilePresenter.MyView {
  private View view;
  @Inject CustomerProfilePresenter profilePresenter;
  @BindView(R.id.name) TextView nameTv;
  @BindView(R.id.profileImage) ImageView profileImage;
  private ProfileInterface profileInterface;

  public CustomerProfileFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  public ProfileInterface getProfileInterface() {
    return profileInterface;
  }

  public void setProfileInterface(
      ProfileInterface profileInterface) {
    this.profileInterface = profileInterface;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_customer, container, false);
      ButterKnife.bind(this, view);
    }
    Toolbar toolbar = view.findViewById(R.id.toolbar);

    ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(v -> {
      dismiss();
      CustomerProfileFragment.this.dispose();
    });
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    profilePresenter.setView(this);
    showProgress("....");
    profilePresenter.getProfile();
    profileImage.setOnClickListener(v -> {
      profileInterface.getProfileImage();
    });
  }

  @Override public void setProfile(CustomerModel customerModel) {
    nameTv.setText(customerModel.getName());
    dismissProgressDialog();
    Glide.with(this)
        .load(Constants.IMAGE_URL + customerModel.getAvatar())
        .error(R.drawable.circleuser)
        .circleCrop()
        .into(profileImage);
  }

  @Override public void uploadSuccess(Sucess sucess) {

    profilePresenter.getProfile();
  }

  public void setImageBitmap(Bitmap thumbnail) {
  }

  public void upload(File destination) {
    profilePresenter.upload(destination);
  }

  public void handleError(Throwable throwable) {

    profilePresenter.getProfile();
    if (isAdded()) {
      if (!(throwable instanceof UnknownHostException)) {
      }
    }
  }

  public interface ProfileInterface {
    void getProfileImage();
  }
}
