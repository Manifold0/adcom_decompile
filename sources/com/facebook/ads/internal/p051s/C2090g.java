package com.facebook.ads.internal.p051s;

import com.tapjoy.TJAdUnitConstants.String;

/* renamed from: com.facebook.ads.internal.s.g */
public enum C2090g {
    TEST("test"),
    BROWSER_SESSION("browser_session"),
    CLOSE(String.CLOSE),
    SHOW_AD_CALLED("show_ad_called"),
    IMPRESSION("impression"),
    INVALIDATION("invalidation"),
    STORE("store"),
    OFF_TARGET_CLICK("off_target_click"),
    OPEN_LINK("open_link"),
    NATIVE_VIEW("native_view"),
    f4754k("video"),
    USER_RETURN("user_return"),
    AD_REPORTING("x_out"),
    PREVIEW_IMPRESSION("preview_impression"),
    AD_SELECTION("ad_selection"),
    CLICK_GUARD("click_guard"),
    TWO_STEP("two_step_dialog"),
    TWO_STEP_CANCEL("two_step_cancel"),
    SWIPE_TO_CLICK("swipe_click"),
    CLOSE_BROWSE_VIEW("browse_view_closed"),
    WATCH_AND_X_MINIMIZED("watch_and_x_minimized");
    
    /* renamed from: v */
    private String f4766v;

    private C2090g(String str) {
        this.f4766v = str;
    }

    /* renamed from: a */
    public static C2090g m5209a(String str) {
        for (C2090g c2090g : C2090g.values()) {
            if (c2090g.f4766v.equalsIgnoreCase(str)) {
                return c2090g;
            }
        }
        return null;
    }

    public String toString() {
        return this.f4766v;
    }
}
