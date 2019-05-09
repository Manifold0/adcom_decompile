// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.firebase.inject.Provider;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface ComponentContainer
{
    @KeepForSdk
     <T> T get(final Class<T> p0);
    
    @KeepForSdk
     <T> Provider<T> getProvider(final Class<T> p0);
}
