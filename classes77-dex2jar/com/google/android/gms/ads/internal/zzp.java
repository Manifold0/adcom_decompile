// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Arrays;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzjj;
import java.util.Iterator;
import java.util.Collection;
import java.util.TreeSet;
import android.support.annotation.Nullable;
import android.os.Bundle;
import javax.annotation.ParametersAreNonnullByDefault;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
@ParametersAreNonnullByDefault
public final class zzp
{
    private static String zza(@Nullable final Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<String> iterator = new TreeSet<String>(bundle.keySet()).iterator();
        while (iterator.hasNext()) {
            final Object value = bundle.get((String)iterator.next());
            String s;
            if (value == null) {
                s = "null";
            }
            else if (value instanceof Bundle) {
                s = zza((Bundle)value);
            }
            else {
                s = value.toString();
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static Object[] zza(final String s, final zzjj zzjj, final String s2, final int n, @Nullable final zzjn zzjn) {
        final HashSet set = new HashSet((Collection<? extends E>)Arrays.asList(s.split(",")));
        final ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        list.add(s2);
        if (set.contains("formatString")) {
            list.add(null);
        }
        if (set.contains("networkType")) {
            list.add((String)n);
        }
        if (set.contains("birthday")) {
            list.add((String)zzjj.zzapw);
        }
        if (set.contains("extras")) {
            list.add(zza(zzjj.extras));
        }
        if (set.contains("gender")) {
            list.add((String)zzjj.zzapx);
        }
        if (set.contains("keywords")) {
            if (zzjj.zzapy != null) {
                list.add(zzjj.zzapy.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("isTestDevice")) {
            list.add((String)zzjj.zzapz);
        }
        if (set.contains("tagForChildDirectedTreatment")) {
            list.add((String)zzjj.zzaqa);
        }
        if (set.contains("manualImpressionsEnabled")) {
            list.add((String)zzjj.zzaqb);
        }
        if (set.contains("publisherProvidedId")) {
            list.add(zzjj.zzaqc);
        }
        if (set.contains("location")) {
            if (zzjj.zzaqe != null) {
                list.add(zzjj.zzaqe.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("contentUrl")) {
            list.add(zzjj.zzaqf);
        }
        if (set.contains("networkExtras")) {
            list.add(zza(zzjj.zzaqg));
        }
        if (set.contains("customTargeting")) {
            list.add(zza(zzjj.zzaqh));
        }
        if (set.contains("categoryExclusions")) {
            if (zzjj.zzaqi != null) {
                list.add(zzjj.zzaqi.toString());
            }
            else {
                list.add(null);
            }
        }
        if (set.contains("requestAgent")) {
            list.add(zzjj.zzaqj);
        }
        if (set.contains("requestPackage")) {
            list.add(zzjj.zzaqk);
        }
        if (set.contains("isDesignedForFamilies")) {
            list.add((String)zzjj.zzaql);
        }
        return list.toArray();
    }
}
