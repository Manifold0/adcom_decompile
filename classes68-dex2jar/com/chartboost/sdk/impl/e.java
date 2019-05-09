// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.g;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;
import com.chartboost.sdk.Model.CBError;
import java.util.Iterator;
import com.chartboost.sdk.Model.b;
import com.chartboost.sdk.Libraries.CBLogging;
import java.io.File;
import java.util.TreeSet;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.d;
import com.chartboost.sdk.Tracking.a;
import android.content.SharedPreferences;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.ScheduledFuture;
import java.util.SortedSet;
import java.util.Map;
import com.chartboost.sdk.c;
import android.os.Handler;
import com.chartboost.sdk.Libraries.i;
import com.chartboost.sdk.Libraries.f;
import java.util.concurrent.ScheduledExecutorService;

public class e
{
    private final long A;
    private final String[] B;
    final ScheduledExecutorService a;
    public final f b;
    final i c;
    final Handler d;
    final c e;
    final com.chartboost.sdk.impl.c f;
    int g;
    final Map<String, com.chartboost.sdk.impl.f> h;
    final SortedSet<com.chartboost.sdk.impl.f> i;
    final SortedSet<com.chartboost.sdk.impl.f> j;
    ScheduledFuture<?> k;
    private final l l;
    private final ah m;
    private final ai n;
    private final ap o;
    private final AtomicReference<com.chartboost.sdk.Model.e> p;
    private final SharedPreferences q;
    private final com.chartboost.sdk.Tracking.a r;
    private final ak s;
    private final d t;
    private final al u;
    private int v;
    private boolean w;
    private final Map<String, Long> x;
    private final Map<String, Integer> y;
    private final long z;
    
    public e(final com.chartboost.sdk.impl.c f, final ScheduledExecutorService a, final l l, final f b, final ah m, final ai n, final ap o, final AtomicReference<com.chartboost.sdk.Model.e> p16, final SharedPreferences q, final i c, final com.chartboost.sdk.Tracking.a r, final Handler d, final c e, final ak s, final d t, final al u) {
        this.g = 0;
        this.z = TimeUnit.SECONDS.toNanos(5L);
        this.A = TimeUnit.SECONDS.toNanos(1L);
        this.B = new String[] { "ASKED_TO_CACHE", "ASKED_TO_SHOW", "REQUESTING_TO_CACHE", "REQUESTING_TO_SHOW", "DOWNLOADING_TO_CACHE", "DOWNLOADING_TO_SHOW", "READY", "ASKING_UI_TO_SHOW_AD", "DONE" };
        this.a = a;
        this.l = l;
        this.b = b;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p16;
        this.q = q;
        this.c = c;
        this.r = r;
        this.d = d;
        this.e = e;
        this.s = s;
        this.t = t;
        this.u = u;
        this.f = f;
        this.v = 1;
        this.h = new HashMap<String, com.chartboost.sdk.impl.f>();
        this.j = new TreeSet<com.chartboost.sdk.impl.f>();
        this.i = new TreeSet<com.chartboost.sdk.impl.f>();
        this.x = new HashMap<String, Long>();
        this.y = new HashMap<String, Integer>();
        this.w = false;
    }
    
    private com.chartboost.sdk.Model.c a(final com.chartboost.sdk.impl.f f, final String s) {
        return new com.chartboost.sdk.Model.c(f.d, new com.chartboost.sdk.impl.d(this, f), this.b, this.m, this.o, this.q, this.r, this.d, this.e, this.s, this.t, this.u, this.f, f.b, s);
    }
    
    private String a(final com.chartboost.sdk.Model.a a, File a2) {
        if (a.r == null) {
            CBLogging.b("AdUnitManager", "AdUnit does not have a template body");
            return null;
        }
        a2 = a.r.a(a2);
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        hashMap.putAll(a.d);
        hashMap.put("{% certification_providers %}", com.chartboost.sdk.impl.o.a(a.s));
        for (final Map.Entry<String, b> entry : a.c.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().b);
        }
        try {
            return com.chartboost.sdk.impl.n.a(a2, (Map<String, String>)hashMap);
        }
        catch (Exception ex) {
            a.a(this.getClass(), "loadTemplateHtml", ex);
            return null;
        }
    }
    
    private void a(final com.chartboost.sdk.impl.f f, final int n, final String s) {
        boolean b;
        com.chartboost.sdk.Model.e e;
        boolean b2;
        boolean b3;
        aj.a a = null;
        aj aj;
        Label_0029_Outer:Label_0045_Outer:Label_0075_Outer:
        while (true) {
            b = true;
            while (true) {
            Label_0439:
                while (true) {
                Label_0433:
                    while (true) {
                        Label_0427: {
                            while (true) {
                                Label_0343: {
                                    try {
                                        e = this.p.get();
                                        if (this.f.a != 2) {
                                            break Label_0427;
                                        }
                                        b2 = true;
                                        if (!e.y || b2) {
                                            break Label_0433;
                                        }
                                        b3 = true;
                                        a = new aj.a() {
                                            final /* synthetic */ long b = com.chartboost.sdk.impl.e.this.c.b();
                                            
                                            @Override
                                            public void a(final aj aj, final CBError cbError) {
                                                com.chartboost.sdk.impl.e.this.a(f, cbError);
                                            }
                                            
                                            @Override
                                            public void a(final aj aj, final JSONObject jsonObject) {
                                                while (true) {
                                                    while (true) {
                                                        Label_0159: {
                                                            try {
                                                                f.p = (int)TimeUnit.NANOSECONDS.toMillis(com.chartboost.sdk.impl.e.this.c.b() - this.b);
                                                                f.q = (int)TimeUnit.NANOSECONDS.toMillis(aj.h);
                                                                f.r = (int)TimeUnit.NANOSECONDS.toMillis(aj.i);
                                                                com.chartboost.sdk.Model.a a;
                                                                if (b2) {
                                                                    a = new com.chartboost.sdk.Model.a(0, jsonObject, true);
                                                                }
                                                                else {
                                                                    if (!b3) {
                                                                        break Label_0159;
                                                                    }
                                                                    a = new com.chartboost.sdk.Model.a(1, jsonObject, false);
                                                                }
                                                                com.chartboost.sdk.impl.e.this.a(f, a);
                                                                return;
                                                            }
                                                            catch (JSONException ex) {
                                                                com.chartboost.sdk.Tracking.a.a(e.class, "sendAdGetRequest.onSuccess", (Exception)ex);
                                                                com.chartboost.sdk.impl.e.this.a(f, new CBError(CBError.a.d, "Response conversion failure"));
                                                                return;
                                                            }
                                                        }
                                                        com.chartboost.sdk.Model.a a = new com.chartboost.sdk.Model.a(0, jsonObject, false);
                                                        continue Label_0029_Outer;
                                                    }
                                                }
                                            }
                                        };
                                        if (f.c == 2) {
                                            if (b2) {
                                                aj = new aj(this.f.d, this.o, this.r, n, (aj.a)a);
                                                aj.l = true;
                                                aj.a("location", f.b);
                                                aj.a("cache", b);
                                                aj.a("raw", true);
                                                f.e = 0;
                                            }
                                            else {
                                                if (!b3) {
                                                    break Label_0343;
                                                }
                                                aj = new am(String.format(this.f.e, e.F), this.o, this.r, n, a);
                                                ((am)aj).a("cache_assets", this.b.c(), 0);
                                                ((am)aj).a("location", f.b, 0);
                                                ((am)aj).a("cache", b, 0);
                                                ((am)aj).l = true;
                                                f.e = 1;
                                            }
                                            aj.j = 1;
                                            this.g = 2;
                                            this.m.a((ad<Object>)aj);
                                            this.r.a(this.f.a(f.e), s, f.b);
                                            return;
                                        }
                                        break Label_0439;
                                    }
                                    catch (Exception ex) {
                                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "sendAdGetRequest", ex);
                                        this.a(f, new CBError(CBError.a.a, "error sending ad-get request"));
                                        return;
                                    }
                                }
                                aj = new aj(this.f.d, this.o, this.r, n, (aj.a)a);
                                aj.a("local-videos", this.b.b());
                                aj.l = true;
                                aj.a("location", f.b);
                                aj.a("cache", b);
                                f.e = 0;
                                continue;
                            }
                        }
                        b2 = false;
                        continue Label_0045_Outer;
                    }
                    b3 = false;
                    continue Label_0075_Outer;
                }
                b = false;
                continue;
            }
        }
    }
    
    private boolean a(final com.chartboost.sdk.Model.a a) {
        final File a2 = this.b.d().a;
        for (final b b : a.c.values()) {
            if (!b.a(a2).exists()) {
                CBLogging.b("AdUnitManager", "Asset does not exist: " + b.b);
                return false;
            }
        }
        return true;
    }
    
    private boolean a(final SortedSet<com.chartboost.sdk.impl.f> set, final int n, final int c, final int n2, final String s) {
        final Iterator<com.chartboost.sdk.impl.f> iterator = (Iterator<com.chartboost.sdk.impl.f>)set.iterator();
        while (iterator.hasNext()) {
            final com.chartboost.sdk.impl.f f = iterator.next();
            if (f.c != n || f.d != null) {
                iterator.remove();
            }
            else {
                if (this.e(f.b)) {
                    continue;
                }
                if (this.f.g(f.b)) {
                    f.c = c;
                    iterator.remove();
                    this.a(f, n2, s);
                    return true;
                }
                f.c = 8;
                this.h.remove(f.b);
                iterator.remove();
            }
        }
        return false;
    }
    
    private void b(final com.chartboost.sdk.impl.f f, final CBError.CBImpressionError cbImpressionError) {
        final Handler d = this.d;
        final com.chartboost.sdk.impl.c f2 = this.f;
        f2.getClass();
        d.post((Runnable)f2.new a(4, f.b, cbImpressionError));
        if (cbImpressionError == CBError.CBImpressionError.NO_AD_FOUND) {
            return;
        }
        String f3;
        if (f.d != null) {
            f3 = f.d.f;
        }
        else {
            f3 = null;
        }
        String s;
        if (f.c == 0 || f.c == 2 || f.c == 4) {
            s = "cache";
        }
        else {
            s = "show";
        }
        int n;
        if (f.d != null) {
            n = f.d.b;
        }
        else {
            n = f.e;
        }
        final Integer value = n;
        String s2;
        if (value != null) {
            if (value == 0) {
                s2 = "native";
            }
            else {
                s2 = "web";
            }
        }
        else {
            s2 = null;
        }
        String string;
        if (f.c >= 0 && f.c < this.B.length) {
            string = this.B[f.c];
        }
        else {
            string = "Unknown state: " + f.c;
        }
        this.r.a(this.f.b, s, s2, cbImpressionError.toString(), f3, f.b, string);
    }
    
    private void b(final com.chartboost.sdk.impl.f f, final String s) {
        if (!this.p.get().p) {
            return;
        }
        String f2;
        if (f.d != null) {
            f2 = f.d.f;
        }
        else {
            f2 = null;
        }
        String s2;
        if (f.c == 0 || f.c == 2 || f.c == 4) {
            s2 = "cache";
        }
        else {
            s2 = "show";
        }
        Integer n;
        if (f.d != null) {
            n = f.d.b;
        }
        else {
            n = f.e;
        }
        String s3;
        if (n != null) {
            if (n == 0) {
                s3 = "native";
            }
            else {
                s3 = "web";
            }
        }
        else {
            s3 = null;
        }
        String string;
        if (f.c >= 0 && f.c < this.B.length) {
            string = this.B[f.c];
        }
        else {
            string = "Unknown state: " + f.c;
        }
        this.r.a(s, this.f.b, s2, s3, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("adGetRequestSubmitToCallbackMs", f.p), com.chartboost.sdk.Libraries.e.a("downloadRequestToCompletionMs", f.n), com.chartboost.sdk.Libraries.e.a("downloadAccumulatedProcessingMs", f.o), com.chartboost.sdk.Libraries.e.a("adGetRequestGetResponseCodeMs", f.q), com.chartboost.sdk.Libraries.e.a("adGetRequestReadDataMs", f.r), com.chartboost.sdk.Libraries.e.a("cacheRequestToReadyMs", f.k), com.chartboost.sdk.Libraries.e.a("showRequestToReadyMs", f.l), com.chartboost.sdk.Libraries.e.a("showRequestToShownMs", f.m), com.chartboost.sdk.Libraries.e.a("adId", f2), com.chartboost.sdk.Libraries.e.a("location", f.b), com.chartboost.sdk.Libraries.e.a("state", string)), false);
    }
    
    private void c() {
        Long value;
        if (this.g == 1) {
            final long b = this.c.b();
            final Iterator<Map.Entry<String, Long>> iterator = this.x.entrySet().iterator();
            value = null;
            while (iterator.hasNext()) {
                final Map.Entry<String, Long> entry = iterator.next();
                if (this.h.get(entry.getKey()) != null) {
                    final long max = Math.max(this.z, entry.getValue() - b);
                    if (value != null && max >= value) {
                        continue;
                    }
                    value = max;
                }
            }
        }
        else {
            value = null;
        }
        Label_0198: {
            if (value == null || this.k == null) {
                break Label_0198;
            }
            int n;
            if (Math.abs(value - this.k.getDelay(TimeUnit.NANOSECONDS)) <= TimeUnit.SECONDS.toNanos(5L)) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                break Label_0198;
            }
            return;
        }
        if (this.k != null) {
            this.k.cancel(false);
            this.k = null;
        }
        if (value != null) {
            this.k = this.a.schedule(new a(2, null, null, null), value, TimeUnit.NANOSECONDS);
        }
    }
    
    private void c(final com.chartboost.sdk.impl.f f) {
        if (f.d != null && (f.c == 5 || f.c == 4)) {
            int g;
            if (f.c == 5) {
                g = 1;
            }
            else {
                g = 2;
            }
            if (f.g > g) {
                final h h = new h() {
                    @Override
                    public void a(final boolean b, final int n, final int n2) {
                        com.chartboost.sdk.impl.e.this.a(f, b, n, n2);
                    }
                };
                f.g = g;
                this.l.a(g, f.d.c, new AtomicInteger(), com.chartboost.sdk.g.a().a(h));
            }
        }
    }
    
    private void d() {
        final long b = this.c.b();
        final Iterator<Long> iterator = this.x.values().iterator();
        while (iterator.hasNext()) {
            if (b - iterator.next() >= 0L) {
                iterator.remove();
            }
        }
    }
    
    private void d(final com.chartboost.sdk.impl.f f) {
        final int c = f.c;
        final long b = this.c.b();
        if (f.h != null) {
            f.k = (int)TimeUnit.NANOSECONDS.toMillis(b - f.h);
        }
        if (f.i != null) {
            f.l = (int)TimeUnit.NANOSECONDS.toMillis(b - f.i);
        }
        this.b(f, "ad-unit-cached");
        f.c = 6;
        if (f.f) {
            final Handler d = this.d;
            final com.chartboost.sdk.impl.c f2 = this.f;
            f2.getClass();
            d.post((Runnable)f2.new a(0, f.b, null));
        }
        if (c == 5) {
            this.h(f);
        }
    }
    
    private void e(final com.chartboost.sdk.impl.f f) {
        this.b(f, CBError.CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        this.f(f);
        this.g(f);
    }
    
    private boolean e() {
        boolean b = true;
        if (this.f.a == 0 && !com.chartboost.sdk.i.u) {
            if (this.q.getInt("cbPrefSessionCount", 0) != 1) {
                b = false;
            }
            return b;
        }
        return false;
    }
    
    private boolean e(final String s) {
        return this.x.containsKey(s);
    }
    
    private void f(final com.chartboost.sdk.impl.f f) {
        this.h.remove(f.b);
        f.c = 8;
        f.d = null;
    }
    
    private void g(final com.chartboost.sdk.impl.f f) {
        final com.chartboost.sdk.Model.e e = this.p.get();
        final long s = e.s;
        final int t = e.t;
        Integer value;
        if ((value = this.y.get(f.b)) == null) {
            value = 0;
        }
        final Integer value2 = Math.min(value, t);
        this.y.put(f.b, value2 + 1);
        this.x.put(f.b, TimeUnit.MILLISECONDS.toNanos(s << (int)value2) + this.c.b());
    }
    
    private void h(final com.chartboost.sdk.impl.f f) {
        if (!this.n.b()) {
            final Handler d = this.d;
            final com.chartboost.sdk.impl.c f2 = this.f;
            f2.getClass();
            d.post((Runnable)f2.new a(4, f.b, CBError.CBImpressionError.INTERNET_UNAVAILABLE_AT_SHOW));
            return;
        }
        com.chartboost.sdk.Model.a d2;
        File a;
        CBError.CBImpressionError a2;
        CBError.CBImpressionError cbImpressionError;
        Enum<CBError.CBImpressionError> enum1 = null;
        Iterator<b> iterator;
        b b;
        String a3;
        com.chartboost.sdk.Model.c a4;
        c e;
        c.c c;
        Label_0148_Outer:Label_0216_Outer:
        while (true) {
            while (true) {
            Label_0379:
                while (true) {
                    Label_0376:Label_0257_Outer:
                    while (true) {
                        Label_0371: {
                        Label_0257:
                            while (true) {
                                Label_0363: {
                                    while (true) {
                                        Label_0358: {
                                            Label_0355: {
                                                Label_0350: {
                                                    try {
                                                        d2 = f.d;
                                                        a = this.b.d().a;
                                                        if (d2.b != 0 || (!this.f.g && !d2.p.equals("video"))) {
                                                            break Label_0371;
                                                        }
                                                        a2 = this.a(d2.a);
                                                        if ((cbImpressionError = a2) != null) {
                                                            CBLogging.b("AdUnitManager", "Video media unavailable for the impression");
                                                            cbImpressionError = a2;
                                                        }
                                                        if ((enum1 = cbImpressionError) == null) {
                                                            iterator = d2.c.values().iterator();
                                                            enum1 = cbImpressionError;
                                                            if (!iterator.hasNext()) {
                                                                break Label_0379;
                                                            }
                                                            b = iterator.next();
                                                            if (!b.a(a).exists()) {
                                                                CBLogging.b("AdUnitManager", "Asset does not exist: " + b.b);
                                                                enum1 = CBError.CBImpressionError.ASSET_MISSING;
                                                                break Label_0376;
                                                            }
                                                            break Label_0257;
                                                        }
                                                        else {
                                                            if (enum1 != null) {
                                                                break Label_0363;
                                                            }
                                                            if (d2.b != 1) {
                                                                break Label_0358;
                                                            }
                                                            a3 = this.a(d2, a);
                                                            if (a3 != null) {
                                                                break Label_0355;
                                                            }
                                                            enum1 = CBError.CBImpressionError.ERROR_LOADING_WEB_VIEW;
                                                            if (enum1 != null) {
                                                                break Label_0350;
                                                            }
                                                            a4 = this.a(f, a3);
                                                            if (enum1 == null) {
                                                                f.c = 7;
                                                                e = this.e;
                                                                e.getClass();
                                                                c = e.new c(10);
                                                                c.d = a4;
                                                                f.j = this.c.b();
                                                                this.d.post((Runnable)c);
                                                                return;
                                                            }
                                                        }
                                                    }
                                                    catch (Exception ex) {
                                                        com.chartboost.sdk.Tracking.a.a(this.getClass(), "showReady", ex);
                                                        enum1 = CBError.CBImpressionError.INTERNAL;
                                                        a4 = null;
                                                        continue Label_0257;
                                                    }
                                                    break;
                                                }
                                                a4 = null;
                                                continue Label_0257;
                                            }
                                            continue Label_0257_Outer;
                                        }
                                        a3 = null;
                                        continue Label_0257_Outer;
                                    }
                                }
                                a4 = null;
                                continue Label_0257;
                            }
                            break Label_0376;
                        }
                        cbImpressionError = null;
                        continue Label_0148_Outer;
                    }
                    continue Label_0216_Outer;
                }
                continue;
            }
        }
        this.b(f, (CBError.CBImpressionError)enum1);
        this.f(f);
    }
    
    private void i(final com.chartboost.sdk.impl.f f) {
        final aj aj = new aj(this.f.f, this.o, this.r, 2, (aj.a)new com.chartboost.sdk.impl.g(this, f.b));
        aj.j = 1;
        aj.a("cached", "0");
        final String f2 = f.d.f;
        if (!f2.isEmpty()) {
            aj.a("ad_id", f2);
        }
        aj.a("location", f.b);
        this.m.a((ad<Object>)aj);
        this.r.b(this.f.a(f.d.b), f.b, f2);
    }
    
    CBError.CBImpressionError a(JSONObject optJSONObject) {
        if (optJSONObject == null) {
            return CBError.CBImpressionError.INVALID_RESPONSE;
        }
        final JSONObject optJSONObject2 = optJSONObject.optJSONObject("assets");
        if (optJSONObject2 == null) {
            return CBError.CBImpressionError.INVALID_RESPONSE;
        }
        String s;
        if (CBUtility.a(CBUtility.a())) {
            s = "video-portrait";
        }
        else {
            s = "video-landscape";
        }
        optJSONObject = optJSONObject2.optJSONObject(s);
        if (optJSONObject == null) {
            return CBError.CBImpressionError.VIDEO_UNAVAILABLE_FOR_CURRENT_ORIENTATION;
        }
        final String optString = optJSONObject.optString("id");
        if (optString.isEmpty()) {
            return CBError.CBImpressionError.VIDEO_ID_MISSING;
        }
        if (new File(this.b.d().g, optString).exists()) {
            return null;
        }
        return CBError.CBImpressionError.VIDEO_UNAVAILABLE;
    }
    
    public com.chartboost.sdk.Model.a a(final String s) {
        synchronized (this) {
            final com.chartboost.sdk.impl.f f = this.h.get(s);
            com.chartboost.sdk.Model.a d;
            if (f != null && (f.c == 6 || f.c == 7)) {
                d = f.d;
            }
            else {
                d = null;
            }
            return d;
        }
    }
    
    void a() {
        if (this.g == 0) {
            this.g = 1;
            this.b();
        }
    }
    
    void a(final com.chartboost.sdk.impl.f f) {
        if (f.c == 7) {
            f.c = 6;
            f.j = null;
            f.i = null;
            f.m = null;
        }
    }
    
    void a(final com.chartboost.sdk.impl.f f, final CBError.CBImpressionError cbImpressionError) {
        this.b(f, cbImpressionError);
        if (f.c == 7) {
            if (cbImpressionError != CBError.CBImpressionError.IMPRESSION_ALREADY_VISIBLE) {
                this.g(f);
                this.f(f);
                this.b();
                return;
            }
            f.c = 6;
            f.j = null;
            f.i = null;
            f.m = null;
        }
    }
    
    void a(final com.chartboost.sdk.impl.f f, final CBError cbError) {
        synchronized (this) {
            if (this.g != 0) {
                this.g = 1;
                this.b(f, cbError.c());
                this.f(f);
                this.g(f);
                this.b();
            }
        }
    }
    
    void a(final com.chartboost.sdk.impl.f f, final com.chartboost.sdk.Model.a d) {
        synchronized (this) {
            this.g = 1;
            int c;
            if (f.c == 2) {
                c = 4;
            }
            else {
                c = 5;
            }
            f.c = c;
            f.d = d;
            this.c(f);
            this.b();
        }
    }
    
    void a(final com.chartboost.sdk.impl.f f, final boolean b, final int n, final int n2) {
        synchronized (this) {
            if (f.c == 4 || f.c == 5) {
                f.n = n;
                f.o = n2;
                if (b) {
                    this.d(f);
                }
                else {
                    this.e(f);
                }
            }
            this.b();
        }
    }
    
    void b() {
        if (this.w) {
            return;
        }
        try {
            this.w = true;
            this.d();
            if (this.g == 1 && !this.a(this.j, 1, 3, 1, "show")) {
                this.a(this.i, 0, 2, 2, "cache");
            }
            this.c();
        }
        finally {
            this.w = false;
        }
    }
    
    void b(final com.chartboost.sdk.impl.f f) {
        if (f.c == 7) {
            if (f.i != null && f.m == null) {
                f.m = (int)TimeUnit.NANOSECONDS.toMillis(this.c.b() - f.i);
            }
            this.b(f, "ad-unit-shown");
            this.y.remove(f.b);
            final Handler d = this.d;
            final com.chartboost.sdk.impl.c f2 = this.f;
            f2.getClass();
            d.post((Runnable)f2.new a(5, f.b, null));
            this.i(f);
            this.f(f);
            this.b();
        }
    }
    
    void b(final String s) {
        if (this.e()) {
            final com.chartboost.sdk.impl.c f = this.f;
            f.getClass();
            this.d.postDelayed((Runnable)f.new a(4, s, CBError.CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.A);
            return;
        }
        final com.chartboost.sdk.impl.f f2 = this.h.get(s);
        com.chartboost.sdk.impl.f f3;
        if ((f3 = f2) != null) {
            f3 = f2;
            if (f2.c == 6) {
                f3 = f2;
                if (!this.a(f2.d)) {
                    this.h.remove(s);
                    f3 = null;
                }
            }
        }
        com.chartboost.sdk.impl.f f4;
        if ((f4 = f3) == null) {
            f4 = new com.chartboost.sdk.impl.f(this.v++, s, 0);
            this.h.put(s, f4);
            this.i.add(f4);
        }
        f4.f = true;
        if (f4.h == null) {
            f4.h = this.c.b();
        }
        switch (f4.c) {
            case 6:
            case 7: {
                final Handler d = this.d;
                final com.chartboost.sdk.impl.c f5 = this.f;
                f5.getClass();
                d.post((Runnable)f5.new a(0, s, null));
                break;
            }
        }
        this.b();
    }
    
    void c(final String s) {
        if (this.e()) {
            final com.chartboost.sdk.impl.c f = this.f;
            f.getClass();
            this.d.postDelayed((Runnable)f.new a(4, s, CBError.CBImpressionError.FIRST_SESSION_INTERSTITIALS_DISABLED), this.A);
            return;
        }
        com.chartboost.sdk.impl.f f2;
        if ((f2 = this.h.get(s)) == null) {
            f2 = new com.chartboost.sdk.impl.f(this.v++, s, 1);
            this.h.put(s, f2);
            this.j.add(f2);
        }
        if (f2.i == null) {
            f2.i = this.c.b();
        }
        switch (f2.c) {
            case 0: {
                this.i.remove(f2);
                this.j.add(f2);
                f2.c = 1;
                break;
            }
            case 2: {
                f2.c = 3;
                break;
            }
            case 4: {
                f2.c = 5;
                this.c(f2);
                break;
            }
            case 6: {
                this.h(f2);
                break;
            }
        }
        this.b();
    }
    
    void d(final String s) {
        final com.chartboost.sdk.impl.f f = this.h.get(s);
        if (f != null && f.c == 6) {
            this.f(f);
            this.b();
        }
    }
    
    public class a implements Runnable
    {
        final int a;
        final String b;
        final com.chartboost.sdk.impl.f c;
        final CBError.CBImpressionError d;
        
        public a(final int a, final String b, final com.chartboost.sdk.impl.f c, final CBError.CBImpressionError d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }
        
        @Override
        public void run() {
            while (true) {
                Label_0182: {
                    Label_0168: {
                        Label_0150: {
                            Label_0136: {
                                Label_0122: {
                                    Label_0108: {
                                        try {
                                            synchronized (com.chartboost.sdk.impl.e.this) {
                                                switch (this.a) {
                                                    case 0: {
                                                        com.chartboost.sdk.impl.e.this.a();
                                                    }
                                                    case 1: {
                                                        return;
                                                    }
                                                    case 2: {
                                                        break;
                                                    }
                                                    case 3: {
                                                        break Label_0108;
                                                    }
                                                    case 4: {
                                                        break Label_0122;
                                                    }
                                                    case 5: {
                                                        break Label_0136;
                                                    }
                                                    case 6: {
                                                        break Label_0150;
                                                    }
                                                    case 7: {
                                                        break Label_0168;
                                                    }
                                                    case 8: {
                                                        break Label_0182;
                                                    }
                                                    default: {
                                                        return;
                                                    }
                                                }
                                            }
                                        }
                                        catch (Exception ex) {
                                            com.chartboost.sdk.Tracking.a.a(this.getClass(), "run", ex);
                                            return;
                                        }
                                        com.chartboost.sdk.impl.e.this.k = null;
                                        com.chartboost.sdk.impl.e.this.b();
                                        return;
                                    }
                                    com.chartboost.sdk.impl.e.this.b(this.b);
                                    return;
                                }
                                com.chartboost.sdk.impl.e.this.c(this.b);
                                return;
                            }
                            com.chartboost.sdk.impl.e.this.b(this.c);
                            return;
                        }
                        com.chartboost.sdk.impl.e.this.a(this.c, this.d);
                        return;
                    }
                    com.chartboost.sdk.impl.e.this.a(this.c);
                    return;
                }
                com.chartboost.sdk.impl.e.this.d(this.b);
            }
        }
    }
}
