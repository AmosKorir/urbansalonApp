package com.app.remote.data.mappers;

import com.app.remote.data.models.SalonApiResponse;
import com.app.remote.domain.models.SalonModel;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class SalonMapper {
  public static SalonModel transform(SalonApiResponse salonApiResponse){
    return SalonModel.newBuilder()
        .withAccesstoken(salonApiResponse.getAccesstoken())
        .withAvatar(salonApiResponse.getAvatar())
        .withName(salonApiResponse.getName())
        .withLatitude(salonApiResponse.getLatitude())
        .withLatitude(salonApiResponse.getLatitude())
        .withLocation(salonApiResponse.getLocation())
        .withClosingtime(salonApiResponse.getClosingtime())
        .withOpeningtime(salonApiResponse.getOpeningtime())
        .withStatus(salonApiResponse.getStatus())
        .withPhone(salonApiResponse.getPhone())
        .withSalonid(salonApiResponse.getSalonid())
        .build();
  }
}
