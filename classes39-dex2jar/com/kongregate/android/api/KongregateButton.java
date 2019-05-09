// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api;

import com.kongregate.android.internal.util.g;
import android.widget.ImageView$ScaleType;
import com.kongregate.android.internal.util.j;
import android.view.View;
import android.view.View$OnClickListener;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.ImageView;

public class KongregateButton extends ImageView
{
    private boolean mInitialized;
    
    public KongregateButton(final Context context) {
        super(context);
        this.setup(context, null);
        this.mInitialized = true;
    }
    
    public KongregateButton(final Context context, final AttributeSet set) {
        super(context, set);
        this.setup(context, set);
        this.mInitialized = true;
    }
    
    public KongregateButton(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.setup(context, set);
        this.mInitialized = true;
    }
    
    private void setup(final Context context, final AttributeSet set) {
        this.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                final KongregateAPI instance = APIBootstrap.getInstance();
                if (instance != null) {
                    instance.mobile().openKongregateWindow(KongregateButton.this.getContext());
                    return;
                }
                j.c("Kongregate API not initialized");
            }
        });
        this.setScaleType(ImageView$ScaleType.FIT_CENTER);
        if (!this.isInEditMode()) {
            this.setImageResource(g.a("kongregate_button", "drawable", context));
        }
    }
    
    public void setOnClickListener(final View$OnClickListener onClickListener) {
        if (!this.mInitialized) {
            super.setOnClickListener(onClickListener);
            return;
        }
        throw new IllegalStateException("Don't call setOnClickListener for KongregateButton");
    }
}
