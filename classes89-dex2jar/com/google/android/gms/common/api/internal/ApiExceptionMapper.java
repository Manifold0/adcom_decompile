// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ApiExceptionMapper implements StatusExceptionMapper
{
    public Exception getException(final Status status) {
        return (Exception)ApiExceptionUtil.fromStatus(status);
    }
}
