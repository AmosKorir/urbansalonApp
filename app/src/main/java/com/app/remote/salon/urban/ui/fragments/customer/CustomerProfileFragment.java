package com.app.remote.salon.urban.ui.fragments.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.data.BuildConfig;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.presentation.customerpresenters.CustomerProfilePresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import com.bumptech.glide.Glide;
import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class CustomerProfileFragment extends BaseDialogFragment
    implements CustomerProfilePresenter.MyView {
  private View view;
  @Inject CustomerProfilePresenter profilePresenter;
  @BindView(R.id.name) TextView nameTv;
  @BindView(R.id.profileImage) ImageView profileImage;

  public CustomerProfileFragment() {
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
    profilePresenter.getProfile();
  }

  @Override public void setProfile(CustomerModel customerModel) {
    nameTv.setText(customerModel.getName());
    Glide.with(this)
        .load(BuildConfig.BASE_URL + customerModel.getAvatar())
        .error(R.drawable.circleuser)
        .circleCrop()
        .into(profileImage);
  }

  @Override public void onResume() {
    // Get existing layout params for the window
    ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
    // Assign window properties to fill the parent
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    params.height = WindowManager.LayoutParams.MATCH_PARENT;
    getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    // Call super onResume after sizing
    super.onResume();
  }
}
