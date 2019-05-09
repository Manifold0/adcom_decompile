package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.player.GoogleVrVideo.GoogleVrVideoCallbacks;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

class GoogleVrProxy extends C0203c implements GoogleVrVideo {
    /* renamed from: f */
    private boolean f35f = false;
    /* renamed from: g */
    private boolean f36g = false;
    /* renamed from: h */
    private Runnable f37h = null;
    /* renamed from: i */
    private Vector f38i = new Vector();
    /* renamed from: j */
    private SurfaceView f39j = null;
    /* renamed from: k */
    private C0208a f40k = new C0208a(this);
    /* renamed from: l */
    private Thread f41l = null;
    /* renamed from: m */
    private Handler f42m = new Handler(this, Looper.getMainLooper()) {
        /* renamed from: a */
        final /* synthetic */ GoogleVrProxy f17a;

        public final void handleMessage(Message message) {
            switch (message.what) {
                case 135711:
                    switch (message.arg1) {
                        case 2147483645:
                            Iterator it = this.f17a.f38i.iterator();
                            while (it.hasNext()) {
                                ((GoogleVrVideoCallbacks) it.next()).onFrameAvailable();
                            }
                            return;
                        case 2147483646:
                            Surface surface = (Surface) message.obj;
                            Iterator it2 = this.f17a.f38i.iterator();
                            while (it2.hasNext()) {
                                ((GoogleVrVideoCallbacks) it2.next()).onSurfaceAvailable(surface);
                            }
                            return;
                        default:
                            super.handleMessage(message);
                            return;
                    }
                default:
                    super.handleMessage(message);
                    return;
            }
        }
    };

    /* renamed from: com.unity3d.player.GoogleVrProxy$4 */
    class C02074 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ GoogleVrProxy f27a;

        C02074(GoogleVrProxy googleVrProxy) {
            this.f27a = googleVrProxy;
        }

        public final void run() {
            try {
                if (this.f27a.a != null) {
                    this.f27a.a.m177a("unload", new Object[0]);
                    this.f27a.a.m177a("deinitialize", new Object[0]);
                    this.f27a.a = null;
                }
                this.f27a.f40k.f29b = false;
            } catch (Exception e) {
                this.f27a.reportError("Exception unloading Google VR on UI Thread. " + e.getLocalizedMessage());
            }
        }
    }

    /* renamed from: com.unity3d.player.GoogleVrProxy$a */
    class C0208a {
        /* renamed from: a */
        public boolean f28a = false;
        /* renamed from: b */
        public boolean f29b = false;
        /* renamed from: c */
        public boolean f30c = false;
        /* renamed from: d */
        public boolean f31d = false;
        /* renamed from: e */
        public boolean f32e = true;
        /* renamed from: f */
        public boolean f33f = false;
        /* renamed from: g */
        final /* synthetic */ GoogleVrProxy f34g;

        C0208a(GoogleVrProxy googleVrProxy) {
            this.f34g = googleVrProxy;
        }

        /* renamed from: a */
        public final boolean m33a() {
            return this.f28a && this.f29b;
        }

        /* renamed from: b */
        public final void m34b() {
            this.f28a = false;
            this.f29b = false;
            this.f31d = false;
            this.f32e = true;
            this.f33f = false;
        }
    }

    public GoogleVrProxy(C0230f c0230f) {
        super("Google VR", c0230f);
        initVrJni();
    }

    /* renamed from: a */
    private void m37a(boolean z) {
        this.f40k.f31d = z;
    }

    /* renamed from: a */
    private static boolean m38a(int i) {
        return VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    private boolean m39a(ClassLoader classLoader) {
        try {
            Class loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            C0257o c0257o = new C0257o(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            c0257o.m178a("initialize", new Class[]{Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class});
            c0257o.m178a("deinitialize", new Class[0]);
            c0257o.m178a("load", new Class[]{Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class});
            c0257o.m178a("enable", new Class[]{Boolean.TYPE});
            c0257o.m178a("unload", new Class[0]);
            c0257o.m178a("pause", new Class[0]);
            c0257o.m178a("resume", new Class[0]);
            c0257o.m178a("getGvrLayout", new Class[0]);
            c0257o.m178a("getVideoSurfaceId", new Class[0]);
            c0257o.m178a("getVideoSurface", new Class[0]);
            this.a = c0257o;
            return true;
        } catch (Exception e) {
            reportError("Exception initializing GoogleVR from Unity library. " + e.getLocalizedMessage());
            return false;
        }
    }

    /* renamed from: d */
    private boolean m42d() {
        return this.f40k.f31d;
    }

    /* renamed from: e */
    private void m44e() {
        Activity activity = (Activity) this.c;
        if (this.f36g && !this.f40k.f33f && activity != null) {
            this.f40k.f33f = true;
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(DriveFile.MODE_READ_ONLY);
            activity.startActivity(intent);
        }
    }

    private final native void initVrJni();

    private final native boolean isQuiting();

    private final native void setVrVideoTransform(float[][] fArr);

    /* renamed from: a */
    public final void m45a(Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.f36g = true;
        }
    }

    /* renamed from: a */
    public final boolean m46a() {
        return this.f40k.f28a;
    }

    /* renamed from: a */
    public final boolean m47a(Activity activity, Context context, SurfaceView surfaceView, Runnable runnable) {
        if (activity == null || context == null || surfaceView == null || runnable == null) {
            reportError("Invalid parameters passed to Google VR initiialization.");
            return false;
        }
        this.f40k.m34b();
        this.c = context;
        this.f37h = runnable;
        if (!m38a(19)) {
            reportError("Google VR requires a device that supports an api version of 19 (KitKat) or better.");
            return false;
        } else if (this.f36g && !m38a(24)) {
            reportError("Daydream requires a device that supports an api version of 24 (Nougat) or better.");
            return false;
        } else if (!m39a(UnityPlayer.class.getClassLoader())) {
            return false;
        } else {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) this.a.m177a("initialize", activity, context, surfaceView, Boolean.valueOf(this.f36g), this.f42m)).booleanValue();
            } catch (Exception e) {
                reportError("Exception while trying to intialize Unity Google VR Library. " + e.getLocalizedMessage());
                booleanValue = false;
            }
            if (booleanValue) {
                this.f39j = surfaceView;
                this.f40k.f28a = true;
                this.d = "";
                return true;
            }
            reportError("Unable to initialize GoogleVR library.");
            return false;
        }
    }

    /* renamed from: b */
    public final void m48b() {
        resumeGvrLayout();
    }

    /* renamed from: c */
    public final void m49c() {
        if (this.f39j != null) {
            this.f39j.getHolder().setSizeFromLayout();
        }
    }

    public void deregisterGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.f38i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.f38i.remove(googleVrVideoCallbacks);
        }
    }

    protected Object getVideoSurface() {
        Object obj = null;
        if (m42d() && !this.f40k.f32e) {
            try {
                obj = this.a.m177a("getVideoSurface", new Object[0]);
            } catch (Exception e) {
                reportError("Exception caught while Getting GoogleVR Video Surface. " + e.getLocalizedMessage());
            }
        }
        return obj;
    }

    protected int getVideoSurfaceId() {
        if (!m42d() || this.f40k.f32e) {
            return -1;
        }
        try {
            return ((Integer) this.a.m177a("getVideoSurfaceId", new Object[0])).intValue();
        } catch (Exception e) {
            reportError("Exception caught while getting Video Surface ID from GoogleVR. " + e.getLocalizedMessage());
            return -1;
        }
    }

    protected long loadGoogleVr(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (!this.f40k.f28a) {
            return 0;
        }
        final AtomicLong atomicLong = new AtomicLong(0);
        String str = (z || z2) ? "Daydream" : "Cardboard";
        this.d = str;
        final boolean z6 = z;
        final boolean z7 = z2;
        final boolean z8 = z3;
        final boolean z9 = z4;
        final boolean z10 = z5;
        if (!runOnUiThreadWithSync(new Runnable(this) {
            /* renamed from: g */
            final /* synthetic */ GoogleVrProxy f24g;

            public final void run() {
                try {
                    atomicLong.set(((Long) this.f24g.a.m177a("load", Boolean.valueOf(z6), Boolean.valueOf(z7), Boolean.valueOf(z8), Boolean.valueOf(z9), Boolean.valueOf(z10), this.f24g.f37h)).longValue());
                    this.f24g.f40k.f29b = true;
                } catch (Exception e) {
                    this.f24g.reportError("Exception caught while loading GoogleVR. " + e.getLocalizedMessage());
                    atomicLong.set(0);
                }
            }
        }) || atomicLong.longValue() == 0) {
            reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }

    protected void pauseGvrLayout() {
        if (this.f40k.m33a() && !this.f40k.f32e) {
            if (m42d()) {
                Iterator it = this.f38i.iterator();
                while (it.hasNext()) {
                    ((GoogleVrVideoCallbacks) it.next()).onSurfaceUnavailable();
                }
            }
            if (this.a != null) {
                this.a.m177a("pause", new Object[0]);
            }
            this.f40k.f32e = true;
        }
    }

    public void registerGoogleVrVideoListener(GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.f38i.contains(googleVrVideoCallbacks)) {
            this.f38i.add(googleVrVideoCallbacks);
            Surface surface = (Surface) getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }

    protected void resumeGvrLayout() {
        if (this.f40k.m33a() && this.f40k.f32e) {
            if (this.a != null) {
                this.a.m177a("resume", new Object[0]);
            }
            this.f40k.f32e = false;
        }
    }

    protected void setGoogleVrModeEnabled(final boolean z) {
        if (this.f40k.m33a() && this.b != null && this.c != null) {
            if (!z && isQuiting()) {
                m44e();
            }
            runOnUiThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ GoogleVrProxy f26b;

                public final void run() {
                    if (z != this.f26b.m42d()) {
                        try {
                            if (!z || this.f26b.m42d()) {
                                if (!z && this.f26b.m42d()) {
                                    this.f26b.m37a(false);
                                    if (this.f26b.a != null) {
                                        this.f26b.a.m177a("enable", Boolean.valueOf(false));
                                    }
                                    if (this.f26b.a != null && this.f26b.b != null) {
                                        this.f26b.b.removeViewFromPlayer((View) this.f26b.a.m177a("getGvrLayout", new Object[0]));
                                    }
                                }
                            } else if (this.f26b.a == null || this.f26b.b == null || this.f26b.b.addViewToPlayer((View) this.f26b.a.m177a("getGvrLayout", new Object[0]), true)) {
                                if (this.f26b.a != null) {
                                    this.f26b.a.m177a("enable", Boolean.valueOf(true));
                                }
                                this.f26b.m37a(true);
                            } else {
                                this.f26b.reportError("Unable to add Google VR to view hierarchy.");
                            }
                        } catch (Exception e) {
                            this.f26b.reportError("Exception enabling Google VR on UI Thread. " + e.getLocalizedMessage());
                        }
                    }
                }
            });
        }
    }

    public void setVideoLocationTransform(float[] fArr) {
        float[][] fArr2 = (float[][]) Array.newInstance(Float.TYPE, new int[]{4, 4});
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                fArr2[i][i2] = fArr[(i * 4) + i2];
            }
        }
        setVrVideoTransform(fArr2);
    }

    protected void unloadGoogleVr() {
        if (this.f40k.f31d) {
            setGoogleVrModeEnabled(false);
        }
        if (this.f40k.f30c) {
            this.f40k.f30c = false;
        }
        this.f39j = null;
        runOnUiThread(new C02074(this));
    }
}
