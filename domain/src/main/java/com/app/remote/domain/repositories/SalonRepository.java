package com.app.remote.domain.repositories;

import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import io.reactivex.Single;
import java.io.File;
import java.util.List;

/**
 * Created by Korir on 6/2/19.
 * amoskrr@gmail.com
 */
public interface SalonRepository {
  Single<SalonModel> registerSalon(String name, String phone, String password, String location,
      String latitude, String longitude);

  Single<Service> createService(String accessToken, String name, String price);

  Single<List<Service>> getAllServices(String accessToken);

  Single<Sucess> uploadServicImage(File imageFile, String serviceId);

  Single<List<CustomerOrder>> getActiveOrders(String accessToken);

  
  Single<Sucess> setOrderStatus(String accessToken, Integer orderid, int i);
}
