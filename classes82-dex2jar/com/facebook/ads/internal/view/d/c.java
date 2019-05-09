// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.d;

import android.support.v7.widget.RecyclerView$LayoutManager;
import android.view.MotionEvent;
import android.view.View;
import android.util.AttributeSet;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View$OnTouchListener;
import android.support.v7.widget.RecyclerView;

public class c extends RecyclerView implements View$OnTouchListener
{
    protected final int a;
    protected int b;
    private int c;
    private boolean d;
    private boolean e;
    private LinearLayoutManager f;
    private a g;
    
    public c(final Context context) {
        super(context);
        this.b = 0;
        this.c = 0;
        this.d = true;
        this.e = false;
        this.a = this.a();
        this.setOnTouchListener((View$OnTouchListener)this);
    }
    
    public c(final Context context, final AttributeSet set) {
        super(context, set);
        this.b = 0;
        this.c = 0;
        this.d = true;
        this.e = false;
        this.a = this.a();
        this.setOnTouchListener((View$OnTouchListener)this);
    }
    
    public c(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.b = 0;
        this.c = 0;
        this.d = true;
        this.e = false;
        this.a = this.a();
        this.setOnTouchListener((View$OnTouchListener)this);
    }
    
    private int a() {
        return (int)this.getContext().getResources().getDisplayMetrics().density * 10;
    }
    
    private int getItemCount() {
        if (this.getAdapter() == null) {
            return 0;
        }
        return this.getAdapter().getItemCount();
    }
    
    protected void a(final int b, final boolean b2) {
        if (this.getAdapter() == null) {
            return;
        }
        this.b = b;
        if (b2) {
            this.smoothScrollToPosition(b);
            return;
        }
        this.scrollToPosition(b);
    }
    
    public int getCurrentPosition() {
        return this.b;
    }
    
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        final int c = (int)motionEvent.getRawX();
        final int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 1 || actionMasked == 6 || actionMasked == 3 || actionMasked == 4) {
            if (this.e) {
                final int n = this.c - c;
                final int a = this.g.a(n);
                int n2;
                if (n > this.a) {
                    n2 = Math.min(this.b + a, this.getItemCount() - 1);
                }
                else if (n < -this.a) {
                    n2 = Math.max(this.b - a, 0);
                }
                else {
                    n2 = this.b;
                }
                this.a(n2, true);
            }
            this.d = true;
            this.e = false;
            return true;
        }
        if (actionMasked == 0 || actionMasked == 5 || (this.d && actionMasked == 2)) {
            this.c = c;
            if (this.d) {
                this.d = false;
            }
            this.e = true;
        }
        return false;
    }
    
    public void setLayoutManager(final RecyclerView$LayoutManager layoutManager) {
        if (!(layoutManager instanceof LinearLayoutManager)) {
            throw new IllegalArgumentException("SnapRecyclerView only supports LinearLayoutManager");
        }
        super.setLayoutManager(layoutManager);
        this.f = (LinearLayoutManager)layoutManager;
    }
    
    public void setSnapDelegate(final a g) {
        this.g = g;
    }
    
    public interface a
    {
        int a(final int p0);
    }
}
