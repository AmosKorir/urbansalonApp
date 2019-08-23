package com.app.remote.data.di;

import com.app.remote.data.api.CustomerApi;
import com.app.remote.data.api.SalonApi;
import com.app.remote.data.repositories.CustomerApiRepository;
import com.app.remote.data.repositories.SalonApiRepository;
import com.app.remote.domain.constants.DIConstants;
import com.app.remote.domain.repositories.CustomerRepository;
import com.app.remote.domain.repositories.SalonRepository;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
//bundle the module to single module.
@Module(includes = {ApiModule.class, DataBaseModule.class, RepositoryModule.class}) public class DataModule {

  @Provides @Named(DIConstants.API) CustomerRepository provideCustomerApiRepository(CustomerApi customerApi){
    return new CustomerApiRepository(customerApi);
  }

  @Provides @Named(DIConstants.API) SalonRepository provideSalonRepisitory(SalonApi salonApi){
    return new SalonApiRepository(salonApi);
  }
}
