// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.events;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface Publisher
{
    @KeepForSdk
    void publish(final Event<?> p0);
}
