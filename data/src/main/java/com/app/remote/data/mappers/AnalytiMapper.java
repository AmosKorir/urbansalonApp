package com.app.remote.data.mappers;

import com.app.remote.data.models.AnalyticApiResponse;
import com.app.remote.domain.models.AnalyticModel;

/**
 * Created by Korir on 7/16/19.
 * amoskrr@gmail.com
 */
public class AnalytiMapper {
  public static AnalyticModel transform(AnalyticApiResponse analyticApiResponse){
    return AnalyticModel.newBuilder()
        .withCount(analyticApiResponse.getCount())
        .withTotal(analyticApiResponse.getTotal())
        .withDate(analyticApiResponse.getDate())
        .withService(ServiceMapper.transform(analyticApiResponse.getServiceAPiResponse()))
        .build();
  }
}
