// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.iro;

import android.text.TextUtils;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.view.View;
import java.lang.ref.WeakReference;

abstract class c
{
    private WeakReference<View> \u02bb;
    private final boolean \u02bc;
    private boolean \u02bd;
    TrackerListener \u02ca;
    private final y \u02ca\u0971;
    o \u02cb;
    final String \u02ce;
    f \u02cf;
    private boolean \u02cf\u0971;
    WeakReference<WebView> \u0971;
    final boolean \u141d;
    
    c(@Nullable final View view, final boolean \u02bc, final boolean \u141d) {
        this.\u02cb = null;
        b.\u02cf(3, "BaseTracker", this, "Initializing.");
        String string;
        if (\u02bc) {
            string = "m" + this.hashCode();
        }
        else {
            string = "";
        }
        this.\u02ce = string;
        this.\u02bb = new WeakReference<View>(view);
        this.\u02bc = \u02bc;
        this.\u141d = \u141d;
        this.\u02bd = false;
        this.\u02cf\u0971 = false;
        this.\u02ca\u0971 = new y();
    }
    
    @CallSuper
    public void changeTargetView(final View view) {
        b.\u02cf(3, "BaseTracker", this, "changing view to " + b.\u0971(view));
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
            b.\u02cf(3, "BaseTracker", this, "In startTracking method.");
            this.\u02ce();
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingStarted("Tracking started on " + b.\u0971(this.\u02bb.get()));
            }
            final String string = "startTracking succeeded for " + b.\u0971(this.\u02bb.get());
            b.\u02cf(3, "BaseTracker", this, string);
            b.\u02ce("[SUCCESS] ", this.\u02ca() + " " + string);
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
                                b.\u02cf(3, "BaseTracker", this, "In stopTracking method.");
                                this.\u02cf\u0971 = true;
                                if (this.\u02cf != null) {
                                    this.\u02cf.\u02cf(this);
                                    sb = new StringBuilder("Attempt to stop tracking ad impression was ");
                                    if (n == 0) {
                                        break Label_0171;
                                    }
                                    s = "";
                                    b.\u02cf(3, "BaseTracker", this, sb.append(s).append("successful.").toString());
                                    if (n == 0) {
                                        break Label_0177;
                                    }
                                    s2 = "[SUCCESS] ";
                                    append = new StringBuilder().append(this.\u02ca()).append(" stopTracking ");
                                    if (n != 0) {
                                        s3 = "succeeded";
                                        b.\u02ce(s2, append.append(s3).append(" for ").append(b.\u0971(this.\u02bb.get())).toString());
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
                                o.\u0971(ex);
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
    
    final View \u02bb() {
        return this.\u02bb.get();
    }
    
    final String \u02bc() {
        this.\u02ca\u0971.\u02ce(this.\u02ce, this.\u02bb.get());
        return this.\u02ca\u0971.\u02ce;
    }
    
    final String \u02bd() {
        return b.\u0971(this.\u02bb.get());
    }
    
    abstract String \u02ca();
    
    final void \u02cb() throws o {
        if (this.\u02cb != null) {
            throw new o("Tracker initialization failed: " + this.\u02cb.getMessage());
        }
    }
    
    final void \u02cb(final WebView webView) throws o {
        if (webView != null) {
            this.\u0971 = new WeakReference<WebView>(webView);
            if (this.\u02cf == null) {
                int n;
                if (this.\u02bc || this.\u141d) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    b.\u02cf(3, "BaseTracker", this, "Attempting bridge installation.");
                    if (this.\u0971.get() != null) {
                        this.\u02cf = new f(this.\u0971.get(), f.b.\u0971);
                        b.\u02cf(3, "BaseTracker", this, "Bridge installed.");
                    }
                    else {
                        this.\u02cf = null;
                        b.\u02cf(3, "BaseTracker", this, "Bridge not installed, WebView is null.");
                    }
                }
            }
            if (this.\u02cf != null) {
                this.\u02cf.\u02ce(this);
            }
        }
    }
    
    @CallSuper
    void \u02ce() throws o {
        b.\u02cf(3, "BaseTracker", this, "Attempting to start impression.");
        this.\u02cb();
        if (this.\u02bd) {
            throw new o("Tracker already started");
        }
        if (this.\u02cf\u0971) {
            throw new o("Tracker already stopped");
        }
        this.\u02cf(new ArrayList<String>());
        if (this.\u02cf != null) {
            this.\u02cf.\u0971(this);
            this.\u02bd = true;
            b.\u02cf(3, "BaseTracker", this, "Impression started.");
            return;
        }
        b.\u02cf(3, "BaseTracker", this, "Bridge is null, won't start tracking");
        throw new o("Bridge is null");
    }
    
    final void \u02cf() throws o {
        if (this.\u02bd) {
            throw new o("Tracker already started");
        }
        if (this.\u02cf\u0971) {
            throw new o("Tracker already stopped");
        }
    }
    
    @CallSuper
    void \u02cf(final List<String> list) throws o {
        if (this.\u02bb.get() == null && !this.\u141d) {
            list.add("Tracker's target view is null");
        }
        if (!list.isEmpty()) {
            throw new o(TextUtils.join((CharSequence)" and ", (Iterable)list));
        }
    }
    
    final void \u0971(String \u0971, final Exception ex) {
        try {
            o.\u0971(ex);
            \u0971 = o.\u0971(\u0971, ex);
            if (this.\u02ca != null) {
                this.\u02ca.onTrackingFailedToStart(\u0971);
            }
            b.\u02cf(3, "BaseTracker", this, \u0971);
            b.\u02ce("[ERROR] ", this.\u02ca() + " " + \u0971);
        }
        catch (Exception ex2) {}
    }
    
    final boolean \u0971() {
        return this.\u02bd && !this.\u02cf\u0971;
    }
}
