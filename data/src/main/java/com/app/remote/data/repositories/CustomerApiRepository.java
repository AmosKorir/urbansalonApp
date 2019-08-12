package com.app.remote.data.repositories;

import com.app.remote.data.api.CustomerApi;
import com.app.remote.data.mappers.CustomerMapper;
import com.app.remote.data.mappers.CustomerOrderMapper;
import com.app.remote.data.mappers.OrderMapper;
import com.app.remote.data.mappers.SuccessMapper;
import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.repositories.CustomerRepository;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.io.File;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public class CustomerApiRepository implements CustomerRepository {
  CustomerApi customerApi;

  public CustomerApiRepository(CustomerApi customerApi) {
    this.customerApi = customerApi;
  }

  @Override
  public Single<CustomerModel> registerCustomer(String name, String phone, String password) {
    return customerApi.createCustomer(name, phone, password)
        .map(CustomerMapper::transform);
  }

  @Override public Single<CustomerModel> loginCustomer(String phone, String password) {
    return customerApi.loginCustomer(phone, password)
        .map(CustomerMapper::transform);
  }

  @Override public Single<OrderModel> bookService(String accessToken, String serviceid, String date,
      String time) {
    return customerApi.bookService(accessToken, serviceid, date, time).map(OrderMapper::transform);
  }

  @Override public Single<CustomerModel> getProfile(String accessToken) {
    return customerApi.getProfile(accessToken).map(CustomerMapper::transform);
  }

  @Override public Single<List<CustomerOrder>> getCustomerOrder(String accessToken) {
    return customerApi.getCustomerOrders(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override public Single<OrderModel> cancelOrder(String accessToken, String orderId) {
    return customerApi.cancelOrder(accessToken, orderId)
        .map(OrderMapper::transform);
  }

  @Override public Single<List<CustomerOrder>> getHistory(String accessToken) {
    return customerApi.getCustomerOrdersClose(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override
  public Single<Sucess> setOrderStatus(String acceessToken, Integer orderId, String status) {
    return customerApi.changeOrderStatus(acceessToken, orderId, Integer.parseInt(status)).map(
        SuccessMapper::transform);
  }

  @Override public Single<Sucess> uploadProfile(File destination, String accessToken) {
    MultipartBody.Builder builder = new MultipartBody.Builder();
    builder.setType(MultipartBody.FORM);
    builder.addFormDataPart("accessToken", accessToken);
    builder.addFormDataPart("file", destination.getName(),
        RequestBody.create(MediaType.parse("image/*"), destination));
    MultipartBody requestBody = builder.build();
    return customerApi.uploadProfile(requestBody)
        .map(SuccessMapper::transform);
  }

  @Override public Single<Sucess> rate(String accessToken, String serviceId, String rating) {
    return customerApi.rate(accessToken,serviceId,rating)
        .map(SuccessMapper::transform);
  }
}
