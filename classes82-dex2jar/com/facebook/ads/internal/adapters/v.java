// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import java.io.Serializable;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.h;
import com.facebook.ads.internal.view.i.b.g;
import com.facebook.ads.internal.view.i.a.a;
import com.facebook.ads.internal.view.i.b.q;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.content.IntentFilter;
import com.facebook.ads.internal.view.p;
import android.content.Context;
import android.content.BroadcastReceiver;

public class v extends BroadcastReceiver
{
    private Context a;
    private p b;
    private boolean c;
    
    public v(final p b, final Context context) {
        this.c = false;
        this.b = b;
        this.a = context.getApplicationContext();
    }
    
    public void a() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.b.getUniqueId());
        intentFilter.addAction("videoInterstitalEvent:" + this.b.getUniqueId());
        intentFilter.addAction("performCtaClick:" + this.b.getUniqueId());
        LocalBroadcastManager.getInstance(this.a).registerReceiver((BroadcastReceiver)this, intentFilter);
    }
    
    public void b() {
        try {
            LocalBroadcastManager.getInstance(this.a).unregisterReceiver((BroadcastReceiver)this);
        }
        catch (Exception ex) {}
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String[] split = intent.getAction().split(":");
        if (split.length == 2 && split[1].equals(this.b.getUniqueId())) {
            if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
                if (this.b.getListener() != null) {
                    this.b.getListener().g();
                    this.b.getListener().a();
                }
            }
            else if (split[0].equals("videoInterstitalEvent")) {
                final Serializable serializableExtra = intent.getSerializableExtra("event");
                if (serializableExtra instanceof q) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().f();
                        this.b.getListener().a();
                    }
                    if (this.c) {
                        this.b.a(1);
                    }
                    else {
                        this.b.a(((q)serializableExtra).b());
                    }
                    this.b.setVisibility(0);
                    this.b.a(com.facebook.ads.internal.view.i.a.a.b);
                    return;
                }
                if (serializableExtra instanceof g) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().d();
                    }
                }
                else if (serializableExtra instanceof h) {
                    if (this.b.getListener() != null) {
                        this.b.getListener().e();
                    }
                }
                else {
                    if (serializableExtra instanceof c) {
                        if (this.b.getListener() != null) {
                            this.b.getListener().h();
                        }
                        this.c = true;
                        return;
                    }
                    if (serializableExtra instanceof k) {
                        if (this.b.getListener() != null) {
                            this.b.getListener().c();
                        }
                        this.c = false;
                        return;
                    }
                    if (serializableExtra instanceof i && this.b.getListener() != null) {
                        this.b.getListener().b();
                    }
                }
            }
            else if (split[0].equals("performCtaClick")) {
                this.b.b();
            }
        }
    }
}
