// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class bk implements cp
{
    final /* synthetic */ az a;
    
    bk(final az a) {
        this.a = a;
    }
    
    @Override
    public void a(final cn cn) {
        this.a.logger.d("InterActivity", "Clicking through from video button...");
        this.a.clickThroughFromVideo();
    }
    
    @Override
    public void b(final cn cn) {
        this.a.logger.d("InterActivity", "Closing ad from video button...");
        this.a.dismiss();
    }
    
    @Override
    public void c(final cn cn) {
        this.a.logger.d("InterActivity", "Skipping video from video button...");
        this.a.skipVideo();
    }
}
