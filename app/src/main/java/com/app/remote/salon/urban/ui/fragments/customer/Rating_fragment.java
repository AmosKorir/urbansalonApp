package com.app.remote.salon.urban.ui.fragments.customer;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.remote.domain.models.Sucess;
import com.app.remote.presentation.customerpresenters.RatingPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import javax.inject.Inject;

public class Rating_fragment extends BaseDialogFragment implements RatingPresenter.MyView {
  private View view;
  private static String serviceId = "param1";
  private OnFragmentInteractionListener mListener;
  @BindView(R.id.ratingBar) RatingBar ratingBar;
  @Inject RatingPresenter ratingPresenter;

  public Rating_fragment() {
    // Required empty public constructor
  }

  // TODO: Rename and change types and number of parameters
  public static Rating_fragment newInstance(String param1) {
    Rating_fragment fragment = new Rating_fragment();
    Bundle args = new Bundle();
    args.putString("service_id", param1);
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onStart() {
    super.onStart();
    ratingPresenter.setView(this);
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      serviceId = getArguments().getString("service_id");
    }
    injector().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_rating_fragment, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @OnClick(R.id.ok)public  void submit(){
    ratingPresenter.rateService(serviceId,String.valueOf(ratingBar.getRating()));
    dismiss();
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  @Override public void success(Sucess sucess) {
    //dismiss();
  }

  public interface OnFragmentInteractionListener {
    // TODO: Update argument type and name
    void onFragmentInteraction(Uri uri);
  }
}
