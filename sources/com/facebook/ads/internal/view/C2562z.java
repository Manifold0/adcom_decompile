package com.facebook.ads.internal.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C1822f;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2408g;
import com.facebook.ads.internal.view.p022i.p023b.C2409h;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2414q;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.facebook.ads.internal.view.p022i.p067c.C2440b;
import com.facebook.ads.internal.view.p061e.C2329b;

/* renamed from: com.facebook.ads.internal.view.z */
public class C2562z implements C1921a {
    /* renamed from: a */
    private final C1812l f6290a = new C25561(this);
    /* renamed from: b */
    private final C1814j f6291b = new C25572(this);
    /* renamed from: c */
    private final C1818d f6292c = new C25583(this);
    /* renamed from: d */
    private final C1822f f6293d = new C25594(this);
    /* renamed from: e */
    private final AudienceNetworkActivity f6294e;
    /* renamed from: f */
    private final C2085c f6295f;
    /* renamed from: g */
    private final C2394a f6296g;
    /* renamed from: h */
    private final C1789a f6297h;
    /* renamed from: i */
    private C2423b f6298i;
    /* renamed from: j */
    private int f6299j;

    /* renamed from: com.facebook.ads.internal.view.z$1 */
    class C25561 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2562z f6283a;

        C25561(C2562z c2562z) {
            this.f6283a = c2562z;
        }

        /* renamed from: a */
        public void m6598a(C2411k c2411k) {
            this.f6283a.f6297h.mo5336a("videoInterstitalEvent", (C2061d) c2411k);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.z$2 */
    class C25572 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2562z f6284a;

        C25572(C2562z c2562z) {
            this.f6284a = c2562z;
        }

        /* renamed from: a */
        public void m6600a(C2410i c2410i) {
            this.f6284a.f6297h.mo5336a("videoInterstitalEvent", (C2061d) c2410i);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.z$3 */
    class C25583 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2562z f6285a;

        C25583(C2562z c2562z) {
            this.f6285a = c2562z;
        }

        /* renamed from: a */
        public void m6602a(C2406c c2406c) {
            this.f6285a.f6297h.mo5336a("videoInterstitalEvent", (C2061d) c2406c);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.z$4 */
    class C25594 extends C1822f {
        /* renamed from: a */
        final /* synthetic */ C2562z f6286a;

        C25594(C2562z c2562z) {
            this.f6286a = c2562z;
        }

        /* renamed from: a */
        public void m6604a(C2407e c2407e) {
            this.f6286a.f6294e.finish();
        }
    }

    /* renamed from: com.facebook.ads.internal.view.z$6 */
    class C25616 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ C2562z f6289a;

        C25616(C2562z c2562z) {
            this.f6289a = c2562z;
        }

        public void onClick(View view) {
            this.f6289a.f6297h.mo5335a("performCtaClick");
        }
    }

    public C2562z(final AudienceNetworkActivity audienceNetworkActivity, C2085c c2085c, C1789a c1789a) {
        this.f6294e = audienceNetworkActivity;
        this.f6295f = c2085c;
        this.f6296g = new C2394a(audienceNetworkActivity);
        this.f6296g.m6158a(new C2440b(audienceNetworkActivity));
        this.f6296g.getEventBus().m5029a(this.f6290a, this.f6291b, this.f6292c, this.f6293d);
        this.f6297h = c1789a;
        this.f6296g.setIsFullScreen(true);
        this.f6296g.setVolume(1.0f);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        this.f6296g.setLayoutParams(layoutParams);
        c1789a.mo5333a(this.f6296g);
        View c2367g = new C2367g(audienceNetworkActivity);
        c2367g.setOnClickListener(new OnClickListener(this) {
            /* renamed from: b */
            final /* synthetic */ C2562z f6288b;

            public void onClick(View view) {
                audienceNetworkActivity.finish();
            }
        });
        c1789a.mo5333a(c2367g);
    }

    /* renamed from: a */
    public void m6607a(int i) {
        this.f6296g.setVideoProgressReportIntervalMs(i);
    }

    /* renamed from: a */
    public void mo5403a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity) {
        String stringExtra = intent.getStringExtra("useNativeCtaButton");
        if (!(stringExtra == null || stringExtra.isEmpty())) {
            View c2329b = new C2329b(audienceNetworkActivity, stringExtra);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            int i = (int) (C2600x.f6420b * 16.0f);
            layoutParams.setMargins(i, i, i, i);
            layoutParams.addRule(10);
            layoutParams.addRule(9);
            c2329b.setLayoutParams(layoutParams);
            c2329b.setOnClickListener(new C25616(this));
            this.f6297h.mo5333a(c2329b);
        }
        this.f6299j = intent.getIntExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, 0);
        this.f6298i = new C2423b((Context) audienceNetworkActivity, this.f6295f, this.f6296g, intent.getStringExtra(AudienceNetworkActivity.CLIENT_TOKEN), intent.getBundleExtra(AudienceNetworkActivity.VIDEO_LOGGER));
        this.f6296g.setVideoMPD(intent.getStringExtra(AudienceNetworkActivity.VIDEO_MPD));
        this.f6296g.setVideoURI(intent.getStringExtra(AudienceNetworkActivity.VIDEO_URL));
        if (this.f6299j > 0) {
            this.f6296g.m6155a(this.f6299j);
        }
        if (intent.getBooleanExtra(AudienceNetworkActivity.AUTOPLAY, false)) {
            this.f6296g.m6157a(C2389a.USER_STARTED);
        }
    }

    /* renamed from: a */
    public void mo5404a(Bundle bundle) {
    }

    /* renamed from: a */
    public void m6610a(View view) {
        this.f6296g.setControlsAnchorView(view);
    }

    public void a_(boolean z) {
        this.f6297h.mo5336a("videoInterstitalEvent", new C2408g());
        this.f6296g.m6163e();
    }

    /* renamed from: b */
    public void mo5406b(boolean z) {
        this.f6297h.mo5336a("videoInterstitalEvent", new C2409h());
        this.f6296g.m6157a(C2389a.USER_STARTED);
    }

    public void onDestroy() {
        this.f6297h.mo5336a("videoInterstitalEvent", new C2414q(this.f6299j, this.f6296g.getCurrentPositionInMillis()));
        this.f6298i.m6221b(this.f6296g.getCurrentPositionInMillis());
        this.f6296g.m6165g();
        this.f6296g.m6170l();
    }

    public void setListener(C1789a c1789a) {
    }
}
