package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnLongClickListener;

/* renamed from: com.applovin.impl.adview.z */
class C1272z implements OnLongClickListener {
    /* renamed from: a */
    final /* synthetic */ C1260n f1934a;

    C1272z(C1260n c1260n) {
        this.f1934a = c1260n;
    }

    public boolean onLongClick(View view) {
        this.f1934a.f1897a.mo4172d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
