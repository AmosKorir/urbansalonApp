package com.app.remote.data.mappers;

import com.app.remote.data.models.CustomerApiResponse;
import com.app.remote.domain.models.CustomerModel;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class CustomerMapper {
  public static CustomerModel transform(CustomerApiResponse customerApiResponse){
    return CustomerModel.newBuilder()
        .withAccesstoken(customerApiResponse.getAccesstoken())
        .withCustomerid(customerApiResponse.getCustomerid())
        .withName(customerApiResponse.getName())
        .withPhone(customerApiResponse.getPhone())
        .withAvatar(customerApiResponse.getAvatar())
        .build();
  }
}
