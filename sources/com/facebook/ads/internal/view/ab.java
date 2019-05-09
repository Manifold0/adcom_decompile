package com.facebook.ads.internal.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;

public class ab extends View {
    /* renamed from: a */
    private aa f5155a;

    public ab(Context context, aa aaVar) {
        super(context);
        this.f5155a = aaVar;
        setLayoutParams(new LayoutParams(0, 0));
    }

    public void onWindowVisibilityChanged(int i) {
        if (this.f5155a != null) {
            this.f5155a.mo5502a(i);
        }
    }
}
