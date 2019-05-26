package com.app.remote.data.di;

import dagger.Module;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
//bundle the module to single module.
@Module(includes = {ApiModule.class, DataBaseModule.class, RepositoryModule.class}) public class DataModule {
}
