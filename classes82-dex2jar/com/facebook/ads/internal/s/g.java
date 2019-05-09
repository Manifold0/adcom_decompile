// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.s;

public enum g
{
    a("test"), 
    b("browser_session"), 
    c("close"), 
    d("show_ad_called"), 
    e("impression"), 
    f("invalidation"), 
    g("store"), 
    h("off_target_click"), 
    i("open_link"), 
    j("native_view"), 
    k("video"), 
    l("user_return"), 
    m("x_out"), 
    n("preview_impression"), 
    o("ad_selection"), 
    p("click_guard"), 
    q("two_step_dialog"), 
    r("two_step_cancel"), 
    s("swipe_click"), 
    t("browse_view_closed"), 
    u("watch_and_x_minimized");
    
    private String v;
    
    private g(final String v) {
        this.v = v;
    }
    
    public static g a(final String s) {
        final g[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final g g = values[i];
            if (g.v.equalsIgnoreCase(s)) {
                return g;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.v;
    }
}
