// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Activity;
import com.google.android.gms.ads.internal.zzw;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzd;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.View$OnTouchListener;
import android.webkit.WebView;
import android.view.View;
import android.view.ViewParent;
import android.support.annotation.Nullable;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.ads.internal.zzbo;

@zzadh
@ParametersAreNonnullByDefault
@VisibleForTesting
public interface zzaqw extends zzbo, zzapw, zzarr, zzars, zzarw, zzarz, zzasa, zzasb, zzft, zzue, zzve
{
    void destroy();
    
    Context getContext();
    
    int getHeight();
    
    ViewGroup$LayoutParams getLayoutParams();
    
    void getLocationOnScreen(final int[] p0);
    
    @Nullable
    View$OnClickListener getOnClickListener();
    
    ViewParent getParent();
    
    int getRequestedOrientation();
    
    View getView();
    
    WebView getWebView();
    
    int getWidth();
    
    boolean isDestroyed();
    
    void loadData(final String p0, final String p1, final String p2);
    
    void loadDataWithBaseURL(final String p0, final String p1, final String p2, final String p3, @Nullable final String p4);
    
    void loadUrl(final String p0);
    
    void measure(final int p0, final int p1);
    
    void onPause();
    
    void onResume();
    
    void setBackgroundColor(final int p0);
    
    void setOnClickListener(final View$OnClickListener p0);
    
    void setOnTouchListener(final View$OnTouchListener p0);
    
    void setRequestedOrientation(final int p0);
    
    void setWebChromeClient(final WebChromeClient p0);
    
    void setWebViewClient(final WebViewClient p0);
    
    void stopLoading();
    
    void zza(final zzd p0);
    
    void zza(final zzarl p0);
    
    void zza(final zzasi p0);
    
    void zza(final String p0, final zzv<? super zzaqw> p1);
    
    void zza(final String p0, final Predicate<zzv<? super zzaqw>> p1);
    
    void zzai(final int p0);
    
    void zzai(final boolean p0);
    
    void zzaj(final boolean p0);
    
    void zzak(final boolean p0);
    
    void zzb(final zzd p0);
    
    void zzb(final zzox p0);
    
    void zzb(final String p0, final zzv<? super zzaqw> p1);
    
    zzw zzbi();
    
    void zzbm(final Context p0);
    
    void zzc(final String p0, final String p1, @Nullable final String p2);
    
    void zzdr(final String p0);
    
    void zzno();
    
    @Nullable
    zzarl zztm();
    
    Activity zzto();
    
    zznw zztp();
    
    zzang zztq();
    
    void zzty();
    
    void zztz();
    
    void zzu(final boolean p0);
    
    Context zzua();
    
    zzd zzub();
    
    zzd zzuc();
    
    zzasi zzud();
    
    String zzue();
    
    @Nullable
    zzasc zzuf();
    
    WebViewClient zzug();
    
    boolean zzuh();
    
    zzci zzui();
    
    boolean zzuj();
    
    void zzuk();
    
    boolean zzul();
    
    boolean zzum();
    
    boolean zzun();
    
    void zzuo();
    
    void zzup();
    
    zzox zzuq();
    
    void zzur();
    
    void zzus();
}
