// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.inject;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface Provider<T>
{
    @KeepForSdk
    T get();
}
