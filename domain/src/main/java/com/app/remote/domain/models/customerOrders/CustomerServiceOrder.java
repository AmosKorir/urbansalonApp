package com.app.remote.domain.models.customerOrders;

import com.app.remote.domain.models.SalonModel;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class CustomerServiceOrder {
  private String serviceid;
  private String salonid;
  private String name;
  private Integer price;
  private Integer status;
  private String avatar;
  private String createdAt;
  private String updatedAt;
  private SalonModel salon;

  public CustomerServiceOrder() {
  }

  private CustomerServiceOrder(Builder builder) {
    setServiceid(builder.serviceid);
    setSalonid(builder.salonid);
    setName(builder.name);
    setPrice(builder.price);
    setStatus(builder.status);
    setAvatar(builder.avatar);
    setCreatedAt(builder.createdAt);
    setUpdatedAt(builder.updatedAt);
    setSalon(builder.salon);
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public SalonModel getSalon() {
    return salon;
  }

  public void setSalon(SalonModel salon) {
    this.salon = salon;
  }

  public static final class Builder {
    private String serviceid;
    private String salonid;
    private String name;
    private Integer price;
    private Integer status;
    private String avatar;
    private String createdAt;
    private String updatedAt;
    private SalonModel salon;

    private Builder() {
    }

    public Builder withServiceid(String val) {
      serviceid = val;
      return this;
    }

    public Builder withSalonid(String val) {
      salonid = val;
      return this;
    }

    public Builder withName(String val) {
      name = val;
      return this;
    }

    public Builder withPrice(Integer val) {
      price = val;
      return this;
    }

    public Builder withStatus(Integer val) {
      status = val;
      return this;
    }

    public Builder withAvatar(String val) {
      avatar = val;
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

    public Builder withSalon(SalonModel val) {
      salon = val;
      return this;
    }

    public CustomerServiceOrder build() {
      return new CustomerServiceOrder(this);
    }
  }
}
