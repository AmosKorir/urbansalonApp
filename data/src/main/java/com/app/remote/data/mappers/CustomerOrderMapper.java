package com.app.remote.data.mappers;

import com.app.remote.data.models.customerorders.CustomerOrderResponse;
import com.app.remote.data.models.customerorders.ServiceCustomerOrderResponse;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.models.customerOrders.CustomerServiceOrder;

/**
 * Created by Korir on 7/6/19.
 * amoskrr@gmail.com
 */
public class CustomerOrderMapper {
  public static CustomerOrder transform(CustomerOrderResponse  customerOrderResponse){
    return CustomerOrder.newBuilder()
        .withCustomer(CustomerMapper.transform(customerOrderResponse.getCustomer()))
        .withService(transform(customerOrderResponse.getService()))
        .withOrderno(customerOrderResponse.getOrderno())
        .withDatebooked(customerOrderResponse.getDatebooked())
        .withOrderid(customerOrderResponse.getOrderid())
        .withStatus(customerOrderResponse.getStatus())
        .withTimebooked(customerOrderResponse.getTimebooked())
        .withCustomerid(customerOrderResponse.getCustomerid())
        .withCreatedAt(customerOrderResponse.getCreatedAt())
        .build();
  }

  public static CustomerServiceOrder  transform(ServiceCustomerOrderResponse serviceCustomerOrderResponse){
    return CustomerServiceOrder.newBuilder()
        .withAvatar(serviceCustomerOrderResponse.getAvatar())
        .withName(serviceCustomerOrderResponse.getName())
        .withPrice(serviceCustomerOrderResponse.getPrice())
        .withSalon(SalonMapper.transform(serviceCustomerOrderResponse.getSalon()))
        .withStatus(serviceCustomerOrderResponse.getStatus())
        .withServiceid(serviceCustomerOrderResponse.getServiceid())
        .withSalonid(serviceCustomerOrderResponse.getSalonid())
        .build();
  }
}
