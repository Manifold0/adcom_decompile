// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface ComponentFactory<T>
{
    @KeepForSdk
    T create(final ComponentContainer p0);
}
