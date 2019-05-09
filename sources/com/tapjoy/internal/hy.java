package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Logger;

public final class hy {
    /* renamed from: a */
    static final Logger f8193a = Logger.getLogger(hy.class.getName());

    private hy() {
    }

    /* renamed from: a */
    public static hw m8142a(C2981if c2981if) {
        if (c2981if != null) {
            return new ia(c2981if);
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public static hv m8141a(ie ieVar) {
        if (ieVar != null) {
            return new hz(ieVar);
        }
        throw new IllegalArgumentException("sink == null");
    }

    /* renamed from: a */
    public static ie m8143a(final OutputStream outputStream) {
        final ig igVar = new ig();
        if (outputStream != null) {
            return new ie() {
                /* renamed from: a */
                public final void mo6341a(hu huVar, long j) {
                    ih.m8186a(huVar.f8183b, 0, j);
                    while (j > 0) {
                        igVar.mo6368a();
                        ib ibVar = huVar.f8182a;
                        int min = (int) Math.min(j, (long) (ibVar.f8205c - ibVar.f8204b));
                        outputStream.write(ibVar.f8203a, ibVar.f8204b, min);
                        ibVar.f8204b += min;
                        j -= (long) min;
                        huVar.f8183b -= (long) min;
                        if (ibVar.f8204b == ibVar.f8205c) {
                            huVar.f8182a = ibVar.m8167a();
                            ic.m8171a(ibVar);
                        }
                    }
                }

                public final void flush() {
                    outputStream.flush();
                }

                public final void close() {
                    outputStream.close();
                }

                public final String toString() {
                    return "sink(" + outputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("out == null");
    }

    /* renamed from: a */
    public static C2981if m8144a(final InputStream inputStream) {
        final ig igVar = new ig();
        if (inputStream != null) {
            return new C2981if() {
                /* renamed from: b */
                public final long mo6342b(hu huVar, long j) {
                    if (j < 0) {
                        throw new IllegalArgumentException("byteCount < 0: " + j);
                    } else if (j == 0) {
                        return 0;
                    } else {
                        try {
                            igVar.mo6368a();
                            ib c = huVar.m8119c(1);
                            int read = inputStream.read(c.f8203a, c.f8205c, (int) Math.min(j, (long) (8192 - c.f8205c)));
                            if (read == -1) {
                                return -1;
                            }
                            c.f8205c += read;
                            huVar.f8183b += (long) read;
                            return (long) read;
                        } catch (AssertionError e) {
                            if (hy.m8145a(e)) {
                                throw new IOException(e);
                            }
                            throw e;
                        }
                    }
                }

                public final void close() {
                    inputStream.close();
                }

                public final String toString() {
                    return "source(" + inputStream + ")";
                }
            };
        }
        throw new IllegalArgumentException("in == null");
    }

    /* renamed from: a */
    static boolean m8145a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }
}
