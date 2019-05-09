package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zziw extends IOException {
    public zziw(String str) {
        super(str);
    }

    static zziw zzbk() {
        return new zziw("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    static zziw zzbl() {
        return new zziw("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static zziw zzbm() {
        return new zziw("CodedInputStream encountered a malformed varint.");
    }
}
