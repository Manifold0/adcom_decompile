package com.applovin.impl.sdk;

import android.content.Context;
import android.net.Uri;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class NativeAdImpl implements au, cj {
    public static final String QUERY_PARAM_IS_FIRST_PLAY = "fp";
    public static final String QUERY_PARAM_VIDEO_PERCENT_VIEWED = "pv";
    /* renamed from: a */
    private final AppLovinSdkImpl f1985a;
    /* renamed from: b */
    private final C1287n f1986b;
    /* renamed from: c */
    private final String f1987c;
    /* renamed from: d */
    private final String f1988d;
    /* renamed from: e */
    private final String f1989e;
    /* renamed from: f */
    private final String f1990f;
    /* renamed from: g */
    private final String f1991g;
    /* renamed from: h */
    private final String f1992h;
    /* renamed from: i */
    private final String f1993i;
    /* renamed from: j */
    private final String f1994j;
    /* renamed from: k */
    private final String f1995k;
    /* renamed from: l */
    private final String f1996l;
    /* renamed from: m */
    private final String f1997m;
    /* renamed from: n */
    private final String f1998n;
    /* renamed from: o */
    private final String f1999o;
    /* renamed from: p */
    private final String f2000p;
    /* renamed from: q */
    private final long f2001q;
    /* renamed from: r */
    private final List<String> f2002r;
    /* renamed from: s */
    private String f2003s;
    /* renamed from: t */
    private String f2004t;
    /* renamed from: u */
    private float f2005u;
    /* renamed from: v */
    private String f2006v;
    /* renamed from: w */
    private AtomicBoolean f2007w;

    private NativeAdImpl(C1287n c1287n, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, float f, String str11, String str12, String str13, String str14, String str15, String str16, String str17, long j, List<String> list, AppLovinSdkImpl appLovinSdkImpl) {
        this.f2007w = new AtomicBoolean();
        this.f1986b = c1287n;
        this.f1987c = str;
        this.f1988d = str2;
        this.f1989e = str3;
        this.f1990f = str4;
        this.f1991g = str5;
        this.f1992h = str6;
        this.f1993i = str7;
        this.f1994j = str8;
        this.f2003s = str9;
        this.f2004t = str10;
        this.f2005u = f;
        this.f2006v = str11;
        this.f1996l = str12;
        this.f1997m = str13;
        this.f1998n = str14;
        this.f1999o = str15;
        this.f2000p = str16;
        this.f1995k = str17;
        this.f2001q = j;
        this.f2002r = list;
        this.f1985a = appLovinSdkImpl;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        NativeAdImpl nativeAdImpl = (NativeAdImpl) obj;
        if (this.f1986b == null ? nativeAdImpl.f1986b != null : !this.f1986b.equals(nativeAdImpl.f1986b)) {
            return false;
        }
        if (this.f1994j == null ? nativeAdImpl.f1994j != null : !this.f1994j.equals(nativeAdImpl.f1994j)) {
            return false;
        }
        if (this.f2000p == null ? nativeAdImpl.f2000p != null : !this.f2000p.equals(nativeAdImpl.f2000p)) {
            return false;
        }
        if (this.f1997m == null ? nativeAdImpl.f1997m != null : !this.f1997m.equals(nativeAdImpl.f1997m)) {
            return false;
        }
        if (this.f1995k == null ? nativeAdImpl.f1995k != null : !this.f1995k.equals(nativeAdImpl.f1995k)) {
            return false;
        }
        if (this.f1993i == null ? nativeAdImpl.f1993i != null : !this.f1993i.equals(nativeAdImpl.f1993i)) {
            return false;
        }
        if (this.f1996l == null ? nativeAdImpl.f1996l != null : !this.f1996l.equals(nativeAdImpl.f1996l)) {
            return false;
        }
        if (this.f1988d == null ? nativeAdImpl.f1988d != null : !this.f1988d.equals(nativeAdImpl.f1988d)) {
            return false;
        }
        if (this.f1989e == null ? nativeAdImpl.f1989e != null : !this.f1989e.equals(nativeAdImpl.f1989e)) {
            return false;
        }
        if (this.f1990f == null ? nativeAdImpl.f1990f != null : !this.f1990f.equals(nativeAdImpl.f1990f)) {
            return false;
        }
        if (this.f1991g == null ? nativeAdImpl.f1991g != null : !this.f1991g.equals(nativeAdImpl.f1991g)) {
            return false;
        }
        if (this.f1992h == null ? nativeAdImpl.f1992h != null : !this.f1992h.equals(nativeAdImpl.f1992h)) {
            return false;
        }
        if (this.f1999o == null ? nativeAdImpl.f1999o != null : !this.f1999o.equals(nativeAdImpl.f1999o)) {
            return false;
        }
        if (this.f1998n == null ? nativeAdImpl.f1998n != null : !this.f1998n.equals(nativeAdImpl.f1998n)) {
            return false;
        }
        if (this.f2002r != null) {
            if (this.f2002r.equals(nativeAdImpl.f2002r)) {
                return true;
            }
        } else if (nativeAdImpl.f2002r == null) {
            return true;
        }
        return false;
    }

    public long getAdId() {
        return this.f2001q;
    }

    public C1287n getAdZone() {
        return this.f1986b;
    }

    public String getCaptionText() {
        return this.f1994j;
    }

    public String getClCode() {
        return this.f2000p;
    }

    public String getClickUrl() {
        return this.f1997m;
    }

    public String getCtaText() {
        return this.f1995k;
    }

    public String getDescriptionText() {
        return this.f1993i;
    }

    public String getIconUrl() {
        return this.f2003s;
    }

    public String getImageUrl() {
        return this.f2004t;
    }

    public String getImpressionTrackingUrl() {
        return this.f1996l;
    }

    public List<String> getResourcePrefixes() {
        return this.f2002r;
    }

    public String getSourceIconUrl() {
        return this.f1988d;
    }

    public String getSourceImageUrl() {
        return this.f1989e;
    }

    public String getSourceStarRatingImageUrl() {
        return this.f1990f;
    }

    public String getSourceVideoUrl() {
        return this.f1991g;
    }

    public float getStarRating() {
        return this.f2005u;
    }

    public String getTitle() {
        return this.f1992h;
    }

    public String getVideoEndTrackingUrl(int i, boolean z) {
        if (this.f1999o == null) {
            return Uri.EMPTY.toString();
        }
        if (i < 0 || i > 100) {
            this.f1985a.getLogger().userError("AppLovinNativeAd", "Invalid percent viewed supplied.", new IllegalArgumentException("Percent viewed must be an integer between 0 and 100."));
        }
        return Uri.parse(this.f1999o).buildUpon().appendQueryParameter(QUERY_PARAM_VIDEO_PERCENT_VIEWED, Integer.toString(i)).appendQueryParameter(QUERY_PARAM_IS_FIRST_PLAY, Boolean.toString(z)).build().toString();
    }

    public String getVideoStartTrackingUrl() {
        return this.f1998n;
    }

    public String getVideoUrl() {
        return this.f2006v;
    }

    public String getZoneId() {
        return this.f1987c;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((this.f1986b != null ? this.f1986b.hashCode() : 0) + (((this.f2000p != null ? this.f2000p.hashCode() : 0) + (((this.f1999o != null ? this.f1999o.hashCode() : 0) + (((this.f1998n != null ? this.f1998n.hashCode() : 0) + (((this.f1997m != null ? this.f1997m.hashCode() : 0) + (((this.f1996l != null ? this.f1996l.hashCode() : 0) + (((this.f1995k != null ? this.f1995k.hashCode() : 0) + (((this.f1994j != null ? this.f1994j.hashCode() : 0) + (((this.f1993i != null ? this.f1993i.hashCode() : 0) + (((this.f1992h != null ? this.f1992h.hashCode() : 0) + (((this.f1991g != null ? this.f1991g.hashCode() : 0) + (((this.f1990f != null ? this.f1990f.hashCode() : 0) + (((this.f1989e != null ? this.f1989e.hashCode() : 0) + ((this.f1988d != null ? this.f1988d.hashCode() : 0) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (this.f2002r != null) {
            i = this.f2002r.hashCode();
        }
        return hashCode + i;
    }

    public boolean isImagePrecached() {
        boolean z = (this.f2003s == null || this.f2003s.equals(this.f1988d)) ? false : true;
        boolean z2 = (this.f2004t == null || this.f2004t.equals(this.f1989e)) ? false : true;
        return z && z2;
    }

    public boolean isVideoPrecached() {
        return (this.f2006v == null || this.f2006v.equals(this.f1991g)) ? false : true;
    }

    public void launchClickTarget(Context context) {
        this.f1985a.getPersistentPostbackManager().m2609a(this.f1997m);
        AppLovinSdkUtils.openUri(context, Uri.parse(this.f1997m), this.f1985a);
    }

    public void setIconUrl(String str) {
        this.f2003s = str;
    }

    public void setImageUrl(String str) {
        this.f2004t = str;
    }

    public void setStarRating(float f) {
        this.f2005u = f;
    }

    public void setVideoUrl(String str) {
        this.f2006v = str;
    }

    public String toString() {
        return "AppLovinNativeAd{clCode='" + this.f2000p + '\'' + ", adZone='" + this.f1986b + '\'' + ", sourceIconUrl='" + this.f1988d + '\'' + ", sourceImageUrl='" + this.f1989e + '\'' + ", sourceStarRatingImageUrl='" + this.f1990f + '\'' + ", sourceVideoUrl='" + this.f1991g + '\'' + ", title='" + this.f1992h + '\'' + ", descriptionText='" + this.f1993i + '\'' + ", captionText='" + this.f1994j + '\'' + ", ctaText='" + this.f1995k + '\'' + ", iconUrl='" + this.f2003s + '\'' + ", imageUrl='" + this.f2004t + '\'' + ", starRating='" + this.f2005u + '\'' + ", videoUrl='" + this.f2006v + '\'' + ", impressionTrackingUrl='" + this.f1996l + '\'' + ", clickUrl='" + this.f1997m + '\'' + ", videoStartTrackingUrl='" + this.f1998n + '\'' + ", videoEndTrackingUrl='" + this.f1999o + '\'' + ", resourcePrefixes=" + this.f2002r + '}';
    }

    public void trackImpression() {
        trackImpression(null);
    }

    public void trackImpression(AppLovinPostbackListener appLovinPostbackListener) {
        if (!this.f2007w.getAndSet(true)) {
            this.f1985a.getLogger().mo4172d("AppLovinNativeAd", "Tracking impression...");
            this.f1985a.getPostbackService().dispatchPostbackAsync(this.f1996l, appLovinPostbackListener);
        } else if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(this.f1996l, AppLovinErrorCodes.NATIVE_AD_IMPRESSION_ALREADY_TRACKED);
        }
    }
}
