package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TJPlacementManager;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.internal.fc.C2915a;
import com.tapjoy.internal.gn.C2954a;
import java.util.Observer;

public final class fa extends gn {
    /* renamed from: b */
    private final fc f7715b = new C29141(this);

    /* renamed from: com.tapjoy.internal.fa$1 */
    class C29141 extends fc {
        /* renamed from: a */
        final /* synthetic */ fa f7712a;

        C29141(fa faVar) {
            this.f7712a = faVar;
        }

        /* renamed from: a */
        protected final /* synthetic */ TJPlacement mo6270a(Context context, TJPlacementListener tJPlacementListener, Object obj) {
            C2954a c2954a = (C2954a) obj;
            TJPlacement createPlacement = TJPlacementManager.createPlacement(TapjoyConnectCore.getContext(), c2954a.f7958b, false, tJPlacementListener);
            createPlacement.pushId = c2954a.f7957a;
            return createPlacement;
        }

        /* renamed from: b */
        protected final /* synthetic */ C2915a mo6275b(Object obj) {
            C2954a c2954a = (C2954a) obj;
            return new C2915a(this, c2954a, c2954a.f7960d);
        }

        /* renamed from: a */
        protected final boolean mo6272a() {
            return true;
        }

        /* renamed from: a */
        protected final boolean mo6274a(Observer observer) {
            if (TapjoyConnectCore.isViewOpen()) {
                TJPlacementManager.dismissContentShowing(true);
            }
            return super.mo6274a(observer);
        }
    }

    static {
        gn.m7698a(new fa());
    }

    /* renamed from: a */
    public static void m7703a() {
    }

    private fa() {
    }

    /* renamed from: b */
    protected final boolean mo6277b() {
        return this.f7715b.f7692b != null;
    }

    /* renamed from: a */
    protected final void mo6276a(C2954a c2954a) {
        this.f7715b.m7678c(c2954a);
    }
}
