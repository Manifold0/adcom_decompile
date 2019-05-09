// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import android.os.RemoteException;
import com.google.android.gms.internal.ads.zzakb;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

final class zzbq extends WebViewClient
{
    private final /* synthetic */ zzbp zzaba;
    
    zzbq(final zzbp zzaba) {
        this.zzaba = zzaba;
    }
    
    public final void onReceivedError(final WebView webView, final WebResourceRequest webResourceRequest, final WebResourceError webResourceError) {
        if (this.zzaba.zzxs == null) {
            return;
        }
        try {
            this.zzaba.zzxs.onAdFailedToLoad(0);
        }
        catch (RemoteException ex) {
            zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
    }
    
    public final boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        if (s.startsWith(this.zzaba.zzeb())) {
            return false;
        }
        if (s.startsWith((String)zzkb.zzik().zzd(zznk.zzbcw))) {
            while (true) {
                if (this.zzaba.zzxs == null) {
                    break Label_0060;
                }
                try {
                    this.zzaba.zzxs.onAdFailedToLoad(3);
                    this.zzaba.zzk(0);
                    return true;
                }
                catch (RemoteException ex) {
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex);
                    continue;
                }
                break;
            }
        }
        if (s.startsWith((String)zzkb.zzik().zzd(zznk.zzbcx))) {
            while (true) {
                if (this.zzaba.zzxs == null) {
                    break Label_0124;
                }
                try {
                    this.zzaba.zzxs.onAdFailedToLoad(0);
                    this.zzaba.zzk(0);
                    return true;
                }
                catch (RemoteException ex2) {
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex2);
                    continue;
                }
                break;
            }
        }
        if (s.startsWith((String)zzkb.zzik().zzd(zznk.zzbcy))) {
            while (true) {
                if (this.zzaba.zzxs == null) {
                    break Label_0187;
                }
                try {
                    this.zzaba.zzxs.onAdLoaded();
                    this.zzaba.zzk(this.zzaba.zzu(s));
                    return true;
                }
                catch (RemoteException ex3) {
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)ex3);
                    continue;
                }
                break;
            }
        }
        if (s.startsWith("gmsg://")) {
            return true;
        }
        while (true) {
            if (this.zzaba.zzxs == null) {
                break Label_0249;
            }
            try {
                this.zzaba.zzxs.onAdLeftApplication();
                this.zzaba.zzw(this.zzaba.zzv(s));
                return true;
            }
            catch (RemoteException ex4) {
                zzakb.zzd("#007 Could not call remote method.", (Throwable)ex4);
                continue;
            }
            break;
        }
    }
}
