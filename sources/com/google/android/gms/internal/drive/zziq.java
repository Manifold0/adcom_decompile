package com.google.android.gms.internal.drive;

import java.io.IOException;

public final class zziq extends IOException {
    zziq(int i, int i2) {
        super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
    }
}
