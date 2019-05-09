// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import java.util.Collections;
import java.util.Arrays;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import android.content.pm.Signature;
import android.content.pm.PackageManager$NameNotFoundException;
import android.os.Build;
import java.util.ArrayList;
import android.os.Bundle;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashSet;
import android.app.Activity;
import java.util.Iterator;
import android.text.TextUtils;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.Context;
import java.util.List;
import java.util.Collection;

public class e
{
    public static final String a = "scope";
    public static final String b = "client_id";
    public static final String c = "e2e";
    public static final Collection<String> d;
    public static final Collection<String> e;
    public static final String f = "legacy_override";
    public static final String g = "response_type";
    public static final String h = "return_scopes";
    public static final String i = "token,signed_request";
    public static final String j = "true";
    public static final String k = "v2.4";
    private static final b l;
    private static List<b> m;
    
    static {
        d = com.kongregate.android.internal.util.e.d.a("service_disabled", "AndroidAuthKillSwitchException");
        e = com.kongregate.android.internal.util.e.d.a("access_denied", "OAuthAccessDeniedException");
        l = (b)new a();
        com.kongregate.android.internal.util.e.m = a();
    }
    
    static Intent a(final Context context, Intent intent, final b b) {
        if (intent == null) {
            intent = null;
        }
        else {
            final ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
            if (resolveActivity == null) {
                return null;
            }
            if (!b.a(context, resolveActivity.activityInfo.packageName)) {
                return null;
            }
        }
        return intent;
    }
    
    public static Intent a(final Context context, final String s, final Collection<String> collection, final String s2) {
        for (final b b : com.kongregate.android.internal.util.e.m) {
            final Intent putExtra = new Intent().setClassName(b.a(), "com.facebook.katana.ProxyAuth").putExtra("client_id", s);
            if (!com.kongregate.android.internal.util.e.d.a((Collection<Object>)collection)) {
                putExtra.putExtra("scope", TextUtils.join((CharSequence)",", (Iterable)collection));
            }
            if (!com.kongregate.android.internal.util.e.d.a(s2)) {
                putExtra.putExtra("e2e", s2);
            }
            putExtra.putExtra("response_type", "token,signed_request");
            putExtra.putExtra("return_scopes", "true");
            putExtra.putExtra("legacy_override", "v2.4");
            final Intent a = a(context, putExtra, b);
            if (a != null) {
                return a;
            }
        }
        return null;
    }
    
    public static c a(final int n, final int n2, final Intent intent) {
        if (n != 64206) {
            return null;
        }
        if (intent == null) {
            return com.kongregate.android.internal.util.e.c.c;
        }
        if (n2 == 0) {
            return com.kongregate.android.internal.util.e.c.c;
        }
        if (n2 != -1) {
            return com.kongregate.android.internal.util.e.c.a;
        }
        return a(intent);
    }
    
    public static c a(final Activity activity, String a) {
        final HashSet<String> set = new HashSet<String>();
        set.add("email");
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("init", System.currentTimeMillis());
                a = (String)a((Context)activity, a, set, jsonObject.toString());
                if (a == null) {
                    return com.kongregate.android.internal.util.e.c.d;
                }
                activity.startActivityForResult((Intent)a, 64206);
                return null;
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    private static c a(final Intent intent) {
        final Bundle extras = intent.getExtras();
        final String b = b(extras);
        final String string = extras.getString("error_code");
        final String c = c(extras);
        if (b == null && string == null && c == null) {
            return new c(a(extras));
        }
        if (com.kongregate.android.internal.util.e.d.contains(b)) {
            return com.kongregate.android.internal.util.e.c.b;
        }
        if (com.kongregate.android.internal.util.e.e.contains(b)) {
            return com.kongregate.android.internal.util.e.c.c;
        }
        return com.kongregate.android.internal.util.e.c.a;
    }
    
    public static String a(final Bundle bundle) {
        return bundle.getString("access_token");
    }
    
    private static List<b> a() {
        final ArrayList<e> list = new ArrayList<e>();
        list.add(com.kongregate.android.internal.util.e.l);
        list.add(new e());
        return (List<b>)list;
    }
    
    private static String b(final Bundle bundle) {
        String s;
        if ((s = bundle.getString("error")) == null) {
            s = bundle.getString("error_type");
        }
        return s;
    }
    
    private static String c(final Bundle bundle) {
        String s;
        if ((s = bundle.getString("error_message")) == null) {
            s = bundle.getString("error_description");
        }
        return s;
    }
    
    private static class a extends b
    {
        @Override
        protected String a() {
            return "com.facebook.katana";
        }
    }
    
    private abstract static class b
    {
        private static final HashSet<String> a;
        
        static {
            a = b();
        }
        
        private static HashSet<String> b() {
            final HashSet<String> set = new HashSet<String>();
            set.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            set.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            set.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return set;
        }
        
        protected abstract String a();
        
        public boolean a(final Context context, String a) {
            final String brand = Build.BRAND;
            final int flags = context.getApplicationInfo().flags;
            if (!brand.startsWith("generic") || (flags & 0x2) == 0x0) {
                try {
                    final Signature[] signatures = context.getPackageManager().getPackageInfo(a, 64).signatures;
                    for (int length = signatures.length, i = 0; i < length; ++i) {
                        a = com.kongregate.android.internal.util.e.d.a(signatures[i].toByteArray());
                        if (b.a.contains(a)) {
                            return true;
                        }
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    return false;
                }
                return false;
            }
            return true;
        }
    }
    
    static class c
    {
        static final c a;
        static final c b;
        static final c c;
        static final c d;
        private final String e;
        private final f.a f;
        
        static {
            a = new c(com.kongregate.android.internal.util.f.a.b);
            b = new c(com.kongregate.android.internal.util.f.a.c);
            c = new c(com.kongregate.android.internal.util.f.a.d);
            d = new c(com.kongregate.android.internal.util.f.a.e);
        }
        
        private c(final f.a f) {
            this.e = null;
            this.f = f;
        }
        
        private c(final String e) {
            this.e = e;
            this.f = com.kongregate.android.internal.util.f.a.a;
        }
        
        String a() {
            return this.e;
        }
        
        f.a b() {
            return this.f;
        }
    }
    
    private static class d
    {
        private static String a(final String s, final byte[] array) {
            try {
                return a(MessageDigest.getInstance(s), array);
            }
            catch (NoSuchAlgorithmException ex) {
                return null;
            }
        }
        
        private static String a(final MessageDigest messageDigest, final byte[] array) {
            messageDigest.update(array);
            final byte[] digest = messageDigest.digest();
            final StringBuilder sb = new StringBuilder();
            for (int length = digest.length, i = 0; i < length; ++i) {
                final byte b = digest[i];
                sb.append(Integer.toHexString(b >> 4 & 0xF));
                sb.append(Integer.toHexString(b >> 0 & 0xF));
            }
            return sb.toString();
        }
        
        public static String a(final byte[] array) {
            return a("SHA-1", array);
        }
        
        public static <T> Collection<T> a(final T... array) {
            return Collections.unmodifiableCollection((Collection<? extends T>)Arrays.asList(array));
        }
        
        public static boolean a(final String s) {
            return s == null || s.length() == 0;
        }
        
        public static <T> boolean a(final Collection<T> collection) {
            return collection == null || collection.size() == 0;
        }
    }
    
    private static class e extends b
    {
        @Override
        protected String a() {
            return "com.facebook.wakizashi";
        }
    }
}
