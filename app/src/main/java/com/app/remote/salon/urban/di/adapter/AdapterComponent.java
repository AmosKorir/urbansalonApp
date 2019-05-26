package com.app.remote.salon.urban.di.adapter;

import dagger.Subcomponent;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
@Subcomponent(modules = { AdapterModule.class }) public interface AdapterComponent {

    @Subcomponent.Builder interface Builder {
        Builder adapterModule(AdapterModule adapterModule);

        AdapterComponent build();
    }
}