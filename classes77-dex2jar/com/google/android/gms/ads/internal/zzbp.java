// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzamu;
import android.view.ViewGroup$LayoutParams;
import android.text.TextUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import android.net.Uri$Builder;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzmu;
import com.google.android.gms.internal.ads.zzlu;
import com.google.android.gms.internal.ads.zzlg;
import com.google.android.gms.internal.ads.zzla;
import com.google.android.gms.internal.ads.zzkx;
import com.google.android.gms.internal.ads.zzke;
import com.google.android.gms.internal.ads.zzahe;
import com.google.android.gms.internal.ads.zzabc;
import com.google.android.gms.internal.ads.zzaaw;
import com.google.android.gms.internal.ads.zzlo;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import android.content.Intent;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzakb;
import android.app.Activity;
import android.view.View;
import android.net.Uri;
import android.view.View$OnTouchListener;
import android.webkit.WebViewClient;
import java.util.concurrent.Callable;
import com.google.android.gms.internal.ads.zzaki;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzkh;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import com.google.android.gms.internal.ads.zzci;
import java.util.concurrent.Future;
import com.google.android.gms.internal.ads.zzjn;
import android.content.Context;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzkt;

@zzadh
@ParametersAreNonnullByDefault
public final class zzbp extends zzkt
{
    private final Context mContext;
    private final zzjn zzaau;
    private final Future<zzci> zzaav;
    private final zzbu zzaaw;
    @Nullable
    private WebView zzaax;
    @Nullable
    private zzci zzaay;
    private AsyncTask<Void, Void, String> zzaaz;
    @Nullable
    private zzkh zzxs;
    private final zzang zzyf;
    
    public zzbp(final Context mContext, final zzjn zzaau, final String s, final zzang zzyf) {
        this.mContext = mContext;
        this.zzyf = zzyf;
        this.zzaau = zzaau;
        this.zzaax = new WebView(this.mContext);
        this.zzaav = (Future<zzci>)zzaki.zza((Callable<Object>)new zzbs(this));
        this.zzaaw = new zzbu(s);
        this.zzk(0);
        this.zzaax.setVerticalScrollBarEnabled(false);
        this.zzaax.getSettings().setJavaScriptEnabled(true);
        this.zzaax.setWebViewClient((WebViewClient)new zzbq(this));
        this.zzaax.setOnTouchListener((View$OnTouchListener)new zzbr(this));
    }
    
    private final String zzv(String s) {
        if (this.zzaay == null) {
            return s;
        }
        s = (String)Uri.parse(s);
        try {
            s = (String)this.zzaay.zza((Uri)s, this.mContext, null, null);
            return ((Uri)s).toString();
        }
        catch (zzcj zzcj) {
            zzakb.zzc("Unable to process ad data", (Throwable)zzcj);
            return ((Uri)s).toString();
        }
    }
    
    private final void zzw(final String s) {
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse(s));
        this.mContext.startActivity(intent);
    }
    
    public final void destroy() throws RemoteException {
        Preconditions.checkMainThread("destroy must be called on the main UI thread.");
        this.zzaaz.cancel(true);
        this.zzaav.cancel(true);
        this.zzaax.destroy();
        this.zzaax = null;
    }
    
    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }
    
    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }
    
    @Nullable
    public final zzlo getVideoController() {
        return null;
    }
    
    public final boolean isLoading() throws RemoteException {
        return false;
    }
    
    public final boolean isReady() throws RemoteException {
        return false;
    }
    
    public final void pause() throws RemoteException {
        Preconditions.checkMainThread("pause must be called on the main UI thread.");
    }
    
    public final void resume() throws RemoteException {
        Preconditions.checkMainThread("resume must be called on the main UI thread.");
    }
    
    public final void setImmersiveMode(final boolean b) {
        throw new IllegalStateException("Unused method");
    }
    
    public final void setManualImpressionsEnabled(final boolean b) throws RemoteException {
    }
    
    public final void setUserId(final String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void showInterstitial() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void stopLoading() throws RemoteException {
    }
    
    public final void zza(final zzaaw zzaaw) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzabc zzabc, final String s) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzahe zzahe) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzjn zzjn) throws RemoteException {
        throw new IllegalStateException("AdSize must be set before initialization");
    }
    
    public final void zza(final zzke zzke) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzkh zzxs) throws RemoteException {
        this.zzxs = zzxs;
    }
    
    public final void zza(final zzkx zzkx) {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzla zzla) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzlg zzlg) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzmu zzmu) {
        throw new IllegalStateException("Unused method");
    }
    
    public final void zza(final zzod zzod) throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final boolean zzb(final zzjj zzjj) throws RemoteException {
        Preconditions.checkNotNull((Object)this.zzaax, (Object)"This Search Ad has already been torn down");
        this.zzaaw.zza(zzjj, this.zzyf);
        this.zzaaz = (AsyncTask<Void, Void, String>)new zzbt(this, null).execute((Object[])new Void[0]);
        return true;
    }
    
    public final Bundle zzba() {
        throw new IllegalStateException("Unused method");
    }
    
    public final IObjectWrapper zzbj() throws RemoteException {
        Preconditions.checkMainThread("getAdFrame must be called on the main UI thread.");
        return ObjectWrapper.wrap((Object)this.zzaax);
    }
    
    public final zzjn zzbk() throws RemoteException {
        return this.zzaau;
    }
    
    public final void zzbm() throws RemoteException {
        throw new IllegalStateException("Unused method");
    }
    
    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }
    
    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }
    
    @Nullable
    public final String zzck() throws RemoteException {
        return null;
    }
    
    @VisibleForTesting
    final String zzea() {
        final Uri$Builder uri$Builder = new Uri$Builder();
        uri$Builder.scheme("https://").appendEncodedPath((String)zzkb.zzik().zzd(zznk.zzbcz));
        uri$Builder.appendQueryParameter("query", this.zzaaw.getQuery());
        uri$Builder.appendQueryParameter("pubId", this.zzaaw.zzed());
        final Map<String, String> zzee = this.zzaaw.zzee();
        for (final String s : zzee.keySet()) {
            uri$Builder.appendQueryParameter(s, (String)zzee.get(s));
        }
        Object o;
        Object zzeb = o = uri$Builder.build();
        while (true) {
            if (this.zzaay == null) {
                break Label_0153;
            }
            try {
                o = this.zzaay.zza((Uri)zzeb, this.mContext);
                zzeb = this.zzeb();
                final String encodedQuery = ((Uri)o).getEncodedQuery();
                return new StringBuilder(String.valueOf(zzeb).length() + 1 + String.valueOf(encodedQuery).length()).append((String)zzeb).append("#").append(encodedQuery).toString();
            }
            catch (zzcj zzcj) {
                zzakb.zzc("Unable to process ad data", (Throwable)zzcj);
                o = zzeb;
                continue;
            }
            break;
        }
    }
    
    @VisibleForTesting
    final String zzeb() {
        String zzec = this.zzaaw.zzec();
        if (TextUtils.isEmpty((CharSequence)zzec)) {
            zzec = "www.google.com";
        }
        final String s = (String)zzkb.zzik().zzd(zznk.zzbcz);
        return new StringBuilder(String.valueOf(zzec).length() + 8 + String.valueOf(s).length()).append("https://").append(zzec).append(s).toString();
    }
    
    @VisibleForTesting
    final void zzk(final int n) {
        if (this.zzaax == null) {
            return;
        }
        this.zzaax.setLayoutParams(new ViewGroup$LayoutParams(-1, n));
    }
    
    @VisibleForTesting
    final int zzu(String queryParameter) {
        queryParameter = Uri.parse(queryParameter).getQueryParameter("height");
        if (TextUtils.isEmpty((CharSequence)queryParameter)) {
            return 0;
        }
        try {
            zzkb.zzif();
            return zzamu.zza(this.mContext, Integer.parseInt(queryParameter));
        }
        catch (NumberFormatException ex) {
            return 0;
        }
    }
}
