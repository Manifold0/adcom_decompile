// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import android.content.Context;
import android.net.Uri;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.List;

public class NativeAdImpl implements au, cj
{
    public static final String QUERY_PARAM_IS_FIRST_PLAY = "fp";
    public static final String QUERY_PARAM_VIDEO_PERCENT_VIEWED = "pv";
    private final AppLovinSdkImpl a;
    private final n b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private final String n;
    private final String o;
    private final String p;
    private final long q;
    private final List<String> r;
    private String s;
    private String t;
    private float u;
    private String v;
    private AtomicBoolean w;
    
    private NativeAdImpl(final n b, final String c, final String d, final String e, final String f, final String g, final String h, final String i, final String j, final String s, final String t, final float u, final String v, final String l, final String m, final String n, final String o, final String p22, final String k, final long q, final List<String> r, final AppLovinSdkImpl a) {
        this.w = new AtomicBoolean();
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        this.s = s;
        this.t = t;
        this.u = u;
        this.v = v;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p22;
        this.k = k;
        this.q = q;
        this.r = r;
        this.a = a;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final NativeAdImpl nativeAdImpl = (NativeAdImpl)o;
            Label_0059: {
                if (this.b != null) {
                    if (this.b.equals(nativeAdImpl.b)) {
                        break Label_0059;
                    }
                }
                else if (nativeAdImpl.b == null) {
                    break Label_0059;
                }
                return false;
            }
            Label_0089: {
                if (this.j != null) {
                    if (this.j.equals(nativeAdImpl.j)) {
                        break Label_0089;
                    }
                }
                else if (nativeAdImpl.j == null) {
                    break Label_0089;
                }
                return false;
            }
            Label_0119: {
                if (this.p != null) {
                    if (this.p.equals(nativeAdImpl.p)) {
                        break Label_0119;
                    }
                }
                else if (nativeAdImpl.p == null) {
                    break Label_0119;
                }
                return false;
            }
            Label_0149: {
                if (this.m != null) {
                    if (this.m.equals(nativeAdImpl.m)) {
                        break Label_0149;
                    }
                }
                else if (nativeAdImpl.m == null) {
                    break Label_0149;
                }
                return false;
            }
            Label_0179: {
                if (this.k != null) {
                    if (this.k.equals(nativeAdImpl.k)) {
                        break Label_0179;
                    }
                }
                else if (nativeAdImpl.k == null) {
                    break Label_0179;
                }
                return false;
            }
            Label_0209: {
                if (this.i != null) {
                    if (this.i.equals(nativeAdImpl.i)) {
                        break Label_0209;
                    }
                }
                else if (nativeAdImpl.i == null) {
                    break Label_0209;
                }
                return false;
            }
            Label_0239: {
                if (this.l != null) {
                    if (this.l.equals(nativeAdImpl.l)) {
                        break Label_0239;
                    }
                }
                else if (nativeAdImpl.l == null) {
                    break Label_0239;
                }
                return false;
            }
            Label_0269: {
                if (this.d != null) {
                    if (this.d.equals(nativeAdImpl.d)) {
                        break Label_0269;
                    }
                }
                else if (nativeAdImpl.d == null) {
                    break Label_0269;
                }
                return false;
            }
            Label_0299: {
                if (this.e != null) {
                    if (this.e.equals(nativeAdImpl.e)) {
                        break Label_0299;
                    }
                }
                else if (nativeAdImpl.e == null) {
                    break Label_0299;
                }
                return false;
            }
            Label_0329: {
                if (this.f != null) {
                    if (this.f.equals(nativeAdImpl.f)) {
                        break Label_0329;
                    }
                }
                else if (nativeAdImpl.f == null) {
                    break Label_0329;
                }
                return false;
            }
            Label_0359: {
                if (this.g != null) {
                    if (this.g.equals(nativeAdImpl.g)) {
                        break Label_0359;
                    }
                }
                else if (nativeAdImpl.g == null) {
                    break Label_0359;
                }
                return false;
            }
            Label_0389: {
                if (this.h != null) {
                    if (this.h.equals(nativeAdImpl.h)) {
                        break Label_0389;
                    }
                }
                else if (nativeAdImpl.h == null) {
                    break Label_0389;
                }
                return false;
            }
            Label_0419: {
                if (this.o != null) {
                    if (this.o.equals(nativeAdImpl.o)) {
                        break Label_0419;
                    }
                }
                else if (nativeAdImpl.o == null) {
                    break Label_0419;
                }
                return false;
            }
            Label_0449: {
                if (this.n != null) {
                    if (this.n.equals(nativeAdImpl.n)) {
                        break Label_0449;
                    }
                }
                else if (nativeAdImpl.n == null) {
                    break Label_0449;
                }
                return false;
            }
            if (this.r != null) {
                if (this.r.equals(nativeAdImpl.r)) {
                    return true;
                }
            }
            else if (nativeAdImpl.r == null) {
                return true;
            }
            return false;
        }
        return true;
    }
    
    @Override
    public long getAdId() {
        return this.q;
    }
    
    public n getAdZone() {
        return this.b;
    }
    
    @Override
    public String getCaptionText() {
        return this.j;
    }
    
    public String getClCode() {
        return this.p;
    }
    
    @Override
    public String getClickUrl() {
        return this.m;
    }
    
    @Override
    public String getCtaText() {
        return this.k;
    }
    
    @Override
    public String getDescriptionText() {
        return this.i;
    }
    
    @Override
    public String getIconUrl() {
        return this.s;
    }
    
    @Override
    public String getImageUrl() {
        return this.t;
    }
    
    @Override
    public String getImpressionTrackingUrl() {
        return this.l;
    }
    
    public List<String> getResourcePrefixes() {
        return this.r;
    }
    
    public String getSourceIconUrl() {
        return this.d;
    }
    
    public String getSourceImageUrl() {
        return this.e;
    }
    
    public String getSourceStarRatingImageUrl() {
        return this.f;
    }
    
    public String getSourceVideoUrl() {
        return this.g;
    }
    
    @Override
    public float getStarRating() {
        return this.u;
    }
    
    @Override
    public String getTitle() {
        return this.h;
    }
    
    @Override
    public String getVideoEndTrackingUrl(final int n, final boolean b) {
        if (this.o == null) {
            return Uri.EMPTY.toString();
        }
        if (n < 0 || n > 100) {
            this.a.getLogger().userError("AppLovinNativeAd", "Invalid percent viewed supplied.", new IllegalArgumentException("Percent viewed must be an integer between 0 and 100."));
        }
        return Uri.parse(this.o).buildUpon().appendQueryParameter("pv", Integer.toString(n)).appendQueryParameter("fp", Boolean.toString(b)).build().toString();
    }
    
    @Override
    public String getVideoStartTrackingUrl() {
        return this.n;
    }
    
    @Override
    public String getVideoUrl() {
        return this.v;
    }
    
    @Override
    public String getZoneId() {
        return this.c;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.d != null) {
            hashCode2 = this.d.hashCode();
        }
        else {
            hashCode2 = 0;
        }
        int hashCode3;
        if (this.e != null) {
            hashCode3 = this.e.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.f != null) {
            hashCode4 = this.f.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        int hashCode5;
        if (this.g != null) {
            hashCode5 = this.g.hashCode();
        }
        else {
            hashCode5 = 0;
        }
        int hashCode6;
        if (this.h != null) {
            hashCode6 = this.h.hashCode();
        }
        else {
            hashCode6 = 0;
        }
        int hashCode7;
        if (this.i != null) {
            hashCode7 = this.i.hashCode();
        }
        else {
            hashCode7 = 0;
        }
        int hashCode8;
        if (this.j != null) {
            hashCode8 = this.j.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        int hashCode9;
        if (this.k != null) {
            hashCode9 = this.k.hashCode();
        }
        else {
            hashCode9 = 0;
        }
        int hashCode10;
        if (this.l != null) {
            hashCode10 = this.l.hashCode();
        }
        else {
            hashCode10 = 0;
        }
        int hashCode11;
        if (this.m != null) {
            hashCode11 = this.m.hashCode();
        }
        else {
            hashCode11 = 0;
        }
        int hashCode12;
        if (this.n != null) {
            hashCode12 = this.n.hashCode();
        }
        else {
            hashCode12 = 0;
        }
        int hashCode13;
        if (this.o != null) {
            hashCode13 = this.o.hashCode();
        }
        else {
            hashCode13 = 0;
        }
        int hashCode14;
        if (this.p != null) {
            hashCode14 = this.p.hashCode();
        }
        else {
            hashCode14 = 0;
        }
        int hashCode15;
        if (this.b != null) {
            hashCode15 = this.b.hashCode();
        }
        else {
            hashCode15 = 0;
        }
        if (this.r != null) {
            hashCode = this.r.hashCode();
        }
        return (hashCode15 + (hashCode14 + (hashCode13 + (hashCode12 + (hashCode11 + (hashCode10 + (hashCode9 + (hashCode8 + (hashCode7 + (hashCode6 + (hashCode5 + (hashCode4 + (hashCode3 + hashCode2 * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31) * 31 + hashCode;
    }
    
    @Override
    public boolean isImagePrecached() {
        boolean b;
        if (this.s != null && !this.s.equals(this.d)) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.t != null && !this.t.equals(this.e)) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        return b && b2;
    }
    
    @Override
    public boolean isVideoPrecached() {
        return this.v != null && !this.v.equals(this.g);
    }
    
    @Override
    public void launchClickTarget(final Context context) {
        this.a.getPersistentPostbackManager().a(this.m);
        AppLovinSdkUtils.openUri(context, Uri.parse(this.m), this.a);
    }
    
    public void setIconUrl(final String s) {
        this.s = s;
    }
    
    public void setImageUrl(final String t) {
        this.t = t;
    }
    
    public void setStarRating(final float u) {
        this.u = u;
    }
    
    public void setVideoUrl(final String v) {
        this.v = v;
    }
    
    @Override
    public String toString() {
        return "AppLovinNativeAd{clCode='" + this.p + '\'' + ", adZone='" + this.b + '\'' + ", sourceIconUrl='" + this.d + '\'' + ", sourceImageUrl='" + this.e + '\'' + ", sourceStarRatingImageUrl='" + this.f + '\'' + ", sourceVideoUrl='" + this.g + '\'' + ", title='" + this.h + '\'' + ", descriptionText='" + this.i + '\'' + ", captionText='" + this.j + '\'' + ", ctaText='" + this.k + '\'' + ", iconUrl='" + this.s + '\'' + ", imageUrl='" + this.t + '\'' + ", starRating='" + this.u + '\'' + ", videoUrl='" + this.v + '\'' + ", impressionTrackingUrl='" + this.l + '\'' + ", clickUrl='" + this.m + '\'' + ", videoStartTrackingUrl='" + this.n + '\'' + ", videoEndTrackingUrl='" + this.o + '\'' + ", resourcePrefixes=" + this.r + '}';
    }
    
    @Override
    public void trackImpression() {
        this.trackImpression(null);
    }
    
    @Override
    public void trackImpression(final AppLovinPostbackListener appLovinPostbackListener) {
        if (!this.w.getAndSet(true)) {
            this.a.getLogger().d("AppLovinNativeAd", "Tracking impression...");
            this.a.getPostbackService().dispatchPostbackAsync(this.l, appLovinPostbackListener);
        }
        else if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(this.l, -702);
        }
    }
}
