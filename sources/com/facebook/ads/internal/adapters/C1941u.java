package com.facebook.ads.internal.adapters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.facebook.ads.AdError;
import com.facebook.ads.internal.view.p022i.p023b.aa;

/* renamed from: com.facebook.ads.internal.adapters.u */
public class C1941u extends BroadcastReceiver {
    /* renamed from: a */
    private String f4241a;
    /* renamed from: b */
    private C1940t f4242b;
    /* renamed from: c */
    private C1930s f4243c;

    public C1941u(String str, C1930s c1930s, C1940t c1940t) {
        this.f4243c = c1930s;
        this.f4242b = c1940t;
        this.f4241a = str;
    }

    /* renamed from: a */
    public IntentFilter m4595a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(aa.REWARDED_VIDEO_COMPLETE.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARDED_VIDEO_ERROR.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARDED_VIDEO_AD_CLICK.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARDED_VIDEO_IMPRESSION.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARDED_VIDEO_CLOSED.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARD_SERVER_SUCCESS.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARD_SERVER_FAILED.m6206a(this.f4241a));
        intentFilter.addAction(aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.m6206a(this.f4241a));
        return intentFilter;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (aa.REWARDED_VIDEO_COMPLETE.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5436d(this.f4243c);
        } else if (aa.REWARDED_VIDEO_ERROR.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5432a(this.f4243c, AdError.INTERNAL_ERROR);
        } else if (aa.REWARDED_VIDEO_AD_CLICK.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5434b(this.f4243c);
        } else if (aa.REWARDED_VIDEO_IMPRESSION.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5435c(this.f4243c);
        } else if (aa.REWARDED_VIDEO_CLOSED.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5430a();
        } else if (aa.REWARD_SERVER_FAILED.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5437e(this.f4243c);
        } else if (aa.REWARD_SERVER_SUCCESS.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5438f(this.f4243c);
        } else if (aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.m6206a(this.f4241a).equals(action)) {
            this.f4242b.mo5433b();
        }
    }
}
