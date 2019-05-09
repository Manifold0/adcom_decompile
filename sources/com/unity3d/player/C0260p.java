package com.unity3d.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.MediaController;
import android.widget.MediaController.MediaPlayerControl;
import java.io.FileInputStream;
import java.io.IOException;

/* renamed from: com.unity3d.player.p */
public final class C0260p extends FrameLayout implements OnBufferingUpdateListener, OnCompletionListener, OnPreparedListener, OnVideoSizeChangedListener, Callback, MediaPlayerControl {
    /* renamed from: a */
    private static boolean f225a = false;
    /* renamed from: b */
    private final Context f226b;
    /* renamed from: c */
    private final SurfaceView f227c;
    /* renamed from: d */
    private final SurfaceHolder f228d;
    /* renamed from: e */
    private final String f229e;
    /* renamed from: f */
    private final int f230f;
    /* renamed from: g */
    private final int f231g;
    /* renamed from: h */
    private final boolean f232h;
    /* renamed from: i */
    private final long f233i;
    /* renamed from: j */
    private final long f234j;
    /* renamed from: k */
    private final FrameLayout f235k;
    /* renamed from: l */
    private final Display f236l;
    /* renamed from: m */
    private int f237m;
    /* renamed from: n */
    private int f238n;
    /* renamed from: o */
    private int f239o;
    /* renamed from: p */
    private int f240p;
    /* renamed from: q */
    private MediaPlayer f241q;
    /* renamed from: r */
    private MediaController f242r;
    /* renamed from: s */
    private boolean f243s = false;
    /* renamed from: t */
    private boolean f244t = false;
    /* renamed from: u */
    private int f245u = 0;
    /* renamed from: v */
    private boolean f246v = false;
    /* renamed from: w */
    private boolean f247w = false;
    /* renamed from: x */
    private C0258a f248x;
    /* renamed from: y */
    private C0259b f249y;
    /* renamed from: z */
    private volatile int f250z = 0;

    /* renamed from: com.unity3d.player.p$a */
    public interface C0258a {
        /* renamed from: a */
        void mo695a(int i);
    }

    /* renamed from: com.unity3d.player.p$b */
    public class C0259b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C0260p f222a;
        /* renamed from: b */
        private C0260p f223b;
        /* renamed from: c */
        private boolean f224c = false;

        public C0259b(C0260p c0260p, C0260p c0260p2) {
            this.f222a = c0260p;
            this.f223b = c0260p2;
        }

        /* renamed from: a */
        public final void m180a() {
            this.f224c = true;
        }

        public final void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (!this.f224c) {
                if (C0260p.f225a) {
                    C0260p.m183b("Stopping the video player due to timeout.");
                }
                this.f223b.CancelOnPrepare();
            }
        }
    }

    protected C0260p(Context context, String str, int i, int i2, int i3, boolean z, long j, long j2, C0258a c0258a) {
        super(context);
        this.f248x = c0258a;
        this.f226b = context;
        this.f235k = this;
        this.f227c = new SurfaceView(context);
        this.f228d = this.f227c.getHolder();
        this.f228d.addCallback(this);
        this.f235k.setBackgroundColor(i);
        this.f235k.addView(this.f227c);
        this.f236l = ((WindowManager) this.f226b.getSystemService("window")).getDefaultDisplay();
        this.f229e = str;
        this.f230f = i2;
        this.f231g = i3;
        this.f232h = z;
        this.f233i = j;
        this.f234j = j2;
        if (f225a) {
            C0260p.m183b("fileName: " + this.f229e);
        }
        if (f225a) {
            C0260p.m183b("backgroundColor: " + i);
        }
        if (f225a) {
            C0260p.m183b("controlMode: " + this.f230f);
        }
        if (f225a) {
            C0260p.m183b("scalingMode: " + this.f231g);
        }
        if (f225a) {
            C0260p.m183b("isURL: " + this.f232h);
        }
        if (f225a) {
            C0260p.m183b("videoOffset: " + this.f233i);
        }
        if (f225a) {
            C0260p.m183b("videoLength: " + this.f234j);
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* renamed from: a */
    private void m181a(int i) {
        this.f250z = i;
        if (this.f248x != null) {
            this.f248x.mo695a(this.f250z);
        }
    }

    /* renamed from: b */
    private static void m183b(String str) {
        Log.i("Video", "VideoPlayer: " + str);
    }

    /* renamed from: c */
    private void m185c() {
        if (this.f241q != null) {
            this.f241q.setDisplay(this.f228d);
            if (!this.f246v) {
                if (f225a) {
                    C0260p.m183b("Resuming playback");
                }
                this.f241q.start();
                return;
            }
            return;
        }
        m181a(0);
        doCleanUp();
        try {
            this.f241q = new MediaPlayer();
            if (this.f232h) {
                this.f241q.setDataSource(this.f226b, Uri.parse(this.f229e));
            } else if (this.f234j != 0) {
                FileInputStream fileInputStream = new FileInputStream(this.f229e);
                this.f241q.setDataSource(fileInputStream.getFD(), this.f233i, this.f234j);
                fileInputStream.close();
            } else {
                try {
                    AssetFileDescriptor openFd = getResources().getAssets().openFd(this.f229e);
                    this.f241q.setDataSource(openFd.getFileDescriptor(), openFd.getStartOffset(), openFd.getLength());
                    openFd.close();
                } catch (IOException e) {
                    FileInputStream fileInputStream2 = new FileInputStream(this.f229e);
                    this.f241q.setDataSource(fileInputStream2.getFD());
                    fileInputStream2.close();
                }
            }
            this.f241q.setDisplay(this.f228d);
            this.f241q.setScreenOnWhilePlaying(true);
            this.f241q.setOnBufferingUpdateListener(this);
            this.f241q.setOnCompletionListener(this);
            this.f241q.setOnPreparedListener(this);
            this.f241q.setOnVideoSizeChangedListener(this);
            this.f241q.setAudioStreamType(3);
            this.f241q.prepareAsync();
            this.f249y = new C0259b(this, this);
            new Thread(this.f249y).start();
        } catch (Exception e2) {
            if (f225a) {
                C0260p.m183b("error: " + e2.getMessage() + e2);
            }
            m181a(2);
        }
    }

    /* renamed from: d */
    private void m186d() {
        if (!isPlaying()) {
            m181a(1);
            if (f225a) {
                C0260p.m183b("startVideoPlayback");
            }
            updateVideoLayout();
            if (!this.f246v) {
                start();
            }
        }
    }

    public final void CancelOnPrepare() {
        m181a(2);
    }

    /* renamed from: a */
    final boolean m187a() {
        return this.f246v;
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
        if (f225a) {
            C0260p.m183b("destroyPlayer");
        }
        if (!this.f246v) {
            pause();
        }
        doCleanUp();
    }

    protected final void doCleanUp() {
        if (this.f249y != null) {
            this.f249y.m180a();
            this.f249y = null;
        }
        if (this.f241q != null) {
            this.f241q.release();
            this.f241q = null;
        }
        this.f239o = 0;
        this.f240p = 0;
        this.f244t = false;
        this.f243s = false;
    }

    public final int getBufferPercentage() {
        return this.f232h ? this.f245u : 100;
    }

    public final int getCurrentPosition() {
        return this.f241q == null ? 0 : this.f241q.getCurrentPosition();
    }

    public final int getDuration() {
        return this.f241q == null ? 0 : this.f241q.getDuration();
    }

    public final boolean isPlaying() {
        boolean z = this.f244t && this.f243s;
        return this.f241q == null ? !z : this.f241q.isPlaying() || !z;
    }

    public final void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
        if (f225a) {
            C0260p.m183b("onBufferingUpdate percent:" + i);
        }
        this.f245u = i;
    }

    public final void onCompletion(MediaPlayer mediaPlayer) {
        if (f225a) {
            C0260p.m183b("onCompletion called");
        }
        destroyPlayer();
        m181a(3);
    }

    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4 && (this.f230f != 2 || i == 0 || keyEvent.isSystem())) {
            return this.f242r != null ? this.f242r.onKeyDown(i, keyEvent) : super.onKeyDown(i, keyEvent);
        } else {
            destroyPlayer();
            m181a(3);
            return true;
        }
    }

    public final void onPrepared(MediaPlayer mediaPlayer) {
        if (f225a) {
            C0260p.m183b("onPrepared called");
        }
        if (this.f249y != null) {
            this.f249y.m180a();
            this.f249y = null;
        }
        if (this.f230f == 0 || this.f230f == 1) {
            this.f242r = new MediaController(this.f226b);
            this.f242r.setMediaPlayer(this);
            this.f242r.setAnchorView(this);
            this.f242r.setEnabled(true);
            this.f242r.show();
        }
        this.f244t = true;
        if (this.f244t && this.f243s) {
            m186d();
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f230f != 2 || action != 0) {
            return this.f242r != null ? this.f242r.onTouchEvent(motionEvent) : super.onTouchEvent(motionEvent);
        } else {
            destroyPlayer();
            m181a(3);
            return true;
        }
    }

    public final void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        if (f225a) {
            C0260p.m183b("onVideoSizeChanged called " + i + "x" + i2);
        }
        if (i != 0 && i2 != 0) {
            this.f243s = true;
            this.f239o = i;
            this.f240p = i2;
            if (this.f244t && this.f243s) {
                m186d();
            }
        } else if (f225a) {
            C0260p.m183b("invalid video width(" + i + ") or height(" + i2 + ")");
        }
    }

    public final void pause() {
        if (this.f241q != null) {
            if (this.f247w) {
                this.f241q.pause();
            }
            this.f246v = true;
        }
    }

    public final void seekTo(int i) {
        if (this.f241q != null) {
            this.f241q.seekTo(i);
        }
    }

    public final void start() {
        if (f225a) {
            C0260p.m183b("Start");
        }
        if (this.f241q != null) {
            if (this.f247w) {
                this.f241q.start();
            }
            this.f246v = false;
        }
    }

    public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        if (f225a) {
            C0260p.m183b("surfaceChanged called " + i + " " + i2 + "x" + i3);
        }
        if (this.f237m != i2 || this.f238n != i3) {
            this.f237m = i2;
            this.f238n = i3;
            if (this.f247w) {
                updateVideoLayout();
            }
        }
    }

    public final void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (f225a) {
            C0260p.m183b("surfaceCreated called");
        }
        this.f247w = true;
        m185c();
    }

    public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (f225a) {
            C0260p.m183b("surfaceDestroyed called");
        }
        this.f247w = false;
    }

    protected final void updateVideoLayout() {
        if (f225a) {
            C0260p.m183b("updateVideoLayout");
        }
        if (this.f241q != null) {
            if (this.f237m == 0 || this.f238n == 0) {
                WindowManager windowManager = (WindowManager) this.f226b.getSystemService("window");
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                this.f237m = displayMetrics.widthPixels;
                this.f238n = displayMetrics.heightPixels;
            }
            int i = this.f237m;
            int i2 = this.f238n;
            if (this.f243s) {
                float f = ((float) this.f239o) / ((float) this.f240p);
                float f2 = ((float) this.f237m) / ((float) this.f238n);
                if (this.f231g == 1) {
                    if (f2 <= f) {
                        i2 = (int) (((float) this.f237m) / f);
                    } else {
                        i = (int) (((float) this.f238n) * f);
                    }
                } else if (this.f231g == 2) {
                    if (f2 >= f) {
                        i2 = (int) (((float) this.f237m) / f);
                    } else {
                        i = (int) (((float) this.f238n) * f);
                    }
                } else if (this.f231g == 0) {
                    i = this.f239o;
                    i2 = this.f240p;
                }
            } else if (f225a) {
                C0260p.m183b("updateVideoLayout: Video size is not known yet");
            }
            if (this.f237m != i || this.f238n != i2) {
                if (f225a) {
                    C0260p.m183b("frameWidth = " + i + "; frameHeight = " + i2);
                }
                this.f235k.updateViewLayout(this.f227c, new LayoutParams(i, i2, 17));
            }
        }
    }
}
