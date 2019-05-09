// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;
import android.app.AlarmManager;
import java.sql.Timestamp;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;

public abstract class r extends s
{
    public final Context a;
    public final t b;
    private boolean c;
    
    public r(final Context context, final t b) {
        this.c = false;
        this.a = context.getApplicationContext();
        this.b = b;
    }
    
    private static void b(final Context context, final Intent intent) {
        intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(context, 0, new Intent(), 0));
    }
    
    private boolean b() {
        final boolean e = this.b.e(this.a);
        if (e) {
            final long f = this.b.f(this.a);
            if (f != 0L && System.currentTimeMillis() > f) {
                new Timestamp(f);
                return false;
            }
        }
        return e;
    }
    
    private static boolean b(final Context context, final String s) {
        try {
            if (ae.b(context.getPackageManager(), "com.google.android.gms") < 3159130) {
                return false;
            }
            final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gms");
            b(context, intent);
            intent.putExtra("sender", s);
            if (context.startService(intent) != null) {
                return true;
            }
        }
        catch (RuntimeException ex) {
            return false;
        }
        return false;
    }
    
    private static boolean c(final Context context, final String s) {
        try {
            ae.b(context.getPackageManager(), "com.google.android.gsf");
            final Intent intent = new Intent("com.google.android.c2dm.intent.REGISTER");
            intent.setPackage("com.google.android.gsf");
            b(context, intent);
            intent.putExtra("sender", s);
            return context.startService(intent) != null;
        }
        catch (RuntimeException ex) {
            return false;
        }
    }
    
    private void e(final String s) {
        this.b.a(this.a, s);
        this.b.a(this.a, true);
        if ((this.c || !b(this.a, s)) && c(this.a, s)) {
            return;
        }
    }
    
    private void f(final String s) {
        while (true) {
            synchronized (this) {
                this.a(this.a);
                this.b.a(this.a, false);
                this.b.a(this.a, ae.a(this.a));
                if (!s.equals(this.b.b(this.a))) {
                    this.b.b(this.a, false);
                    this.b.b(this.a, s);
                    this.a(this.a, s);
                }
                else {
                    if (this.b()) {
                        return;
                    }
                    this.a(this.a, s);
                }
            }
        }
    }
    
    protected final void a(final Context context) {
        this.b.b(context, 3000);
    }
    
    protected final void a(final String s) {
        final String b = this.b.b(this.a);
        if (b.length() == 0) {
            this.e(s);
            return;
        }
        if (!s.equals(this.b.a(this.a))) {
            this.e(s);
            return;
        }
        final int d = this.b.d(this.a);
        final int a = ae.a(this.a);
        if (d != Integer.MIN_VALUE && d != a) {
            this.e(s);
            return;
        }
        if (this.b.c(this.a)) {
            this.e(s);
            return;
        }
        if (!this.b()) {
            this.a(this.a, b);
        }
    }
    
    public final boolean a() {
        final String a = this.b.a(this.a);
        if (!ct.c(a)) {
            this.a(a);
            return true;
        }
        return false;
    }
    
    public final boolean a(final Intent intent) {
        final String action = intent.getAction();
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            final String stringExtra = intent.getStringExtra("registration_id");
            final String stringExtra2 = intent.getStringExtra("unregistered");
            final String stringExtra3 = intent.getStringExtra("error");
            if (stringExtra != null) {
                if (stringExtra.length() > 0) {
                    if (stringExtra.startsWith("|")) {
                        return false;
                    }
                    this.f(stringExtra);
                    return false;
                }
                else if (stringExtra2 == null && stringExtra3 == null && !this.c) {
                    this.c = true;
                    this.a();
                    return true;
                }
            }
            if (stringExtra2 != null) {
                final String b = this.b.b(this.a);
                this.a(this.a);
                this.b.b(this.a, false);
                this.b.b(this.a, "");
                this.b.a(this.a, true);
                this.b(b);
                return false;
            }
            if (stringExtra3 != null) {
                boolean b2;
                if ("SERVICE_NOT_AVAILABLE".equals(stringExtra3)) {
                    b2 = this.c(stringExtra3);
                }
                else {
                    if (!this.c) {
                        this.c(stringExtra3);
                        this.c = true;
                        this.a();
                        return true;
                    }
                    b2 = this.d(stringExtra3);
                }
                if (b2) {
                    final int g = this.b.g(this.a);
                    final Intent intent2 = new Intent("com.google.android.gcm.intent.RETRY");
                    intent2.setPackage(this.a.getPackageName());
                    ((AlarmManager)this.a.getSystemService("alarm")).set(3, SystemClock.elapsedRealtime() + g, PendingIntent.getBroadcast(this.a, 0, intent2, 0));
                    if (g < 3600000) {
                        this.b.b(this.a, g * 2);
                    }
                }
                return true;
            }
        }
        else if ("com.google.android.c2dm.intent.RECEIVE".equals(action)) {
            final String stringExtra4 = intent.getStringExtra("message_type");
            if (stringExtra4 != null) {
                if ("deleted_messages".equals(stringExtra4)) {
                    final String stringExtra5 = intent.getStringExtra("total_deleted");
                    if (stringExtra5 == null) {
                        return false;
                    }
                    try {
                        this.a(Integer.parseInt(stringExtra5));
                        return false;
                    }
                    catch (NumberFormatException ex) {
                        return false;
                    }
                }
                return false;
            }
            return this.a(this.a, intent);
        }
        else {
            if (!"com.google.android.gcm.intent.RETRY".equals(action)) {
                return false;
            }
            final String package1 = intent.getPackage();
            if (package1 == null || !package1.equals(this.a.getPackageName())) {
                return false;
            }
            this.a();
            return true;
        }
        return false;
    }
}
