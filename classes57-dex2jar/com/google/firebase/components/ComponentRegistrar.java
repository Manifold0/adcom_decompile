// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface ComponentRegistrar
{
    @KeepForSdk
    List<Component<?>> getComponents();
}
