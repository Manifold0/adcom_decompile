// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.io.IOException;

public final class zzds extends zzei
{
    public zzds(final zzcz zzcz, final String s, final String s2, final zzba zzba, final int n, final int n2) {
        super(zzcz, s, s2, zzba, n, 24);
    }
    
    private final void zzau() {
        final AdvertisingIdClient zzan = this.zzps.zzan();
        if (zzan != null) {
            try {
                final AdvertisingIdClient$Info info = zzan.getInfo();
                final String zzn = zzdg.zzn(info.getId());
                if (zzn != null) {
                    synchronized (this.zztq) {
                        this.zztq.zzfi = zzn;
                        this.zztq.zzfk = info.isLimitAdTrackingEnabled();
                        this.zztq.zzfj = 5;
                    }
                }
            }
            catch (IOException ex) {}
        }
    }
    
    @Override
    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        if (this.zzps.zzaf()) {
            this.zzau();
            return;
        }
        synchronized (this.zztq) {
            this.zztq.zzfi = (String)this.zztz.invoke(null, this.zzps.getContext());
        }
    }
    
    @Override
    public final Void zzat() throws Exception {
        if (this.zzps.isInitialized()) {
            return super.zzat();
        }
        if (this.zzps.zzaf()) {
            this.zzau();
        }
        return null;
    }
}
