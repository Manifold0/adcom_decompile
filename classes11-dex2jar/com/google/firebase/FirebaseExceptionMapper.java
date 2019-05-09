// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;

@KeepForSdk
public class FirebaseExceptionMapper implements StatusExceptionMapper
{
    @Override
    public Exception getException(final Status status) {
        if (status.getStatusCode() == 8) {
            return new FirebaseException(status.zzg());
        }
        return new FirebaseApiNotAvailableException(status.zzg());
    }
}
