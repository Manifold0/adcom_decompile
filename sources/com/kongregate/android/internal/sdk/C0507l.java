package com.kongregate.android.internal.sdk;

import android.content.Intent;

/* renamed from: com.kongregate.android.internal.sdk.l */
public class C0507l {
    /* renamed from: a */
    public static final String f551a = "com.kongregate.android.internal.sdk.BroadcastAPIEvent";
    /* renamed from: b */
    public static final String f552b = "com.kongregate.android.internal.sdk.event";
    /* renamed from: c */
    public static final String f553c = "com.kongregate.android.internal.sdk.data";
    /* renamed from: d */
    public static final String f554d = "com.kongregate.android.internal.sdk.AlertDismissEvent";
    /* renamed from: e */
    public static final String f555e = "com.kongregate.android.internal.sdk.AlertDismissButton";
    /* renamed from: f */
    public static final String f556f = "gdpr_policy_accepted";
    /* renamed from: g */
    public static final String f557g = "gdpr_view_policy";
    /* renamed from: h */
    public static final String f558h = "com.kongregate.android.internal.sdk.KongregateStatsSync";
    /* renamed from: i */
    public static final String f559i = "com.kongregate.android.internal.sdk.ExpiredSesssion";
    /* renamed from: j */
    public static final String f560j = "com.kongregate.android.internal.sdk.ClosePanel";
    /* renamed from: k */
    public static final String f561k = "com.kongregate.android.internal.sdk.LoadUser";
    /* renamed from: l */
    public static final String f562l = "username";

    /* renamed from: a */
    public static final Intent m456a(String str) {
        return C0507l.m457a(str, null);
    }

    /* renamed from: a */
    public static final Intent m457a(String str, String str2) {
        Intent intent = new Intent(f551a);
        intent.putExtra(f552b, str);
        if (str2 != null) {
            intent.putExtra(f553c, str2);
        }
        return intent;
    }
}
