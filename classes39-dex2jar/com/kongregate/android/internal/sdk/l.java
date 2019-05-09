// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk;

import android.content.Intent;

public class l
{
    public static final String a = "com.kongregate.android.internal.sdk.BroadcastAPIEvent";
    public static final String b = "com.kongregate.android.internal.sdk.event";
    public static final String c = "com.kongregate.android.internal.sdk.data";
    public static final String d = "com.kongregate.android.internal.sdk.AlertDismissEvent";
    public static final String e = "com.kongregate.android.internal.sdk.AlertDismissButton";
    public static final String f = "gdpr_policy_accepted";
    public static final String g = "gdpr_view_policy";
    public static final String h = "com.kongregate.android.internal.sdk.KongregateStatsSync";
    public static final String i = "com.kongregate.android.internal.sdk.ExpiredSesssion";
    public static final String j = "com.kongregate.android.internal.sdk.ClosePanel";
    public static final String k = "com.kongregate.android.internal.sdk.LoadUser";
    public static final String l = "username";
    
    public static final Intent a(final String s) {
        return a(s, null);
    }
    
    public static final Intent a(final String s, final String s2) {
        final Intent intent = new Intent("com.kongregate.android.internal.sdk.BroadcastAPIEvent");
        intent.putExtra("com.kongregate.android.internal.sdk.event", s);
        if (s2 != null) {
            intent.putExtra("com.kongregate.android.internal.sdk.data", s2);
        }
        return intent;
    }
}
