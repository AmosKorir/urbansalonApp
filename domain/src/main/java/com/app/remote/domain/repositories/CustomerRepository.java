package com.app.remote.domain.repositories;

import com.app.remote.domain.models.CustomerModel;
import com.app.remote.domain.models.OrderModel;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.sun.net.httpserver.Authenticator;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.List;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public interface CustomerRepository {
  Single<CustomerModel> registerCustomer(String name, String phone, String password);

  Single<CustomerModel> loginCustomer(String phone, String password);

 Single<OrderModel> bookService(String accessToken, String serviceId,String date, String time);

  Single<CustomerModel> getProfile(String accessToken);

  Single<List<CustomerOrder>> getCustomerOrder(String accessToken);

  Single<OrderModel> cancelOrder(String accessToken, String orderId);
}
