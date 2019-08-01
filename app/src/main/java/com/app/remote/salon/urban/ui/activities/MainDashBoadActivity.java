package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.OnClick;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.customerpresenters.CustomerProfilePresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.HomeFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerHistoryFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerOrderFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerProfileFragment;
import com.app.remote.salon.urban.ui.fragments.customer.SalonsFragments;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.inject.Inject;

public class MainDashBoadActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener,

    CustomerProfileFragment.ProfileInterface {
  private static final int IMAGE_CODE = 567;
  CustomerProfileFragment customerProfileFragment=new CustomerProfileFragment();
  @Inject FragmentManager fragmentManager;
  private File imageFile;
  DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_dash_boad_activyity);
    injector().inject(this);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setTitle("Urban Salon");
    setSupportActionBar(toolbar);

    drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_dash_boad_activyity, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    int id = item.getItemId();

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.profile) {
      startProfile();
    } else if (id == R.id.History) {
      startHistory();
    } else if (id == R.id.nav_send) {

    } else if (id == R.id.Bookedservice) {
      startOrders();
    } else if (id == R.id.swichAccount) {
      switchAccount(2);
    } else if (id == R.id.nav_logout) {
      logout();
    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  // my code

  @Override protected void onStart() {
    super.onStart();
    setTitle("Current Salons");
    initRecyclerView();
    startHomeFragment();
  }

  //init fragment management
  private FragmentTransaction initFragments() {
    FragmentTransaction fragmentTransaction;
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    return fragmentTransaction;
  }

  private void startHomeFragment() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new HomeFragment());
    fragmentTransaction.commit();
  }

  private void startOrders() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new CustomerOrderFragment());
    fragmentTransaction.commit();
  }

  private void startHistory() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new CustomerHistoryFragment());
    fragmentTransaction.commit();
  }

  private void startProfile() {
    customerProfileFragment=new CustomerProfileFragment();
    customerProfileFragment.setProfileInterface(this);
    customerProfileFragment.show(fragmentManager, "profile");
  }

  private void startSalons() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new SalonsFragments());
    fragmentTransaction.commit();
  }

  public void initRecyclerView() {

  }

  //top layout navigation
  @OnClick(R.id.bookings) public void bookinga() {
    startOrders();
  }

  @OnClick(R.id.services) public void servicea() {
    startHomeFragment();
  }

  @OnClick(R.id.salons) public void salonsw() {
    startSalons();
  }

  @OnClick(R.id.drawerIcon) public void toggleDrawer() {
    drawer.openDrawer(Gravity.LEFT);
  }

  @Override public void getProfileImage() {
    Intent intent = new Intent(Intent.ACTION_PICK);
    intent.setType("image/*");
    startActivityForResult(intent, IMAGE_CODE);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (data != null) {
      try {
        Uri imageUri = data.getData();
        Bitmap thumbnail =
            MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(getCacheDir(), "temp.jpg");
        FileOutputStream fo;
        try {
          fo = new FileOutputStream(destination);
          fo.write(bytes.toByteArray());
          fo.close();
          imageFile = destination;
          customerProfileFragment.upload(imageFile);

        } catch (IOException e) {
          customToast("Failed to get the image");
          e.printStackTrace();
        }
      } catch (Exception e) {
        Log.d("MMMM",e.getMessage());
      }
    } else {
      customToast("Failed to get the image");
    }
  }


}


