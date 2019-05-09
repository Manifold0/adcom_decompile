package com.unity3d.player;

import android.os.Build.VERSION;

/* renamed from: com.unity3d.player.j */
public final class C0246j {
    /* renamed from: a */
    static final boolean f188a = (VERSION.SDK_INT >= 19);
    /* renamed from: b */
    static final boolean f189b = (VERSION.SDK_INT >= 21);
    /* renamed from: c */
    static final boolean f190c;
    /* renamed from: d */
    static final C0242e f191d;

    static {
        boolean z = true;
        if (VERSION.SDK_INT < 23) {
            z = false;
        }
        f190c = z;
        f191d = z ? new C0244h() : null;
    }
}
