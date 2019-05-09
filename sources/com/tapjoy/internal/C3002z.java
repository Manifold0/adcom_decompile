package com.tapjoy.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.tapjoy.internal.z */
public final class C3002z {
    /* renamed from: a */
    private static final ThreadLocal f8249a = new C30001();
    /* renamed from: b */
    private static final ThreadLocal f8250b = new C30012();

    /* renamed from: com.tapjoy.internal.z$1 */
    static class C30001 extends ThreadLocal {
        C30001() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        }
    }

    /* renamed from: com.tapjoy.internal.z$2 */
    static class C30012 extends ThreadLocal {
        C30012() {
        }

        protected final /* synthetic */ Object initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH.mm.ss");
        }
    }

    /* renamed from: a */
    public static String m8235a(Date date) {
        return ((DateFormat) f8249a.get()).format(date);
    }
}
