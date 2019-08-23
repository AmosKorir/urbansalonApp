package com.app.remote.data.mappers;

import com.app.remote.data.models.ServiceAPiResponse;
import com.app.remote.domain.models.Service;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class ServiceMapper {
  public static Service transform(ServiceAPiResponse serviceAPiResponse){
    return Service.newBuilder()
        .withAvatar(serviceAPiResponse.getAvatar())
        .withName(serviceAPiResponse.getName())
        .withPrice(serviceAPiResponse.getPrice())
        .withSalonid(serviceAPiResponse.getSalonid())
        .withServiceid(serviceAPiResponse.getServiceid())
        .withStatus(serviceAPiResponse.getStatus())
        .build();

  }
}
