package com.tapjoy.internal;

import android.app.Notification;
import android.app.Notification.BigPictureStyle;
import android.app.Notification.BigTextStyle;
import android.app.Notification.Builder;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.tapjoy.internal.a */
public final class C2834a {
    /* renamed from: a */
    private static final C2828f f7143a;

    /* renamed from: com.tapjoy.internal.a$a */
    public static class C2822a {
        /* renamed from: a */
        public int f7113a;
        /* renamed from: b */
        public CharSequence f7114b;
        /* renamed from: c */
        public PendingIntent f7115c;
    }

    /* renamed from: com.tapjoy.internal.a$l */
    public static abstract class C2823l {
        /* renamed from: d */
        C2826d f7116d;
        /* renamed from: e */
        public CharSequence f7117e;
        /* renamed from: f */
        CharSequence f7118f;
        /* renamed from: g */
        boolean f7119g = false;
    }

    /* renamed from: com.tapjoy.internal.a$b */
    public static class C2824b extends C2823l {
        /* renamed from: a */
        Bitmap f7120a;
        /* renamed from: b */
        Bitmap f7121b;
        /* renamed from: c */
        boolean f7122c;
    }

    /* renamed from: com.tapjoy.internal.a$c */
    public static class C2825c extends C2823l {
        /* renamed from: a */
        public CharSequence f7123a;
    }

    /* renamed from: com.tapjoy.internal.a$d */
    public static class C2826d {
        /* renamed from: a */
        Context f7124a;
        /* renamed from: b */
        public CharSequence f7125b;
        /* renamed from: c */
        public CharSequence f7126c;
        /* renamed from: d */
        public PendingIntent f7127d;
        /* renamed from: e */
        PendingIntent f7128e;
        /* renamed from: f */
        RemoteViews f7129f;
        /* renamed from: g */
        public Bitmap f7130g;
        /* renamed from: h */
        CharSequence f7131h;
        /* renamed from: i */
        int f7132i;
        /* renamed from: j */
        int f7133j;
        /* renamed from: k */
        boolean f7134k;
        /* renamed from: l */
        C2823l f7135l;
        /* renamed from: m */
        CharSequence f7136m;
        /* renamed from: n */
        int f7137n;
        /* renamed from: o */
        int f7138o;
        /* renamed from: p */
        boolean f7139p;
        /* renamed from: q */
        ArrayList f7140q = new ArrayList();
        /* renamed from: r */
        public Notification f7141r = new Notification();

        public C2826d(Context context) {
            this.f7124a = context;
            this.f7141r.when = System.currentTimeMillis();
            this.f7141r.audioStreamType = -1;
            this.f7133j = 0;
        }

        /* renamed from: a */
        public final C2826d m7133a(C2823l c2823l) {
            if (this.f7135l != c2823l) {
                this.f7135l = c2823l;
                if (this.f7135l != null) {
                    C2823l c2823l2 = this.f7135l;
                    if (c2823l2.f7116d != this) {
                        c2823l2.f7116d = this;
                        if (c2823l2.f7116d != null) {
                            c2823l2.f7116d.m7133a(c2823l2);
                        }
                    }
                }
            }
            return this;
        }
    }

    /* renamed from: com.tapjoy.internal.a$e */
    public static class C2827e extends C2823l {
        /* renamed from: a */
        ArrayList f7142a = new ArrayList();
    }

    /* renamed from: com.tapjoy.internal.a$f */
    public interface C2828f {
        /* renamed from: a */
        Notification mo6174a(C2826d c2826d);
    }

    /* renamed from: com.tapjoy.internal.a$g */
    static class C2829g implements C2828f {
        C2829g() {
        }

        /* renamed from: a */
        public Notification mo6174a(C2826d c2826d) {
            Notification notification = c2826d.f7141r;
            notification.setLatestEventInfo(c2826d.f7124a, c2826d.f7125b, c2826d.f7126c, c2826d.f7127d);
            if (c2826d.f7133j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    /* renamed from: com.tapjoy.internal.a$h */
    static class C2830h extends C2829g {
        C2830h() {
        }

        /* renamed from: a */
        public final Notification mo6174a(C2826d c2826d) {
            Notification notification = c2826d.f7141r;
            notification.setLatestEventInfo(c2826d.f7124a, c2826d.f7125b, c2826d.f7126c, c2826d.f7127d);
            Context context = c2826d.f7124a;
            CharSequence charSequence = c2826d.f7125b;
            CharSequence charSequence2 = c2826d.f7126c;
            PendingIntent pendingIntent = c2826d.f7127d;
            PendingIntent pendingIntent2 = c2826d.f7128e;
            notification.setLatestEventInfo(context, charSequence, charSequence2, pendingIntent);
            notification.fullScreenIntent = pendingIntent2;
            if (c2826d.f7133j > 0) {
                notification.flags |= 128;
            }
            return notification;
        }
    }

    /* renamed from: com.tapjoy.internal.a$i */
    static class C2831i implements C2828f {
        C2831i() {
        }

        /* renamed from: a */
        public final Notification mo6174a(C2826d c2826d) {
            boolean z;
            Context context = c2826d.f7124a;
            Notification notification = c2826d.f7141r;
            CharSequence charSequence = c2826d.f7125b;
            CharSequence charSequence2 = c2826d.f7126c;
            CharSequence charSequence3 = c2826d.f7131h;
            RemoteViews remoteViews = c2826d.f7129f;
            int i = c2826d.f7132i;
            PendingIntent pendingIntent = c2826d.f7127d;
            PendingIntent pendingIntent2 = c2826d.f7128e;
            Bitmap bitmap = c2826d.f7130g;
            Builder lights = new Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOngoing(z);
            if ((notification.flags & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOnlyAlertOnce(z);
            if ((notification.flags & 16) != 0) {
                z = true;
            } else {
                z = false;
            }
            Builder deleteIntent = lights.setAutoCancel(z).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z = true;
            } else {
                z = false;
            }
            return deleteIntent.setFullScreenIntent(pendingIntent2, z).setLargeIcon(bitmap).setNumber(i).getNotification();
        }
    }

    /* renamed from: com.tapjoy.internal.a$j */
    static class C2832j implements C2828f {
        C2832j() {
        }

        /* renamed from: a */
        public final Notification mo6174a(C2826d c2826d) {
            boolean z;
            Context context = c2826d.f7124a;
            Notification notification = c2826d.f7141r;
            CharSequence charSequence = c2826d.f7125b;
            CharSequence charSequence2 = c2826d.f7126c;
            CharSequence charSequence3 = c2826d.f7131h;
            RemoteViews remoteViews = c2826d.f7129f;
            int i = c2826d.f7132i;
            PendingIntent pendingIntent = c2826d.f7127d;
            PendingIntent pendingIntent2 = c2826d.f7128e;
            Bitmap bitmap = c2826d.f7130g;
            int i2 = c2826d.f7137n;
            int i3 = c2826d.f7138o;
            boolean z2 = c2826d.f7139p;
            Builder lights = new Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
            if ((notification.flags & 2) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOngoing(z);
            if ((notification.flags & 8) != 0) {
                z = true;
            } else {
                z = false;
            }
            lights = lights.setOnlyAlertOnce(z);
            if ((notification.flags & 16) != 0) {
                z = true;
            } else {
                z = false;
            }
            Builder deleteIntent = lights.setAutoCancel(z).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
            if ((notification.flags & 128) != 0) {
                z = true;
            } else {
                z = false;
            }
            return deleteIntent.setFullScreenIntent(pendingIntent2, z).setLargeIcon(bitmap).setNumber(i).setProgress(i2, i3, z2).getNotification();
        }
    }

    /* renamed from: com.tapjoy.internal.a$k */
    static class C2833k implements C2828f {
        C2833k() {
        }

        /* renamed from: a */
        public final Notification mo6174a(C2826d c2826d) {
            C2841b c2841b = new C2841b(c2826d.f7124a, c2826d.f7141r, c2826d.f7125b, c2826d.f7126c, c2826d.f7131h, c2826d.f7129f, c2826d.f7132i, c2826d.f7127d, c2826d.f7128e, c2826d.f7130g, c2826d.f7137n, c2826d.f7138o, c2826d.f7139p, c2826d.f7134k, c2826d.f7133j, c2826d.f7136m);
            Iterator it = c2826d.f7140q.iterator();
            while (it.hasNext()) {
                C2822a c2822a = (C2822a) it.next();
                c2841b.f7221a.addAction(c2822a.f7113a, c2822a.f7114b, c2822a.f7115c);
            }
            if (c2826d.f7135l != null) {
                CharSequence charSequence;
                boolean z;
                CharSequence charSequence2;
                if (c2826d.f7135l instanceof C2825c) {
                    C2825c c2825c = (C2825c) c2826d.f7135l;
                    charSequence = c2825c.e;
                    z = c2825c.g;
                    charSequence2 = c2825c.f;
                    BigTextStyle bigText = new BigTextStyle(c2841b.f7221a).setBigContentTitle(charSequence).bigText(c2825c.f7123a);
                    if (z) {
                        bigText.setSummaryText(charSequence2);
                    }
                } else if (c2826d.f7135l instanceof C2827e) {
                    C2827e c2827e = (C2827e) c2826d.f7135l;
                    c2841b.m7189a(c2827e.e, c2827e.g, c2827e.f, c2827e.f7142a);
                } else if (c2826d.f7135l instanceof C2824b) {
                    C2824b c2824b = (C2824b) c2826d.f7135l;
                    charSequence = c2824b.e;
                    z = c2824b.g;
                    charSequence2 = c2824b.f;
                    Bitmap bitmap = c2824b.f7120a;
                    Bitmap bitmap2 = c2824b.f7121b;
                    boolean z2 = c2824b.f7122c;
                    BigPictureStyle bigPicture = new BigPictureStyle(c2841b.f7221a).setBigContentTitle(charSequence).bigPicture(bitmap);
                    if (z2) {
                        bigPicture.bigLargeIcon(bitmap2);
                    }
                    if (z) {
                        bigPicture.setSummaryText(charSequence2);
                    }
                }
            }
            return c2841b.f7221a.build();
        }
    }

    static {
        if (VERSION.SDK_INT >= 16) {
            f7143a = new C2833k();
        } else if (VERSION.SDK_INT >= 14) {
            f7143a = new C2832j();
        } else if (VERSION.SDK_INT >= 11) {
            f7143a = new C2831i();
        } else if (VERSION.SDK_INT >= 9) {
            f7143a = new C2830h();
        } else {
            f7143a = new C2829g();
        }
    }
}
