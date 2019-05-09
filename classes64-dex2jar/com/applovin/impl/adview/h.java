// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class h implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    private h(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.l != null) {
            this.a.l.setVisibility(8);
        }
    }
}
