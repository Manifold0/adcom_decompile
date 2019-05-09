package com.facebook.ads.internal.p034c;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.protocol.AdErrorType;

/* renamed from: com.facebook.ads.internal.c.h */
public class C1984h extends Handler {
    @Nullable
    /* renamed from: a */
    Messenger f4354a;
    /* renamed from: b */
    boolean f4355b;
    /* renamed from: c */
    boolean f4356c;
    /* renamed from: d */
    private final Context f4357d;
    /* renamed from: e */
    private final Messenger f4358e;
    /* renamed from: f */
    private final C1971b f4359f;
    /* renamed from: g */
    private ServiceConnection f4360g = new C19831(this);

    /* renamed from: com.facebook.ads.internal.c.h$1 */
    class C19831 implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ C1984h f4353a;

        C19831(C1984h c1984h) {
            this.f4353a = c1984h;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f4353a.f4354a = new Messenger(iBinder);
            this.f4353a.m4733a("Attached.");
            try {
                this.f4353a.m4732a(this.f4353a.f4354a, 1, null);
                if (this.f4353a.f4356c) {
                    this.f4353a.f4356c = false;
                    this.f4353a.f4359f.m4672b();
                }
            } catch (RemoteException e) {
                C1984h.m4730b(this.f4353a);
            }
            this.f4353a.m4733a("Remote service connected.");
        }

        public void onServiceDisconnected(ComponentName componentName) {
            this.f4353a.m4733a("Disconnected.");
            if (this.f4353a.f4355b) {
                C1984h.m4730b(this.f4353a);
            }
        }
    }

    C1984h(Context context, C1971b c1971b) {
        super(Looper.getMainLooper());
        this.f4357d = context;
        this.f4358e = new Messenger(this);
        this.f4359f = c1971b;
    }

    /* renamed from: b */
    static /* synthetic */ void m4730b(C1984h c1984h) {
        c1984h.f4354a = null;
        c1984h.m4734b();
        c1984h.f4359f.m4668a(10, AdErrorType.REMOTE_ADS_SERVICE_ERROR, null);
    }

    /* renamed from: a */
    void m4731a() {
        this.f4355b = this.f4357d.bindService(new Intent(this.f4357d, AdsMessengerService.class), this.f4360g, 1);
        if (this.f4355b) {
            m4733a("Binding.");
            return;
        }
        C2625a.m6738a(this.f4357d, "ipc", C2626b.ab, new Exception("Context.bind() returned false."));
        this.f4356c = false;
        m4733a("Can't bind to service. Use internal.");
        this.f4359f.mo5457c();
    }

    /* renamed from: a */
    void m4732a(Messenger messenger, int i, @Nullable Bundle bundle) {
        Message obtain = Message.obtain(null, i);
        obtain.replyTo = this.f4358e;
        if (bundle != null) {
            obtain.setData(bundle);
        }
        obtain.getData().putString("STR_AD_ID_KEY", this.f4359f.f4322c);
        messenger.send(obtain);
    }

    /* renamed from: a */
    public void m4733a(String str) {
    }

    /* renamed from: b */
    void m4734b() {
        if (this.f4355b) {
            if (this.f4354a != null) {
                try {
                    m4732a(this.f4354a, 2, null);
                } catch (RemoteException e) {
                }
            }
            this.f4355b = false;
            this.f4357d.unbindService(this.f4360g);
            m4733a("Unbinding.");
        }
    }

    public void handleMessage(Message message) {
        if (message.what == 3) {
            m4733a("Received check alive.");
            return;
        }
        String string = message.getData().getString("STR_AD_ID_KEY");
        m4733a("Received message " + message.what + " for Ad: " + string);
        if (this.f4359f.f4322c.equals(string)) {
            this.f4359f.mo5456a(message);
        }
    }
}
