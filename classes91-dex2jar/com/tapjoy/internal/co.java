// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

public final class co
{
    private final String[] a;
    
    public co() {
        this.a = new String[512];
    }
    
    public final String a(final char[] array, final int n, final int n2) {
        final boolean b = false;
        int i = n;
        int n3 = 0;
        while (i < n + n2) {
            n3 = n3 * 31 + array[i];
            ++i;
        }
        final int n4 = n3 >>> 20 ^ n3 >>> 12 ^ n3;
        final int n5 = (n4 ^ (n4 >>> 7 ^ n4 >>> 4)) & this.a.length - 1;
        final String s = this.a[n5];
        if (s != null) {
            int n6 = 0;
            Label_0104: {
                if (s.length() != n2) {
                    n6 = (b ? 1 : 0);
                }
                else {
                    for (int j = 0; j < n2; ++j) {
                        n6 = (b ? 1 : 0);
                        if (array[n + j] != s.charAt(j)) {
                            break Label_0104;
                        }
                    }
                    n6 = 1;
                }
            }
            if (n6 != 0) {
                return s;
            }
        }
        return this.a[n5] = new String(array, n, n2);
    }
}
