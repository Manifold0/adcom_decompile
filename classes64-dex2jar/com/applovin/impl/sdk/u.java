// 
// Decompiled by Procyon v0.5.34
// 

package com.applovin.impl.sdk;

import java.util.Iterator;
import com.applovin.sdk.AppLovinAdUpdateListener;
import java.util.Collection;
import java.util.HashSet;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;

class u implements AppLovinAdLoadListener
{
    final /* synthetic */ AppLovinAdServiceImpl a;
    private final v b;
    
    private u(final AppLovinAdServiceImpl a, final v b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void adReceived(AppLovinAd iterator) {
        Object o = ((q)iterator).t();
        AppLovinAd b = iterator;
        if (!(iterator instanceof aq)) {
            b = iterator;
            if (((n)o).l()) {
                this.a.a.c().adReceived(iterator);
                b = new aq((n)o, this.a.a);
            }
        }
        while (true) {
            Object a = this.b.a;
            while (true) {
                synchronized (a) {
                    if (((n)o).i()) {
                        final long j = ((n)o).j();
                        if (j > 0L) {
                            this.b.c = j * 1000L + System.currentTimeMillis();
                        }
                        else if (j == 0L) {
                            this.b.c = Long.MAX_VALUE;
                        }
                        this.b.b = b;
                        final HashSet set = new HashSet(this.b.f);
                        this.b.f.clear();
                        final HashSet set2 = new HashSet(this.b.e);
                        this.b.d = false;
                        // monitorexit(a)
                        this.a.b((n)o);
                        o = set.iterator();
                        while (((Iterator)o).hasNext()) {
                            a = ((Iterator<AppLovinAdLoadListener>)o).next();
                            try {
                                ((AppLovinAdLoadListener)a).adReceived(b);
                            }
                            catch (Throwable a) {
                                this.a.b.e("AppLovinAdService", "Unable to notify listener about a newly loaded ad", (Throwable)a);
                            }
                        }
                        break;
                    }
                }
                this.b.b = null;
                this.b.c = 0L;
                continue;
            }
        }
        final Collection collection;
        iterator = (AppLovinAd)collection.iterator();
        while (((Iterator)iterator).hasNext()) {
            final AppLovinAdUpdateListener appLovinAdUpdateListener = ((Iterator<AppLovinAdUpdateListener>)iterator).next();
            try {
                appLovinAdUpdateListener.adUpdated(b);
            }
            catch (Throwable t) {
                this.a.b.e("AppLovinAdService", "Unable to notify listener about an updated loaded ad", t);
            }
        }
    }
    
    @Override
    public void failedToReceiveAd(final int n) {
        Object o = this.b.a;
        synchronized (o) {
            final HashSet<AppLovinAdLoadListener> set = new HashSet<AppLovinAdLoadListener>(this.b.f);
            this.b.f.clear();
            this.b.d = false;
            // monitorexit(o)
            o = set.iterator();
            while (((Iterator)o).hasNext()) {
                final AppLovinAdLoadListener appLovinAdLoadListener = ((Iterator<AppLovinAdLoadListener>)o).next();
                try {
                    appLovinAdLoadListener.failedToReceiveAd(n);
                }
                catch (Throwable t) {
                    this.a.b.e("AppLovinAdService", "Unable to notify listener about ad load failure", t);
                }
            }
        }
    }
}
