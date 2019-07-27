package com.app.remote.salon.urban.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import butterknife.BindView;
import butterknife.OnClick;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.salonpresenter.ServicesPresenter;
import com.app.remote.salon.urban.R;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class AddSearvicActivity extends BaseActivity implements ServicesPresenter.MyView {
  private static final int IMAGE_CODE = 4555;
  @Inject ServicesPresenter servicesPresenter;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @BindView(R.id.name) TextInputEditText nameEd;
  @BindView(R.id.amount) TextInputEditText priceEd;
  @BindView(R.id.serviceImage) ImageView serviceImage;
  private File imageFile = null;
  private View view;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_searvic);
    setTitle("Add Service");
    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
    toolbar.setNavigationOnClickListener(v -> finish());
    injector().inject(this);
  }

  @OnClick(R.id.createBtn) public void createServiceClicked() {
    showProgress("Creating Service");
    getUserInput();
  }

  @OnClick(R.id.serviceImage) public void getImageClicked() {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent, IMAGE_CODE);
  }

  private void getUserInput() {
    String name, price;
    name = nameEd.getText().toString().trim();
    price = priceEd.getText().toString().trim();
    //TODO test the input if is a perfect number
    if (!name.isEmpty() && !price.isEmpty() && imageFile != null) {
      servicesPresenter.createOrder(name, price, imageFile);
    } else {
      dismissProgressDialog();
      customToast(getString(R.string.all_fields_required));
    }
  }

  @Override protected void onStart() {
    super.onStart();
    servicesPresenter.setView(this);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (data != null) {
      try {
        Uri imageUri = data.getData();
        Bitmap thumbnail =
            MediaStore.Images.Media.getBitmap(context.getContentResolver(), imageUri);
        serviceImage.setImageBitmap(thumbnail);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(context.getCacheDir(), "temp.jpg");
        FileOutputStream fo;
        try {
          fo = new FileOutputStream(destination);
          fo.write(bytes.toByteArray());
          fo.close();
          imageFile = destination;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } catch (Exception e) {
      }
    } else {
      customToast("Failed to get the image");
    }
  }

  @Override public void sucess(Service service) {

  }

  @Override public void services(List<Service> services) {

  }

  @Override public void sucess(Sucess sucess) {
    customToast("saved succesfully");
    nameEd.setText(Constants.EMPTY_STRING);
    priceEd.setText(Constants.EMPTY_STRING);
  }

  @Override public void recommended(List<Service> services) {

  }
}
