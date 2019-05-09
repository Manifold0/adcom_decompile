package com.facebook.ads;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.p033b.C1943a;
import com.facebook.ads.internal.p033b.C1947b;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2069d;
import com.facebook.ads.internal.protocol.C2071f;
import com.facebook.ads.internal.view.p019c.C2248a;
import com.facebook.ads.internal.view.p019c.C2251c;

public class AdView extends RelativeLayout implements Ad {
    /* renamed from: a */
    private final DisplayMetrics f3690a;
    /* renamed from: b */
    private final C2069d f3691b;
    /* renamed from: c */
    private final String f3692c;
    /* renamed from: d */
    private C1947b f3693d;
    /* renamed from: e */
    private AdListener f3694e;
    /* renamed from: f */
    private View f3695f;
    /* renamed from: g */
    private C2251c f3696g;
    /* renamed from: h */
    private String f3697h;

    public AdView(Context context, final String str, AdSize adSize) {
        super(context);
        if (adSize == null || adSize == AdSize.INTERSTITIAL) {
            throw new IllegalArgumentException("adSize");
        }
        this.f3690a = getContext().getResources().getDisplayMetrics();
        this.f3691b = adSize.toInternalAdSize();
        this.f3692c = str;
        C1943a c1943a = new C1943a(str, C2071f.m5049a(this.f3691b), AdPlacementType.BANNER, adSize.toInternalAdSize(), 1);
        c1943a.m4602a(this.f3697h);
        this.f3693d = new C1947b(context, c1943a);
        this.f3693d.m4609a(new C1786a(this) {
            /* renamed from: b */
            final /* synthetic */ AdView f3689b;

            /* renamed from: com.facebook.ads.AdView$1$1 */
            class C17851 implements OnLongClickListener {
                /* renamed from: a */
                final /* synthetic */ C17871 f3687a;

                C17851(C17871 c17871) {
                    this.f3687a = c17871;
                }

                public boolean onLongClick(View view) {
                    boolean z = false;
                    this.f3687a.f3689b.f3696g.setBounds(0, 0, this.f3687a.f3689b.f3695f.getWidth(), this.f3687a.f3689b.f3695f.getHeight());
                    C2251c f = this.f3687a.f3689b.f3696g;
                    if (!this.f3687a.f3689b.f3696g.m5768a()) {
                        z = true;
                    }
                    f.m5767a(z);
                    return true;
                }
            }

            /* renamed from: a */
            public void mo5323a() {
                if (this.f3689b.f3694e != null) {
                    this.f3689b.f3694e.onAdClicked(this.f3689b);
                }
            }

            /* renamed from: a */
            public void mo5324a(View view) {
                if (view == null) {
                    throw new IllegalStateException("Cannot present null view");
                }
                this.f3689b.f3695f = view;
                this.f3689b.removeAllViews();
                this.f3689b.addView(this.f3689b.f3695f);
                if (this.f3689b.f3695f instanceof C2248a) {
                    C2071f.m5050a(this.f3689b.f3690a, this.f3689b.f3695f, this.f3689b.f3691b);
                }
                if (this.f3689b.f3694e != null) {
                    this.f3689b.f3694e.onAdLoaded(this.f3689b);
                }
                if (C2078a.m5091b(this.f3689b.getContext())) {
                    this.f3689b.f3696g = new C2251c();
                    this.f3689b.f3696g.m5766a(str);
                    this.f3689b.f3696g.m5770b(this.f3689b.getContext().getPackageName());
                    if (this.f3689b.f3693d.m4614b() != null) {
                        this.f3689b.f3696g.m5764a(this.f3689b.f3693d.m4614b().m4980a());
                    }
                    if (this.f3689b.f3695f instanceof C2248a) {
                        this.f3689b.f3696g.m5765a(((C2248a) this.f3689b.f3695f).getViewabilityChecker());
                    }
                    this.f3689b.f3695f.setOnLongClickListener(new C17851(this));
                    this.f3689b.f3695f.getOverlay().add(this.f3689b.f3696g);
                }
            }

            /* renamed from: a */
            public void mo5325a(AdAdapter adAdapter) {
                if (this.f3689b.f3693d != null) {
                    this.f3689b.f3693d.m4618e();
                }
            }

            /* renamed from: a */
            public void mo5326a(C2065a c2065a) {
                if (this.f3689b.f3694e != null) {
                    this.f3689b.f3694e.onError(this.f3689b, AdError.getAdErrorFromWrapper(c2065a));
                }
            }

            /* renamed from: b */
            public void mo5327b() {
                if (this.f3689b.f3694e != null) {
                    this.f3689b.f3694e.onLoggingImpression(this.f3689b);
                }
            }
        });
    }

    /* renamed from: a */
    private void m3950a(String str) {
        this.f3693d.m4615b(str);
    }

    public void destroy() {
        if (this.f3693d != null) {
            this.f3693d.m4613a(true);
            this.f3693d = null;
        }
        if (this.f3696g != null && C2078a.m5091b(getContext())) {
            this.f3696g.m5769b();
            this.f3695f.getOverlay().remove(this.f3696g);
        }
        removeAllViews();
        this.f3695f = null;
        this.f3694e = null;
    }

    @Deprecated
    public void disableAutoRefresh() {
    }

    public String getPlacementId() {
        return this.f3692c;
    }

    public boolean isAdInvalidated() {
        return this.f3693d == null || this.f3693d.m4620g();
    }

    public void loadAd() {
        m3950a(null);
    }

    public void loadAdFromBid(String str) {
        m3950a(str);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (this.f3695f != null) {
            C2071f.m5050a(this.f3690a, this.f3695f, this.f3691b);
        }
    }

    public void setAdListener(AdListener adListener) {
        this.f3694e = adListener;
    }

    public void setExtraHints(ExtraHints extraHints) {
        this.f3697h = extraHints.getHints();
    }
}
