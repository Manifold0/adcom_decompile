package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bk implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bh f2147a;

    bk(bh bhVar) {
        this.f2147a = bhVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f2147a.f2144b.f2138b.m2336a(this.f2147a.f2143a, this.f2147a.f2144b.f2141e);
    }
}
