package com.app.remote.domain.models;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class Service {
  private String serviceid;
  private String salonid;
  private String name;
  private String price;
  private String status;
  private String avatar;
  private String createdAt;
  private String updatedAt;

  public Service() {
  }

  private Service(Builder builder) {
    setServiceid(builder.serviceid);
    setSalonid(builder.salonid);
    setName(builder.name);
    setPrice(builder.price);
    setStatus(builder.status);
    setAvatar(builder.avatar);
    setCreatedAt(builder.createdAt);
    setUpdatedAt(builder.updatedAt);
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

  public static final class Builder {
    private String serviceid;
    private String salonid;
    private String name;
    private String price;
    private String status;
    private String avatar;
    private String createdAt;
    private String updatedAt;

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

    public Builder withPrice(String val) {
      price = val;
      return this;
    }

    public Builder withStatus(String val) {
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

    public Service build() {
      return new Service(this);
    }
  }
}
