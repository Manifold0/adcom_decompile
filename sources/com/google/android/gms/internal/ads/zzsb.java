package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.formats.OnPublisherAdViewLoadedListener;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

public final class zzsb extends zzrj {
    private final OnPublisherAdViewLoadedListener zzblf;

    public zzsb(OnPublisherAdViewLoadedListener onPublisherAdViewLoadedListener) {
        this.zzblf = onPublisherAdViewLoadedListener;
    }

    public final void zza(zzks zzks, IObjectWrapper iObjectWrapper) {
        if (zzks != null && iObjectWrapper != null) {
            PublisherAdView publisherAdView = new PublisherAdView((Context) ObjectWrapper.unwrap(iObjectWrapper));
            try {
                if (zzks.zzbx() instanceof zzjf) {
                    zzjf zzjf = (zzjf) zzks.zzbx();
                    publisherAdView.setAdListener(zzjf != null ? zzjf.getAdListener() : null);
                }
            } catch (Throwable e) {
                zzane.zzb("", e);
            }
            try {
                if (zzks.zzbw() instanceof zzjp) {
                    zzjp zzjp = (zzjp) zzks.zzbw();
                    publisherAdView.setAppEventListener(zzjp != null ? zzjp.getAppEventListener() : null);
                }
            } catch (Throwable e2) {
                zzane.zzb("", e2);
            }
            zzamu.zzsy.post(new zzsc(this, publisherAdView, zzks));
        }
    }
}
