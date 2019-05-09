// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.c;

import android.os.Message;
import com.facebook.ads.internal.w.h.a;
import android.content.Intent;
import com.facebook.ads.internal.ipc.AdsMessengerService;
import com.facebook.ads.internal.protocol.AdErrorType;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.os.Looper;
import android.content.ServiceConnection;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Messenger;
import android.os.Handler;

public class h extends Handler
{
    @Nullable
    Messenger a;
    boolean b;
    boolean c;
    private final Context d;
    private final Messenger e;
    private final b f;
    private ServiceConnection g;
    
    h(final Context d, final b f) {
        super(Looper.getMainLooper());
        this.g = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                h.this.a = new Messenger(binder);
                h.this.a("Attached.");
                while (true) {
                    try {
                        h.this.a(h.this.a, 1, null);
                        if (h.this.c) {
                            h.this.c = false;
                            h.this.f.b();
                        }
                        h.this.a("Remote service connected.");
                    }
                    catch (RemoteException ex) {
                        h.b(h.this);
                        continue;
                    }
                    break;
                }
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                h.this.a("Disconnected.");
                if (h.this.b) {
                    h.b(h.this);
                }
            }
        };
        this.d = d;
        this.e = new Messenger((Handler)this);
        this.f = f;
    }
    
    static /* synthetic */ void b(final h h) {
        h.a = null;
        h.b();
        h.f.a(10, AdErrorType.REMOTE_ADS_SERVICE_ERROR, null);
    }
    
    void a() {
        this.b = this.d.bindService(new Intent(this.d, (Class)AdsMessengerService.class), this.g, 1);
        if (this.b) {
            this.a("Binding.");
            return;
        }
        com.facebook.ads.internal.w.h.a.a(this.d, "ipc", com.facebook.ads.internal.w.h.b.ab, new Exception("Context.bind() returned false."));
        this.c = false;
        this.a("Can't bind to service. Use internal.");
        this.f.c();
    }
    
    void a(final Messenger messenger, final int n, @Nullable final Bundle data) {
        final Message obtain = Message.obtain((Handler)null, n);
        obtain.replyTo = this.e;
        if (data != null) {
            obtain.setData(data);
        }
        obtain.getData().putString("STR_AD_ID_KEY", this.f.c);
        messenger.send(obtain);
    }
    
    public void a(final String s) {
    }
    
    void b() {
        if (!this.b) {
            return;
        }
        while (true) {
            if (this.a == null) {
                break Label_0024;
            }
            try {
                this.a(this.a, 2, null);
                this.b = false;
                this.d.unbindService(this.g);
                this.a("Unbinding.");
            }
            catch (RemoteException ex) {
                continue;
            }
            break;
        }
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 3) {
            this.a("Received check alive.");
        }
        else {
            final String string = message.getData().getString("STR_AD_ID_KEY");
            this.a("Received message " + message.what + " for Ad: " + string);
            if (this.f.c.equals(string)) {
                this.f.a(message);
            }
        }
    }
}
