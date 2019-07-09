package com.app.remote.data.models.customerorders;

import com.app.remote.data.models.SalonApiResponse;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class ServiceCustomerOrderResponse {

  @SerializedName("serviceid")

  private String serviceid;
  @SerializedName("salonid")

  private String salonid;
  @SerializedName("name")

  private String name;
  @SerializedName("price")

  private Integer price;
  @SerializedName("status")

  private Integer status;
  @SerializedName("avatar")

  private String avatar;
  @SerializedName("createdAt")

  private String createdAt;
  @SerializedName("updatedAt")

  private String updatedAt;
  @SerializedName("salon")

  private SalonApiResponse salon;

  public ServiceCustomerOrderResponse() {
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

  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
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

  public SalonApiResponse getSalon() {
    return salon;
  }

  public void setSalon(SalonApiResponse salon) {
    this.salon = salon;
  }
}
