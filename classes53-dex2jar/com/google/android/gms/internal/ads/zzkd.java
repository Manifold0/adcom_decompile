// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.AdListener;

@zzadh
public class zzkd extends AdListener
{
    private final Object lock;
    private AdListener zzasi;
    
    public zzkd() {
        this.lock = new Object();
    }
    
    @Override
    public void onAdClosed() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdClosed();
            }
        }
    }
    
    @Override
    public void onAdFailedToLoad(final int n) {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdFailedToLoad(n);
            }
        }
    }
    
    @Override
    public void onAdLeftApplication() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdLeftApplication();
            }
        }
    }
    
    @Override
    public void onAdLoaded() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdLoaded();
            }
        }
    }
    
    @Override
    public void onAdOpened() {
        synchronized (this.lock) {
            if (this.zzasi != null) {
                this.zzasi.onAdOpened();
            }
        }
    }
    
    public final void zza(final AdListener zzasi) {
        synchronized (this.lock) {
            this.zzasi = zzasi;
        }
    }
}
