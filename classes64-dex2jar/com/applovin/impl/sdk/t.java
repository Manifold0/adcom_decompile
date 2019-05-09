// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class t implements Runnable
{
    final /* synthetic */ r a;
    
    t(final r a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        this.a.a.removeClickTrackingOverlay();
    }
}
