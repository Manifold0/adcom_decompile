// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

public final class d extends RecyclerView
{
    public d(final Context carouselLayoutManager) {
        super(carouselLayoutManager);
        this.setCarouselLayoutManager(carouselLayoutManager);
    }
    
    private void setCarouselLayoutManager(final Context context) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context, 0, false);
        layoutManager.setAutoMeasureEnabled(true);
        super.setLayoutManager((RecyclerView$LayoutManager)layoutManager);
    }
    
    public LinearLayoutManager getLayoutManager() {
        return (LinearLayoutManager)super.getLayoutManager();
    }
    
    public void setLayoutManager(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
    }
}
