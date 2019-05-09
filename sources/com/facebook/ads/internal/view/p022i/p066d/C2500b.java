package com.facebook.ads.internal.view.p022i.p066d;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.media.MediaPlayer.TrackInfo;
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
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.view.p022i.p065a.C2389a;
import java.io.IOException;

@TargetApi(14)
/* renamed from: com.facebook.ads.internal.view.i.d.b */
public class C2500b extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnInfoListener, OnPreparedListener, OnSeekCompleteListener, OnVideoSizeChangedListener, SurfaceTextureListener, C2495c {
    /* renamed from: t */
    private static final String f6052t = C2500b.class.getSimpleName();
    /* renamed from: a */
    private Uri f6053a;
    /* renamed from: b */
    private C2393e f6054b;
    /* renamed from: c */
    private Surface f6055c;
    @Nullable
    /* renamed from: d */
    private MediaPlayer f6056d;
    /* renamed from: e */
    private MediaController f6057e;
    /* renamed from: f */
    private C2501d f6058f = C2501d.IDLE;
    /* renamed from: g */
    private C2501d f6059g = C2501d.IDLE;
    /* renamed from: h */
    private C2501d f6060h = C2501d.IDLE;
    /* renamed from: i */
    private boolean f6061i = false;
    /* renamed from: j */
    private View f6062j;
    /* renamed from: k */
    private int f6063k = 0;
    /* renamed from: l */
    private long f6064l;
    /* renamed from: m */
    private int f6065m = 0;
    /* renamed from: n */
    private int f6066n = 0;
    /* renamed from: o */
    private float f6067o = 1.0f;
    /* renamed from: p */
    private boolean f6068p = false;
    /* renamed from: q */
    private int f6069q = 3;
    /* renamed from: r */
    private boolean f6070r = false;
    /* renamed from: s */
    private boolean f6071s = false;
    /* renamed from: u */
    private int f6072u = 0;
    /* renamed from: v */
    private boolean f6073v = false;
    /* renamed from: w */
    private C2389a f6074w = C2389a.NOT_STARTED;
    /* renamed from: x */
    private final MediaPlayerControl f6075x = new C24971(this);

    /* renamed from: com.facebook.ads.internal.view.i.d.b$1 */
    class C24971 implements MediaPlayerControl {
        /* renamed from: a */
        final /* synthetic */ C2500b f6049a;

        C24971(C2500b c2500b) {
            this.f6049a = c2500b;
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
            return this.f6049a.f6056d != null ? this.f6049a.f6056d.getAudioSessionId() : 0;
        }

        public int getBufferPercentage() {
            return 0;
        }

        public int getCurrentPosition() {
            return this.f6049a.getCurrentPosition();
        }

        public int getDuration() {
            return this.f6049a.getDuration();
        }

        public boolean isPlaying() {
            return this.f6049a.f6056d != null && this.f6049a.f6056d.isPlaying();
        }

        public void pause() {
            this.f6049a.mo5613a(true);
        }

        public void seekTo(int i) {
            this.f6049a.mo5611a(i);
        }

        public void start() {
            this.f6049a.mo5612a(C2389a.USER_STARTED);
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.d.b$2 */
    class C24982 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2500b f6050a;

        C24982(C2500b c2500b) {
            this.f6050a = c2500b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(this.f6050a.f6073v || this.f6050a.f6057e == null || motionEvent.getAction() != 1)) {
                if (this.f6050a.f6057e.isShowing()) {
                    this.f6050a.f6057e.hide();
                } else {
                    this.f6050a.f6057e.show();
                }
            }
            return true;
        }
    }

    /* renamed from: com.facebook.ads.internal.view.i.d.b$3 */
    class C24993 implements OnTouchListener {
        /* renamed from: a */
        final /* synthetic */ C2500b f6051a;

        C24993(C2500b c2500b) {
            this.f6051a = c2500b;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(this.f6051a.f6073v || this.f6051a.f6057e == null || motionEvent.getAction() != 1)) {
                if (this.f6051a.f6057e.isShowing()) {
                    this.f6051a.f6057e.hide();
                } else {
                    this.f6051a.f6057e.show();
                }
            }
            return true;
        }
    }

    public C2500b(Context context) {
        super(context);
    }

    public C2500b(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public C2500b(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @TargetApi(21)
    public C2500b(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* renamed from: a */
    private boolean m6428a(@Nullable Surface surface) {
        if (this.f6056d == null) {
            return false;
        }
        try {
            this.f6056d.setSurface(surface);
            return true;
        } catch (Throwable e) {
            C2625a.m6741b(getContext(), "player", C2626b.f6518I, e);
            Log.d(f6052t, "The MediaPlayer failed", e);
            return false;
        }
    }

    /* renamed from: f */
    private boolean m6431f() {
        return this.f6058f == C2501d.PREPARED || this.f6058f == C2501d.STARTED || this.f6058f == C2501d.PAUSED || this.f6058f == C2501d.PLAYBACK_COMPLETED;
    }

    /* renamed from: g */
    private boolean m6432g() {
        if (this.f6056d == null) {
            return false;
        }
        try {
            this.f6056d.reset();
            return true;
        } catch (Throwable e) {
            C2625a.m6741b(getContext(), "player", C2626b.f6519J, e);
            Log.d(f6052t, "The MediaPlayer failed", e);
            return false;
        }
    }

    private void setVideoState(C2501d c2501d) {
        if (c2501d != this.f6058f) {
            this.f6058f = c2501d;
            if (this.f6054b != null) {
                this.f6054b.mo5600a(c2501d);
            }
        }
    }

    /* renamed from: a */
    public void mo5610a() {
        if (!this.f6070r) {
            mo5613a(false);
        }
    }

    /* renamed from: a */
    public void mo5611a(int i) {
        if (this.f6056d == null || !m6431f()) {
            this.f6063k = i;
        } else if (i < getDuration() && i > 0) {
            this.f6072u = getCurrentPosition();
            this.f6063k = i;
            this.f6056d.seekTo(i);
        }
    }

    /* renamed from: a */
    public void mo5612a(C2389a c2389a) {
        this.f6059g = C2501d.STARTED;
        this.f6074w = c2389a;
        if (this.f6058f == C2501d.STARTED || this.f6058f == C2501d.PREPARED || this.f6058f == C2501d.IDLE || this.f6058f == C2501d.PAUSED || this.f6058f == C2501d.PLAYBACK_COMPLETED) {
            if (this.f6056d == null) {
                setup(this.f6053a);
            } else {
                if (this.f6063k > 0) {
                    this.f6056d.seekTo(this.f6063k);
                }
                this.f6056d.start();
                if (this.f6058f != C2501d.PREPARED || this.f6071s) {
                    setVideoState(C2501d.STARTED);
                }
            }
        }
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }

    /* renamed from: a */
    public void mo5613a(boolean z) {
        this.f6059g = C2501d.PAUSED;
        if (this.f6056d != null) {
            boolean z2 = (this.f6058f == C2501d.PREPARING || this.f6058f == C2501d.PREPARED) ? false : true;
            if (z2) {
                if (z) {
                    this.f6060h = C2501d.PAUSED;
                    this.f6061i = true;
                }
                this.f6056d.pause();
                if (this.f6058f != C2501d.PLAYBACK_COMPLETED) {
                    setVideoState(C2501d.PAUSED);
                    return;
                }
                return;
            }
            return;
        }
        setVideoState(C2501d.IDLE);
    }

    /* renamed from: b */
    public void mo5614b() {
        setVideoState(C2501d.PLAYBACK_COMPLETED);
        mo5615c();
        this.f6063k = 0;
    }

    /* renamed from: c */
    public void mo5615c() {
        this.f6059g = C2501d.IDLE;
        if (this.f6056d != null) {
            int currentPosition = this.f6056d.getCurrentPosition();
            if (currentPosition > 0) {
                this.f6063k = currentPosition;
            }
            this.f6056d.stop();
            m6432g();
            this.f6056d.release();
            this.f6056d = null;
            if (this.f6057e != null) {
                this.f6057e.hide();
                this.f6057e.setEnabled(false);
            }
        }
        setVideoState(C2501d.IDLE);
    }

    @SuppressLint({"NewApi"})
    /* renamed from: d */
    public boolean mo5616d() {
        if (this.f6056d == null || VERSION.SDK_INT < 16) {
            return false;
        }
        try {
            for (TrackInfo trackType : this.f6056d.getTrackInfo()) {
                if (trackType.getTrackType() == 2) {
                    return true;
                }
            }
            return false;
        } catch (Throwable e) {
            Log.e(f6052t, "Couldn't retrieve video information", e);
            return true;
        }
    }

    /* renamed from: e */
    public void mo5617e() {
        if (this.f6056d != null) {
            m6428a(null);
            this.f6056d.setOnBufferingUpdateListener(null);
            this.f6056d.setOnCompletionListener(null);
            this.f6056d.setOnErrorListener(null);
            this.f6056d.setOnInfoListener(null);
            this.f6056d.setOnPreparedListener(null);
            this.f6056d.setOnVideoSizeChangedListener(null);
            this.f6056d.setOnSeekCompleteListener(null);
            m6432g();
            this.f6056d = null;
            setVideoState(C2501d.IDLE);
        }
    }

    public int getCurrentPosition() {
        return (this.f6056d == null || !m6431f()) ? 0 : this.f6056d.getCurrentPosition();
    }

    public int getDuration() {
        return (this.f6056d == null || !m6431f()) ? 0 : this.f6056d.getDuration();
    }

    public long getInitialBufferTime() {
        return this.f6064l;
    }

    public C2389a getStartReason() {
        return this.f6074w;
    }

    public C2501d getState() {
        return this.f6058f;
    }

    public C2501d getTargetState() {
        return this.f6059g;
    }

    public int getVideoHeight() {
        return this.f6066n;
    }

    public int getVideoWidth() {
        return this.f6065m;
    }

    public View getView() {
        return this;
    }

    public float getVolume() {
        return this.f6067o;
    }

    public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.f6056d != null) {
            this.f6056d.pause();
        }
        setVideoState(C2501d.PLAYBACK_COMPLETED);
        mo5611a(0);
        this.f6063k = 0;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        if (this.f6069q <= 0 || getState() != C2501d.STARTED) {
            setVideoState(C2501d.ERROR);
            mo5615c();
        } else {
            this.f6069q--;
            mo5615c();
            mo5612a(this.f6074w);
        }
        return true;
    }

    public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
        boolean z = true;
        switch (i) {
            case 3:
                this.f6071s = true;
                if (this.f6059g != C2501d.STARTED) {
                    return true;
                }
                setVideoState(C2501d.STARTED);
                return true;
            case 701:
                setVideoState(C2501d.BUFFERING);
                break;
            case 702:
                if (this.f6058f == C2501d.PREPARING || this.f6058f == C2501d.PREPARED) {
                    z = false;
                }
                if (z) {
                    setVideoState(C2501d.STARTED);
                    break;
                }
                break;
        }
        return false;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        setVideoState(C2501d.PREPARED);
        if (this.f6068p && !this.f6073v) {
            this.f6057e = new MediaController(getContext());
            this.f6057e.setAnchorView(this.f6062j == null ? this : this.f6062j);
            this.f6057e.setMediaPlayer(this.f6075x);
            this.f6057e.setEnabled(true);
        }
        setRequestedVolume(this.f6067o);
        this.f6065m = mediaPlayer.getVideoWidth();
        this.f6066n = mediaPlayer.getVideoHeight();
        if (this.f6063k > 0) {
            if (this.f6063k >= this.f6056d.getDuration()) {
                this.f6063k = 0;
            }
            this.f6056d.seekTo(this.f6063k);
            this.f6063k = 0;
        }
        if (this.f6059g == C2501d.STARTED) {
            mo5612a(this.f6074w);
        }
    }

    public void onSeekComplete(MediaPlayer mediaPlayer) {
        if (this.f6054b != null) {
            this.f6054b.mo5599a(this.f6072u, this.f6063k);
            this.f6063k = 0;
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        if (this.f6055c == null) {
            this.f6055c = new Surface(surfaceTexture);
        }
        if (m6428a(this.f6055c)) {
            this.f6061i = false;
            if (this.f6058f == C2501d.PAUSED && this.f6060h != C2501d.PAUSED) {
                mo5612a(this.f6074w);
                return;
            }
            return;
        }
        setVideoState(C2501d.ERROR);
        mo5617e();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        m6428a(null);
        if (this.f6055c != null) {
            this.f6055c.release();
            this.f6055c = null;
        }
        if (!this.f6061i) {
            this.f6060h = this.f6068p ? C2501d.STARTED : this.f6058f;
            this.f6061i = true;
        }
        if (this.f6058f != C2501d.PAUSED) {
            mo5613a(false);
        }
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.f6065m = mediaPlayer.getVideoWidth();
        this.f6066n = mediaPlayer.getVideoHeight();
        if (this.f6065m != 0 && this.f6066n != 0) {
            requestLayout();
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (this.f6056d != null) {
            if (this.f6057e != null && this.f6057e.isShowing()) {
                return;
            }
            if (z) {
                this.f6061i = false;
                if (this.f6058f == C2501d.PAUSED && this.f6060h != C2501d.PAUSED) {
                    mo5612a(this.f6074w);
                    return;
                }
                return;
            }
            if (!this.f6061i) {
                this.f6060h = this.f6068p ? C2501d.STARTED : this.f6058f;
                this.f6061i = true;
            }
            if (this.f6058f != C2501d.PAUSED) {
                mo5610a();
            }
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f6052t, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }

    public void setBackgroundPlaybackEnabled(boolean z) {
        this.f6070r = z;
    }

    public void setControlsAnchorView(View view) {
        this.f6062j = view;
        view.setOnTouchListener(new C24993(this));
    }

    public void setForeground(Drawable drawable) {
        if (VERSION.SDK_INT < 24) {
            super.setForeground(drawable);
        } else if (AdInternalSettings.isDebugBuild()) {
            Log.w(f6052t, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }

    public void setFullScreen(boolean z) {
        this.f6068p = z;
        if (this.f6068p && !this.f6073v) {
            setOnTouchListener(new C24982(this));
        }
    }

    public void setRequestedVolume(float f) {
        this.f6067o = f;
        if (this.f6056d != null && this.f6058f != C2501d.PREPARING && this.f6058f != C2501d.IDLE) {
            this.f6056d.setVolume(f, f);
        }
    }

    public void setVideoMPD(@Nullable String str) {
    }

    public void setVideoStateChangeListener(C2393e c2393e) {
        this.f6054b = c2393e;
    }

    public void setup(Uri uri) {
        MediaPlayer mediaPlayer;
        Object e;
        Throwable th;
        AssetFileDescriptor assetFileDescriptor = null;
        this.f6071s = false;
        this.f6053a = uri;
        if (this.f6056d != null) {
            m6432g();
            m6428a(null);
            mediaPlayer = this.f6056d;
            setVideoState(C2501d.IDLE);
        } else {
            mediaPlayer = new MediaPlayer();
        }
        try {
            if (uri.getScheme().equals("asset")) {
                try {
                    AssetFileDescriptor openFd = getContext().getAssets().openFd(uri.getPath().substring(1));
                    try {
                        mediaPlayer.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                        if (openFd != null) {
                            try {
                                openFd.close();
                            } catch (IOException e2) {
                                Log.w(f6052t, "Unable to close" + e2);
                            }
                        }
                    } catch (SecurityException e3) {
                        e = e3;
                        assetFileDescriptor = openFd;
                        try {
                            Log.w(f6052t, "Failed to open assets " + e);
                            setVideoState(C2501d.ERROR);
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e22) {
                                    Log.w(f6052t, "Unable to close" + e22);
                                }
                            }
                            mediaPlayer.setLooping(false);
                            mediaPlayer.setOnBufferingUpdateListener(this);
                            mediaPlayer.setOnCompletionListener(this);
                            mediaPlayer.setOnErrorListener(this);
                            mediaPlayer.setOnInfoListener(this);
                            mediaPlayer.setOnPreparedListener(this);
                            mediaPlayer.setOnVideoSizeChangedListener(this);
                            mediaPlayer.setOnSeekCompleteListener(this);
                            mediaPlayer.prepareAsync();
                            this.f6056d = mediaPlayer;
                            setVideoState(C2501d.PREPARING);
                            setSurfaceTextureListener(this);
                            if (isAvailable()) {
                                onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (assetFileDescriptor != null) {
                                try {
                                    assetFileDescriptor.close();
                                } catch (IOException e4) {
                                    Log.w(f6052t, "Unable to close" + e4);
                                }
                            }
                            throw th;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        assetFileDescriptor = openFd;
                        Log.w(f6052t, "Failed to open assets " + e);
                        setVideoState(C2501d.ERROR);
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        mediaPlayer.setLooping(false);
                        mediaPlayer.setOnBufferingUpdateListener(this);
                        mediaPlayer.setOnCompletionListener(this);
                        mediaPlayer.setOnErrorListener(this);
                        mediaPlayer.setOnInfoListener(this);
                        mediaPlayer.setOnPreparedListener(this);
                        mediaPlayer.setOnVideoSizeChangedListener(this);
                        mediaPlayer.setOnSeekCompleteListener(this);
                        mediaPlayer.prepareAsync();
                        this.f6056d = mediaPlayer;
                        setVideoState(C2501d.PREPARING);
                        setSurfaceTextureListener(this);
                        if (isAvailable()) {
                            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        assetFileDescriptor = openFd;
                        if (assetFileDescriptor != null) {
                            assetFileDescriptor.close();
                        }
                        throw th;
                    }
                } catch (SecurityException e6) {
                    e = e6;
                    Log.w(f6052t, "Failed to open assets " + e);
                    setVideoState(C2501d.ERROR);
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setOnBufferingUpdateListener(this);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnErrorListener(this);
                    mediaPlayer.setOnInfoListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.setOnVideoSizeChangedListener(this);
                    mediaPlayer.setOnSeekCompleteListener(this);
                    mediaPlayer.prepareAsync();
                    this.f6056d = mediaPlayer;
                    setVideoState(C2501d.PREPARING);
                    setSurfaceTextureListener(this);
                    if (isAvailable()) {
                        onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                    }
                } catch (IOException e7) {
                    e = e7;
                    Log.w(f6052t, "Failed to open assets " + e);
                    setVideoState(C2501d.ERROR);
                    if (assetFileDescriptor != null) {
                        assetFileDescriptor.close();
                    }
                    mediaPlayer.setLooping(false);
                    mediaPlayer.setOnBufferingUpdateListener(this);
                    mediaPlayer.setOnCompletionListener(this);
                    mediaPlayer.setOnErrorListener(this);
                    mediaPlayer.setOnInfoListener(this);
                    mediaPlayer.setOnPreparedListener(this);
                    mediaPlayer.setOnVideoSizeChangedListener(this);
                    mediaPlayer.setOnSeekCompleteListener(this);
                    mediaPlayer.prepareAsync();
                    this.f6056d = mediaPlayer;
                    setVideoState(C2501d.PREPARING);
                    setSurfaceTextureListener(this);
                    if (isAvailable()) {
                        onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
                    }
                }
            }
            mediaPlayer.setDataSource(uri.toString());
            mediaPlayer.setLooping(false);
            mediaPlayer.setOnBufferingUpdateListener(this);
            mediaPlayer.setOnCompletionListener(this);
            mediaPlayer.setOnErrorListener(this);
            mediaPlayer.setOnInfoListener(this);
            mediaPlayer.setOnPreparedListener(this);
            mediaPlayer.setOnVideoSizeChangedListener(this);
            mediaPlayer.setOnSeekCompleteListener(this);
            mediaPlayer.prepareAsync();
            this.f6056d = mediaPlayer;
            setVideoState(C2501d.PREPARING);
        } catch (Exception e8) {
            setVideoState(C2501d.ERROR);
            mediaPlayer.release();
            Log.e(f6052t, "Cannot prepare media player with SurfaceTexture: " + e8);
        }
        setSurfaceTextureListener(this);
        if (isAvailable()) {
            onSurfaceTextureAvailable(getSurfaceTexture(), 0, 0);
        }
    }
}
