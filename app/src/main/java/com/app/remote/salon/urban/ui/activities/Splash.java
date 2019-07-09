package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.app.remote.domain.repositories.SalonRepository;
import com.app.remote.salon.urban.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startActivity(new Intent(this,MainDashBoadActivity.class));
        finish();
    }
}
