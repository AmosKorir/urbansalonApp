package com.app.remote.salon.urban.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.adapters.ServicesRecyclerViewAdapter;

public class SalonDetailsActivity extends BaseActivity {
  @BindView(R.id.serviceRecyclerView) RecyclerView serviceRecyclerView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salon_details);
    injector().inject(this);
  }

  @Override protected void onStart() {
    super.onStart();
    initRecyclerView();
  }

  private void initRecyclerView() {
    serviceRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    //serviceRecyclerView.setAdapter(new ServicesRecyclerViewAdapter(this));
  }
}
