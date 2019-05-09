package com.facebook.ads.internal.p046v.p053b.p054a;

import android.text.TextUtils;
import com.facebook.ads.internal.p046v.p053b.C2185m;

/* renamed from: com.facebook.ads.internal.v.b.a.f */
public class C2162f implements C2156c {
    /* renamed from: a */
    public String mo5527a(String str) {
        int lastIndexOf = str.lastIndexOf(46);
        Object substring = (lastIndexOf == -1 || lastIndexOf <= str.lastIndexOf(47) || (lastIndexOf + 2) + 4 <= str.length()) ? "" : str.substring(lastIndexOf + 1, str.length());
        String c = C2185m.m5596c(str);
        return TextUtils.isEmpty(substring) ? c : c + "." + substring;
    }
}
