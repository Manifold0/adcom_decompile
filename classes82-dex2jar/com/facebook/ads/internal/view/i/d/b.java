// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.d;

import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.graphics.drawable.Drawable;
import android.graphics.SurfaceTexture;
import android.annotation.SuppressLint;
import android.media.MediaPlayer$TrackInfo;
import android.os.Build$VERSION;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.MediaController$MediaPlayerControl;
import com.facebook.ads.internal.view.i.a.a;
import android.view.View;
import android.widget.MediaController;
import android.support.annotation.Nullable;
import android.media.MediaPlayer;
import android.view.Surface;
import android.net.Uri;
import android.annotation.TargetApi;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnSeekCompleteListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;
import android.view.TextureView;

@TargetApi(14)
public class b extends TextureView implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnInfoListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnSeekCompleteListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener, c
{
    private static final String t;
    private Uri a;
    private e b;
    private Surface c;
    @Nullable
    private MediaPlayer d;
    private MediaController e;
    private d f;
    private d g;
    private d h;
    private boolean i;
    private View j;
    private int k;
    private long l;
    private int m;
    private int n;
    private float o;
    private boolean p;
    private int q;
    private boolean r;
    private boolean s;
    private int u;
    private boolean v;
    private a w;
    private final MediaController$MediaPlayerControl x;
    
    static {
        t = b.class.getSimpleName();
    }
    
    public b(final Context context) {
        super(context);
        this.f = com.facebook.ads.internal.view.i.d.d.a;
        this.g = com.facebook.ads.internal.view.i.d.d.a;
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = false;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.o = 1.0f;
        this.p = false;
        this.q = 3;
        this.r = false;
        this.s = false;
        this.u = 0;
        this.v = false;
        this.w = com.facebook.ads.internal.view.i.a.a.a;
        this.x = (MediaController$MediaPlayerControl)new MediaController$MediaPlayerControl() {
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
                if (com.facebook.ads.internal.view.i.d.b.this.d != null) {
                    return com.facebook.ads.internal.view.i.d.b.this.d.getAudioSessionId();
                }
                return 0;
            }
            
            public int getBufferPercentage() {
                return 0;
            }
            
            public int getCurrentPosition() {
                return com.facebook.ads.internal.view.i.d.b.this.getCurrentPosition();
            }
            
            public int getDuration() {
                return com.facebook.ads.internal.view.i.d.b.this.getDuration();
            }
            
            public boolean isPlaying() {
                return com.facebook.ads.internal.view.i.d.b.this.d != null && com.facebook.ads.internal.view.i.d.b.this.d.isPlaying();
            }
            
            public void pause() {
                com.facebook.ads.internal.view.i.d.b.this.a(true);
            }
            
            public void seekTo(final int n) {
                com.facebook.ads.internal.view.i.d.b.this.a(n);
            }
            
            public void start() {
                com.facebook.ads.internal.view.i.d.b.this.a(com.facebook.ads.internal.view.i.a.a.b);
            }
        };
    }
    
    public b(final Context context, final AttributeSet set) {
        super(context, set);
        this.f = com.facebook.ads.internal.view.i.d.d.a;
        this.g = com.facebook.ads.internal.view.i.d.d.a;
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = false;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.o = 1.0f;
        this.p = false;
        this.q = 3;
        this.r = false;
        this.s = false;
        this.u = 0;
        this.v = false;
        this.w = com.facebook.ads.internal.view.i.a.a.a;
        this.x = (MediaController$MediaPlayerControl)new MediaController$MediaPlayerControl() {
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
                if (com.facebook.ads.internal.view.i.d.b.this.d != null) {
                    return com.facebook.ads.internal.view.i.d.b.this.d.getAudioSessionId();
                }
                return 0;
            }
            
            public int getBufferPercentage() {
                return 0;
            }
            
            public int getCurrentPosition() {
                return com.facebook.ads.internal.view.i.d.b.this.getCurrentPosition();
            }
            
            public int getDuration() {
                return com.facebook.ads.internal.view.i.d.b.this.getDuration();
            }
            
            public boolean isPlaying() {
                return com.facebook.ads.internal.view.i.d.b.this.d != null && com.facebook.ads.internal.view.i.d.b.this.d.isPlaying();
            }
            
            public void pause() {
                com.facebook.ads.internal.view.i.d.b.this.a(true);
            }
            
            public void seekTo(final int n) {
                com.facebook.ads.internal.view.i.d.b.this.a(n);
            }
            
            public void start() {
                com.facebook.ads.internal.view.i.d.b.this.a(com.facebook.ads.internal.view.i.a.a.b);
            }
        };
    }
    
    public b(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.f = com.facebook.ads.internal.view.i.d.d.a;
        this.g = com.facebook.ads.internal.view.i.d.d.a;
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = false;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.o = 1.0f;
        this.p = false;
        this.q = 3;
        this.r = false;
        this.s = false;
        this.u = 0;
        this.v = false;
        this.w = com.facebook.ads.internal.view.i.a.a.a;
        this.x = (MediaController$MediaPlayerControl)new MediaController$MediaPlayerControl() {
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
                if (com.facebook.ads.internal.view.i.d.b.this.d != null) {
                    return com.facebook.ads.internal.view.i.d.b.this.d.getAudioSessionId();
                }
                return 0;
            }
            
            public int getBufferPercentage() {
                return 0;
            }
            
            public int getCurrentPosition() {
                return com.facebook.ads.internal.view.i.d.b.this.getCurrentPosition();
            }
            
            public int getDuration() {
                return com.facebook.ads.internal.view.i.d.b.this.getDuration();
            }
            
            public boolean isPlaying() {
                return com.facebook.ads.internal.view.i.d.b.this.d != null && com.facebook.ads.internal.view.i.d.b.this.d.isPlaying();
            }
            
            public void pause() {
                com.facebook.ads.internal.view.i.d.b.this.a(true);
            }
            
            public void seekTo(final int n) {
                com.facebook.ads.internal.view.i.d.b.this.a(n);
            }
            
            public void start() {
                com.facebook.ads.internal.view.i.d.b.this.a(com.facebook.ads.internal.view.i.a.a.b);
            }
        };
    }
    
    @TargetApi(21)
    public b(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.f = com.facebook.ads.internal.view.i.d.d.a;
        this.g = com.facebook.ads.internal.view.i.d.d.a;
        this.h = com.facebook.ads.internal.view.i.d.d.a;
        this.i = false;
        this.k = 0;
        this.m = 0;
        this.n = 0;
        this.o = 1.0f;
        this.p = false;
        this.q = 3;
        this.r = false;
        this.s = false;
        this.u = 0;
        this.v = false;
        this.w = com.facebook.ads.internal.view.i.a.a.a;
        this.x = (MediaController$MediaPlayerControl)new MediaController$MediaPlayerControl() {
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
                if (com.facebook.ads.internal.view.i.d.b.this.d != null) {
                    return com.facebook.ads.internal.view.i.d.b.this.d.getAudioSessionId();
                }
                return 0;
            }
            
            public int getBufferPercentage() {
                return 0;
            }
            
            public int getCurrentPosition() {
                return com.facebook.ads.internal.view.i.d.b.this.getCurrentPosition();
            }
            
            public int getDuration() {
                return com.facebook.ads.internal.view.i.d.b.this.getDuration();
            }
            
            public boolean isPlaying() {
                return com.facebook.ads.internal.view.i.d.b.this.d != null && com.facebook.ads.internal.view.i.d.b.this.d.isPlaying();
            }
            
            public void pause() {
                com.facebook.ads.internal.view.i.d.b.this.a(true);
            }
            
            public void seekTo(final int n) {
                com.facebook.ads.internal.view.i.d.b.this.a(n);
            }
            
            public void start() {
                com.facebook.ads.internal.view.i.d.b.this.a(com.facebook.ads.internal.view.i.a.a.b);
            }
        };
    }
    
    private boolean a(@Nullable final Surface surface) {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.setSurface(surface);
            return true;
        }
        catch (IllegalStateException ex) {
            com.facebook.ads.internal.w.h.a.b(this.getContext(), "player", com.facebook.ads.internal.w.h.b.I, ex);
            Log.d(com.facebook.ads.internal.view.i.d.b.t, "The MediaPlayer failed", (Throwable)ex);
            return false;
        }
    }
    
    private boolean f() {
        return this.f == com.facebook.ads.internal.view.i.d.d.c || this.f == com.facebook.ads.internal.view.i.d.d.d || this.f == com.facebook.ads.internal.view.i.d.d.e || this.f == com.facebook.ads.internal.view.i.d.d.g;
    }
    
    private boolean g() {
        if (this.d == null) {
            return false;
        }
        try {
            this.d.reset();
            return true;
        }
        catch (IllegalStateException ex) {
            com.facebook.ads.internal.w.h.a.b(this.getContext(), "player", com.facebook.ads.internal.w.h.b.J, ex);
            Log.d(com.facebook.ads.internal.view.i.d.b.t, "The MediaPlayer failed", (Throwable)ex);
            return false;
        }
    }
    
    private void setVideoState(final d f) {
        if (f != this.f) {
            this.f = f;
            if (this.b != null) {
                this.b.a(f);
            }
        }
    }
    
    public void a() {
        if (!this.r) {
            this.a(false);
        }
    }
    
    public void a(final int n) {
        if (this.d != null && this.f()) {
            if (n < this.getDuration() && n > 0) {
                this.u = this.getCurrentPosition();
                this.k = n;
                this.d.seekTo(n);
            }
            return;
        }
        this.k = n;
    }
    
    public void a(final a w) {
        this.g = com.facebook.ads.internal.view.i.d.d.d;
        this.w = w;
        if (this.f == com.facebook.ads.internal.view.i.d.d.d || this.f == com.facebook.ads.internal.view.i.d.d.c || this.f == com.facebook.ads.internal.view.i.d.d.a || this.f == com.facebook.ads.internal.view.i.d.d.e || this.f == com.facebook.ads.internal.view.i.d.d.g) {
            if (this.d == null) {
                this.setup(this.a);
            }
            else {
                if (this.k > 0) {
                    this.d.seekTo(this.k);
                }
                this.d.start();
                if (this.f != com.facebook.ads.internal.view.i.d.d.c || this.s) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.d);
                }
            }
        }
        if (this.isAvailable()) {
            this.onSurfaceTextureAvailable(this.getSurfaceTexture(), 0, 0);
        }
    }
    
    public void a(final boolean b) {
        this.g = com.facebook.ads.internal.view.i.d.d.e;
        if (this.d != null) {
            int n;
            if (this.f != com.facebook.ads.internal.view.i.d.d.b && this.f != com.facebook.ads.internal.view.i.d.d.c) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                if (b) {
                    this.h = com.facebook.ads.internal.view.i.d.d.e;
                    this.i = true;
                }
                this.d.pause();
                if (this.f != com.facebook.ads.internal.view.i.d.d.g) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.e);
                }
            }
            return;
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
    }
    
    public void b() {
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.g);
        this.c();
        this.k = 0;
    }
    
    public void c() {
        this.g = com.facebook.ads.internal.view.i.d.d.a;
        if (this.d != null) {
            final int currentPosition = this.d.getCurrentPosition();
            if (currentPosition > 0) {
                this.k = currentPosition;
            }
            this.d.stop();
            this.g();
            this.d.release();
            this.d = null;
            if (this.e != null) {
                this.e.hide();
                this.e.setEnabled(false);
            }
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
    }
    
    @SuppressLint({ "NewApi" })
    public boolean d() {
        final boolean b = true;
        boolean b2;
        if (this.d == null || Build$VERSION.SDK_INT < 16) {
            b2 = false;
        }
        else {
            try {
                final MediaPlayer$TrackInfo[] trackInfo = this.d.getTrackInfo();
                for (int length = trackInfo.length, i = 0; i < length; ++i) {
                    final int trackType = trackInfo[i].getTrackType();
                    b2 = b;
                    if (trackType == 2) {
                        return b2;
                    }
                }
                return false;
            }
            catch (RuntimeException ex) {
                Log.e(com.facebook.ads.internal.view.i.d.b.t, "Couldn't retrieve video information", (Throwable)ex);
                return true;
            }
        }
        return b2;
    }
    
    public void e() {
        if (this.d != null) {
            this.a((Surface)null);
            this.d.setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)null);
            this.d.setOnCompletionListener((MediaPlayer$OnCompletionListener)null);
            this.d.setOnErrorListener((MediaPlayer$OnErrorListener)null);
            this.d.setOnInfoListener((MediaPlayer$OnInfoListener)null);
            this.d.setOnPreparedListener((MediaPlayer$OnPreparedListener)null);
            this.d.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)null);
            this.d.setOnSeekCompleteListener((MediaPlayer$OnSeekCompleteListener)null);
            this.g();
            this.d = null;
            this.setVideoState(com.facebook.ads.internal.view.i.d.d.a);
        }
    }
    
    public int getCurrentPosition() {
        int currentPosition = 0;
        if (this.d != null) {
            currentPosition = currentPosition;
            if (this.f()) {
                currentPosition = this.d.getCurrentPosition();
            }
        }
        return currentPosition;
    }
    
    public int getDuration() {
        if (this.d == null || !this.f()) {
            return 0;
        }
        return this.d.getDuration();
    }
    
    public long getInitialBufferTime() {
        return this.l;
    }
    
    public a getStartReason() {
        return this.w;
    }
    
    public d getState() {
        return this.f;
    }
    
    public d getTargetState() {
        return this.g;
    }
    
    public int getVideoHeight() {
        return this.n;
    }
    
    public int getVideoWidth() {
        return this.m;
    }
    
    public View getView() {
        return (View)this;
    }
    
    public float getVolume() {
        return this.o;
    }
    
    public void onBufferingUpdate(final MediaPlayer mediaPlayer, final int n) {
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        if (this.d != null) {
            this.d.pause();
        }
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.g);
        this.a(0);
        this.k = 0;
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        if (this.q > 0 && this.getState() == com.facebook.ads.internal.view.i.d.d.d) {
            --this.q;
            this.c();
            this.a(this.w);
        }
        else {
            this.setVideoState(com.facebook.ads.internal.view.i.d.d.h);
            this.c();
        }
        return true;
    }
    
    public boolean onInfo(final MediaPlayer mediaPlayer, int n, int n2) {
        n2 = 1;
        boolean b = true;
        switch (n) {
            case 701: {
                this.setVideoState(com.facebook.ads.internal.view.i.d.d.f);
                break;
            }
            case 702: {
                if (this.f != com.facebook.ads.internal.view.i.d.d.b && this.f != com.facebook.ads.internal.view.i.d.d.c) {
                    n = n2;
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.d);
                    break;
                }
                break;
            }
            case 3: {
                this.s = true;
                if (this.g == com.facebook.ads.internal.view.i.d.d.d) {
                    this.setVideoState(com.facebook.ads.internal.view.i.d.d.d);
                    return true;
                }
                return b;
            }
        }
        b = false;
        return b;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.setVideoState(com.facebook.ads.internal.view.i.d.d.c);
        if (this.p && !this.v) {
            this.e = new MediaController(this.getContext());
            final MediaController e = this.e;
            Object j;
            if (this.j == null) {
                j = this;
            }
            else {
                j = this.j;
            }
            e.setAnchorView((View)j);
            this.e.setMediaPlayer(this.x);
            this.e.setEnabled(true);
        }
        this.setRequestedVolume(this.o);
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.k > 0) {
            if (this.k >= this.d.getDuration()) {
                this.k = 0;
            }
            this.d.seekTo(this.k);
            this.k = 0;
        }
        if (this.g == com.facebook.ads.internal.view.i.d.d.d) {
            this.a(this.w);
        }
    }
    
    public void onSeekComplete(final MediaPlayer mediaPlayer) {
        if (this.b == null) {
            return;
        }
        this.b.a(this.u, this.k);
        this.k = 0;
    }
    
    public void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        if (this.c == null) {
            this.c = new Surface(surfaceTexture);
        }
        if (!this.a(this.c)) {
            this.setVideoState(com.facebook.ads.internal.view.i.d.d.h);
            this.e();
        }
        else {
            this.i = false;
            if (this.f == com.facebook.ads.internal.view.i.d.d.e && this.h != com.facebook.ads.internal.view.i.d.d.e) {
                this.a(this.w);
            }
        }
    }
    
    public boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        this.a((Surface)null);
        if (this.c != null) {
            this.c.release();
            this.c = null;
        }
        if (!this.i) {
            d h;
            if (this.p) {
                h = com.facebook.ads.internal.view.i.d.d.d;
            }
            else {
                h = this.f;
            }
            this.h = h;
            this.i = true;
        }
        if (this.f != com.facebook.ads.internal.view.i.d.d.e) {
            this.a(false);
        }
        return true;
    }
    
    public void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
    }
    
    public void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
    }
    
    public void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        this.m = mediaPlayer.getVideoWidth();
        this.n = mediaPlayer.getVideoHeight();
        if (this.m != 0 && this.n != 0) {
            this.requestLayout();
        }
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (this.d != null && (this.e == null || !this.e.isShowing())) {
            if (!b) {
                if (!this.i) {
                    d h;
                    if (this.p) {
                        h = com.facebook.ads.internal.view.i.d.d.d;
                    }
                    else {
                        h = this.f;
                    }
                    this.h = h;
                    this.i = true;
                }
                if (this.f != com.facebook.ads.internal.view.i.d.d.e) {
                    this.a();
                }
            }
            else {
                this.i = false;
                if (this.f == com.facebook.ads.internal.view.i.d.d.e && this.h != com.facebook.ads.internal.view.i.d.d.e) {
                    this.a(this.w);
                }
            }
        }
    }
    
    public void setBackgroundDrawable(final Drawable backgroundDrawable) {
        if (Build$VERSION.SDK_INT < 24) {
            super.setBackgroundDrawable(backgroundDrawable);
        }
        else if (AdInternalSettings.isDebugBuild()) {
            Log.w(com.facebook.ads.internal.view.i.d.b.t, "Google always throw an exception with setBackgroundDrawable on Nougat above. so we silently ignore it.");
        }
    }
    
    public void setBackgroundPlaybackEnabled(final boolean r) {
        this.r = r;
    }
    
    public void setControlsAnchorView(final View j) {
        (this.j = j).setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (com.facebook.ads.internal.view.i.d.b.this.v || com.facebook.ads.internal.view.i.d.b.this.e == null || motionEvent.getAction() != 1) {
                    return true;
                }
                if (com.facebook.ads.internal.view.i.d.b.this.e.isShowing()) {
                    com.facebook.ads.internal.view.i.d.b.this.e.hide();
                    return true;
                }
                com.facebook.ads.internal.view.i.d.b.this.e.show();
                return true;
            }
        });
    }
    
    public void setForeground(final Drawable foreground) {
        if (Build$VERSION.SDK_INT < 24) {
            super.setForeground(foreground);
        }
        else if (AdInternalSettings.isDebugBuild()) {
            Log.w(com.facebook.ads.internal.view.i.d.b.t, "Google always throw an exception with setForeground on Nougat above. so we silently ignore it.");
        }
    }
    
    public void setFullScreen(final boolean p) {
        this.p = p;
        if (this.p && !this.v) {
            this.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
                public boolean onTouch(final View view, final MotionEvent motionEvent) {
                    if (com.facebook.ads.internal.view.i.d.b.this.v || com.facebook.ads.internal.view.i.d.b.this.e == null || motionEvent.getAction() != 1) {
                        return true;
                    }
                    if (com.facebook.ads.internal.view.i.d.b.this.e.isShowing()) {
                        com.facebook.ads.internal.view.i.d.b.this.e.hide();
                        return true;
                    }
                    com.facebook.ads.internal.view.i.d.b.this.e.show();
                    return true;
                }
            });
        }
    }
    
    public void setRequestedVolume(final float o) {
        this.o = o;
        if (this.d != null && this.f != com.facebook.ads.internal.view.i.d.d.b && this.f != com.facebook.ads.internal.view.i.d.d.a) {
            this.d.setVolume(o, o);
        }
    }
    
    public void setVideoMPD(@Nullable final String s) {
    }
    
    public void setVideoStateChangeListener(final e b) {
        this.b = b;
    }
    
    public void setup(final Uri p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          8
        //     3: aconst_null    
        //     4: astore          7
        //     6: aload_0        
        //     7: iconst_0       
        //     8: putfield        com/facebook/ads/internal/view/i/d/b.s:Z
        //    11: aload_0        
        //    12: aload_1        
        //    13: putfield        com/facebook/ads/internal/view/i/d/b.a:Landroid/net/Uri;
        //    16: aload_0        
        //    17: getfield        com/facebook/ads/internal/view/i/d/b.d:Landroid/media/MediaPlayer;
        //    20: ifnull          201
        //    23: aload_0        
        //    24: invokespecial   com/facebook/ads/internal/view/i/d/b.g:()Z
        //    27: pop            
        //    28: aload_0        
        //    29: aconst_null    
        //    30: invokespecial   com/facebook/ads/internal/view/i/d/b.a:(Landroid/view/Surface;)Z
        //    33: pop            
        //    34: aload_0        
        //    35: getfield        com/facebook/ads/internal/view/i/d/b.d:Landroid/media/MediaPlayer;
        //    38: astore          9
        //    40: aload_0        
        //    41: getstatic       com/facebook/ads/internal/view/i/d/d.a:Lcom/facebook/ads/internal/view/i/d/d;
        //    44: invokespecial   com/facebook/ads/internal/view/i/d/b.setVideoState:(Lcom/facebook/ads/internal/view/i/d/d;)V
        //    47: aload_1        
        //    48: invokevirtual   android/net/Uri.getScheme:()Ljava/lang/String;
        //    51: ldc_w           "asset"
        //    54: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    57: istore_2       
        //    58: iload_2        
        //    59: ifeq            420
        //    62: aload_0        
        //    63: invokevirtual   com/facebook/ads/internal/view/i/d/b.getContext:()Landroid/content/Context;
        //    66: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    69: aload_1        
        //    70: invokevirtual   android/net/Uri.getPath:()Ljava/lang/String;
        //    73: iconst_1       
        //    74: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    77: invokevirtual   android/content/res/AssetManager.openFd:(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
        //    80: astore_1       
        //    81: aload_1        
        //    82: invokevirtual   android/content/res/AssetFileDescriptor.getStartOffset:()J
        //    85: lstore_3       
        //    86: aload_1        
        //    87: invokevirtual   android/content/res/AssetFileDescriptor.getLength:()J
        //    90: lstore          5
        //    92: aload           9
        //    94: aload_1        
        //    95: invokevirtual   android/content/res/AssetFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
        //    98: lload_3        
        //    99: lload           5
        //   101: invokevirtual   android/media/MediaPlayer.setDataSource:(Ljava/io/FileDescriptor;JJ)V
        //   104: aload_1        
        //   105: ifnull          112
        //   108: aload_1        
        //   109: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //   112: aload           9
        //   114: iconst_0       
        //   115: invokevirtual   android/media/MediaPlayer.setLooping:(Z)V
        //   118: aload           9
        //   120: aload_0        
        //   121: invokevirtual   android/media/MediaPlayer.setOnBufferingUpdateListener:(Landroid/media/MediaPlayer$OnBufferingUpdateListener;)V
        //   124: aload           9
        //   126: aload_0        
        //   127: invokevirtual   android/media/MediaPlayer.setOnCompletionListener:(Landroid/media/MediaPlayer$OnCompletionListener;)V
        //   130: aload           9
        //   132: aload_0        
        //   133: invokevirtual   android/media/MediaPlayer.setOnErrorListener:(Landroid/media/MediaPlayer$OnErrorListener;)V
        //   136: aload           9
        //   138: aload_0        
        //   139: invokevirtual   android/media/MediaPlayer.setOnInfoListener:(Landroid/media/MediaPlayer$OnInfoListener;)V
        //   142: aload           9
        //   144: aload_0        
        //   145: invokevirtual   android/media/MediaPlayer.setOnPreparedListener:(Landroid/media/MediaPlayer$OnPreparedListener;)V
        //   148: aload           9
        //   150: aload_0        
        //   151: invokevirtual   android/media/MediaPlayer.setOnVideoSizeChangedListener:(Landroid/media/MediaPlayer$OnVideoSizeChangedListener;)V
        //   154: aload           9
        //   156: aload_0        
        //   157: invokevirtual   android/media/MediaPlayer.setOnSeekCompleteListener:(Landroid/media/MediaPlayer$OnSeekCompleteListener;)V
        //   160: aload           9
        //   162: invokevirtual   android/media/MediaPlayer.prepareAsync:()V
        //   165: aload_0        
        //   166: aload           9
        //   168: putfield        com/facebook/ads/internal/view/i/d/b.d:Landroid/media/MediaPlayer;
        //   171: aload_0        
        //   172: getstatic       com/facebook/ads/internal/view/i/d/d.b:Lcom/facebook/ads/internal/view/i/d/d;
        //   175: invokespecial   com/facebook/ads/internal/view/i/d/b.setVideoState:(Lcom/facebook/ads/internal/view/i/d/d;)V
        //   178: aload_0        
        //   179: aload_0        
        //   180: invokevirtual   com/facebook/ads/internal/view/i/d/b.setSurfaceTextureListener:(Landroid/view/TextureView$SurfaceTextureListener;)V
        //   183: aload_0        
        //   184: invokevirtual   com/facebook/ads/internal/view/i/d/b.isAvailable:()Z
        //   187: ifeq            200
        //   190: aload_0        
        //   191: aload_0        
        //   192: invokevirtual   com/facebook/ads/internal/view/i/d/b.getSurfaceTexture:()Landroid/graphics/SurfaceTexture;
        //   195: iconst_0       
        //   196: iconst_0       
        //   197: invokevirtual   com/facebook/ads/internal/view/i/d/b.onSurfaceTextureAvailable:(Landroid/graphics/SurfaceTexture;II)V
        //   200: return         
        //   201: new             Landroid/media/MediaPlayer;
        //   204: dup            
        //   205: invokespecial   android/media/MediaPlayer.<init>:()V
        //   208: astore          9
        //   210: goto            47
        //   213: astore_1       
        //   214: getstatic       com/facebook/ads/internal/view/i/d/b.t:Ljava/lang/String;
        //   217: new             Ljava/lang/StringBuilder;
        //   220: dup            
        //   221: invokespecial   java/lang/StringBuilder.<init>:()V
        //   224: ldc_w           "Unable to close"
        //   227: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   230: aload_1        
        //   231: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   234: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   237: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   240: pop            
        //   241: goto            112
        //   244: astore_1       
        //   245: aload_0        
        //   246: getstatic       com/facebook/ads/internal/view/i/d/d.h:Lcom/facebook/ads/internal/view/i/d/d;
        //   249: invokespecial   com/facebook/ads/internal/view/i/d/b.setVideoState:(Lcom/facebook/ads/internal/view/i/d/d;)V
        //   252: aload           9
        //   254: invokevirtual   android/media/MediaPlayer.release:()V
        //   257: getstatic       com/facebook/ads/internal/view/i/d/b.t:Ljava/lang/String;
        //   260: new             Ljava/lang/StringBuilder;
        //   263: dup            
        //   264: invokespecial   java/lang/StringBuilder.<init>:()V
        //   267: ldc_w           "Cannot prepare media player with SurfaceTexture: "
        //   270: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   273: aload_1        
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   277: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   280: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   283: pop            
        //   284: goto            178
        //   287: astore_1       
        //   288: aload           7
        //   290: astore          8
        //   292: getstatic       com/facebook/ads/internal/view/i/d/b.t:Ljava/lang/String;
        //   295: new             Ljava/lang/StringBuilder;
        //   298: dup            
        //   299: invokespecial   java/lang/StringBuilder.<init>:()V
        //   302: ldc_w           "Failed to open assets "
        //   305: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   308: aload_1        
        //   309: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   312: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   315: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   318: pop            
        //   319: aload           7
        //   321: astore          8
        //   323: aload_0        
        //   324: getstatic       com/facebook/ads/internal/view/i/d/d.h:Lcom/facebook/ads/internal/view/i/d/d;
        //   327: invokespecial   com/facebook/ads/internal/view/i/d/b.setVideoState:(Lcom/facebook/ads/internal/view/i/d/d;)V
        //   330: aload           7
        //   332: ifnull          112
        //   335: aload           7
        //   337: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //   340: goto            112
        //   343: astore_1       
        //   344: getstatic       com/facebook/ads/internal/view/i/d/b.t:Ljava/lang/String;
        //   347: new             Ljava/lang/StringBuilder;
        //   350: dup            
        //   351: invokespecial   java/lang/StringBuilder.<init>:()V
        //   354: ldc_w           "Unable to close"
        //   357: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   360: aload_1        
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   364: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   367: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   370: pop            
        //   371: goto            112
        //   374: astore_1       
        //   375: aload           8
        //   377: ifnull          385
        //   380: aload           8
        //   382: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //   385: aload_1        
        //   386: athrow         
        //   387: astore          7
        //   389: getstatic       com/facebook/ads/internal/view/i/d/b.t:Ljava/lang/String;
        //   392: new             Ljava/lang/StringBuilder;
        //   395: dup            
        //   396: invokespecial   java/lang/StringBuilder.<init>:()V
        //   399: ldc_w           "Unable to close"
        //   402: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   405: aload           7
        //   407: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   410: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   413: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   416: pop            
        //   417: goto            385
        //   420: aload           9
        //   422: aload_1        
        //   423: invokevirtual   android/net/Uri.toString:()Ljava/lang/String;
        //   426: invokevirtual   android/media/MediaPlayer.setDataSource:(Ljava/lang/String;)V
        //   429: goto            112
        //   432: astore          7
        //   434: aload_1        
        //   435: astore          8
        //   437: aload           7
        //   439: astore_1       
        //   440: goto            375
        //   443: astore          8
        //   445: aload_1        
        //   446: astore          7
        //   448: aload           8
        //   450: astore_1       
        //   451: goto            288
        //   454: astore_1       
        //   455: goto            288
        //   458: astore          8
        //   460: aload_1        
        //   461: astore          7
        //   463: aload           8
        //   465: astore_1       
        //   466: goto            288
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  47     58     244    287    Ljava/lang/Exception;
        //  62     81     454    458    Ljava/lang/SecurityException;
        //  62     81     287    288    Ljava/io/IOException;
        //  62     81     374    375    Any
        //  81     104    458    469    Ljava/lang/SecurityException;
        //  81     104    443    454    Ljava/io/IOException;
        //  81     104    432    443    Any
        //  108    112    213    244    Ljava/io/IOException;
        //  108    112    244    287    Ljava/lang/Exception;
        //  112    178    244    287    Ljava/lang/Exception;
        //  214    241    244    287    Ljava/lang/Exception;
        //  292    319    374    375    Any
        //  323    330    374    375    Any
        //  335    340    343    374    Ljava/io/IOException;
        //  335    340    244    287    Ljava/lang/Exception;
        //  344    371    244    287    Ljava/lang/Exception;
        //  380    385    387    420    Ljava/io/IOException;
        //  380    385    244    287    Ljava/lang/Exception;
        //  385    387    244    287    Ljava/lang/Exception;
        //  389    417    244    287    Ljava/lang/Exception;
        //  420    429    244    287    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 221, Size: 221
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
