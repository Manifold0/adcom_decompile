// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.g;

import android.support.v7.widget.RecyclerView$ViewHolder;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup;
import java.util.List;
import android.support.v7.widget.RecyclerView$Adapter;

public class d extends RecyclerView$Adapter<f>
{
    private final List<String> a;
    private final int b;
    
    d(final List<String> a, final int b) {
        this.a = a;
        this.b = b;
    }
    
    public f a(final ViewGroup viewGroup, final int n) {
        return new f(new e(viewGroup.getContext()));
    }
    
    public void a(final f f, int b) {
        final String s = this.a.get(b);
        final ViewGroup$MarginLayoutParams layoutParams = new ViewGroup$MarginLayoutParams(-2, -1);
        int b2;
        if (b == 0) {
            b2 = this.b * 4;
        }
        else {
            b2 = this.b;
        }
        if (b >= this.getItemCount() - 1) {
            b = this.b * 4;
        }
        else {
            b = this.b;
        }
        layoutParams.setMargins(b2, 0, b, 0);
        f.a().setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        f.a().a(s);
    }
    
    public int getItemCount() {
        return this.a.size();
    }
}
