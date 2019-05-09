package com.applovin.impl.adview;

import android.net.Uri;
import android.os.Bundle;
import com.applovin.impl.p016a.C1228a;
import com.applovin.impl.p016a.C1232e;
import com.applovin.impl.p016a.C1235h;
import com.applovin.impl.p016a.C1239l;
import com.applovin.impl.p016a.C1240m;
import com.applovin.impl.p016a.C1241n;
import com.applovin.impl.p016a.C1245r;
import com.applovin.sdk.AppLovinSdkUtils;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ck extends az {
    /* renamed from: a */
    private final Set<C1239l> f1557a = new HashSet();

    /* renamed from: a */
    private void m1762a() {
        if (isFullyWatched() && !this.f1557a.isEmpty()) {
            this.logger.mo4178w("InterstitialActivity", "Firing " + this.f1557a.size() + " un-fired video progress trackers when video was completed.");
            m1767a(this.f1557a);
        }
    }

    /* renamed from: a */
    private void m1763a(C1232e c1232e) {
        m1764a(c1232e, C1235h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m1764a(C1232e c1232e, C1235h c1235h) {
        m1766a(c1232e, "", c1235h);
    }

    /* renamed from: a */
    private void m1765a(C1232e c1232e, String str) {
        m1766a(c1232e, str, C1235h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m1766a(C1232e c1232e, String str, C1235h c1235h) {
        if (isVastAd()) {
            m1768a(((C1228a) this.currentAd).m1836a(c1232e, str), c1235h);
        }
    }

    /* renamed from: a */
    private void m1767a(Set<C1239l> set) {
        m1768a((Set) set, C1235h.UNSPECIFIED);
    }

    /* renamed from: a */
    private void m1768a(Set<C1239l> set, C1235h c1235h) {
        if (isVastAd() && set != null && !set.isEmpty()) {
            long toSeconds = TimeUnit.MILLISECONDS.toSeconds((long) this.videoView.getCurrentPosition());
            C1245r c = m1769b().m1841c();
            Uri a = c != null ? c.m1934a() : null;
            this.logger.mo4172d("InterstitialActivity", "Firing " + set.size() + " tracker(s): " + set);
            C1241n.m1913a((Set) set, toSeconds, a, c1235h, this.sdk);
        }
    }

    /* renamed from: b */
    private C1228a m1769b() {
        return this.currentAd instanceof C1228a ? (C1228a) this.currentAd : null;
    }

    public void clickThroughFromVideo() {
        super.clickThroughFromVideo();
        m1763a(C1232e.VIDEO_CLICK);
    }

    public void dismiss() {
        if (isVastAd()) {
            m1765a(C1232e.f1604d, String.CLOSE);
            m1765a(C1232e.COMPANION, String.CLOSE);
        }
        super.dismiss();
    }

    public void handleCountdownStep() {
        if (isVastAd()) {
            long toSeconds = ((long) this.computedLengthSeconds) - TimeUnit.MILLISECONDS.toSeconds((long) (this.videoView.getDuration() - this.videoView.getCurrentPosition()));
            Set hashSet = new HashSet();
            for (C1239l c1239l : new HashSet(this.f1557a)) {
                if (c1239l.m1900a(toSeconds, getVideoPercentViewed())) {
                    hashSet.add(c1239l);
                    this.f1557a.remove(c1239l);
                }
            }
            m1767a(hashSet);
        }
    }

    public void handleMediaError() {
        m1764a(C1232e.ERROR, C1235h.MEDIA_FILE_ERROR);
        super.handleMediaError();
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (isVastAd()) {
            String a = m1769b().m1835a(this.currentPlacement);
            if (AppLovinSdkUtils.isValidString(a)) {
                this.logger.mo4172d("InterstitialActivity", "Firing AppLovin impression...");
                this.sdk.getPersistentPostbackManager().m2611a(a, null, false);
            }
            this.f1557a.addAll(m1769b().m1837a(C1232e.f1604d, C1240m.f1650a));
            m1763a(C1232e.IMPRESSION);
            m1765a(C1232e.f1604d, "creativeView");
        }
    }

    public void playVideo() {
        this.countdownManager.m2014a("PROGRESS_TRACKING", this.settingsProxy.m2699Z(), new cl(this));
        super.playVideo();
    }

    public void showPoststitial() {
        if (isVastAd()) {
            m1762a();
            if (!C1241n.m1920c(m1769b())) {
                dismiss();
                return;
            } else if (!this.poststitialWasDisplayed) {
                m1765a(C1232e.COMPANION, "creativeView");
                super.showPoststitial();
                return;
            } else {
                return;
            }
        }
        super.showPoststitial();
    }

    public void skipVideo() {
        m1765a(C1232e.f1604d, "skip");
        super.skipVideo();
    }

    public void toggleMute() {
        super.toggleMute();
        if (this.videoMuted) {
            m1765a(C1232e.f1604d, "mute");
        } else {
            m1765a(C1232e.f1604d, "unmute");
        }
    }
}
