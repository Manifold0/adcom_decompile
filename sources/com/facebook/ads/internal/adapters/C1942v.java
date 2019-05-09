package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.ads.internal.view.C2532p;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2408g;
import com.facebook.ads.internal.view.p022i.p023b.C2409h;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2414q;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import java.io.Serializable;

/* renamed from: com.facebook.ads.internal.adapters.v */
public class C1942v extends BroadcastReceiver {
    /* renamed from: a */
    private Context f4244a;
    /* renamed from: b */
    private C2532p f4245b;
    /* renamed from: c */
    private boolean f4246c = false;

    public C1942v(C2532p c2532p, Context context) {
        this.f4245b = c2532p;
        this.f4244a = context.getApplicationContext();
    }

    /* renamed from: a */
    public void m4596a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.ads.interstitial.displayed:" + this.f4245b.getUniqueId());
        intentFilter.addAction("videoInterstitalEvent:" + this.f4245b.getUniqueId());
        intentFilter.addAction("performCtaClick:" + this.f4245b.getUniqueId());
        LocalBroadcastManager.getInstance(this.f4244a).registerReceiver(this, intentFilter);
    }

    /* renamed from: b */
    public void m4597b() {
        try {
            LocalBroadcastManager.getInstance(this.f4244a).unregisterReceiver(this);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String[] split = intent.getAction().split(":");
        if (split.length != 2 || !split[1].equals(this.f4245b.getUniqueId())) {
            return;
        }
        if (split[0].equals("com.facebook.ads.interstitial.displayed")) {
            if (this.f4245b.getListener() != null) {
                this.f4245b.getListener().mo5357g();
                this.f4245b.getListener().mo5351a();
            }
        } else if (split[0].equals("videoInterstitalEvent")) {
            Serializable serializableExtra = intent.getSerializableExtra("event");
            if (serializableExtra instanceof C2414q) {
                if (this.f4245b.getListener() != null) {
                    this.f4245b.getListener().mo5356f();
                    this.f4245b.getListener().mo5351a();
                }
                if (this.f4246c) {
                    this.f4245b.m6155a(1);
                } else {
                    this.f4245b.m6155a(((C2414q) serializableExtra).m6209b());
                }
                this.f4245b.setVisibility(0);
                this.f4245b.m6157a(C2389a.USER_STARTED);
            } else if (serializableExtra instanceof C2408g) {
                if (this.f4245b.getListener() != null) {
                    this.f4245b.getListener().mo5354d();
                }
            } else if (serializableExtra instanceof C2409h) {
                if (this.f4245b.getListener() != null) {
                    this.f4245b.getListener().mo5355e();
                }
            } else if (serializableExtra instanceof C2406c) {
                if (this.f4245b.getListener() != null) {
                    this.f4245b.getListener().mo5358h();
                }
                this.f4246c = true;
            } else if (serializableExtra instanceof C2411k) {
                if (this.f4245b.getListener() != null) {
                    this.f4245b.getListener().mo5353c();
                }
                this.f4246c = false;
            } else if ((serializableExtra instanceof C2410i) && this.f4245b.getListener() != null) {
                this.f4245b.getListener().mo5352b();
            }
        } else if (split[0].equals("performCtaClick")) {
            this.f4245b.mo5639b();
        }
    }
}
