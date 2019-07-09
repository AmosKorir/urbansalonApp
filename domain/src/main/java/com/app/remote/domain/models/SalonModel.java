package com.app.remote.domain.models;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class SalonModel {

  private String name;
  private String phone;
  private String salonid;
  private String location;
  private String latitude;
  private String longitude;
  private String accesstoken;
  private String updatedAt;
  private String createdAt;
  private String avatar;
  private String status;
  private String openingtime;
  private String closingtime;

  public SalonModel() {
  }

  private SalonModel(Builder builder) {
    setName(builder.name);
    setPhone(builder.phone);
    setSalonid(builder.salonid);
    setLocation(builder.location);
    setLatitude(builder.latitude);
    setLongitude(builder.longitude);
    setAccesstoken(builder.accesstoken);
    setUpdatedAt(builder.updatedAt);
    setCreatedAt(builder.createdAt);
    setAvatar(builder.avatar);
    setStatus(builder.status);
    setOpeningtime(builder.openingtime);
    setClosingtime(builder.closingtime);
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

  public String getSalonid() {
    return salonid;
  }

  public void setSalonid(String salonid) {
    this.salonid = salonid;
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

  public static final class Builder {
    private String name;
    private String phone;
    private String salonid;
    private String location;
    private String latitude;
    private String longitude;
    private String accesstoken;
    private String updatedAt;
    private String createdAt;
    private String avatar;
    private String status;
    private String openingtime;
    private String closingtime;

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

    public Builder withSalonid(String val) {
      salonid = val;
      return this;
    }

    public Builder withLocation(String val) {
      location = val;
      return this;
    }

    public Builder withLatitude(String val) {
      latitude = val;
      return this;
    }

    public Builder withLongitude(String val) {
      longitude = val;
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

    public Builder withStatus(String val) {
      status = val;
      return this;
    }

    public Builder withOpeningtime(String val) {
      openingtime = val;
      return this;
    }

    public Builder withClosingtime(String val) {
      closingtime = val;
      return this;
    }

    public SalonModel build() {
      return new SalonModel(this);
    }
  }
}
