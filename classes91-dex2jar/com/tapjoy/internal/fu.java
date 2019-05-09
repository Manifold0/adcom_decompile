// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.opengl.GLSurfaceView;
import android.app.Activity;

public final class fu
{
    public static final bf a;
    private static Activity b;
    private static final cd c;
    private static final cd d;
    
    static {
        c = new cd();
        d = new cd();
        a = new bf() {
            @Override
            public final boolean a(final Runnable runnable) {
                final GLSurfaceView glSurfaceView = (GLSurfaceView)fu.c.a();
                if (glSurfaceView != null) {
                    glSurfaceView.queueEvent(runnable);
                    return true;
                }
                return false;
            }
        };
    }
    
    public static Activity a() {
        Activity activity;
        if ((activity = fu.b) == null) {
            activity = com.tapjoy.internal.d.a();
        }
        return activity;
    }
    
    static void a(final GLSurfaceView glSurfaceView) {
        fu.c.a(glSurfaceView);
        glSurfaceView.queueEvent((Runnable)new Runnable() {
            @Override
            public final void run() {
                fu.d.a(Thread.currentThread());
            }
        });
    }
    
    public static Thread b() {
        return (Thread)fu.d.a();
    }
}
