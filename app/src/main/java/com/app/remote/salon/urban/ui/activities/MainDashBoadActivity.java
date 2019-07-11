package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.OnClick;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.HomeFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerHistoryFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerOrderFragment;
import com.app.remote.salon.urban.ui.fragments.customer.CustomerProfileFragment;
import javax.inject.Inject;

public class MainDashBoadActivity extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  @Inject FragmentManager fragmentManager;
  DrawerLayout drawer;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main_dash_boad_activyity);
    injector().inject(this);
    Toolbar toolbar = findViewById(R.id.toolbar);
    setTitle("Urban Salon");
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show());
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
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    //if (id == R.id.action_settings) {
    //  return true;
    //}

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

  //run the account Fragment
  //private void startCreateFragment() {
  //  FragmentTransaction fragmentTransaction = initFragments();
  //  fragmentTransaction.replace(R.id.fragment_container, new CreateServiceFragment());
  //  fragmentTransaction.commit();
  //  customToast("dfsgjkk");
  //}
  @OnClick(R.id.fab) public void switchAccount() {
    startActivity(new Intent(this, SalonDashBoard.class));
    finish();
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
    //FragmentTransaction fragmentTransaction = initFragments();
    //fragmentTransaction.replace(R.id.fragment_container, new CustomerProfileFragment());
    //fragmentTransaction.commit();
    CustomerProfileFragment customerProfileFragment=new CustomerProfileFragment();
    customerProfileFragment.setStyle(DialogFragment.STYLE_NORMAL, R.style.Dialog_FullScreen);
    customerProfileFragment.show(fragmentManager,"profile");
  }

  public void initRecyclerView() {

  }

  //top layout navigation
  @OnClick(R.id.bookinga) public void bookinga() {
    startOrders();
  }

  @OnClick(R.id.bookinga) public void bookingw() {
    startOrders();
  }

  @OnClick(R.id.servicesa) public void servicea() {
    startHomeFragment();
  }

  @OnClick(R.id.servicesw) public void servicew() {
    startHomeFragment();
  }

  @OnClick(R.id.salonsa) public void salonsa() {

  }

  @OnClick(R.id.salonsw) public void salonsw() {

  }
  @OnClick(R.id.drawerIcon) public void toggleDrawer(){
    drawer.openDrawer(Gravity.LEFT);
  }
}
