// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.ViewGroup$LayoutParams;
import java.util.LinkedHashSet;
import java.util.Collections;
import java.util.Set;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzbv;
import android.webkit.CookieSyncManager;
import android.webkit.CookieManager;
import android.view.Window;
import android.webkit.WebSettings;
import android.app.DownloadManager$Request;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewTreeObserver;
import android.net.http.SslError;
import android.webkit.WebResourceResponse;
import java.io.InputStream;
import java.util.Map;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.content.Context;
import android.os.Process;
import android.annotation.TargetApi;

@zzadh
@TargetApi(8)
public class zzakq
{
    private zzakq() {
    }
    
    public static boolean zzi(final zzaqw zzaqw) {
        if (zzaqw == null) {
            return false;
        }
        zzaqw.onPause();
        return true;
    }
    
    public static boolean zzj(final zzaqw zzaqw) {
        if (zzaqw == null) {
            return false;
        }
        zzaqw.onResume();
        return true;
    }
    
    public static boolean zzrp() {
        final int myUid = Process.myUid();
        return myUid == 0 || myUid == 1000;
    }
    
    public String getDefaultUserAgent(final Context context) {
        return "";
    }
    
    public boolean isAttachedToWindow(final View view) {
        return view.getWindowToken() != null || view.getWindowVisibility() != 8;
    }
    
    public void setBackground(final View view, final Drawable backgroundDrawable) {
        view.setBackgroundDrawable(backgroundDrawable);
    }
    
    public Drawable zza(final Context context, final Bitmap bitmap, final boolean b, final float n) {
        return (Drawable)new BitmapDrawable(context.getResources(), bitmap);
    }
    
    public WebResourceResponse zza(final String s, final String s2, final int n, final String s3, final Map<String, String> map, final InputStream inputStream) {
        return new WebResourceResponse(s, s2, inputStream);
    }
    
    public zzaqx zza(final zzaqw zzaqw, final boolean b) {
        return new zzaqx(zzaqw, b);
    }
    
    public String zza(final SslError sslError) {
        return "";
    }
    
    public void zza(final ViewTreeObserver viewTreeObserver, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(viewTreeObserver$OnGlobalLayoutListener);
    }
    
    public boolean zza(final DownloadManager$Request downloadManager$Request) {
        return false;
    }
    
    public boolean zza(final Context context, final WebSettings webSettings) {
        return false;
    }
    
    public boolean zza(final Window window) {
        return false;
    }
    
    public void zzaw(final Context context) {
    }
    
    public CookieManager zzax(final Context context) {
        if (zzrp()) {
            return null;
        }
        try {
            CookieSyncManager.createInstance(context);
            return CookieManager.getInstance();
        }
        catch (Throwable t) {
            zzakb.zzb("Failed to obtain CookieManager.", t);
            zzbv.zzeo().zza(t, "ApiLevelUtil.getCookieManager");
            return null;
        }
    }
    
    public Set<String> zzh(final Uri uri) {
        if (uri.isOpaque()) {
            return Collections.emptySet();
        }
        final String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptySet();
        }
        final LinkedHashSet<String> set = new LinkedHashSet<String>();
        int n = 0;
        int n2;
        do {
            if ((n2 = encodedQuery.indexOf(38, n)) == -1) {
                n2 = encodedQuery.length();
            }
            final int index = encodedQuery.indexOf(61, n);
            int n3;
            if (index > n2 || (n3 = index) == -1) {
                n3 = n2;
            }
            set.add(Uri.decode(encodedQuery.substring(n, n3)));
        } while ((n = n2 + 1) < encodedQuery.length());
        return (Set<String>)Collections.unmodifiableSet((Set<?>)set);
    }
    
    public int zzrl() {
        return 0;
    }
    
    public int zzrm() {
        return 1;
    }
    
    public int zzrn() {
        return 5;
    }
    
    public ViewGroup$LayoutParams zzro() {
        return new ViewGroup$LayoutParams(-2, -2);
    }
    
    public int zzrq() {
        return 0;
    }
    
    public boolean zzy(final View view) {
        return false;
    }
    
    public boolean zzz(final View view) {
        return false;
    }
}
