package com.app.remote.domain.models.customerOrders;

import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.models.Service;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class CustomerOrder {
  private Integer orderid;
  private String customerid;
  private String serviceid;
  private String orderno;
  private Integer status;
  private String timebooked;
  private String datebooked;
  private String createdAt;
  private String updatedAt;
  private CustomerServiceOrder service;
  private CustomerModel customer;

  public CustomerOrder() {
  }

  private CustomerOrder(Builder builder) {
    setOrderid(builder.orderid);
    setCustomerid(builder.customerid);
    setServiceid(builder.serviceid);
    setOrderno(builder.orderno);
    setStatus(builder.status);
    setTimebooked(builder.timebooked);
    setDatebooked(builder.datebooked);
    setCreatedAt(builder.createdAt);
    setUpdatedAt(builder.updatedAt);
    setService(builder.service);
    setCustomer(builder.customer);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public Integer getOrderid() {
    return orderid;
  }

  public void setOrderid(Integer orderid) {
    this.orderid = orderid;
  }

  public String getCustomerid() {
    return customerid;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
  }

  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }

  public String getOrderno() {
    return orderno;
  }

  public void setOrderno(String orderno) {
    this.orderno = orderno;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
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

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }

  public CustomerServiceOrder getService() {
    return service;
  }

  public void setService(CustomerServiceOrder service) {
    this.service = service;
  }

  public CustomerModel getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerModel customer) {
    this.customer = customer;
  }

  public static final class Builder {
    private Integer orderid;
    private String customerid;
    private String serviceid;
    private String orderno;
    private Integer status;
    private String timebooked;
    private String datebooked;
    private String createdAt;
    private String updatedAt;
    private CustomerServiceOrder service;
    private CustomerModel customer;

    private Builder() {
    }

    public Builder withOrderid(Integer val) {
      orderid = val;
      return this;
    }

    public Builder withCustomerid(String val) {
      customerid = val;
      return this;
    }

    public Builder withServiceid(String val) {
      serviceid = val;
      return this;
    }

    public Builder withOrderno(String val) {
      orderno = val;
      return this;
    }

    public Builder withStatus(Integer val) {
      status = val;
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

    public Builder withCreatedAt(String val) {
      createdAt = val;
      return this;
    }

    public Builder withUpdatedAt(String val) {
      updatedAt = val;
      return this;
    }

    public Builder withService(CustomerServiceOrder val) {
      service = val;
      return this;
    }

    public Builder withCustomer(CustomerModel val) {
      customer = val;
      return this;
    }

    public CustomerOrder build() {
      return new CustomerOrder(this);
    }
  }
}
