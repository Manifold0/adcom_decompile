// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.app.NotificationManager;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.content.pm.PackageManager$NameNotFoundException;
import android.graphics.BitmapFactory;
import android.text.Html;
import android.app.PendingIntent;
import android.os.Build$VERSION;
import android.content.Intent;
import com.tapjoy.TapjoyReceiver;
import android.app.Notification;
import android.content.res.Resources$NotFoundException;
import android.os.Bundle;
import android.content.SharedPreferences$Editor;
import android.content.Context;

public final class ge extends r
{
    private static ge c;
    
    private ge(final Context context) {
        super(context, new t() {
            @Override
            public final String a(final Context context) {
                return gm.a(context).b.getString("gcm.senderIds", "");
            }
            
            @Override
            public final void a(final Context context, final int n) {
                p.a(gm.a(context).b, "gcm.appVersion", n);
            }
            
            @Override
            public final void a(final Context context, final long n) {
                final SharedPreferences$Editor edit = gm.a(context).b.edit();
                edit.putLong("gcm.onServerExpirationTime", n);
                edit.commit();
            }
            
            @Override
            public final void a(final Context context, final String s) {
                p.a(gm.a(context).b, "gcm.senderIds", s);
            }
            
            @Override
            public final void a(final Context context, final boolean b) {
                p.a(gm.a(context).b, "gcm.stale", b);
            }
            
            @Override
            public final String b(final Context context) {
                return gm.a(context).b.getString("gcm.regId", "");
            }
            
            @Override
            public final void b(final Context context, final int n) {
                p.a(gm.a(context).b, "gcm.backoff", n);
            }
            
            @Override
            public final void b(final Context context, final String s) {
                p.a(gm.a(context).b, "gcm.regId", s);
            }
            
            @Override
            public final void b(final Context context, final boolean b) {
                gm.a(context).a(b);
            }
            
            @Override
            public final boolean c(final Context context) {
                return gm.a(context).b.getBoolean("gcm.stale", true);
            }
            
            @Override
            public final int d(final Context context) {
                return gm.a(context).b.getInt("gcm.appVersion", Integer.MIN_VALUE);
            }
            
            @Override
            public final boolean e(final Context context) {
                return gm.a(context).b.getBoolean("gcm.onServer", false);
            }
            
            @Override
            public final long f(final Context context) {
                return gm.a(context).b.getLong("gcm.onServerExpirationTime", 0L);
            }
            
            @Override
            public final int g(final Context context) {
                return gm.a(context).b.getInt("gcm.backoff", 0);
            }
        });
    }
    
    private static int a(Bundle value, final String s, final Context context) {
        if (value != null) {
            value = (Bundle)value.get(s);
            if (value instanceof Integer) {
                final int intValue = (int)value;
                try {
                    if ("drawable".equals(context.getResources().getResourceTypeName(intValue))) {
                        return intValue;
                    }
                }
                catch (Resources$NotFoundException ex) {}
            }
            if (value != null && fz.a) {
                ac.a(4, "Tapjoy", "meta-data of {} invalid", s);
            }
        }
        return 0;
    }
    
    private static Notification a(final Context context, String e, String fromHtml, final String s, final boolean b, final boolean b2, final String s2, String packageName, int icon) {
        final Bitmap bitmap = null;
        final Intent intent = new Intent(context.getApplicationContext(), (Class)TapjoyReceiver.class);
        intent.setAction("com.tapjoy.PUSH_CLICK");
        intent.putExtra("com.tapjoy.PUSH_ID", e);
        if (s2 != null) {
            intent.putExtra("com.tapjoy.PUSH_PAYLOAD", s2);
        }
        if (packageName != null) {
            intent.putExtra("com.tapjoy.PUSH_PLACEMENT", packageName);
        }
        int n = 134217728;
        if (Build$VERSION.SDK_INT == 19) {
            n = 268435456;
        }
        final PendingIntent broadcast = PendingIntent.getBroadcast(context.getApplicationContext(), icon, intent, n);
        if (broadcast == null) {
            return null;
        }
        final PackageManager packageManager = context.getPackageManager();
        packageName = context.getPackageName();
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 128);
            if (((String)fromHtml).length() == 0) {
                e = (String)packageManager.getApplicationLabel(applicationInfo);
            }
            else {
                e = (String)fromHtml;
                if (b) {
                    e = (String)Html.fromHtml((String)fromHtml);
                }
            }
            fromHtml = s;
            if (b) {
                fromHtml = Html.fromHtml(s);
            }
            if ((icon = a(applicationInfo.metaData, "com.tapjoy.notification.icon", context)) == 0) {
                if (applicationInfo.icon != 0) {
                    icon = applicationInfo.icon;
                }
                else {
                    icon = 17301651;
                }
            }
            final int a = a(applicationInfo.metaData, "com.tapjoy.notification.icon.large", context);
            Bitmap decodeResource = bitmap;
            if (a != 0) {
                decodeResource = BitmapFactory.decodeResource(context.getResources(), a);
            }
            final a.d d = new a.d(context);
            d.r.icon = icon;
            d.r.tickerText = e;
            d.b = e;
            d.c = (CharSequence)fromHtml;
            d.d = broadcast;
            final Notification r = d.r;
            r.flags |= 0x10;
            final a.c c = new a.c();
            c.e = e;
            c.a = (CharSequence)fromHtml;
            final a.d a2 = d.a(c);
            if (b2) {
                a2.r.defaults = 1;
            }
            if (decodeResource != null) {
                a2.g = decodeResource;
            }
            return com.tapjoy.internal.a.a().a(a2);
        }
        catch (PackageManager$NameNotFoundException ex) {
            return null;
        }
    }
    
    private static boolean a(final Object o) {
        return Boolean.TRUE.equals(o) || "true".equals(o);
    }
    
    private static int b(final Object o) {
        if (o instanceof Number) {
            return ((Number)o).intValue();
        }
        if (o instanceof String) {
            try {
                return Integer.parseInt((String)o);
            }
            catch (NumberFormatException ex) {}
        }
        return 0;
    }
    
    public static ge b(final Context context) {
        synchronized (ge.class) {
            if (ge.c == null) {
                ge.c = new ge(context);
            }
            return ge.c;
        }
    }
    
    protected final void a(final int n) {
    }
    
    protected final void a(final Context context, final String s) {
        gc.a(context).a(s);
    }
    
    protected final boolean a(final Context context, final Intent intent) {
        intent.getExtras();
        final String stringExtra = intent.getStringExtra("fiverocks");
        if (stringExtra == null) {
            return false;
        }
        if (gf.a(context).f()) {
            final gb g = gc.a(context).g;
            final dy.a a = g.a(eb.APP, "push_ignore");
            a.s = new ef(null, null, stringExtra);
            g.a(a);
            return true;
        }
        final String stringExtra2 = intent.getStringExtra("title");
        final String stringExtra3 = intent.getStringExtra("message");
        if (stringExtra3 != null) {
            final Bundle extras = intent.getExtras();
            final Object value = extras.get("rich");
            final Object value2 = extras.get("sound");
            final Object value3 = extras.get("important");
            final String string = extras.getString("payload");
            final Object value4 = extras.get("always");
            boolean b;
            if ("true".equals(value4) || Boolean.TRUE.equals(value4)) {
                b = true;
            }
            else {
                b = false;
            }
            final Object value5 = extras.get("repeatable");
            final boolean b2 = "true".equals(value5) || Boolean.TRUE.equals(value5);
            final String string2 = extras.getString("placement");
            final int b3 = b(extras.get("nid"));
            if (b || !gc.a(context).d()) {
                final String a2 = ct.a(stringExtra2);
                final boolean a3 = a(value);
                final boolean a4 = a(value2);
                a(value3);
                final Notification a5 = a(context, stringExtra, a2, stringExtra3, a3, a4, string, string2, b3);
                final gc a6 = gc.a(context);
                final long currentTimeMillis = System.currentTimeMillis();
                a6.b(context);
                int n;
                if (a6.f.a(stringExtra, currentTimeMillis, b2)) {
                    final gb g2 = a6.g;
                    final dy.a a7 = g2.a(eb.APP, "push_show");
                    a7.s = new ef(null, null, stringExtra);
                    g2.a(a7);
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    ((NotificationManager)context.getSystemService("notification")).notify(b3, a5);
                }
            }
        }
        return true;
    }
    
    protected final void b(final String s) {
        this.a();
    }
    
    protected final boolean c(final String s) {
        return true;
    }
    
    protected final boolean d(final String s) {
        return false;
    }
    
    final void e(final String s) {
        if (s != null && s.length() > 0) {
            super.a(super.a);
            super.a((new String[] { s })[0]);
        }
    }
}
