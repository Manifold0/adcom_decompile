// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebResourceResponse;
import java.util.Iterator;
import android.webkit.WebSettings;
import android.graphics.Paint;
import com.google.android.gms.ads.internal.zzbv;
import android.os.Build$VERSION;
import java.util.concurrent.CopyOnWriteArrayList;
import android.content.Context;
import android.webkit.WebViewClient;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;
import android.webkit.WebView;

@zzadh
@ParametersAreNonnullByDefault
public class zzass extends WebView implements zzasx, zzasz, zzata, zzatb
{
    private final List<zzasx> zzdew;
    private final List<zzatb> zzdex;
    private final List<zzasz> zzdey;
    private final List<zzata> zzdez;
    private final zzash zzdfa;
    protected final WebViewClient zzdfb;
    
    public zzass(final zzash zzdfa) {
        super((Context)zzdfa);
        this.zzdew = new CopyOnWriteArrayList<zzasx>();
        this.zzdex = new CopyOnWriteArrayList<zzatb>();
        this.zzdey = new CopyOnWriteArrayList<zzasz>();
        this.zzdez = new CopyOnWriteArrayList<zzata>();
        this.zzdfa = zzdfa;
        this.setBackgroundColor(0);
        final WebSettings settings = this.getSettings();
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (Build$VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzbv.zzem().zza(this.getContext(), settings);
        this.removeJavascriptInterface("accessibility");
        this.removeJavascriptInterface("accessibilityTraversal");
        while (true) {
            try {
                this.getSettings().setJavaScriptEnabled(true);
                this.setLayerType(1, (Paint)null);
                super.setWebViewClient(this.zzdfb = new zzast(this, this, this, this));
            }
            catch (NullPointerException ex) {
                zzakb.zzb("Unable to enable Javascript.", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public void addJavascriptInterface(final Object o, final String s) {
        if (Build$VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(o, s);
            return;
        }
        zzakb.v("Ignore addJavascriptInterface due to low Android version.");
    }
    
    public void loadUrl(final String noClassDefFoundError) {
        try {
            super.loadUrl((String)noClassDefFoundError);
        }
        catch (Exception ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {
            goto Label_0007;
        }
        catch (IncompatibleClassChangeError noClassDefFoundError) {
            goto Label_0007;
        }
    }
    
    public void setWebViewClient(final WebViewClient webViewClient) {
    }
    
    public final void zza(final zzasx zzasx) {
        this.zzdew.add(zzasx);
    }
    
    public final void zza(final zzasz zzasz) {
        this.zzdey.add(zzasz);
    }
    
    public final void zza(final zzata zzata) {
        this.zzdez.add(zzata);
    }
    
    public final void zza(final zzatb zzatb) {
        this.zzdex.add(zzatb);
    }
    
    public final boolean zza(final zzasu zzasu) {
        final Iterator<zzasx> iterator = this.zzdew.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().zza(zzasu)) {
                return true;
            }
        }
        return false;
    }
    
    public final void zzb(final zzasu zzasu) {
        final Iterator<zzasz> iterator = this.zzdey.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzb(zzasu);
        }
    }
    
    public void zzbe(final String s) {
        zzasy.zza(this, s);
    }
    
    public void zzc(final zzasu zzasu) {
        final Iterator<zzata> iterator = this.zzdez.iterator();
        while (iterator.hasNext()) {
            iterator.next().zzc(zzasu);
        }
    }
    
    public final WebResourceResponse zzd(final zzasu zzasu) {
        final Iterator<zzatb> iterator = this.zzdex.iterator();
        while (iterator.hasNext()) {
            final WebResourceResponse zzd = iterator.next().zzd(zzasu);
            if (zzd != null) {
                return zzd;
            }
        }
        return null;
    }
    
    protected final zzash zzvv() {
        return this.zzdfa;
    }
}
