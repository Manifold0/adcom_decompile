package com.facebook.ads.internal.p049q;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.share.internal.MessengerShareContentUtility;

/* renamed from: com.facebook.ads.internal.q.a */
public class C2077a {
    /* renamed from: a */
    private final Context f4689a;
    /* renamed from: b */
    private final String f4690b;
    /* renamed from: c */
    private final String f4691c;
    /* renamed from: d */
    private boolean f4692d = false;
    /* renamed from: e */
    private Messenger f4693e;
    /* renamed from: f */
    private final ServiceConnection f4694f = new C20761(this);

    /* renamed from: com.facebook.ads.internal.q.a$1 */
    class C20761 implements ServiceConnection {
        /* renamed from: a */
        final /* synthetic */ C2077a f4688a;

        C20761(C2077a c2077a) {
            this.f4688a = c2077a;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            this.f4688a.f4692d = true;
            this.f4688a.f4693e = new Messenger(iBinder);
            Message obtain = Message.obtain(null, 1);
            obtain.setData(C2077a.m5056a(this.f4688a));
            try {
                this.f4688a.f4693e.send(obtain);
            } catch (Exception e) {
                C2625a.m6741b(this.f4688a.f4689a, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6560y, e);
            }
            this.f4688a.f4689a.unbindService(this);
        }

        public void onServiceDisconnected(ComponentName componentName) {
            try {
                this.f4688a.f4689a.unbindService(this);
            } catch (IllegalArgumentException e) {
            }
            this.f4688a.f4693e = null;
            this.f4688a.f4692d = false;
        }
    }

    public C2077a(Context context, String str, String str2) {
        this.f4689a = context;
        this.f4690b = str;
        this.f4691c = str2;
    }

    /* renamed from: a */
    static /* synthetic */ Bundle m5056a(C2077a c2077a) {
        Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", c2077a.f4691c);
        bundle.putString("PARAM_REQUEST_ID", c2077a.f4690b);
        return bundle;
    }

    /* renamed from: a */
    public void m5061a() {
        Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.f4689a.bindService(intent, this.f4694f, 1)) {
                this.f4689a.unbindService(this.f4694f);
            }
        } catch (Exception e) {
            C2625a.m6741b(this.f4689a, MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE, C2626b.f6561z, e);
        }
    }
}
