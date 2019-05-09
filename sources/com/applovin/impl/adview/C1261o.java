package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/* renamed from: com.applovin.impl.adview.o */
class C1261o implements OnTouchListener {
    /* renamed from: a */
    final /* synthetic */ C1260n f1903a;

    C1261o(C1260n c1260n) {
        this.f1903a = c1260n;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
