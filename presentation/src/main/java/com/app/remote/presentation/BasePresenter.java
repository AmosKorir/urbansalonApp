package com.app.remote.presentation;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public interface BasePresenter {

    void dispose();

    interface View{
        void handleError(Throwable throwable);
    }
}
