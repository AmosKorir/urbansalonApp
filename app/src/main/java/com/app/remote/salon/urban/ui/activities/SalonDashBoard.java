package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.app.remote.presentation.salonpresenter.ServicesPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.sallon.OrderSFragment;
import com.app.remote.salon.urban.ui.fragments.sallon.ServicesFragment;
import javax.inject.Inject;

public class SalonDashBoard extends BaseActivity
    implements NavigationView.OnNavigationItemSelectedListener {
  @Inject FragmentManager fragmentManager;
  @Inject ServicesPresenter servicesPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salon_dash_board);
    injector().inject(this);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        startActivity(new Intent(SalonDashBoard.this, AddSearvicActivity.class));
        //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //    .setAction("Action", null).show();
      }
    });
    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    NavigationView navigationView = findViewById(R.id.nav_view);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
  }

  //@OnClick(R.id.fab) public void switchAccount() {
  //  startActivity(new Intent(this, MainDashBoadActivity.class));
  //  finish();
  //}

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
    getMenuInflater().inflate(R.menu.salon_dash_board, menu);
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
    // Handle navigation view item clicks here.
    int id = item.getItemId();
    if (id == R.id.Orders) {
      startActiveOrders();
    } else if (id == R.id.services) {

    } else if (id == R.id.profile) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  @Override protected void onStart() {
    super.onStart();
    startSalonSerVice();
    String accessToken = servicesPresenter.getAccessToken();
    customToast(accessToken);
    if (accessToken == null) {
      startActivity(new Intent(this, RegisterSalonActivity.class));
    }
  }

  //init fragment management
  private FragmentTransaction initFragments() {
    FragmentTransaction fragmentTransaction;
    fragmentManager = getSupportFragmentManager();
    fragmentTransaction = fragmentManager.beginTransaction();
    return fragmentTransaction;
  }

  //run the account Fragment
  private void startSalonSerVice() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new ServicesFragment());
    fragmentTransaction.commit();
  }

  private void startActiveOrders() {
    FragmentTransaction fragmentTransaction = initFragments();
    fragmentTransaction.replace(R.id.fragment_container, new OrderSFragment());
    fragmentTransaction.commit();
  }
}
