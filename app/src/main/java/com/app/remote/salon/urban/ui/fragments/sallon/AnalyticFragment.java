package com.app.remote.salon.urban.ui.fragments.sallon;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.models.AnalyticModel;
import com.app.remote.domain.models.CpieData;
import com.app.remote.presentation.salonpresenter.AlayticPresenter;
import com.app.remote.salon.urban.R;
import com.app.remote.salon.urban.ui.fragments.BaseFragment;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;

public class AnalyticFragment extends BaseFragment implements AlayticPresenter.MyView {
  private View view;
  @Inject AlayticPresenter alayticPresenter;
  @BindView(R.id.seven_day_booking) LineChart severDay;
  @BindView(R.id.piechart) PieChart pieChart;
  @BindView(R.id.seven_earning_booking) LineChart booking;
  @Inject @Named(DIConstants.ACTIVITY) Context context;

  public AnalyticFragment() {
    // Required empty public constructor
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    injector().inject(this);
  }

  private void setSevenDayChart(LineChart chart, List<AnalyticModel> analyticModels, Boolean type) {
    List<Entry> entries = new ArrayList<Entry>();
    final String[] quarters = new String[analyticModels.size()];
    ValueFormatter formatter = new ValueFormatter() {
      @Override
      public String getAxisLabel(float value, AxisBase axis) {
        return quarters[(int) value];
      }
    };

    for (int i = 0; i < analyticModels.size(); i++) {
      int total;
      if (type) {
        total = Integer.parseInt(analyticModels.get(i).getTotal());
      } else {
        total = Integer.parseInt(analyticModels.get(i).getCount());
      }

      entries.add(new Entry(i, total));
      quarters[i] = analyticModels.get(i).getDate();
    }

    LineDataSet dataSet = new LineDataSet(entries, "");
    dataSet.setColor(context.getResources().getColor(R.color.colorAccent));
    dataSet.setDrawValues(true);
    dataSet.setLineWidth(2f);
    dataSet.setDrawCircles(true);
    LineData lineData = new LineData(dataSet);
    lineData.setValueFormatter(formatter);
    chart.setData(lineData);

    YAxis rightYAxis = chart.getAxisRight();
    rightYAxis.setEnabled(false);
    YAxis rightYAxisL = chart.getAxisLeft();
    rightYAxisL.setEnabled(false);
    XAxis xAxis = chart.getXAxis();
    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
    xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
    xAxis.setValueFormatter(formatter);
    chart.getAxisLeft().setDrawGridLines(true);
    chart.getAxisRight().setDrawGridLines(false);
    chart.getXAxis().setDrawGridLines(true);
    chart.setAutoScaleMinMaxEnabled(true);
    Legend legend = chart.getLegend();
    chart.setBorderColor(R.color.greyColor);
    chart.setNoDataTextColor(R.color.greyColor);
    chart.setGridBackgroundColor(context.getResources().getColor(R.color.greyColor));
    legend.setEnabled(true);
    legend.setTextColor(context.getResources().getColor(R.color.greyColor));
    chart.animateXY(5000, 5000);
    chart.invalidate();
  }

  private void setPieChart(CpieData cpieData) {

    ArrayList NoOfEmp = new ArrayList();

    for (int i = 0; i < cpieData.getCount().length; i++) {
      NoOfEmp.add(new PieEntry(cpieData.getCount()[i], cpieData.getLabels()[i]));
    }
    PieDataSet dataSet = new PieDataSet(NoOfEmp, "Orders");
    PieData data = new PieData();

    data.setDataSet(dataSet);
    data.setValueFormatter(new PercentFormatter());
    pieChart.setData(data);
    dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
    pieChart.animateXY(5000, 5000);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    if (view == null) {
      view = inflater.inflate(R.layout.fragment_analytic, container, false);
      ButterKnife.bind(this, view);
    }
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    alayticPresenter.setView(this);
    alayticPresenter.getBookingSevenDays();
    alayticPresenter.getPieData();
  }

  @Override public void setSevenDay(List<AnalyticModel> analyticModels) {
    setSevenDayChart(severDay, analyticModels, true);
    setSevenDayChart(booking, analyticModels, false);
  }

  @Override public void setPieData(CpieData pieData) {
    setPieChart(pieData);
  }
}
