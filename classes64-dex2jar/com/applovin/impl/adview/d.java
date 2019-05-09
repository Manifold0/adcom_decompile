// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import android.view.View;

class d implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    d(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.d();
        if (this.a.b != null && this.a.l != null && this.a.l.getParent() == null) {
            this.a.b.addView((View)this.a.l);
            b((View)this.a.l, this.a.p.getSize());
        }
    }
}
