// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.d;

import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ExtractorMediaSource$EventListener;
import android.os.Handler;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.upstream.DataSource$Factory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.upstream.TransferListener;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import android.widget.MediaController$MediaPlayerControl;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.trackselection.TrackSelection$Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection$Factory;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.Timeline;
import android.graphics.SurfaceTexture;
import com.facebook.ads.internal.o.b;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.widget.MediaController;
import com.google.android.exoplayer2.SimpleExoPlayer;
import android.view.Surface;
import android.support.annotation.Nullable;
import android.net.Uri;
import android.annotation.TargetApi;
import com.google.android.exoplayer2.SimpleExoPlayer$VideoListener;
import com.google.android.exoplayer2.ExoPlayer$EventListener;
import android.view.TextureView$SurfaceTextureListener;
import android.view.TextureView;

@TargetApi(14)
public class a extends TextureView implements TextureView$SurfaceTextureListener, c, ExoPlayer$EventListener, SimpleExoPlayer$VideoListener
{
    private static final String a;
    private Uri b;
    @Nullable
    private String c;
    private e d;
    private Surface e;
    @Nullable
    private SimpleExoPlayer f;
    private MediaController g;
    private d h;
    private d i;
    private d j;
    private boolean k;
    private View l;
    private boolean m;
    private boolean n;
    private long o;
    private long p;
    private long q;
    private int r;
    private int s;
    private float t;
    private int u;
    private boolean v;
    private boolean w;
    private com.facebook.ads.internal.view.i.a.a x;
    private boolean y;
    
    static {
        a = a.class.getSimpleName();
    }
    
    public a(final Context context) {
        super(context);
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = com.facebook.ads.internal.view.i.d.d.a;
        this.j = com.facebook.ads.internal.view.i.d.d.a;
        this.k = false;
        this.m = false;
        this.n = false;
        this.t = 1.0f;
        this.u = -1;
        this.v = false;
        this.w = false;
        this.x = com.facebook.ads.internal.view.i.a.a.a;
        this.y = false;
    }
    
    public a(final Context context, final AttributeSet set) {
        super(context, set);
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = com.facebook.ads.internal.view.i.d.d.a;
        this.j = com.facebook.ads.internal.view.i.d.d.a;
        this.k = false;
        this.m = false;
        this.n = false;
        this.t = 1.0f;
        this.u = -1;
        this.v = false;
        this.w = false;
        this.x = com.facebook.ads.internal.view.i.a.a.a;
        this.y = false;
    }
    
    public a(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = com.facebook.ads.internal.view.i.d.d.a;
        this.j = com.facebook.ads.internal.view.i.d.d.a;
        this.k = false;
        this.m = false;
        this.n = false;
        this.t = 1.0f;
        this.u = -1;
        this.v = false;
        this.w = false;
        this.x = com.facebook.ads.internal.view.i.a.a.a;
        this.y = false;
    }
    
    @TargetApi(21)
    public a(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = com.facebook.ads.internal.view.i.d.d.a;
        this.j = com.facebook.ads.internal.view.i.d.d.a;
        this.k = false;
        this.m = false;
        this.n = false;
        this.t = 1.0f;
        this.u = -1;
        this.v = false;
        this.w = false;
        this.x = com.facebook.ads.internal.view.i.a.a.a;
        this.y = false;
    }
    
    private void f() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
        if (this.f != null) {
            this.f.release();
            this.f = null;
        }
        this.g = null;
        this.m = false;
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
    }
    
    private void setVideoState(final d h) {
        if (h != this.h) {
            this.h = h;
            if (this.h == com.facebook.ads.internal.view.i.d.d.d) {
                this.m = true;
            }
            if (this.d != null) {
                this.d.a(h);
            }
        }
    }
    
    public void a() {
        if (!this.w) {
            this.a(false);
        }
    }
    
    public void a(final int n) {
        if (this.f != null) {
            this.u = this.getCurrentPosition();
            this.f.seekTo((long)n);
            return;
        }
        this.q = n;
    }
    
    public void a(final com.facebook.ads.internal.view.i.a.a x) {
        this.i = com.facebook.ads.internal.view.i.d.d.d;
        this.x = x;
        if (this.f == null) {
            this.setup(this.b);
        }
        else if (this.h == com.facebook.ads.internal.view.i.d.d.c || this.h == com.facebook.ads.internal.view.i.d.d.e || this.h == com.facebook.ads.internal.view.i.d.d.g) {
            this.f.setPlayWhenReady(true);
            this.setVideoState(com.facebook.ads.internal.view.i.d.d.d);
        }
    }
    
    public void a(final boolean b) {
        if (this.f != null) {
            this.f.setPlayWhenReady(false);
            return;
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
    }
    
    public void b() {
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.g);
        this.c();
        this.q = 0L;
    }
    
    public void c() {
        this.i = com.facebook.ads.internal.view.i.d.d.a;
        if (this.f != null) {
            this.f.stop();
            this.f.release();
            this.f = null;
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
    }
    
    public boolean d() {
        return this.f != null && this.f.getAudioFormat() != null;
    }
    
    public void e() {
        this.f();
    }
    
    public int getCurrentPosition() {
        if (this.f != null) {
            return (int)this.f.getCurrentPosition();
        }
        return 0;
    }
    
    public int getDuration() {
        if (this.f == null) {
            return 0;
        }
        return (int)this.f.getDuration();
    }
    
    public long getInitialBufferTime() {
        return this.p;
    }
    
    public com.facebook.ads.internal.view.i.a.a getStartReason() {
        return this.x;
    }
    
    public d getState() {
        return this.h;
    }
    
    public d getTargetState() {
        return this.i;
    }
    
    public int getVideoHeight() {
        return this.s;
    }
    
    public int getVideoWidth() {
        return this.r;
    }
    
    public View getView() {
        return (View)this;
    }
    
    public float getVolume() {
        return this.t;
    }
    
    public void onLoadingChanged(final boolean b) {
    }
    
    public void onPlaybackParametersChanged(final PlaybackParameters playbackParameters) {
    }
    
    public void onPlayerError(final ExoPlaybackException ex) {
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.h);
        ex.printStackTrace();
        com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a((Throwable)ex, "[ExoPlayer] Error during playback of ExoPlayer"));
    }
    
    public void onPlayerStateChanged(final boolean b, int u) {
        switch (u) {
            case 1: {
                this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
            }
            case 2: {
                if (this.u >= 0) {
                    u = this.u;
                    this.u = -1;
                    this.d.a(u, this.getCurrentPosition());
                    return;
                }
                break;
            }
            case 3: {
                if (this.o != 0L) {
                    this.p = System.currentTimeMillis() - this.o;
                }
                this.setRequestedVolume(this.t);
                if (this.q > 0L && this.q < this.f.getDuration()) {
                    this.f.seekTo(this.q);
                    this.q = 0L;
                }
                if (this.f.getCurrentPosition() != 0L && !b && this.m) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.e);
                    return;
                }
                if (b || this.h == com.facebook.ads.internal.view.i.d.d.g) {
                    break;
                }
                this.setVideoState(com.facebook.ads.internal.view.i.d.d.c);
                if (this.i == com.facebook.ads.internal.view.i.d.d.d) {
                    this.a(this.x);
                    this.i = com.facebook.ads.internal.view.i.d.d.a;
                    return;
                }
                break;
            }
            case 4: {
                if (b) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.g);
                }
                if (this.f != null) {
                    this.f.setPlayWhenReady(false);
                    if (!b) {
                        this.f.seekToDefaultPosition();
                    }
                }
                this.m = false;
            }
        }
    }
    
    public void onPositionDiscontinuity() {
    }
    
    public void onRenderedFirstFrame() {
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        if (this.e != null) {
            this.e.release();
        }
        this.e = new Surface(surfaceTexture);
        if (this.f != null) {
            this.f.setVideoSurface(this.e);
            this.k = false;
            if (this.h == com.facebook.ads.internal.view.i.d.d.e && this.j != com.facebook.ads.internal.view.i.d.d.e) {
                this.a(this.x);
            }
        }
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        if (this.e != null) {
            this.e.release();
            this.e = null;
            if (this.f != null) {
                this.f.setVideoSurface((Surface)null);
            }
        }
        if (!this.k) {
            d j;
            if (this.n) {
                j = com.facebook.ads.internal.view.i.d.d.d;
            }
            else {
                j = this.h;
            }
            this.j = j;
            this.k = true;
        }
        if (this.h != com.facebook.ads.internal.view.i.d.d.e) {
            this.a(false);
        }
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void onTimelineChanged(final Timeline timeline, final Object o) {
    }
    
    public void onTracksChanged(final TrackGroupArray trackGroupArray, final TrackSelectionArray trackSelectionArray) {
    }
    
    public void onVideoSizeChanged(final int r, final int s, final int n, final float n2) {
        this.r = r;
        this.s = s;
        if (this.r != 0 && this.s != 0) {
            this.requestLayout();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.f != null && (this.g == null || !this.g.isShowing())) {
            if (!b) {
                if (!this.k) {
                    d j;
                    if (this.n) {
                        j = com.facebook.ads.internal.view.i.d.d.d;
                    }
                    else {
                        j = this.h;
                    }
                    this.j = j;
                    this.k = true;
                }
                if (this.h != com.facebook.ads.internal.view.i.d.d.e) {
                    this.a();
                }
            }
            else {
                this.k = false;
                if (this.h == com.facebook.ads.internal.view.i.d.d.e && this.j != com.facebook.ads.internal.view.i.d.d.e) {
                    this.a(this.x);
                }
            }
        }
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        if (Build$VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(backgroundDrawable);
        }
        else if (AdInternalSettings.isDebugBuild()) {
            Log.w(com.facebook.ads.internal.view.i.d.a.a, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }
    
    public void setBackgroundPlaybackEnabled(final boolean w) {
        this.w = w;
    }
    
    public void setControlsAnchorView(final View l) {
        (this.l = l).setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (com.facebook.ads.internal.view.i.d.a.this.g != null && motionEvent.getAction() == 1) {
                    if (!com.facebook.ads.internal.view.i.d.a.this.g.isShowing()) {
                        com.facebook.ads.internal.view.i.d.a.this.g.show();
                        return true;
                    }
                    com.facebook.ads.internal.view.i.d.a.this.g.hide();
                }
                return true;
            }
        });
    }
    
    public void setForeground(final Drawable foreground) {
        if (Build$VERSION.SDK_INT < 24) {
            super.setForeground(foreground);
        }
        else if (AdInternalSettings.isDebugBuild()) {
            Log.w(com.facebook.ads.internal.view.i.d.a.a, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }
    
    public void setFullScreen(final boolean n) {
        this.n = n;
        if (n && !this.v) {
            this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    if (com.facebook.ads.internal.view.i.d.a.this.g != null && motionEvent.getAction() == 1) {
                        if (!com.facebook.ads.internal.view.i.d.a.this.g.isShowing()) {
                            com.facebook.ads.internal.view.i.d.a.this.g.show();
                            return true;
                        }
                        com.facebook.ads.internal.view.i.d.a.this.g.hide();
                    }
                    return true;
                }
            });
        }
    }
    
    public void setRequestedVolume(final float n) {
        this.t = n;
        if (this.f != null && this.h != com.facebook.ads.internal.view.i.d.d.b && this.h != com.facebook.ads.internal.view.i.d.d.a) {
            this.f.setVolume(n);
        }
    }
    
    public void setTestMode(final boolean y) {
        this.y = y;
    }
    
    public void setVideoMPD(@Nullable final String c) {
        this.c = c;
    }
    
    public void setVideoStateChangeListener(final e d) {
        this.d = d;
    }
    
    public void setup(final Uri b) {
        if (this.f != null) {
            this.f();
        }
        this.b = b;
        this.setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
        final DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        (this.f = ExoPlayerFactory.newSimpleInstance(this.getContext(), (TrackSelector)new DefaultTrackSelector((TrackSelection$Factory)new AdaptiveTrackSelection$Factory((BandwidthMeter)defaultBandwidthMeter)), (LoadControl)new DefaultLoadControl())).setVideoListener((SimpleExoPlayer$VideoListener)this);
        this.f.addListener((ExoPlayer$EventListener)this);
        this.f.setPlayWhenReady(false);
        if (this.n && !this.v) {
            this.g = new MediaController(this.getContext());
            final MediaController g = this.g;
            Object l;
            if (this.l == null) {
                l = this;
            }
            else {
                l = this.l;
            }
            g.setAnchorView((View)l);
            this.g.setMediaPlayer((MediaController$MediaPlayerControl)new MediaController$MediaPlayerControl() {
                public boolean canPause() {
                    return true;
                }
                
                public boolean canSeekBackward() {
                    return true;
                }
                
                public boolean canSeekForward() {
                    return true;
                }
                
                public int getAudioSessionId() {
                    if (com.facebook.ads.internal.view.i.d.a.this.f != null) {
                        return com.facebook.ads.internal.view.i.d.a.this.f.getAudioSessionId();
                    }
                    return 0;
                }
                
                public int getBufferPercentage() {
                    if (com.facebook.ads.internal.view.i.d.a.this.f != null) {
                        return com.facebook.ads.internal.view.i.d.a.this.f.getBufferedPercentage();
                    }
                    return 0;
                }
                
                public int getCurrentPosition() {
                    return com.facebook.ads.internal.view.i.d.a.this.getCurrentPosition();
                }
                
                public int getDuration() {
                    return com.facebook.ads.internal.view.i.d.a.this.getDuration();
                }
                
                public boolean isPlaying() {
                    return com.facebook.ads.internal.view.i.d.a.this.f != null && com.facebook.ads.internal.view.i.d.a.this.f.getPlayWhenReady();
                }
                
                public void pause() {
                    com.facebook.ads.internal.view.i.d.a.this.a(true);
                }
                
                public void seekTo(final int n) {
                    com.facebook.ads.internal.view.i.d.a.this.a(n);
                }
                
                public void start() {
                    com.facebook.ads.internal.view.i.d.a.this.a(com.facebook.ads.internal.view.i.a.a.b);
                }
            });
            this.g.setEnabled(true);
        }
        if (this.c == null || this.c.length() == 0 || this.y) {
            this.f.prepare((MediaSource)new ExtractorMediaSource(this.b, (DataSource$Factory)new DefaultDataSourceFactory(this.getContext(), Util.getUserAgent(this.getContext(), "ads"), (TransferListener)defaultBandwidthMeter), (ExtractorsFactory)new DefaultExtractorsFactory(), (Handler)null, (ExtractorMediaSource$EventListener)null));
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.b);
        if (this.isAvailable()) {
            this.onSurfaceTextureAvailable(this.getSurfaceTexture(), 0, 0);
        }
    }
}
