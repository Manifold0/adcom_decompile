package com.unity3d.player;

import android.content.Context;
import android.graphics.Rect;

public class Camera2Wrapper implements C0198d {
    /* renamed from: a */
    private Context f3a;
    /* renamed from: b */
    private C0238a f4b = null;

    public Camera2Wrapper(Context context) {
        this.f3a = context;
        initCamera2Jni();
    }

    private final native void initCamera2Jni();

    private final native void nativeFrameReady(Object obj, Object obj2, Object obj3, int i, int i2, int i3);

    /* renamed from: a */
    public final void m21a() {
        closeCamera2();
    }

    /* renamed from: a */
    public final void mo683a(Object obj, Object obj2, Object obj3, int i, int i2, int i3) {
        nativeFrameReady(obj, obj2, obj3, i, i2, i3);
    }

    protected void closeCamera2() {
        if (this.f4b != null) {
            this.f4b.m144b();
        }
        this.f4b = null;
    }

    protected int getCamera2Count() {
        return C0246j.f189b ? C0238a.m120a(this.f3a) : 0;
    }

    protected int getCamera2SensorOrientation(int i) {
        return C0246j.f189b ? C0238a.m121a(this.f3a, i) : 0;
    }

    protected Rect getFrameSizeCamera2() {
        return this.f4b != null ? this.f4b.m142a() : new Rect();
    }

    protected boolean initializeCamera2(int i, int i2, int i3, int i4) {
        if (!C0246j.f189b || this.f4b != null || UnityPlayer.currentActivity == null) {
            return false;
        }
        this.f4b = new C0238a(this);
        return this.f4b.m143a(this.f3a, i, i2, i3, i4);
    }

    protected boolean isCamera2FrontFacing(int i) {
        return C0246j.f189b ? C0238a.m130b(this.f3a, i) : false;
    }

    protected void startCamera2() {
        if (this.f4b != null) {
            this.f4b.m145c();
        }
    }

    protected void stopCamera2() {
        if (this.f4b != null) {
            this.f4b.m146d();
        }
    }
}
