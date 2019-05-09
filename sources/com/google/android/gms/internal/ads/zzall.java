package com.google.android.gms.internal.ads;

import android.app.AlertDialog.Builder;
import android.content.Context;

final class zzall implements Runnable {
    final /* synthetic */ Context val$context;
    private final /* synthetic */ String zzcsq;
    private final /* synthetic */ boolean zzcsr;
    private final /* synthetic */ boolean zzcss;

    zzall(zzalk zzalk, Context context, String str, boolean z, boolean z2) {
        this.val$context = context;
        this.zzcsq = str;
        this.zzcsr = z;
        this.zzcss = z2;
    }

    public final void run() {
        Builder builder = new Builder(this.val$context);
        builder.setMessage(this.zzcsq);
        if (this.zzcsr) {
            builder.setTitle("Error");
        } else {
            builder.setTitle("Info");
        }
        if (this.zzcss) {
            builder.setNeutralButton("Dismiss", null);
        } else {
            builder.setPositiveButton("Learn More", new zzalm(this));
            builder.setNegativeButton("Dismiss", null);
        }
        builder.create().show();
    }
}
