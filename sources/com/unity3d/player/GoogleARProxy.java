package com.unity3d.player;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.util.Log;

class GoogleARProxy extends C0203c {
    /* renamed from: f */
    private boolean f15f = false;

    /* renamed from: com.unity3d.player.GoogleARProxy$2 */
    class C02002 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ GoogleARProxy f7a;

        C02002(GoogleARProxy googleARProxy) {
            this.f7a = googleARProxy;
        }

        public final void run() {
            try {
                if (this.f7a.a != null) {
                    this.f7a.a.m177a("create", new Object[0]);
                }
            } catch (Exception e) {
                this.f7a.reportError("Exception creating " + this.f7a.e + " VR on UI Thread. " + e.getLocalizedMessage());
            }
        }
    }

    /* renamed from: com.unity3d.player.GoogleARProxy$3 */
    class C02013 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ GoogleARProxy f8a;

        C02013(GoogleARProxy googleARProxy) {
            this.f8a = googleARProxy;
        }

        public final void run() {
            try {
                if (this.f8a.a != null) {
                    this.f8a.a.m177a("pause", new Object[0]);
                }
            } catch (Exception e) {
                this.f8a.reportError("Exception pausing " + this.f8a.e + " VR on UI Thread. " + e.getLocalizedMessage());
            }
        }
    }

    /* renamed from: com.unity3d.player.GoogleARProxy$4 */
    class C02024 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ GoogleARProxy f9a;

        C02024(GoogleARProxy googleARProxy) {
            this.f9a = googleARProxy;
        }

        public final void run() {
            try {
                if (this.f9a.a != null) {
                    this.f9a.a.m177a("resume", new Object[0]);
                }
            } catch (Exception e) {
                this.f9a.reportError("Exception resuming " + this.f9a.e + " VR on UI Thread. " + e.getLocalizedMessage());
            }
        }
    }

    GoogleARProxy(C0230f c0230f) {
        super("Google AR", c0230f);
    }

    /* renamed from: a */
    public static boolean m23a() {
        try {
            Class loadClass = UnityPlayer.class.getClassLoader().loadClass("com.unity3d.unitygar.GoogleAR");
            C0257o c0257o = new C0257o(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            c0257o.m178a("getClassVersion", new Class[0]);
            if (((Number) c0257o.m177a("getClassVersion", new Object[0])).intValue() > 0) {
                Log.d("Unity", "Loading ARCore V1+ path.");
                return false;
            }
            Log.d("Unity", "Loading ARCore Preview path (Version <= 1).");
            return true;
        } catch (Exception e) {
            Log.d("Unity", "Loading ARCore Preview path.");
            return true;
        }
    }

    /* renamed from: a */
    private boolean m24a(ClassLoader classLoader) {
        if (this.f15f) {
            return true;
        }
        try {
            Class loadClass = classLoader.loadClass("com.unity3d.unitygar.GoogleAR");
            C0257o c0257o = new C0257o(loadClass, loadClass.getConstructor(new Class[0]).newInstance(new Object[0]));
            c0257o.m178a("initialize", new Class[]{Activity.class});
            c0257o.m178a("create", new Class[0]);
            c0257o.m178a("pause", new Class[0]);
            c0257o.m178a("resume", new Class[0]);
            this.a = c0257o;
            this.f15f = true;
            return true;
        } catch (Exception e) {
            this.b.reportError("Google AR Error", e.toString() + e.getLocalizedMessage());
            return false;
        }
    }

    private final native void tangoOnCreate(Activity activity);

    private final native void tangoOnServiceConnected(IBinder iBinder);

    private final native void tangoOnStop();

    /* renamed from: a */
    final void m25a(final Activity activity, Context context) {
        if (m24a(UnityPlayer.class.getClassLoader())) {
            this.c = context;
            runOnUiThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ GoogleARProxy f6b;

                public final void run() {
                    try {
                        if (this.f6b.a != null) {
                            this.f6b.a.m177a("initialize", activity);
                        }
                    } catch (Exception e) {
                        this.f6b.reportError("Exception creating " + this.f6b.e + " VR on UI Thread. " + e.getLocalizedMessage());
                    }
                }
            });
        }
    }

    /* renamed from: b */
    final void m26b() {
        runOnUiThread(new C02002(this));
    }

    /* renamed from: c */
    final void m27c() {
        runOnUiThread(new C02013(this));
    }

    /* renamed from: d */
    final void m28d() {
        runOnUiThread(new C02024(this));
    }

    /* renamed from: e */
    public final boolean m29e() {
        return this.f15f;
    }
}
