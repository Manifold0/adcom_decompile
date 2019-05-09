// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Random;
import com.google.android.gms.ads.internal.zzbv;
import android.os.RemoteException;

final class zztu extends zzki
{
    private final zzkh zzboi;
    
    zztu(final zzkh zzboi) {
        this.zzboi = zzboi;
    }
    
    public final void onAdClicked() throws RemoteException {
        this.zzboi.onAdClicked();
    }
    
    public final void onAdClosed() throws RemoteException {
        if (zzud.zzlv()) {
            final int intValue = (int)zzkb.zzik().zzd(zznk.zzazg);
            final int intValue2 = (int)zzkb.zzik().zzd(zznk.zzazh);
            if (intValue <= 0 || intValue2 < 0) {
                zzbv.zzex().zzld();
            }
            else {
                zzakk.zzcrm.postDelayed(zztv.zzboj, (long)(new Random().nextInt(intValue2 + 1) + intValue));
            }
        }
        this.zzboi.onAdClosed();
    }
    
    public final void onAdFailedToLoad(final int n) throws RemoteException {
        this.zzboi.onAdFailedToLoad(n);
    }
    
    public final void onAdImpression() throws RemoteException {
        this.zzboi.onAdImpression();
    }
    
    public final void onAdLeftApplication() throws RemoteException {
        this.zzboi.onAdLeftApplication();
    }
    
    public final void onAdLoaded() throws RemoteException {
        this.zzboi.onAdLoaded();
    }
    
    public final void onAdOpened() throws RemoteException {
        this.zzboi.onAdOpened();
    }
}
