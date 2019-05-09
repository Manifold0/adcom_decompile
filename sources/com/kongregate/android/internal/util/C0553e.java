package com.kongregate.android.internal.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.kongregate.android.internal.util.C0555f.C0554a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.kongregate.android.internal.util.e */
public class C0553e {
    /* renamed from: a */
    public static final String f726a = "scope";
    /* renamed from: b */
    public static final String f727b = "client_id";
    /* renamed from: c */
    public static final String f728c = "e2e";
    /* renamed from: d */
    public static final Collection<String> f729d = C0551d.m643a("service_disabled", "AndroidAuthKillSwitchException");
    /* renamed from: e */
    public static final Collection<String> f730e = C0551d.m643a("access_denied", "OAuthAccessDeniedException");
    /* renamed from: f */
    public static final String f731f = "legacy_override";
    /* renamed from: g */
    public static final String f732g = "response_type";
    /* renamed from: h */
    public static final String f733h = "return_scopes";
    /* renamed from: i */
    public static final String f734i = "token,signed_request";
    /* renamed from: j */
    public static final String f735j = "true";
    /* renamed from: k */
    public static final String f736k = "v2.4";
    /* renamed from: l */
    private static final C0548b f737l = new C0549a();
    /* renamed from: m */
    private static List<C0548b> f738m = C0553e.m653a();

    /* renamed from: com.kongregate.android.internal.util.e$b */
    private static abstract class C0548b {
        /* renamed from: a */
        private static final HashSet<String> f719a = C0548b.m634b();

        /* renamed from: a */
        protected abstract String mo1244a();

        private C0548b() {
        }

        /* renamed from: b */
        private static HashSet<String> m634b() {
            HashSet<String> hashSet = new HashSet();
            hashSet.add("8a3c4b262d721acd49a4bf97d5213199c86fa2b9");
            hashSet.add("a4b7452e2ed8f5f191058ca7bbfd26b0d3214bfc");
            hashSet.add("5e8f16062ea3cd2c4a0d547876baa6f38cabf625");
            return hashSet;
        }

        /* renamed from: a */
        public boolean m636a(Context context, String str) {
            String str2 = Build.BRAND;
            int i = context.getApplicationInfo().flags;
            if (str2.startsWith(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE) && (i & 2) != 0) {
                return true;
            }
            try {
                for (Signature toByteArray : context.getPackageManager().getPackageInfo(str, 64).signatures) {
                    if (f719a.contains(C0551d.m642a(toByteArray.toByteArray()))) {
                        return true;
                    }
                }
                return false;
            } catch (NameNotFoundException e) {
                return false;
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.util.e$a */
    private static class C0549a extends C0548b {
        private C0549a() {
            super();
        }

        /* renamed from: a */
        protected String mo1244a() {
            return "com.facebook.katana";
        }
    }

    /* renamed from: com.kongregate.android.internal.util.e$c */
    static class C0550c {
        /* renamed from: a */
        static final C0550c f720a = new C0550c(C0554a.UNKNOWN);
        /* renamed from: b */
        static final C0550c f721b = new C0550c(C0554a.ACCESS_DISABLED);
        /* renamed from: c */
        static final C0550c f722c = new C0550c(C0554a.LOGIN_CANCELLED);
        /* renamed from: d */
        static final C0550c f723d = new C0550c(C0554a.NO_SYSTEM_ACCOUNT);
        /* renamed from: e */
        private final String f724e;
        /* renamed from: f */
        private final C0554a f725f;

        private C0550c(C0554a c0554a) {
            this.f724e = null;
            this.f725f = c0554a;
        }

        private C0550c(String str) {
            this.f724e = str;
            this.f725f = C0554a.SUCCESS;
        }

        /* renamed from: a */
        String m638a() {
            return this.f724e;
        }

        /* renamed from: b */
        C0554a m639b() {
            return this.f725f;
        }
    }

    /* renamed from: com.kongregate.android.internal.util.e$d */
    private static class C0551d {
        /* renamed from: a */
        public static String m642a(byte[] bArr) {
            return C0551d.m640a(Constants.SHA1, bArr);
        }

        /* renamed from: a */
        private static String m640a(String str, byte[] bArr) {
            try {
                return C0551d.m641a(MessageDigest.getInstance(str), bArr);
            } catch (NoSuchAlgorithmException e) {
                return null;
            }
        }

        /* renamed from: a */
        private static String m641a(MessageDigest messageDigest, byte[] bArr) {
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : digest) {
                stringBuilder.append(Integer.toHexString((b >> 4) & 15));
                stringBuilder.append(Integer.toHexString((b >> 0) & 15));
            }
            return stringBuilder.toString();
        }

        /* renamed from: a */
        public static <T> boolean m645a(Collection<T> collection) {
            return collection == null || collection.size() == 0;
        }

        /* renamed from: a */
        public static boolean m644a(String str) {
            return str == null || str.length() == 0;
        }

        /* renamed from: a */
        public static <T> Collection<T> m643a(T... tArr) {
            return Collections.unmodifiableCollection(Arrays.asList(tArr));
        }
    }

    /* renamed from: com.kongregate.android.internal.util.e$e */
    private static class C0552e extends C0548b {
        private C0552e() {
            super();
        }

        /* renamed from: a */
        protected String mo1244a() {
            return "com.facebook.wakizashi";
        }
    }

    /* renamed from: a */
    private static List<C0548b> m653a() {
        List<C0548b> arrayList = new ArrayList();
        arrayList.add(f737l);
        arrayList.add(new C0552e());
        return arrayList;
    }

    /* renamed from: a */
    static Intent m647a(Context context, Intent intent, C0548b c0548b) {
        if (intent == null) {
            return null;
        }
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return null;
        }
        if (c0548b.m636a(context, resolveActivity.activityInfo.packageName)) {
            return intent;
        }
        return null;
    }

    /* renamed from: a */
    public static C0550c m650a(Activity activity, String str) {
        Collection hashSet = new HashSet();
        hashSet.add("email");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("init", System.currentTimeMillis());
        } catch (JSONException e) {
        }
        Intent a = C0553e.m648a(activity, str, hashSet, jSONObject.toString());
        if (a == null) {
            return C0550c.f723d;
        }
        activity.startActivityForResult(a, 64206);
        return null;
    }

    /* renamed from: a */
    public static Intent m648a(Context context, String str, Collection<String> collection, String str2) {
        for (C0548b c0548b : f738m) {
            Intent putExtra = new Intent().setClassName(c0548b.mo1244a(), "com.facebook.katana.ProxyAuth").putExtra("client_id", str);
            if (!C0551d.m645a((Collection) collection)) {
                putExtra.putExtra("scope", TextUtils.join(",", collection));
            }
            if (!C0551d.m644a(str2)) {
                putExtra.putExtra("e2e", str2);
            }
            putExtra.putExtra("response_type", "token,signed_request");
            putExtra.putExtra("return_scopes", "true");
            putExtra.putExtra("legacy_override", f736k);
            Intent a = C0553e.m647a(context, putExtra, c0548b);
            if (a != null) {
                return a;
            }
        }
        return null;
    }

    /* renamed from: a */
    public static C0550c m649a(int i, int i2, Intent intent) {
        if (i != 64206) {
            return null;
        }
        if (intent == null) {
            return C0550c.f722c;
        }
        if (i2 == 0) {
            return C0550c.f722c;
        }
        if (i2 != -1) {
            return C0550c.f720a;
        }
        return C0553e.m651a(intent);
    }

    /* renamed from: a */
    private static C0550c m651a(Intent intent) {
        Bundle extras = intent.getExtras();
        String b = C0553e.m654b(extras);
        String string = extras.getString(NativeProtocol.BRIDGE_ARG_ERROR_CODE);
        String c = C0553e.m655c(extras);
        if (b == null && string == null && c == null) {
            return new C0550c(C0553e.m652a(extras));
        }
        if (f729d.contains(b)) {
            return C0550c.f721b;
        }
        if (f730e.contains(b)) {
            return C0550c.f722c;
        }
        return C0550c.f720a;
    }

    /* renamed from: a */
    public static String m652a(Bundle bundle) {
        return bundle.getString("access_token");
    }

    /* renamed from: b */
    private static String m654b(Bundle bundle) {
        String string = bundle.getString("error");
        if (string == null) {
            return bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_TYPE);
        }
        return string;
    }

    /* renamed from: c */
    private static String m655c(Bundle bundle) {
        String string = bundle.getString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE);
        if (string == null) {
            return bundle.getString(NativeProtocol.BRIDGE_ARG_ERROR_DESCRIPTION);
        }
        return string;
    }
}
