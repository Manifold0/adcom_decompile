package com.tapjoy.internal;

import java.util.Arrays;

abstract class gr implements fo {
    /* renamed from: a */
    private static final String[] f7978a;

    gr() {
    }

    static {
        String[] strArr = new String[]{"reward", "purchase", "custom_action"};
        f7978a = strArr;
        Arrays.sort(strArr);
    }

    /* renamed from: a */
    public final void mo6323a(fp fpVar) {
        if (this instanceof fs) {
            fs fsVar = (fs) this;
            fpVar.mo6134a(fsVar.mo6324a(), fsVar.mo6325b());
        } else if (this instanceof ft) {
            ft ftVar = (ft) this;
            fpVar.mo6135a(ftVar.mo6326a(), ftVar.mo6327b(), ftVar.mo6328c(), ftVar.mo6329d());
        }
    }

    /* renamed from: a */
    public static boolean m7988a(String str) {
        return Arrays.binarySearch(f7978a, str) >= 0;
    }

    /* renamed from: a */
    public static gr m7987a(String str, bs bsVar) {
        if ("reward".equals(str)) {
            return (gr) bsVar.m7248a(hb.f8038a);
        }
        if ("purchase".equals(str)) {
            return (gr) bsVar.m7248a(gz.f8022a);
        }
        return null;
    }
}
