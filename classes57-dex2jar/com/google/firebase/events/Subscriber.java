// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.events;

import java.util.concurrent.Executor;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface Subscriber
{
    @KeepForSdk
     <T> void subscribe(final Class<T> p0, final EventHandler<? super T> p1);
    
    @KeepForSdk
     <T> void subscribe(final Class<T> p0, final Executor p1, final EventHandler<? super T> p2);
    
    @KeepForSdk
     <T> void unsubscribe(final Class<T> p0, final EventHandler<? super T> p1);
}
