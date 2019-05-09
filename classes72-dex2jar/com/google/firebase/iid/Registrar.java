// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.Arrays;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.events.Subscriber;
import com.google.firebase.components.Dependency;
import com.google.firebase.FirebaseApp;
import com.google.firebase.components.Component;
import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.support.annotation.Keep;
import com.google.firebase.components.ComponentRegistrar;

@Keep
@KeepForSdk
public final class Registrar implements ComponentRegistrar
{
    @Keep
    public final List<Component<?>> getComponents() {
        return Arrays.asList(Component.builder((Class)FirebaseInstanceId.class).add(Dependency.required((Class)FirebaseApp.class)).add(Dependency.required((Class)Subscriber.class)).factory(zzao.zzcm).alwaysEager().build(), Component.builder((Class)FirebaseInstanceIdInternal.class).add(Dependency.required((Class)FirebaseInstanceId.class)).factory(zzap.zzcm).build());
    }
    
    private static final class zza implements FirebaseInstanceIdInternal
    {
        private final FirebaseInstanceId zzcn;
        
        public zza(final FirebaseInstanceId zzcn) {
            this.zzcn = zzcn;
        }
        
        public final String getId() {
            return this.zzcn.getId();
        }
        
        public final String getToken() {
            return this.zzcn.getToken();
        }
    }
}
