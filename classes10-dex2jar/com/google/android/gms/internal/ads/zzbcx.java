// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.lang.reflect.Modifier;
import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.List;
import java.util.TreeSet;
import java.lang.reflect.Method;
import java.util.HashMap;

final class zzbcx
{
    static String zza(final zzbcu zzbcu, final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("# ").append(s);
        zza(zzbcu, sb, 0);
        return sb.toString();
    }
    
    private static void zza(final zzbcu zzbcu, final StringBuilder sb, final int n) {
        final HashMap<Object, Method> hashMap = new HashMap<Object, Method>();
        final HashMap<String, Method> hashMap2 = new HashMap<String, Method>();
        final TreeSet<String> set = new TreeSet<String>();
        final Method[] declaredMethods = zzbcu.getClass().getDeclaredMethods();
        for (int length = declaredMethods.length, i = 0; i < length; ++i) {
            final Method method = declaredMethods[i];
            hashMap2.put(method.getName(), method);
            if (method.getParameterTypes().length == 0) {
                hashMap.put(method.getName(), method);
                if (method.getName().startsWith("get")) {
                    set.add(method.getName());
                }
            }
        }
        for (final String s : set) {
            final String replaceFirst = s.replaceFirst("get", "");
            if (replaceFirst.endsWith("List") && !replaceFirst.endsWith("OrBuilderList") && !replaceFirst.equals("List")) {
                final String value = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                final String value2 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 4));
                String concat;
                if (value2.length() != 0) {
                    concat = value.concat(value2);
                }
                else {
                    concat = new String(value);
                }
                final Method method2 = hashMap.get(s);
                if (method2 != null && method2.getReturnType().equals(List.class)) {
                    zza(sb, n, zzep(concat), zzbbo.zza(method2, zzbcu, new Object[0]));
                    continue;
                }
            }
            if (replaceFirst.endsWith("Map") && !replaceFirst.equals("Map")) {
                final String value3 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                final String value4 = String.valueOf(replaceFirst.substring(1, replaceFirst.length() - 3));
                String concat2;
                if (value4.length() != 0) {
                    concat2 = value3.concat(value4);
                }
                else {
                    concat2 = new String(value3);
                }
                final Method method3 = hashMap.get(s);
                if (method3 != null && method3.getReturnType().equals(Map.class) && !method3.isAnnotationPresent(Deprecated.class) && Modifier.isPublic(method3.getModifiers())) {
                    zza(sb, n, zzep(concat2), zzbbo.zza(method3, zzbcu, new Object[0]));
                    continue;
                }
            }
            final String value5 = String.valueOf(replaceFirst);
            String concat3;
            if (value5.length() != 0) {
                concat3 = "set".concat(value5);
            }
            else {
                concat3 = new String("set");
            }
            if (hashMap2.get(concat3) != null) {
                if (replaceFirst.endsWith("Bytes")) {
                    final String value6 = String.valueOf(replaceFirst.substring(0, replaceFirst.length() - 5));
                    String concat4;
                    if (value6.length() != 0) {
                        concat4 = "get".concat(value6);
                    }
                    else {
                        concat4 = new String("get");
                    }
                    if (hashMap.containsKey(concat4)) {
                        continue;
                    }
                }
                final String value7 = String.valueOf(replaceFirst.substring(0, 1).toLowerCase());
                final String value8 = String.valueOf(replaceFirst.substring(1));
                String concat5;
                if (value8.length() != 0) {
                    concat5 = value7.concat(value8);
                }
                else {
                    concat5 = new String(value7);
                }
                final String value9 = String.valueOf(replaceFirst);
                String concat6;
                if (value9.length() != 0) {
                    concat6 = "get".concat(value9);
                }
                else {
                    concat6 = new String("get");
                }
                final Method method4 = hashMap.get(concat6);
                final String value10 = String.valueOf(replaceFirst);
                String concat7;
                if (value10.length() != 0) {
                    concat7 = "has".concat(value10);
                }
                else {
                    concat7 = new String("has");
                }
                final Method method5 = hashMap.get(concat7);
                if (method4 == null) {
                    continue;
                }
                final Object zza = zzbbo.zza(method4, zzbcu, new Object[0]);
                int booleanValue;
                if (method5 == null) {
                    int n2;
                    if (zza instanceof Boolean) {
                        if (!(boolean)zza) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (zza instanceof Integer) {
                        if ((int)zza == 0) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (zza instanceof Float) {
                        if ((float)zza == 0.0f) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (zza instanceof Double) {
                        if ((double)zza == 0.0) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (zza instanceof String) {
                        n2 = (zza.equals("") ? 1 : 0);
                    }
                    else if (zza instanceof zzbah) {
                        n2 = (zza.equals(zzbah.zzdpq) ? 1 : 0);
                    }
                    else if (zza instanceof zzbcu) {
                        if (zza == ((zzbcu)zza).zzadg()) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else if (zza instanceof Enum) {
                        if (((Enum)zza).ordinal() == 0) {
                            n2 = 1;
                        }
                        else {
                            n2 = 0;
                        }
                    }
                    else {
                        n2 = 0;
                    }
                    if (n2 == 0) {
                        booleanValue = 1;
                    }
                    else {
                        booleanValue = 0;
                    }
                }
                else {
                    booleanValue = (((boolean)zzbbo.zza(method5, zzbcu, new Object[0])) ? 1 : 0);
                }
                if (booleanValue == 0) {
                    continue;
                }
                zza(sb, n, zzep(concat5), zza);
            }
        }
        if (zzbcu instanceof zzbbo.zzc) {
            final Iterator<Map.Entry<Object, Object>> iterator2 = ((zzbbo.zzc)zzbcu).zzdtz.iterator();
            if (iterator2.hasNext()) {
                ((Map.Entry)iterator2.next()).getKey();
                throw new NoSuchMethodError();
            }
        }
        if (((zzbbo)zzbcu).zzdtt != null) {
            ((zzbbo)zzbcu).zzdtt.zza(sb, n);
        }
    }
    
    static final void zza(final StringBuilder sb, final int n, final String s, final Object o) {
        final int n2 = 0;
        final int n3 = 0;
        if (o instanceof List) {
            final Iterator<Object> iterator = (Iterator<Object>)((List)o).iterator();
            while (iterator.hasNext()) {
                zza(sb, n, s, iterator.next());
            }
        }
        else if (o instanceof Map) {
            final Iterator<Map.Entry<?, ?>> iterator2 = ((Map)o).entrySet().iterator();
            while (iterator2.hasNext()) {
                zza(sb, n, s, iterator2.next());
            }
        }
        else {
            sb.append('\n');
            for (int i = 0; i < n; ++i) {
                sb.append(' ');
            }
            sb.append(s);
            if (o instanceof String) {
                sb.append(": \"").append(zzbea.zzaq(zzbah.zzem((String)o))).append('\"');
            }
            else {
                if (o instanceof zzbah) {
                    sb.append(": \"").append(zzbea.zzaq((zzbah)o)).append('\"');
                    return;
                }
                if (o instanceof zzbbo) {
                    sb.append(" {");
                    zza((zzbcu)o, sb, n + 2);
                    sb.append("\n");
                    for (int j = n3; j < n; ++j) {
                        sb.append(' ');
                    }
                    sb.append("}");
                    return;
                }
                if (o instanceof Map.Entry) {
                    sb.append(" {");
                    final Map.Entry entry = (Map.Entry)o;
                    zza(sb, n + 2, "key", entry.getKey());
                    zza(sb, n + 2, "value", entry.getValue());
                    sb.append("\n");
                    for (int k = n2; k < n; ++k) {
                        sb.append(' ');
                    }
                    sb.append("}");
                    return;
                }
                sb.append(": ").append(o.toString());
            }
        }
    }
    
    private static final String zzep(final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (Character.isUpperCase(char1)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(char1));
        }
        return sb.toString();
    }
}
