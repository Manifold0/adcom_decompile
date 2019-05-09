package com.facebook.ads.internal.view;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.NativeAd;
import com.facebook.ads.internal.adapters.C1942v;
import com.facebook.ads.internal.p021o.C2058a;
import com.facebook.ads.internal.p021o.C2059b;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.C2094a.C2093a;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.C2423b;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.google.android.gms.drive.DriveFile;
import java.util.UUID;

/* renamed from: com.facebook.ads.internal.view.p */
public class C2532p extends C2394a {
    /* renamed from: b */
    private static final String f6183b = C2532p.class.getSimpleName();
    /* renamed from: c */
    private final String f6184c = UUID.randomUUID().toString();
    /* renamed from: d */
    private final C1812l f6185d = new C25291(this);
    /* renamed from: e */
    private final C1814j f6186e = new C25302(this);
    /* renamed from: f */
    private final C1818d f6187f = new C25313(this);
    /* renamed from: g */
    private final C1942v f6188g;
    /* renamed from: h */
    private C2085c f6189h;
    @Nullable
    /* renamed from: i */
    private C2423b f6190i;
    @Nullable
    /* renamed from: j */
    private Uri f6191j;
    @Nullable
    /* renamed from: k */
    private String f6192k;
    @Nullable
    /* renamed from: l */
    private String f6193l;
    @Nullable
    /* renamed from: m */
    private String f6194m;
    @Nullable
    /* renamed from: n */
    private C1807q f6195n;
    @Nullable
    /* renamed from: o */
    private NativeAd f6196o;

    /* renamed from: com.facebook.ads.internal.view.p$1 */
    class C25291 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ C2532p f6180a;

        C25291(C2532p c2532p) {
            this.f6180a = c2532p;
        }

        /* renamed from: a */
        public void m6527a(C2411k c2411k) {
            if (this.f6180a.f6195n != null) {
                this.f6180a.f6195n.mo5353c();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.p$2 */
    class C25302 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ C2532p f6181a;

        C25302(C2532p c2532p) {
            this.f6181a = c2532p;
        }

        /* renamed from: a */
        public void m6529a(C2410i c2410i) {
            if (this.f6181a.f6195n != null) {
                this.f6181a.f6195n.mo5352b();
            }
        }
    }

    /* renamed from: com.facebook.ads.internal.view.p$3 */
    class C25313 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ C2532p f6182a;

        C25313(C2532p c2532p) {
            this.f6182a = c2532p;
        }

        /* renamed from: a */
        public void m6531a(C2406c c2406c) {
            if (this.f6182a.f6195n != null) {
                this.f6182a.f6195n.mo5358h();
            }
        }
    }

    public C2532p(Context context) {
        super(context);
        this.f6188g = new C1942v(this, context);
        m6534t();
    }

    public C2532p(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f6188g = new C1942v(this, context);
        m6534t();
    }

    public C2532p(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f6188g = new C1942v(this, context);
        m6534t();
    }

    @TargetApi(21)
    public C2532p(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f6188g = new C1942v(this, context);
        m6534t();
    }

    /* renamed from: a */
    private void m6533a(String str) {
        C2625a.m6741b(getContext(), "parsing", C2626b.f6522M, new C2066b(AdErrorType.PARSER_FAILURE, str));
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(f6183b, str);
        }
    }

    /* renamed from: t */
    private void m6534t() {
        getEventBus().m5029a(this.f6185d, this.f6186e, this.f6187f);
    }

    /* renamed from: a */
    public void mo5638a() {
        Context context = getContext();
        Intent intent = new Intent(context, AudienceNetworkActivity.getAdActivity());
        if (this.f6190i == null) {
            m6533a("Must setClientToken first");
        } else if (this.f6191j == null && this.f6193l == null) {
            m6533a("Must setVideoURI or setVideoMPD first");
        } else {
            intent.putExtra("useNativeCtaButton", this.f6194m);
            intent.putExtra(AudienceNetworkActivity.VIEW_TYPE, C2093a.FULL_SCREEN_VIDEO);
            intent.putExtra(AudienceNetworkActivity.VIDEO_URL, this.f6191j.toString());
            intent.putExtra(AudienceNetworkActivity.CLIENT_TOKEN, this.f6192k == null ? "" : this.f6192k);
            intent.putExtra(AudienceNetworkActivity.VIDEO_MPD, this.f6193l);
            intent.putExtra(AudienceNetworkActivity.PREDEFINED_ORIENTATION_KEY, 13);
            intent.putExtra(AudienceNetworkActivity.VIDEO_SEEK_TIME, getCurrentPositionInMillis());
            intent.putExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f6184c);
            intent.putExtra(AudienceNetworkActivity.VIDEO_LOGGER, this.f6190i.mo5398g());
            intent.putExtra("video_time_polling_interval", getVideoProgressReportIntervalMs());
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        try {
            m6160a(false);
            setVisibility(8);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            try {
                intent.setClass(context, InterstitialAdActivity.class);
                context.startActivity(intent);
            } catch (Throwable e2) {
                C2059b.m5023a(C2058a.m5020a(e2, "Error occurred while loading fullscreen video activity."));
            }
        } catch (Throwable e22) {
            C2059b.m5023a(C2058a.m5020a(e22, "Error occurred while loading fullscreen video activity."));
        }
    }

    /* renamed from: b */
    public void mo5639b() {
        if (this.f6196o != null) {
            this.f6196o.onCtaBroadcast();
        }
    }

    @Nullable
    public C1807q getListener() {
        return this.f6195n;
    }

    public String getUniqueId() {
        return this.f6184c;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.f6188g.m4596a();
    }

    protected void onDetachedFromWindow() {
        this.f6188g.m4597b();
        super.onDetachedFromWindow();
    }

    public void setAdEventManager(C2085c c2085c) {
        this.f6189h = c2085c;
    }

    public void setClientToken(@Nullable String str) {
        if (this.f6190i != null) {
            this.f6190i.m6244a();
        }
        this.f6192k = str;
        this.f6190i = str != null ? new C2423b(getContext(), this.f6189h, this, str) : null;
    }

    public void setEnableBackgroundVideo(boolean z) {
        this.a.setBackgroundPlaybackEnabled(z);
    }

    public void setListener(@Nullable C1807q c1807q) {
        this.f6195n = c1807q;
    }

    public void setNativeAd(@Nullable NativeAd nativeAd) {
        this.f6196o = nativeAd;
    }

    public void setVideoCTA(@Nullable String str) {
        this.f6194m = str;
    }

    public void setVideoMPD(@Nullable String str) {
        if (str == null || this.f6190i != null) {
            this.f6193l = str;
            super.setVideoMPD(str);
            return;
        }
        m6533a("Must setClientToken first");
    }

    public void setVideoURI(@Nullable Uri uri) {
        if (uri == null || this.f6190i != null) {
            this.f6191j = uri;
            super.setVideoURI(uri);
            return;
        }
        m6533a("Must setClientToken first");
    }
}
