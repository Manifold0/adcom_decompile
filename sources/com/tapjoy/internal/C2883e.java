package com.tapjoy.internal;

import android.app.Dialog;
import android.content.Context;

/* renamed from: com.tapjoy.internal.e */
public final class C2883e extends Dialog {
    /* renamed from: a */
    private boolean f7477a = false;

    public C2883e(Context context) {
        super(context, 16973835);
        requestWindowFeature(1);
        getWindow().setBackgroundDrawableResource(17170445);
    }

    public final void show() {
        this.f7477a = false;
        super.show();
    }

    public final void cancel() {
        this.f7477a = true;
        super.cancel();
    }
}
