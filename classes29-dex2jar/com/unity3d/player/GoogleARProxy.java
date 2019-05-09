// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.content.Context;
import android.os.IBinder;
import android.app.Activity;
import android.util.Log;

class GoogleARProxy extends c
{
    private boolean f;
    
    GoogleARProxy(final f f) {
        super("Google AR", f);
        this.f = false;
    }
    
    public static boolean a() {
        try {
            final Class<?> loadClass = UnityPlayer.class.getClassLoader().loadClass("com.unity3d.unitygar.GoogleAR");
            final o o = new o(loadClass, loadClass.getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]));
            o.a("getClassVersion", new Class[0]);
            if (((Number)o.a("getClassVersion", new Object[0])).intValue() > 0) {
                Log.d("Unity", "Loading ARCore V1+ path.");
                return false;
            }
            Log.d("Unity", "Loading ARCore Preview path (Version <= 1).");
            return true;
        }
        catch (Exception ex) {
            Log.d("Unity", "Loading ARCore Preview path.");
            return true;
        }
    }
    
    private boolean a(final ClassLoader classLoader) {
        if (this.f) {
            return true;
        }
        try {
            final Class<?> loadClass = classLoader.loadClass("com.unity3d.unitygar.GoogleAR");
            final o a = new o(loadClass, loadClass.getConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]));
            a.a("initialize", new Class[] { Activity.class });
            a.a("create", new Class[0]);
            a.a("pause", new Class[0]);
            a.a("resume", new Class[0]);
            this.a = a;
            return this.f = true;
        }
        catch (Exception ex) {
            this.b.reportError("Google AR Error", ex.toString() + ex.getLocalizedMessage());
            return false;
        }
    }
    
    private final native void tangoOnCreate(final Activity p0);
    
    private final native void tangoOnServiceConnected(final IBinder p0);
    
    private final native void tangoOnStop();
    
    final void a(final Activity activity, final Context c) {
        if (!this.a(UnityPlayer.class.getClassLoader())) {
            return;
        }
        this.c = c;
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("initialize", activity);
                    }
                }
                catch (Exception ex) {
                    GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.e + " VR on UI Thread. " + ex.getLocalizedMessage());
                }
            }
        });
    }
    
    final void b() {
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("create", new Object[0]);
                    }
                }
                catch (Exception ex) {
                    GoogleARProxy.this.reportError("Exception creating " + GoogleARProxy.this.e + " VR on UI Thread. " + ex.getLocalizedMessage());
                }
            }
        });
    }
    
    final void c() {
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("pause", new Object[0]);
                    }
                }
                catch (Exception ex) {
                    GoogleARProxy.this.reportError("Exception pausing " + GoogleARProxy.this.e + " VR on UI Thread. " + ex.getLocalizedMessage());
                }
            }
        });
    }
    
    final void d() {
        this.runOnUiThread(new Runnable() {
            @Override
            public final void run() {
                try {
                    if (GoogleARProxy.this.a != null) {
                        GoogleARProxy.this.a.a("resume", new Object[0]);
                    }
                }
                catch (Exception ex) {
                    GoogleARProxy.this.reportError("Exception resuming " + GoogleARProxy.this.e + " VR on UI Thread. " + ex.getLocalizedMessage());
                }
            }
        });
    }
    
    public final boolean e() {
        return this.f;
    }
}
