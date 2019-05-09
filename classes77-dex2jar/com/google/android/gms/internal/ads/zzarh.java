// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.res.Resources;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.os.Build$VERSION;
import android.graphics.drawable.GradientDrawable;
import com.google.android.gms.ads.impl.R$string;
import com.google.android.gms.ads.internal.zzbv;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import com.google.android.gms.ads.internal.zzw;
import android.support.annotation.Nullable;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.overlay.zzc;
import android.webkit.WebViewClient;
import android.webkit.WebChromeClient;
import android.view.View$OnTouchListener;
import android.webkit.WebView;
import android.view.View;
import android.view.View$OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;

@zzadh
public final class zzarh extends FrameLayout implements zzaqw
{
    private final zzaqw zzdcy;
    private final zzapn zzdcz;
    
    public zzarh(final zzaqw zzdcy) {
        super(zzdcy.getContext());
        this.zzdcy = zzdcy;
        this.zzdcz = new zzapn(zzdcy.zzua(), (ViewGroup)this, this);
        this.addView(this.zzdcy.getView());
    }
    
    public final void destroy() {
        this.zzdcy.destroy();
    }
    
    public final View$OnClickListener getOnClickListener() {
        return this.zzdcy.getOnClickListener();
    }
    
    public final int getRequestedOrientation() {
        return this.zzdcy.getRequestedOrientation();
    }
    
    public final View getView() {
        return (View)this;
    }
    
    public final WebView getWebView() {
        return this.zzdcy.getWebView();
    }
    
    public final boolean isDestroyed() {
        return this.zzdcy.isDestroyed();
    }
    
    public final void loadData(final String s, final String s2, final String s3) {
        this.zzdcy.loadData(s, s2, s3);
    }
    
    public final void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.zzdcy.loadDataWithBaseURL(s, s2, s3, s4, s5);
    }
    
    public final void loadUrl(final String s) {
        this.zzdcy.loadUrl(s);
    }
    
    public final void onPause() {
        this.zzdcz.onPause();
        this.zzdcy.onPause();
    }
    
    public final void onResume() {
        this.zzdcy.onResume();
    }
    
    public final void setOnClickListener(final View$OnClickListener onClickListener) {
        this.zzdcy.setOnClickListener(onClickListener);
    }
    
    public final void setOnTouchListener(final View$OnTouchListener onTouchListener) {
        this.zzdcy.setOnTouchListener(onTouchListener);
    }
    
    public final void setRequestedOrientation(final int requestedOrientation) {
        this.zzdcy.setRequestedOrientation(requestedOrientation);
    }
    
    public final void setWebChromeClient(final WebChromeClient webChromeClient) {
        this.zzdcy.setWebChromeClient(webChromeClient);
    }
    
    public final void setWebViewClient(final WebViewClient webViewClient) {
        this.zzdcy.setWebViewClient(webViewClient);
    }
    
    public final void stopLoading() {
        this.zzdcy.stopLoading();
    }
    
    public final void zza(final zzc zzc) {
        this.zzdcy.zza(zzc);
    }
    
    public final void zza(final zzd zzd) {
        this.zzdcy.zza(zzd);
    }
    
    public final void zza(final zzarl zzarl) {
        this.zzdcy.zza(zzarl);
    }
    
    public final void zza(final zzasi zzasi) {
        this.zzdcy.zza(zzasi);
    }
    
    public final void zza(final zzfs zzfs) {
        this.zzdcy.zza(zzfs);
    }
    
    public final void zza(final String s, final zzv<? super zzaqw> zzv) {
        this.zzdcy.zza(s, zzv);
    }
    
    public final void zza(final String s, final Predicate<zzv<? super zzaqw>> predicate) {
        this.zzdcy.zza(s, predicate);
    }
    
    public final void zza(final String s, final Map<String, ?> map) {
        this.zzdcy.zza(s, map);
    }
    
    public final void zza(final String s, final JSONObject jsonObject) {
        this.zzdcy.zza(s, jsonObject);
    }
    
    public final void zza(final boolean b, final int n) {
        this.zzdcy.zza(b, n);
    }
    
    public final void zza(final boolean b, final int n, final String s) {
        this.zzdcy.zza(b, n, s);
    }
    
    public final void zza(final boolean b, final int n, final String s, final String s2) {
        this.zzdcy.zza(b, n, s, s2);
    }
    
    public final void zzah(final boolean b) {
        this.zzdcy.zzah(b);
    }
    
    public final void zzai(final int n) {
        this.zzdcy.zzai(n);
    }
    
    public final void zzai(final boolean b) {
        this.zzdcy.zzai(b);
    }
    
    public final void zzaj(final boolean b) {
        this.zzdcy.zzaj(b);
    }
    
    public final void zzak(final boolean b) {
        this.zzdcy.zzak(b);
    }
    
    public final void zzb(final zzd zzd) {
        this.zzdcy.zzb(zzd);
    }
    
    public final void zzb(@Nullable final zzox zzox) {
        this.zzdcy.zzb(zzox);
    }
    
    public final void zzb(final String s, final zzv<? super zzaqw> zzv) {
        this.zzdcy.zzb(s, zzv);
    }
    
    public final void zzb(final String s, final JSONObject jsonObject) {
        this.zzdcy.zzb(s, jsonObject);
    }
    
    public final void zzbe(final String s) {
        this.zzdcy.zzbe(s);
    }
    
    public final zzw zzbi() {
        return this.zzdcy.zzbi();
    }
    
    public final void zzbm(final Context context) {
        this.zzdcy.zzbm(context);
    }
    
    public final void zzc(final String s, final String s2, @Nullable final String s3) {
        this.zzdcy.zzc(s, s2, s3);
    }
    
    public final void zzcl() {
        this.zzdcy.zzcl();
    }
    
    public final void zzcm() {
        this.zzdcy.zzcm();
    }
    
    public final void zzdr(final String s) {
        this.zzdcy.zzdr(s);
    }
    
    public final void zzno() {
        this.zzdcy.zzno();
    }
    
    public final void zznp() {
        this.zzdcy.zznp();
    }
    
    public final String zzol() {
        return this.zzdcy.zzol();
    }
    
    public final zzapn zztl() {
        return this.zzdcz;
    }
    
    public final zzarl zztm() {
        return this.zzdcy.zztm();
    }
    
    public final zznv zztn() {
        return this.zzdcy.zztn();
    }
    
    public final Activity zzto() {
        return this.zzdcy.zzto();
    }
    
    public final zznw zztp() {
        return this.zzdcy.zztp();
    }
    
    public final zzang zztq() {
        return this.zzdcy.zztq();
    }
    
    public final int zztr() {
        return this.getMeasuredHeight();
    }
    
    public final int zzts() {
        return this.getMeasuredWidth();
    }
    
    public final void zzty() {
        this.zzdcy.zzty();
    }
    
    public final void zztz() {
        this.zzdcy.zztz();
    }
    
    public final void zzu(final boolean b) {
        this.zzdcy.zzu(b);
    }
    
    public final Context zzua() {
        return this.zzdcy.zzua();
    }
    
    public final zzd zzub() {
        return this.zzdcy.zzub();
    }
    
    public final zzd zzuc() {
        return this.zzdcy.zzuc();
    }
    
    public final zzasi zzud() {
        return this.zzdcy.zzud();
    }
    
    public final String zzue() {
        return this.zzdcy.zzue();
    }
    
    public final zzasc zzuf() {
        return this.zzdcy.zzuf();
    }
    
    public final WebViewClient zzug() {
        return this.zzdcy.zzug();
    }
    
    public final boolean zzuh() {
        return this.zzdcy.zzuh();
    }
    
    public final zzci zzui() {
        return this.zzdcy.zzui();
    }
    
    public final boolean zzuj() {
        return this.zzdcy.zzuj();
    }
    
    public final void zzuk() {
        this.zzdcz.onDestroy();
        this.zzdcy.zzuk();
    }
    
    public final boolean zzul() {
        return this.zzdcy.zzul();
    }
    
    public final boolean zzum() {
        return this.zzdcy.zzum();
    }
    
    public final boolean zzun() {
        return this.zzdcy.zzun();
    }
    
    public final void zzuo() {
        this.zzdcy.zzuo();
    }
    
    public final void zzup() {
        this.zzdcy.zzup();
    }
    
    @Nullable
    public final zzox zzuq() {
        return this.zzdcy.zzuq();
    }
    
    public final void zzur() {
        this.setBackgroundColor(0);
        this.zzdcy.setBackgroundColor(0);
    }
    
    public final void zzus() {
        final TextView textView = new TextView(this.getContext());
        final Resources resources = zzbv.zzeo().getResources();
        String string;
        if (resources != null) {
            string = resources.getString(R$string.s7);
        }
        else {
            string = "Test Ad";
        }
        textView.setText((CharSequence)string);
        textView.setTextSize(15.0f);
        textView.setTextColor(-1);
        textView.setPadding(5, 0, 5, 0);
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(0);
        gradientDrawable.setColor(-12303292);
        gradientDrawable.setCornerRadius(8.0f);
        if (Build$VERSION.SDK_INT >= 16) {
            textView.setBackground((Drawable)gradientDrawable);
        }
        else {
            textView.setBackgroundDrawable((Drawable)gradientDrawable);
        }
        this.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 49));
        this.bringChildToFront((View)textView);
    }
}
