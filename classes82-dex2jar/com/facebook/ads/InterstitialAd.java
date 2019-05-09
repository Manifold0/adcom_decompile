// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.util.EnumSet;
import android.content.Context;
import com.facebook.ads.internal.c.f;
import com.facebook.ads.internal.c.g;
import android.support.annotation.UiThread;

@UiThread
public class InterstitialAd implements Ad
{
    private final g a;
    private final f b;
    
    public InterstitialAd(final Context context, final String s) {
        this.a = new g(context.getApplicationContext(), this, s);
        this.b = new f(this.a);
    }
    
    private void a(final EnumSet<CacheFlag> set, final String s) {
        this.b.a(this, set, s);
    }
    
    @Override
    public void destroy() {
        this.b.d();
    }
    
    @Override
    protected void finalize() {
        this.b.e();
    }
    
    @Override
    public String getPlacementId() {
        return this.a.b;
    }
    
    @Override
    public boolean isAdInvalidated() {
        return this.b.g();
    }
    
    public boolean isAdLoaded() {
        return this.b.f();
    }
    
    @Override
    public void loadAd() {
        this.loadAd(CacheFlag.ALL);
    }
    
    public void loadAd(final EnumSet<CacheFlag> set) {
        this.a(set, null);
    }
    
    @Override
    public void loadAdFromBid(final String s) {
        this.a(CacheFlag.ALL, s);
    }
    
    public void loadAdFromBid(final EnumSet<CacheFlag> set, final String s) {
        this.a(set, s);
    }
    
    public void setAdListener(final InterstitialAdListener c) {
        this.a.c = c;
    }
    
    public void setExtraHints(final ExtraHints extraHints) {
        this.a.d = extraHints.getHints();
    }
    
    public boolean show() {
        return this.b.a(this);
    }
}
