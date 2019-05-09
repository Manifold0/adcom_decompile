// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.cha;

import java.util.ArrayList;
import android.text.TextUtils;
import java.util.List;
import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.view.View;
import java.lang.ref.WeakReference;

abstract class d
{
    private WeakReference<View> \u02bb;
    private final boolean \u02bc;
    final boolean \u02bd;
    TrackerListener \u02ca;
    private boolean \u02ca\u0971;
    final String \u02cb;
    j \u02ce;
    WeakReference<WebView> \u02cf;
    private boolean \u037a;
    o \u0971;
    private final u \u141d;
    
    d(@Nullable final View view, final boolean \u02bc, final boolean \u02bd) {
        this.\u0971 = null;
        a.\u02cf(3, "BaseTracker", this, "Initializing.");
        String string;
        if (\u02bc) {
            string = "m" + this.hashCode();
        }
        else {
            string = "";
        }
        this.\u02cb = string;
        this.\u02bb = new WeakReference<View>(view);
        this.\u02bc = \u02bc;
        this.\u02bd = \u02bd;
        this.\u02ca\u0971 = false;
        this.\u037a = false;
        this.\u141d = new u();
    }
    
    @CallSuper
    public void changeTargetView(final View view) {
        a.\u02cf(3, "BaseTracker", this, "changing view to " + a.\u02cf(view));
        this.\u02bb = new WeakReference<View>(view);
    }
    
    public void removeListener() {
        this.\u02ca = null;
    }
    
    @Deprecated
    public void setActivity(final Activity activity) {
    }
    
    public void setListener(final TrackerListener \u02ca) {
        this.\u02ca = \u02ca;
    }
    
    public void startTracking() {
        try {
            a.\u02cf(3, "BaseTracker", this, "In startTracking method.");
            this.\u02cf();
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingStarted("Tracking started on " + a.\u02cf(this.\u02bb.get()));
            }
            final String string = "startTracking succeeded for " + a.\u02cf(this.\u02bb.get());
            a.\u02cf(3, "BaseTracker", this, string);
            a.\u02ca("[SUCCESS] ", this.\u02cb() + " " + string);
        }
        catch (Exception ex) {
            this.\u0971("startTracking", ex);
        }
    }
    
    @CallSuper
    public void stopTracking() {
        int n;
        StringBuilder sb;
        String s;
        String s2;
        StringBuilder append;
        String s3;
        Label_0048_Outer:Label_0075_Outer:Label_0103_Outer:
        while (true) {
            n = 1;
            while (true) {
            Label_0183:
                while (true) {
                Label_0177:
                    while (true) {
                    Label_0171:
                        while (true) {
                            try {
                                a.\u02cf(3, "BaseTracker", this, "In stopTracking method.");
                                this.\u037a = true;
                                if (this.\u02ce != null) {
                                    this.\u02ce.\u02cb(this);
                                    sb = new StringBuilder("Attempt to stop tracking ad impression was ");
                                    if (n == 0) {
                                        break Label_0171;
                                    }
                                    s = "";
                                    a.\u02cf(3, "BaseTracker", this, sb.append(s).append("successful.").toString());
                                    if (n == 0) {
                                        break Label_0177;
                                    }
                                    s2 = "[SUCCESS] ";
                                    append = new StringBuilder().append(this.\u02cb()).append(" stopTracking ");
                                    if (n != 0) {
                                        s3 = "succeeded";
                                        a.\u02ca(s2, append.append(s3).append(" for ").append(a.\u02cf(this.\u02bb.get())).toString());
                                        if (this.\u02ca != null) {
                                            this.\u02ca.onTrackingStopped("");
                                            this.\u02ca = null;
                                        }
                                        return;
                                    }
                                    break Label_0183;
                                }
                            }
                            catch (Exception ex) {
                                o.\u02ce(ex);
                            }
                            n = 0;
                            continue Label_0048_Outer;
                        }
                        s = "un";
                        continue Label_0075_Outer;
                    }
                    s2 = "[ERROR] ";
                    continue Label_0103_Outer;
                }
                s3 = "failed";
                continue;
            }
        }
    }
    
    final String \u02bb() {
        return a.\u02cf(this.\u02bb.get());
    }
    
    final View \u02bc() {
        return this.\u02bb.get();
    }
    
    final String \u02bd() {
        this.\u141d.\u02cb(this.\u02cb, this.\u02bb.get());
        return this.\u141d.\u0971;
    }
    
    final boolean \u02ca() {
        return this.\u02ca\u0971 && !this.\u037a;
    }
    
    abstract String \u02cb();
    
    @CallSuper
    void \u02cb(final List<String> list) throws o {
        if (this.\u02bb.get() == null && !this.\u02bd) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join((CharSequence)" and ", (Iterable)list));
        }
    }
    
    final void \u02ce() throws o {
        if (this.\u0971 != null) {
            throw new o("Tracker initialization failed: " + this.\u0971.getMessage());
        }
    }
    
    @CallSuper
    void \u02cf() throws o {
        a.\u02cf(3, "BaseTracker", this, "Attempting to start impression.");
        this.\u02ce();
        if (this.\u02ca\u0971) {
            throw new o("Tracker already started");
        }
        if (this.\u037a) {
            throw new o("Tracker already stopped");
        }
        this.\u02cb(new ArrayList<String>());
        if (this.\u02ce != null) {
            this.\u02ce.\u02ce(this);
            this.\u02ca\u0971 = true;
            a.\u02cf(3, "BaseTracker", this, "Impression started.");
            return;
        }
        a.\u02cf(3, "BaseTracker", this, "Bridge is null, won't start tracking");
        throw new o("Bridge is null");
    }
    
    final void \u0971() throws o {
        if (this.\u02ca\u0971) {
            throw new o("Tracker already started");
        }
        if (this.\u037a) {
            throw new o("Tracker already stopped");
        }
    }
    
    final void \u0971(final WebView webView) throws o {
        if (webView != null) {
            this.\u02cf = new WeakReference<WebView>(webView);
            if (this.\u02ce == null) {
                int n;
                if (this.\u02bc || this.\u02bd) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    a.\u02cf(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.\u02cf.get() != null) {
                        this.\u02ce = new j(this.\u02cf.get(), j.e.\u02cf);
                        a.\u02cf(3, "BaseTracker", this, "Bridge installed.");
                    }
                    else {
                        this.\u02ce = null;
                        a.\u02cf(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            if (this.\u02ce != null) {
                this.\u02ce.\u02ca(this);
            }
        }
    }
    
    final void \u0971(String \u02ce, final Exception ex) {
        try {
            o.\u02ce(ex);
            \u02ce = o.\u02ce(\u02ce, ex);
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingFailedToStart(\u02ce);
            }
            a.\u02cf(3, "BaseTracker", this, \u02ce);
            a.\u02ca("[ERROR] ", this.\u02cb() + " " + \u02ce);
        }
        catch (Exception ex2) {}
    }
}
