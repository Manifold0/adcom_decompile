// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Map;
import java.util.HashMap;
import android.util.Log;

public final class ac
{
    public static void a(final int n, final String s, final String s2, final Throwable t) {
        if (s2 != null) {
            Log.println(n, s, s2);
        }
        if (t != null) {
            Log.println(n, s, Log.getStackTraceString(t));
        }
    }
    
    public static void a(final int n, final String s, final String s2, final Object... array) {
        Throwable t;
        if (array == null || array.length == 0) {
            t = null;
        }
        else {
            final Object o = array[array.length - 1];
            if (o instanceof Throwable) {
                t = (Throwable)o;
            }
            else {
                t = null;
            }
        }
        ij ij = null;
        Label_0028: {
            if (s2 == null) {
                ij = new ij(null, array, t);
            }
            else if (array == null) {
                ij = new ij(s2);
            }
            else {
                final StringBuffer sb = new StringBuffer(s2.length() + 50);
                int i = 0;
                int n2 = 0;
                while (i < array.length) {
                    final int index = s2.indexOf("{}", n2);
                    if (index == -1) {
                        if (n2 == 0) {
                            ij = new ij(s2, array, t);
                            break Label_0028;
                        }
                        sb.append(s2.substring(n2, s2.length()));
                        ij = new ij(sb.toString(), array, t);
                        break Label_0028;
                    }
                    else {
                        int n3;
                        if (index != 0 && s2.charAt(index - 1) == '\\') {
                            n3 = 1;
                        }
                        else {
                            n3 = 0;
                        }
                        if (n3 != 0) {
                            int n4;
                            if (index >= 2 && s2.charAt(index - 2) == '\\') {
                                n4 = 1;
                            }
                            else {
                                n4 = 0;
                            }
                            if (n4 == 0) {
                                --i;
                                sb.append(s2.substring(n2, index - 1));
                                sb.append('{');
                                n2 = index + 1;
                            }
                            else {
                                sb.append(s2.substring(n2, index - 1));
                                ik.a(sb, array[i], new HashMap());
                                n2 = index + 2;
                            }
                        }
                        else {
                            sb.append(s2.substring(n2, index));
                            ik.a(sb, array[i], new HashMap());
                            n2 = index + 2;
                        }
                        ++i;
                    }
                }
                sb.append(s2.substring(n2, s2.length()));
                if (i < array.length - 1) {
                    ij = new ij(sb.toString(), array, t);
                }
                else {
                    ij = new ij(sb.toString(), array, null);
                }
            }
        }
        a(n, s, ij.b, ij.c);
    }
    
    public static void a(final String s, final String s2, final Object... array) {
        a(6, s, s2, array);
    }
}
