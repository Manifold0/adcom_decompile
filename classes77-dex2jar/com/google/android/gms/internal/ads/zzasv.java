// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.graphics.Canvas;
import android.annotation.TargetApi;
import android.webkit.ValueCallback;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzasv extends zzass
{
    @GuardedBy("this")
    private boolean zzdfh;
    @GuardedBy("this")
    private boolean zzdfi;
    
    public zzasv(final zzash zzash) {
        super(zzash);
        zzbv.zzeo().zzqe();
    }
    
    private final void zzqf() {
        synchronized (this) {
            if (!this.zzdfi) {
                this.zzdfi = true;
                zzbv.zzeo().zzqf();
            }
        }
    }
    
    public void destroy() {
        synchronized (this) {
            if (!this.zzdfh) {
                this.zzdfh = true;
                this.zzam(false);
                zzakb.v("Initiating WebView self destruct sequence in 3...");
                zzakb.v("Loading blank page in WebView, 2...");
                try {
                    super.loadUrl("about:blank");
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                    zzbv.zzeo().zza(unsatisfiedLinkError, "AdWebViewImpl.loadUrlUnsafe");
                    zzakb.zzd("#007 Could not call remote method.", (Throwable)unsatisfiedLinkError);
                }
            }
        }
    }
    
    @TargetApi(19)
    public void evaluateJavascript(final String s, final ValueCallback<String> valueCallback) {
        synchronized (this) {
            if (this.isDestroyed()) {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue((Object)null);
                }
            }
            else {
                super.evaluateJavascript(s, (ValueCallback)valueCallback);
            }
        }
    }
    
    protected void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.isDestroyed()) {
                    this.zzam(true);
                }
                this.zzqf();
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public final boolean isDestroyed() {
        synchronized (this) {
            return this.zzdfh;
        }
    }
    
    public void loadData(final String s, final String s2, final String s3) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.loadData(s, s2, s3);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    public void loadDataWithBaseURL(final String s, final String s2, final String s3, final String s4, final String s5) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.loadDataWithBaseURL(s, s2, s3, s4, s5);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    @Override
    public void loadUrl(final String s) {
        synchronized (this) {
            if (!this.isDestroyed()) {
                super.loadUrl(s);
            }
            else {
                zzakb.zzdk("#004 The webview is destroyed. Ignoring action.");
            }
        }
    }
    
    @TargetApi(21)
    protected void onDraw(final Canvas canvas) {
        if (this.isDestroyed()) {
            return;
        }
        super.onDraw(canvas);
    }
    
    public void onPause() {
        if (this.isDestroyed()) {
            return;
        }
        super.onPause();
    }
    
    public void onResume() {
        if (this.isDestroyed()) {
            return;
        }
        super.onResume();
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return !this.isDestroyed() && super.onTouchEvent(motionEvent);
    }
    
    public void stopLoading() {
        if (this.isDestroyed()) {
            return;
        }
        super.stopLoading();
    }
    
    @GuardedBy("this")
    protected void zzam(final boolean b) {
    }
    
    @Override
    public final void zzc(final zzasu zzasu) {
        synchronized (this) {
            if (this.isDestroyed()) {
                zzakb.v("Blank page loaded, 1...");
                this.zzuk();
            }
            else {
                super.zzc(zzasu);
            }
        }
    }
    
    public final void zzuk() {
        synchronized (this) {
            zzakb.v("Destroying WebView!");
            this.zzqf();
            zzaoe.zzcvy.execute(new zzasw(this));
        }
    }
}
