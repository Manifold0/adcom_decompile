package com.tapjoy.internal;

import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import com.kongregate.p000o.p003a.C0578a;
import com.tapjoy.internal.dy.C2879a;
import com.tapjoy.internal.ga.C29361;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public final class gp {
    /* renamed from: a */
    final gb f7967a;
    /* renamed from: b */
    final AtomicBoolean f7968b = new AtomicBoolean();
    /* renamed from: c */
    ScheduledFuture f7969c;
    /* renamed from: d */
    private final Runnable f7970d = new C29561(this);
    /* renamed from: e */
    private final Runnable f7971e = new C29572(this);

    /* renamed from: com.tapjoy.internal.gp$1 */
    class C29561 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ gp f7965a;

        C29561(gp gpVar) {
            this.f7965a = gpVar;
        }

        public final void run() {
            if (this.f7965a.f7968b.compareAndSet(true, false)) {
                fz.m7811a("The session ended");
                gb gbVar = this.f7965a.f7967a;
                long elapsedRealtime = SystemClock.elapsedRealtime() - gbVar.f7840c;
                gf gfVar = gbVar.f7838a;
                synchronized (gfVar) {
                    long a = gfVar.f7894c.f7939i.m8211a() + elapsedRealtime;
                    gfVar.f7894c.f7939i.m8214a(a);
                    gfVar.f7893b.f7602i = Long.valueOf(a);
                }
                C2879a a2 = gbVar.m7823a(eb.APP, C0578a.f789b);
                a2.f7420i = Long.valueOf(elapsedRealtime);
                gbVar.m7824a(a2);
                gbVar.f7840c = 0;
                gf gfVar2 = gbVar.f7838a;
                long longValue = a2.f7416e.longValue();
                synchronized (gfVar2) {
                    Editor a3 = gfVar2.f7894c.m7972a();
                    gfVar2.f7894c.f7940j.m8213a(a3, longValue);
                    gfVar2.f7894c.f7941k.m8213a(a3, elapsedRealtime);
                    a3.commit();
                    gfVar2.f7893b.f7603j = Long.valueOf(longValue);
                    gfVar2.f7893b.f7604k = Long.valueOf(elapsedRealtime);
                }
                ga gaVar = gbVar.f7839b;
                if (gaVar.f7834b != null) {
                    gaVar.m7821a();
                    new C29361(gaVar).run();
                }
                gaVar.f7833a.flush();
                ev.f7703d.notifyObservers();
            }
        }
    }

    /* renamed from: com.tapjoy.internal.gp$2 */
    class C29572 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ gp f7966a;

        C29572(gp gpVar) {
            this.f7966a = gpVar;
        }

        public final void run() {
        }
    }

    gp(gb gbVar) {
        this.f7967a = gbVar;
    }

    /* renamed from: a */
    public final void m7982a() {
        if (!this.f7968b.get()) {
            return;
        }
        if (!Boolean.FALSE.booleanValue()) {
            this.f7970d.run();
        } else if (this.f7969c == null || this.f7969c.cancel(false)) {
            this.f7969c = gq.f7972a.schedule(this.f7970d, 3000, TimeUnit.MILLISECONDS);
        }
    }
}
