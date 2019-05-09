// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bh implements ao
{
    final /* synthetic */ az a;
    
    bh(final az a) {
        this.a = a;
    }
    
    @Override
    public void a() {
        if (this.a.H != null) {
            if (!this.a.shouldContinueFullLengthVideoCountdown()) {
                this.a.H.setVisibility(8);
                return;
            }
            this.a.H.setProgress((int)(this.a.settingsProxy.af() * (this.a.videoView.getCurrentPosition() / (float)this.a.videoView.getDuration())));
        }
    }
    
    @Override
    public boolean b() {
        return this.a.shouldContinueFullLengthVideoCountdown();
    }
}
