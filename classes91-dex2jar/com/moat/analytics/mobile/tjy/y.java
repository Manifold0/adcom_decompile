// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.tjy;

import android.util.Log;
import android.view.View;
import com.moat.analytics.mobile.tjy.base.functional.a;
import java.lang.ref.WeakReference;

class y implements ba
{
    final /* synthetic */ WeakReference a;
    final /* synthetic */ ap b;
    final /* synthetic */ String c;
    final /* synthetic */ v d;
    
    y(final v d, final WeakReference a, final ap b, final String c) {
        this.d = d;
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    @Override
    public a a() {
        final View view = (View)this.a.get();
        if (view == null) {
            if (this.b.b()) {
                Log.e("MoatFactory", "Target view is null. Not creating NativeDisplayTracker.");
            }
            return com.moat.analytics.mobile.tjy.base.functional.a.a();
        }
        if (this.b.b()) {
            Log.d("MoatFactory", "Creating NativeDisplayTracker for " + view.getClass().getSimpleName() + "@" + view.hashCode());
        }
        return com.moat.analytics.mobile.tjy.base.functional.a.a(new af(view, this.c, this.d.b, this.b));
    }
}
