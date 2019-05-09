// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import java.io.IOException;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.FileInputStream;
import java.io.File;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.SurfaceHolder;
import java.util.Map;
import android.net.Uri;
import android.view.SurfaceHolder$Callback;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.view.SurfaceView;

public class at extends SurfaceView implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, SurfaceHolder$Callback, a
{
    private final String a;
    private Uri b;
    private Map<String, String> c;
    private int d;
    private int e;
    private int f;
    private SurfaceHolder g;
    private MediaPlayer h;
    private int i;
    private int j;
    private int k;
    private int l;
    private MediaPlayer$OnCompletionListener m;
    private MediaPlayer$OnPreparedListener n;
    private int o;
    private MediaPlayer$OnErrorListener p;
    private int q;
    
    public at(final Context context) {
        super(context);
        this.a = "VideoSurfaceView";
        this.e = 0;
        this.f = 0;
        this.g = null;
        this.h = null;
        this.f();
    }
    
    private void a(final boolean b) {
        if (this.h != null) {
            this.h.reset();
            this.h.release();
            this.h = null;
            this.e = 0;
            if (b) {
                this.f = 0;
            }
        }
    }
    
    private void f() {
        this.i = 0;
        this.j = 0;
        this.getHolder().addCallback((SurfaceHolder$Callback)this);
        this.getHolder().setType(3);
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
        this.requestFocus();
        this.e = 0;
        this.f = 0;
    }
    
    private void g() {
        if (this.b == null || this.g == null) {
            return;
        }
        final Intent intent = new Intent("com.android.music.musicservicecommand");
        intent.putExtra("command", "pause");
        this.getContext().sendBroadcast(intent);
        this.a(false);
        try {
            (this.h = new MediaPlayer()).setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
            this.h.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
            this.d = -1;
            this.h.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            this.h.setOnErrorListener((MediaPlayer$OnErrorListener)this);
            this.h.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
            this.o = 0;
            this.h.setDisplay(this.g);
            this.h.setAudioStreamType(3);
            this.h.setScreenOnWhilePlaying(true);
            final FileInputStream fileInputStream = new FileInputStream(new File(this.b.toString()));
            this.h.setDataSource(fileInputStream.getFD());
            fileInputStream.close();
            this.h.prepareAsync();
            this.e = 1;
        }
        catch (IOException ex) {
            CBLogging.c("VideoSurfaceView", "Unable to open content: " + this.b, ex);
            this.e = -1;
            this.f = -1;
            this.onError(this.h, 1, 0);
        }
        catch (IllegalArgumentException ex2) {
            CBLogging.c("VideoSurfaceView", "Unable to open content: " + this.b, ex2);
            this.e = -1;
            this.f = -1;
            this.onError(this.h, 1, 0);
        }
    }
    
    private boolean h() {
        return this.h != null && this.e != -1 && this.e != 0 && this.e != 1;
    }
    
    public void a() {
        if (this.h()) {
            this.h.start();
            this.e = 3;
        }
        this.f = 3;
    }
    
    public void a(final int q) {
        if (this.h()) {
            this.h.seekTo(q);
            this.q = 0;
            return;
        }
        this.q = q;
    }
    
    public void a(final int n, final int n2) {
    }
    
    public void a(final MediaPlayer$OnCompletionListener m) {
        this.m = m;
    }
    
    public void a(final MediaPlayer$OnErrorListener p) {
        this.p = p;
    }
    
    public void a(final MediaPlayer$OnPreparedListener n) {
        this.n = n;
    }
    
    public void a(final Uri uri) {
        this.a(uri, null);
    }
    
    public void a(final Uri b, final Map<String, String> c) {
        this.b = b;
        this.c = c;
        this.q = 0;
        this.g();
        this.requestLayout();
        this.invalidate();
    }
    
    public void b() {
        if (this.h() && this.h.isPlaying()) {
            this.h.pause();
            this.e = 4;
        }
        this.f = 4;
    }
    
    public int c() {
        if (!this.h()) {
            return this.d = -1;
        }
        if (this.d > 0) {
            return this.d;
        }
        return this.d = this.h.getDuration();
    }
    
    public int d() {
        if (this.h()) {
            return this.h.getCurrentPosition();
        }
        return 0;
    }
    
    public boolean e() {
        return this.h() && this.h.isPlaying();
    }
    
    public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int o) {
        this.o = o;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        this.f = 5;
        if (this.e != 5) {
            this.e = 5;
            if (this.m != null) {
                this.m.onCompletion(this.h);
            }
        }
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        CBLogging.a("VideoSurfaceView", "Error: " + n + "," + n2);
        this.e = -1;
        this.f = -1;
        if (this.p == null || this.p.onError(this.h, n, n2)) {}
        return true;
    }
    
    protected void onMeasure(int min, int n) {
        final int defaultSize = getDefaultSize(0, min);
        n = getDefaultSize(0, n);
        if (this.i > 0 && this.j > 0) {
            min = Math.min(n, Math.round(this.j / (float)this.i * defaultSize));
            n = Math.min(defaultSize, Math.round(n * (this.i / (float)this.j)));
        }
        else {
            min = n;
            n = defaultSize;
        }
        this.setMeasuredDimension(n, min);
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.e = 2;
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.n != null) {
            this.n.onPrepared(this.h);
        }
        final int q = this.q;
        if (q != 0) {
            this.a(q);
        }
        if (this.i != 0 && this.j != 0) {
            this.getHolder().setFixedSize(this.i, this.j);
            if (this.k == this.i && this.l == this.j && this.f == 3) {
                this.a();
            }
        }
        else if (this.f == 3) {
            this.a();
        }
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.i != 0 && this.j != 0) {
            this.getHolder().setFixedSize(this.i, this.j);
        }
    }
    
    public void surfaceChanged(final SurfaceHolder surfaceHolder, int n, int k, final int l) {
        final int n2 = 1;
        this.k = k;
        this.l = l;
        if (this.f == 3) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (this.i == k && this.j == l) {
            k = n2;
        }
        else {
            k = 0;
        }
        if (this.h != null && n != 0 && k != 0) {
            if (this.q != 0) {
                this.a(this.q);
            }
            this.a();
        }
    }
    
    public void surfaceCreated(final SurfaceHolder g) {
        this.g = g;
        this.g();
    }
    
    public void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
        this.g = null;
        this.a(true);
    }
}
