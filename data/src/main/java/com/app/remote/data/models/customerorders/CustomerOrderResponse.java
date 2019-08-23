package com.app.remote.data.models.customerorders;

import com.app.remote.data.models.CustomerApiResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 7/5/19.
 * amoskrr@gmail.com
 */
public class CustomerOrderResponse {

  @SerializedName("orderid")
  
  private Integer orderid;
  @SerializedName("customerid")
  
  private String customerid;
  @SerializedName("serviceid")
  
  private String serviceid;
  @SerializedName("orderno")
  
  private String orderno;
  @SerializedName("status")
  
  private Integer status;
  @SerializedName("timebooked")
  
  private String timebooked;
  @SerializedName("datebooked")
  
  private String datebooked;
  @SerializedName("createdAt")
  
  private String createdAt;
  @SerializedName("updatedAt")
  
  private String updatedAt;
  @SerializedName("service")
  
  private ServiceCustomerOrderResponse service;
  @SerializedName("customer")
  
  private CustomerApiResponse customer;

  public CustomerOrderResponse() {
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

  public ServiceCustomerOrderResponse getService() {
    return service;
  }

  public void setService(ServiceCustomerOrderResponse service) {
    this.service = service;
  }

  public CustomerApiResponse getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerApiResponse customer) {
    this.customer = customer;
  }
}
