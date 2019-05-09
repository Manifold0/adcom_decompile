package com.chartboost.sdk.InPlay;

import android.graphics.Bitmap;
import android.os.Handler;
import com.chartboost.sdk.C1392b;
import com.chartboost.sdk.C1409h;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.C1386a;
import com.chartboost.sdk.Model.C1387b;
import com.chartboost.sdk.Model.C1390e;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Tracking.C1391a;
import com.chartboost.sdk.impl.C1434c;
import com.chartboost.sdk.impl.C1434c.C1433a;
import com.chartboost.sdk.impl.C1440e;
import com.chartboost.sdk.impl.C1440e.C1439a;
import com.chartboost.sdk.impl.C1442g;
import com.chartboost.sdk.impl.C1454s;
import com.chartboost.sdk.impl.ad;
import com.chartboost.sdk.impl.ah;
import com.chartboost.sdk.impl.aj;
import com.chartboost.sdk.impl.ak;
import com.chartboost.sdk.impl.ap;
import com.chartboost.sdk.impl.bh;
import com.facebook.places.model.PlaceFields;
import java.io.File;
import java.util.concurrent.Executor;

public final class CBInPlay {
    /* renamed from: a */
    private final C1440e f2667a;
    public final String appName;
    /* renamed from: b */
    private final ah f2668b;
    /* renamed from: c */
    private final ap f2669c;
    /* renamed from: d */
    private final C1391a f2670d;
    /* renamed from: e */
    private final ak f2671e;
    /* renamed from: f */
    private final C1386a f2672f;
    /* renamed from: g */
    private Bitmap f2673g;
    public final File largeAppIconFile;
    public final String largeAppIconUrl;
    public final String location;

    private CBInPlay(C1440e adUnitManager, ah networkService, ap requestBodyFields, C1391a track, ak urlOpener, C1386a adUnit, String largeAppIconUrl, File largeAppIconFile, String location) {
        this.f2667a = adUnitManager;
        this.f2668b = networkService;
        this.f2669c = requestBodyFields;
        this.f2670d = track;
        this.f2671e = urlOpener;
        this.f2672f = adUnit;
        this.appName = adUnit.f2745q;
        this.largeAppIconUrl = largeAppIconUrl;
        this.largeAppIconFile = largeAppIconFile;
        this.location = location;
    }

    public void show() {
        ad ajVar = new aj("/inplay/show", this.f2669c, this.f2670d, 2, new C1442g(this.f2667a, this.location));
        ajVar.j = 1;
        ajVar.m3386a("inplay-dictionary", this.f2672f.f2729a);
        ajVar.m3386a(PlaceFields.LOCATION, this.location);
        this.f2668b.m3371a(ajVar);
    }

    public void click() {
        String str = this.f2672f.f2738j;
        String str2 = this.f2672f.f2737i;
        if (str2.isEmpty() || !this.f2671e.m3397a(str2)) {
            str2 = str;
        }
        aj ajVar = new aj("/api/click", this.f2669c, this.f2670d, 2, null);
        ajVar.m3386a(PlaceFields.LOCATION, this.location);
        ajVar.m3386a("to", this.f2672f.f2741m);
        ajVar.m3386a("cgn", this.f2672f.f2735g);
        ajVar.m3386a("creative", this.f2672f.f2736h);
        ajVar.m3386a("ad_id", this.f2672f.f2734f);
        ajVar.m3386a("type", (Object) "native");
        if (str2 == null || str2.isEmpty()) {
            this.f2671e.m3396a(null, false, str2, CBClickError.URI_INVALID, ajVar);
        } else {
            this.f2671e.m3395a(null, str2, ajVar);
        }
    }

    public String getLocation() {
        return this.location;
    }

    public Bitmap getAppIcon() throws Exception {
        if (this.f2673g == null) {
            try {
                byte[] b = bh.m3524b(this.largeAppIconFile);
                if (b != null) {
                    this.f2673g = C1454s.m3627a().m3628a(b);
                }
                if (this.f2673g == null) {
                    CBLogging.m3099b("CBInPlay", "Error decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    if (!this.largeAppIconFile.delete()) {
                        CBLogging.m3099b("CBInPlay", "Unable to delete corrupt inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                    }
                    throw new Exception("decodeByteArrayToBitmap returned null");
                }
            } catch (Throwable e) {
                CBLogging.m3099b("CBInPlay", "Out of memory decoding inplay bitmap " + this.largeAppIconFile.getAbsolutePath());
                throw new Exception(e);
            }
        }
        return this.f2673g;
    }

    public String getAppName() {
        return this.appName;
    }

    public static void cacheInPlay(String location) {
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a()) {
            Handler handler;
            C1434c c1434c;
            if (C1454s.m3627a().m3631a((CharSequence) location)) {
                CBLogging.m3099b("CBInPlay", "cacheInPlay location cannot be empty");
                handler = a.f2917p;
                c1434c = a.f2906e;
                c1434c.getClass();
                handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
                return;
            }
            C1390e c1390e = (C1390e) a.f2914m.get();
            if ((c1390e.f2817y && c1390e.f2818z) || (c1390e.f2797e && c1390e.f2798f)) {
                C1440e c1440e = a.f2905d;
                c1440e.getClass();
                a.f2902a.execute(new C1439a(c1440e, 3, location, null, null));
                return;
            }
            handler = a.f2917p;
            c1434c = a.f2906e;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.END_POINT_DISABLED));
        }
    }

    public static boolean hasInPlay(String location) {
        C1409h a = C1409h.m3324a();
        if (a == null || !C1392b.m3234a()) {
            return false;
        }
        if (C1454s.m3627a().m3631a((CharSequence) location)) {
            CBLogging.m3099b("CBInPlay", "hasInPlay location cannot be empty");
            Handler handler = a.f2917p;
            C1434c c1434c = a.f2906e;
            c1434c.getClass();
            handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            return false;
        } else if (a.f2905d.m3574a(location) != null) {
            return true;
        } else {
            return false;
        }
    }

    public static CBInPlay getInPlay(String location) {
        CBInPlay cBInPlay = null;
        C1409h a = C1409h.m3324a();
        if (a != null && C1392b.m3234a()) {
            Handler handler;
            C1434c c1434c;
            if (C1454s.m3627a().m3631a((CharSequence) location)) {
                CBLogging.m3099b("CBInPlay", "Inplay location cannot be empty");
                handler = a.f2917p;
                c1434c = a.f2906e;
                c1434c.getClass();
                handler.post(new C1433a(c1434c, 4, location, CBImpressionError.INVALID_LOCATION));
            } else {
                C1390e c1390e = (C1390e) a.f2914m.get();
                CBImpressionError cBImpressionError = CBImpressionError.NO_AD_FOUND;
                if ((c1390e.f2817y && c1390e.f2818z) || (c1390e.f2797e && c1390e.f2798f)) {
                    C1440e c1440e;
                    CBInPlay cBInPlay2;
                    C1386a a2 = a.f2905d.m3574a(location);
                    if (a2 != null) {
                        CBImpressionError cBImpressionError2;
                        CBInPlay cBInPlay3;
                        C1387b c1387b = (C1387b) a2.f2731c.get("lg");
                        if (c1387b != null) {
                            File a3 = c1387b.m3168a(a.f2905d.f3234b.m3140d().f2696a);
                            if (a3.exists()) {
                                CBInPlay cBInPlay4 = new CBInPlay(a.f2905d, a.f2909h, a.f2911j, a.f2916o, a.f2919r, a2, c1387b.f2750c, a3, location);
                                cBImpressionError2 = cBImpressionError;
                                cBInPlay3 = cBInPlay4;
                            } else {
                                cBImpressionError2 = CBImpressionError.ASSET_MISSING;
                                cBInPlay3 = null;
                            }
                        } else {
                            cBImpressionError2 = cBImpressionError;
                            cBInPlay3 = null;
                        }
                        Executor executor = a.f2902a;
                        c1440e = a.f2905d;
                        c1440e.getClass();
                        executor.execute(new C1439a(c1440e, 8, location, null, null));
                        cBImpressionError = cBImpressionError2;
                        cBInPlay2 = cBInPlay3;
                    } else {
                        cBInPlay2 = null;
                    }
                    if (cBInPlay2 == null) {
                        Executor executor2 = a.f2902a;
                        c1440e = a.f2905d;
                        c1440e.getClass();
                        executor2.execute(new C1439a(c1440e, 3, location, null, null));
                    }
                    cBInPlay = cBInPlay2;
                }
                if (cBInPlay == null) {
                    handler = a.f2917p;
                    c1434c = a.f2906e;
                    c1434c.getClass();
                    handler.post(new C1433a(c1434c, 4, location, cBImpressionError));
                }
            }
        }
        return cBInPlay;
    }
}
