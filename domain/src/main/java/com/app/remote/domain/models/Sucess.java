package com.app.remote.domain.models;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class Sucess {
  private boolean status;

  public Sucess() {
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  private Sucess(Builder builder) {
    status = builder.status;
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public static final class Builder {
    private boolean status;

    private Builder() {
    }

    public Builder withStatus(boolean val) {
      status = val;
      return this;
    }

    public Sucess build() {
      return new Sucess(this);
    }
  }
}
