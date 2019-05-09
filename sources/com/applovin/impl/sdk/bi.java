package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class bi implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ bh f2145a;

    bi(bh bhVar) {
        this.f2145a = bhVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f2145a.f2144b.f2142f.schedule(new bj(this), 200);
    }
}
