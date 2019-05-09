package com.kongregate.android.internal.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.KongregateEventBundle;
import com.kongregate.android.api.KongregateEventBundleListener;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.api.KongregateServices;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationListener;
import com.kongregate.android.api.MicrotransactionServices.ReceiptVerificationStatus;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.api.StatServices;
import com.kongregate.p000o.p001j.C0666a;
import com.kongregate.p000o.p001j.C0668c;
import com.kongregate.p000o.p009h.C0655b;
import com.kongregate.p000o.p009h.C0656c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: com.kongregate.android.internal.sdk.c */
abstract class C0485c implements KongregateAPI {
    /* renamed from: a */
    protected final long f415a;
    /* renamed from: b */
    protected final String f416b;
    /* renamed from: c */
    protected final Context f417c;
    /* renamed from: d */
    protected final KongregateServices f418d;
    /* renamed from: e */
    protected final StatServices f419e;
    /* renamed from: f */
    protected final MobileServices f420f;
    /* renamed from: g */
    protected final AnalyticsServices f421g;
    /* renamed from: h */
    protected final MicrotransactionServices f422h;
    /* renamed from: i */
    protected final AtomicReference<C0668c> f423i = new AtomicReference(new C0668c());
    /* renamed from: j */
    protected final Handler f424j;
    /* renamed from: k */
    private final Collection<C0491d> f425k = new LinkedList();
    /* renamed from: l */
    private final Collection<KongregateEventBundle> f426l = new LinkedList();

    /* renamed from: com.kongregate.android.internal.sdk.c$c */
    protected class C0482c implements StatServices {
        /* renamed from: a */
        final /* synthetic */ C0485c f412a;

        protected C0482c(C0485c c0485c) {
            this.f412a = c0485c;
        }

        public void submit(String str, long j) {
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.c$a */
    protected class C0489a implements KongregateServices {
        /* renamed from: a */
        final /* synthetic */ C0485c f458a;

        public C0489a(C0485c c0485c) {
            this.f458a = c0485c;
        }

        public String getUsername() {
            return ((C0668c) this.f458a.f423i.get()).m1195c();
        }

        public long getUserId() {
            return ((C0668c) this.f458a.f423i.get()).m1197e();
        }

        public boolean isGuest() {
            return ((C0668c) this.f458a.f423i.get()).m1198f();
        }

        public boolean isConnected() {
            return false;
        }

        public String getGameAuthToken() {
            return null;
        }

        public void setCharacterToken(String str) {
        }

        public boolean hasKongPlus() {
            return false;
        }

        public boolean hasUnreadGuildMessages() {
            return false;
        }

        public int getNotificationCount() {
            return 0;
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.c$b */
    protected static class C0490b implements MicrotransactionServices {
        /* renamed from: a */
        protected final ConcurrentHashMap<Long, C0655b> f459a = new ConcurrentHashMap();

        protected C0490b() {
        }

        public void requestUserItemList() {
        }

        public boolean hasItem(String str) {
            long g = C0666a.m1145a().m1177g();
            C0655b c0655b = g == 0 ? C0655b.f1057a : (C0655b) this.f459a.get(Long.valueOf(g));
            if (c0655b != null) {
                for (C0656c c0656c : c0655b.m1137a()) {
                    if (c0656c.f1061b.equals(str)) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void verifyTransaction(String str, String str2, ReceiptVerificationListener receiptVerificationListener) {
            throw new IllegalStateException("not implemented");
        }

        public void verifyTransaction(String str, String str2) {
            throw new IllegalStateException("not implemented");
        }

        public ReceiptVerificationStatus receiptVerificationStatus(String str) {
            throw new IllegalStateException("not implemented");
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.c$d */
    private static class C0491d {
        /* renamed from: a */
        KongregateEventBundleListener f460a;
        /* renamed from: b */
        KongregateEventListener f461b;

        private C0491d(KongregateEventListener kongregateEventListener) {
            this.f460a = null;
            this.f461b = null;
            this.f461b = kongregateEventListener;
        }

        private C0491d(KongregateEventBundleListener kongregateEventBundleListener) {
            this.f460a = null;
            this.f461b = null;
            this.f460a = kongregateEventBundleListener;
        }

        /* renamed from: a */
        private void m429a(KongregateEventBundle kongregateEventBundle) {
            if (this.f460a != null) {
                this.f460a.onKongregateEventBundle(kongregateEventBundle.getName(), kongregateEventBundle.getJSONBundle());
            } else if (this.f461b != null) {
                this.f461b.onEvent(kongregateEventBundle.getName());
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            C0491d c0491d = (C0491d) obj;
            if (this.f460a == null ? c0491d.f460a != null : !this.f460a.equals(c0491d.f460a)) {
                return false;
            }
            if (this.f461b != null) {
                if (this.f461b.equals(c0491d.f461b)) {
                    return true;
                }
            } else if (c0491d.f461b == null) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            int hashCode;
            int i = 0;
            if (this.f460a != null) {
                hashCode = this.f460a.hashCode();
            } else {
                hashCode = 0;
            }
            hashCode *= 31;
            if (this.f461b != null) {
                i = this.f461b.hashCode();
            }
            return hashCode + i;
        }
    }

    public C0485c(Context context, long j, String str) {
        this.f415a = j;
        this.f416b = str;
        this.f417c = context.getApplicationContext();
        this.f424j = new Handler(Looper.getMainLooper());
        this.f418d = mo1150a();
        this.f419e = mo1151b();
        this.f420f = mo1152c();
        this.f421g = mo1153d();
        this.f422h = mo1154e();
    }

    public KongregateServices services() {
        return this.f418d;
    }

    public StatServices stats() {
        return this.f419e;
    }

    public MobileServices mobile() {
        return this.f420f;
    }

    public AnalyticsServices analytics() {
        return this.f421g;
    }

    public MicrotransactionServices mtx() {
        return this.f422h;
    }

    public long getApplicationId() {
        return this.f415a;
    }

    public String getApiKey() {
        return this.f416b;
    }

    /* renamed from: a */
    protected boolean m311a(C0668c c0668c) {
        if (c0668c != null) {
            return ((C0668c) this.f423i.getAndSet(c0668c)).m1197e() != c0668c.m1197e();
        } else {
            throw new IllegalArgumentException("User cannot be null");
        }
    }

    /* renamed from: a */
    protected void m308a(C0491d c0491d) {
        if (c0491d != null) {
            synchronized (this.f425k) {
                boolean isEmpty = this.f425k.isEmpty();
                this.f425k.add(c0491d);
                if (isEmpty) {
                    for (KongregateEventBundle a : this.f426l) {
                        m307a(a);
                    }
                    this.f426l.clear();
                }
            }
        }
    }

    public void addEventListener(KongregateEventListener kongregateEventListener) {
        m308a(new C0491d(kongregateEventListener));
    }

    public void addEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        m308a(new C0491d(kongregateEventBundleListener));
    }

    public void removeEventBundleListener(KongregateEventBundleListener kongregateEventBundleListener) {
        m313b(new C0491d(kongregateEventBundleListener));
    }

    public void removeEventListener(KongregateEventListener kongregateEventListener) {
        m313b(new C0491d(kongregateEventListener));
    }

    /* renamed from: b */
    protected void m313b(C0491d c0491d) {
        synchronized (this.f425k) {
            this.f425k.remove(c0491d);
        }
    }

    public List<String> pollEvents() {
        List<String> arrayList;
        synchronized (this.f425k) {
            arrayList = new ArrayList(this.f426l.size());
            for (KongregateEventBundle name : this.f426l) {
                arrayList.add(name.getName());
            }
            this.f426l.clear();
        }
        return arrayList;
    }

    public List<KongregateEventBundle> pollEventBundles() {
        List arrayList;
        synchronized (this.f425k) {
            arrayList = new ArrayList(this.f426l);
            this.f426l.clear();
        }
        return arrayList;
    }

    public boolean isReady() {
        return false;
    }

    public Context getApplicationContext() {
        return this.f417c.getApplicationContext();
    }

    /* renamed from: a */
    protected void m309a(String str) {
        m310a(str, null);
    }

    /* renamed from: a */
    protected void m310a(String str, String str2) {
        m307a(new KongregateEventBundle(str, str2));
    }

    /* renamed from: a */
    protected void m307a(final KongregateEventBundle kongregateEventBundle) {
        this.f424j.post(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ C0485c f457b;

            public void run() {
                synchronized (this.f457b.f425k) {
                    if (this.f457b.f425k.isEmpty()) {
                        this.f457b.f426l.add(kongregateEventBundle);
                        return;
                    }
                    for (C0491d a : this.f457b.f425k) {
                        a.m429a(kongregateEventBundle);
                    }
                }
            }
        });
    }

    /* renamed from: a */
    protected KongregateServices mo1150a() {
        return new C0489a(this);
    }

    /* renamed from: b */
    protected StatServices mo1151b() {
        return new C0482c(this);
    }

    /* renamed from: c */
    protected MobileServices mo1152c() {
        return null;
    }

    /* renamed from: d */
    protected AnalyticsServices mo1153d() {
        return null;
    }

    /* renamed from: e */
    protected MicrotransactionServices mo1154e() {
        return new C0490b();
    }
}
