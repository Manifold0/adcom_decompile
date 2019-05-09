// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.support.annotation.FloatRange;
import android.support.annotation.IntRange;
import android.util.Log;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.i.b.e;
import com.facebook.ads.internal.view.i.b.w;
import com.facebook.ads.internal.view.i.b.c;
import com.facebook.ads.internal.view.i.b.q;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.m;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.f;
import com.facebook.ads.internal.view.i.b.x;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.r;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.n;
import com.facebook.ads.internal.view.p;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;

public abstract class MediaViewVideoRenderer extends FrameLayout
{
    private static final String d;
    @Nullable
    protected NativeAd a;
    protected VideoAutoplayBehavior b;
    final p c;
    private final n e;
    private final l f;
    private final j g;
    private final r h;
    private final d i;
    private final x j;
    private final f k;
    private boolean l;
    private boolean m;
    
    static {
        d = MediaViewVideoRenderer.class.getSimpleName();
    }
    
    public MediaViewVideoRenderer(final Context context) {
        super(context);
        this.e = new n() {
            @Override
            public void a(final m m) {
                MediaViewVideoRenderer.this.onPrepared();
            }
        };
        this.f = new l() {
            @Override
            public void a(final k k) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(true, true);
                }
                MediaViewVideoRenderer.this.onPlayed();
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                MediaViewVideoRenderer.this.onPaused();
            }
        };
        this.h = new r() {
            @Override
            public void a(final q q) {
                MediaViewVideoRenderer.this.onSeek();
            }
        };
        this.i = new d() {
            @Override
            public void a(final c c) {
                MediaViewVideoRenderer.this.onCompleted();
            }
        };
        this.j = new x() {
            @Override
            public void a(final w w) {
                MediaViewVideoRenderer.this.onVolumeChanged();
            }
        };
        this.k = new f() {
            @Override
            public void a(final e e) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(false, true);
                }
                MediaViewVideoRenderer.this.onError();
            }
        };
        this.c = new p(context);
        this.b();
    }
    
    public MediaViewVideoRenderer(final Context context, final AttributeSet set) {
        super(context, set);
        this.e = new n() {
            @Override
            public void a(final m m) {
                MediaViewVideoRenderer.this.onPrepared();
            }
        };
        this.f = new l() {
            @Override
            public void a(final k k) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(true, true);
                }
                MediaViewVideoRenderer.this.onPlayed();
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                MediaViewVideoRenderer.this.onPaused();
            }
        };
        this.h = new r() {
            @Override
            public void a(final q q) {
                MediaViewVideoRenderer.this.onSeek();
            }
        };
        this.i = new d() {
            @Override
            public void a(final c c) {
                MediaViewVideoRenderer.this.onCompleted();
            }
        };
        this.j = new x() {
            @Override
            public void a(final w w) {
                MediaViewVideoRenderer.this.onVolumeChanged();
            }
        };
        this.k = new f() {
            @Override
            public void a(final e e) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(false, true);
                }
                MediaViewVideoRenderer.this.onError();
            }
        };
        this.c = new p(context, set);
        this.b();
    }
    
    public MediaViewVideoRenderer(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.e = new n() {
            @Override
            public void a(final m m) {
                MediaViewVideoRenderer.this.onPrepared();
            }
        };
        this.f = new l() {
            @Override
            public void a(final k k) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(true, true);
                }
                MediaViewVideoRenderer.this.onPlayed();
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                MediaViewVideoRenderer.this.onPaused();
            }
        };
        this.h = new r() {
            @Override
            public void a(final q q) {
                MediaViewVideoRenderer.this.onSeek();
            }
        };
        this.i = new d() {
            @Override
            public void a(final c c) {
                MediaViewVideoRenderer.this.onCompleted();
            }
        };
        this.j = new x() {
            @Override
            public void a(final w w) {
                MediaViewVideoRenderer.this.onVolumeChanged();
            }
        };
        this.k = new f() {
            @Override
            public void a(final e e) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(false, true);
                }
                MediaViewVideoRenderer.this.onError();
            }
        };
        this.c = new p(context, set, n);
        this.b();
    }
    
    @TargetApi(21)
    public MediaViewVideoRenderer(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.e = new n() {
            @Override
            public void a(final m m) {
                MediaViewVideoRenderer.this.onPrepared();
            }
        };
        this.f = new l() {
            @Override
            public void a(final k k) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(true, true);
                }
                MediaViewVideoRenderer.this.onPlayed();
            }
        };
        this.g = new j() {
            @Override
            public void a(final i i) {
                MediaViewVideoRenderer.this.onPaused();
            }
        };
        this.h = new r() {
            @Override
            public void a(final q q) {
                MediaViewVideoRenderer.this.onSeek();
            }
        };
        this.i = new d() {
            @Override
            public void a(final c c) {
                MediaViewVideoRenderer.this.onCompleted();
            }
        };
        this.j = new x() {
            @Override
            public void a(final w w) {
                MediaViewVideoRenderer.this.onVolumeChanged();
            }
        };
        this.k = new f() {
            @Override
            public void a(final e e) {
                if (MediaViewVideoRenderer.this.a != null) {
                    MediaViewVideoRenderer.this.a.f().a(false, true);
                }
                MediaViewVideoRenderer.this.onError();
            }
        };
        this.c = new p(context, set, n, n2);
        this.b();
    }
    
    private void b() {
        this.c.setEnableBackgroundVideo(this.shouldAllowBackgroundPlayback());
        final ViewGroup$LayoutParams layoutParams = new ViewGroup$LayoutParams(-1, -1);
        this.c.setLayoutParams(layoutParams);
        super.addView((View)this.c, -1, layoutParams);
        com.facebook.ads.internal.w.b.j.a((View)this.c, com.facebook.ads.internal.w.b.j.n);
        this.c.getEventBus().a(this.e, this.f, this.g, this.h, this.i, this.j, this.k);
    }
    
    protected void a() {
        this.pause(false);
        this.c.setClientToken(null);
        this.c.setVideoMPD(null);
        this.c.setVideoURI((Uri)null);
        this.c.setVideoCTA(null);
        this.c.setNativeAd(null);
        this.b = VideoAutoplayBehavior.DEFAULT;
        if (this.a != null) {
            this.a.f().a(false, false);
        }
        this.a = null;
    }
    
    public void addView(final View view) {
    }
    
    public void addView(final View view, final int n) {
    }
    
    public void addView(final View view, final int n, final int n2) {
    }
    
    public void addView(final View view, final int n, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
    }
    
    public void addView(final View view, final ViewGroup$LayoutParams viewGroup$LayoutParams) {
    }
    
    public void destroy() {
        this.c.l();
    }
    
    public final void disengageSeek(final VideoStartReason videoStartReason) {
        if (!this.l) {
            Log.w(MediaViewVideoRenderer.d, "disengageSeek called without engageSeek.");
            return;
        }
        this.l = false;
        if (this.m) {
            this.c.a(videoStartReason.a());
        }
        this.onSeekDisengaged();
    }
    
    public final void engageSeek() {
        if (this.l) {
            Log.w(MediaViewVideoRenderer.d, "engageSeek called without disengageSeek.");
            return;
        }
        this.l = true;
        this.m = com.facebook.ads.internal.view.i.d.d.d.equals(this.c.getState());
        this.c.a(false);
        this.onSeekEngaged();
    }
    
    @IntRange(from = 0L)
    public final int getCurrentTimeMs() {
        return this.c.getCurrentPositionInMillis();
    }
    
    @IntRange(from = 0L)
    public final int getDuration() {
        return this.c.getDuration();
    }
    
    final View getVideoView() {
        return this.c.getVideoView();
    }
    
    @FloatRange(from = 0.0, to = 1.0)
    public final float getVolume() {
        return this.c.getVolume();
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
    
    public final void pause(final boolean b) {
        this.c.a(b);
    }
    
    public final void play(final VideoStartReason videoStartReason) {
        this.c.a(videoStartReason.a());
    }
    
    public final void seekTo(@IntRange(from = 0L) final int n) {
        if (!this.l) {
            Log.w(MediaViewVideoRenderer.d, "Seeking must be preceded by a call to engageSeek, and followed by a call to disengageSeek.");
            return;
        }
        this.c.a(n);
    }
    
    final void setAdEventManager(final com.facebook.ads.internal.s.c adEventManager) {
        this.c.setAdEventManager(adEventManager);
    }
    
    final void setListener(final com.facebook.ads.internal.view.q listener) {
        this.c.setListener(listener);
    }
    
    protected void setNativeAd(final NativeAd nativeAd) {
        this.a = nativeAd;
        this.c.setClientToken(nativeAd.h());
        this.c.setVideoMPD(nativeAd.b());
        this.c.setVideoURI(nativeAd.a());
        this.c.setVideoProgressReportIntervalMs(nativeAd.g().w());
        this.c.setVideoCTA(nativeAd.getAdCallToAction());
        this.c.setNativeAd(nativeAd);
        this.b = nativeAd.c();
    }
    
    public final void setVolume(@FloatRange(from = 0.0, to = 1.0) final float volume) {
        this.c.setVolume(volume);
    }
    
    public boolean shouldAllowBackgroundPlayback() {
        return false;
    }
    
    public final boolean shouldAutoplay() {
        return this.c != null && this.c.getState() != com.facebook.ads.internal.view.i.d.d.g && this.b == VideoAutoplayBehavior.ON;
    }
}
