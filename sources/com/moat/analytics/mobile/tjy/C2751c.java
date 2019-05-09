package com.moat.analytics.mobile.tjy;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import com.moat.analytics.mobile.tjy.base.asserts.C2746a;
import java.lang.ref.WeakReference;

/* renamed from: com.moat.analytics.mobile.tjy.c */
class C2751c implements C2742a {
    /* renamed from: a */
    private final WeakReference f6707a;
    /* renamed from: b */
    private final WeakReference f6708b;
    /* renamed from: c */
    private boolean f6709c;
    /* renamed from: d */
    private final ap f6710d;
    /* renamed from: e */
    private boolean f6711e;

    C2751c(Activity activity, ap apVar) {
        C2746a.m6881a(activity);
        if (apVar.mo6105b()) {
            Log.d("MoatActivityState", "Listening to Activity: " + (activity != null ? activity.getClass() + "@" + activity.hashCode() : "null"));
        }
        this.f6707a = new WeakReference(activity.getApplication());
        this.f6708b = new WeakReference(activity);
        this.f6710d = apVar;
        this.f6709c = false;
    }

    /* renamed from: a */
    public boolean mo6125a() {
        return this.f6711e;
    }

    /* renamed from: b */
    public void mo6126b() {
        if (!this.f6709c) {
            ((Application) this.f6707a.get()).registerActivityLifecycleCallbacks(new C2753e());
        }
    }

    /* renamed from: c */
    public Activity mo6127c() {
        return (Activity) this.f6708b.get();
    }
}
