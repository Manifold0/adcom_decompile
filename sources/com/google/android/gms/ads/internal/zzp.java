package com.google.android.gms.ads.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzp {
    private static String zza(@Nullable Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Iterator it = new TreeSet(bundle.keySet()).iterator();
        while (it.hasNext()) {
            Object obj = bundle.get((String) it.next());
            String zza = obj == null ? "null" : obj instanceof Bundle ? zza((Bundle) obj) : obj.toString();
            stringBuilder.append(zza);
        }
        return stringBuilder.toString();
    }

    public static Object[] zza(String str, zzjj zzjj, String str2, int i, @Nullable zzjn zzjn) {
        Set hashSet = new HashSet(Arrays.asList(str.split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        arrayList.add(str2);
        if (hashSet.contains("formatString")) {
            arrayList.add(null);
        }
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(zzjj.zzapw));
        }
        if (hashSet.contains("extras")) {
            arrayList.add(zza(zzjj.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(zzjj.zzapx));
        }
        if (hashSet.contains("keywords")) {
            if (zzjj.zzapy != null) {
                arrayList.add(zzjj.zzapy.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(zzjj.zzapz));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(zzjj.zzaqa));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(zzjj.zzaqb));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(zzjj.zzaqc);
        }
        if (hashSet.contains(PlaceFields.LOCATION)) {
            if (zzjj.zzaqe != null) {
                arrayList.add(zzjj.zzaqe.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(zzjj.zzaqf);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zza(zzjj.zzaqg));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zza(zzjj.zzaqh));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (zzjj.zzaqi != null) {
                arrayList.add(zzjj.zzaqi.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(zzjj.zzaqj);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(zzjj.zzaqk);
        }
        if (hashSet.contains("isDesignedForFamilies")) {
            arrayList.add(Boolean.valueOf(zzjj.zzaql));
        }
        return arrayList.toArray();
    }
}
