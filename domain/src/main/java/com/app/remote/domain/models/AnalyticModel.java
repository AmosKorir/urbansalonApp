package com.app.remote.domain.models;

/**
 * Created by Korir on 7/16/19.
 * amoskrr@gmail.com
 */
public class AnalyticModel {

  private String date;

  private String count;

  private String total;

  private Service service;

  public AnalyticModel() {
  }

  private AnalyticModel(Builder builder) {
    setDate(builder.date);
    setCount(builder.count);
    setTotal(builder.total);
    setService(builder.service);
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public static final class Builder {
    private String date;
    private String count;
    private String total;
    private Service service;

    private Builder() {
    }

    public Builder withDate(String val) {
      date = val;
      return this;
    }

    public Builder withCount(String val) {
      count = val;
      return this;
    }

    public Builder withTotal(String val) {
      total = val;
      return this;
    }

    public Builder withService(Service val) {
      service = val;
      return this;
    }

    public AnalyticModel build() {
      return new AnalyticModel(this);
    }
  }
}
