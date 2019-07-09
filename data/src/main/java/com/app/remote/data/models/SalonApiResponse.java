package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class SalonApiResponse {

  @SerializedName("name")

  private String name;
  @SerializedName("phone")

  private String phone;
  @SerializedName("salonid")

  private String salonid;
  @SerializedName("password")

  private String password;
  @SerializedName("location")

  private String location;
  @SerializedName("latitude")

  private String latitude;
  @SerializedName("longitude")

  private String longitude;
  @SerializedName("accesstoken")

  private String accesstoken;
  @SerializedName("updatedAt")

  private String updatedAt;
  @SerializedName("createdAt")

  private String createdAt;
  @SerializedName("avatar")

  private String avatar;
  @SerializedName("status")

  private String status;
  @SerializedName("openingtime")

  private String openingtime;
  @SerializedName("closingtime")

  private String closingtime;

  public SalonApiResponse() {
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

  public String getSalonid() {
    return salonid;
  }

  public void setSalonid(String salonid) {
    this.salonid = salonid;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
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

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getOpeningtime() {
    return openingtime;
  }

  public void setOpeningtime(String openingtime) {
    this.openingtime = openingtime;
  }

  public String getClosingtime() {
    return closingtime;
  }

  public void setClosingtime(String closingtime) {
    this.closingtime = closingtime;
  }
}
