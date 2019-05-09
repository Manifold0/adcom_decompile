package com.tapjoy.internal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import com.adjust.sdk.Constants;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tapjoy.TapjoyConstants;
import java.sql.Timestamp;

/* renamed from: com.tapjoy.internal.r */
public abstract class C2948r extends C2947s {
    /* renamed from: a */
    public final Context f7887a;
    /* renamed from: b */
    public final C2945t f7888b;
    /* renamed from: c */
    private boolean f7889c = false;

    public C2948r(Context context, C2945t c2945t) {
        this.f7887a = context.getApplicationContext();
        this.f7888b = c2945t;
    }

    /* renamed from: b */
    private boolean m7901b() {
        boolean e = this.f7888b.mo6307e(this.f7887a);
        if (e) {
            long f = this.f7888b.mo6308f(this.f7887a);
            if (f != 0 && System.currentTimeMillis() > f) {
                new Object[1][0] = new Timestamp(f);
                return false;
            }
        }
        return e;
    }

    /* renamed from: a */
    public final boolean m7908a() {
        String a = this.f7888b.mo6296a(this.f7887a);
        if (ct.m7339c(a)) {
            return false;
        }
        m7907a(a);
        return true;
    }

    /* renamed from: a */
    protected final void m7907a(String str) {
        String b = this.f7888b.mo6301b(this.f7887a);
        if (b.length() == 0) {
            mo6316e(str);
            return;
        }
        if (str.equals(this.f7888b.mo6296a(this.f7887a))) {
            int d = this.f7888b.mo6306d(this.f7887a);
            int a = ae.m7147a(this.f7887a);
            if (d != Integer.MIN_VALUE && d != a) {
                Object[] objArr = new Object[]{Integer.valueOf(d), Integer.valueOf(a), b};
                mo6316e(str);
                return;
            } else if (this.f7888b.mo6305c(this.f7887a)) {
                new Object[1][0] = b;
                mo6316e(str);
                return;
            } else if (m7901b()) {
                r1 = new Object[]{str, b};
                return;
            } else {
                r1 = new Object[]{str, b};
                mo6311a(this.f7887a, b);
                return;
            }
        }
        Object[] objArr2 = new Object[]{this.f7888b.mo6296a(this.f7887a), str, b};
        mo6316e(str);
    }

    /* renamed from: e */
    private void mo6316e(String str) {
        this.f7888b.mo6299a(this.f7887a, str);
        this.f7888b.mo6300a(this.f7887a, true);
        if ((!this.f7889c && C2948r.m7902b(this.f7887a, str)) || !C2948r.m7903c(this.f7887a, str)) {
        }
    }

    /* renamed from: b */
    private static boolean m7902b(Context context, String str) {
        try {
            int b = ae.m7149b(context.getPackageManager(), "com.google.android.gms");
            if (b >= 3159130) {
                Object[] objArr = new Object[]{Integer.valueOf(b), str};
                Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
                intent.setPackage("com.google.android.gms");
                C2948r.m7900b(context, intent);
                intent.putExtra("sender", str);
                if (context.startService(intent) != null) {
                    return true;
                }
                return false;
            }
            new Object[1][0] = Integer.valueOf(b);
            return false;
        } catch (RuntimeException e) {
        }
    }

    /* renamed from: c */
    private static boolean m7903c(Context context, String str) {
        try {
            int b = ae.m7149b(context.getPackageManager(), "com.google.android.gsf");
            Object[] objArr = new Object[]{Integer.valueOf(b), str};
            Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gsf");
            C2948r.m7900b(context, intent);
            intent.putExtra("sender", str);
            if (context.startService(intent) != null) {
                return true;
            }
            return false;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /* renamed from: b */
    private static void m7900b(Context context, Intent intent) {
        intent.putExtra(TapjoyConstants.TJC_APP_PLACEMENT, PendingIntent.getBroadcast(context, 0, new Intent(), 0));
    }

    /* renamed from: a */
    protected final void m7906a(Context context) {
        this.f7888b.mo6302b(context, 3000);
    }

    /* renamed from: a */
    public final boolean m7909a(Intent intent) {
        String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            action = intent.getStringExtra("registration_id");
            String stringExtra = intent.getStringExtra("unregistered");
            String stringExtra2 = intent.getStringExtra("error");
            if (action != null) {
                if (action.length() > 0) {
                    if (action.startsWith("|")) {
                        return false;
                    }
                    m7905f(action);
                    return false;
                } else if (stringExtra == null && stringExtra2 == null && !this.f7889c) {
                    this.f7889c = true;
                    m7908a();
                    return true;
                }
            }
            if (stringExtra != null) {
                action = this.f7888b.mo6301b(this.f7887a);
                m7906a(this.f7887a);
                this.f7888b.mo6304b(this.f7887a, false);
                this.f7888b.mo6303b(this.f7887a, "");
                this.f7888b.mo6300a(this.f7887a, true);
                mo6313b(action);
                return false;
            } else if (stringExtra2 == null) {
                return false;
            } else {
                boolean c;
                if ("SERVICE_NOT_AVAILABLE".equals(stringExtra2)) {
                    c = mo6314c(stringExtra2);
                } else if (this.f7889c) {
                    c = mo6315d(stringExtra2);
                } else {
                    mo6314c(stringExtra2);
                    this.f7889c = true;
                    m7908a();
                    return true;
                }
                if (c) {
                    int g = this.f7888b.mo6309g(this.f7887a);
                    new Object[1][0] = Integer.valueOf(g);
                    Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                    intent2.setPackage(this.f7887a.getPackageName());
                    ((AlarmManager) this.f7887a.getSystemService(NotificationCompat.CATEGORY_ALARM)).set(3, SystemClock.elapsedRealtime() + ((long) g), PendingIntent.getBroadcast(this.f7887a, 0, intent2, 0));
                    if (g < Constants.ONE_HOUR) {
                        this.f7888b.mo6302b(this.f7887a, g * 2);
                    }
                }
                return true;
            }
        } else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            action = intent.getStringExtra("message_type");
            if (action == null) {
                return mo6312a(this.f7887a, intent);
            }
            if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(action)) {
                action = intent.getStringExtra("total_deleted");
                if (action == null) {
                    return false;
                }
                try {
                    int parseInt = Integer.parseInt(action);
                    new Object[1][0] = Integer.valueOf(parseInt);
                    mo6310a(parseInt);
                    return false;
                } catch (NumberFormatException e) {
                    new Object[1][0] = action;
                    return false;
                }
            }
            new Object[1][0] = action;
            return false;
        } else if ("com.google.android.gcm.intent.RETRY".equals(action)) {
            action = intent.getPackage();
            if (action == null || !action.equals(this.f7887a.getPackageName())) {
                new Object[1][0] = action;
                return false;
            }
            m7908a();
            return true;
        } else {
            new Object[1][0] = action;
            return false;
        }
    }

    /* renamed from: f */
    private synchronized void m7905f(String str) {
        m7906a(this.f7887a);
        this.f7888b.mo6300a(this.f7887a, false);
        this.f7888b.mo6297a(this.f7887a, ae.m7147a(this.f7887a));
        if (!str.equals(this.f7888b.mo6301b(this.f7887a))) {
            new Object[1][0] = str;
            this.f7888b.mo6304b(this.f7887a, false);
            this.f7888b.mo6303b(this.f7887a, str);
            mo6311a(this.f7887a, str);
        } else if (m7901b()) {
            new Object[1][0] = str;
        } else {
            new Object[1][0] = str;
            mo6311a(this.f7887a, str);
        }
    }
}
