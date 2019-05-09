// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.graphics.Paint;
import android.view.View;
import java.util.Set;
import android.net.Uri;
import android.view.Window;
import java.util.concurrent.Callable;
import android.webkit.WebSettings;
import android.content.Context;
import android.app.DownloadManager$Request;
import android.annotation.TargetApi;

@TargetApi(11)
public class zzakt extends zzaks
{
    @Override
    public zzaqx zza(final zzaqw zzaqw, final boolean b) {
        return new zzart(zzaqw, b);
    }
    
    @Override
    public final boolean zza(final DownloadManager$Request downloadManager$Request) {
        downloadManager$Request.allowScanningByMediaScanner();
        downloadManager$Request.setNotificationVisibility(1);
        return true;
    }
    
    @Override
    public boolean zza(final Context context, final WebSettings webSettings) {
        super.zza(context, webSettings);
        return (boolean)zzaml.zza(context, (Callable)new zzaku(this, context, webSettings));
    }
    
    @Override
    public final boolean zza(final Window window) {
        window.setFlags(16777216, 16777216);
        return true;
    }
    
    @Override
    public final Set<String> zzh(final Uri uri) {
        return (Set<String>)uri.getQueryParameterNames();
    }
    
    @Override
    public final boolean zzy(final View view) {
        view.setLayerType(0, (Paint)null);
        return true;
    }
    
    @Override
    public final boolean zzz(final View view) {
        view.setLayerType(1, (Paint)null);
        return true;
    }
}
