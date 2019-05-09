package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.os.MessageQueue.IdleHandler;
import android.os.Process;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.facebook.places.model.PlaceFields;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.unity3d.player.C0253l.C0252a;
import com.unity3d.player.C0267q.C0213a;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class UnityPlayer extends FrameLayout implements C0230f {
    public static Activity currentActivity = null;
    /* renamed from: t */
    private static boolean f120t;
    /* renamed from: a */
    C0229e f121a = new C0229e();
    /* renamed from: b */
    C0250k f122b = null;
    /* renamed from: c */
    private int f123c = -1;
    /* renamed from: d */
    private boolean f124d = false;
    /* renamed from: e */
    private boolean f125e = true;
    /* renamed from: f */
    private C0255n f126f = new C0255n();
    /* renamed from: g */
    private final ConcurrentLinkedQueue f127g = new ConcurrentLinkedQueue();
    /* renamed from: h */
    private BroadcastReceiver f128h = null;
    /* renamed from: i */
    private boolean f129i = false;
    /* renamed from: j */
    private C0225c f130j = new C0225c();
    /* renamed from: k */
    private TelephonyManager f131k;
    /* renamed from: l */
    private ClipboardManager f132l;
    /* renamed from: m */
    private C0253l f133m;
    /* renamed from: n */
    private GoogleARProxy f134n = null;
    /* renamed from: o */
    private GoogleARCoreApi f135o = null;
    /* renamed from: p */
    private C0223a f136p = new C0223a(this);
    /* renamed from: q */
    private Camera2Wrapper f137q = null;
    /* renamed from: r */
    private Context f138r;
    /* renamed from: s */
    private SurfaceView f139s;
    /* renamed from: u */
    private boolean f140u;
    /* renamed from: v */
    private boolean f141v;
    /* renamed from: w */
    private boolean f142w = false;
    /* renamed from: x */
    private C0267q f143x;

    /* renamed from: com.unity3d.player.UnityPlayer$f */
    private abstract class C0212f implements Runnable {
        /* renamed from: e */
        final /* synthetic */ UnityPlayer f52e;

        private C0212f(UnityPlayer unityPlayer) {
            this.f52e = unityPlayer;
        }

        /* renamed from: a */
        public abstract void mo687a();

        public final void run() {
            if (!this.f52e.isFinishing()) {
                mo687a();
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$1 */
    class C02141 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f72a;

        /* renamed from: com.unity3d.player.UnityPlayer$1$1 */
        class C02111 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C02141 f51a;

            C02111(C02141 c02141) {
                this.f51a = c02141;
            }

            public final void run() {
                this.f51a.f72a.f126f.m172d();
                this.f51a.f72a.m96f();
            }
        }

        C02141(UnityPlayer unityPlayer) {
            this.f72a = unityPlayer;
        }

        public final void run() {
            this.f72a.m119a(new C02111(this));
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$2 */
    class C02152 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f77a;

        C02152(UnityPlayer unityPlayer) {
            this.f77a = unityPlayer;
        }

        public final void run() {
            this.f77a.nativeLowMemory();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$3 */
    class C02163 extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f78a;

        public void onReceive(Context context, Intent intent) {
            this.f78a.m90c();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$4 */
    class C02174 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f79a;

        C02174(UnityPlayer unityPlayer) {
            this.f79a = unityPlayer;
        }

        public final void run() {
            this.f79a.nativeResume();
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$6 */
    class C02196 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f90a;

        C02196(UnityPlayer unityPlayer) {
            this.f90a = unityPlayer;
        }

        public final void run() {
            if (this.f90a.f122b != null) {
                this.f90a.f122b.dismiss();
                this.f90a.f122b = null;
            }
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$a */
    class C0223a implements SensorEventListener {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f97a;

        C0223a(UnityPlayer unityPlayer) {
            this.f97a = unityPlayer;
        }

        public final void onAccuracyChanged(Sensor sensor, int i) {
        }

        public final void onSensorChanged(SensorEvent sensorEvent) {
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$b */
    enum C0224b {
        ;

        static {
            f98a = 1;
            f99b = 2;
            f100c = 3;
            f101d = new int[]{f98a, f99b, f100c};
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$c */
    private class C0225c extends PhoneStateListener {
        /* renamed from: a */
        final /* synthetic */ UnityPlayer f102a;

        private C0225c(UnityPlayer unityPlayer) {
            this.f102a = unityPlayer;
        }

        public final void onCallStateChanged(int i, String str) {
            boolean z = true;
            UnityPlayer unityPlayer = this.f102a;
            if (i != 1) {
                z = false;
            }
            unityPlayer.nativeMuteMasterAudio(z);
        }
    }

    /* renamed from: com.unity3d.player.UnityPlayer$d */
    enum C0226d {
        PAUSE,
        RESUME,
        QUIT,
        SURFACE_LOST,
        SURFACE_ACQUIRED,
        FOCUS_LOST,
        FOCUS_GAINED,
        NEXT_FRAME
    }

    /* renamed from: com.unity3d.player.UnityPlayer$e */
    private class C0229e extends Thread {
        /* renamed from: a */
        Handler f114a;
        /* renamed from: b */
        boolean f115b;
        /* renamed from: c */
        boolean f116c;
        /* renamed from: d */
        int f117d;
        /* renamed from: e */
        int f118e;
        /* renamed from: f */
        final /* synthetic */ UnityPlayer f119f;

        /* renamed from: com.unity3d.player.UnityPlayer$e$1 */
        class C02271 implements Callback {
            /* renamed from: a */
            final /* synthetic */ C0229e f112a;

            C02271(C0229e c0229e) {
                this.f112a = c0229e;
            }

            /* renamed from: a */
            private void m65a() {
                if (this.f112a.f117d == C0224b.f100c && this.f112a.f116c) {
                    this.f112a.f119f.nativeFocusChanged(true);
                    this.f112a.f117d = C0224b.f98a;
                }
            }

            public final boolean handleMessage(Message message) {
                if (message.what != 2269) {
                    return false;
                }
                C0226d c0226d = (C0226d) message.obj;
                if (c0226d == C0226d.NEXT_FRAME) {
                    return true;
                }
                if (c0226d == C0226d.QUIT) {
                    Looper.myLooper().quit();
                } else if (c0226d == C0226d.RESUME) {
                    this.f112a.f115b = true;
                } else if (c0226d == C0226d.PAUSE) {
                    this.f112a.f115b = false;
                } else if (c0226d == C0226d.SURFACE_LOST) {
                    this.f112a.f116c = false;
                } else if (c0226d == C0226d.SURFACE_ACQUIRED) {
                    this.f112a.f116c = true;
                    m65a();
                } else if (c0226d == C0226d.FOCUS_LOST) {
                    if (this.f112a.f117d == C0224b.f98a) {
                        this.f112a.f119f.nativeFocusChanged(false);
                    }
                    this.f112a.f117d = C0224b.f99b;
                } else if (c0226d == C0226d.FOCUS_GAINED) {
                    this.f112a.f117d = C0224b.f100c;
                    m65a();
                }
                return true;
            }
        }

        /* renamed from: com.unity3d.player.UnityPlayer$e$2 */
        class C02282 implements IdleHandler {
            /* renamed from: a */
            final /* synthetic */ C0229e f113a;

            C02282(C0229e c0229e) {
                this.f113a = c0229e;
            }

            public final boolean queueIdle() {
                this.f113a.f119f.executeGLThreadJobs();
                if (this.f113a.f115b && this.f113a.f116c) {
                    if (this.f113a.f118e >= 0) {
                        if (this.f113a.f118e == 0 && this.f113a.f119f.m102i()) {
                            this.f113a.f119f.m74a();
                        }
                        C0229e c0229e = this.f113a;
                        c0229e.f118e--;
                    }
                    if (!(this.f113a.f119f.isFinishing() || this.f113a.f119f.nativeRender())) {
                        this.f113a.f119f.m90c();
                    }
                    Message.obtain(this.f113a.f114a, 2269, C0226d.NEXT_FRAME).sendToTarget();
                }
                return true;
            }
        }

        private C0229e(UnityPlayer unityPlayer) {
            this.f119f = unityPlayer;
            this.f115b = false;
            this.f116c = false;
            this.f117d = C0224b.f99b;
            this.f118e = 5;
        }

        /* renamed from: a */
        private void m66a(C0226d c0226d) {
            Message.obtain(this.f114a, 2269, c0226d).sendToTarget();
        }

        /* renamed from: a */
        public final void m67a() {
            m66a(C0226d.QUIT);
        }

        /* renamed from: a */
        public final void m68a(Runnable runnable) {
            m66a(C0226d.PAUSE);
            Message.obtain(this.f114a, runnable).sendToTarget();
        }

        /* renamed from: b */
        public final void m69b() {
            m66a(C0226d.RESUME);
        }

        /* renamed from: b */
        public final void m70b(Runnable runnable) {
            m66a(C0226d.SURFACE_LOST);
            Message.obtain(this.f114a, runnable).sendToTarget();
        }

        /* renamed from: c */
        public final void m71c() {
            m66a(C0226d.FOCUS_GAINED);
        }

        /* renamed from: c */
        public final void m72c(Runnable runnable) {
            Message.obtain(this.f114a, runnable).sendToTarget();
            m66a(C0226d.SURFACE_ACQUIRED);
        }

        /* renamed from: d */
        public final void m73d() {
            m66a(C0226d.FOCUS_LOST);
        }

        public final void run() {
            setName("UnityMain");
            Looper.prepare();
            this.f114a = new Handler(new C02271(this));
            Looper.myQueue().addIdleHandler(new C02282(this));
            Looper.loop();
        }
    }

    static {
        new C0254m().m165a();
        f120t = false;
        f120t = loadLibraryStatic(ParametersKeys.MAIN);
    }

    public UnityPlayer(Context context) {
        super(context);
        if (context instanceof Activity) {
            currentActivity = (Activity) context;
            this.f123c = currentActivity.getRequestedOrientation();
        }
        m76a(currentActivity);
        this.f138r = context;
        if (currentActivity != null && m102i()) {
            this.f133m = new C0253l(this.f138r, C0252a.m164a()[getSplashMode()]);
            addView(this.f133m);
        }
        if (C0246j.f190c) {
            if (currentActivity != null) {
                C0246j.f191d.mo694a(currentActivity, new C02141(this));
            } else {
                this.f126f.m172d();
            }
        }
        m77a(this.f138r.getApplicationInfo());
        if (C0255n.m168c()) {
            initJni(context);
            this.f139s = m85b();
            addView(this.f139s);
            bringChildToFront(this.f133m);
            this.f140u = false;
            nativeInitWebRequest(UnityWebRequest.class);
            m106k();
            this.f131k = (TelephonyManager) this.f138r.getSystemService(PlaceFields.PHONE);
            this.f132l = (ClipboardManager) this.f138r.getSystemService("clipboard");
            this.f137q = new Camera2Wrapper(this.f138r);
            this.f121a.start();
            return;
        }
        AlertDialog create = new Builder(this.f138r).setTitle("Failure to initialize!").setPositiveButton("OK", new OnClickListener(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f62a;

            {
                this.f62a = r1;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f62a.m90c();
            }
        }).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (C0255n.m168c()) {
            nativeUnitySendMessage(str, str2, str3);
        } else {
            C0243g.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
        }
    }

    /* renamed from: a */
    private void m74a() {
        m119a(new Runnable(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f65a;

            {
                this.f65a = r1;
            }

            public final void run() {
                this.f65a.removeView(this.f65a.f133m);
                this.f65a.f133m = null;
            }
        });
    }

    /* renamed from: a */
    private void m75a(int i, Surface surface) {
        if (!this.f124d) {
            m89b(0, surface);
        }
    }

    /* renamed from: a */
    private static void m76a(Activity activity) {
        if (activity != null && activity.getIntent().getBooleanExtra("android.intent.extra.VR_LAUNCH", false) && activity.getWindow() != null) {
            View decorView = activity.getWindow().getDecorView();
            if (decorView != null) {
                decorView.setSystemUiVisibility(7);
            }
        }
    }

    /* renamed from: a */
    private static void m77a(ApplicationInfo applicationInfo) {
        if (f120t && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            C0255n.m166a();
        }
    }

    /* renamed from: a */
    private void m78a(View view, View view2) {
        int i;
        if (this.f126f.m173e()) {
            i = 0;
        } else {
            pause();
            i = 1;
        }
        if (view != null) {
            ViewParent parent = view.getParent();
            if (!((parent instanceof UnityPlayer) && ((UnityPlayer) parent) == this)) {
                if (parent instanceof ViewGroup) {
                    ((ViewGroup) parent).removeView(view);
                }
                addView(view);
                bringChildToFront(view);
                view.setVisibility(0);
            }
        }
        if (view2 != null && view2.getParent() == this) {
            view2.setVisibility(8);
            removeView(view2);
        }
        if (i != 0) {
            resume();
        }
    }

    /* renamed from: a */
    private void m79a(C0212f c0212f) {
        if (!isFinishing()) {
            m88b((Runnable) c0212f);
        }
    }

    /* renamed from: b */
    private SurfaceView m85b() {
        SurfaceView surfaceView = new SurfaceView(this.f138r);
        surfaceView.getHolder().setFormat(-3);
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f66a;

            {
                this.f66a = r1;
            }

            public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                this.f66a.m75a(0, surfaceHolder.getSurface());
            }

            public final void surfaceCreated(SurfaceHolder surfaceHolder) {
                this.f66a.m75a(0, surfaceHolder.getSurface());
            }

            public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                this.f66a.m75a(0, null);
            }
        });
        surfaceView.setFocusable(true);
        surfaceView.setFocusableInTouchMode(true);
        return surfaceView;
    }

    /* renamed from: b */
    private void m88b(Runnable runnable) {
        if (!C0255n.m168c()) {
            return;
        }
        if (Thread.currentThread() == this.f121a) {
            runnable.run();
        } else {
            this.f127g.add(runnable);
        }
    }

    /* renamed from: b */
    private boolean m89b(final int i, final Surface surface) {
        if (!C0255n.m168c()) {
            return false;
        }
        final Semaphore semaphore = new Semaphore(0);
        Runnable anonymousClass18 = new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ UnityPlayer f70d;

            public final void run() {
                this.f70d.nativeRecreateGfxState(i, surface);
                semaphore.release();
            }
        };
        if (i != 0) {
            anonymousClass18.run();
        } else if (surface == null) {
            this.f121a.m70b(anonymousClass18);
        } else {
            this.f121a.m72c(anonymousClass18);
        }
        if (surface == null && i == 0) {
            try {
                if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                    C0243g.Log(5, "Timeout while trying detaching primary window.");
                }
            } catch (InterruptedException e) {
                C0243g.Log(5, "UI thread got interrupted while trying to detach the primary window from the Unity Engine.");
            }
        }
        return true;
    }

    /* renamed from: c */
    private void m90c() {
        if ((this.f138r instanceof Activity) && !((Activity) this.f138r).isFinishing()) {
            ((Activity) this.f138r).finish();
        }
    }

    /* renamed from: d */
    private void m92d() {
        reportSoftInputStr(null, 1, true);
        if (this.f126f.m175g()) {
            if (C0255n.m168c()) {
                final Semaphore semaphore = new Semaphore(0);
                this.f121a.m68a(isFinishing() ? new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ UnityPlayer f74b;

                    public final void run() {
                        this.f74b.m95e();
                        semaphore.release();
                    }
                } : new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ UnityPlayer f76b;

                    public final void run() {
                        if (this.f76b.nativePause()) {
                            this.f76b.f140u = true;
                            this.f76b.m95e();
                            semaphore.release(2);
                            return;
                        }
                        semaphore.release();
                    }
                });
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        C0243g.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    C0243g.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.f126f.m171c(false);
            this.f126f.m170b(true);
            if (this.f129i) {
                this.f131k.listen(this.f130j, 0);
            }
        }
    }

    /* renamed from: e */
    private void m95e() {
        this.f141v = nativeShouldQuit();
        this.f142w = true;
        nativeDone();
    }

    /* renamed from: f */
    private void m96f() {
        if (this.f126f.m174f()) {
            this.f126f.m171c(true);
            m88b(new C02174(this));
            this.f121a.m69b();
        }
    }

    /* renamed from: g */
    private static void m99g() {
        if (!C0255n.m168c()) {
            return;
        }
        if (NativeLoader.unload()) {
            C0255n.m167b();
            return;
        }
        throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
    }

    /* renamed from: h */
    private ApplicationInfo m100h() {
        return this.f138r.getPackageManager().getApplicationInfo(this.f138r.getPackageName(), 128);
    }

    /* renamed from: i */
    private boolean m102i() {
        try {
            return m100h().metaData.getBoolean("unity.splash-enable");
        } catch (Exception e) {
            return false;
        }
    }

    private final native void initJni(Context context);

    /* renamed from: j */
    private boolean m105j() {
        try {
            return m100h().metaData.getBoolean("unity.tango-enable");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: k */
    private void m106k() {
        if (this.f138r instanceof Activity) {
            ((Activity) this.f138r).getWindow().setFlags(1024, 1024);
        }
    }

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            C0243g.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            C0243g.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    private final native void nativeDone();

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native boolean nativeIsAutorotationOn();

    private final native void nativeLowMemory();

    private final native void nativeMuteMasterAudio(boolean z);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(int i, Surface surface);

    private final native boolean nativeRender();

    private final native void nativeRestartActivityIndicator();

    private final native void nativeResume();

    private final native void nativeSetInputString(String str);

    private final native boolean nativeShouldQuit();

    private final native void nativeSoftInputCanceled();

    private final native void nativeSoftInputClosed();

    private final native void nativeSoftInputLostFocus();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    /* renamed from: a */
    final void m119a(Runnable runnable) {
        if (this.f138r instanceof Activity) {
            ((Activity) this.f138r).runOnUiThread(runnable);
        } else {
            C0243g.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void addPhoneCallListener() {
        this.f129i = true;
        this.f131k.listen(this.f130j, 32);
    }

    public boolean addViewToPlayer(View view, boolean z) {
        boolean z2 = true;
        m78a(view, z ? this.f139s : null);
        boolean z3 = view.getParent() == this;
        boolean z4 = z && this.f139s.getParent() == null;
        boolean z5 = this.f139s.getParent() == this;
        if (!(z3 && (z4 || z5))) {
            z2 = false;
        }
        if (!z2) {
            if (!z3) {
                C0243g.Log(6, "addViewToPlayer: Failure adding view to hierarchy");
            }
            if (!(z4 || z5)) {
                C0243g.Log(6, "addViewToPlayer: Failure removing old view from hierarchy");
            }
        }
        return z2;
    }

    public void configurationChanged(Configuration configuration) {
        if (this.f139s instanceof SurfaceView) {
            this.f139s.getHolder().setSizeFromLayout();
        }
        if (this.f143x != null) {
            this.f143x.m203c();
        }
        GoogleVrProxy b = GoogleVrApi.m32b();
        if (b != null) {
            b.m49c();
        }
    }

    protected void disableLogger() {
        C0243g.f186a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.f124d = surface != null;
            m119a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ UnityPlayer f71a;

                {
                    this.f71a = r1;
                }

                public final void run() {
                    if (this.f71a.f124d) {
                        this.f71a.removeView(this.f71a.f139s);
                    } else {
                        this.f71a.addView(this.f71a.f139s);
                    }
                }
            });
        }
        return m89b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.f127g.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected String getClipboardText() {
        String str = "";
        ClipData primaryClip = this.f132l.getPrimaryClip();
        return primaryClip != null ? primaryClip.getItemAt(0).coerceToText(this.f138r).toString() : str;
    }

    public Bundle getSettings() {
        return Bundle.EMPTY;
    }

    protected int getSplashMode() {
        try {
            return m100h().metaData.getInt("unity.splash-mode");
        } catch (Exception e) {
            return 0;
        }
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        final Runnable c02196 = new C02196(this);
        if (C0246j.f189b) {
            m79a(new C0212f(this) {
                /* renamed from: b */
                final /* synthetic */ UnityPlayer f92b;

                /* renamed from: a */
                public final void mo687a() {
                    this.f92b.m119a(c02196);
                }
            });
        } else {
            m119a(c02196);
        }
    }

    public void init(int i, boolean z) {
    }

    protected boolean initializeGoogleAr() {
        if (this.f134n == null && currentActivity != null && m105j()) {
            if (GoogleARProxy.m23a()) {
                this.f134n = new GoogleARProxy(this);
                this.f134n.m25a(currentActivity, this.f138r);
                this.f134n.m26b();
                if (!this.f126f.m173e()) {
                    this.f134n.m28d();
                }
                return this.f134n.m29e();
            }
            this.f135o = new GoogleARCoreApi();
            this.f135o.initializeARCore(currentActivity);
            if (!this.f126f.m173e()) {
                this.f135o.resumeARCore();
            }
        }
        return false;
    }

    protected boolean initializeGoogleVr() {
        GoogleVrProxy b = GoogleVrApi.m32b();
        if (b == null) {
            GoogleVrApi.m31a(this);
            b = GoogleVrApi.m32b();
            if (b == null) {
                C0243g.Log(6, "Unable to create Google VR subsystem.");
                return false;
            }
        }
        final Semaphore semaphore = new Semaphore(0);
        final Runnable anonymousClass11 = new Runnable(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f57a;

            {
                this.f57a = r1;
            }

            public final void run() {
                this.f57a.injectEvent(new KeyEvent(0, 4));
                this.f57a.injectEvent(new KeyEvent(1, 4));
            }
        };
        m119a(new Runnable(this) {
            /* renamed from: d */
            final /* synthetic */ UnityPlayer f61d;

            public final void run() {
                if (!b.m47a(UnityPlayer.currentActivity, this.f61d.f138r, this.f61d.m85b(), anonymousClass11)) {
                    C0243g.Log(6, "Unable to initialize Google VR subsystem.");
                }
                if (UnityPlayer.currentActivity != null) {
                    b.m45a(UnityPlayer.currentActivity.getIntent());
                }
                semaphore.release();
            }
        });
        try {
            if (semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                return b.m46a();
            }
            C0243g.Log(5, "Timeout while trying to initialize Google VR.");
            return false;
        } catch (InterruptedException e) {
            C0243g.Log(5, "UI thread was interrupted while initializing Google VR. " + e.getLocalizedMessage());
            return false;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return !C0255n.m168c() ? false : nativeInjectEvent(inputEvent);
    }

    protected boolean isFinishing() {
        if (!this.f140u) {
            boolean z = (this.f138r instanceof Activity) && ((Activity) this.f138r).isFinishing();
            this.f140u = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    public void lowMemory() {
        if (C0255n.m168c()) {
            m88b(new C02152(this));
        }
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyLongPress(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.f134n != null) {
            this.f134n.m27c();
        } else if (this.f135o != null) {
            this.f135o.pauseARCore();
        }
        if (this.f143x != null) {
            this.f143x.m200a();
        }
        GoogleVrProxy b = GoogleVrApi.m32b();
        if (b != null) {
            b.pauseGvrLayout();
        }
        m92d();
    }

    public void quit() {
        if (GoogleVrApi.m32b() != null) {
            GoogleVrApi.m30a();
        }
        if (this.f137q != null) {
            this.f137q.m21a();
            this.f137q = null;
        }
        this.f140u = true;
        if (!this.f126f.m173e()) {
            pause();
        }
        this.f121a.m67a();
        try {
            this.f121a.join(4000);
        } catch (InterruptedException e) {
            this.f121a.interrupt();
        }
        if (this.f128h != null) {
            this.f138r.unregisterReceiver(this.f128h);
        }
        this.f128h = null;
        if (C0255n.m168c()) {
            removeAllViews();
        }
        if (this.f141v || !this.f142w) {
            kill();
        }
        m99g();
    }

    public void removeViewFromPlayer(View view) {
        Object obj = 1;
        m78a(this.f139s, view);
        Object obj2 = view.getParent() == null ? 1 : null;
        Object obj3 = this.f139s.getParent() == this ? 1 : null;
        if (obj2 == null || obj3 == null) {
            obj = null;
        }
        if (obj == null) {
            if (obj2 == null) {
                C0243g.Log(6, "removeViewFromPlayer: Failure removing view from hierarchy");
            }
            if (obj3 == null) {
                C0243g.Log(6, "removeVireFromPlayer: Failure agging old view to hierarchy");
            }
        }
    }

    public void reportError(String str, String str2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(": ");
        stringBuilder.append(str2);
        C0243g.Log(6, stringBuilder.toString());
    }

    protected void reportSoftInputStr(final String str, final int i, final boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        m79a(new C0212f(this) {
            /* renamed from: d */
            final /* synthetic */ UnityPlayer f56d;

            /* renamed from: a */
            public final void mo687a() {
                if (z) {
                    this.f56d.nativeSoftInputCanceled();
                } else if (str != null) {
                    this.f56d.nativeSetInputString(str);
                }
                if (i == 1) {
                    this.f56d.nativeSoftInputClosed();
                }
            }
        });
    }

    public void resume() {
        if (this.f134n != null) {
            this.f134n.m28d();
        } else if (this.f135o != null) {
            this.f135o.resumeARCore();
        }
        this.f126f.m170b(false);
        if (this.f143x != null) {
            this.f143x.m202b();
        }
        m96f();
        nativeRestartActivityIndicator();
        GoogleVrProxy b = GoogleVrApi.m32b();
        if (b != null) {
            b.m48b();
        }
    }

    protected void setCharacterLimit(final int i) {
        m119a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ UnityPlayer f96b;

            public final void run() {
                if (this.f96b.f122b != null) {
                    this.f96b.f122b.m162a(i);
                }
            }
        });
    }

    protected void setClipboardText(String str) {
        this.f132l.setPrimaryClip(ClipData.newPlainText("Text", str));
    }

    protected void setSoftInputStr(final String str) {
        m119a(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ UnityPlayer f94b;

            public final void run() {
                if (this.f94b.f122b != null && str != null) {
                    this.f94b.f122b.m163a(str);
                }
            }
        });
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2, int i2) {
        final UnityPlayer unityPlayer = this;
        final String str3 = str;
        final int i3 = i;
        final boolean z5 = z;
        final boolean z6 = z2;
        final boolean z7 = z3;
        final boolean z8 = z4;
        final String str4 = str2;
        final int i4 = i2;
        m119a(new Runnable(this) {
            /* renamed from: j */
            final /* synthetic */ UnityPlayer f89j;

            public final void run() {
                this.f89j.f122b = new C0250k(this.f89j.f138r, unityPlayer, str3, i3, z5, z6, z7, str4, i4);
                this.f89j.f122b.show();
            }
        });
    }

    protected boolean showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        if (this.f143x == null) {
            this.f143x = new C0267q(this);
        }
        boolean a = this.f143x.m201a(this.f138r, str, i, i2, i3, z, (long) i4, (long) i5, new C0213a(this) {
            /* renamed from: a */
            final /* synthetic */ UnityPlayer f63a;

            {
                this.f63a = r1;
            }

            /* renamed from: a */
            public final void mo688a() {
                this.f63a.f143x = null;
            }
        });
        if (a) {
            m119a(new Runnable(this) {
                /* renamed from: a */
                final /* synthetic */ UnityPlayer f64a;

                {
                    this.f64a = r1;
                }

                public final void run() {
                    if (this.f64a.nativeIsAutorotationOn() && (this.f64a.f138r instanceof Activity)) {
                        ((Activity) this.f64a.f138r).setRequestedOrientation(this.f64a.f123c);
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

    protected void toggleGyroscopeSensor(boolean z) {
        SensorManager sensorManager = (SensorManager) this.f138r.getSystemService("sensor");
        Sensor defaultSensor = sensorManager.getDefaultSensor(11);
        if (z) {
            sensorManager.registerListener(this.f136p, defaultSensor, 1);
        } else {
            sensorManager.unregisterListener(this.f136p);
        }
    }

    public void windowFocusChanged(boolean z) {
        this.f126f.m169a(z);
        if (z && this.f122b != null) {
            nativeSoftInputLostFocus();
            reportSoftInputStr(null, 1, false);
        }
        if (z) {
            this.f121a.m71c();
        } else {
            this.f121a.m73d();
        }
        m96f();
    }
}
