// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.ipc;

import android.os.Message;
import android.support.annotation.VisibleForTesting;
import android.os.Handler;
import android.content.Context;
import com.facebook.ads.internal.n.a;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Messenger;
import android.support.annotation.UiThread;
import android.app.Service;

@UiThread
public class AdsMessengerService extends Service
{
    private Messenger a;
    private boolean b;
    private final ServiceConnection c;
    
    public AdsMessengerService() {
        this.c = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                AdsMessengerService.this.b = true;
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                AdsMessengerService.this.b = false;
                AdsMessengerService.this.unbindService(AdsMessengerService.this.c);
            }
        };
    }
    
    public IBinder onBind(final Intent intent) {
        return this.a.getBinder();
    }
    
    public void onCreate() {
        AdInternalSettings.d = true;
        com.facebook.ads.internal.n.a.a((Context)this);
        com.facebook.ads.internal.n.a.b((Context)this);
        this.a = new Messenger((Handler)new a(this.getApplicationContext()));
        if (com.facebook.ads.internal.r.a.Y(this.getApplicationContext())) {
            this.bindService(new Intent(this.getApplicationContext(), (Class)AdsProcessPriorityService.class), this.c, 1);
        }
    }
    
    public void onDestroy() {
        com.facebook.ads.internal.e.a.a().b();
        if (this.b) {
            this.unbindService(this.c);
        }
    }
    
    @VisibleForTesting
    public static class a extends Handler
    {
        private final com.facebook.ads.internal.ipc.a a;
        
        private a(final Context context) {
            this.a = new com.facebook.ads.internal.ipc.a(context);
        }
        
        public void handleMessage(final Message message) {
            final String string = message.getData().getString("STR_AD_ID_KEY");
            switch (message.what) {
                default: {
                    if (!this.a.a(message)) {
                        super.handleMessage(message);
                    }
                }
                case 1: {
                    com.facebook.ads.internal.e.a.a().a(string, message.replyTo);
                }
                case 2: {
                    com.facebook.ads.internal.e.a.a().d(string);
                }
            }
        }
    }
}
