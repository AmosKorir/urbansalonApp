package com.app.remote.salon.urban.ui.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.app.remote.salon.urban.R;
import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.model.SliderPage;

public class IntroActivity extends AppIntro {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    SliderPage sliderPage = new SliderPage();


    addSlide(SampleSlide.newInstance(R.layout.fragment_introone));
    addSlide(SampleSlide.newInstance(R.layout.fragment_introtwo));
    addSlide(SampleSlide.newInstance(R.layout.fragment_intothree));
    addSlide(SampleSlide.newInstance(R.layout.fragment_share));

    showSkipButton(true);
    setProgressButtonEnabled(true);
  }

  @Override
  public void onSkipPressed(Fragment currentFragment) {
    super.onSkipPressed(currentFragment);
    // Do something when users tap on Skip button.
    start();
  }

  @Override
  public void onDonePressed(Fragment currentFragment) {
    super.onDonePressed(currentFragment);
    // Do something when users tap on Done button.
    start();
  }

  @Override
  public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
    super.onSlideChanged(oldFragment, newFragment);
    // Do something when the slide changes.
  }

  private void start(){
    SharedPreferences sharedPreferences=getSharedPreferences("intro",MODE_PRIVATE);
    SharedPreferences.Editor edit=sharedPreferences.edit();
    edit.putInt("value",1);
    edit.commit();
    startActivity(new Intent(this,MainDashBoadActivity.class));

  }
}
