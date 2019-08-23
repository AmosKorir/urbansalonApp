package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class OrderModelResponse {

  @SerializedName("orderid")
  
  private String orderid;
  @SerializedName("orderno")
  
  private String orderno;
  @SerializedName("serviceid")
  
  private String serviceid;
  @SerializedName("customerid")
  
  private String customerid;
  @SerializedName("timebooked")
  
  private String timebooked;
  @SerializedName("datebooked")
  
  private String datebooked;
  @SerializedName("status")
  
  private String status;
  @SerializedName("updatedAt")
  
  private String updatedAt;
  @SerializedName("createdAt")
  
  private String createdAt;

  public OrderModelResponse() {
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
}
