package com.google.android.gms.internal.firebase_messaging;

final class zzg extends zzd {
    private final zze zzh = new zze();

    zzg() {
    }

    public final void zza(Throwable th, Throwable th2) {
        if (th2 == th) {
            throw new IllegalArgumentException("Self suppression is not allowed.", th2);
        } else if (th2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        } else {
            this.zzh.zza(th, true).add(th2);
        }
    }
}
