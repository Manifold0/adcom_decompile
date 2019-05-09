package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.view.Surface;
import android.view.TextureView;
import android.view.TextureView.SurfaceTextureListener;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.av.C1421a;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class au extends TextureView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, SurfaceTextureListener, C1421a {
    /* renamed from: a */
    private final String f3084a = "VideoTextureView";
    /* renamed from: b */
    private Uri f3085b;
    /* renamed from: c */
    private Map<String, String> f3086c;
    /* renamed from: d */
    private int f3087d;
    /* renamed from: e */
    private int f3088e = 0;
    /* renamed from: f */
    private int f3089f = 0;
    /* renamed from: g */
    private Surface f3090g = null;
    /* renamed from: h */
    private MediaPlayer f3091h = null;
    /* renamed from: i */
    private int f3092i;
    /* renamed from: j */
    private int f3093j;
    /* renamed from: k */
    private OnCompletionListener f3094k;
    /* renamed from: l */
    private OnPreparedListener f3095l;
    /* renamed from: m */
    private int f3096m;
    /* renamed from: n */
    private OnErrorListener f3097n;
    /* renamed from: o */
    private int f3098o;

    public au(Context context) {
        super(context);
        m3444f();
    }

    /* renamed from: f */
    private void m3444f() {
        this.f3092i = 0;
        this.f3093j = 0;
        setSurfaceTextureListener(this);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f3088e = 0;
        this.f3089f = 0;
    }

    /* renamed from: a */
    public void mo4291a(Uri uri) {
        m3455a(uri, null);
    }

    /* renamed from: a */
    public void mo4287a(int i, int i2) {
        if (this.f3092i != 0 && this.f3093j != 0 && i != 0 && i2 != 0) {
            float min = Math.min(((float) i) / ((float) this.f3092i), ((float) i2) / ((float) this.f3093j));
            float f = ((float) this.f3092i) * min;
            min *= (float) this.f3093j;
            Matrix matrix = new Matrix();
            matrix.setScale(f / ((float) i), min / ((float) i2), ((float) i) / 2.0f, ((float) i2) / 2.0f);
            setTransform(matrix);
        }
    }

    /* renamed from: a */
    public void m3455a(Uri uri, Map<String, String> map) {
        this.f3085b = uri;
        this.f3086c = map;
        this.f3098o = 0;
        m3445g();
        requestLayout();
        invalidate();
    }

    /* renamed from: g */
    private void m3445g() {
        if (this.f3085b != null && this.f3090g != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra(String.COMMAND, "pause");
            getContext().sendBroadcast(intent);
            m3443a(false);
            m3446h();
            try {
                this.f3091h = new MediaPlayer();
                this.f3091h.setOnPreparedListener(this);
                this.f3091h.setOnVideoSizeChangedListener(this);
                this.f3087d = -1;
                this.f3091h.setOnCompletionListener(this);
                this.f3091h.setOnErrorListener(this);
                this.f3091h.setOnBufferingUpdateListener(this);
                this.f3096m = 0;
                FileInputStream fileInputStream = new FileInputStream(new File(this.f3085b.toString()));
                this.f3091h.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
                this.f3091h.setSurface(this.f3090g);
                this.f3091h.setAudioStreamType(3);
                this.f3091h.setScreenOnWhilePlaying(true);
                this.f3091h.prepareAsync();
                this.f3088e = 1;
            } catch (Throwable e) {
                CBLogging.m3102c("VideoTextureView", "Unable to open content: " + this.f3085b, e);
                this.f3088e = -1;
                this.f3089f = -1;
                onError(this.f3091h, 1, 0);
            } catch (Throwable e2) {
                CBLogging.m3102c("VideoTextureView", "Unable to open content: " + this.f3085b, e2);
                this.f3088e = -1;
                this.f3089f = -1;
                onError(this.f3091h, 1, 0);
            }
        }
    }

    /* renamed from: h */
    private void m3446h() {
        try {
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(this.f3085b.toString());
            String extractMetadata = mediaMetadataRetriever.extractMetadata(19);
            String extractMetadata2 = mediaMetadataRetriever.extractMetadata(18);
            this.f3093j = Integer.parseInt(extractMetadata);
            this.f3092i = Integer.parseInt(extractMetadata2);
        } catch (Throwable e) {
            CBLogging.m3102c("play video", "read size error", e);
        }
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        this.f3092i = mp.getVideoWidth();
        this.f3093j = mp.getVideoHeight();
        if (this.f3092i != 0 && this.f3093j != 0) {
            mo4287a(getWidth(), getHeight());
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.f3088e = 2;
        this.f3092i = mp.getVideoWidth();
        this.f3093j = mp.getVideoHeight();
        if (this.f3095l != null) {
            this.f3095l.onPrepared(this.f3091h);
        }
        int i = this.f3098o;
        if (i != 0) {
            mo4286a(i);
        }
        if (this.f3089f == 3) {
            mo4285a();
        }
    }

    public void onCompletion(MediaPlayer mp) {
        this.f3089f = 5;
        if (this.f3088e != 5) {
            this.f3088e = 5;
            if (this.f3094k != null) {
                this.f3094k.onCompletion(this.f3091h);
            }
        }
    }

    public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
        CBLogging.m3097a("VideoTextureView", "Error: " + framework_err + "," + impl_err);
        if (framework_err == 100) {
            m3445g();
        } else {
            this.f3088e = -1;
            this.f3089f = -1;
            if (this.f3097n != null && this.f3097n.onError(this.f3091h, framework_err, impl_err)) {
            }
        }
        return true;
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.f3096m = percent;
    }

    /* renamed from: a */
    public void mo4290a(OnPreparedListener onPreparedListener) {
        this.f3095l = onPreparedListener;
    }

    /* renamed from: a */
    public void mo4288a(OnCompletionListener onCompletionListener) {
        this.f3094k = onCompletionListener;
    }

    /* renamed from: a */
    public void mo4289a(OnErrorListener onErrorListener) {
        this.f3097n = onErrorListener;
    }

    /* renamed from: a */
    private void m3443a(boolean z) {
        if (this.f3091h != null) {
            this.f3091h.reset();
            this.f3091h.release();
            this.f3091h = null;
            this.f3088e = 0;
            if (z) {
                this.f3089f = 0;
            }
        }
    }

    /* renamed from: a */
    public void mo4285a() {
        if (m3447i()) {
            this.f3091h.start();
            this.f3088e = 3;
        }
        this.f3089f = 3;
    }

    /* renamed from: b */
    public void mo4292b() {
        if (m3447i() && this.f3091h.isPlaying()) {
            this.f3091h.pause();
            this.f3088e = 4;
        }
        this.f3089f = 4;
    }

    /* renamed from: c */
    public int mo4293c() {
        if (!m3447i()) {
            this.f3087d = -1;
            return this.f3087d;
        } else if (this.f3087d > 0) {
            return this.f3087d;
        } else {
            this.f3087d = this.f3091h.getDuration();
            return this.f3087d;
        }
    }

    /* renamed from: d */
    public int mo4294d() {
        if (m3447i()) {
            return this.f3091h.getCurrentPosition();
        }
        return 0;
    }

    /* renamed from: a */
    public void mo4286a(int i) {
        if (m3447i()) {
            this.f3091h.seekTo(i);
            this.f3098o = 0;
            return;
        }
        this.f3098o = i;
    }

    /* renamed from: e */
    public boolean mo4295e() {
        return m3447i() && this.f3091h.isPlaying();
    }

    /* renamed from: i */
    private boolean m3447i() {
        return (this.f3091h == null || this.f3088e == -1 || this.f3088e == 0 || this.f3088e == 1) ? false : true;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int w, int h) {
        this.f3090g = new Surface(surface);
        m3445g();
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        this.f3090g = null;
        m3443a(true);
        return true;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int w, int h) {
        Object obj = this.f3089f == 3 ? 1 : null;
        if (this.f3091h != null && obj != null) {
            if (this.f3098o != 0) {
                mo4286a(this.f3098o);
            }
            mo4285a();
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
    }
}
