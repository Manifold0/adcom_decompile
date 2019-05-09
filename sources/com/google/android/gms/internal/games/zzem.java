package com.google.android.gms.internal.games;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.HashMap;
import java.util.Set;

public final class zzem {
    private static final String[] zzmt = new String[]{"requestId", "outcome"};
    private final int statusCode;
    private final HashMap<String, Integer> zzmu;

    private zzem(int i, HashMap<String, Integer> hashMap) {
        this.statusCode = i;
        this.zzmu = hashMap;
    }

    @VisibleForTesting
    public static zzem zzbd(DataHolder dataHolder) {
        zzeo zzeo = new zzeo();
        zzeo.zzo(dataHolder.getStatusCode());
        int count = dataHolder.getCount();
        for (int i = 0; i < count; i++) {
            int windowIndex = dataHolder.getWindowIndex(i);
            zzeo.zzh(dataHolder.getString("requestId", i, windowIndex), dataHolder.getInteger("outcome", i, windowIndex));
        }
        return zzeo.zzca();
    }

    @VisibleForTesting
    public final Set<String> getRequestIds() {
        return this.zzmu.keySet();
    }

    @VisibleForTesting
    public final int getRequestOutcome(String str) {
        Preconditions.checkArgument(this.zzmu.containsKey(str), new StringBuilder(String.valueOf(str).length() + 46).append("Request ").append(str).append(" was not part of the update operation!").toString());
        return ((Integer) this.zzmu.get(str)).intValue();
    }
}
