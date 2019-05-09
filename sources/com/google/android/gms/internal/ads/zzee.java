package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class zzee extends zzei {
    public zzee(zzcz zzcz, String str, String str2, zzba zzba, int i, int i2) {
        super(zzcz, str, str2, zzba, i, 48);
    }

    protected final void zzar() throws IllegalAccessException, InvocationTargetException {
        this.zztq.zzel = Integer.valueOf(2);
        boolean booleanValue = ((Boolean) this.zztz.invoke(null, new Object[]{this.zzps.getContext()})).booleanValue();
        synchronized (this.zztq) {
            if (booleanValue) {
                this.zztq.zzel = Integer.valueOf(1);
            } else {
                this.zztq.zzel = Integer.valueOf(0);
            }
        }
    }
}
