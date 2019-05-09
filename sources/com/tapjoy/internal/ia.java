package com.tapjoy.internal;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;

final class ia implements hw {
    /* renamed from: a */
    public final hu f8200a = new hu();
    /* renamed from: b */
    public final C2981if f8201b;
    /* renamed from: c */
    boolean f8202c;

    ia(C2981if c2981if) {
        if (c2981if == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f8201b = c2981if;
    }

    /* renamed from: b */
    public final long mo6342b(hu huVar, long j) {
        if (huVar == null) {
            throw new IllegalArgumentException("sink == null");
        } else if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f8202c) {
            throw new IllegalStateException("closed");
        } else if (this.f8200a.f8183b == 0 && this.f8201b.mo6342b(this.f8200a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        } else {
            return this.f8200a.mo6342b(huVar, Math.min(j, this.f8200a.f8183b));
        }
    }

    /* renamed from: b */
    public final boolean mo6346b() {
        if (!this.f8202c) {
            return this.f8200a.mo6346b() && this.f8201b.mo6342b(this.f8200a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
        } else {
            throw new IllegalStateException("closed");
        }
    }

    /* renamed from: c */
    public final byte mo6347c() {
        mo6340a(1);
        return this.f8200a.mo6347c();
    }

    /* renamed from: b */
    public final hx mo6345b(long j) {
        mo6340a(j);
        return this.f8200a.mo6345b(j);
    }

    /* renamed from: c */
    public final String mo6348c(long j) {
        mo6340a(j);
        return this.f8200a.mo6348c(j);
    }

    /* renamed from: e */
    public final int mo6352e() {
        mo6340a(4);
        return ih.m8184a(this.f8200a.m8121d());
    }

    /* renamed from: f */
    public final long mo6354f() {
        mo6340a(8);
        return this.f8200a.mo6354f();
    }

    /* renamed from: d */
    public final void mo6351d(long j) {
        if (this.f8202c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.f8200a.f8183b == 0 && this.f8201b.mo6342b(this.f8200a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long min = Math.min(j, this.f8200a.f8183b);
            this.f8200a.mo6351d(min);
            j -= min;
        }
    }

    public final void close() {
        if (!this.f8202c) {
            this.f8202c = true;
            this.f8201b.close();
            hu huVar = this.f8200a;
            try {
                huVar.mo6351d(huVar.f8183b);
            } catch (EOFException e) {
                throw new AssertionError(e);
            }
        }
    }

    public final String toString() {
        return "buffer(" + this.f8201b + ")";
    }

    /* renamed from: a */
    public final void mo6340a(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        } else if (this.f8202c) {
            throw new IllegalStateException("closed");
        } else {
            Object obj;
            while (this.f8200a.f8183b < j) {
                if (this.f8201b.mo6342b(this.f8200a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    obj = null;
                    break;
                }
            }
            obj = 1;
            if (obj == null) {
                throw new EOFException();
            }
        }
    }
}
