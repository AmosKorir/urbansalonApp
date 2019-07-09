package com.app.remote.domain.models;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class OrderModel {

  private String orderid;
  private String orderno;
  private String serviceid;
  private String customerid;
  private String timebooked;
  private String datebooked;
  private String status;
  private String updatedAt;
  private String createdAt;

  public OrderModel() {
  }

  private OrderModel(Builder builder) {
    setOrderid(builder.orderid);
    setOrderno(builder.orderno);
    setServiceid(builder.serviceid);
    setCustomerid(builder.customerid);
    setTimebooked(builder.timebooked);
    setDatebooked(builder.datebooked);
    setStatus(builder.status);
    setUpdatedAt(builder.updatedAt);
    setCreatedAt(builder.createdAt);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public String getOrderid() {
    return orderid;
  }

  public void setOrderid(String orderid) {
    this.orderid = orderid;
  }

  public String getOrderno() {
    return orderno;
  }

  public void setOrderno(String orderno) {
    this.orderno = orderno;
  }

  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }

  public String getCustomerid() {
    return customerid;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
  }

  public String getTimebooked() {
    return timebooked;
  }

  public void setTimebooked(String timebooked) {
    this.timebooked = timebooked;
  }

  public String getDatebooked() {
    return datebooked;
  }

  public void setDatebooked(String datebooked) {
    this.datebooked = datebooked;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public static final class Builder {
    private String orderid;
    private String orderno;
    private String serviceid;
    private String customerid;
    private String timebooked;
    private String datebooked;
    private String status;
    private String updatedAt;
    private String createdAt;

    private Builder() {
    }

    public Builder withOrderid(String val) {
      orderid = val;
      return this;
    }

    public Builder withOrderno(String val) {
      orderno = val;
      return this;
    }

    public Builder withServiceid(String val) {
      serviceid = val;
      return this;
    }

    public Builder withCustomerid(String val) {
      customerid = val;
      return this;
    }

    public Builder withTimebooked(String val) {
      timebooked = val;
      return this;
    }

    public Builder withDatebooked(String val) {
      datebooked = val;
      return this;
    }

    public Builder withStatus(String val) {
      status = val;
      return this;
    }

    public Builder withUpdatedAt(String val) {
      updatedAt = val;
      return this;
    }

    public Builder withCreatedAt(String val) {
      createdAt = val;
      return this;
    }

    public OrderModel build() {
      return new OrderModel(this);
    }
  }
}
