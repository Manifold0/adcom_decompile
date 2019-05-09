// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Comparator;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.List;

public final class zzp
{
    public final List<zzl> allHeaders;
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzab;
    public final boolean zzac;
    private final long zzad;
    
    private zzp(final int statusCode, final byte[] data, final Map<String, String> zzab, final List<zzl> list, final boolean zzac, final long zzad) {
        this.statusCode = statusCode;
        this.data = data;
        this.zzab = zzab;
        if (list == null) {
            this.allHeaders = null;
        }
        else {
            this.allHeaders = Collections.unmodifiableList((List<? extends zzl>)list);
        }
        this.zzac = zzac;
        this.zzad = zzad;
    }
    
    @Deprecated
    public zzp(final int n, final byte[] array, final Map<String, String> map, final boolean b, final long n2) {
        List<zzl> emptyList;
        if (map == null) {
            emptyList = null;
        }
        else if (map.isEmpty()) {
            emptyList = Collections.emptyList();
        }
        else {
            final ArrayList<zzl> list = new ArrayList<zzl>(map.size());
            final Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
            while (true) {
                emptyList = list;
                if (!iterator.hasNext()) {
                    break;
                }
                final Map.Entry<String, String> entry = iterator.next();
                list.add(new zzl(entry.getKey(), entry.getValue()));
            }
        }
        this(n, array, map, emptyList, b, n2);
    }
    
    public zzp(final int n, final byte[] array, final boolean b, final long n2, final List<zzl> list) {
        Map<String, String> emptyMap;
        if (list == null) {
            emptyMap = null;
        }
        else if (list.isEmpty()) {
            emptyMap = Collections.emptyMap();
        }
        else {
            final TreeMap<String, String> treeMap = new TreeMap<String, String>(String.CASE_INSENSITIVE_ORDER);
            final Iterator<zzl> iterator = list.iterator();
            while (true) {
                emptyMap = treeMap;
                if (!iterator.hasNext()) {
                    break;
                }
                final zzl zzl = iterator.next();
                treeMap.put(zzl.getName(), zzl.getValue());
            }
        }
        this(n, array, emptyMap, list, b, n2);
    }
    
    @Deprecated
    public zzp(final byte[] array, final Map<String, String> map) {
        this(200, array, map, false, 0L);
    }
}
