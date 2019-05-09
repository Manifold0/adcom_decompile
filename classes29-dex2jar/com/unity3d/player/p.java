// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.KeyEvent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import java.io.IOException;
import java.io.FileInputStream;
import android.net.Uri;
import android.util.Log;
import android.view.WindowManager;
import android.view.View;
import android.widget.MediaController;
import android.media.MediaPlayer;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.content.Context;
import android.widget.MediaController$MediaPlayerControl;
import android.view.SurfaceHolder$Callback;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.widget.FrameLayout;

public final class p extends FrameLayout implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, SurfaceHolder$Callback, MediaController$MediaPlayerControl
{
    private static boolean a;
    private final Context b;
    private final SurfaceView c;
    private final SurfaceHolder d;
    private final String e;
    private final int f;
    private final int g;
    private final boolean h;
    private final long i;
    private final long j;
    private final FrameLayout k;
    private final Display l;
    private int m;
    private int n;
    private int o;
    private int p;
    private MediaPlayer q;
    private MediaController r;
    private boolean s;
    private boolean t;
    private int u;
    private boolean v;
    private boolean w;
    private a x;
    private b y;
    private volatile int z;
    
    static {
        p.a = false;
    }
    
    protected p(final Context b, final String e, final int backgroundColor, final int f, final int g, final boolean h, final long i, final long j, final a x) {
        super(b);
        this.s = false;
        this.t = false;
        this.u = 0;
        this.v = false;
        this.w = false;
        this.z = 0;
        this.x = x;
        this.b = b;
        this.k = this;
        this.c = new SurfaceView(b);
        (this.d = this.c.getHolder()).addCallback((SurfaceHolder$Callback)this);
        this.k.setBackgroundColor(backgroundColor);
        this.k.addView((View)this.c);
        this.l = ((WindowManager)this.b.getSystemService("window")).getDefaultDisplay();
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.j = j;
        if (com.unity3d.player.p.a) {
            b("fileName: " + this.e);
        }
        if (com.unity3d.player.p.a) {
            b("backgroundColor: " + backgroundColor);
        }
        if (com.unity3d.player.p.a) {
            b("controlMode: " + this.f);
        }
        if (com.unity3d.player.p.a) {
            b("scalingMode: " + this.g);
        }
        if (com.unity3d.player.p.a) {
            b("isURL: " + this.h);
        }
        if (com.unity3d.player.p.a) {
            b("videoOffset: " + this.i);
        }
        if (com.unity3d.player.p.a) {
            b("videoLength: " + this.j);
        }
        this.setFocusable(true);
        this.setFocusableInTouchMode(true);
    }
    
    private void a(final int z) {
        this.z = z;
        if (this.x != null) {
            this.x.a(this.z);
        }
    }
    
    private static void b(final String s) {
        Log.i("Video", "VideoPlayer: " + s);
    }
    
    private void c() {
        if (this.q != null) {
            this.q.setDisplay(this.d);
            if (!this.v) {
                if (com.unity3d.player.p.a) {
                    b("Resuming playback");
                }
                this.q.start();
            }
            return;
        }
        while (true) {
            this.a(0);
            this.doCleanUp();
            while (true) {
                try {
                    this.q = new MediaPlayer();
                    if (this.h) {
                        this.q.setDataSource(this.b, Uri.parse(this.e));
                        this.q.setDisplay(this.d);
                        this.q.setScreenOnWhilePlaying(true);
                        this.q.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
                        this.q.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
                        this.q.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
                        this.q.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
                        this.q.setAudioStreamType(3);
                        this.q.prepareAsync();
                        this.y = new b(this);
                        new Thread(this.y).start();
                        return;
                    }
                }
                catch (Exception ex) {
                    if (com.unity3d.player.p.a) {
                        b("error: " + ex.getMessage() + ex);
                    }
                    this.a(2);
                    return;
                }
                if (this.j != 0L) {
                    final FileInputStream fileInputStream = new FileInputStream(this.e);
                    this.q.setDataSource(fileInputStream.getFD(), this.i, this.j);
                    fileInputStream.close();
                    continue;
                }
                final AssetManager assets = this.getResources().getAssets();
                try {
                    final AssetFileDescriptor openFd = assets.openFd(this.e);
                    this.q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                    continue;
                }
                catch (IOException ex2) {
                    final FileInputStream fileInputStream2 = new FileInputStream(this.e);
                    this.q.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                    continue;
                }
                continue;
            }
        }
    }
    
    private void d() {
        if (!this.isPlaying()) {
            this.a(1);
            if (com.unity3d.player.p.a) {
                b("startVideoPlayback");
            }
            this.updateVideoLayout();
            if (!this.v) {
                this.start();
            }
        }
    }
    
    public final void CancelOnPrepare() {
        this.a(2);
    }
    
    final boolean a() {
        return this.v;
    }
    
    public final boolean canPause() {
        return true;
    }
    
    public final boolean canSeekBackward() {
        return true;
    }
    
    public final boolean canSeekForward() {
        return true;
    }
    
    protected final void destroyPlayer() {
        if (com.unity3d.player.p.a) {
            b("destroyPlayer");
        }
        if (!this.v) {
            this.pause();
        }
        this.doCleanUp();
    }
    
    protected final void doCleanUp() {
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.q != null) {
            this.q.release();
            this.q = null;
        }
        this.o = 0;
        this.p = 0;
        this.t = false;
        this.s = false;
    }
    
    public final int getBufferPercentage() {
        if (this.h) {
            return this.u;
        }
        return 100;
    }
    
    public final int getCurrentPosition() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getCurrentPosition();
    }
    
    public final int getDuration() {
        if (this.q == null) {
            return 0;
        }
        return this.q.getDuration();
    }
    
    public final boolean isPlaying() {
        boolean b;
        if (this.t && this.s) {
            b = true;
        }
        else {
            b = false;
        }
        if (this.q == null) {
            if (b) {
                return false;
            }
        }
        else if (!this.q.isPlaying() && b) {
            return false;
        }
        return true;
    }
    
    public final void onBufferingUpdate(final MediaPlayer mediaPlayer, final int u) {
        if (com.unity3d.player.p.a) {
            b("onBufferingUpdate percent:" + u);
        }
        this.u = u;
    }
    
    public final void onCompletion(final MediaPlayer mediaPlayer) {
        if (com.unity3d.player.p.a) {
            b("onCompletion called");
        }
        this.destroyPlayer();
        this.a(3);
    }
    
    public final boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4 || (this.f == 2 && n != 0 && !keyEvent.isSystem())) {
            this.destroyPlayer();
            this.a(3);
            return true;
        }
        if (this.r != null) {
            return this.r.onKeyDown(n, keyEvent);
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public final void onPrepared(final MediaPlayer mediaPlayer) {
        if (com.unity3d.player.p.a) {
            b("onPrepared called");
        }
        if (this.y != null) {
            this.y.a();
            this.y = null;
        }
        if (this.f == 0 || this.f == 1) {
            (this.r = new MediaController(this.b)).setMediaPlayer((MediaController$MediaPlayerControl)this);
            this.r.setAnchorView((View)this);
            this.r.setEnabled(true);
            this.r.show();
        }
        this.t = true;
        if (this.t && this.s) {
            this.d();
        }
    }
    
    public final boolean onTouchEvent(final MotionEvent motionEvent) {
        final int action = motionEvent.getAction();
        if (this.f == 2 && (action & 0xFF) == 0x0) {
            this.destroyPlayer();
            this.a(3);
            return true;
        }
        if (this.r != null) {
            return this.r.onTouchEvent(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }
    
    public final void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int o, final int p3) {
        if (com.unity3d.player.p.a) {
            b("onVideoSizeChanged called " + o + "x" + p3);
        }
        if (o == 0 || p3 == 0) {
            if (com.unity3d.player.p.a) {
                b("invalid video width(" + o + ") or height(" + p3 + ")");
            }
        }
        else {
            this.s = true;
            this.o = o;
            this.p = p3;
            if (this.t && this.s) {
                this.d();
            }
        }
    }
    
    public final void pause() {
        if (this.q == null) {
            return;
        }
        if (this.w) {
            this.q.pause();
        }
        this.v = true;
    }
    
    public final void seekTo(final int n) {
        if (this.q == null) {
            return;
        }
        this.q.seekTo(n);
    }
    
    public final void start() {
        if (com.unity3d.player.p.a) {
            b("Start");
        }
        if (this.q == null) {
            return;
        }
        if (this.w) {
            this.q.start();
        }
        this.v = false;
    }
    
    public final void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int m, final int n2) {
        if (com.unity3d.player.p.a) {
            b("surfaceChanged called " + n + " " + m + "x" + n2);
        }
        if (this.m != m || this.n != n2) {
            this.m = m;
            this.n = n2;
            if (this.w) {
                this.updateVideoLayout();
            }
        }
    }
    
    public final void surfaceCreated(final SurfaceHolder surfaceHolder) {
        if (com.unity3d.player.p.a) {
            b("surfaceCreated called");
        }
        this.w = true;
        this.c();
    }
    
    public final void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
        if (com.unity3d.player.p.a) {
            b("surfaceDestroyed called");
        }
        this.w = false;
    }
    
    protected final void updateVideoLayout() {
        if (com.unity3d.player.p.a) {
            b("updateVideoLayout");
        }
        if (this.q != null) {
            if (this.m == 0 || this.n == 0) {
                final WindowManager windowManager = (WindowManager)this.b.getSystemService("window");
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                this.m = displayMetrics.widthPixels;
                this.n = displayMetrics.heightPixels;
            }
            final int m = this.m;
            final int n = this.n;
            int p;
            int o;
            if (this.s) {
                final float n2 = this.o / (float)this.p;
                final float n3 = this.m / (float)this.n;
                if (this.g == 1) {
                    if (n3 <= n2) {
                        p = (int)(this.m / n2);
                        o = m;
                    }
                    else {
                        o = (int)(this.n * n2);
                        p = n;
                    }
                }
                else if (this.g == 2) {
                    if (n3 >= n2) {
                        p = (int)(this.m / n2);
                        o = m;
                    }
                    else {
                        o = (int)(this.n * n2);
                        p = n;
                    }
                }
                else {
                    p = n;
                    o = m;
                    if (this.g == 0) {
                        o = this.o;
                        p = this.p;
                    }
                }
            }
            else {
                p = n;
                o = m;
                if (com.unity3d.player.p.a) {
                    b("updateVideoLayout: Video size is not known yet");
                    p = n;
                    o = m;
                }
            }
            if (this.m != o || this.n != p) {
                if (com.unity3d.player.p.a) {
                    b("frameWidth = " + o + "; frameHeight = " + p);
                }
                this.k.updateViewLayout((View)this.c, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(o, p, 17));
            }
        }
    }
    
    public interface a
    {
        void a(final int p0);
    }
    
    public final class b implements Runnable
    {
        private p b;
        private boolean c;
        
        public b(final p b) {
            this.b = b;
            this.c = false;
        }
        
        public final void a() {
            this.c = true;
        }
        
        @Override
        public final void run() {
            while (true) {
                try {
                    Thread.sleep(5000L);
                    if (!this.c) {
                        if (com.unity3d.player.p.a) {
                            b("Stopping the video player due to timeout.");
                        }
                        this.b.CancelOnPrepare();
                    }
                }
                catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                    continue;
                }
                break;
            }
        }
    }
}
