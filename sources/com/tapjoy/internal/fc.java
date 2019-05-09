package com.tapjoy.internal;

import android.content.Context;
import com.tapjoy.TJActionRequest;
import com.tapjoy.TJError;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TapjoyConstants;
import com.tapjoy.TapjoyLog;
import java.util.Observable;
import java.util.Observer;

abstract class fc {
    /* renamed from: b */
    volatile C2915a f7692b;

    /* renamed from: com.tapjoy.internal.fc$a */
    class C2915a implements TJPlacementListener, Observer {
        /* renamed from: a */
        final /* synthetic */ fc f7723a;
        /* renamed from: b */
        private final Object f7724b;
        /* renamed from: c */
        private final el f7725c;
        /* renamed from: d */
        private volatile boolean f7726d;
        /* renamed from: e */
        private TJPlacement f7727e;

        C2915a(fc fcVar, Object obj) {
            this(fcVar, obj, new el(TapjoyConstants.TIMER_INCREMENT));
        }

        C2915a(fc fcVar, Object obj, el elVar) {
            this.f7723a = fcVar;
            this.f7724b = obj;
            this.f7725c = elVar;
        }

        /* renamed from: a */
        final void m7715a() {
            synchronized (this) {
                if (this.f7726d) {
                } else if (this.f7725c.m7649a()) {
                    m7714a("Timed out");
                } else {
                    if (!TapjoyConnectCore.isConnected()) {
                        ev.f7700a.addObserver(this);
                        if (TapjoyConnectCore.isConnected()) {
                            ev.f7700a.deleteObserver(this);
                        } else {
                            return;
                        }
                    }
                    if (this.f7727e == null) {
                        if (this.f7723a.mo6272a()) {
                            this.f7727e = this.f7723a.mo6270a(TapjoyConnectCore.getContext(), this, this.f7724b);
                            this.f7727e.requestContent();
                            return;
                        }
                        m7714a("Cannot request");
                    } else if (!this.f7727e.isContentReady()) {
                    } else if (this.f7723a.mo6274a((Observer) this)) {
                        this.f7727e.showContent();
                        m7714a(null);
                    }
                }
            }
        }

        /* renamed from: a */
        private void m7714a(String str) {
            synchronized (this) {
                String a = this.f7723a.mo6271a(this.f7724b);
                if (str == null) {
                    TapjoyLog.m7129i("SystemPlacement", "Placement " + a + " is presented now");
                } else {
                    TapjoyLog.m7129i("SystemPlacement", "Cannot show placement " + a + " now (" + str + ")");
                }
                this.f7726d = true;
                this.f7727e = null;
                ev.f7700a.deleteObserver(this);
                ev.f7704e.deleteObserver(this);
                ev.f7702c.deleteObserver(this);
            }
            fc fcVar = this.f7723a;
            synchronized (fcVar) {
                if (fcVar.f7692b == this) {
                    fcVar.f7692b = null;
                }
            }
        }

        public final void update(Observable observable, Object data) {
            m7715a();
        }

        public final void onRequestSuccess(TJPlacement placement) {
        }

        public final void onRequestFailure(TJPlacement placement, TJError error) {
            m7714a(error.message);
        }

        public final void onContentReady(TJPlacement placement) {
            m7715a();
        }

        public final void onContentShow(TJPlacement placement) {
        }

        public final void onContentDismiss(TJPlacement placement) {
        }

        public final void onPurchaseRequest(TJPlacement placement, TJActionRequest request, String productId) {
        }

        public final void onRewardRequest(TJPlacement placement, TJActionRequest request, String itemId, int quantity) {
        }
    }

    /* renamed from: a */
    protected abstract TJPlacement mo6270a(Context context, TJPlacementListener tJPlacementListener, Object obj);

    /* renamed from: a */
    protected abstract String mo6271a(Object obj);

    fc() {
    }

    /* renamed from: c */
    public final boolean m7678c(Object obj) {
        if (!mo6272a()) {
            return false;
        }
        C2915a c2915a = null;
        synchronized (this) {
            if (this.f7692b == null) {
                c2915a = mo6275b(obj);
                this.f7692b = c2915a;
            }
        }
        if (c2915a == null) {
            return false;
        }
        c2915a.m7715a();
        return true;
    }

    /* renamed from: b */
    protected C2915a mo6275b(Object obj) {
        return new C2915a(this, obj);
    }

    /* renamed from: a */
    protected boolean mo6272a() {
        return !TapjoyConnectCore.isFullScreenViewOpen();
    }

    /* renamed from: a */
    protected boolean mo6274a(Observer observer) {
        if (TapjoyConnectCore.isFullScreenViewOpen()) {
            ev.f7704e.addObserver(observer);
            if (TapjoyConnectCore.isFullScreenViewOpen()) {
                return false;
            }
            ev.f7704e.deleteObserver(observer);
        }
        if (!gc.m7831a().m7850d()) {
            ev.f7702c.addObserver(observer);
            if (!gc.m7831a().m7850d()) {
                return false;
            }
            ev.f7702c.deleteObserver(observer);
        }
        return true;
    }
}
