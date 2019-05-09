// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import com.applovin.impl.sdk.an;

class ab implements Runnable
{
    final /* synthetic */ an a;
    final /* synthetic */ n b;
    
    ab(final n b, final an a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void run() {
        this.b.getSettings().setMediaPlaybackRequiresUserGesture(this.a.ag());
    }
}
