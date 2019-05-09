package com.applovin.impl.adview;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

/* renamed from: com.applovin.impl.adview.c */
class C1249c implements OnDismissListener {
    /* renamed from: a */
    final /* synthetic */ C1248b f1829a;

    C1249c(C1248b c1248b) {
        this.f1829a = c1248b;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        this.f1829a.f1792a.contractAd();
    }
}
