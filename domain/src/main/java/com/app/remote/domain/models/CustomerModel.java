package com.app.remote.domain.models;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class CustomerModel {
  private String name;
  private String phone;
  private String customerid;
  private String accesstoken;
  private String updatedAt;
  private String createdAt;
  private String avatar;

  public CustomerModel() {
  }

  private CustomerModel(Builder builder) {
    setName(builder.name);
    setPhone(builder.phone);
    setCustomerid(builder.customerid);
    setAccesstoken(builder.accesstoken);
    setUpdatedAt(builder.updatedAt);
    setCreatedAt(builder.createdAt);
    setAvatar(builder.avatar);
  }

  public static Builder newBuilder() {
    return new Builder();
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

  public static final class Builder {
    private String name;
    private String phone;
    private String customerid;
    private String accesstoken;
    private String updatedAt;
    private String createdAt;
    private String avatar;

    private Builder() {
    }

    public Builder withName(String val) {
      name = val;
      return this;
    }

    public Builder withPhone(String val) {
      phone = val;
      return this;
    }

    public Builder withCustomerid(String val) {
      customerid = val;
      return this;
    }

    public Builder withAccesstoken(String val) {
      accesstoken = val;
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

    public Builder withAvatar(String val) {
      avatar = val;
      return this;
    }

    public CustomerModel build() {
      return new CustomerModel(this);
    }
  }
}
