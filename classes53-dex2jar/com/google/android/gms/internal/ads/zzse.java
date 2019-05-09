// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd;

public final class zzse extends zzrp
{
    private final UnifiedNativeAd.UnconfirmedClickListener zzblk;
    
    public zzse(final UnifiedNativeAd.UnconfirmedClickListener zzblk) {
        this.zzblk = zzblk;
    }
    
    public final void onUnconfirmedClickCancelled() {
        this.zzblk.onUnconfirmedClickCancelled();
    }
    
    public final void onUnconfirmedClickReceived(final String s) {
        this.zzblk.onUnconfirmedClickReceived(s);
    }
}
