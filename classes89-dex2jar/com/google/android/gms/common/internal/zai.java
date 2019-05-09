// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;

final class zai implements zaa
{
    @Override
    public final ApiException zaf(final Status status) {
        return ApiExceptionUtil.fromStatus(status);
    }
}
