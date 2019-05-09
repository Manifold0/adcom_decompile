// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.List;

public final class bb
{
    public static void a(final List list, final int n) {
        if (n < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (n > 0) {
            final int size = list.size();
            if (n > size) {
                throw new IndexOutOfBoundsException();
            }
            int i;
            if ((i = n) == size) {
                list.clear();
            }
            else {
                while (i > 0) {
                    list.remove(0);
                    --i;
                }
            }
        }
    }
}
