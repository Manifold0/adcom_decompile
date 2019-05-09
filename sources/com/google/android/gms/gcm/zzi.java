package com.google.android.gms.gcm;

import android.os.Bundle;

public final class zzi {
    public static final zzi zziad = new zzi(0, 30, 3600);
    private static zzi zziae = new zzi(1, 30, 3600);
    private final int zziaf;
    private final int zziag = 30;
    private final int zziah = 3600;

    private zzi(int i, int i2, int i3) {
        this.zziaf = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzi)) {
            return false;
        }
        zzi zzi = (zzi) obj;
        return zzi.zziaf == this.zziaf && zzi.zziag == this.zziag && zzi.zziah == this.zziah;
    }

    public final int hashCode() {
        return (((((this.zziaf + 1) ^ 1000003) * 1000003) ^ this.zziag) * 1000003) ^ this.zziah;
    }

    public final String toString() {
        int i = this.zziaf;
        int i2 = this.zziag;
        return "policy=" + i + " initial_backoff=" + i2 + " maximum_backoff=" + this.zziah;
    }

    public final int zzaul() {
        return this.zziaf;
    }

    public final int zzaum() {
        return this.zziag;
    }

    public final int zzaun() {
        return this.zziah;
    }

    public final Bundle zzu(Bundle bundle) {
        bundle.putInt("retry_policy", this.zziaf);
        bundle.putInt("initial_backoff_seconds", this.zziag);
        bundle.putInt("maximum_backoff_seconds", this.zziah);
        return bundle;
    }
}
