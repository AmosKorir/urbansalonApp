package com.app.remote.salon.urban.ui.fragments.sallon;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.salonpresenter.SalonProfilePresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import com.bumptech.glide.Glide;
import java.io.File;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalonProfile extends BaseDialogFragment implements SalonProfilePresenter.MyView {
  @Inject SalonProfilePresenter salonProfilePresenter;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @BindView(R.id.name) TextView nameTv;
  @BindView(R.id.profileImage) ImageView profileImage;
  @BindView(R.id.openingtime) EditText opening;
  @BindView(R.id.closingt) EditText closingTime;
  @BindView(R.id.availability) Switch aSwitch;
  @BindView(R.id.town) TextView town;
  @BindView(R.id.phone) TextView phoneTv;
  private ProfileInterface profileInterface;

  private View view;

  public SalonProfile() {
  }

  public ProfileInterface getProfileInterface() {
    return profileInterface;
  }

  public void setProfileInterface(
      ProfileInterface profileInterface) {
    this.profileInterface = profileInterface;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  @Override public void onStart() {
    super.onStart();
    salonProfilePresenter.setView(this);
    salonProfilePresenter.getSalonSelf();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_salon_profile, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @OnClick(R.id.profileImage) public void getImageClicked() {
    profileInterface.getProfileImage();
  }

  @OnClick(R.id.updateBtn) public void update() {
    String openingStr, closingStr, availaibity;
    if (aSwitch.isChecked()) {
      availaibity = "0";
    } else {
      availaibity = "1";
    }

    openingStr = opening.getText().toString().trim();
    closingStr = closingTime.getText().toString().trim();
    if (!openingStr.isEmpty() && !closingStr.isEmpty()) {
      showProgress("Updating");
      salonProfilePresenter.update(openingStr, closingStr, availaibity);
    } else {
      customToast(getString(R.string.all_fields_required));
    }
  }

  @Override public void onResume() {
    ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    params.height = WindowManager.LayoutParams.MATCH_PARENT;
    getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);
    super.onResume();
  }

  @Override public void setSalon(SalonModel salonModel) {
    dismissProgressDialog();
    nameTv.setText(salonModel.getName());
    phoneTv.setText(salonModel.getPhone());
    town.setText(salonModel.getLocation());
    Glide.with(context)
        .load(Constants.IMAGE_URL + salonModel.getAvatar())
        .circleCrop()
        .placeholder(R.drawable.circleuser)
        .into(profileImage);
    try {
      opening.setText(salonModel.getOpeningtime());
      closingTime.setText(salonModel.getClosingtime());
      String tatus = salonModel.getStatus();
      if (tatus.equals("1")) {
        aSwitch.setChecked(false);
      } else {
        aSwitch.setChecked(true);
      }
    } catch (Exception re) {

    }
  }

  @Override public void suceess(Sucess sucess) {
    dismissProgressDialog();
    customToast("Update Successfully");
  }

  public void setImageBitmap(Bitmap thumbnail) {
    profileImage.setImageBitmap(thumbnail);
  }

  public void upload(File imageFile) {
    showProgress("Setting profile");
    customToast("uploaded");
    salonProfilePresenter.uploadProfile(imageFile);
  }

  public interface ProfileInterface {
    void getProfileImage();
  }
}
