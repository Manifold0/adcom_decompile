package com.facebook.ads;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.adapters.C1786a;
import com.facebook.ads.internal.adapters.C1897d;
import com.facebook.ads.internal.adapters.C1911n;
import com.facebook.ads.internal.adapters.C1912f;
import com.facebook.ads.internal.p033b.C1943a;
import com.facebook.ads.internal.p033b.C1952d;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.view.p019c.C2251c;
import com.facebook.ads.p018a.C1800a;
import java.util.EnumSet;

public class InstreamVideoAdView extends RelativeLayout implements Ad {
    /* renamed from: a */
    private final Context f3747a;
    /* renamed from: b */
    private final String f3748b;
    /* renamed from: c */
    private final AdSize f3749c;
    /* renamed from: d */
    private C1952d f3750d;
    @Nullable
    /* renamed from: e */
    private C1912f f3751e;
    /* renamed from: f */
    private boolean f3752f;
    @Nullable
    /* renamed from: g */
    private InstreamVideoAdListener f3753g;
    @Nullable
    /* renamed from: h */
    private View f3754h;
    @Nullable
    /* renamed from: i */
    private Bundle f3755i;
    @Nullable
    /* renamed from: j */
    private C2251c f3756j;

    /* renamed from: com.facebook.ads.InstreamVideoAdView$1 */
    class C17991 extends C1786a {
        /* renamed from: a */
        final /* synthetic */ InstreamVideoAdView f3745a;

        /* renamed from: com.facebook.ads.InstreamVideoAdView$1$1 */
        class C17981 implements OnLongClickListener {
            /* renamed from: a */
            final /* synthetic */ C17991 f3744a;

            C17981(C17991 c17991) {
                this.f3744a = c17991;
            }

            public boolean onLongClick(View view) {
                boolean z = false;
                if (this.f3744a.f3745a.f3754h == null || this.f3744a.f3745a.f3756j == null) {
                    return false;
                }
                this.f3744a.f3745a.f3756j.setBounds(0, 0, this.f3744a.f3745a.f3754h.getWidth(), this.f3744a.f3745a.f3754h.getHeight());
                C2251c f = this.f3744a.f3745a.f3756j;
                if (!this.f3744a.f3745a.f3756j.m5768a()) {
                    z = true;
                }
                f.m5767a(z);
                return true;
            }
        }

        C17991(InstreamVideoAdView instreamVideoAdView) {
            this.f3745a = instreamVideoAdView;
        }

        /* renamed from: a */
        public void mo5323a() {
            if (this.f3745a.f3753g != null) {
                this.f3745a.f3753g.onAdClicked(this.f3745a);
            }
        }

        /* renamed from: a */
        public void mo5324a(View view) {
            if (view == null) {
                throw new IllegalStateException("Cannot present null view");
            }
            this.f3745a.f3754h = view;
            this.f3745a.removeAllViews();
            this.f3745a.f3754h.setLayoutParams(new LayoutParams(-1, -1));
            this.f3745a.addView(this.f3745a.f3754h);
            if (C2078a.m5091b(this.f3745a.f3747a)) {
                this.f3745a.f3756j = new C2251c();
                this.f3745a.f3756j.m5766a(this.f3745a.f3748b);
                this.f3745a.f3756j.m5770b(this.f3745a.f3747a.getPackageName());
                if (this.f3745a.f3750d.m4614b() != null) {
                    this.f3745a.f3756j.m5764a(this.f3745a.f3750d.m4614b().m4980a());
                }
                this.f3745a.f3754h.getOverlay().add(this.f3745a.f3756j);
                this.f3745a.f3754h.setOnLongClickListener(new C17981(this));
            }
        }

        /* renamed from: a */
        public void mo5325a(AdAdapter adAdapter) {
            if (this.f3745a.f3750d != null) {
                this.f3745a.f3752f = true;
                if (this.f3745a.f3753g != null) {
                    this.f3745a.f3753g.onAdLoaded(this.f3745a);
                }
            }
        }

        /* renamed from: a */
        public void mo5326a(C2065a c2065a) {
            if (this.f3745a.f3753g != null) {
                this.f3745a.f3753g.onError(this.f3745a, AdError.getAdErrorFromWrapper(c2065a));
            }
        }

        /* renamed from: b */
        public void mo5327b() {
            if (this.f3745a.f3753g != null) {
                this.f3745a.f3753g.onLoggingImpression(this.f3745a);
            }
        }

        /* renamed from: c */
        public void mo5342c() {
            if (this.f3745a.f3753g != null) {
                this.f3745a.f3753g.onAdVideoComplete(this.f3745a);
            }
        }
    }

    /* renamed from: com.facebook.ads.InstreamVideoAdView$2 */
    class C18012 implements C1800a {
        /* renamed from: a */
        final /* synthetic */ InstreamVideoAdView f3746a;

        C18012(InstreamVideoAdView instreamVideoAdView) {
            this.f3746a = instreamVideoAdView;
        }

        /* renamed from: a */
        public void mo5343a(C1911n c1911n) {
            this.f3746a.f3752f = true;
            if (this.f3746a.f3753g != null) {
                this.f3746a.f3753g.onAdLoaded(this.f3746a);
            }
        }

        /* renamed from: a */
        public void mo5344a(C1911n c1911n, View view) {
            if (view == null) {
                throw new IllegalStateException("Cannot present null view");
            }
            this.f3746a.f3754h = view;
            this.f3746a.removeAllViews();
            this.f3746a.f3754h.setLayoutParams(new LayoutParams(-1, -1));
            this.f3746a.addView(this.f3746a.f3754h);
        }

        /* renamed from: a */
        public void mo5345a(C1911n c1911n, AdError adError) {
            if (this.f3746a.f3753g != null) {
                this.f3746a.f3753g.onError(this.f3746a, adError);
            }
        }

        /* renamed from: b */
        public void mo5346b(C1911n c1911n) {
            if (this.f3746a.f3753g != null) {
                this.f3746a.f3753g.onAdClicked(this.f3746a);
            }
        }

        /* renamed from: c */
        public void mo5347c(C1911n c1911n) {
        }

        /* renamed from: d */
        public void mo5348d(C1911n c1911n) {
            if (this.f3746a.f3753g != null) {
                this.f3746a.f3753g.onAdVideoComplete(this.f3746a);
            }
        }
    }

    public InstreamVideoAdView(Context context, Bundle bundle) {
        this(context, bundle.getString("placementID"), (AdSize) bundle.get("adSize"));
        this.f3755i = bundle;
    }

    public InstreamVideoAdView(Context context, String str, AdSize adSize) {
        super(context);
        this.f3752f = false;
        this.f3747a = context;
        this.f3748b = str;
        this.f3749c = adSize;
        this.f3750d = getController();
    }

    /* renamed from: a */
    private void m4020a(String str) {
        if (this.f3755i != null) {
            this.f3751e = (C1912f) new C1897d().m4382a(AdPlacementType.INSTREAM);
            this.f3751e.m4427a(getContext(), new C18012(this), this.f3750d.g, this.f3755i.getBundle("adapter"), EnumSet.of(CacheFlag.NONE));
            return;
        }
        this.f3750d.m4615b(str);
    }

    private C1952d getController() {
        this.f3750d = new C1952d(getContext(), new C1943a(this.f3748b, C2070e.INSTREAM_VIDEO, AdPlacementType.INSTREAM, this.f3749c.toInternalAdSize(), 1));
        this.f3750d.m4609a(new C17991(this));
        return this.f3750d;
    }

    public void destroy() {
        if (this.f3756j != null && C2078a.m5091b(this.f3747a)) {
            this.f3756j.m5769b();
            if (this.f3754h != null) {
                this.f3754h.getOverlay().remove(this.f3756j);
            }
        }
        if (this.f3750d != null) {
            this.f3750d.m4613a(true);
            this.f3750d = null;
            this.f3750d = getController();
            this.f3751e = null;
            this.f3752f = false;
            removeAllViews();
        }
    }

    public String getPlacementId() {
        return this.f3748b;
    }

    public Bundle getSaveInstanceState() {
        C1911n c1911n = this.f3751e != null ? this.f3751e : (C1911n) this.f3750d.f;
        if (c1911n == null) {
            return null;
        }
        Bundle g = c1911n.mo5398g();
        if (g == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putBundle("adapter", g);
        bundle.putString("placementID", this.f3748b);
        bundle.putSerializable("adSize", this.f3749c);
        return bundle;
    }

    public boolean isAdInvalidated() {
        return this.f3750d == null || this.f3750d.m4620g();
    }

    public boolean isAdLoaded() {
        return this.f3752f;
    }

    public void loadAd() {
        m4020a(null);
    }

    public void loadAdFromBid(String str) {
        m4020a(str);
    }

    public void setAdListener(InstreamVideoAdListener instreamVideoAdListener) {
        this.f3753g = instreamVideoAdListener;
    }

    public boolean show() {
        if (this.f3752f && (this.f3750d != null || this.f3751e != null)) {
            if (this.f3751e != null) {
                this.f3751e.mo5397e();
            } else {
                this.f3750d.m4618e();
            }
            this.f3752f = false;
            return true;
        } else if (this.f3753g == null) {
            return false;
        } else {
            this.f3753g.onError(this, AdError.SHOW_CALLED_BEFORE_LOAD_ERROR);
            return false;
        }
    }
}
