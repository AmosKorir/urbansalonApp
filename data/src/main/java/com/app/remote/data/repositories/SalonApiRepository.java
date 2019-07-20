package com.app.remote.data.repositories;

import com.app.remote.data.api.SalonApi;
import com.app.remote.data.mappers.AnalytiMapper;
import com.app.remote.data.mappers.CustomerOrderMapper;
import com.app.remote.data.mappers.SalonMapper;
import com.app.remote.data.mappers.ServiceMapper;
import com.app.remote.data.mappers.SuccessMapper;
import com.app.remote.domain.models.AnalyticModel;
import com.app.remote.domain.models.SalonModel;
import com.app.remote.domain.models.Service;
import com.app.remote.domain.models.Sucess;
import com.app.remote.domain.models.customerOrders.CustomerOrder;
import com.app.remote.domain.repositories.SalonRepository;
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
public class SalonApiRepository implements SalonRepository {
  SalonApi salonApi;

  public SalonApiRepository(SalonApi salonApi) {
    this.salonApi = salonApi;
  }

  @Override public Single<SalonModel> registerSalon(String name, String phone, String password,
      String location, String latitude, String longitude) {
    return salonApi.createSalon(name, phone, password, location, longitude, latitude)
        .map(SalonMapper::transform);
  }

  @Override public Single<Service> createService(String accessToken, String name, String price) {
    return salonApi.createService(accessToken, name, price)
        .map(ServiceMapper::transform);
  }

  @Override public Single<List<Service>> getAllServices(String accessToken) {
    return salonApi.getAllServices(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ServiceMapper::transform)
        .toList();
  }

  @Override public Single<Sucess> uploadServicImage(File imageFile, String serviceId) {
    MultipartBody.Builder builder = new MultipartBody.Builder();
    builder.setType(MultipartBody.FORM);
    builder.addFormDataPart("serviceid", serviceId);
    builder.addFormDataPart("file", imageFile.getName(),
        RequestBody.create(MediaType.parse("image/*"), imageFile));
    MultipartBody requestBody = builder.build();

    return salonApi.uploadServiceImage(requestBody).map(SuccessMapper::transform);
  }

  @Override public Single<List<CustomerOrder>> getActiveOrders(String accessToken) {
    return salonApi.getActiveOrders(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override public Single<Sucess> setOrderStatus(String accessToken, Integer orderid, int i) {
    return salonApi.setOrderStatus(accessToken, orderid, i)
        .map(SuccessMapper::transform);
  }

  @Override public Single<List<Service>> getRecommended(String accessToken) {
    return salonApi.getRecommended(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(ServiceMapper::transform)
        .toList();
  }

  @Override public Single<List<CustomerOrder>> getClosedOrders(String accessToken) {
    return salonApi.getClosedOrders(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override public Single<List<CustomerOrder>> getPendingOrders(String accessToken) {
    return salonApi.getPendingOrders(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override public Single<List<CustomerOrder>> getRejectedOrders(String accesstoken) {
    return salonApi.getRejectedOrders(accesstoken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(CustomerOrderMapper::transform)
        .toList();
  }

  @Override public Single<SalonModel> loginUser(String phone, String password) {
    return salonApi.login(phone, password);
  }

  @Override public Single<List<AnalyticModel>> getSevenDays(String accessToken) {
    return salonApi.getSevenDay(accessToken)
        .flatMapPublisher(Flowable::fromIterable)
        .map(AnalytiMapper::transform)
        .toList();
  }

  @Override public Single<List<SalonModel>> getSalons() {
    return salonApi.getSalons()
        .flatMapPublisher(Flowable::fromIterable)
        .map(SalonMapper::transform)
        .toList();
  }

  @Override public Single<SalonModel> getsalonSelf(String accessToken) {
    return salonApi.getSalonSelf(accessToken)
        .map(SalonMapper::transform);
  }

  @Override public Single<Sucess> updateSalon(String accessToken, String opening, String closing,
      String avilabity) {
    return salonApi.updateSalon(accessToken,opening,closing,avilabity)
        .map(SuccessMapper::transform);
  }

  @Override public Single<Sucess> uploadProfile(String accessToken, File imageFile) {
    MultipartBody.Builder builder = new MultipartBody.Builder();
    builder.setType(MultipartBody.FORM);
    builder.addFormDataPart("file", imageFile.getName(),
        RequestBody.create(MediaType.parse("image/*"), imageFile));
    MultipartBody requestBody = builder.build();
    return salonApi.uploadProfile(requestBody,accessToken)
        .map(SuccessMapper::transform);
  }
}
