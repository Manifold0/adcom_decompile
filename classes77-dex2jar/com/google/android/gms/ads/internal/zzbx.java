// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.ArrayList;
import android.view.MotionEvent;
import android.view.View;
import android.app.Activity;
import android.view.ViewTreeObserver$OnScrollChangedListener;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.internal.ads.zzamt;
import com.google.android.gms.internal.ads.zzald;
import android.widget.ViewSwitcher;

public final class zzbx extends ViewSwitcher
{
    private final zzald zzaed;
    @Nullable
    private final zzamt zzaee;
    private boolean zzaef;
    
    public zzbx(final Context context, final String adUnitId, final String s, final ViewTreeObserver$OnGlobalLayoutListener viewTreeObserver$OnGlobalLayoutListener, final ViewTreeObserver$OnScrollChangedListener viewTreeObserver$OnScrollChangedListener) {
        super(context);
        (this.zzaed = new zzald(context)).setAdUnitId(adUnitId);
        this.zzaed.zzda(s);
        this.zzaef = true;
        if (context instanceof Activity) {
            this.zzaee = new zzamt((Activity)context, (View)this, viewTreeObserver$OnGlobalLayoutListener, viewTreeObserver$OnScrollChangedListener);
        }
        else {
            this.zzaee = new zzamt(null, (View)this, viewTreeObserver$OnGlobalLayoutListener, viewTreeObserver$OnScrollChangedListener);
        }
        this.zzaee.zzsc();
    }
    
    protected final void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzaee != null) {
            this.zzaee.onAttachedToWindow();
        }
    }
    
    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzaee != null) {
            this.zzaee.onDetachedFromWindow();
        }
    }
    
    public final boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        if (this.zzaef) {
            this.zzaed.zze(motionEvent);
        }
        return false;
    }
    
    public final void removeAllViews() {
        final int n = 0;
        final ArrayList<zzaqw> list = new ArrayList<zzaqw>();
        for (int i = 0; i < this.getChildCount(); ++i) {
            final View child = this.getChildAt(i);
            if (child != null && child instanceof zzaqw) {
                list.add((zzaqw)child);
            }
        }
        super.removeAllViews();
        final ArrayList<zzaqw> list2 = list;
        final int size = list2.size();
        int j = n;
        while (j < size) {
            final zzaqw value = list2.get(j);
            ++j;
            value.destroy();
        }
    }
    
    public final zzald zzfr() {
        return this.zzaed;
    }
    
    public final void zzfs() {
        zzakb.v("Disable position monitoring on adFrame.");
        if (this.zzaee != null) {
            this.zzaee.zzsd();
        }
    }
    
    public final void zzft() {
        zzakb.v("Enable debug gesture detector on adFrame.");
        this.zzaef = true;
    }
    
    public final void zzfu() {
        zzakb.v("Disable debug gesture detector on adFrame.");
        this.zzaef = false;
    }
}
