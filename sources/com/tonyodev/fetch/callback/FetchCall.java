package com.tonyodev.fetch.callback;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.tonyodev.fetch.request.Request;

public interface FetchCall<T> {
    void onError(int i, @NonNull Request request);

    void onSuccess(@Nullable T t, @NonNull Request request);
}
