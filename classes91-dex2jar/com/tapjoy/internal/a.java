// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Iterator;
import android.app.Notification$BigPictureStyle;
import android.app.Notification$BigTextStyle;
import android.app.Notification$Builder;
import android.app.Notification;
import java.util.ArrayList;
import android.widget.RemoteViews;
import android.content.Context;
import android.graphics.Bitmap;
import android.app.PendingIntent;
import android.os.Build$VERSION;

public final class a
{
    private static final f a;
    
    static {
        if (Build$VERSION.SDK_INT >= 16) {
            a = (f)new k();
            return;
        }
        if (Build$VERSION.SDK_INT >= 14) {
            a = (f)new j();
            return;
        }
        if (Build$VERSION.SDK_INT >= 11) {
            a = (f)new i();
            return;
        }
        if (Build$VERSION.SDK_INT >= 9) {
            a = (f)new h();
            return;
        }
        a = (f)new g();
    }
    
    public static final class a
    {
        public int a;
        public CharSequence b;
        public PendingIntent c;
    }
    
    public static final class b extends l
    {
        Bitmap a;
        Bitmap b;
        boolean c;
    }
    
    public static final class c extends l
    {
        public CharSequence a;
    }
    
    public static final class d
    {
        Context a;
        public CharSequence b;
        public CharSequence c;
        public PendingIntent d;
        PendingIntent e;
        RemoteViews f;
        public Bitmap g;
        CharSequence h;
        int i;
        int j;
        boolean k;
        l l;
        CharSequence m;
        int n;
        int o;
        boolean p;
        ArrayList q;
        public Notification r;
        
        public d(final Context a) {
            this.q = new ArrayList();
            this.r = new Notification();
            this.a = a;
            this.r.when = System.currentTimeMillis();
            this.r.audioStreamType = -1;
            this.j = 0;
        }
        
        public final d a(l l) {
            if (this.l != l) {
                this.l = l;
                if (this.l != null) {
                    l = this.l;
                    if (l.d != this) {
                        l.d = this;
                        if (l.d != null) {
                            l.d.a(l);
                        }
                    }
                }
            }
            return this;
        }
    }
    
    public static final class e extends l
    {
        ArrayList a;
        
        public e() {
            this.a = new ArrayList();
        }
    }
    
    public interface f
    {
        Notification a(final d p0);
    }
    
    static class g implements f
    {
        @Override
        public Notification a(final d d) {
            final Notification r = d.r;
            r.setLatestEventInfo(d.a, d.b, d.c, d.d);
            if (d.j > 0) {
                r.flags |= 0x80;
            }
            return r;
        }
    }
    
    static final class h extends g
    {
        @Override
        public final Notification a(final d d) {
            final Notification r = d.r;
            r.setLatestEventInfo(d.a, d.b, d.c, d.d);
            final Context a = d.a;
            final CharSequence b = d.b;
            final CharSequence c = d.c;
            final PendingIntent d2 = d.d;
            final PendingIntent e = d.e;
            r.setLatestEventInfo(a, b, c, d2);
            r.fullScreenIntent = e;
            if (d.j > 0) {
                r.flags |= 0x80;
            }
            return r;
        }
    }
    
    static final class i implements f
    {
        @Override
        public final Notification a(final d d) {
            final Context a = d.a;
            final Notification r = d.r;
            return new Notification$Builder(a).setWhen(r.when).setSmallIcon(r.icon, r.iconLevel).setContent(r.contentView).setTicker(r.tickerText, d.f).setSound(r.sound, r.audioStreamType).setVibrate(r.vibrate).setLights(r.ledARGB, r.ledOnMS, r.ledOffMS).setOngoing((r.flags & 0x2) != 0x0).setOnlyAlertOnce((r.flags & 0x8) != 0x0).setAutoCancel((r.flags & 0x10) != 0x0).setDefaults(r.defaults).setContentTitle(d.b).setContentText(d.c).setContentInfo(d.h).setContentIntent(d.d).setDeleteIntent(r.deleteIntent).setFullScreenIntent(d.e, (r.flags & 0x80) != 0x0).setLargeIcon(d.g).setNumber(d.i).getNotification();
        }
    }
    
    static final class j implements f
    {
        @Override
        public final Notification a(final d d) {
            final Context a = d.a;
            final Notification r = d.r;
            return new Notification$Builder(a).setWhen(r.when).setSmallIcon(r.icon, r.iconLevel).setContent(r.contentView).setTicker(r.tickerText, d.f).setSound(r.sound, r.audioStreamType).setVibrate(r.vibrate).setLights(r.ledARGB, r.ledOnMS, r.ledOffMS).setOngoing((r.flags & 0x2) != 0x0).setOnlyAlertOnce((r.flags & 0x8) != 0x0).setAutoCancel((r.flags & 0x10) != 0x0).setDefaults(r.defaults).setContentTitle(d.b).setContentText(d.c).setContentInfo(d.h).setContentIntent(d.d).setDeleteIntent(r.deleteIntent).setFullScreenIntent(d.e, (r.flags & 0x80) != 0x0).setLargeIcon(d.g).setNumber(d.i).setProgress(d.n, d.o, d.p).getNotification();
        }
    }
    
    static final class k implements f
    {
        @Override
        public final Notification a(final d d) {
            final com.tapjoy.internal.b b = new com.tapjoy.internal.b(d.a, d.r, d.b, d.c, d.h, d.f, d.i, d.d, d.e, d.g, d.n, d.o, d.p, d.k, d.j, d.m);
            for (final a a : d.q) {
                b.a.addAction(a.a, a.b, a.c);
            }
            if (d.l != null) {
                if (d.l instanceof c) {
                    final c c = (c)d.l;
                    final CharSequence e = c.e;
                    final boolean g = c.g;
                    final CharSequence f = c.f;
                    final Notification$BigTextStyle bigText = new Notification$BigTextStyle(b.a).setBigContentTitle(e).bigText(c.a);
                    if (g) {
                        bigText.setSummaryText(f);
                    }
                }
                else if (d.l instanceof e) {
                    final e e2 = (e)d.l;
                    b.a(e2.e, e2.g, e2.f, e2.a);
                }
                else if (d.l instanceof b) {
                    final b b2 = (b)d.l;
                    final CharSequence e3 = b2.e;
                    final boolean g2 = b2.g;
                    final CharSequence f2 = b2.f;
                    final Bitmap a2 = b2.a;
                    final Bitmap b3 = b2.b;
                    final boolean c2 = b2.c;
                    final Notification$BigPictureStyle bigPicture = new Notification$BigPictureStyle(b.a).setBigContentTitle(e3).bigPicture(a2);
                    if (c2) {
                        bigPicture.bigLargeIcon(b3);
                    }
                    if (g2) {
                        bigPicture.setSummaryText(f2);
                    }
                }
            }
            return b.a.build();
        }
    }
    
    public abstract static class l
    {
        d d;
        public CharSequence e;
        CharSequence f;
        boolean g;
        
        public l() {
            this.g = false;
        }
    }
}
