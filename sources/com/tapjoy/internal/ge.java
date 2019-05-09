package com.tapjoy.internal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources.NotFoundException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Html;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.drive.DriveFile;
import com.onesignal.OneSignalDbContract.NotificationTable;
import com.tapjoy.Tapjoy;
import com.tapjoy.TapjoyReceiver;
import com.tapjoy.internal.C2834a.C2823l;
import com.tapjoy.internal.C2834a.C2825c;
import com.tapjoy.internal.C2834a.C2826d;
import com.tapjoy.internal.dy.C2879a;
import com.vungle.warren.ui.VungleActivity;

public final class ge extends C2948r {
    /* renamed from: c */
    private static ge f7890c;

    /* renamed from: com.tapjoy.internal.ge$1 */
    class C29461 implements C2945t {
        C29461() {
        }

        /* renamed from: a */
        public final String mo6296a(Context context) {
            return gm.m7971a(context).f7932b.getString("gcm.senderIds", "");
        }

        /* renamed from: a */
        public final void mo6299a(Context context, String str) {
            C2991p.m8216a(gm.m7971a(context).f7932b, "gcm.senderIds", str);
        }

        /* renamed from: b */
        public final String mo6301b(Context context) {
            return gm.m7971a(context).f7932b.getString("gcm.regId", "");
        }

        /* renamed from: b */
        public final void mo6303b(Context context, String str) {
            C2991p.m8216a(gm.m7971a(context).f7932b, "gcm.regId", str);
        }

        /* renamed from: c */
        public final boolean mo6305c(Context context) {
            return gm.m7971a(context).f7932b.getBoolean("gcm.stale", true);
        }

        /* renamed from: a */
        public final void mo6300a(Context context, boolean z) {
            C2991p.m8217a(gm.m7971a(context).f7932b, "gcm.stale", z);
        }

        /* renamed from: d */
        public final int mo6306d(Context context) {
            return gm.m7971a(context).f7932b.getInt("gcm.appVersion", Integer.MIN_VALUE);
        }

        /* renamed from: a */
        public final void mo6297a(Context context, int i) {
            C2991p.m8215a(gm.m7971a(context).f7932b, "gcm.appVersion", i);
        }

        /* renamed from: e */
        public final boolean mo6307e(Context context) {
            return gm.m7971a(context).f7932b.getBoolean("gcm.onServer", false);
        }

        /* renamed from: b */
        public final void mo6304b(Context context, boolean z) {
            gm.m7971a(context).m7973a(z);
        }

        /* renamed from: f */
        public final long mo6308f(Context context) {
            return gm.m7971a(context).f7932b.getLong("gcm.onServerExpirationTime", 0);
        }

        /* renamed from: a */
        public final void mo6298a(Context context, long j) {
            Editor edit = gm.m7971a(context).f7932b.edit();
            edit.putLong("gcm.onServerExpirationTime", j);
            edit.commit();
        }

        /* renamed from: g */
        public final int mo6309g(Context context) {
            return gm.m7971a(context).f7932b.getInt("gcm.backoff", 0);
        }

        /* renamed from: b */
        public final void mo6302b(Context context, int i) {
            C2991p.m8215a(gm.m7971a(context).f7932b, "gcm.backoff", i);
        }
    }

    /* renamed from: b */
    public static synchronized ge m7914b(Context context) {
        ge geVar;
        synchronized (ge.class) {
            if (f7890c == null) {
                f7890c = new ge(context);
            }
            geVar = f7890c;
        }
        return geVar;
    }

    private ge(Context context) {
        super(context, new C29461());
    }

    /* renamed from: e */
    final void mo6316e(String str) {
        if (str != null && str.length() > 0) {
            String[] strArr = new String[]{str};
            super.m7906a(this.f7887a);
            super.m7907a(strArr[0]);
        }
    }

    /* renamed from: a */
    protected final void mo6311a(Context context, String str) {
        new Object[1][0] = str;
        gc.m7832a(context).m7840a(str);
    }

    /* renamed from: b */
    protected final void mo6313b(String str) {
        new Object[1][0] = str;
        m7908a();
    }

    /* renamed from: a */
    protected final boolean mo6312a(Context context, Intent intent) {
        Object[] objArr = new Object[]{intent, intent.getExtras()};
        String stringExtra = intent.getStringExtra("fiverocks");
        if (stringExtra == null) {
            return false;
        }
        if (gf.m7923a(context).m7939f()) {
            gb gbVar = gc.m7832a(context).f7855g;
            C2879a a = gbVar.m7823a(eb.APP, "push_ignore");
            a.f7430s = new ef(null, null, stringExtra);
            gbVar.m7824a(a);
            return true;
        }
        String stringExtra2 = intent.getStringExtra("title");
        String stringExtra3 = intent.getStringExtra("message");
        if (stringExtra3 != null) {
            boolean z;
            Bundle extras = intent.getExtras();
            Object obj = extras.get("rich");
            Object obj2 = extras.get("sound");
            Object obj3 = extras.get("important");
            String string = extras.getString(MessengerShareContentUtility.ATTACHMENT_PAYLOAD);
            Object obj4 = extras.get("always");
            obj4 = ("true".equals(obj4) || Boolean.TRUE.equals(obj4)) ? 1 : null;
            Object obj5 = extras.get("repeatable");
            if ("true".equals(obj5) || Boolean.TRUE.equals(obj5)) {
                z = true;
            } else {
                z = false;
            }
            String string2 = extras.getString(VungleActivity.PLACEMENT_EXTRA);
            int b = m7913b(extras.get("nid"));
            if (!(obj4 == null && gc.m7832a(context).m7850d())) {
                String a2 = ct.m7337a(stringExtra2);
                boolean a3 = m7912a(obj);
                boolean a4 = m7912a(obj2);
                m7912a(obj3);
                Notification a5 = m7911a(context, stringExtra, a2, stringExtra3, a3, a4, string, string2, b);
                gc a6 = gc.m7832a(context);
                long currentTimeMillis = System.currentTimeMillis();
                a6.m7846b(context);
                if (a6.f7854f.m7930a(stringExtra, currentTimeMillis, z)) {
                    gbVar = a6.f7855g;
                    C2879a a7 = gbVar.m7823a(eb.APP, "push_show");
                    a7.f7430s = new ef(null, null, stringExtra);
                    gbVar.m7824a(a7);
                    obj4 = 1;
                } else {
                    obj4 = null;
                }
                if (obj4 != null) {
                    ((NotificationManager) context.getSystemService(NotificationTable.TABLE_NAME)).notify(b, a5);
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    private static boolean m7912a(Object obj) {
        return Boolean.TRUE.equals(obj) || "true".equals(obj);
    }

    /* renamed from: b */
    private static int m7913b(Object obj) {
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        if (obj instanceof String) {
            try {
                return Integer.parseInt((String) obj);
            } catch (NumberFormatException e) {
            }
        }
        return 0;
    }

    /* renamed from: a */
    protected final void mo6310a(int i) {
        new Object[1][0] = Integer.valueOf(i);
    }

    /* renamed from: c */
    protected final boolean mo6314c(String str) {
        new Object[1][0] = str;
        return true;
    }

    /* renamed from: d */
    protected final boolean mo6315d(String str) {
        new Object[1][0] = str;
        return false;
    }

    /* renamed from: a */
    private static Notification m7911a(Context context, String str, String str2, String str3, boolean z, boolean z2, String str4, String str5, int i) {
        Bitmap bitmap = null;
        Intent intent = new Intent(context.getApplicationContext(), TapjoyReceiver.class);
        intent.setAction("com.tapjoy.PUSH_CLICK");
        intent.putExtra("com.tapjoy.PUSH_ID", str);
        if (str4 != null) {
            intent.putExtra(Tapjoy.INTENT_EXTRA_PUSH_PAYLOAD, str4);
        }
        if (str5 != null) {
            intent.putExtra("com.tapjoy.PUSH_PLACEMENT", str5);
        }
        int i2 = 134217728;
        if (VERSION.SDK_INT == 19) {
            i2 = DriveFile.MODE_READ_ONLY;
        }
        PendingIntent broadcast = PendingIntent.getBroadcast(context.getApplicationContext(), i, intent, i2);
        if (broadcast == null) {
            return null;
        }
        PackageManager packageManager = context.getPackageManager();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(context.getPackageName(), 128);
            if (str2.length() == 0) {
                str2 = packageManager.getApplicationLabel(applicationInfo);
            } else if (z) {
                str2 = Html.fromHtml(str2);
            }
            if (z) {
                str3 = Html.fromHtml(str3);
            }
            i2 = m7910a(applicationInfo.metaData, "com.tapjoy.notification.icon", context);
            if (i2 == 0) {
                i2 = applicationInfo.icon != 0 ? applicationInfo.icon : 17301651;
            }
            int a = m7910a(applicationInfo.metaData, "com.tapjoy.notification.icon.large", context);
            if (a != 0) {
                bitmap = BitmapFactory.decodeResource(context.getResources(), a);
            }
            C2826d c2826d = new C2826d(context);
            c2826d.f7141r.icon = i2;
            c2826d.f7141r.tickerText = str2;
            c2826d.f7125b = str2;
            c2826d.f7126c = str3;
            c2826d.f7127d = broadcast;
            Notification notification = c2826d.f7141r;
            notification.flags |= 16;
            C2823l c2825c = new C2825c();
            c2825c.e = str2;
            c2825c.f7123a = str3;
            C2826d a2 = c2826d.m7133a(c2825c);
            if (z2) {
                a2.f7141r.defaults = 1;
            }
            if (bitmap != null) {
                a2.f7130g = bitmap;
            }
            return C2834a.f7143a.mo6174a(a2);
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    /* renamed from: a */
    private static int m7910a(Bundle bundle, String str, Context context) {
        if (bundle != null) {
            Object obj = bundle.get(str);
            if (obj instanceof Integer) {
                int intValue = ((Integer) obj).intValue();
                try {
                    if ("drawable".equals(context.getResources().getResourceTypeName(intValue))) {
                        return intValue;
                    }
                } catch (NotFoundException e) {
                }
            }
            if (obj != null) {
                String str2 = "meta-data of {} invalid";
                Object[] objArr = new Object[]{str};
                if (fz.f7823a) {
                    ac.m7144a(4, "Tapjoy", str2, objArr);
                }
            }
        }
        return 0;
    }
}
