// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.lang.reflect.Array;
import android.view.View;
import java.util.concurrent.atomic.AtomicLong;
import android.content.Intent;
import android.content.Context;
import android.app.Activity;
import android.os.Build$VERSION;
import java.util.Iterator;
import android.view.Surface;
import android.os.Message;
import android.os.Looper;
import android.os.Handler;
import android.view.SurfaceView;
import java.util.Vector;

class GoogleVrProxy extends c implements GoogleVrVideo
{
    private boolean f;
    private boolean g;
    private Runnable h;
    private Vector i;
    private SurfaceView j;
    private a k;
    private Thread l;
    private Handler m;
    
    public GoogleVrProxy(final f f) {
        super("Google VR", f);
        this.f = false;
        this.g = false;
        this.h = null;
        this.i = new Vector();
        this.j = null;
        this.k = new a();
        this.l = null;
        this.m = new Handler(Looper.getMainLooper()) {
            public final void handleMessage(final Message message) {
                Label_0029: {
                    switch (message.what) {
                        default: {
                            super.handleMessage(message);
                            break;
                        }
                        case 135711: {
                            switch (message.arg1) {
                                default: {
                                    super.handleMessage(message);
                                    return;
                                }
                                case 2147483646: {
                                    final Surface surface = (Surface)message.obj;
                                    final Iterator<GoogleVrVideoCallbacks> iterator = (Iterator<GoogleVrVideoCallbacks>)GoogleVrProxy.this.i.iterator();
                                    while (iterator.hasNext()) {
                                        iterator.next().onSurfaceAvailable(surface);
                                    }
                                    break Label_0029;
                                }
                                case 2147483645: {
                                    final Iterator<GoogleVrVideoCallbacks> iterator2 = (Iterator<GoogleVrVideoCallbacks>)GoogleVrProxy.this.i.iterator();
                                    while (iterator2.hasNext()) {
                                        iterator2.next().onFrameAvailable();
                                    }
                                    break Label_0029;
                                }
                            }
                            break;
                        }
                    }
                }
            }
        };
        this.initVrJni();
    }
    
    private void a(final boolean d) {
        this.k.d = d;
    }
    
    private static boolean a(final int n) {
        return Build$VERSION.SDK_INT >= n;
    }
    
    private boolean a(final ClassLoader classLoader) {
        try {
            final Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygvr.GoogleVR");
            final o a = new o(loadClass, loadClass.getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]));
            a.a("initialize", new Class[] { Activity.class, Context.class, SurfaceView.class, Boolean.TYPE, Handler.class });
            a.a("deinitialize", new Class[0]);
            a.a("load", new Class[] { Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Boolean.TYPE, Runnable.class });
            a.a("enable", new Class[] { Boolean.TYPE });
            a.a("unload", new Class[0]);
            a.a("pause", new Class[0]);
            a.a("resume", new Class[0]);
            a.a("getGvrLayout", new Class[0]);
            a.a("getVideoSurfaceId", new Class[0]);
            a.a("getVideoSurface", new Class[0]);
            this.a = a;
            return true;
        }
        catch (Exception ex) {
            this.reportError("Exception initializing GoogleVR from Unity library. " + ex.getLocalizedMessage());
            return false;
        }
    }
    
    private boolean d() {
        return this.k.d;
    }
    
    private void e() {
        final Activity activity = (Activity)this.c;
        if (this.g && !this.k.f && activity != null) {
            this.k.f = true;
            final Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.HOME");
            intent.setFlags(268435456);
            activity.startActivity(intent);
        }
    }
    
    private final native void initVrJni();
    
    private final native boolean isQuiting();
    
    private final native void setVrVideoTransform(final float[][] p0);
    
    public final void a(final Intent intent) {
        if (intent != null && intent.getBooleanExtra("android.intent.extra.VR_LAUNCH", false)) {
            this.g = true;
        }
    }
    
    public final boolean a() {
        return this.k.a;
    }
    
    public final boolean a(final Activity activity, final Context c, final SurfaceView j, final Runnable h) {
        if (activity == null || c == null || j == null || h == null) {
            this.reportError("Invalid parameters passed to Google VR initiialization.");
            return false;
        }
        this.k.b();
        this.c = c;
        this.h = h;
        if (!a(19)) {
            this.reportError("Google VR requires a device that supports an api version of 19 (KitKat) or better.");
            return false;
        }
        if (this.g && !a(24)) {
            this.reportError("Daydream requires a device that supports an api version of 24 (Nougat) or better.");
            return false;
        }
        if (!this.a(UnityPlayer.class.getClassLoader())) {
            return false;
        }
        while (true) {
            try {
                final int booleanValue = ((boolean)this.a.a("initialize", activity, c, j, this.g, this.m)) ? 1 : 0;
                if (booleanValue == 0) {
                    this.reportError("Unable to initialize GoogleVR library.");
                    return false;
                }
            }
            catch (Exception ex) {
                this.reportError("Exception while trying to intialize Unity Google VR Library. " + ex.getLocalizedMessage());
                final int booleanValue = 0;
                continue;
            }
            break;
        }
        this.j = j;
        this.k.a = true;
        this.d = "";
        return true;
    }
    
    public final void b() {
        this.resumeGvrLayout();
    }
    
    public final void c() {
        if (this.j != null) {
            this.j.getHolder().setSizeFromLayout();
        }
    }
    
    @Override
    public void deregisterGoogleVrVideoListener(final GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (this.i.contains(googleVrVideoCallbacks)) {
            googleVrVideoCallbacks.onSurfaceUnavailable();
            this.i.remove(googleVrVideoCallbacks);
        }
    }
    
    protected Object getVideoSurface() {
        if (!this.d() || this.k.e) {
            return null;
        }
        try {
            return this.a.a("getVideoSurface", new Object[0]);
        }
        catch (Exception ex) {
            this.reportError("Exception caught while Getting GoogleVR Video Surface. " + ex.getLocalizedMessage());
            return null;
        }
    }
    
    protected int getVideoSurfaceId() {
        if (!this.d() || this.k.e) {
            return -1;
        }
        try {
            return (int)this.a.a("getVideoSurfaceId", new Object[0]);
        }
        catch (Exception ex) {
            this.reportError("Exception caught while getting Video Surface ID from GoogleVR. " + ex.getLocalizedMessage());
            return -1;
        }
    }
    
    protected long loadGoogleVr(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        if (!this.k.a) {
            return 0L;
        }
        final AtomicLong atomicLong = new AtomicLong(0L);
        String d;
        if (b || b2) {
            d = "Daydream";
        }
        else {
            d = "Cardboard";
        }
        this.d = d;
        if (!this.runOnUiThreadWithSync(new Runnable() {
            @Override
            public final void run() {
                try {
                    atomicLong.set((long)GoogleVrProxy.this.a.a("load", b, b2, b3, b4, b5, GoogleVrProxy.this.h));
                    GoogleVrProxy.this.k.b = true;
                }
                catch (Exception ex) {
                    GoogleVrProxy.this.reportError("Exception caught while loading GoogleVR. " + ex.getLocalizedMessage());
                    atomicLong.set(0L);
                }
            }
        }) || atomicLong.longValue() == 0L) {
            this.reportError("Google VR had a fatal issue while loading. VR will not be available.");
        }
        return atomicLong.longValue();
    }
    
    protected void pauseGvrLayout() {
        if (this.k.a() && !this.k.e) {
            if (this.d()) {
                final Iterator<GoogleVrVideoCallbacks> iterator = this.i.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onSurfaceUnavailable();
                }
            }
            if (this.a != null) {
                this.a.a("pause", new Object[0]);
            }
            this.k.e = true;
        }
    }
    
    @Override
    public void registerGoogleVrVideoListener(final GoogleVrVideoCallbacks googleVrVideoCallbacks) {
        if (!this.i.contains(googleVrVideoCallbacks)) {
            this.i.add(googleVrVideoCallbacks);
            final Surface surface = (Surface)this.getVideoSurface();
            if (surface != null) {
                googleVrVideoCallbacks.onSurfaceAvailable(surface);
            }
        }
    }
    
    protected void resumeGvrLayout() {
        if (this.k.a() && this.k.e) {
            if (this.a != null) {
                this.a.a("resume", new Object[0]);
            }
            this.k.e = false;
        }
    }
    
    protected void setGoogleVrModeEnabled(final boolean b) {
        if (this.k.a() && this.b != null && this.c != null) {
            if (!b && this.isQuiting()) {
                this.e();
            }
            this.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    if (b != GoogleVrProxy.this.d()) {
                        Label_0168: {
                            try {
                                if (!b || GoogleVrProxy.this.d()) {
                                    break Label_0168;
                                }
                                if (GoogleVrProxy.this.a != null && GoogleVrProxy.this.b != null && !GoogleVrProxy.this.b.addViewToPlayer((View)GoogleVrProxy.this.a.a("getGvrLayout", new Object[0]), true)) {
                                    GoogleVrProxy.this.reportError("Unable to add Google VR to view hierarchy.");
                                    return;
                                }
                            }
                            catch (Exception ex) {
                                GoogleVrProxy.this.reportError("Exception enabling Google VR on UI Thread. " + ex.getLocalizedMessage());
                                return;
                            }
                            if (GoogleVrProxy.this.a != null) {
                                GoogleVrProxy.this.a.a("enable", true);
                            }
                            GoogleVrProxy.this.a(true);
                            return;
                        }
                        if (!b && GoogleVrProxy.this.d()) {
                            GoogleVrProxy.this.a(false);
                            if (GoogleVrProxy.this.a != null) {
                                GoogleVrProxy.this.a.a("enable", false);
                            }
                            if (GoogleVrProxy.this.a != null && GoogleVrProxy.this.b != null) {
                                GoogleVrProxy.this.b.removeViewFromPlayer((View)GoogleVrProxy.this.a.a("getGvrLayout", new Object[0]));
                            }
                        }
                    }
                }
            });
        }
    }
    
    @Override
    public void setVideoLocationTransform(final float[] array) {
        final float[][] vrVideoTransform = (float[][])Array.newInstance(Float.TYPE, 4, 4);
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                vrVideoTransform[i][j] = array[i * 4 + j];
            }
        }
        this.setVrVideoTransform(vrVideoTransform);
    }
    
    protected void unloadGoogleVr() {
        if (this.k.d) {
            this.setGoogleVrModeEnabled(false);
        }
        if (this.k.c) {
            this.k.c = false;
        }
        this.j = null;
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (GoogleVrProxy.this.a != null) {
                        GoogleVrProxy.this.a.a("unload", new Object[0]);
                        GoogleVrProxy.this.a.a("deinitialize", new Object[0]);
                        GoogleVrProxy.this.a = null;
                    }
                    GoogleVrProxy.this.k.b = false;
                }
                catch (Exception ex) {
                    GoogleVrProxy.this.reportError("Exception unloading Google VR on UI Thread. " + ex.getLocalizedMessage());
                }
            }
        });
    }
    
    final class a
    {
        public boolean a;
        public boolean b;
        public boolean c;
        public boolean d;
        public boolean e;
        public boolean f;
        
        a() {
            this.a = false;
            this.b = false;
            this.c = false;
            this.d = false;
            this.e = true;
            this.f = false;
        }
        
        public final boolean a() {
            return this.a && this.b;
        }
        
        public final void b() {
            this.a = false;
            this.b = false;
            this.d = false;
            this.e = true;
            this.f = false;
        }
    }
}
