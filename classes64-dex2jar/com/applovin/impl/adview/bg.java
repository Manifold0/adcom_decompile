// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

import java.util.concurrent.TimeUnit;

class bg implements ao
{
    final /* synthetic */ long a;
    final /* synthetic */ az b;
    
    bg(final az b, final long a) {
        this.b = b;
        this.a = a;
    }
    
    @Override
    public void a() {
        if (this.b.C != null) {
            final long seconds = TimeUnit.MILLISECONDS.toSeconds(this.a - this.b.videoView.getCurrentPosition());
            if (seconds <= 0L) {
                this.b.C.setVisibility(8);
                this.b.p = true;
            }
            else if (this.b.r()) {
                this.b.C.a((int)seconds);
            }
        }
    }
    
    @Override
    public boolean b() {
        return this.b.r();
    }
}
