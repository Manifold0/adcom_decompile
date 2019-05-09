// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import com.kongregate.o.j.a;
import com.kongregate.o.h.b;
import java.util.concurrent.ConcurrentHashMap;
import java.util.ArrayList;
import java.util.List;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.api.KongregateEventBundleListener;
import java.util.Iterator;
import android.os.Looper;
import java.util.LinkedList;
import com.kongregate.android.api.KongregateEventBundle;
import java.util.Collection;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicReference;
import com.kongregate.android.api.MicrotransactionServices;
import com.kongregate.android.api.AnalyticsServices;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.api.StatServices;
import com.kongregate.android.api.KongregateServices;
import android.content.Context;
import com.kongregate.android.api.KongregateAPI;

abstract class c implements KongregateAPI
{
    protected final long a;
    protected final String b;
    protected final Context c;
    protected final KongregateServices d;
    protected final StatServices e;
    protected final MobileServices f;
    protected final AnalyticsServices g;
    protected final MicrotransactionServices h;
    protected final AtomicReference<c> i;
    protected final Handler j;
    private final Collection<d> k;
    private final Collection<KongregateEventBundle> l;
    
    public c(final Context context, final long a, final String b) {
        this.i = new AtomicReference<c>(new c());
        this.k = new LinkedList<d>();
        this.l = new LinkedList<KongregateEventBundle>();
        this.a = a;
        this.b = b;
        this.c = context.getApplicationContext();
        this.j = new Handler(Looper.getMainLooper());
        this.d = this.a();
        this.e = this.b();
        this.f = this.c();
        this.g = this.d();
        this.h = this.e();
    }
    
    protected KongregateServices a() {
        return new a();
    }
    
    protected void a(final KongregateEventBundle kongregateEventBundle) {
        this.j.post((Runnable)new Runnable() {
            @Override
            public void run() {
                synchronized (com.kongregate.android.internal.sdk.c.this.k) {
                    if (com.kongregate.android.internal.sdk.c.this.k.isEmpty()) {
                        com.kongregate.android.internal.sdk.c.this.l.add(kongregateEventBundle);
                        return;
                    }
                    final Iterator<d> iterator = com.kongregate.android.internal.sdk.c.this.k.iterator();
                    while (iterator.hasNext()) {
                        iterator.next().a(kongregateEventBundle);
                    }
                }
            }
            // monitorexit(collection)
        });
    }
    
    protected void a(final d d) {
        if (d == null) {
            return;
        }
        Label_0086: {
            synchronized (this.k) {
                final boolean empty = this.k.isEmpty();
                this.k.add(d);
                if (!empty) {
                    break Label_0086;
                }
                final Iterator<KongregateEventBundle> iterator = this.l.iterator();
                while (iterator.hasNext()) {
                    this.a(iterator.next());
                }
            }
            this.l.clear();
        }
    }
    // monitorexit(collection)
    
    protected void a(final String s) {
        this.a(s, null);
    }
    
    protected void a(final String s, final String s2) {
        this.a(new KongregateEventBundle(s, s2));
    }
    
    protected boolean a(final c c) {
        if (c == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        return this.i.getAndSet(c).e() != c.e();
    }
    
    @Override
    public void addEventBundleListener(final KongregateEventBundleListener kongregateEventBundleListener) {
        this.a(new d(kongregateEventBundleListener));
    }
    
    @Override
    public void addEventListener(final KongregateEventListener kongregateEventListener) {
        this.a(new d(kongregateEventListener));
    }
    
    @Override
    public AnalyticsServices analytics() {
        return this.g;
    }
    
    protected StatServices b() {
        return new c();
    }
    
    protected void b(final d d) {
        synchronized (this.k) {
            this.k.remove(d);
        }
    }
    
    protected MobileServices c() {
        return null;
    }
    
    protected AnalyticsServices d() {
        return null;
    }
    
    protected MicrotransactionServices e() {
        return new b();
    }
    
    @Override
    public String getApiKey() {
        return this.b;
    }
    
    @Override
    public Context getApplicationContext() {
        return this.c.getApplicationContext();
    }
    
    @Override
    public long getApplicationId() {
        return this.a;
    }
    
    @Override
    public boolean isReady() {
        return false;
    }
    
    @Override
    public MobileServices mobile() {
        return this.f;
    }
    
    @Override
    public MicrotransactionServices mtx() {
        return this.h;
    }
    
    @Override
    public List<KongregateEventBundle> pollEventBundles() {
        synchronized (this.k) {
            final ArrayList<KongregateEventBundle> list = new ArrayList<KongregateEventBundle>(this.l);
            this.l.clear();
            return list;
        }
    }
    
    @Override
    public List<String> pollEvents() {
        synchronized (this.k) {
            final ArrayList<String> list = new ArrayList<String>(this.l.size());
            final Iterator<KongregateEventBundle> iterator = this.l.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().getName());
            }
        }
        this.l.clear();
        // monitorexit(collection)
        return;
    }
    
    @Override
    public void removeEventBundleListener(final KongregateEventBundleListener kongregateEventBundleListener) {
        this.b(new d(kongregateEventBundleListener));
    }
    
    @Override
    public void removeEventListener(final KongregateEventListener kongregateEventListener) {
        this.b(new d(kongregateEventListener));
    }
    
    @Override
    public KongregateServices services() {
        return this.d;
    }
    
    @Override
    public StatServices stats() {
        return this.e;
    }
    
    protected class a implements KongregateServices
    {
        public a() {
        }
        
        @Override
        public String getGameAuthToken() {
            return null;
        }
        
        @Override
        public int getNotificationCount() {
            return 0;
        }
        
        @Override
        public long getUserId() {
            return com.kongregate.android.internal.sdk.c.this.i.get().e();
        }
        
        @Override
        public String getUsername() {
            return com.kongregate.android.internal.sdk.c.this.i.get().c();
        }
        
        @Override
        public boolean hasKongPlus() {
            return false;
        }
        
        @Override
        public boolean hasUnreadGuildMessages() {
            return false;
        }
        
        @Override
        public boolean isConnected() {
            return false;
        }
        
        @Override
        public boolean isGuest() {
            return com.kongregate.android.internal.sdk.c.this.i.get().f();
        }
        
        @Override
        public void setCharacterToken(final String s) {
        }
    }
    
    protected static class b implements MicrotransactionServices
    {
        protected final ConcurrentHashMap<Long, com.kongregate.o.h.b> a;
        
        protected b() {
            this.a = new ConcurrentHashMap<Long, com.kongregate.o.h.b>();
        }
        
        @Override
        public boolean hasItem(final String s) {
            final long g = com.kongregate.o.j.a.a().g();
            com.kongregate.o.h.b a;
            if (g == 0L) {
                a = com.kongregate.o.h.b.a;
            }
            else {
                a = this.a.get(g);
            }
            if (a != null) {
                final Iterator<com.kongregate.o.h.c> iterator = a.a().iterator();
                while (iterator.hasNext()) {
                    if (iterator.next().b.equals(s)) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        @Override
        public ReceiptVerificationStatus receiptVerificationStatus(final String s) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void requestUserItemList() {
        }
        
        @Override
        public void verifyTransaction(final String s, final String s2) {
            throw new IllegalStateException("not implemented");
        }
        
        @Override
        public void verifyTransaction(final String s, final String s2, final ReceiptVerificationListener receiptVerificationListener) {
            throw new IllegalStateException("not implemented");
        }
    }
    
    protected class c implements StatServices
    {
        @Override
        public void submit(final String s, final long n) {
        }
    }
    
    private static class d
    {
        KongregateEventBundleListener a;
        KongregateEventListener b;
        
        private d(final KongregateEventBundleListener a) {
            this.a = null;
            this.b = null;
            this.a = a;
        }
        
        private d(final KongregateEventListener b) {
            this.a = null;
            this.b = null;
            this.b = b;
        }
        
        private void a(final KongregateEventBundle kongregateEventBundle) {
            if (this.a != null) {
                this.a.onKongregateEventBundle(kongregateEventBundle.getName(), kongregateEventBundle.getJSONBundle());
            }
            else if (this.b != null) {
                this.b.onEvent(kongregateEventBundle.getName());
            }
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (o == null || this.getClass() != o.getClass()) {
                    return false;
                }
                final d d = (d)o;
                Label_0059: {
                    if (this.a != null) {
                        if (this.a.equals(d.a)) {
                            break Label_0059;
                        }
                    }
                    else if (d.a == null) {
                        break Label_0059;
                    }
                    return false;
                }
                if (this.b != null) {
                    if (this.b.equals(d.b)) {
                        return true;
                    }
                }
                else if (d.b == null) {
                    return true;
                }
                return false;
            }
            return true;
        }
        
        @Override
        public int hashCode() {
            int hashCode = 0;
            int hashCode2;
            if (this.a != null) {
                hashCode2 = this.a.hashCode();
            }
            else {
                hashCode2 = 0;
            }
            if (this.b != null) {
                hashCode = this.b.hashCode();
            }
            return hashCode2 * 31 + hashCode;
        }
    }
}
