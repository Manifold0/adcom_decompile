// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.graphics.SurfaceTexture;
import android.graphics.Matrix;
import android.media.MediaMetadataRetriever;
import java.io.IOException;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.FileInputStream;
import java.io.File;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.view.Surface;
import java.util.Map;
import android.net.Uri;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.view.TextureView;

public class au extends TextureView implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener, a
{
    private final String a;
    private Uri b;
    private Map<String, String> c;
    private int d;
    private int e;
    private int f;
    private Surface g;
    private MediaPlayer h;
    private int i;
    private int j;
    private MediaPlayer$OnCompletionListener k;
    private MediaPlayer$OnPreparedListener l;
    private int m;
    private MediaPlayer$OnErrorListener n;
    private int o;
    
    public au(final Context context) {
        super(context);
        this.a = "VideoTextureView";
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
        this.setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
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
        this.h();
        try {
            (this.h = new MediaPlayer()).setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
            this.h.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
            this.d = -1;
            this.h.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            this.h.setOnErrorListener((MediaPlayer$OnErrorListener)this);
            this.h.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
            this.m = 0;
            final FileInputStream fileInputStream = new FileInputStream(new File(this.b.toString()));
            this.h.setDataSource(fileInputStream.getFD());
            fileInputStream.close();
            this.h.setSurface(this.g);
            this.h.setAudioStreamType(3);
            this.h.setScreenOnWhilePlaying(true);
            this.h.prepareAsync();
            this.e = 1;
        }
        catch (IOException ex) {
            CBLogging.c("VideoTextureView", "Unable to open content: " + this.b, ex);
            this.e = -1;
            this.f = -1;
            this.onError(this.h, 1, 0);
        }
        catch (IllegalArgumentException ex2) {
            CBLogging.c("VideoTextureView", "Unable to open content: " + this.b, ex2);
            this.e = -1;
            this.f = -1;
            this.onError(this.h, 1, 0);
        }
    }
    
    private void h() {
        try {
            final MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(this.b.toString());
            final String metadata = mediaMetadataRetriever.extractMetadata(19);
            final String metadata2 = mediaMetadataRetriever.extractMetadata(18);
            this.j = Integer.parseInt(metadata);
            this.i = Integer.parseInt(metadata2);
        }
        catch (Exception ex) {
            CBLogging.c("play video", "read size error", ex);
        }
    }
    
    private boolean i() {
        return this.h != null && this.e != -1 && this.e != 0 && this.e != 1;
    }
    
    public void a() {
        if (this.i()) {
            this.h.start();
            this.e = 3;
        }
        this.f = 3;
    }
    
    public void a(final int o) {
        if (this.i()) {
            this.h.seekTo(o);
            this.o = 0;
            return;
        }
        this.o = o;
    }
    
    public void a(final int n, final int n2) {
        if (this.i == 0 || this.j == 0 || n == 0 || n2 == 0) {
            return;
        }
        final float min = Math.min(n / (float)this.i, n2 / (float)this.j);
        final float n3 = (float)this.i;
        final float n4 = (float)this.j;
        final Matrix transform = new Matrix();
        transform.setScale(n3 * min / n, min * n4 / n2, n / 2.0f, n2 / 2.0f);
        this.setTransform(transform);
    }
    
    public void a(final MediaPlayer$OnCompletionListener k) {
        this.k = k;
    }
    
    public void a(final MediaPlayer$OnErrorListener n) {
        this.n = n;
    }
    
    public void a(final MediaPlayer$OnPreparedListener l) {
        this.l = l;
    }
    
    public void a(final Uri uri) {
        this.a(uri, null);
    }
    
    public void a(final Uri b, final Map<String, String> c) {
        this.b = b;
        this.c = c;
        this.o = 0;
        this.g();
        this.requestLayout();
        this.invalidate();
    }
    
    public void b() {
        if (this.i() && this.h.isPlaying()) {
            this.h.pause();
            this.e = 4;
        }
        this.f = 4;
    }
    
    public int c() {
        if (!this.i()) {
            return this.d = -1;
        }
        if (this.d > 0) {
            return this.d;
        }
        return this.d = this.h.getDuration();
    }
    
    public int d() {
        if (this.i()) {
            return this.h.getCurrentPosition();
        }
        return 0;
    }
    
    public boolean e() {
        return this.i() && this.h.isPlaying();
    }
    
    public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int m) {
        this.m = m;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        this.f = 5;
        if (this.e != 5) {
            this.e = 5;
            if (this.k != null) {
                this.k.onCompletion(this.h);
            }
        }
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        CBLogging.a("VideoTextureView", "Error: " + n + "," + n2);
        if (n == 100) {
            this.g();
        }
        else {
            this.e = -1;
            this.f = -1;
            if (this.n != null && this.n.onError(this.h, n, n2)) {
                return true;
            }
        }
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.e = 2;
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.l != null) {
            this.l.onPrepared(this.h);
        }
        final int o = this.o;
        if (o != 0) {
            this.a(o);
        }
        if (this.f == 3) {
            this.a();
        }
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        this.g = new Surface(surfaceTexture);
        this.g();
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        this.g = null;
        this.a(true);
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, int n, final int n2) {
        if (this.f == 3) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (this.h != null && n != 0) {
            if (this.o != 0) {
                this.a(this.o);
            }
            this.a();
        }
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.i = mediaPlayer.getVideoWidth();
        this.j = mediaPlayer.getVideoHeight();
        if (this.i != 0 && this.j != 0) {
            this.a(this.getWidth(), this.getHeight());
        }
    }
}
