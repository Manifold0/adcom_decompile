// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Libraries;

import java.io.File;

public class g
{
    public final File a;
    public final File b;
    public final File c;
    public final File d;
    public final File e;
    public final File f;
    public final File g;
    
    g(final File file) {
        this.a = new File(file, ".chartboost");
        if (!this.a.exists()) {
            this.a.mkdirs();
        }
        this.b = a(this.a, "css");
        this.c = a(this.a, "html");
        this.d = a(this.a, "images");
        this.e = a(this.a, "js");
        this.f = a(this.a, "templates");
        this.g = a(this.a, "videos");
    }
    
    private static File a(File file, final String s) {
        file = new File(file, s);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
}
