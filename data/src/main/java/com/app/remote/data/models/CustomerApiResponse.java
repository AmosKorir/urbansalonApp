package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class CustomerApiResponse {

  @SerializedName("name")
  
  private String name;
  @SerializedName("phone")
  
  private String phone;
  @SerializedName("customerid")
  
  private String customerid;
  @SerializedName("password")
  
  private String password;
  @SerializedName("accesstoken")
  
  private String accesstoken;
  @SerializedName("updatedAt")
  
  private String updatedAt;
  @SerializedName("createdAt")
  
  private String createdAt;
  @SerializedName("avatar")
  
  private String avatar;

  public CustomerApiResponse() {
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getCustomerid() {
    return customerid;
  }

  public void setCustomerid(String customerid) {
    this.customerid = customerid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAccesstoken() {
    return accesstoken;
  }

  public void setAccesstoken(String accesstoken) {
    this.accesstoken = accesstoken;
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

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}
