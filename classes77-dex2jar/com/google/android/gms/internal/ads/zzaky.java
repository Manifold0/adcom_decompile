// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebSettings;
import android.content.Context;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.annotation.TargetApi;

@TargetApi(16)
public class zzaky extends zzakv
{
    @Override
    public final void setBackground(final View view, final Drawable background) {
        view.setBackground(background);
    }
    
    @Override
    public final void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeOnGlobalLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    @Override
    public boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        webSettings.setAllowFileAccessFromFileURLs(false);
        webSettings.setAllowUniversalAccessFromFileURLs(false);
        return true;
    }
}
