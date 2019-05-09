package com.facebook.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import com.facebook.ads.internal.p025w.p026b.C2580j;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.view.C1807q;
import com.facebook.ads.internal.view.C2532p;
import com.facebook.ads.internal.view.p022i.p023b.C1810n;
import com.facebook.ads.internal.view.p022i.p023b.C1812l;
import com.facebook.ads.internal.view.p022i.p023b.C1814j;
import com.facebook.ads.internal.view.p022i.p023b.C1816r;
import com.facebook.ads.internal.view.p022i.p023b.C1818d;
import com.facebook.ads.internal.view.p022i.p023b.C1820x;
import com.facebook.ads.internal.view.p022i.p023b.C1822f;
import com.facebook.ads.internal.view.p022i.p023b.C2406c;
import com.facebook.ads.internal.view.p022i.p023b.C2407e;
import com.facebook.ads.internal.view.p022i.p023b.C2410i;
import com.facebook.ads.internal.view.p022i.p023b.C2411k;
import com.facebook.ads.internal.view.p022i.p023b.C2412m;
import com.facebook.ads.internal.view.p022i.p023b.C2414q;
import com.facebook.ads.internal.view.p022i.p023b.C2419w;
import com.facebook.ads.internal.view.p022i.p066d.C2501d;

public abstract class MediaViewVideoRenderer extends FrameLayout {
    /* renamed from: d */
    private static final String f3724d = MediaViewVideoRenderer.class.getSimpleName();
    @Nullable
    /* renamed from: a */
    protected NativeAd f3725a;
    /* renamed from: b */
    protected VideoAutoplayBehavior f3726b;
    /* renamed from: c */
    final C2532p f3727c;
    /* renamed from: e */
    private final C1810n f3728e = new C18111(this);
    /* renamed from: f */
    private final C1812l f3729f = new C18132(this);
    /* renamed from: g */
    private final C1814j f3730g = new C18153(this);
    /* renamed from: h */
    private final C1816r f3731h = new C18174(this);
    /* renamed from: i */
    private final C1818d f3732i = new C18195(this);
    /* renamed from: j */
    private final C1820x f3733j = new C18216(this);
    /* renamed from: k */
    private final C1822f f3734k = new C18237(this);
    /* renamed from: l */
    private boolean f3735l;
    /* renamed from: m */
    private boolean f3736m;

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$1 */
    class C18111 extends C1810n {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3767a;

        C18111(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3767a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4054a(C2412m c2412m) {
            this.f3767a.onPrepared();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$2 */
    class C18132 extends C1812l {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3768a;

        C18132(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3768a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4057a(C2411k c2411k) {
            if (this.f3768a.f3725a != null) {
                this.f3768a.f3725a.m4078f().m5321a(true, true);
            }
            this.f3768a.onPlayed();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$3 */
    class C18153 extends C1814j {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3769a;

        C18153(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3769a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4060a(C2410i c2410i) {
            this.f3769a.onPaused();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$4 */
    class C18174 extends C1816r {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3770a;

        C18174(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3770a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4063a(C2414q c2414q) {
            this.f3770a.onSeek();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$5 */
    class C18195 extends C1818d {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3771a;

        C18195(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3771a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4066a(C2406c c2406c) {
            this.f3771a.onCompleted();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$6 */
    class C18216 extends C1820x {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3772a;

        C18216(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3772a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4069a(C2419w c2419w) {
            this.f3772a.onVolumeChanged();
        }
    }

    /* renamed from: com.facebook.ads.MediaViewVideoRenderer$7 */
    class C18237 extends C1822f {
        /* renamed from: a */
        final /* synthetic */ MediaViewVideoRenderer f3773a;

        C18237(MediaViewVideoRenderer mediaViewVideoRenderer) {
            this.f3773a = mediaViewVideoRenderer;
        }

        /* renamed from: a */
        public void m4072a(C2407e c2407e) {
            if (this.f3773a.f3725a != null) {
                this.f3773a.f3725a.m4078f().m5321a(false, true);
            }
            this.f3773a.onError();
        }
    }

    public MediaViewVideoRenderer(Context context) {
        super(context);
        this.f3727c = new C2532p(context);
        m3994b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3727c = new C2532p(context, attributeSet);
        m3994b();
    }

    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f3727c = new C2532p(context, attributeSet, i);
        m3994b();
    }

    @TargetApi(21)
    public MediaViewVideoRenderer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f3727c = new C2532p(context, attributeSet, i, i2);
        m3994b();
    }

    /* renamed from: b */
    private void m3994b() {
        this.f3727c.setEnableBackgroundVideo(shouldAllowBackgroundPlayback());
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        this.f3727c.setLayoutParams(layoutParams);
        super.addView(this.f3727c, -1, layoutParams);
        C2580j.m6643a(this.f3727c, C2580j.INTERNAL_AD_MEDIA);
        this.f3727c.getEventBus().m5029a(this.f3728e, this.f3729f, this.f3730g, this.f3731h, this.f3732i, this.f3733j, this.f3734k);
    }

    /* renamed from: a */
    protected void mo5339a() {
        pause(false);
        this.f3727c.setClientToken(null);
        this.f3727c.setVideoMPD(null);
        this.f3727c.setVideoURI((Uri) null);
        this.f3727c.setVideoCTA(null);
        this.f3727c.setNativeAd(null);
        this.f3726b = VideoAutoplayBehavior.DEFAULT;
        if (this.f3725a != null) {
            this.f3725a.m4078f().m5321a(false, false);
        }
        this.f3725a = null;
    }

    public void addView(View view) {
    }

    public void addView(View view, int i) {
    }

    public void addView(View view, int i, int i2) {
    }

    public void addView(View view, int i, LayoutParams layoutParams) {
    }

    public void addView(View view, LayoutParams layoutParams) {
    }

    public void destroy() {
        this.f3727c.m6170l();
    }

    public final void disengageSeek(VideoStartReason videoStartReason) {
        if (this.f3735l) {
            this.f3735l = false;
            if (this.f3736m) {
                this.f3727c.m6157a(videoStartReason.m4129a());
            }
            onSeekDisengaged();
            return;
        }
        Log.w(f3724d, "disengageSeek called without engageSeek.");
    }

    public final void engageSeek() {
        if (this.f3735l) {
            Log.w(f3724d, "engageSeek called without disengageSeek.");
            return;
        }
        this.f3735l = true;
        this.f3736m = C2501d.STARTED.equals(this.f3727c.getState());
        this.f3727c.m6160a(false);
        onSeekEngaged();
    }

    @IntRange(from = 0)
    public final int getCurrentTimeMs() {
        return this.f3727c.getCurrentPositionInMillis();
    }

    @IntRange(from = 0)
    public final int getDuration() {
        return this.f3727c.getDuration();
    }

    final View getVideoView() {
        return this.f3727c.getVideoView();
    }

    @FloatRange(from = 0.0d, to = 1.0d)
    public final float getVolume() {
        return this.f3727c.getVolume();
    }

    public void onCompleted() {
    }

    public void onError() {
    }

    public void onPaused() {
    }

    public void onPlayed() {
    }

    public void onPrepared() {
    }

    public void onSeek() {
    }

    public void onSeekDisengaged() {
    }

    public void onSeekEngaged() {
    }

    public void onVolumeChanged() {
    }

    public final void pause(boolean z) {
        this.f3727c.m6160a(z);
    }

    public final void play(VideoStartReason videoStartReason) {
        this.f3727c.m6157a(videoStartReason.m4129a());
    }

    public final void seekTo(@IntRange(from = 0) int i) {
        if (this.f3735l) {
            this.f3727c.m6155a(i);
        } else {
            Log.w(f3724d, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
        }
    }

    final void setAdEventManager(C2085c c2085c) {
        this.f3727c.setAdEventManager(c2085c);
    }

    final void setListener(C1807q c1807q) {
        this.f3727c.setListener(c1807q);
    }

    protected void setNativeAd(NativeAd nativeAd) {
        this.f3725a = nativeAd;
        this.f3727c.setClientToken(nativeAd.m4080h());
        this.f3727c.setVideoMPD(nativeAd.m4083b());
        this.f3727c.setVideoURI(nativeAd.m4081a());
        this.f3727c.setVideoProgressReportIntervalMs(nativeAd.m4079g().m4523w());
        this.f3727c.setVideoCTA(nativeAd.getAdCallToAction());
        this.f3727c.setNativeAd(nativeAd);
        this.f3726b = nativeAd.m4084c();
    }

    public final void setVolume(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        this.f3727c.setVolume(f);
    }

    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }

    public final boolean shouldAutoplay() {
        return (this.f3727c == null || this.f3727c.getState() == C2501d.PLAYBACK_COMPLETED || this.f3726b != VideoAutoplayBehavior.ON) ? false : true;
    }
}
