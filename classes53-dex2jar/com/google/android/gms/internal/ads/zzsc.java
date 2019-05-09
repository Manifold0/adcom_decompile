// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdView;

final class zzsc implements Runnable
{
    private final /* synthetic */ PublisherAdView zzblg;
    private final /* synthetic */ zzks zzblh;
    private final /* synthetic */ zzsb zzbli;
    
    zzsc(final zzsb zzbli, final PublisherAdView zzblg, final zzks zzblh) {
        this.zzbli = zzbli;
        this.zzblg = zzblg;
        this.zzblh = zzblh;
    }
    
    @Override
    public final void run() {
        if (this.zzblg.zza(this.zzblh)) {
            this.zzbli.zzblf.onPublisherAdViewLoaded(this.zzblg);
            return;
        }
        zzane.zzdk("Could not bind.");
    }
}
