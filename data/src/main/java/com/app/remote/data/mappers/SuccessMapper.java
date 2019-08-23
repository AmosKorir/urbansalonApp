package com.app.remote.data.mappers;

import com.app.remote.data.models.SuccessApiResponse;
import com.app.remote.domain.models.Sucess;

/**
 * Created by Korir on 6/7/19.
 * amoskrr@gmail.com
 */
public class SuccessMapper {
  public static Sucess transform(SuccessApiResponse successApiResponse){
    return  Sucess.newBuilder().withStatus(successApiResponse.isStatus()).build();
  }
}
