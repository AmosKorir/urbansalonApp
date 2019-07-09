package com.app.remote.data.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class SuccessApiResponse {
  @SerializedName("status")
  boolean Status ;

  public SuccessApiResponse() {
  }

  public boolean isStatus() {
    return Status;
  }

  public void setStatus(boolean status) {
    Status = status;
  }
}
