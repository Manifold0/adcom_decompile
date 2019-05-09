package com.tapjoy.internal;

import android.util.Log;
import java.util.HashMap;

public final class ac {
    /* renamed from: a */
    public static void m7145a(String str, String str2, Object... objArr) {
        m7144a(6, str, str2, objArr);
    }

    /* renamed from: a */
    public static void m7144a(int i, String str, String str2, Object... objArr) {
        Throwable th;
        ij ijVar;
        if (objArr == null || objArr.length == 0) {
            th = null;
        } else {
            Object obj = objArr[objArr.length - 1];
            th = obj instanceof Throwable ? (Throwable) obj : null;
        }
        if (str2 == null) {
            ijVar = new ij(null, objArr, th);
        } else if (objArr == null) {
            ijVar = new ij(str2);
        } else {
            StringBuffer stringBuffer = new StringBuffer(str2.length() + 50);
            int i2 = 0;
            int i3 = 0;
            while (i2 < objArr.length) {
                int indexOf = str2.indexOf("{}", i3);
                if (indexOf != -1) {
                    Object obj2;
                    if (indexOf == 0 || str2.charAt(indexOf - 1) != '\\') {
                        obj2 = null;
                    } else {
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        if (indexOf < 2 || str2.charAt(indexOf - 2) != '\\') {
                            obj2 = null;
                        } else {
                            obj2 = 1;
                        }
                        if (obj2 == null) {
                            i2--;
                            stringBuffer.append(str2.substring(i3, indexOf - 1));
                            stringBuffer.append('{');
                            i3 = indexOf + 1;
                        } else {
                            stringBuffer.append(str2.substring(i3, indexOf - 1));
                            ik.m8190a(stringBuffer, objArr[i2], new HashMap());
                            i3 = indexOf + 2;
                        }
                    } else {
                        stringBuffer.append(str2.substring(i3, indexOf));
                        ik.m8190a(stringBuffer, objArr[i2], new HashMap());
                        i3 = indexOf + 2;
                    }
                    i2++;
                } else if (i3 == 0) {
                    ijVar = new ij(str2, objArr, th);
                } else {
                    stringBuffer.append(str2.substring(i3, str2.length()));
                    ijVar = new ij(stringBuffer.toString(), objArr, th);
                }
            }
            stringBuffer.append(str2.substring(i3, str2.length()));
            if (i2 < objArr.length - 1) {
                ijVar = new ij(stringBuffer.toString(), objArr, th);
            } else {
                ijVar = new ij(stringBuffer.toString(), objArr, null);
            }
        }
        m7143a(i, str, ijVar.f8222b, ijVar.f8223c);
    }

    /* renamed from: a */
    public static void m7143a(int i, String str, String str2, Throwable th) {
        if (str2 != null) {
            Log.println(i, str, str2);
        }
        if (th != null) {
            Log.println(i, str, Log.getStackTraceString(th));
        }
    }
}
