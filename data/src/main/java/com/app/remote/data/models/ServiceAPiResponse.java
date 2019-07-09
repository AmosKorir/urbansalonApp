package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class ServiceAPiResponse {

  @SerializedName("serviceid")
  
  private String serviceid;
  @SerializedName("salonid")
  
  private String salonid;
  @SerializedName("name")
  
  private String name;
  @SerializedName("price")
  
  private String price;
  @SerializedName("status")
  
  private String status;
  @SerializedName("avatar")
  
  private String avatar;
  @SerializedName("createdAt")
  
  private String createdAt;
  @SerializedName("updatedAt")
  
  private String updatedAt;

  public ServiceAPiResponse() {
  }

  public String getServiceid() {
    return serviceid;
  }

  public void setServiceid(String serviceid) {
    this.serviceid = serviceid;
  }

  public String getSalonid() {
    return salonid;
  }

  public void setSalonid(String salonid) {
    this.salonid = salonid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
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
}
