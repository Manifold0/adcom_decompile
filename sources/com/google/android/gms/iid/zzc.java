package com.google.android.gms.iid;

import android.content.Intent;

final class zzc implements Runnable {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ Intent zzibw;
    private /* synthetic */ zzb zzibx;

    zzc(zzb zzb, Intent intent, Intent intent2) {
        this.zzibx = zzb;
        this.val$intent = intent;
        this.zzibw = intent2;
    }

    public final void run() {
        this.zzibx.handleIntent(this.val$intent);
        this.zzibx.zzh(this.zzibw);
    }
}
