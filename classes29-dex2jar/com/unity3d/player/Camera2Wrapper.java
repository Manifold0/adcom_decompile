// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.graphics.Rect;
import android.content.Context;

public class Camera2Wrapper implements d
{
    private Context a;
    private a b;
    
    public Camera2Wrapper(final Context a) {
        this.b = null;
        this.a = a;
        this.initCamera2Jni();
    }
    
    private final native void initCamera2Jni();
    
    private final native void nativeFrameReady(final Object p0, final Object p1, final Object p2, final int p3, final int p4, final int p5);
    
    public final void a() {
        this.closeCamera2();
    }
    
    @Override
    public final void a(final Object o, final Object o2, final Object o3, final int n, final int n2, final int n3) {
        this.nativeFrameReady(o, o2, o3, n, n2, n3);
    }
    
    protected void closeCamera2() {
        if (this.b != null) {
            this.b.b();
        }
        this.b = null;
    }
    
    protected int getCamera2Count() {
        if (j.b) {
            return com.unity3d.player.a.a(this.a);
        }
        return 0;
    }
    
    protected int getCamera2SensorOrientation(final int n) {
        if (j.b) {
            return com.unity3d.player.a.a(this.a, n);
        }
        return 0;
    }
    
    protected Rect getFrameSizeCamera2() {
        if (this.b != null) {
            return this.b.a();
        }
        return new Rect();
    }
    
    protected boolean initializeCamera2(final int n, final int n2, final int n3, final int n4) {
        if (j.b && this.b == null && UnityPlayer.currentActivity != null) {
            this.b = new a(this);
            return this.b.a(this.a, n, n2, n3, n4);
        }
        return false;
    }
    
    protected boolean isCamera2FrontFacing(final int n) {
        return j.b && com.unity3d.player.a.b(this.a, n);
    }
    
    protected void startCamera2() {
        if (this.b != null) {
            this.b.c();
        }
    }
    
    protected void stopCamera2() {
        if (this.b != null) {
            this.b.d();
        }
    }
}
