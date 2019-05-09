// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.Arrays;
import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DependencyCycleException extends DependencyException
{
    private final List<Component<?>> zza;
    
    @KeepForSdk
    public DependencyCycleException(final List<Component<?>> zza) {
        super("Dependency cycle detected: " + Arrays.toString(zza.toArray()));
        this.zza = zza;
    }
    
    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zza;
    }
}
