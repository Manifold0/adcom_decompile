package com.tapjoy.internal;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public final class aa {
    /* renamed from: a */
    public static String m7141a(File file, File file2) {
        String str = null;
        String b = file.exists() ? ct.m7338b(bl.m7199a(file)) : null;
        if (file2 != null && file2.exists()) {
            str = ct.m7338b(bl.m7199a(file2));
        }
        Object[] objArr = new Object[]{file, file2, b, str};
        if (b == null) {
            if (str == null) {
                str = UUID.randomUUID().toString();
                if (file2 != null) {
                    try {
                        bl.m7201a(file2, str);
                    } catch (IOException e) {
                    }
                }
            }
            try {
                bl.m7201a(file, str);
            } catch (IOException e2) {
            }
            return !str.equals(bl.m7199a(file)) ? str : str;
        } else {
            if (str == null && file2 != null) {
                try {
                    bl.m7201a(file2, b);
                    return b;
                } catch (IOException e3) {
                }
            }
            return b;
        }
    }
}
