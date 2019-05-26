package com.app.remote.data.utils;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Korir on 3/12/19.
 * amoskrr@gmail.com
 */
public class RxUtil {
    public static CompositeDisposable initDisposables(CompositeDisposable compositeDisposable) {
        if (compositeDisposable == null || (compositeDisposable != null && compositeDisposable.isDisposed())) {
            return new CompositeDisposable();
        }

        return compositeDisposable;
    }

    public static void dispose(CompositeDisposable compositeDisposable) {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
    }
}
