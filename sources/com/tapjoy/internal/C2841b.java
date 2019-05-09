package com.tapjoy.internal;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.Notification.InboxStyle;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.RemoteViews;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.tapjoy.internal.b */
final class C2841b {
    /* renamed from: a */
    Builder f7221a;

    public C2841b(Context context, Notification notification, CharSequence charSequence, CharSequence charSequence2, CharSequence charSequence3, RemoteViews remoteViews, int i, PendingIntent pendingIntent, PendingIntent pendingIntent2, Bitmap bitmap, int i2, int i3, boolean z, boolean z2, int i4, CharSequence charSequence4) {
        boolean z3;
        Builder lights = new Builder(context).setWhen(notification.when).setSmallIcon(notification.icon, notification.iconLevel).setContent(notification.contentView).setTicker(notification.tickerText, remoteViews).setSound(notification.sound, notification.audioStreamType).setVibrate(notification.vibrate).setLights(notification.ledARGB, notification.ledOnMS, notification.ledOffMS);
        if ((notification.flags & 2) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        lights = lights.setOngoing(z3);
        if ((notification.flags & 8) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        lights = lights.setOnlyAlertOnce(z3);
        if ((notification.flags & 16) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        lights = lights.setAutoCancel(z3).setDefaults(notification.defaults).setContentTitle(charSequence).setContentText(charSequence2).setSubText(charSequence4).setContentInfo(charSequence3).setContentIntent(pendingIntent).setDeleteIntent(notification.deleteIntent);
        if ((notification.flags & 128) != 0) {
            z3 = true;
        } else {
            z3 = false;
        }
        this.f7221a = lights.setFullScreenIntent(pendingIntent2, z3).setLargeIcon(bitmap).setNumber(i).setUsesChronometer(z2).setPriority(i4).setProgress(i2, i3, z);
    }

    /* renamed from: a */
    public final void m7189a(CharSequence charSequence, boolean z, CharSequence charSequence2, ArrayList arrayList) {
        InboxStyle bigContentTitle = new InboxStyle(this.f7221a).setBigContentTitle(charSequence);
        if (z) {
            bigContentTitle.setSummaryText(charSequence2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            bigContentTitle.addLine((CharSequence) it.next());
        }
    }
}
