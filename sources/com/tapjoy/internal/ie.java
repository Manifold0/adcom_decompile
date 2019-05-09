package com.tapjoy.internal;

import java.io.Closeable;
import java.io.Flushable;

public interface ie extends Closeable, Flushable {
    /* renamed from: a */
    void mo6341a(hu huVar, long j);

    void close();

    void flush();
}
