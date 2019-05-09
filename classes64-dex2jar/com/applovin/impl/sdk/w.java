// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

class w extends dx
{
    final /* synthetic */ AppLovinAdServiceImpl a;
    private final n b;
    
    private w(final AppLovinAdServiceImpl a, final n b) {
        this.a = a;
        super("UpdateAdTask", a.a);
        this.b = b;
    }
    
    @Override
    public void run() {
    Label_0102_Outer:
        while (true) {
            boolean b = true;
            this.e.d("AppLovinAdService", "Attempt update for spec: " + this.b);
            final v b2 = this.a.a(this.b);
            while (true) {
            Label_0275:
                while (true) {
                    synchronized (b2.a) {
                        final boolean i = this.b.i();
                        final boolean d = this.a.a();
                        if (!b2.e.isEmpty()) {
                            final boolean b3 = true;
                            if (System.currentTimeMillis() > b2.c) {
                                this.e.d("AppLovinAdService", "Update ad states - isRefreshEnabled=" + i + " hasUpdateListeners=" + b3 + " isCurrentAdExpired=" + b + " isDeviceOn=" + d + " isWaitingForAd=" + b2.d);
                                if (i && b3 && b && d && !b2.d) {
                                    this.e.d("AppLovinAdService", "Performing ad update...");
                                    b2.d = true;
                                    this.a.a(this.b, new u(this.a, b2, null));
                                }
                                else {
                                    this.e.d("AppLovinAdService", "Ad update skipped");
                                }
                                return;
                            }
                            break Label_0275;
                        }
                    }
                    final boolean b3 = false;
                    continue Label_0102_Outer;
                }
                b = false;
                continue;
            }
        }
    }
}
