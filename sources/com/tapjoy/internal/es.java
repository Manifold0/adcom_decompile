package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.internal.ev.C2912a;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

public final class es extends fw implements Observer {
    /* renamed from: b */
    private final Map f7695b = new HashMap();
    /* renamed from: c */
    private final el f7696c = new el();
    /* renamed from: d */
    private boolean f7697d;
    /* renamed from: e */
    private final fc f7698e = new C29101(this);

    /* renamed from: com.tapjoy.internal.es$1 */
    class C29101 extends fc {
        /* renamed from: a */
        final /* synthetic */ es f7693a;

        C29101(es esVar) {
            this.f7693a = esVar;
        }

        /* renamed from: a */
        protected final boolean mo6272a() {
            return super.mo6272a() && !gn.m7700c();
        }

        /* renamed from: a */
        protected final /* bridge */ /* synthetic */ String mo6271a(Object obj) {
            return "AppLaunch";
        }

        /* renamed from: a */
        protected final /* synthetic */ TJPlacement mo6270a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            return TJPlacementManager.createPlacement(context, "AppLaunch", true, tJPlacementListener);
        }
    }

    static {
        fw.f7694a = new es();
    }

    /* renamed from: a */
    public static void m7684a() {
    }

    private es() {
    }

    public final void update(Observable observable, Object data) {
        C2912a c2912a = ev.f7703d;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    protected final void mo6273a(android.app.Activity r7) {
        /*
        r6 = this;
        r2 = 1;
        r1 = 0;
        if (r7 == 0) goto L_0x000e;
    L_0x0004:
        r3 = r7.getTaskId();
        r0 = -1;
        if (r3 != r0) goto L_0x0023;
    L_0x000b:
        r0 = r1;
    L_0x000c:
        if (r0 != 0) goto L_0x001a;
    L_0x000e:
        r0 = r6.f7697d;
        if (r0 != 0) goto L_0x0020;
    L_0x0012:
        r0 = r6.f7696c;
        r0 = r0.m7649a();
        if (r0 == 0) goto L_0x0020;
    L_0x001a:
        r0 = r6.f7698e;
        r1 = 0;
        r0.m7678c(r1);
    L_0x0020:
        r6.f7697d = r2;
        return;
    L_0x0023:
        r4 = r7.getIntent();
        if (r4 == 0) goto L_0x0046;
    L_0x0029:
        r0 = r4.getCategories();
        if (r0 == 0) goto L_0x0048;
    L_0x002f:
        r5 = "android.intent.category.LAUNCHER";
        r0 = r0.contains(r5);
        if (r0 == 0) goto L_0x0048;
    L_0x0037:
        r0 = "android.intent.action.MAIN";
        r5 = r4.getAction();
        r0 = r0.equals(r5);
        if (r0 == 0) goto L_0x0048;
    L_0x0043:
        r0 = r2;
    L_0x0044:
        if (r0 != 0) goto L_0x004a;
    L_0x0046:
        r0 = r1;
        goto L_0x000c;
    L_0x0048:
        r0 = r1;
        goto L_0x0044;
    L_0x004a:
        r0 = r4.getComponent();
        if (r0 != 0) goto L_0x0052;
    L_0x0050:
        r0 = r1;
        goto L_0x000c;
    L_0x0052:
        r0 = r0.getClassName();
        r4 = r6.f7695b;
        r5 = java.lang.Integer.valueOf(r3);
        r0 = r4.put(r0, r5);
        r0 = (java.lang.Integer) r0;
        if (r0 == 0) goto L_0x006c;
    L_0x0064:
        r0 = r0.intValue();
        if (r0 != r3) goto L_0x006c;
    L_0x006a:
        r0 = r1;
        goto L_0x000c;
    L_0x006c:
        r0 = r2;
        goto L_0x000c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tapjoy.internal.es.a(android.app.Activity):void");
    }
}
