// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.common.api.ApiException;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ApiExceptionUtil
{
    @NonNull
    @KeepForSdk
    public static ApiException fromStatus(@NonNull final Status status) {
        if (status.hasResolution()) {
            return (ApiException)new ResolvableApiException(status);
        }
        return new ApiException(status);
    }
}
