// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.internal;

import android.support.annotation.Nullable;
import com.google.firebase.auth.GetTokenResult;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.annotation.KeepForSdk;

@Deprecated
@KeepForSdk
public interface InternalTokenProvider
{
    @KeepForSdk
    Task<GetTokenResult> getAccessToken(final boolean p0);
    
    @Nullable
    @KeepForSdk
    String getUid();
}
