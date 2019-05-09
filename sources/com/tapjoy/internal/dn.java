package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

public abstract class dn {
    /* renamed from: c */
    public static final dn f7336c = new dn(dk.VARINT, Boolean.class) {
        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            int d = c2874do.m7459d();
            if (d == 0) {
                return Boolean.FALSE;
            }
            if (d == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", new Object[]{Integer.valueOf(d)}));
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            int i;
            if (((Boolean) obj).booleanValue()) {
                i = 1;
            } else {
                i = 0;
            }
            dpVar.m7470c(i);
        }
    };
    /* renamed from: d */
    public static final dn f7337d = new dn(dk.VARINT, Integer.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                return dp.m7464a(intValue);
            }
            return 10;
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            int intValue = ((Integer) obj).intValue();
            if (intValue >= 0) {
                dpVar.m7470c(intValue);
            } else {
                dpVar.m7471c((long) intValue);
            }
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Integer.valueOf(c2874do.m7459d());
        }
    };
    /* renamed from: e */
    public static final dn f7338e = new dn(dk.VARINT, Integer.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return dp.m7464a(((Integer) obj).intValue());
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7470c(((Integer) obj).intValue());
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Integer.valueOf(c2874do.m7459d());
        }
    };
    /* renamed from: f */
    public static final dn f7339f = new dn(dk.VARINT, Integer.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return dp.m7464a(dp.m7467b(((Integer) obj).intValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7470c(dp.m7467b(((Integer) obj).intValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            int d = c2874do.m7459d();
            return Integer.valueOf((-(d & 1)) ^ (d >>> 1));
        }
    };
    /* renamed from: g */
    public static final dn f7340g;
    /* renamed from: h */
    public static final dn f7341h;
    /* renamed from: i */
    public static final dn f7342i = new dn(dk.VARINT, Long.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return dp.m7466a(((Long) obj).longValue());
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7471c(((Long) obj).longValue());
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Long.valueOf(c2874do.m7460e());
        }
    };
    /* renamed from: j */
    public static final dn f7343j = new dn(dk.VARINT, Long.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return dp.m7466a(((Long) obj).longValue());
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7471c(((Long) obj).longValue());
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Long.valueOf(c2874do.m7460e());
        }
    };
    /* renamed from: k */
    public static final dn f7344k = new dn(dk.VARINT, Long.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return dp.m7466a(dp.m7468b(((Long) obj).longValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7471c(dp.m7468b(((Long) obj).longValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            long e = c2874do.m7460e();
            return Long.valueOf((-(e & 1)) ^ (e >>> 1));
        }
    };
    /* renamed from: l */
    public static final dn f7345l;
    /* renamed from: m */
    public static final dn f7346m;
    /* renamed from: n */
    public static final dn f7347n = new dn(dk.FIXED32, Float.class) {
        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7472d(Float.floatToIntBits(((Float) obj).floatValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Float.valueOf(Float.intBitsToFloat(c2874do.m7461f()));
        }
    };
    /* renamed from: o */
    public static final dn f7348o = new dn(dk.FIXED64, Double.class) {
        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7473d(Double.doubleToLongBits(((Double) obj).doubleValue()));
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return Double.valueOf(Double.longBitsToDouble(c2874do.m7462g()));
        }
    };
    /* renamed from: p */
    public static final dn f7349p = new dn(dk.LENGTH_DELIMITED, String.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            int i = 0;
            String str = (String) obj;
            int length = str.length();
            int i2 = 0;
            while (i < length) {
                char charAt = str.charAt(i);
                if (charAt >= '') {
                    if (charAt < 'ࠀ') {
                        i2 += 2;
                    } else if (charAt < '?' || charAt > '?') {
                        i2 += 3;
                    } else if (charAt <= '?' && i + 1 < length && str.charAt(i + 1) >= '?' && str.charAt(i + 1) <= '?') {
                        i2 += 4;
                        i++;
                    }
                    i++;
                }
                i2++;
                i++;
            }
            return i2;
        }

        /* renamed from: a */
        public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.f7377a.mo6344b((String) obj);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return c2874do.f7369a.mo6348c(c2874do.m7463h());
        }
    };
    /* renamed from: q */
    public static final dn f7350q = new dn(dk.LENGTH_DELIMITED, hx.class) {
        /* renamed from: a */
        public final /* synthetic */ int mo6213a(Object obj) {
            return ((hx) obj).mo6363c();
        }

        /* renamed from: a */
        public final /* bridge */ /* synthetic */ void mo6215a(dp dpVar, Object obj) {
            dpVar.m7469a((hx) obj);
        }

        /* renamed from: a */
        public final /* synthetic */ Object mo6214a(C2874do c2874do) {
            return c2874do.f7369a.mo6345b(c2874do.m7463h());
        }
    };
    /* renamed from: a */
    final Class f7351a;
    /* renamed from: b */
    dn f7352b;
    /* renamed from: r */
    private final dk f7353r;

    /* renamed from: com.tapjoy.internal.dn$a */
    public static final class C2873a extends IllegalArgumentException {
        /* renamed from: a */
        public final int f7368a;

        C2873a(int i, Class cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.f7368a = i;
        }
    }

    /* renamed from: a */
    public abstract int mo6213a(Object obj);

    /* renamed from: a */
    public abstract Object mo6214a(C2874do c2874do);

    /* renamed from: a */
    public abstract void mo6215a(dp dpVar, Object obj);

    public dn(dk dkVar, Class cls) {
        this.f7353r = dkVar;
        this.f7351a = cls;
    }

    /* renamed from: a */
    public int mo6216a(int i, Object obj) {
        int a = mo6213a(obj);
        if (this.f7353r == dk.LENGTH_DELIMITED) {
            a += dp.m7464a(a);
        }
        return a + dp.m7464a(dp.m7465a(i, dk.VARINT));
    }

    /* renamed from: a */
    public void mo6217a(dp dpVar, int i, Object obj) {
        dpVar.m7470c(dp.m7465a(i, this.f7353r));
        if (this.f7353r == dk.LENGTH_DELIMITED) {
            dpVar.m7470c(mo6213a(obj));
        }
        mo6215a(dpVar, obj);
    }

    /* renamed from: a */
    private void m7386a(hv hvVar, Object obj) {
        dm.m7407a(obj, "value == null");
        dm.m7407a(hvVar, "sink == null");
        mo6215a(new dp(hvVar), obj);
    }

    /* renamed from: b */
    public final byte[] m7397b(Object obj) {
        dm.m7407a(obj, "value == null");
        hv huVar = new hu();
        try {
            m7386a(huVar, obj);
            return huVar.m8129g();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    public final void m7396a(OutputStream outputStream, Object obj) {
        dm.m7407a(obj, "value == null");
        dm.m7407a(outputStream, "stream == null");
        hv a = hy.m8141a(hy.m8143a(outputStream));
        m7386a(a, obj);
        a.mo6339a();
    }

    /* renamed from: a */
    public final Object m7393a(byte[] bArr) {
        dm.m7407a(bArr, "bytes == null");
        hu huVar = new hu();
        if (bArr != null) {
            return m7385a(huVar.m8108a(bArr, 0, bArr.length));
        }
        throw new IllegalArgumentException("source == null");
    }

    /* renamed from: a */
    public final Object m7392a(InputStream inputStream) {
        dm.m7407a(inputStream, "stream == null");
        return m7385a(hy.m8142a(hy.m8144a(inputStream)));
    }

    /* renamed from: a */
    private Object m7385a(hw hwVar) {
        dm.m7407a(hwVar, "source == null");
        return mo6214a(new C2874do(hwVar));
    }

    /* renamed from: c */
    public static String m7387c(Object obj) {
        return obj.toString();
    }

    static {
        dn anonymousClass10 = new dn(dk.FIXED32, Integer.class) {
            /* renamed from: a */
            public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
                dpVar.m7472d(((Integer) obj).intValue());
            }

            /* renamed from: a */
            public final /* synthetic */ Object mo6214a(C2874do c2874do) {
                return Integer.valueOf(c2874do.m7461f());
            }
        };
        f7340g = anonymousClass10;
        f7341h = anonymousClass10;
        anonymousClass10 = new dn(dk.FIXED64, Long.class) {
            /* renamed from: a */
            public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
                dpVar.m7473d(((Long) obj).longValue());
            }

            /* renamed from: a */
            public final /* synthetic */ Object mo6214a(C2874do c2874do) {
                return Long.valueOf(c2874do.m7462g());
            }
        };
        f7345l = anonymousClass10;
        f7346m = anonymousClass10;
    }

    /* renamed from: a */
    public final dn m7390a() {
        dn dnVar = this.f7352b;
        if (dnVar != null) {
            return dnVar;
        }
        dnVar = new dn(this, this.f7353r, List.class) {
            /* renamed from: r */
            final /* synthetic */ dn f7367r;

            /* renamed from: a */
            public final /* synthetic */ int mo6216a(int i, Object obj) {
                int i2 = 0;
                List list = (List) obj;
                int i3 = 0;
                while (i2 < list.size()) {
                    i3 += this.f7367r.mo6216a(i, list.get(i2));
                    i2++;
                }
                return i3;
            }

            /* renamed from: a */
            public final /* synthetic */ void mo6217a(dp dpVar, int i, Object obj) {
                List list = (List) obj;
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    this.f7367r.mo6217a(dpVar, i, list.get(i2));
                }
            }

            /* renamed from: a */
            public final /* synthetic */ Object mo6214a(C2874do c2874do) {
                return Collections.singletonList(this.f7367r.mo6214a(c2874do));
            }

            /* renamed from: a */
            public final /* synthetic */ void mo6215a(dp dpVar, Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }

            /* renamed from: a */
            public final /* synthetic */ int mo6213a(Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }
        };
        this.f7352b = dnVar;
        return dnVar;
    }
}
