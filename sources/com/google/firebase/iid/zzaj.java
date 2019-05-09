package com.google.firebase.iid;

import android.os.Bundle;

final class zzaj extends zzak<Void> {
    zzaj(int i, int i2, Bundle bundle) {
        super(i, 2, bundle);
    }

    final boolean zzab() {
        return true;
    }

    final void zzb(Bundle bundle) {
        if (bundle.getBoolean("ack", false)) {
            finish(null);
        } else {
            zza(new zzal(4, "Invalid response to one way request"));
        }
    }
}
