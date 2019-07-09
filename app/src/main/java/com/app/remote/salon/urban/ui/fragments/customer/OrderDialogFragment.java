package com.app.remote.salon.urban.ui.fragments.customer;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.presentation.customerpresenters.CustomerPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.activities.CustomerLoginActivity;
import com.app.remote.salon.urban.ui.fragments.BaseBottomSheetFragment;
import com.app.remote.salon.urban.ui.fragments.BaseDialogFragment;
import java.util.Calendar;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * A simple {@link Fragment} subclass.
 */
public class OrderDialogFragment extends BaseBottomSheetFragment implements CustomerPresenter.MyView {
  private View view;
  @Inject CustomerPresenter presenter;
  @Inject @Named(DIConstants.ACTIVITY) Context context;
  @BindView(R.id.time) TextView timeTxt;
  @BindView(R.id.date) TextView dateTxt;
  private int year, month, day;
  private int hour, minutes;
  private DatePicker datePicker;
  private Calendar calendar;
  private String serviceId = Constants.EMPTY_STRING;
  private String dateStr = Constants.EMPTY_STRING;
  private String timeStr = Constants.EMPTY_STRING;

  public OrderDialogFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
    if (getArguments() != null) {
      serviceId = getArguments().getString(Constants.SERVICEID);
    }
  }

  public static OrderDialogFragment newInstance(String serviceId) {
    OrderDialogFragment fragment = new OrderDialogFragment();
    Bundle args = new Bundle();
    args.putString(Constants.SERVICEID, serviceId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    calendar = Calendar.getInstance();
    year = calendar.get(Calendar.YEAR);
    month = calendar.get(Calendar.MONTH);
    day = calendar.get(Calendar.DAY_OF_MONTH);
    hour = calendar.getTime().getHours();
    minutes = calendar.getTime().getMinutes();
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_order_dialog, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @OnClick(R.id.timeBtn) public void timePicker() {
    TimePickerDialog t = new TimePickerDialog(context, timeSetListener, hour, minutes, true);

    t.show();
  }

  @OnClick(R.id.dateBtn) public void datePicker() {
    DatePickerDialog d = new DatePickerDialog(context, myDateListener, year, month, day);
    d.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    d.show();
  }

  private DatePickerDialog.OnDateSetListener myDateListener = (arg0, arg1, arg2, arg3) -> {
    // TODO Auto-generated method stub
    // arg1 = year
    // arg2 = month
    // arg3 = day
    showDate(arg1, arg2 + 1, arg3);
  };

  @OnClick(R.id.bookbtn) public void bookService() {

    presenter.bookService(serviceId, dateTxt.getText().toString(), timeTxt.getText().toString());
  }

  private TimePickerDialog.OnTimeSetListener timeSetListener =
      new TimePickerDialog.OnTimeSetListener() {
        @Override public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
          hour = hourOfDay;
          minutes = minute;
          showTimer(hourOfDay, minute);
        }
      };

  private void showDate(int year, int month, int day) {
    dateTxt.setText(day + "/" + month + "/" + year);
  }

  @Override
  public void onResume() {
    // Get existing layout params for the window
    ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();
    // Assign window properties to fill the parent
    params.width = WindowManager.LayoutParams.MATCH_PARENT;
    params.height = WindowManager.LayoutParams.MATCH_PARENT;
    getDialog().getWindow().setAttributes((android.view.WindowManager.LayoutParams) params);

    // Call super onResume after sizing
    super.onResume();
  }

  @Override public void onStart() {
    super.onStart();
    presenter.setView(this);
    showDate(year, month, day);
    showTimer(hour, minutes);
  }

  private void showTimer(int hour, int minutes) {
    timeTxt.setText(hour + ":" + minutes);
  }

  @Override public void requestLogin() {
    startActivity(new Intent(context, CustomerLoginActivity.class));
  }

  @Override public void loginedIn() {

  }

  @Override public void bookedStatus(OrderModel orderModel) {
    Toast.makeText(context, "booked successful", Toast.LENGTH_SHORT).show();
    this.dismiss();
  }

  @Override public void orders(List<CustomerOrder> customerOrders) {

  }
}
