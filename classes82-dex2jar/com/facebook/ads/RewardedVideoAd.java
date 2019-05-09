// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.content.Context;
import com.facebook.ads.internal.c.i;
import com.facebook.ads.internal.c.j;
import android.support.annotation.UiThread;

@UiThread
public class RewardedVideoAd implements Ad
{
    public static final int UNSET_VIDEO_DURATION = -1;
    private final j a;
    private final i b;
    
    public RewardedVideoAd(final Context context, final String s) {
        this.a = new j(context.getApplicationContext(), s, this);
        this.b = new i(this.a);
    }
    
    private void a(final String s, final boolean b) {
        this.b.a(this, s, b);
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
    
    public int getVideoDuration() {
        return this.a.h;
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
        this.a(null, true);
    }
    
    public void loadAd(final boolean b) {
        this.a(null, b);
    }
    
    @Override
    public void loadAdFromBid(final String s) {
        this.a(s, true);
    }
    
    public void loadAdFromBid(final String s, final boolean b) {
        this.a(s, b);
    }
    
    public void setAdListener(final RewardedVideoAdListener c) {
        this.a.c = c;
    }
    
    public void setExtraHints(final ExtraHints extraHints) {
        this.a.d = extraHints.getHints();
    }
    
    public void setRewardData(final RewardData rewardData) {
        this.b.a(rewardData);
    }
    
    public boolean show() {
        return this.show(-1);
    }
    
    public boolean show(final int n) {
        return this.b.a(this, n);
    }
}
