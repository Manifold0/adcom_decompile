// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.app.Activity;
import android.support.annotation.CallSuper;
import com.moat.analytics.mobile.vng.a.a.a;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.view.View;
import java.lang.ref.WeakReference;

abstract class b
{
    j a;
    final String b;
    final boolean c;
    boolean d;
    boolean e;
    private WeakReference<View> f;
    private WeakReference<WebView> g;
    private final z h;
    private final boolean i;
    
    b(@Nullable final View view, final boolean i, final boolean c) {
        p.a(3, "BaseTracker", this, "Initializing.");
        if (i) {
            this.b = "m" + this.hashCode();
        }
        else {
            this.b = "";
        }
        this.f = new WeakReference<View>(view);
        this.i = i;
        this.c = c;
        this.d = false;
        this.e = false;
        this.h = new z();
    }
    
    private void g() {
        com.moat.analytics.mobile.vng.a.a.a.a(this.g);
        p.a(3, "BaseTracker", this, "Attempting bridge installation.");
        if (this.g.get() != null) {
            if (!this.i && !this.c) {
                this.a = new j(this.g.get(), j.a.a);
            }
            final boolean a = this.a.a;
            final StringBuilder append = new StringBuilder().append("Bridge ");
            String s;
            if (a) {
                s = "";
            }
            else {
                s = "not ";
            }
            p.a(3, "BaseTracker", this, append.append(s).append("installed.").toString());
            return;
        }
        this.a = null;
        p.a(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
    }
    
    abstract String a();
    
    void a(final WebView webView) {
        if (webView != null) {
            this.g = new WeakReference<WebView>(webView);
            if (this.a == null) {
                this.g();
            }
            if (this.a != null && this.a.a) {
                this.a.a(this);
            }
        }
    }
    
    void a(final j a) {
        this.a = a;
    }
    
    boolean b() {
        p.a(3, "BaseTracker", this, "Attempting to start impression.");
        if (this.e) {
            p.a(3, "BaseTracker", this, "startTracking failed, tracker already started");
            p.a("[INFO] ", this.a() + " already started");
            return false;
        }
        final boolean b = this.a.b(this);
        final StringBuilder append = new StringBuilder().append("Impression ");
        String s;
        if (b) {
            s = "";
        }
        else {
            s = "not ";
        }
        p.a(3, "BaseTracker", this, append.append(s).append("started.").toString());
        if (b) {
            this.d = true;
            this.e = true;
            return b;
        }
        return b;
    }
    
    boolean c() {
        p.a(3, "BaseTracker", this, "Attempting to stop impression.");
        this.d = false;
        final boolean c = this.a.c(this);
        final StringBuilder append = new StringBuilder().append("Impression tracking ");
        String s;
        if (c) {
            s = "";
        }
        else {
            s = "not ";
        }
        p.a(3, "BaseTracker", this, append.append(s).append("stopped.").toString());
        return c;
    }
    
    @CallSuper
    public void changeTargetView(final View view) {
        final StringBuilder append = new StringBuilder().append("changing view to ");
        String string;
        if (view != null) {
            string = view.getClass().getSimpleName() + "@" + view.hashCode();
        }
        else {
            string = "null";
        }
        p.a(3, "BaseTracker", this, append.append(string).toString());
        this.f = new WeakReference<View>(view);
    }
    
    View d() {
        return this.f.get();
    }
    
    String e() {
        if (this.d() != null) {
            return this.d().getClass().getSimpleName() + "@" + this.d().hashCode();
        }
        return "";
    }
    
    String f() {
        this.h.a(this.b, this.d());
        return this.h.a;
    }
    
    @Deprecated
    public void setActivity(final Activity activity) {
    }
    
    public void startTracking() {
        boolean b;
        StringBuilder append;
        String s;
        String s2;
        StringBuilder append2;
        String s3;
        Label_0039_Outer:Label_0067_Outer:Label_0096_Outer:
        while (true) {
            b = false;
            while (true) {
            Label_0143:
                while (true) {
                Label_0137:
                    while (true) {
                        while (true) {
                            try {
                                p.a(3, "BaseTracker", this, "In startTracking method.");
                                b = this.b();
                                append = new StringBuilder().append("Attempt to start tracking ad impression was ");
                                if (b) {
                                    s = "";
                                    p.a(3, "BaseTracker", this, append.append(s).append("successful.").toString());
                                    if (!b) {
                                        break Label_0137;
                                    }
                                    s2 = "[SUCCESS] ";
                                    append2 = new StringBuilder().append(this.a()).append(" startTracking ");
                                    if (b) {
                                        s3 = "succeeded";
                                        p.a(s2, append2.append(s3).append(" for ").append(this.e()).toString());
                                        return;
                                    }
                                    break Label_0143;
                                }
                            }
                            catch (Exception ex) {
                                m.a(ex);
                                continue Label_0039_Outer;
                            }
                            break;
                        }
                        s = "un";
                        continue Label_0067_Outer;
                    }
                    s2 = "[ERROR] ";
                    continue Label_0096_Outer;
                }
                s3 = "failed";
                continue;
            }
        }
    }
    
    public void stopTracking() {
        boolean c;
        StringBuilder append;
        String s;
        String s2;
        StringBuilder append2;
        String s3;
        Label_0039_Outer:Label_0067_Outer:Label_0096_Outer:
        while (true) {
            c = false;
            while (true) {
            Label_0143:
                while (true) {
                Label_0137:
                    while (true) {
                        while (true) {
                            try {
                                p.a(3, "BaseTracker", this, "In stopTracking method.");
                                c = this.c();
                                append = new StringBuilder().append("Attempt to stop tracking ad impression was ");
                                if (c) {
                                    s = "";
                                    p.a(3, "BaseTracker", this, append.append(s).append("successful.").toString());
                                    if (!c) {
                                        break Label_0137;
                                    }
                                    s2 = "[SUCCESS] ";
                                    append2 = new StringBuilder().append(this.a()).append(" stopTracking ");
                                    if (c) {
                                        s3 = "succeeded";
                                        p.a(s2, append2.append(s3).append(" for ").append(this.e()).toString());
                                        return;
                                    }
                                    break Label_0143;
                                }
                            }
                            catch (Exception ex) {
                                m.a(ex);
                                continue Label_0039_Outer;
                            }
                            break;
                        }
                        s = "un";
                        continue Label_0067_Outer;
                    }
                    s2 = "[ERROR] ";
                    continue Label_0096_Outer;
                }
                s3 = "failed";
                continue;
            }
        }
    }
}
