// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.impl.a.m;
import java.util.Map;
import com.applovin.sdk.AppLovinSdkUtils;
import android.os.Bundle;
import java.util.Iterator;
import java.util.Collection;
import android.net.Uri;
import com.applovin.impl.a.r;
import com.applovin.impl.a.n;
import java.util.concurrent.TimeUnit;
import com.applovin.impl.a.a;
import com.applovin.impl.a.h;
import com.applovin.impl.a.e;
import java.util.HashSet;
import com.applovin.impl.a.l;
import java.util.Set;

public class ck extends az
{
    private final Set<l> a;
    
    public ck() {
        this.a = new HashSet<l>();
    }
    
    private void a() {
        if (this.isFullyWatched() && !this.a.isEmpty()) {
            this.logger.w("InterstitialActivity", "Firing " + this.a.size() + " un-fired video progress trackers when video was completed.");
            this.a(this.a);
        }
    }
    
    private void a(final e e) {
        this.a(e, com.applovin.impl.a.h.a);
    }
    
    private void a(final e e, final h h) {
        this.a(e, "", h);
    }
    
    private void a(final e e, final String s) {
        this.a(e, s, com.applovin.impl.a.h.a);
    }
    
    private void a(final e e, final String s, final h h) {
        if (this.isVastAd()) {
            this.a(((a)this.currentAd).a(e, s), h);
        }
    }
    
    private void a(final Set<l> set) {
        this.a(set, com.applovin.impl.a.h.a);
    }
    
    private void a(final Set<l> set, final h h) {
        if (this.isVastAd() && set != null && !set.isEmpty()) {
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(this.videoView.getCurrentPosition());
            final r c = this.b().c();
            Uri a;
            if (c != null) {
                a = c.a();
            }
            else {
                a = null;
            }
            this.logger.d("InterstitialActivity", "Firing " + set.size() + " tracker(s): " + set);
            com.applovin.impl.a.n.a(set, seconds, a, h, this.sdk);
        }
    }
    
    private a b() {
        if (this.currentAd instanceof a) {
            return (a)this.currentAd;
        }
        return null;
    }
    
    @Override
    public void clickThroughFromVideo() {
        super.clickThroughFromVideo();
        this.a(com.applovin.impl.a.e.b);
    }
    
    @Override
    public void dismiss() {
        if (this.isVastAd()) {
            this.a(com.applovin.impl.a.e.d, "close");
            this.a(com.applovin.impl.a.e.e, "close");
        }
        super.dismiss();
    }
    
    public void handleCountdownStep() {
        if (this.isVastAd()) {
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(this.videoView.getDuration() - this.videoView.getCurrentPosition());
            final long n = this.computedLengthSeconds;
            final HashSet<l> set = new HashSet<l>();
            for (final l l : new HashSet<l>(this.a)) {
                if (l.a(n - seconds, this.getVideoPercentViewed())) {
                    set.add(l);
                    this.a.remove(l);
                }
            }
            this.a(set);
        }
    }
    
    @Override
    public void handleMediaError() {
        this.a(com.applovin.impl.a.e.f, com.applovin.impl.a.h.j);
        super.handleMediaError();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (this.isVastAd()) {
            final String a = this.b().a(this.currentPlacement);
            if (AppLovinSdkUtils.isValidString(a)) {
                this.logger.d("InterstitialActivity", "Firing AppLovin impression...");
                this.sdk.getPersistentPostbackManager().a(a, null, false);
            }
            this.a.addAll(this.b().a(com.applovin.impl.a.e.d, com.applovin.impl.a.m.a));
            this.a(com.applovin.impl.a.e.a);
            this.a(com.applovin.impl.a.e.d, "creativeView");
        }
    }
    
    public void playVideo() {
        this.countdownManager.a("PROGRESS_TRACKING", this.settingsProxy.Z(), new cl(this));
        super.playVideo();
    }
    
    @Override
    public void showPoststitial() {
        if (!this.isVastAd()) {
            super.showPoststitial();
            return;
        }
        this.a();
        if (com.applovin.impl.a.n.c(this.b())) {
            if (!this.poststitialWasDisplayed) {
                this.a(com.applovin.impl.a.e.e, "creativeView");
                super.showPoststitial();
            }
            return;
        }
        this.dismiss();
    }
    
    @Override
    public void skipVideo() {
        this.a(com.applovin.impl.a.e.d, "skip");
        super.skipVideo();
    }
    
    @Override
    public void toggleMute() {
        super.toggleMute();
        if (this.videoMuted) {
            this.a(com.applovin.impl.a.e.d, "mute");
            return;
        }
        this.a(com.applovin.impl.a.e.d, "unmute");
    }
}
