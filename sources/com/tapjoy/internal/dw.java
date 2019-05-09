package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJConnectListener;
import java.util.Hashtable;

final class dw extends dv {
    /* renamed from: b */
    private final fe f7400b = new C28761(this);

    /* renamed from: com.tapjoy.internal.dw$1 */
    class C28761 extends fe {
        /* renamed from: a */
        final /* synthetic */ dw f7399a;

        C28761(dw dwVar) {
            this.f7399a = dwVar;
        }

        /* renamed from: a */
        protected final boolean mo6266a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
            return super.mo6244a(context, str, hashtable, tJConnectListener);
        }
    }

    dw() {
    }

    /* renamed from: a */
    public final boolean mo6244a(Context context, String str, Hashtable hashtable, TJConnectListener tJConnectListener) {
        return this.f7400b.m7587b(context, str, hashtable, tJConnectListener);
    }
}
