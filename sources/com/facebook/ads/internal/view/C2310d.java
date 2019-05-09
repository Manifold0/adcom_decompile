package com.facebook.ads.internal.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;

/* renamed from: com.facebook.ads.internal.view.d */
public final class C2310d extends RecyclerView {
    public C2310d(Context context) {
        super(context);
        setCarouselLayoutManager(context);
    }

    private void setCarouselLayoutManager(Context context) {
        LayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, false);
        linearLayoutManager.setAutoMeasureEnabled(true);
        super.setLayoutManager(linearLayoutManager);
    }

    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager) super.getLayoutManager();
    }

    public void setLayoutManager(LayoutManager layoutManager) {
    }
}
