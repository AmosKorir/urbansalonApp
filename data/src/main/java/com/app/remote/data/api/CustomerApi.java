package com.app.remote.data.api;

import com.app.remote.data.models.CustomerApiResponse;
import com.app.remote.data.models.OrderModelResponse;
import com.app.remote.data.models.customerorders.CustomerOrderResponse;
import com.app.remote.domain.constants.Constants;
import io.reactivex.Single;
import java.util.List;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public interface CustomerApi {
  @FormUrlEncoded
  @POST("customer") Single<CustomerApiResponse> createCustomer(
      @Field("name") String name,
      @Field("phone") String phone,
      @Field("password") String password
  );

  @FormUrlEncoded
  @POST("customer/login")
  Single<CustomerApiResponse> loginCustomer(@Field("phone") String phone,
      @Field("password") String password);

  @FormUrlEncoded
  @POST("order")
  Single<OrderModelResponse> bookService(
      @Header(Constants.AUTHORIZATION) String accessToken,
      @Field("serviceid") String serviceid,
      @Field("datebooked") String date,
      @Field("timebooked") String time);

  @GET("customer/get_user")
  Single<CustomerApiResponse> getProfile(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/customer/active")
  Single<List<CustomerOrderResponse>> getCustomerOrders(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @GET("order/customer/closed")
  Single<List<CustomerOrderResponse>> getCustomerOrdersClose(
      @Header(Constants.AUTHORIZATION) String accessToken);

  @POST("order/changeStatus")
  Single<OrderModelResponse> cancelOrder(@Header(Constants.AUTHORIZATION) String accessToken,
      String orderId);
}
