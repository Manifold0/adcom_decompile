package com.kongregate.android.internal.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Process;
import android.os.UserHandle;
import android.os.UserManager;
import android.support.v4.view.InputDeviceCompat;
import android.view.View;
import java.util.concurrent.ThreadPoolExecutor;

/* renamed from: com.kongregate.android.internal.util.a */
public class C0542a {
    @TargetApi(11)
    /* renamed from: a */
    public static void m601a(Activity activity) {
        if (C0542a.m605a()) {
            try {
                activity.invalidateOptionsMenu();
            } catch (Throwable e) {
                C0562j.m760c("hasHoneycomb is true but invalidateOptionsMenu doesn't exist", e);
            }
        }
    }

    @TargetApi(11)
    /* renamed from: a */
    public static void m603a(View view, float f) {
        if (C0542a.m605a()) {
            try {
                view.setAlpha(f);
            } catch (NoSuchMethodError e) {
                C0562j.m759c("hasHoneycomb is true but setAlpha(float) doesn't exist");
            }
        }
    }

    @TargetApi(11)
    /* renamed from: a */
    public static void m602a(Activity activity, boolean z) {
        if (activity != null && !activity.isFinishing() && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            View rootView = activity.getWindow().getDecorView().getRootView();
            if (rootView != null && C0542a.m605a() && rootView.hasFocus()) {
                C0562j.m753a("enter immersive mode");
                int i = (C0542a.m607a((Context) activity) && z) ? InputDeviceCompat.SOURCE_TOUCHSCREEN : 0;
                rootView.setSystemUiVisibility((i | 1) | 4);
            }
        }
    }

    /* renamed from: a */
    public static boolean m607a(Context context) {
        if (C0542a.m606a(19)) {
            return C0542a.m609b(context);
        }
        return false;
    }

    @TargetApi(17)
    /* renamed from: b */
    public static boolean m609b(Context context) {
        if (!C0542a.m606a(17)) {
            return true;
        }
        UserHandle myUserHandle = Process.myUserHandle();
        UserManager userManager = (UserManager) context.getSystemService("user");
        if (userManager == null) {
            return false;
        }
        return 0 == userManager.getSerialNumberForUser(myUserHandle);
    }

    /* renamed from: a */
    public static boolean m605a() {
        return VERSION.SDK_INT >= 11;
    }

    /* renamed from: b */
    public static boolean m608b() {
        return VERSION.SDK_INT >= 14;
    }

    /* renamed from: c */
    public static boolean m610c() {
        return VERSION.SDK_INT >= 16;
    }

    /* renamed from: a */
    public static boolean m606a(int i) {
        return VERSION.SDK_INT >= i;
    }

    /* renamed from: a */
    public static void m604a(ThreadPoolExecutor threadPoolExecutor) {
        if (C0542a.m606a(9) && threadPoolExecutor != null) {
            try {
                if (!threadPoolExecutor.allowsCoreThreadTimeOut()) {
                    threadPoolExecutor.allowCoreThreadTimeOut(true);
                }
            } catch (NoSuchMethodError e) {
                C0562j.m759c("NoSuchMethod: allowCoreThreadTimeout");
            }
        }
    }
}
