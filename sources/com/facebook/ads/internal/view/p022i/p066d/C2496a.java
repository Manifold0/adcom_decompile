package com.facebook.ads.internal.view.p022i.p066d;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import com.facebook.ads.internal.p021o.C2058a;
import com.facebook.ads.internal.p021o.C2059b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer.EventListener;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer.VideoListener;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection.Factory;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

@TargetApi(14)
/* renamed from: com.facebook.ads.internal.view.i.d.a */
public class C2496a extends TextureView implements SurfaceTextureListener, C2495c, EventListener, VideoListener {
    /* renamed from: a */
    private static final String f6024a = C2496a.class.getSimpleName();
    /* renamed from: b */
    private Uri f6025b;
    @Nullable
    /* renamed from: c */
    private String f6026c;
    /* renamed from: d */
    private C2393e f6027d;
    /* renamed from: e */
    private Surface f6028e;
    @Nullable
    /* renamed from: f */
    private SimpleExoPlayer f6029f;
    /* renamed from: g */
    private MediaController f6030g;
    /* renamed from: h */
    private C2501d f6031h = C2501d.IDLE;
    /* renamed from: i */
    private C2501d f6032i = C2501d.IDLE;
    /* renamed from: j */
    private C2501d f6033j = C2501d.IDLE;
    /* renamed from: k */
    private boolean f6034k = false;
    /* renamed from: l */
    private View f6035l;
    /* renamed from: m */
    private boolean f6036m = false;
    /* renamed from: n */
    private boolean f6037n = false;
    /* renamed from: o */
    private long f6038o;
    /* renamed from: p */
    private long f6039p;
    /* renamed from: q */
    private long f6040q;
    /* renamed from: r */
    private int f6041r;
    /* renamed from: s */
    private int f6042s;
    /* renamed from: t */
    private float f6043t = 1.0f;
    /* renamed from: u */
    private int f6044u = -1;
    /* renamed from: v */
    private boolean f6045v = false;
    /* renamed from: w */
    private boolean f6046w = false;
    /* renamed from: x */
    private C2389a f6047x = C2389a.NOT_STARTED;
    /* renamed from: y */
    private boolean f6048y = false;

    /* renamed from: com.facebook.ads.internal.view.i.d.a$1 */
    class C24921 implements MediaPlayerControl {
        /* renamed from: a */
        final /* synthetic */ C2496a f6021a;

        C24921(C2496a c2496a) {
            this.f6021a = c2496a;
        }

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
            return this.f6021a.f6029f != null ? this.f6021a.f6029f.getAudioSessionId() : 0;
        }

        public int getBufferPercentage() {
            return this.f6021a.f6029f != null ? this.f6021a.f6029f.getBufferedPercentage() : 0;
        }

        public int getCurrentPosition() {
            return this.f6021a.getCurrentPosition();
        }

        public int getDuration() {
            return this.f6021a.getDuration();
        }

        public boolean isPlaying() {
            return this.f6021a.f6029f != null && this.f6021a.f6029f.getPlayWhenReady();
        }

        public void pause() {
            this.f6021a.mo5613a(true);
        }

        public void seekTo(int i) {
            this.f6021a.mo5611a(i);
        }

        public void start() {
            this.f6021a.mo5612a(C2389a.USER_STARTED);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.d.a$2 */
    class C24932 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2496a f6022a;

        C24932(C2496a c2496a) {
            this.f6022a = c2496a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f6022a.f6030g != null && motionEvent.getAction() == 1) {
                if (this.f6022a.f6030g.isShowing()) {
                    this.f6022a.f6030g.hide();
                } else {
                    this.f6022a.f6030g.show();
                }
            }
            return true;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.d.a$3 */
    class C24943 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2496a f6023a;

        C24943(C2496a c2496a) {
            this.f6023a = c2496a;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (this.f6023a.f6030g != null && motionEvent.getAction() == 1) {
                if (this.f6023a.f6030g.isShowing()) {
                    this.f6023a.f6030g.hide();
                } else {
                    this.f6023a.f6030g.show();
                }
            }
            return true;
        }
    }

    public C2496a(Context context) {
        super(context);
    }

    public C2496a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2496a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public C2496a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* renamed from: f */
    private void m6418f() {
        if (this.f6028e != null) {
            this.f6028e.release();
            this.f6028e = null;
        }
        if (this.f6029f != null) {
            this.f6029f.release();
            this.f6029f = null;
        }
        this.f6030g = null;
        this.f6036m = false;
        setVideoState(C2501d.IDLE);
    }

    private void setVideoState(C2501d c2501d) {
        if (c2501d != this.f6031h) {
            this.f6031h = c2501d;
            if (this.f6031h == C2501d.STARTED) {
                this.f6036m = true;
            }
            if (this.f6027d != null) {
                this.f6027d.mo5600a(c2501d);
            }
        }
    }

    /* renamed from: a */
    public void mo5610a() {
        if (!this.f6046w) {
            mo5613a(false);
        }
    }

    /* renamed from: a */
    public void mo5611a(int i) {
        if (this.f6029f != null) {
            this.f6044u = getCurrentPosition();
            this.f6029f.seekTo((long) i);
            return;
        }
        this.f6040q = (long) i;
    }

    /* renamed from: a */
    public void mo5612a(C2389a c2389a) {
        this.f6032i = C2501d.STARTED;
        this.f6047x = c2389a;
        if (this.f6029f == null) {
            setup(this.f6025b);
        } else if (this.f6031h == C2501d.PREPARED || this.f6031h == C2501d.PAUSED || this.f6031h == C2501d.PLAYBACK_COMPLETED) {
            this.f6029f.setPlayWhenReady(true);
            setVideoState(C2501d.STARTED);
        }
    }

    /* renamed from: a */
    public void mo5613a(boolean z) {
        if (this.f6029f != null) {
            this.f6029f.setPlayWhenReady(false);
        } else {
            setVideoState(C2501d.IDLE);
        }
    }

    /* renamed from: b */
    public void mo5614b() {
        setVideoState(C2501d.PLAYBACK_COMPLETED);
        mo5615c();
        this.f6040q = 0;
    }

    /* renamed from: c */
    public void mo5615c() {
        this.f6032i = C2501d.IDLE;
        if (this.f6029f != null) {
            this.f6029f.stop();
            this.f6029f.release();
            this.f6029f = null;
        }
        setVideoState(C2501d.IDLE);
    }

    /* renamed from: d */
    public boolean mo5616d() {
        return (this.f6029f == null || this.f6029f.getAudioFormat() == null) ? false : true;
    }

    /* renamed from: e */
    public void mo5617e() {
        m6418f();
    }

    public int getCurrentPosition() {
        return this.f6029f != null ? (int) this.f6029f.getCurrentPosition() : 0;
    }

    public int getDuration() {
        return this.f6029f == null ? 0 : (int) this.f6029f.getDuration();
    }

    public long getInitialBufferTime() {
        return this.f6039p;
    }

    public C2389a getStartReason() {
        return this.f6047x;
    }

    public C2501d getState() {
        return this.f6031h;
    }

    public C2501d getTargetState() {
        return this.f6032i;
    }

    public int getVideoHeight() {
        return this.f6042s;
    }

    public int getVideoWidth() {
        return this.f6041r;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.f6043t;
    }

    public void onLoadingChanged(boolean z) {
    }

    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
    }

    public void onPlayerError(ExoPlaybackException exoPlaybackException) {
        setVideoState(C2501d.ERROR);
        exoPlaybackException.printStackTrace();
        C2059b.m5023a(C2058a.m5020a(exoPlaybackException, "[ExoPlayer] Error during playback of ExoPlayer"));
    }

    public void onPlayerStateChanged(boolean z, int i) {
        switch (i) {
            case 1:
                setVideoState(C2501d.IDLE);
                return;
            case 2:
                if (this.f6044u >= 0) {
                    int i2 = this.f6044u;
                    this.f6044u = -1;
                    this.f6027d.mo5599a(i2, getCurrentPosition());
                    return;
                }
                return;
            case 3:
                if (this.f6038o != 0) {
                    this.f6039p = System.currentTimeMillis() - this.f6038o;
                }
                setRequestedVolume(this.f6043t);
                if (this.f6040q > 0 && this.f6040q < this.f6029f.getDuration()) {
                    this.f6029f.seekTo(this.f6040q);
                    this.f6040q = 0;
                }
                if (this.f6029f.getCurrentPosition() != 0 && !z && this.f6036m) {
                    setVideoState(C2501d.PAUSED);
                    return;
                } else if (!z && this.f6031h != C2501d.PLAYBACK_COMPLETED) {
                    setVideoState(C2501d.PREPARED);
                    if (this.f6032i == C2501d.STARTED) {
                        mo5612a(this.f6047x);
                        this.f6032i = C2501d.IDLE;
                        return;
                    }
                    return;
                } else {
                    return;
                }
            case 4:
                if (z) {
                    setVideoState(C2501d.PLAYBACK_COMPLETED);
                }
                if (this.f6029f != null) {
                    this.f6029f.setPlayWhenReady(false);
                    if (!z) {
                        this.f6029f.seekToDefaultPosition();
                    }
                }
                this.f6036m = false;
                return;
            default:
                return;
        }
    }

    public void onPositionDiscontinuity() {
    }

    public void onRenderedFirstFrame() {
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f6028e != null) {
            this.f6028e.release();
        }
        this.f6028e = new Surface(surfaceTexture);
        if (this.f6029f != null) {
            this.f6029f.setVideoSurface(this.f6028e);
            this.f6034k = false;
            if (this.f6031h == C2501d.PAUSED && this.f6033j != C2501d.PAUSED) {
                mo5612a(this.f6047x);
            }
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        if (this.f6028e != null) {
            this.f6028e.release();
            this.f6028e = null;
            if (this.f6029f != null) {
                this.f6029f.setVideoSurface(null);
            }
        }
        if (!this.f6034k) {
            this.f6033j = this.f6037n ? C2501d.STARTED : this.f6031h;
            this.f6034k = true;
        }
        if (this.f6031h != C2501d.PAUSED) {
            mo5613a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onTimelineChanged(Timeline timeline, Object obj) {
    }

    public void onTracksChanged(TrackGroupArray trackGroupArray, TrackSelectionArray trackSelectionArray) {
    }

    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        this.f6041r = i;
        this.f6042s = i2;
        if (this.f6041r != 0 && this.f6042s != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f6029f != null) {
            if (this.f6030g != null && this.f6030g.isShowing()) {
                return;
            }
            if (z) {
                this.f6034k = false;
                if (this.f6031h == C2501d.PAUSED && this.f6033j != C2501d.PAUSED) {
                    mo5612a(this.f6047x);
                    return;
                }
                return;
            }
            if (!this.f6034k) {
                this.f6033j = this.f6037n ? C2501d.STARTED : this.f6031h;
                this.f6034k = true;
            }
            if (this.f6031h != C2501d.PAUSED) {
                mo5610a();
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f6024a, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.f6046w = z;
    }

    public void setControlsAnchorView(View view) {
        this.f6035l = view;
        view.setOnTouchListener(new C24943(this));
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f6024a, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.f6037n = z;
        if (z && !this.f6045v) {
            setOnTouchListener(new C24932(this));
        }
    }

    public void setRequestedVolume(float f) {
        this.f6043t = f;
        if (this.f6029f != null && this.f6031h != C2501d.PREPARING && this.f6031h != C2501d.IDLE) {
            this.f6029f.setVolume(f);
        }
    }

    public void setTestMode(boolean z) {
        this.f6048y = z;
    }

    public void setVideoMPD(@Nullable String str) {
        this.f6026c = str;
    }

    public void setVideoStateChangeListener(C2393e c2393e) {
        this.f6027d = c2393e;
    }

    public void setup(Uri uri) {
        if (this.f6029f != null) {
            m6418f();
        }
        this.f6025b = uri;
        setSurfaceTextureListener(this);
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        this.f6029f = ExoPlayerFactory.newSimpleInstance(getContext(), new DefaultTrackSelector(new Factory(defaultBandwidthMeter)), new DefaultLoadControl());
        this.f6029f.setVideoListener(this);
        this.f6029f.addListener(this);
        this.f6029f.setPlayWhenReady(false);
        if (this.f6037n && !this.f6045v) {
            this.f6030g = new MediaController(getContext());
            this.f6030g.setAnchorView(this.f6035l == null ? this : this.f6035l);
            this.f6030g.setMediaPlayer(new C24921(this));
            this.f6030g.setEnabled(true);
        }
        if (this.f6026c == null || this.f6026c.length() == 0 || this.f6048y) {
            this.f6029f.prepare(new ExtractorMediaSource(this.f6025b, new DefaultDataSourceFactory(getContext(), Util.getUserAgent(getContext(), "ads"), defaultBandwidthMeter), new DefaultExtractorsFactory(), null, null));
        }
        setVideoState(C2501d.PREPARING);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
