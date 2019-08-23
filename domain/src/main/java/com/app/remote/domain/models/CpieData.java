package com.app.remote.domain.models;

/**
 * Created by Korir on 7/20/19.
 * amoskrr@gmail.com
 */
public class CpieData {
  private int count [];
  private String labels[];

  public CpieData() {
  }

  private CpieData(Builder builder) {
    setCount(builder.count);
    setLabels(builder.labels);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int[] getCount() {
    return count;
  }

  public void setCount(int[] count) {
    this.count = count;
  }

  public String[] getLabels() {
    return labels;
  }

  public void setLabels(String[] labels) {
    this.labels = labels;
  }

  public static final class Builder {
    private int[] count;
    private String[] labels;

    private Builder() {
    }

    public Builder withCount(int[] val) {
      count = val;
      return this;
    }

    public Builder withLabels(String[] val) {
      labels = val;
      return this;
    }

    public CpieData build() {
      return new CpieData(this);
    }
  }
}
