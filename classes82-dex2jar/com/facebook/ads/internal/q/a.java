// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.q;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.facebook.ads.internal.w.h.b;
import android.os.Handler;
import android.os.Message;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Messenger;
import android.content.Context;

public class a
{
    private final Context a;
    private final String b;
    private final String c;
    private boolean d;
    private Messenger e;
    private final ServiceConnection f;
    
    public a(final Context a, final String b, final String c) {
        this.d = false;
        this.f = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                com.facebook.ads.internal.q.a.this.d = true;
                com.facebook.ads.internal.q.a.this.e = new Messenger(binder);
                final Message obtain = Message.obtain((Handler)null, 1);
                obtain.setData(com.facebook.ads.internal.q.a.a(com.facebook.ads.internal.q.a.this));
                while (true) {
                    try {
                        com.facebook.ads.internal.q.a.this.e.send(obtain);
                        com.facebook.ads.internal.q.a.this.a.unbindService((ServiceConnection)this);
                    }
                    catch (RemoteException ex) {
                        com.facebook.ads.internal.w.h.a.b(com.facebook.ads.internal.q.a.this.a, "generic", com.facebook.ads.internal.w.h.b.y, (Exception)ex);
                        continue;
                    }
                    break;
                }
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                while (true) {
                    try {
                        com.facebook.ads.internal.q.a.this.a.unbindService((ServiceConnection)this);
                        com.facebook.ads.internal.q.a.this.e = null;
                        com.facebook.ads.internal.q.a.this.d = false;
                    }
                    catch (IllegalArgumentException ex) {
                        continue;
                    }
                    break;
                }
            }
        };
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    static /* synthetic */ Bundle a(final a a) {
        final Bundle bundle = new Bundle();
        bundle.putInt("PARAM_PROTOCOL_VERSION", 1);
        bundle.putString("PARAM_AN_UUID", a.c);
        bundle.putString("PARAM_REQUEST_ID", a.b);
        return bundle;
    }
    
    public void a() {
        final Intent intent = new Intent();
        intent.setClassName("com.facebook.katana", "com.facebook.audiencenetwork.AudienceNetworkService");
        try {
            if (!this.a.bindService(intent, this.f, 1)) {
                this.a.unbindService(this.f);
            }
        }
        catch (Exception ex) {
            com.facebook.ads.internal.w.h.a.b(this.a, "generic", com.facebook.ads.internal.w.h.b.z, ex);
        }
    }
}
