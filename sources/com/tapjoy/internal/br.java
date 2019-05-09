package com.tapjoy.internal;

import java.io.Writer;

public final class br implements bq {
    /* renamed from: a */
    public final String f7231a;

    public br(String str) {
        this.f7231a = str;
    }

    /* renamed from: a */
    public final void mo6184a(Writer writer) {
        writer.write(this.f7231a);
    }

    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof br)) {
            return false;
        }
        return this.f7231a.equals(((br) o).f7231a);
    }

    public final int hashCode() {
        return this.f7231a.hashCode();
    }

    public final String toString() {
        return this.f7231a;
    }
}
