package com.google.android.gms.internal.games;

import java.util.HashMap;

public final class zzeo {
    private int statusCode = 0;
    private HashMap<String, Integer> zzmu = new HashMap();

    public final zzem zzca() {
        return new zzem(this.statusCode, this.zzmu);
    }

    public final zzeo zzh(String str, int i) {
        Object obj;
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
                obj = 1;
                break;
            default:
                obj = null;
                break;
        }
        if (obj != null) {
            this.zzmu.put(str, Integer.valueOf(i));
        }
        return this;
    }

    public final zzeo zzo(int i) {
        this.statusCode = i;
        return this;
    }
}
