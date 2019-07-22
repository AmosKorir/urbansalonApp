package com.app.remote.domain.repositories;

import com.app.remote.domain.models.AnalyticModel;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import io.reactivex.Completable;
import io.reactivex.Flowable;
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

  Single<List<Service>> getRecommended(String accessToken);

  Single<List<CustomerOrder>> getClosedOrders(String accessToken);

  Single<List<CustomerOrder>>getPendingOrders(String accessToken);

  Single<List<CustomerOrder>>getRejectedOrders(String accesstoken);

  Single<SalonModel> loginUser(String phone, String password);

  Single<List<AnalyticModel>> getSevenDays(String accessToken);

  Single<List<SalonModel>> getSalons();

  Single<SalonModel> getsalonSelf(String accessToken);

  Single<Sucess> updateSalon(String accessToken, String opening, String closing, String avilabity);

  Single<Sucess> uploadProfile(String accessToken, File imageFile);


  Single<Sucess> updatService(String accessToken, String i,String status);

  Single<List<Service>> getPiData(String accessToken);
}
