package com.chartboost.sdk.Libraries;

import com.tapjoy.TJAdUnitConstants.String;
import java.io.File;

/* renamed from: com.chartboost.sdk.Libraries.g */
public class C1379g {
    /* renamed from: a */
    public final File f2696a;
    /* renamed from: b */
    public final File f2697b;
    /* renamed from: c */
    public final File f2698c;
    /* renamed from: d */
    public final File f2699d;
    /* renamed from: e */
    public final File f2700e;
    /* renamed from: f */
    public final File f2701f;
    /* renamed from: g */
    public final File f2702g;

    C1379g(File file) {
        this.f2696a = new File(file, ".chartboost");
        if (!this.f2696a.exists()) {
            this.f2696a.mkdirs();
        }
        this.f2697b = C1379g.m3142a(this.f2696a, "css");
        this.f2698c = C1379g.m3142a(this.f2696a, String.HTML);
        this.f2699d = C1379g.m3142a(this.f2696a, "images");
        this.f2700e = C1379g.m3142a(this.f2696a, "js");
        this.f2701f = C1379g.m3142a(this.f2696a, "templates");
        this.f2702g = C1379g.m3142a(this.f2696a, "videos");
    }

    /* renamed from: a */
    private static File m3142a(File file, String str) {
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return file2;
    }
}
