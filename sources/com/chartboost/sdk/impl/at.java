package com.chartboost.sdk.impl;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.av.C1421a;
import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class at extends SurfaceView implements OnBufferingUpdateListener, OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, C1421a {
    /* renamed from: a */
    private final String f3067a = "VideoSurfaceView";
    /* renamed from: b */
    private Uri f3068b;
    /* renamed from: c */
    private Map<String, String> f3069c;
    /* renamed from: d */
    private int f3070d;
    /* renamed from: e */
    private int f3071e = 0;
    /* renamed from: f */
    private int f3072f = 0;
    /* renamed from: g */
    private SurfaceHolder f3073g = null;
    /* renamed from: h */
    private MediaPlayer f3074h = null;
    /* renamed from: i */
    private int f3075i;
    /* renamed from: j */
    private int f3076j;
    /* renamed from: k */
    private int f3077k;
    /* renamed from: l */
    private int f3078l;
    /* renamed from: m */
    private OnCompletionListener f3079m;
    /* renamed from: n */
    private OnPreparedListener f3080n;
    /* renamed from: o */
    private int f3081o;
    /* renamed from: p */
    private OnErrorListener f3082p;
    /* renamed from: q */
    private int f3083q;

    public at(Context context) {
        super(context);
        m3428f();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int i;
        int defaultSize = getDefaultSize(0, widthMeasureSpec);
        int defaultSize2 = getDefaultSize(0, heightMeasureSpec);
        if (this.f3075i <= 0 || this.f3076j <= 0) {
            i = defaultSize2;
            defaultSize2 = defaultSize;
        } else {
            i = Math.min(defaultSize2, Math.round((((float) this.f3076j) / ((float) this.f3075i)) * ((float) defaultSize)));
            defaultSize2 = Math.min(defaultSize, Math.round(((float) defaultSize2) * (((float) this.f3075i) / ((float) this.f3076j))));
        }
        setMeasuredDimension(defaultSize2, i);
    }

    /* renamed from: f */
    private void m3428f() {
        this.f3075i = 0;
        this.f3076j = 0;
        getHolder().addCallback(this);
        getHolder().setType(3);
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.f3071e = 0;
        this.f3072f = 0;
    }

    /* renamed from: a */
    public void mo4291a(Uri uri) {
        m3438a(uri, null);
    }

    /* renamed from: a */
    public void m3438a(Uri uri, Map<String, String> map) {
        this.f3068b = uri;
        this.f3069c = map;
        this.f3083q = 0;
        m3429g();
        requestLayout();
        invalidate();
    }

    /* renamed from: g */
    private void m3429g() {
        if (this.f3068b != null && this.f3073g != null) {
            Intent intent = new Intent("com.android.music.musicservicecommand");
            intent.putExtra(String.COMMAND, "pause");
            getContext().sendBroadcast(intent);
            m3427a(false);
            try {
                this.f3074h = new MediaPlayer();
                this.f3074h.setOnPreparedListener(this);
                this.f3074h.setOnVideoSizeChangedListener(this);
                this.f3070d = -1;
                this.f3074h.setOnCompletionListener(this);
                this.f3074h.setOnErrorListener(this);
                this.f3074h.setOnBufferingUpdateListener(this);
                this.f3081o = 0;
                this.f3074h.setDisplay(this.f3073g);
                this.f3074h.setAudioStreamType(3);
                this.f3074h.setScreenOnWhilePlaying(true);
                FileInputStream fileInputStream = new FileInputStream(new File(this.f3068b.toString()));
                this.f3074h.setDataSource(fileInputStream.getFD());
                fileInputStream.close();
                this.f3074h.prepareAsync();
                this.f3071e = 1;
            } catch (Throwable e) {
                CBLogging.m3102c("VideoSurfaceView", "Unable to open content: " + this.f3068b, e);
                this.f3071e = -1;
                this.f3072f = -1;
                onError(this.f3074h, 1, 0);
            } catch (Throwable e2) {
                CBLogging.m3102c("VideoSurfaceView", "Unable to open content: " + this.f3068b, e2);
                this.f3071e = -1;
                this.f3072f = -1;
                onError(this.f3074h, 1, 0);
            }
        }
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        this.f3075i = mp.getVideoWidth();
        this.f3076j = mp.getVideoHeight();
        if (this.f3075i != 0 && this.f3076j != 0) {
            getHolder().setFixedSize(this.f3075i, this.f3076j);
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.f3071e = 2;
        this.f3075i = mp.getVideoWidth();
        this.f3076j = mp.getVideoHeight();
        if (this.f3080n != null) {
            this.f3080n.onPrepared(this.f3074h);
        }
        int i = this.f3083q;
        if (i != 0) {
            mo4286a(i);
        }
        if (this.f3075i != 0 && this.f3076j != 0) {
            getHolder().setFixedSize(this.f3075i, this.f3076j);
            if (this.f3077k == this.f3075i && this.f3078l == this.f3076j && this.f3072f == 3) {
                mo4285a();
            }
        } else if (this.f3072f == 3) {
            mo4285a();
        }
    }

    public void onCompletion(MediaPlayer mp) {
        this.f3072f = 5;
        if (this.f3071e != 5) {
            this.f3071e = 5;
            if (this.f3079m != null) {
                this.f3079m.onCompletion(this.f3074h);
            }
        }
    }

    public boolean onError(MediaPlayer mp, int framework_err, int impl_err) {
        CBLogging.m3097a("VideoSurfaceView", "Error: " + framework_err + "," + impl_err);
        this.f3071e = -1;
        this.f3072f = -1;
        return (this.f3082p == null || this.f3082p.onError(this.f3074h, framework_err, impl_err)) ? true : true;
    }

    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        this.f3081o = percent;
    }

    /* renamed from: a */
    public void mo4290a(OnPreparedListener onPreparedListener) {
        this.f3080n = onPreparedListener;
    }

    /* renamed from: a */
    public void mo4288a(OnCompletionListener onCompletionListener) {
        this.f3079m = onCompletionListener;
    }

    /* renamed from: a */
    public void mo4289a(OnErrorListener onErrorListener) {
        this.f3082p = onErrorListener;
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        Object obj = 1;
        this.f3077k = w;
        this.f3078l = h;
        Object obj2 = this.f3072f == 3 ? 1 : null;
        if (!(this.f3075i == w && this.f3076j == h)) {
            obj = null;
        }
        if (this.f3074h != null && obj2 != null && r1 != null) {
            if (this.f3083q != 0) {
                mo4286a(this.f3083q);
            }
            mo4285a();
        }
    }

    public void surfaceCreated(SurfaceHolder holder) {
        this.f3073g = holder;
        m3429g();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        this.f3073g = null;
        m3427a(true);
    }

    /* renamed from: a */
    private void m3427a(boolean z) {
        if (this.f3074h != null) {
            this.f3074h.reset();
            this.f3074h.release();
            this.f3074h = null;
            this.f3071e = 0;
            if (z) {
                this.f3072f = 0;
            }
        }
    }

    /* renamed from: a */
    public void mo4285a() {
        if (m3430h()) {
            this.f3074h.start();
            this.f3071e = 3;
        }
        this.f3072f = 3;
    }

    /* renamed from: b */
    public void mo4292b() {
        if (m3430h() && this.f3074h.isPlaying()) {
            this.f3074h.pause();
            this.f3071e = 4;
        }
        this.f3072f = 4;
    }

    /* renamed from: c */
    public int mo4293c() {
        if (!m3430h()) {
            this.f3070d = -1;
            return this.f3070d;
        } else if (this.f3070d > 0) {
            return this.f3070d;
        } else {
            this.f3070d = this.f3074h.getDuration();
            return this.f3070d;
        }
    }

    /* renamed from: d */
    public int mo4294d() {
        if (m3430h()) {
            return this.f3074h.getCurrentPosition();
        }
        return 0;
    }

    /* renamed from: a */
    public void mo4286a(int i) {
        if (m3430h()) {
            this.f3074h.seekTo(i);
            this.f3083q = 0;
            return;
        }
        this.f3083q = i;
    }

    /* renamed from: e */
    public boolean mo4295e() {
        return m3430h() && this.f3074h.isPlaying();
    }

    /* renamed from: h */
    private boolean m3430h() {
        return (this.f3074h == null || this.f3071e == -1 || this.f3071e == 0 || this.f3071e == 1) ? false : true;
    }

    /* renamed from: a */
    public void mo4287a(int i, int i2) {
    }
}
