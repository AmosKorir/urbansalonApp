package com.app.remote.data.api;

import com.app.remote.data.models.SalonApiResponse;
import com.app.remote.data.models.ServiceAPiResponse;
import com.app.remote.data.models.SuccessApiResponse;
import com.app.remote.data.models.customerorders.CustomerOrderResponse;
import com.app.remote.domain.constants.Constants;
import io.reactivex.Single;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public interface SalonApi {
  @FormUrlEncoded
  @POST("salon/register") Single<SalonApiResponse> createSalon(
      @Field("name") String name,
      @Field("phone") String phone,
      @Field("password") String password,
      @Field("location") String location,
      @Field("longitude") String longitude,
      @Field("latitude") String latitude

  );

  @FormUrlEncoded
  @POST("service/create")
  Single<ServiceAPiResponse> createService(
      @Header(Constants.AUTHORIZATION) String accessToken,
      @Field("name") String name,
      @Field("price") String price);

  @GET("service/salon_self")
  Single<List<ServiceAPiResponse>> getAllServices(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @POST("image/upload/service")
  Single<SuccessApiResponse> uploadServiceImage(@Body RequestBody body);

  @GET("order/salon")
  Single<List<CustomerOrderResponse>> getActiveOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @FormUrlEncoded
  @PUT("order/status")
  Single<SuccessApiResponse> setOrderStatus(@Header(Constants.AUTHORIZATION) String accessToken,
      @Field("orderid") Integer orderid, @Field("status") int i);
}
