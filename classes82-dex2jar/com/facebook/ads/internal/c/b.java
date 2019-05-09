// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.protocol.AdErrorType;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.os.Message;
import android.os.Looper;
import java.util.UUID;
import android.os.Handler;
import android.content.Context;
import android.support.annotation.UiThread;
import com.facebook.ads.internal.c.a.c;

@UiThread
public abstract class b implements c
{
    final Context a;
    final h b;
    final String c;
    final a d;
    private final Handler e;
    
    b(final Context a) {
        this.c = UUID.randomUUID().toString();
        com.facebook.ads.internal.n.a.b(this.a = a);
        this.d = new a(a, this);
        this.b = new h(a, this);
        this.e = new Handler(Looper.getMainLooper());
    }
    
    abstract Message a();
    
    void a(final int n, @Nullable final Bundle bundle) {
        try {
            final Messenger a = this.b.a;
            if (a != null) {
                this.b.a(a, n, bundle);
            }
        }
        catch (RemoteException ex) {
            this.b.b();
            this.b.a("Error during sending command!");
        }
    }
    
    void a(final int n, final AdErrorType adErrorType, @Nullable final String s) {
        final Bundle bundle = new Bundle();
        if (s != null) {
            bundle.putString("STR_ERROR_MESSAGE_KEY", s);
        }
        else {
            bundle.putString("STR_ERROR_MESSAGE_KEY", adErrorType.getDefaultErrorMessage());
        }
        bundle.putInt("INT_ERROR_CODE_KEY", adErrorType.getErrorCode());
        this.a(n, this.c, bundle);
    }
    
    @Override
    public void a(final int n, final String s, @Nullable final Bundle bundle) {
        final Message obtain = Message.obtain((Handler)null, n);
        obtain.getData().putString("STR_AD_ID_KEY", s);
        if (bundle != null) {
            obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.e.post((Runnable)new Runnable() {
                @Override
                public void run() {
                    com.facebook.ads.internal.c.b.this.a(obtain);
                }
            });
            return;
        }
        this.a(obtain);
    }
    
    public abstract void a(final Message p0);
    
    boolean a(final Context context) {
        final boolean b = false;
        boolean b2;
        if (AdInternalSettings.b) {
            b2 = true;
        }
        else {
            if (AdInternalSettings.c) {
                com.facebook.ads.internal.w.h.a.a(this.a, "ipc", com.facebook.ads.internal.w.h.b.ag, new Exception("Multiprocess support is off"));
                return false;
            }
            b2 = b;
            if (com.facebook.ads.internal.r.a.V(context)) {
                final int e = AdInternalSettings.e;
                AdInternalSettings.e = e + 1;
                if (e <= 0) {
                    b2 = b;
                    if (com.facebook.ads.internal.r.a.W(context)) {
                        return b2;
                    }
                }
                if (!com.facebook.ads.internal.w.d.a.a(this.a)) {
                    final int f = AdInternalSettings.f;
                    AdInternalSettings.f = f + 1;
                    if (f > 0) {
                        b2 = b;
                        if (AdInternalSettings.f == 3) {
                            com.facebook.ads.internal.w.h.a.a(this.a, "ipc", com.facebook.ads.internal.w.h.b.af, new Exception("Marker file not created after 3 requests."));
                            return false;
                        }
                        return b2;
                    }
                }
                return com.facebook.ads.internal.n.c.b(this.a);
            }
        }
        return b2;
    }
    
    public void b() {
        try {
            if (this.b.a != null) {
                this.b.a.send(this.a());
            }
        }
        catch (RemoteException ex) {
            this.b.b();
            this.c();
            this.b.a("Error during sending load command!");
        }
    }
    
    public abstract void c();
    
    public abstract void d();
    
    public void e() {
        if (com.facebook.ads.internal.r.a.Z(this.a) && this.d.a != com.facebook.ads.internal.c.a.a.f) {
            com.facebook.ads.internal.w.h.a.a(this.a, "api", com.facebook.ads.internal.w.h.b.s, new Exception("Destroy was not called."));
            Log.e("FBAudienceNetwork", "You didn't call destroy() for Ad Object. This may lead to leaking memory. Please, always call destroy() when you don't need this Ad Object any more.");
            this.d();
        }
    }
}
