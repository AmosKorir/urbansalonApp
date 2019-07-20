package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 7/16/19.
 * amoskrr@gmail.com
 */
public class AnalyticApiResponse {
  @SerializedName("date")
  private String date;

  @SerializedName("count")
  private String count;

  @SerializedName("total")

  private String total;
  @SerializedName("service")

  private ServiceAPiResponse serviceAPiResponse;

  public AnalyticApiResponse() {
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getCount() {
    return count;
  }

  public void setCount(String count) {
    this.count = count;
  }

  public String getTotal() {
    return total;
  }

  public void setTotal(String total) {
    this.total = total;
  }

  public ServiceAPiResponse getServiceAPiResponse() {
    return serviceAPiResponse;
  }

  public void setServiceAPiResponse(ServiceAPiResponse serviceAPiResponse) {
    this.serviceAPiResponse = serviceAPiResponse;
  }
}
