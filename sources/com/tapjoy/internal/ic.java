package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;

final class ic {
    /* renamed from: a */
    static ib f8210a;
    /* renamed from: b */
    static long f8211b;

    private ic() {
    }

    /* renamed from: a */
    static ib m8170a() {
        synchronized (ic.class) {
            if (f8210a != null) {
                ib ibVar = f8210a;
                f8210a = ibVar.f8208f;
                ibVar.f8208f = null;
                f8211b -= PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                return ibVar;
            }
            return new ib();
        }
    }

    /* renamed from: a */
    static void m8171a(ib ibVar) {
        if (ibVar.f8208f != null || ibVar.f8209g != null) {
            throw new IllegalArgumentException();
        } else if (!ibVar.f8206d) {
            synchronized (ic.class) {
                if (f8211b + PlaybackStateCompat.ACTION_PLAY_FROM_URI > PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                    return;
                }
                f8211b += PlaybackStateCompat.ACTION_PLAY_FROM_URI;
                ibVar.f8208f = f8210a;
                ibVar.f8205c = 0;
                ibVar.f8204b = 0;
                f8210a = ibVar;
            }
        }
    }
}
