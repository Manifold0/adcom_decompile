// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdSize;
import android.util.Log;
import org.json.JSONObject;
import com.applovin.sdk.AppLovinAd;

public final class aq extends q
{
    private AppLovinAd e;
    private final n f;
    
    public aq(final n f, final AppLovinSdkImpl appLovinSdkImpl) {
        super(new JSONObject(), new JSONObject(), appLovinSdkImpl);
        this.f = f;
    }
    
    private AppLovinAd c() {
        return (AppLovinAd)this.c.c().c(this.f);
    }
    
    private String d() {
        final n t = this.t();
        if (t != null && !t.m()) {
            return t.a();
        }
        return null;
    }
    
    AppLovinAd a() {
        return this.e;
    }
    
    void a(final AppLovinAd e) {
        this.e = e;
    }
    
    AppLovinAd b() {
        if (this.e != null) {
            return this.e;
        }
        return this.c();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final AppLovinAd b = this.b();
        if (b != null) {
            return b.equals(o);
        }
        return super.equals(o);
    }
    
    @Override
    public long getAdIdNumber() {
        long adIdNumber = 0L;
        try {
            final AppLovinAd b = this.b();
            if (b != null) {
                adIdNumber = b.getAdIdNumber();
            }
            return adIdNumber;
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to retrieve ad id number", t);
            return 0L;
        }
    }
    
    @Override
    public AppLovinAdSize getSize() {
        final AppLovinAdSize interstitial = AppLovinAdSize.INTERSTITIAL;
        try {
            return this.t().b();
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to retrieve ad size", t);
            return interstitial;
        }
    }
    
    @Override
    public AppLovinAdType getType() {
        final AppLovinAdType regular = AppLovinAdType.REGULAR;
        try {
            return this.t().c();
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to retrieve ad type", t);
            return regular;
        }
    }
    
    @Override
    public String getZoneId() {
        String a = null;
        try {
            if (!this.f.m()) {
                a = this.f.a();
            }
            return a;
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to return zone id", t);
            return null;
        }
    }
    
    @Override
    public int hashCode() {
        final AppLovinAd b = this.b();
        if (b != null) {
            return b.hashCode();
        }
        return super.hashCode();
    }
    
    @Override
    public boolean isVideoAd() {
        boolean videoAd = false;
        try {
            final AppLovinAd b = this.b();
            if (b != null) {
                videoAd = b.isVideoAd();
            }
            return videoAd;
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to return whether ad is video ad", t);
            return false;
        }
    }
    
    @Override
    public o m() {
        final o b = o.b;
        try {
            return this.t().d();
        }
        catch (Throwable t) {
            Log.e("AppLovinAd", "Failed to return ad mediation type", t);
            return b;
        }
    }
    
    @Override
    public n t() {
        final q q = (q)this.b();
        if (q != null) {
            return q.t();
        }
        return this.f;
    }
    
    @Override
    public String toString() {
        return "[AppLovinAd #" + this.getAdIdNumber() + " adType=" + this.getType() + ", adSize=" + this.getSize() + ", zoneId=" + this.d() + "]";
    }
}
