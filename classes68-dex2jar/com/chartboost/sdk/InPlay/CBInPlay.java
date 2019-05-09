// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.InPlay;

import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.g;
import com.chartboost.sdk.impl.bh;
import com.chartboost.sdk.impl.aj;
import java.util.concurrent.Executor;
import android.os.Handler;
import com.chartboost.sdk.impl.c;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.impl.s;
import com.chartboost.sdk.b;
import com.chartboost.sdk.h;
import java.io.File;
import android.graphics.Bitmap;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.Tracking.a;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.e;

public final class CBInPlay
{
    private final e a;
    public final String appName;
    private final ah b;
    private final ap c;
    private final a d;
    private final ak e;
    private final com.chartboost.sdk.Model.a f;
    private Bitmap g;
    public final File largeAppIconFile;
    public final String largeAppIconUrl;
    public final String location;
    
    private CBInPlay(final e a, final ah b, final ap c, final a d, final ak e, final com.chartboost.sdk.Model.a f, final String largeAppIconUrl, final File largeAppIconFile, final String location) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.appName = f.q;
        this.largeAppIconUrl = largeAppIconUrl;
        this.largeAppIconFile = largeAppIconFile;
        this.location = location;
    }
    
    public static void cacheInPlay(final String s) {
        final h a = h.a();
        if (a == null || !b.a()) {
            return;
        }
        if (s.a().a(s)) {
            CBLogging.b("CBInPlay", "cacheInPlay location cannot be empty");
            final Handler p = a.p;
            final c e = a.e;
            e.getClass();
            p.post((Runnable)e.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
            return;
        }
        final com.chartboost.sdk.Model.e e2 = a.m.get();
        if ((e2.y && e2.z) || (e2.e && e2.f)) {
            final e d = a.d;
            d.getClass();
            a.a.execute(d.new a(3, s, null, null));
            return;
        }
        final Handler p2 = a.p;
        final c e3 = a.e;
        e3.getClass();
        p2.post((Runnable)e3.new a(4, s, CBError.CBImpressionError.END_POINT_DISABLED));
    }
    
    public static CBInPlay getInPlay(final String s) {
        final CBInPlay cbInPlay = null;
        final CBInPlay cbInPlay2 = null;
        final h a = h.a();
        CBInPlay cbInPlay3 = cbInPlay2;
        if (a != null) {
            if (!b.a()) {
                cbInPlay3 = cbInPlay2;
            }
            else {
                if (s.a().a(s)) {
                    CBLogging.b("CBInPlay", "Inplay location cannot be empty");
                    final Handler p = a.p;
                    final c e = a.e;
                    e.getClass();
                    p.post((Runnable)e.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
                    return null;
                }
                final com.chartboost.sdk.Model.e e2 = a.m.get();
                CBError.CBImpressionError cbImpressionError = CBError.CBImpressionError.NO_AD_FOUND;
                CBError.CBImpressionError cbImpressionError2 = null;
                CBInPlay cbInPlay4 = null;
                Label_0290: {
                    if (!e2.y || !e2.z) {
                        cbImpressionError2 = cbImpressionError;
                        cbInPlay4 = cbInPlay;
                        if (!e2.e) {
                            break Label_0290;
                        }
                        cbImpressionError2 = cbImpressionError;
                        cbInPlay4 = cbInPlay;
                        if (!e2.f) {
                            break Label_0290;
                        }
                    }
                    final com.chartboost.sdk.Model.a a2 = a.d.a(s);
                    if (a2 != null) {
                        final com.chartboost.sdk.Model.b b = a2.c.get("lg");
                        if (b != null) {
                            final File a3 = b.a(a.d.b.d().a);
                            if (!a3.exists()) {
                                cbImpressionError = CBError.CBImpressionError.ASSET_MISSING;
                                cbInPlay4 = null;
                            }
                            else {
                                cbInPlay4 = new CBInPlay(a.d, a.h, a.j, a.o, a.r, a2, b.c, a3, s);
                            }
                        }
                        else {
                            cbInPlay4 = null;
                        }
                        final Executor a4 = a.a;
                        final e d = a.d;
                        d.getClass();
                        a4.execute(d.new a(8, s, null, null));
                    }
                    else {
                        cbInPlay4 = null;
                    }
                    if (cbInPlay4 == null) {
                        final Executor a5 = a.a;
                        final e d2 = a.d;
                        d2.getClass();
                        a5.execute(d2.new a(3, s, null, null));
                    }
                    cbImpressionError2 = cbImpressionError;
                }
                if ((cbInPlay3 = cbInPlay4) == null) {
                    final Handler p2 = a.p;
                    final c e3 = a.e;
                    e3.getClass();
                    p2.post((Runnable)e3.new a(4, s, cbImpressionError2));
                    return cbInPlay4;
                }
            }
        }
        return cbInPlay3;
    }
    
    public static boolean hasInPlay(final String s) {
        final h a = h.a();
        if (a != null && b.a()) {
            if (s.a().a(s)) {
                CBLogging.b("CBInPlay", "hasInPlay location cannot be empty");
                final Handler p = a.p;
                final c e = a.e;
                e.getClass();
                p.post((Runnable)e.new a(4, s, CBError.CBImpressionError.INVALID_LOCATION));
                return false;
            }
            if (a.d.a(s) != null) {
                return true;
            }
        }
        return false;
    }
    
    public void click() {
        String j = this.f.j;
        final String i = this.f.i;
        if (!i.isEmpty() && this.e.a(i)) {
            j = i;
        }
        final aj aj = new aj("/api/click", this.c, this.d, 2, null);
        aj.a("location", this.location);
        aj.a("to", this.f.m);
        aj.a("cgn", this.f.g);
        aj.a("creative", this.f.h);
        aj.a("ad_id", this.f.f);
        aj.a("type", "native");
        if (j != null && !j.isEmpty()) {
            this.e.a(null, j, aj);
            return;
        }
        this.e.a(null, false, j, CBError.CBClickError.URI_INVALID, aj);
    }
    
    public Bitmap getAppIcon() throws Exception {
        if (this.g == null) {
            try {
                final byte[] b = bh.b(this.largeAppIconFile);
                if (b != null) {
                    this.g = s.a().a(b);
                }
                if (this.g == null) {
                    CBLogging.b("CBInPlay", "Error decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    if (!this.largeAppIconFile.delete()) {
                        CBLogging.b("CBInPlay", "Unable to delete corrupt inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    }
                    throw new Exception("decodeByteArrayToBitmap returned null");
                }
            }
            catch (OutOfMemoryError outOfMemoryError) {
                CBLogging.b("CBInPlay", "Out of memory decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                throw new Exception(outOfMemoryError);
            }
        }
        return this.g;
    }
    
    public String getAppName() {
        return this.appName;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public void show() {
        final aj aj = new aj("/inplay/show", this.c, this.d, 2, (aj.a)new g(this.a, this.location));
        aj.j = 1;
        aj.a("inplay-dictionary", this.f.a);
        aj.a("location", this.location);
        this.b.a((ad<Object>)aj);
    }
}
