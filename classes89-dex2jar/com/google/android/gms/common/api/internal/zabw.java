// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;

public final class zabw
{
    public final RegisterListenerMethod<Api.AnyClient, ?> zajx;
    public final UnregisterListenerMethod<Api.AnyClient, ?> zajy;
    
    public zabw(@NonNull final RegisterListenerMethod<Api.AnyClient, ?> zajx, @NonNull final UnregisterListenerMethod<Api.AnyClient, ?> zajy) {
        this.zajx = zajx;
        this.zajy = zajy;
    }
}
