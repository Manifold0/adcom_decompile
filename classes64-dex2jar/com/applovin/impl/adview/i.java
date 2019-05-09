// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.adview;

class i implements Runnable
{
    final /* synthetic */ AdViewControllerImpl a;
    
    private i(final AdViewControllerImpl a) {
        this.a = a;
    }
    
    @Override
    public void run() {
        if (this.a.l == null) {
            return;
        }
        try {
            this.a.l.loadDataWithBaseURL("/", "<html></html>", "text/html", (String)null, "");
        }
        catch (Exception ex) {}
    }
}
