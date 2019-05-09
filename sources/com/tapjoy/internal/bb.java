package com.tapjoy.internal;

import java.util.List;

public final class bb {
    /* renamed from: a */
    public static void m7192a(List list, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException();
        } else if (i > 0) {
            int size = list.size();
            if (i > size) {
                throw new IndexOutOfBoundsException();
            } else if (i == size) {
                list.clear();
            } else {
                while (true) {
                    size = i - 1;
                    if (i > 0) {
                        list.remove(0);
                        i = size;
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
