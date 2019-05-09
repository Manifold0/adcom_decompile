// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.os.MessageQueue$IdleHandler;
import android.os.Handler$Callback;
import android.os.Looper;
import android.os.Message;
import android.os.Handler;
import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.view.MotionEvent;
import android.os.Process;
import android.view.KeyEvent;
import android.os.Bundle;
import android.content.ClipData;
import android.content.res.Configuration;
import android.view.InputEvent;
import android.telephony.PhoneStateListener;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder$Callback;
import android.view.ViewParent;
import android.view.ViewGroup;
import android.content.pm.ApplicationInfo;
import android.view.Surface;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.view.View;
import android.view.SurfaceView;
import android.content.Context;
import android.content.ClipboardManager;
import android.telephony.TelephonyManager;
import android.content.BroadcastReceiver;
import java.util.concurrent.ConcurrentLinkedQueue;
import android.app.Activity;
import android.widget.FrameLayout;

public class UnityPlayer extends FrameLayout implements com.unity3d.player.f
{
    public static Activity currentActivity;
    private static boolean t;
    e a;
    k b;
    private int c;
    private boolean d;
    private boolean e;
    private n f;
    private final ConcurrentLinkedQueue g;
    private BroadcastReceiver h;
    private boolean i;
    private c j;
    private TelephonyManager k;
    private ClipboardManager l;
    private l m;
    private GoogleARProxy n;
    private GoogleARCoreApi o;
    private a p;
    private Camera2Wrapper q;
    private Context r;
    private SurfaceView s;
    private boolean u;
    private boolean v;
    private boolean w;
    private q x;
    
    static {
        UnityPlayer.currentActivity = null;
        new m().a();
        UnityPlayer.t = false;
        UnityPlayer.t = loadLibraryStatic("main");
    }
    
    public UnityPlayer(final Context r) {
        super(r);
        this.c = -1;
        this.d = false;
        this.e = true;
        this.f = new n();
        this.g = new ConcurrentLinkedQueue();
        this.h = null;
        this.a = new e((byte)0);
        this.i = false;
        this.j = new c((byte)0);
        this.n = null;
        this.o = null;
        this.p = new a();
        this.q = null;
        this.w = false;
        this.b = null;
        if (r instanceof Activity) {
            UnityPlayer.currentActivity = (Activity)r;
            this.c = UnityPlayer.currentActivity.getRequestedOrientation();
        }
        a(UnityPlayer.currentActivity);
        this.r = r;
        if (UnityPlayer.currentActivity != null && this.i()) {
            this.addView((View)(this.m = new l(this.r, com.unity3d.player.l.a.a()[this.getSplashMode()])));
        }
        if (com.unity3d.player.j.c) {
            if (UnityPlayer.currentActivity != null) {
                com.unity3d.player.j.d.a(UnityPlayer.currentActivity, new Runnable() {
                    @Override
                    public final void run() {
                        UnityPlayer.this.a(new Runnable() {
                            @Override
                            public final void run() {
                                UnityPlayer.this.f.d();
                                UnityPlayer.this.f();
                            }
                        });
                    }
                });
            }
            else {
                this.f.d();
            }
        }
        a(this.r.getApplicationInfo());
        if (!com.unity3d.player.n.c()) {
            final AlertDialog create = new AlertDialog$Builder(this.r).setTitle((CharSequence)"Failure to initialize!").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public final void onClick(final DialogInterface dialogInterface, final int n) {
                    UnityPlayer.this.c();
                }
            }).setMessage((CharSequence)"Your hardware does not support this application, sorry!").create();
            create.setCancelable(false);
            create.show();
            return;
        }
        this.initJni(r);
        this.addView((View)(this.s = this.b()));
        this.bringChildToFront((View)this.m);
        this.u = false;
        this.nativeInitWebRequest(UnityWebRequest.class);
        this.k();
        this.k = (TelephonyManager)this.r.getSystemService("phone");
        this.l = (ClipboardManager)this.r.getSystemService("clipboard");
        this.q = new Camera2Wrapper(this.r);
        this.a.start();
    }
    
    public static void UnitySendMessage(final String s, final String s2, final String s3) {
        if (!n.c()) {
            g.Log(5, "Native libraries not loaded - dropping message for " + s + "." + s2);
            return;
        }
        nativeUnitySendMessage(s, s2, s3);
    }
    
    private void a() {
        this.a(new Runnable() {
            @Override
            public final void run() {
                UnityPlayer.this.removeView((View)UnityPlayer.this.m);
                UnityPlayer.this.m = null;
            }
        });
    }
    
    private void a(final int n, final Surface surface) {
        if (this.d) {
            return;
        }
        this.b(0, surface);
    }
    
    private static void a(final Activity activity) {
        if (activity != null && activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) && activity.getWindow() != null) {
            final View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(7);
            }
        }
    }
    
    private static void a(final ApplicationInfo applicationInfo) {
        if (UnityPlayer.t && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            n.a();
        }
    }
    
    private void a(final View view, final View view2) {
        int n;
        if (!this.f.e()) {
            this.pause();
            n = 1;
        }
        else {
            n = 0;
        }
        if (view != null) {
            final ViewParent parent = view.getParent();
            if (!(parent instanceof UnityPlayer) || (UnityPlayer)parent != this) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup)parent).removeView(view);
                }
                this.addView(view);
                this.bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            this.removeView(view2);
        }
        if (n != 0) {
            this.resume();
        }
    }
    
    private void a(final f f) {
        if (this.isFinishing()) {
            return;
        }
        this.b(f);
    }
    
    static /* synthetic */ void a(final UnityPlayer unityPlayer, final Surface surface) {
        unityPlayer.a(0, surface);
    }
    
    private SurfaceView b() {
        final SurfaceView surfaceView = new SurfaceView(this.r);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.getHolder().addCallback((SurfaceHolder$Callback)new SurfaceHolder$Callback() {
            public final void surfaceChanged(final SurfaceHolder surfaceHolder, final int n, final int n2, final int n3) {
                UnityPlayer.a(UnityPlayer.this, surfaceHolder.getSurface());
            }
            
            public final void surfaceCreated(final SurfaceHolder surfaceHolder) {
                UnityPlayer.a(UnityPlayer.this, surfaceHolder.getSurface());
            }
            
            public final void surfaceDestroyed(final SurfaceHolder surfaceHolder) {
                UnityPlayer.a(UnityPlayer.this, (Surface)null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }
    
    private void b(final Runnable runnable) {
        if (!com.unity3d.player.n.c()) {
            return;
        }
        if (Thread.currentThread() == this.a) {
            runnable.run();
            return;
        }
        this.g.add(runnable);
    }
    
    private boolean b(final int n, final Surface surface) {
        if (!n.c()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable runnable = new Runnable() {
            @Override
            public final void run() {
                UnityPlayer.this.nativeRecreateGfxState(n, surface);
                semaphore.release();
            }
        };
        Label_0089: {
            if (n != 0) {
                break Label_0089;
            }
            Label_0077: {
                if (surface != null) {
                    break Label_0077;
                }
                this.a.b(runnable);
                while (true) {
                    if (surface != null || n != 0) {
                        return true;
                    }
                    try {
                        if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                            com.unity3d.player.g.Log(5, "Timeout while trying detaching primary window.");
                        }
                        return true;
                        this.a.c(runnable);
                        continue;
                        runnable.run();
                        continue;
                    }
                    catch (InterruptedException ex) {
                        com.unity3d.player.g.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
                        return true;
                    }
                    break;
                }
            }
        }
    }
    
    private void c() {
        if (this.r instanceof Activity && !((Activity)this.r).isFinishing()) {
            ((Activity)this.r).finish();
        }
    }
    
    private void d() {
        this.reportSoftInputStr(null, 1, true);
        if (this.f.g()) {
            Label_0089: {
                if (!com.unity3d.player.n.c()) {
                    break Label_0089;
                }
                final Semaphore semaphore = new Semaphore(0);
                Label_0125: {
                    if (!this.isFinishing()) {
                        break Label_0125;
                    }
                    Runnable runnable = new Runnable() {
                        @Override
                        public final void run() {
                            UnityPlayer.this.e();
                            semaphore.release();
                        }
                    };
                    while (true) {
                        this.a.a(runnable);
                        while (true) {
                            try {
                                if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                                    com.unity3d.player.g.Log(5, "Timeout while trying to pause the Unity Engine.");
                                }
                                if (semaphore.drainPermits() > 0) {
                                    this.quit();
                                }
                                this.f.c(false);
                                this.f.b(true);
                                if (this.i) {
                                    this.k.listen((PhoneStateListener)this.j, 0);
                                    return;
                                }
                                break;
                                runnable = new Runnable() {
                                    @Override
                                    public final void run() {
                                        if (UnityPlayer.this.nativePause()) {
                                            UnityPlayer.this.u = true;
                                            UnityPlayer.this.e();
                                            semaphore.release(2);
                                            return;
                                        }
                                        semaphore.release();
                                    }
                                };
                            }
                            catch (InterruptedException ex) {
                                com.unity3d.player.g.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                                continue;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    private void e() {
        this.v = this.nativeShouldQuit();
        this.w = true;
        this.nativeDone();
    }
    
    private void f() {
        if (!this.f.f()) {
            return;
        }
        this.f.c(true);
        this.b(new Runnable() {
            @Override
            public final void run() {
                UnityPlayer.this.nativeResume();
            }
        });
        this.a.b();
    }
    
    private static void g() {
        if (!n.c()) {
            return;
        }
        if (!NativeLoader.unload()) {
            throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
        }
        n.b();
    }
    
    private ApplicationInfo h() {
        return this.r.getPackageManager().getApplicationInfo(this.r.getPackageName(), 128);
    }
    
    private boolean i() {
        try {
            return this.h().metaData.getBoolean("unity.splash-enable");
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private final native void initJni(final Context p0);
    
    private boolean j() {
        try {
            return this.h().metaData.getBoolean("unity.tango-enable");
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    private void k() {
        if (this.r instanceof Activity) {
            ((Activity)this.r).getWindow().setFlags(1024, 1024);
        }
    }
    
    protected static boolean loadLibraryStatic(final String s) {
        try {
            System.loadLibrary(s);
            return true;
        }
        catch (UnsatisfiedLinkError unsatisfiedLinkError) {
            g.Log(6, "Unable to find " + s);
            return false;
        }
        catch (Exception ex) {
            g.Log(6, "Unknown error " + ex);
            return false;
        }
    }
    
    private final native void nativeDone();
    
    private final native void nativeFocusChanged(final boolean p0);
    
    private final native void nativeInitWebRequest(final Class p0);
    
    private final native boolean nativeInjectEvent(final InputEvent p0);
    
    private final native boolean nativeIsAutorotationOn();
    
    private final native void nativeLowMemory();
    
    private final native void nativeMuteMasterAudio(final boolean p0);
    
    private final native boolean nativePause();
    
    private final native void nativeRecreateGfxState(final int p0, final Surface p1);
    
    private final native boolean nativeRender();
    
    private final native void nativeRestartActivityIndicator();
    
    private final native void nativeResume();
    
    private final native void nativeSetInputString(final String p0);
    
    private final native boolean nativeShouldQuit();
    
    private final native void nativeSoftInputCanceled();
    
    private final native void nativeSoftInputClosed();
    
    private final native void nativeSoftInputLostFocus();
    
    private static native void nativeUnitySendMessage(final String p0, final String p1, final String p2);
    
    final void a(final Runnable runnable) {
        if (this.r instanceof Activity) {
            ((Activity)this.r).runOnUiThread(runnable);
            return;
        }
        com.unity3d.player.g.Log(5, "Not running Unity from an Activity; ignored...");
    }
    
    protected void addPhoneCallListener() {
        this.i = true;
        this.k.listen((PhoneStateListener)this.j, 32);
    }
    
    public boolean addViewToPlayer(final View view, final boolean b) {
        final boolean b2 = true;
        Object s;
        if (b) {
            s = this.s;
        }
        else {
            s = null;
        }
        this.a(view, (View)s);
        boolean b3;
        if (view.getParent() == this) {
            b3 = true;
        }
        else {
            b3 = false;
        }
        boolean b4;
        if (b && this.s.getParent() == null) {
            b4 = true;
        }
        else {
            b4 = false;
        }
        boolean b5;
        if (this.s.getParent() == this) {
            b5 = true;
        }
        else {
            b5 = false;
        }
        while (true) {
            Label_0140: {
                if (!b3) {
                    break Label_0140;
                }
                boolean b6 = b2;
                if (!b4) {
                    if (!b5) {
                        break Label_0140;
                    }
                    b6 = b2;
                }
                if (!b6) {
                    if (!b3) {
                        com.unity3d.player.g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
                    }
                    if (!b4 && !b5) {
                        com.unity3d.player.g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
                    }
                }
                return b6;
            }
            boolean b6 = false;
            continue;
        }
    }
    
    public void configurationChanged(final Configuration configuration) {
        if (this.s instanceof SurfaceView) {
            this.s.getHolder().setSizeFromLayout();
        }
        if (this.x != null) {
            this.x.c();
        }
        final GoogleVrProxy b = GoogleVrApi.b();
        if (b != null) {
            b.c();
        }
    }
    
    protected void disableLogger() {
        com.unity3d.player.g.a = true;
    }
    
    public boolean displayChanged(final int n, final Surface surface) {
        if (n == 0) {
            this.d = (surface != null);
            this.a(new Runnable() {
                @Override
                public final void run() {
                    if (UnityPlayer.this.d) {
                        UnityPlayer.this.removeView((View)UnityPlayer.this.s);
                        return;
                    }
                    UnityPlayer.this.addView((View)UnityPlayer.this.s);
                }
            });
        }
        return this.b(n, surface);
    }
    
    protected void executeGLThreadJobs() {
        while (true) {
            final Runnable runnable = this.g.poll();
            if (runnable == null) {
                break;
            }
            runnable.run();
        }
    }
    
    protected String getClipboardText() {
        String string = "";
        final ClipData primaryClip = this.l.getPrimaryClip();
        if (primaryClip != null) {
            string = primaryClip.getItemAt(0).coerceToText(this.r).toString();
        }
        return string;
    }
    
    public Bundle getSettings() {
        return Bundle.EMPTY;
    }
    
    protected int getSplashMode() {
        try {
            return this.h().metaData.getInt("unity.splash-mode");
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    public View getView() {
        return (View)this;
    }
    
    protected void hideSoftInput() {
        final Runnable runnable = new Runnable() {
            @Override
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.dismiss();
                    UnityPlayer.this.b = null;
                }
            }
        };
        if (com.unity3d.player.j.b) {
            this.a((f)new f() {
                @Override
                public final void a() {
                    UnityPlayer.this.a(runnable);
                }
            });
            return;
        }
        this.a(runnable);
    }
    
    public void init(final int n, final boolean b) {
    }
    
    protected boolean initializeGoogleAr() {
        if (this.n == null && UnityPlayer.currentActivity != null && this.j()) {
            if (GoogleARProxy.a()) {
                (this.n = new GoogleARProxy(this)).a(UnityPlayer.currentActivity, this.r);
                this.n.b();
                if (!this.f.e()) {
                    this.n.d();
                }
                return this.n.e();
            }
            (this.o = new GoogleARCoreApi()).initializeARCore(UnityPlayer.currentActivity);
            if (!this.f.e()) {
                this.o.resumeARCore();
            }
        }
        return false;
    }
    
    protected boolean initializeGoogleVr() {
        GoogleVrProxy googleVrProxy;
        if ((googleVrProxy = GoogleVrApi.b()) == null) {
            GoogleVrApi.a(this);
            if ((googleVrProxy = GoogleVrApi.b()) == null) {
                com.unity3d.player.g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        this.a(new Runnable() {
            final /* synthetic */ Runnable b = new Runnable(this) {
                @Override
                public final void run() {
                    UnityPlayer.this.injectEvent((InputEvent)new KeyEvent(0, 4));
                    UnityPlayer.this.injectEvent((InputEvent)new KeyEvent(1, 4));
                }
            };
            
            @Override
            public final void run() {
                if (!googleVrProxy.a(UnityPlayer.currentActivity, UnityPlayer.this.r, UnityPlayer.this.b(), this.b)) {
                    com.unity3d.player.g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    googleVrProxy.a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                com.unity3d.player.g.Log(5, "Timeout while trying to initialize Google VR.");
                return false;
            }
        }
        catch (InterruptedException ex) {
            com.unity3d.player.g.Log(5, "UI thread was interrupted while initializing Google VR. " + ex.getLocalizedMessage());
            return false;
        }
        return googleVrProxy.a();
    }
    
    public boolean injectEvent(final InputEvent inputEvent) {
        return com.unity3d.player.n.c() && this.nativeInjectEvent(inputEvent);
    }
    
    protected boolean isFinishing() {
        boolean b = false;
        if (!this.u) {
            final boolean u = this.r instanceof Activity && ((Activity)this.r).isFinishing();
            this.u = u;
            if (!u) {
                return b;
            }
        }
        b = true;
        return b;
    }
    
    protected void kill() {
        Process.killProcess(Process.myPid());
    }
    
    protected boolean loadLibrary(final String s) {
        return loadLibraryStatic(s);
    }
    
    public void lowMemory() {
        if (!com.unity3d.player.n.c()) {
            return;
        }
        this.b(new Runnable() {
            @Override
            public final void run() {
                UnityPlayer.this.nativeLowMemory();
            }
        });
    }
    
    public boolean onGenericMotionEvent(final MotionEvent motionEvent) {
        return this.injectEvent((InputEvent)motionEvent);
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }
    
    public boolean onKeyLongPress(final int n, final KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }
    
    public boolean onKeyMultiple(final int n, final int n2, final KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }
    
    public boolean onKeyUp(final int n, final KeyEvent keyEvent) {
        return this.injectEvent((InputEvent)keyEvent);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        return this.injectEvent((InputEvent)motionEvent);
    }
    
    public void pause() {
        if (this.n != null) {
            this.n.c();
        }
        else if (this.o != null) {
            this.o.pauseARCore();
        }
        if (this.x != null) {
            this.x.a();
        }
        final GoogleVrProxy b = GoogleVrApi.b();
        if (b != null) {
            b.pauseGvrLayout();
        }
        this.d();
    }
    
    public void quit() {
        if (GoogleVrApi.b() != null) {
            GoogleVrApi.a();
        }
        if (this.q != null) {
            this.q.a();
            this.q = null;
        }
        this.u = true;
        if (!this.f.e()) {
            this.pause();
        }
        this.a.a();
        while (true) {
            try {
                this.a.join(4000L);
                if (this.h != null) {
                    this.r.unregisterReceiver(this.h);
                }
                this.h = null;
                if (com.unity3d.player.n.c()) {
                    this.removeAllViews();
                }
                if (this.v || !this.w) {
                    this.kill();
                }
                g();
            }
            catch (InterruptedException ex) {
                this.a.interrupt();
                continue;
            }
            break;
        }
    }
    
    public void removeViewFromPlayer(final View view) {
        int n = 1;
        this.a((View)this.s, view);
        boolean b;
        if (view.getParent() == null) {
            b = true;
        }
        else {
            b = false;
        }
        boolean b2;
        if (this.s.getParent() == this) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (!b || !b2) {
            n = 0;
        }
        if (n == 0) {
            if (!b) {
                com.unity3d.player.g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (!b2) {
                com.unity3d.player.g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
            }
        }
    }
    
    public void reportError(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        sb.append(s);
        sb.append(": ");
        sb.append(s2);
        com.unity3d.player.g.Log(6, sb.toString());
    }
    
    protected void reportSoftInputStr(final String s, final int n, final boolean b) {
        if (n == 1) {
            this.hideSoftInput();
        }
        this.a((f)new f() {
            @Override
            public final void a() {
                if (b) {
                    UnityPlayer.this.nativeSoftInputCanceled();
                }
                else if (s != null) {
                    UnityPlayer.this.nativeSetInputString(s);
                }
                if (n == 1) {
                    UnityPlayer.this.nativeSoftInputClosed();
                }
            }
        });
    }
    
    public void resume() {
        if (this.n != null) {
            this.n.d();
        }
        else if (this.o != null) {
            this.o.resumeARCore();
        }
        this.f.b(false);
        if (this.x != null) {
            this.x.b();
        }
        this.f();
        this.nativeRestartActivityIndicator();
        final GoogleVrProxy b = GoogleVrApi.b();
        if (b != null) {
            b.b();
        }
    }
    
    protected void setCharacterLimit(final int n) {
        this.a(new Runnable() {
            @Override
            public final void run() {
                if (UnityPlayer.this.b != null) {
                    UnityPlayer.this.b.a(n);
                }
            }
        });
    }
    
    protected void setClipboardText(final String s) {
        this.l.setPrimaryClip(ClipData.newPlainText((CharSequence)"Text", (CharSequence)s));
    }
    
    protected void setSoftInputStr(final String s) {
        this.a(new Runnable() {
            @Override
            public final void run() {
                if (UnityPlayer.this.b != null && s != null) {
                    UnityPlayer.this.b.a(s);
                }
            }
        });
    }
    
    protected void showSoftInput(final String s, final int n, final boolean b, final boolean b2, final boolean b3, final boolean b4, final String s2, final int n2) {
        this.a(new Runnable() {
            @Override
            public final void run() {
                (UnityPlayer.this.b = new k(UnityPlayer.this.r, UnityPlayer.this, s, n, b, b2, b3, s2, n2)).show();
            }
        });
    }
    
    protected boolean showVideoPlayer(final String s, final int n, final int n2, final int n3, final boolean b, final int n4, final int n5) {
        if (this.x == null) {
            this.x = new q(this);
        }
        final boolean a = this.x.a(this.r, s, n, n2, n3, b, n4, n5, (q.a)new q.a() {
            @Override
            public final void a() {
                UnityPlayer.this.x = null;
            }
        });
        if (a) {
            this.a(new Runnable() {
                @Override
                public final void run() {
                    if (UnityPlayer.this.nativeIsAutorotationOn() && UnityPlayer.this.r instanceof Activity) {
                        ((Activity)UnityPlayer.this.r).setRequestedOrientation(UnityPlayer.this.c);
                    }
                }
            });
        }
        return a;
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    protected void toggleGyroscopeSensor(final boolean b) {
        final SensorManager sensorManager = (SensorManager)this.r.getSystemService("sensor");
        final Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (b) {
            sensorManager.registerListener((SensorEventListener)this.p, defaultSensor, 1);
            return;
        }
        sensorManager.unregisterListener((SensorEventListener)this.p);
    }
    
    public void windowFocusChanged(final boolean b) {
        this.f.a(b);
        if (b && this.b != null) {
            this.nativeSoftInputLostFocus();
            this.reportSoftInputStr(null, 1, false);
        }
        if (b) {
            this.a.c();
        }
        else {
            this.a.d();
        }
        this.f();
    }
    
    final class a implements SensorEventListener
    {
        public final void onAccuracyChanged(final Sensor sensor, final int n) {
        }
        
        public final void onSensorChanged(final SensorEvent sensorEvent) {
        }
    }
    
    enum b
    {
        public static final int a;
        public static final int b;
        public static final int c;
        
        static {
            a = 1;
            b = 2;
            c = 3;
            d = new int[] { UnityPlayer.b.a, UnityPlayer.b.b, UnityPlayer.b.c };
        }
    }
    
    private final class c extends PhoneStateListener
    {
        public final void onCallStateChanged(final int n, final String s) {
            boolean b = true;
            final UnityPlayer a = UnityPlayer.this;
            if (n != 1) {
                b = false;
            }
            a.nativeMuteMasterAudio(b);
        }
    }
    
    enum d
    {
        a("PAUSE", 0), 
        b("RESUME", 1), 
        c("QUIT", 2), 
        d("SURFACE_LOST", 3), 
        e("SURFACE_ACQUIRED", 4), 
        f("FOCUS_LOST", 5), 
        g("FOCUS_GAINED", 6), 
        h("NEXT_FRAME", 7);
        
        static {
            i = new d[] { d.a, d.b, d.c, d.d, d.e, d.f, d.g, d.h };
        }
        
        private d(final String s, final int n) {
        }
    }
    
    private final class e extends Thread
    {
        Handler a;
        boolean b;
        boolean c;
        int d;
        int e;
        
        private e() {
            this.b = false;
            this.c = false;
            this.d = UnityPlayer.b.b;
            this.e = 5;
        }
        
        private void a(final d d) {
            Message.obtain(this.a, 2269, (Object)d).sendToTarget();
        }
        
        public final void a() {
            this.a(UnityPlayer.d.c);
        }
        
        public final void a(final Runnable runnable) {
            this.a(UnityPlayer.d.a);
            Message.obtain(this.a, runnable).sendToTarget();
        }
        
        public final void b() {
            this.a(UnityPlayer.d.b);
        }
        
        public final void b(final Runnable runnable) {
            this.a(UnityPlayer.d.d);
            Message.obtain(this.a, runnable).sendToTarget();
        }
        
        public final void c() {
            this.a(UnityPlayer.d.g);
        }
        
        public final void c(final Runnable runnable) {
            Message.obtain(this.a, runnable).sendToTarget();
            this.a(UnityPlayer.d.e);
        }
        
        public final void d() {
            this.a(UnityPlayer.d.f);
        }
        
        @Override
        public final void run() {
            this.setName("UnityMain");
            Looper.prepare();
            this.a = new Handler((Handler$Callback)new Handler$Callback() {
                private void a() {
                    if (UnityPlayer.e.this.d == UnityPlayer.b.c && UnityPlayer.e.this.c) {
                        UnityPlayer.this.nativeFocusChanged(true);
                        UnityPlayer.e.this.d = UnityPlayer.b.a;
                    }
                }
                
                public final boolean handleMessage(final Message message) {
                    if (message.what != 2269) {
                        return false;
                    }
                    final d d = (d)message.obj;
                    if (d == UnityPlayer.d.h) {
                        return true;
                    }
                    if (d == UnityPlayer.d.c) {
                        Looper.myLooper().quit();
                    }
                    else if (d == UnityPlayer.d.b) {
                        UnityPlayer.e.this.b = true;
                    }
                    else if (d == UnityPlayer.d.a) {
                        UnityPlayer.e.this.b = false;
                    }
                    else if (d == UnityPlayer.d.d) {
                        UnityPlayer.e.this.c = false;
                    }
                    else if (d == UnityPlayer.d.e) {
                        UnityPlayer.e.this.c = true;
                        this.a();
                    }
                    else if (d == UnityPlayer.d.f) {
                        if (UnityPlayer.e.this.d == UnityPlayer.b.a) {
                            UnityPlayer.this.nativeFocusChanged(false);
                        }
                        UnityPlayer.e.this.d = UnityPlayer.b.b;
                    }
                    else if (d == UnityPlayer.d.g) {
                        UnityPlayer.e.this.d = UnityPlayer.b.c;
                        this.a();
                    }
                    return true;
                }
            });
            Looper.myQueue().addIdleHandler((MessageQueue$IdleHandler)new MessageQueue$IdleHandler() {
                public final boolean queueIdle() {
                    UnityPlayer.this.executeGLThreadJobs();
                    if (UnityPlayer.e.this.b && UnityPlayer.e.this.c) {
                        if (UnityPlayer.e.this.e >= 0) {
                            if (UnityPlayer.e.this.e == 0 && UnityPlayer.this.i()) {
                                UnityPlayer.this.a();
                            }
                            final e a = UnityPlayer.e.this;
                            --a.e;
                        }
                        if (!UnityPlayer.this.isFinishing() && !UnityPlayer.this.nativeRender()) {
                            UnityPlayer.this.c();
                        }
                        Message.obtain(UnityPlayer.e.this.a, 2269, (Object)UnityPlayer.d.h).sendToTarget();
                        return true;
                    }
                    return true;
                }
            });
            Looper.loop();
        }
    }
    
    private abstract class f implements Runnable
    {
        public abstract void a();
        
        @Override
        public final void run() {
            if (!UnityPlayer.this.isFinishing()) {
                this.a();
            }
        }
    }
}
