// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.AdError;
import android.content.Intent;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.aa;
import android.content.IntentFilter;
import android.content.BroadcastReceiver;

public class u extends BroadcastReceiver
{
    private String a;
    private t b;
    private s c;
    
    public u(final String a, final s c, final t b) {
        this.c = c;
        this.b = b;
        this.a = a;
    }
    
    public IntentFilter a() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(aa.a.a(this.a));
        intentFilter.addAction(aa.d.a(this.a));
        intentFilter.addAction(aa.e.a(this.a));
        intentFilter.addAction(aa.f.a(this.a));
        intentFilter.addAction(aa.g.a(this.a));
        intentFilter.addAction(aa.h.a(this.a));
        intentFilter.addAction(aa.i.a(this.a));
        intentFilter.addAction(aa.j.a(this.a));
        return intentFilter;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        final String action = intent.getAction();
        if (aa.a.a(this.a).equals(action)) {
            this.b.d(this.c);
        }
        else {
            if (aa.d.a(this.a).equals(action)) {
                this.b.a(this.c, AdError.INTERNAL_ERROR);
                return;
            }
            if (aa.e.a(this.a).equals(action)) {
                this.b.b(this.c);
                return;
            }
            if (aa.f.a(this.a).equals(action)) {
                this.b.c(this.c);
                return;
            }
            if (aa.g.a(this.a).equals(action)) {
                this.b.a();
                return;
            }
            if (aa.i.a(this.a).equals(action)) {
                this.b.e(this.c);
                return;
            }
            if (aa.h.a(this.a).equals(action)) {
                this.b.f(this.c);
                return;
            }
            if (aa.j.a(this.a).equals(action)) {
                this.b.b();
            }
        }
    }
}
