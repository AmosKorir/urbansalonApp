package com.app.remote.data.mappers;

import com.app.remote.data.models.OrderModelResponse;
import com.app.remote.domain.models.OrderModel;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class OrderMapper {
  public static OrderModel transform(OrderModelResponse orderModelResponse){
    return OrderModel.newBuilder()
        .withOrderid(orderModelResponse.getOrderid())
        .withOrderno(orderModelResponse.getOrderno())
        .withCustomerid(orderModelResponse.getCustomerid())
        .withServiceid(orderModelResponse.getServiceid())
        .withDatebooked(orderModelResponse.getDatebooked())
        .withTimebooked(orderModelResponse.getTimebooked())
        .build();
  }
}
