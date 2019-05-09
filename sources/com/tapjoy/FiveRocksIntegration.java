package com.tapjoy;

import com.tapjoy.internal.be;
import com.tapjoy.internal.fo;
import com.tapjoy.internal.fp;
import com.tapjoy.internal.fr;
import com.tapjoy.internal.gc;
import com.tapjoy.internal.gd;

public class FiveRocksIntegration {
    /* renamed from: a */
    private static be f6762a = new be();

    /* renamed from: com.tapjoy.FiveRocksIntegration$1 */
    static class C27751 implements fr {
        C27751() {
        }

        /* renamed from: a */
        public final void mo6136a(String str) {
        }

        /* renamed from: b */
        public final void mo6139b(String str) {
            synchronized (FiveRocksIntegration.f6762a) {
                TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.f6762a.get(str);
            }
            if (tJPlacement != null && tJPlacement.f6958a != null) {
                tJPlacement.f6958a.onContentReady(tJPlacement);
            }
        }

        /* renamed from: c */
        public final void mo6140c(String str) {
            synchronized (FiveRocksIntegration.f6762a) {
                TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.f6762a.get(str);
            }
            if (tJPlacement != null && tJPlacement.f6958a != null) {
                tJPlacement.f6958a.onContentShow(tJPlacement);
            }
        }

        /* renamed from: d */
        public final void mo6141d(String str) {
        }

        /* renamed from: a */
        public final void mo6137a(String str, fo foVar) {
            if (foVar != null) {
                foVar.mo6323a(m6977e(str));
            }
        }

        /* renamed from: a */
        public final void mo6138a(String str, String str2, fo foVar) {
            if (foVar != null) {
                foVar.mo6323a(m6977e(str));
            }
            synchronized (FiveRocksIntegration.f6762a) {
                TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.f6762a.get(str);
            }
            if (tJPlacement != null) {
                TapjoyConnectCore.viewDidClose(str2);
                if (tJPlacement.f6958a != null) {
                    tJPlacement.f6958a.onContentDismiss(tJPlacement);
                }
            }
        }

        /* renamed from: e */
        private fp m6977e(final String str) {
            return new fp(this) {
                /* renamed from: b */
                final /* synthetic */ C27751 f6761b;

                /* renamed from: a */
                public final void mo6134a(final String str, String str2) {
                    synchronized (FiveRocksIntegration.f6762a) {
                        TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.f6762a.get(str);
                    }
                    if (tJPlacement != null && tJPlacement.f6958a != null) {
                        tJPlacement.f6958a.onPurchaseRequest(tJPlacement, new TJActionRequest(this) {
                            /* renamed from: b */
                            final /* synthetic */ C27741 f6756b;

                            public final String getRequestId() {
                                return str;
                            }

                            public final String getToken() {
                                return null;
                            }

                            public final void completed() {
                            }

                            public final void cancelled() {
                            }
                        }, str2);
                    }
                }

                /* renamed from: a */
                public final void mo6135a(final String str, String str2, int i, final String str3) {
                    synchronized (FiveRocksIntegration.f6762a) {
                        TJPlacement tJPlacement = (TJPlacement) FiveRocksIntegration.f6762a.get(str);
                    }
                    if (tJPlacement != null && tJPlacement.f6958a != null) {
                        tJPlacement.f6958a.onRewardRequest(tJPlacement, new TJActionRequest(this) {
                            /* renamed from: c */
                            final /* synthetic */ C27741 f6759c;

                            public final String getRequestId() {
                                return str;
                            }

                            public final String getToken() {
                                return str3;
                            }

                            public final void completed() {
                            }

                            public final void cancelled() {
                            }
                        }, str2, i);
                    }
                }
            };
        }
    }

    public static void addPlacementCallback(String placement, TJPlacement p) {
        synchronized (f6762a) {
            f6762a.put(placement, p);
        }
    }

    /* renamed from: a */
    public static void m6984a() {
        gc a = gc.m7831a();
        if (!a.f7851c) {
            a.f7851c = true;
        }
        fr c27751 = new C27751();
        gc.m7831a().f7864p = gd.m7853a(c27751);
    }
}
