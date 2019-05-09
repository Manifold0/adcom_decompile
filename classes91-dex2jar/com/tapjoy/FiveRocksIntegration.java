// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.gd;
import com.tapjoy.internal.fo;
import com.tapjoy.internal.fp;
import com.tapjoy.internal.fr;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.be;

public class FiveRocksIntegration
{
    private static be a;
    
    static {
        FiveRocksIntegration.a = new be();
    }
    
    public static void a() {
        final gc a = gc.a();
        if (!a.c) {
            a.c = true;
        }
        gc.a().p = gd.a(new fr() {
            private fp e(final String s) {
                return new fp() {
                    @Override
                    public final void a(final String s, final String s2) {
                        synchronized (FiveRocksIntegration.a) {
                            final TJPlacement tjPlacement = (TJPlacement)FiveRocksIntegration.a.get(s);
                            // monitorexit(FiveRocksIntegration.b())
                            if (tjPlacement != null && tjPlacement.a != null) {
                                tjPlacement.a.onPurchaseRequest(tjPlacement, new TJActionRequest() {
                                    @Override
                                    public final void cancelled() {
                                    }
                                    
                                    @Override
                                    public final void completed() {
                                    }
                                    
                                    @Override
                                    public final String getRequestId() {
                                        return s;
                                    }
                                    
                                    @Override
                                    public final String getToken() {
                                        return null;
                                    }
                                }, s2);
                            }
                        }
                    }
                    
                    @Override
                    public final void a(final String s, final String s2, final int n, final String s3) {
                        synchronized (FiveRocksIntegration.a) {
                            final TJPlacement tjPlacement = (TJPlacement)FiveRocksIntegration.a.get(s);
                            // monitorexit(FiveRocksIntegration.b())
                            if (tjPlacement != null && tjPlacement.a != null) {
                                tjPlacement.a.onRewardRequest(tjPlacement, new TJActionRequest() {
                                    @Override
                                    public final void cancelled() {
                                    }
                                    
                                    @Override
                                    public final void completed() {
                                    }
                                    
                                    @Override
                                    public final String getRequestId() {
                                        return s;
                                    }
                                    
                                    @Override
                                    public final String getToken() {
                                        return s3;
                                    }
                                }, s2, n);
                            }
                        }
                    }
                };
            }
            
            @Override
            public final void a(final String s) {
            }
            
            @Override
            public final void a(final String s, final fo fo) {
                if (fo != null) {
                    fo.a(this.e(s));
                }
            }
            
            @Override
            public final void a(final String s, final String s2, final fo fo) {
                if (fo != null) {
                    fo.a(this.e(s));
                }
                synchronized (FiveRocksIntegration.a) {
                    final TJPlacement tjPlacement = (TJPlacement)FiveRocksIntegration.a.get(s);
                    // monitorexit(FiveRocksIntegration.b())
                    if (tjPlacement != null) {
                        TapjoyConnectCore.viewDidClose(s2);
                        if (tjPlacement.a != null) {
                            tjPlacement.a.onContentDismiss(tjPlacement);
                        }
                    }
                }
            }
            
            @Override
            public final void b(final String s) {
                synchronized (FiveRocksIntegration.a) {
                    final TJPlacement tjPlacement = (TJPlacement)FiveRocksIntegration.a.get(s);
                    // monitorexit(FiveRocksIntegration.b())
                    if (tjPlacement != null && tjPlacement.a != null) {
                        tjPlacement.a.onContentReady(tjPlacement);
                    }
                }
            }
            
            @Override
            public final void c(final String s) {
                synchronized (FiveRocksIntegration.a) {
                    final TJPlacement tjPlacement = (TJPlacement)FiveRocksIntegration.a.get(s);
                    // monitorexit(FiveRocksIntegration.b())
                    if (tjPlacement != null && tjPlacement.a != null) {
                        tjPlacement.a.onContentShow(tjPlacement);
                    }
                }
            }
            
            @Override
            public final void d(final String s) {
            }
        });
    }
    
    public static void addPlacementCallback(final String s, final TJPlacement tjPlacement) {
        synchronized (FiveRocksIntegration.a) {
            FiveRocksIntegration.a.put(s, tjPlacement);
        }
    }
}
