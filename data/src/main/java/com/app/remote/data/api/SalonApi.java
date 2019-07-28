package com.app.remote.data.api;

import com.app.remote.data.models.AnalyticApiResponse;
import com.app.remote.data.models.SalonApiResponse;
import com.app.remote.data.models.ServiceAPiResponse;
import com.app.remote.data.models.SuccessApiResponse;
import com.app.remote.data.models.customerorders.CustomerOrderResponse;
import com.app.remote.domain.constants.Constants;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import io.reactivex.Single;
import java.util.List;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

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

  @FormUrlEncoded
  @POST("order/status")
  Single<SuccessApiResponse> setOrderStatus(@Header(Constants.AUTHORIZATION) String accessToken,
      @Field("orderid") Integer orderid, @Field("status") int i);

  @GET("service/recommendation")
  Single<List<ServiceAPiResponse>> getRecommended(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/salon/active")
  Single<List<CustomerOrderResponse>> getActiveOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/salon/closed")
  Single<List<CustomerOrderResponse>> getClosedOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/salon/rejected")
  Single<List<CustomerOrderResponse>> getRejectedOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/salon/pending")
  Single<List<CustomerOrderResponse>> getPendingOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @FormUrlEncoded
  @POST("salon/login")
  Single<SalonModel> login(@Field("phone") String phone, @Field("password") String password);

  @GET("analytic/seven")
  Single<List<AnalyticApiResponse>> getSevenDay(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("salon/all")
  Single<List<SalonApiResponse>> getSalons();

  @GET("salon/get_salon")
  Single<SalonApiResponse> getSalonSelf(@Header(Constants.AUTHORIZATION) String accessToken);

  @FormUrlEncoded
  @POST("salon/update")
  Single<SuccessApiResponse> updateSalon(@Header(Constants.AUTHORIZATION) String accessToken,
      @Field("opening") String opening,
      @Field("closing") String closing,
      @Field("status") String availability);

  @FormUrlEncoded
  @POST("upload/salon")
  Single<SuccessApiResponse> uploadProfile(@Body RequestBody requestBody,
      @Header(Constants.AUTHORIZATION) String accessToken);

  @FormUrlEncoded
  @POST("service/status")
  Single<SuccessApiResponse> updateService(@Header(Constants.AUTHORIZATION) String accessToken,
      @Field("serviceid") String serviceid, @Field("status") String status);

  @GET("analytic/picount")
  Single<List<ServiceAPiResponse>> getPiData(@Header(Constants.AUTHORIZATION) String accessToken);
    @GET("service/all")
  Single<List<ServiceAPiResponse>> getAnUthServices();
}
