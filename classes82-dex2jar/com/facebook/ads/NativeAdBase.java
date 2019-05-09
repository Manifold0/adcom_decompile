// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import org.json.JSONObject;
import com.facebook.ads.internal.t.g;
import android.view.View$OnTouchListener;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.t.h;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.j;
import android.view.View;
import com.facebook.ads.internal.m.d;
import com.facebook.ads.internal.adapters.i;
import android.content.Context;
import com.facebook.ads.internal.t.e;

public abstract class NativeAdBase implements Ad
{
    private final e a;
    
    public NativeAdBase(final Context context, final i i, final d d) {
        this.a = new e(context, i, d, getViewTraversalPredicate());
    }
    
    public NativeAdBase(final Context context, final String s) {
        this.a = new e(context, s, getViewTraversalPredicate());
    }
    
    NativeAdBase(final NativeAdBase nativeAdBase) {
        this.a = new e(nativeAdBase.a);
    }
    
    NativeAdBase(final e a) {
        this.a = a;
    }
    
    public static e.c getViewTraversalPredicate() {
        return new e.c() {
            @Override
            public boolean a(final View view) {
                return view instanceof MediaViewVideoRenderer || view instanceof AdChoicesView || view instanceof AdOptionsView || view instanceof j;
            }
        };
    }
    
    void a(final MediaView mediaView) {
        if (mediaView != null) {
            this.a.c(true);
        }
    }
    
    void a(final com.facebook.ads.internal.protocol.e e) {
        this.a.a(e);
    }
    
    void a(final boolean b) {
        this.a.a(b);
    }
    
    void b(final MediaView mediaView) {
        if (mediaView != null) {
            this.a.d(true);
        }
    }
    
    @Override
    public void destroy() {
        this.a.d();
    }
    
    public void downloadMedia() {
        this.a.c();
    }
    
    e f() {
        return this.a;
    }
    
    i g() {
        return this.a.a();
    }
    
    public String getAdBodyText() {
        return this.a.l();
    }
    
    @Nullable
    public String getAdCallToAction() {
        return this.a.a("call_to_action");
    }
    
    public Image getAdChoicesIcon() {
        if (this.a.o() == null) {
            return null;
        }
        return new Image(this.a.o());
    }
    
    @Nullable
    public String getAdChoicesImageUrl() {
        if (this.a.o() == null) {
            return null;
        }
        return this.a.o().a();
    }
    
    public String getAdChoicesLinkUrl() {
        return this.a.p();
    }
    
    public String getAdChoicesText() {
        return this.a.q();
    }
    
    public Image getAdCoverImage() {
        if (this.a.j() == null) {
            return null;
        }
        return new Image(this.a.j());
    }
    
    @Nullable
    public String getAdHeadline() {
        return this.a.a("headline");
    }
    
    public Image getAdIcon() {
        if (this.a.i() == null) {
            return null;
        }
        return new Image(this.a.i());
    }
    
    @Nullable
    public String getAdLinkDescription() {
        return this.a.a("link_description");
    }
    
    @Nullable
    public String getAdSocialContext() {
        return this.a.a("social_context");
    }
    
    @Deprecated
    public Rating getAdStarRating() {
        if (this.a.m() == null) {
            return null;
        }
        return new Rating(this.a.m());
    }
    
    @Nullable
    public String getAdTranslation() {
        return this.a.a("ad_translation");
    }
    
    @Nullable
    public String getAdUntrimmedBodyText() {
        return this.a.a("body");
    }
    
    public NativeAdViewAttributes getAdViewAttributes() {
        if (this.a.k() == null) {
            return null;
        }
        return new NativeAdViewAttributes(this.a.k());
    }
    
    @Nullable
    public String getAdvertiserName() {
        return this.a.a("advertiser_name");
    }
    
    public String getId() {
        return this.a.n();
    }
    
    @Override
    public String getPlacementId() {
        return this.a.e();
    }
    
    @Nullable
    public String getPromotedTranslation() {
        return this.a.a("promoted_translation");
    }
    
    @Nullable
    public String getSponsoredTranslation() {
        return this.a.a("sponsored_translation");
    }
    
    @Nullable
    String h() {
        return this.a.v();
    }
    
    public boolean hasCallToAction() {
        return this.a.h();
    }
    
    @Override
    public boolean isAdInvalidated() {
        return this.a.b();
    }
    
    public boolean isAdLoaded() {
        return this.a.f();
    }
    
    public boolean isNativeConfigEnabled() {
        return this.a.g();
    }
    
    @Override
    public void loadAd() {
        this.loadAd(MediaCacheFlag.ALL);
    }
    
    public void loadAd(final MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), null);
    }
    
    @Override
    public void loadAdFromBid(final String s) {
        this.loadAdFromBid(s, MediaCacheFlag.ALL);
    }
    
    public void loadAdFromBid(final String s, final MediaCacheFlag mediaCacheFlag) {
        this.a.a(mediaCacheFlag.a(), s);
    }
    
    public void onCtaBroadcast() {
        this.a.w();
    }
    
    public void setAdListener(final NativeAdListener nativeAdListener) {
        if (nativeAdListener == null) {
            return;
        }
        this.a.a(new h() {
            @Override
            public void a() {
                nativeAdListener.onMediaDownloaded(NativeAdBase.this);
            }
            
            @Override
            public void a(final a a) {
                nativeAdListener.onError(NativeAdBase.this, AdError.getAdErrorFromWrapper(a));
            }
            
            @Override
            public void b() {
                nativeAdListener.onAdLoaded(NativeAdBase.this);
            }
            
            @Override
            public void c() {
                nativeAdListener.onAdClicked(NativeAdBase.this);
            }
            
            @Override
            public void d() {
                nativeAdListener.onLoggingImpression(NativeAdBase.this);
            }
        });
    }
    
    public void setExtraHints(final ExtraHints extraHints) {
        if (extraHints == null) {
            return;
        }
        this.a.b(extraHints.getHints());
    }
    
    public void setOnTouchListener(final View$OnTouchListener view$OnTouchListener) {
        this.a.a(view$OnTouchListener);
    }
    
    public void unregisterView() {
        this.a.z();
    }
    
    public static class Image
    {
        private final g a;
        
        Image(final g a) {
            this.a = a;
        }
        
        public Image(final String s, final int n, final int n2) {
            this.a = new g(s, n, n2);
        }
        
        @Nullable
        public static Image fromJSONObject(final JSONObject jsonObject) {
            final g a = g.a(jsonObject);
            if (a == null) {
                return null;
            }
            return new Image(a);
        }
        
        public int getHeight() {
            return this.a.c();
        }
        
        public int getWidth() {
            return this.a.b();
        }
    }
    
    public enum MediaCacheFlag
    {
        ALL(com.facebook.ads.internal.t.d.b), 
        NONE(com.facebook.ads.internal.t.d.a);
        
        private final com.facebook.ads.internal.t.d a;
        
        private MediaCacheFlag(final com.facebook.ads.internal.t.d a) {
            this.a = a;
        }
        
        com.facebook.ads.internal.t.d a() {
            return this.a;
        }
        
        public long getCacheFlagValue() {
            return this.a.a();
        }
    }
    
    public enum NativeComponentTag
    {
        AD_BODY(com.facebook.ads.internal.w.b.j.j), 
        AD_CALL_TO_ACTION(com.facebook.ads.internal.w.b.j.k), 
        AD_CHOICES_ICON(com.facebook.ads.internal.w.b.j.m), 
        AD_COVER_IMAGE(com.facebook.ads.internal.w.b.j.h), 
        AD_ICON(com.facebook.ads.internal.w.b.j.f), 
        AD_MEDIA(com.facebook.ads.internal.w.b.j.n), 
        AD_OPTIONS_VIEW(com.facebook.ads.internal.w.b.j.o), 
        AD_SOCIAL_CONTEXT(com.facebook.ads.internal.w.b.j.l), 
        AD_SUBTITLE(com.facebook.ads.internal.w.b.j.i), 
        AD_TITLE(com.facebook.ads.internal.w.b.j.g);
        
        private final com.facebook.ads.internal.w.b.j a;
        
        private NativeComponentTag(final com.facebook.ads.internal.w.b.j a) {
            this.a = a;
        }
        
        public static void tagView(final View view, final NativeComponentTag nativeComponentTag) {
            if (view != null && nativeComponentTag != null) {
                com.facebook.ads.internal.w.b.j.a(view, nativeComponentTag.a);
            }
        }
    }
    
    public static class Rating
    {
        private final com.facebook.ads.internal.t.i a;
        
        public Rating(final double n, final double n2) {
            this.a = new com.facebook.ads.internal.t.i(n, n2);
        }
        
        Rating(final com.facebook.ads.internal.t.i a) {
            this.a = a;
        }
        
        @Nullable
        public static Rating fromJSONObject(final JSONObject jsonObject) {
            final com.facebook.ads.internal.t.i a = com.facebook.ads.internal.t.i.a(jsonObject);
            if (a == null) {
                return null;
            }
            return new Rating(a);
        }
        
        public double getScale() {
            return this.a.b();
        }
        
        public double getValue() {
            return this.a.a();
        }
    }
}
