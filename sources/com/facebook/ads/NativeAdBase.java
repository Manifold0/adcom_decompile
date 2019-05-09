package com.facebook.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnTouchListener;
import com.facebook.ads.internal.adapters.C1924i;
import com.facebook.ads.internal.p017t.C1828h;
import com.facebook.ads.internal.p017t.C2104d;
import com.facebook.ads.internal.p017t.C2114e;
import com.facebook.ads.internal.p017t.C2114e.C1825c;
import com.facebook.ads.internal.p017t.C2115g;
import com.facebook.ads.internal.p017t.C2116i;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p043m.C2047d;
import com.facebook.ads.internal.protocol.C2065a;
import com.facebook.ads.internal.protocol.C2070e;
import com.facebook.ads.internal.view.C2506j;
import org.json.JSONObject;

public abstract class NativeAdBase implements Ad {
    /* renamed from: a */
    private final C2114e f3780a;

    /* renamed from: com.facebook.ads.NativeAdBase$1 */
    static class C18261 implements C1825c {
        C18261() {
        }

        /* renamed from: a */
        public boolean mo5362a(View view) {
            return (view instanceof MediaViewVideoRenderer) || (view instanceof AdChoicesView) || (view instanceof AdOptionsView) || (view instanceof C2506j);
        }
    }

    public static class Image {
        /* renamed from: a */
        private final C2115g f3783a;

        Image(C2115g c2115g) {
            this.f3783a = c2115g;
        }

        public Image(String str, int i, int i2) {
            this.f3783a = new C2115g(str, i, i2);
        }

        @Nullable
        public static Image fromJSONObject(JSONObject jSONObject) {
            C2115g a = C2115g.m5351a(jSONObject);
            return a == null ? null : new Image(a);
        }

        public int getHeight() {
            return this.f3783a.m5354c();
        }

        public int getWidth() {
            return this.f3783a.m5353b();
        }
    }

    public enum MediaCacheFlag {
        NONE(C2104d.NONE),
        ALL(C2104d.ALL);
        
        /* renamed from: a */
        private final C2104d f3785a;

        private MediaCacheFlag(C2104d c2104d) {
            this.f3785a = c2104d;
        }

        /* renamed from: a */
        C2104d m4099a() {
            return this.f3785a;
        }

        public long getCacheFlagValue() {
            return this.f3785a.m5257a();
        }
    }

    public enum NativeComponentTag {
        AD_ICON(C2580j.INTERNAL_AD_ICON),
        AD_TITLE(C2580j.INTERNAL_AD_TITLE),
        AD_COVER_IMAGE(C2580j.INTERNAL_AD_COVER_IMAGE),
        AD_SUBTITLE(C2580j.INTERNAL_AD_SUBTITLE),
        AD_BODY(C2580j.INTERNAL_AD_BODY),
        AD_CALL_TO_ACTION(C2580j.INTERNAL_AD_CALL_TO_ACTION),
        AD_SOCIAL_CONTEXT(C2580j.INTERNAL_AD_SOCIAL_CONTEXT),
        AD_CHOICES_ICON(C2580j.INTERNAL_AD_CHOICES_ICON),
        AD_OPTIONS_VIEW(C2580j.INTERNAL_AD_OPTIONS_VIEW),
        AD_MEDIA(C2580j.INTERNAL_AD_MEDIA);
        
        /* renamed from: a */
        private final C2580j f3787a;

        private NativeComponentTag(C2580j c2580j) {
            this.f3787a = c2580j;
        }

        public static void tagView(View view, NativeComponentTag nativeComponentTag) {
            if (view != null && nativeComponentTag != null) {
                C2580j.m6643a(view, nativeComponentTag.f3787a);
            }
        }
    }

    public static class Rating {
        /* renamed from: a */
        private final C2116i f3788a;

        public Rating(double d, double d2) {
            this.f3788a = new C2116i(d, d2);
        }

        Rating(C2116i c2116i) {
            this.f3788a = c2116i;
        }

        @Nullable
        public static Rating fromJSONObject(JSONObject jSONObject) {
            C2116i a = C2116i.m5355a(jSONObject);
            return a == null ? null : new Rating(a);
        }

        public double getScale() {
            return this.f3788a.m5357b();
        }

        public double getValue() {
            return this.f3788a.m5356a();
        }
    }

    public NativeAdBase(Context context, C1924i c1924i, C2047d c2047d) {
        this.f3780a = new C2114e(context, c1924i, c2047d, getViewTraversalPredicate());
    }

    public NativeAdBase(Context context, String str) {
        this.f3780a = new C2114e(context, str, getViewTraversalPredicate());
    }

    NativeAdBase(NativeAdBase nativeAdBase) {
        this.f3780a = new C2114e(nativeAdBase.f3780a);
    }

    NativeAdBase(C2114e c2114e) {
        this.f3780a = c2114e;
    }

    public static C1825c getViewTraversalPredicate() {
        return new C18261();
    }

    /* renamed from: a */
    void m4074a(MediaView mediaView) {
        if (mediaView != null) {
            this.f3780a.m5326c(true);
        }
    }

    /* renamed from: a */
    void m4075a(C2070e c2070e) {
        this.f3780a.m5315a(c2070e);
    }

    /* renamed from: a */
    void m4076a(boolean z) {
        this.f3780a.m5320a(z);
    }

    /* renamed from: b */
    void m4077b(MediaView mediaView) {
        if (mediaView != null) {
            this.f3780a.m5328d(true);
        }
    }

    public void destroy() {
        this.f3780a.m5327d();
    }

    public void downloadMedia() {
        this.f3780a.m5325c();
    }

    /* renamed from: f */
    C2114e m4078f() {
        return this.f3780a;
    }

    /* renamed from: g */
    C1924i m4079g() {
        return this.f3780a.m5308a();
    }

    public String getAdBodyText() {
        return this.f3780a.m5336l();
    }

    @Nullable
    public String getAdCallToAction() {
        return this.f3780a.m5309a("call_to_action");
    }

    public Image getAdChoicesIcon() {
        return this.f3780a.m5339o() == null ? null : new Image(this.f3780a.m5339o());
    }

    @Nullable
    public String getAdChoicesImageUrl() {
        return this.f3780a.m5339o() == null ? null : this.f3780a.m5339o().m5352a();
    }

    public String getAdChoicesLinkUrl() {
        return this.f3780a.m5340p();
    }

    public String getAdChoicesText() {
        return this.f3780a.m5341q();
    }

    public Image getAdCoverImage() {
        return this.f3780a.m5334j() == null ? null : new Image(this.f3780a.m5334j());
    }

    @Nullable
    public String getAdHeadline() {
        return this.f3780a.m5309a("headline");
    }

    public Image getAdIcon() {
        return this.f3780a.m5333i() == null ? null : new Image(this.f3780a.m5333i());
    }

    @Nullable
    public String getAdLinkDescription() {
        return this.f3780a.m5309a("link_description");
    }

    @Nullable
    public String getAdSocialContext() {
        return this.f3780a.m5309a("social_context");
    }

    @Deprecated
    public Rating getAdStarRating() {
        return this.f3780a.m5337m() == null ? null : new Rating(this.f3780a.m5337m());
    }

    @Nullable
    public String getAdTranslation() {
        return this.f3780a.m5309a("ad_translation");
    }

    @Nullable
    public String getAdUntrimmedBodyText() {
        return this.f3780a.m5309a("body");
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        return this.f3780a.m5335k() == null ? null : new NativeAdViewAttributes(this.f3780a.m5335k());
    }

    @Nullable
    public String getAdvertiserName() {
        return this.f3780a.m5309a("advertiser_name");
    }

    public String getId() {
        return this.f3780a.m5338n();
    }

    public String getPlacementId() {
        return this.f3780a.m5329e();
    }

    @Nullable
    public String getPromotedTranslation() {
        return this.f3780a.m5309a("promoted_translation");
    }

    @Nullable
    public String getSponsoredTranslation() {
        return this.f3780a.m5309a("sponsored_translation");
    }

    @Nullable
    /* renamed from: h */
    String m4080h() {
        return this.f3780a.m5346v();
    }

    public boolean hasCallToAction() {
        return this.f3780a.m5332h();
    }

    public boolean isAdInvalidated() {
        return this.f3780a.m5324b();
    }

    public boolean isAdLoaded() {
        return this.f3780a.m5330f();
    }

    public boolean isNativeConfigEnabled() {
        return this.f3780a.m5331g();
    }

    public void loadAd() {
        loadAd(MediaCacheFlag.ALL);
    }

    public void loadAd(MediaCacheFlag mediaCacheFlag) {
        this.f3780a.m5316a(mediaCacheFlag.m4099a(), null);
    }

    public void loadAdFromBid(String str) {
        loadAdFromBid(str, MediaCacheFlag.ALL);
    }

    public void loadAdFromBid(String str, MediaCacheFlag mediaCacheFlag) {
        this.f3780a.m5316a(mediaCacheFlag.m4099a(), str);
    }

    public void onCtaBroadcast() {
        this.f3780a.m5347w();
    }

    public void setAdListener(final NativeAdListener nativeAdListener) {
        if (nativeAdListener != null) {
            this.f3780a.m5317a(new C1828h(this) {
                /* renamed from: b */
                final /* synthetic */ NativeAdBase f3782b;

                /* renamed from: a */
                public void mo5363a() {
                    nativeAdListener.onMediaDownloaded(this.f3782b);
                }

                /* renamed from: a */
                public void mo5364a(C2065a c2065a) {
                    nativeAdListener.onError(this.f3782b, AdError.getAdErrorFromWrapper(c2065a));
                }

                /* renamed from: b */
                public void mo5365b() {
                    nativeAdListener.onAdLoaded(this.f3782b);
                }

                /* renamed from: c */
                public void mo5366c() {
                    nativeAdListener.onAdClicked(this.f3782b);
                }

                /* renamed from: d */
                public void mo5367d() {
                    nativeAdListener.onLoggingImpression(this.f3782b);
                }
            });
        }
    }

    public void setExtraHints(ExtraHints extraHints) {
        if (extraHints != null) {
            this.f3780a.m5322b(extraHints.getHints());
        }
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.f3780a.m5310a(onTouchListener);
    }

    public void unregisterView() {
        this.f3780a.m5350z();
    }
}
