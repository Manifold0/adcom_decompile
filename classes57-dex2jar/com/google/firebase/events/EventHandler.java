// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface EventHandler<T>
{
    @KeepForSdk
    void handle(final Event<T> p0);
}
