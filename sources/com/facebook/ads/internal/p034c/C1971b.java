package com.facebook.ads.internal.p034c;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.util.Log;
import com.facebook.ads.AudienceNetworkAds;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p070d.C2609a;
import com.facebook.ads.internal.p034c.C1969a.C1964a;
import com.facebook.ads.internal.p034c.p035a.C1967c;
import com.facebook.ads.internal.p045n.C2051a;
import com.facebook.ads.internal.p045n.C2053c;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.UUID;

@UiThread
/* renamed from: com.facebook.ads.internal.c.b */
public abstract class C1971b implements C1967c {
    /* renamed from: a */
    final Context f4320a;
    /* renamed from: b */
    final C1984h f4321b;
    /* renamed from: c */
    final String f4322c = UUID.randomUUID().toString();
    /* renamed from: d */
    final C1969a f4323d;
    /* renamed from: e */
    private final Handler f4324e;

    C1971b(Context context) {
        this.f4320a = context;
        C2051a.m4997b(this.f4320a);
        this.f4323d = new C1969a(context, this);
        this.f4321b = new C1984h(context, this);
        this.f4324e = new Handler(Looper.getMainLooper());
    }

    /* renamed from: a */
    abstract Message mo5455a();

    /* renamed from: a */
    void m4667a(int i, @Nullable Bundle bundle) {
        try {
            Messenger messenger = this.f4321b.f4354a;
            if (messenger != null) {
                this.f4321b.m4732a(messenger, i, bundle);
            }
        } catch (RemoteException e) {
            this.f4321b.m4734b();
            this.f4321b.m4733a("Error during sending command!");
        }
    }

    /* renamed from: a */
    void m4668a(int i, AdErrorType adErrorType, @Nullable String str) {
        Bundle bundle = new Bundle();
        if (str != null) {
            bundle.putString("STR_ERROR_MESSAGE_KEY", str);
        } else {
            bundle.putString("STR_ERROR_MESSAGE_KEY", adErrorType.getDefaultErrorMessage());
        }
        bundle.putInt("INT_ERROR_CODE_KEY", adErrorType.getErrorCode());
        mo5445a(i, this.f4322c, bundle);
    }

    /* renamed from: a */
    public void mo5445a(int i, String str, @Nullable Bundle bundle) {
        final Message obtain = Message.obtain(null, i);
        obtain.getData().putString("STR_AD_ID_KEY", str);
        if (bundle != null) {
            obtain.getData().putBundle("BUNDLE_EXTRAS_KEY", bundle);
        }
        if (Looper.myLooper() != Looper.getMainLooper()) {
            this.f4324e.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C1971b f4319b;

                public void run() {
                    this.f4319b.mo5456a(obtain);
                }
            });
        } else {
            mo5456a(obtain);
        }
    }

    /* renamed from: a */
    public abstract void mo5456a(Message message);

    /* renamed from: a */
    boolean m4671a(Context context) {
        if (AdInternalSettings.f4777b) {
            return true;
        }
        if (AdInternalSettings.f4778c) {
            C2625a.m6738a(this.f4320a, "ipc", C2626b.ag, new Exception("Multiprocess support is off"));
            return false;
        } else if (!C2078a.m5083V(context)) {
            return false;
        } else {
            int i = AdInternalSettings.f4780e;
            AdInternalSettings.f4780e = i + 1;
            if (i <= 0 && C2078a.m5084W(context)) {
                return false;
            }
            if (!C2609a.m6706a(this.f4320a)) {
                i = AdInternalSettings.f4781f;
                AdInternalSettings.f4781f = i + 1;
                if (i > 0) {
                    if (AdInternalSettings.f4781f != 3) {
                        return false;
                    }
                    C2625a.m6738a(this.f4320a, "ipc", C2626b.af, new Exception("Marker file not created after 3 requests."));
                    return false;
                }
            }
            return C2053c.m5003b(this.f4320a);
        }
    }

    /* renamed from: b */
    public void m4672b() {
        try {
            if (this.f4321b.f4354a != null) {
                this.f4321b.f4354a.send(mo5455a());
            }
        } catch (RemoteException e) {
            this.f4321b.m4734b();
            mo5457c();
            this.f4321b.m4733a("Error during sending load command!");
        }
    }

    /* renamed from: c */
    public abstract void mo5457c();

    /* renamed from: d */
    public abstract void mo5458d();

    /* renamed from: e */
    public void m4675e() {
        if (C2078a.m5087Z(this.f4320a) && this.f4323d.f4315a != C1964a.DESTROYED) {
            C2625a.m6738a(this.f4320a, "api", C2626b.f6554s, new Exception("Destroy was not called."));
            Log.e(AudienceNetworkAds.TAG, "You didn't call destroy() for Ad Object. This may lead to leaking memory. Please, always call destroy() when you don't need this Ad Object any more.");
            mo5458d();
        }
    }
}
