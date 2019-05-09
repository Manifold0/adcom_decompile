package com.google.firebase.components;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.Arrays;
import java.util.List;

@KeepForSdk
/* compiled from: com.google.firebase:firebase-common@@16.0.2 */
public class DependencyCycleException extends DependencyException {
    private final List<Component<?>> zza;

    @KeepForSdk
    public DependencyCycleException(List<Component<?>> componentsInCycle) {
        super("Dependency cycle detected: " + Arrays.toString(componentsInCycle.toArray()));
        this.zza = componentsInCycle;
    }

    @KeepForSdk
    public List<Component<?>> getComponentsInCycle() {
        return this.zza;
    }
}
