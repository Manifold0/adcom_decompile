package com.facebook.ads.internal.p024h;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p046v.p053b.C2180l;
import com.facebook.ads.internal.p046v.p053b.C2186o;
import com.facebook.ads.internal.p046v.p053b.p054a.C2155b;
import com.facebook.ads.internal.p046v.p053b.p054a.C2162f;
import com.facebook.ads.internal.p046v.p053b.p054a.C2163g;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* renamed from: com.facebook.ads.internal.h.c */
public class C2014c {
    /* renamed from: a */
    private static final String f4462a = C2014c.class.getSimpleName();
    /* renamed from: b */
    private static C2014c f4463b;
    /* renamed from: c */
    private final Future<C2013a> f4464c;

    /* renamed from: com.facebook.ads.internal.h.c$a */
    private static class C2013a {
        /* renamed from: a */
        private static final Map<String, File> f4460a = new HashMap();
        /* renamed from: b */
        private final Context f4461b;

        C2013a(Context context) {
            this.f4461b = context;
        }

        @Nullable
        /* renamed from: a */
        String m4849a(String str) {
            File file = (File) f4460a.get(str);
            return file == null ? null : "file://" + file.getPath();
        }

        /* renamed from: b */
        boolean m4850b(String str) {
            Throwable e;
            boolean z = true;
            InputStream inputStream = null;
            try {
                File file = new File(C2186o.m5597a(this.f4461b), new C2162f().mo5527a(str));
                C2155b c2155b = new C2155b(file, new C2163g(67108864));
                if (c2155b.mo5525d()) {
                    f4460a.put(str, file);
                    c2155b.mo5523b();
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable e2) {
                            Log.e(C2014c.f4462a, "Error closing the file", e2);
                        }
                    }
                } else {
                    URLConnection openConnection = new URL(str).openConnection();
                    openConnection.connect();
                    InputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                    try {
                        byte[] bArr = new byte[8192];
                        while (true) {
                            int read = bufferedInputStream.read(bArr);
                            if (read == -1) {
                                break;
                            }
                            c2155b.mo5522a(bArr, read);
                        }
                        c2155b.mo5524c();
                        f4460a.put(str, file);
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Throwable e22) {
                                Log.e(C2014c.f4462a, "Error closing the file", e22);
                            }
                        }
                    } catch (IOException e3) {
                        e = e3;
                        inputStream = bufferedInputStream;
                        try {
                            Log.e(C2014c.f4462a, "Error caching the file", e);
                            z = false;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e222) {
                                    Log.e(C2014c.f4462a, "Error closing the file", e222);
                                }
                            }
                            return z;
                        } catch (Throwable th) {
                            e = th;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable e2222) {
                                    Log.e(C2014c.f4462a, "Error closing the file", e2222);
                                }
                            }
                            throw e;
                        }
                    } catch (C2180l e4) {
                        e = e4;
                        inputStream = bufferedInputStream;
                        Log.e(C2014c.f4462a, "Error caching the file", e);
                        z = false;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        return z;
                    } catch (Throwable th2) {
                        e = th2;
                        inputStream = bufferedInputStream;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw e;
                    }
                }
            } catch (IOException e5) {
                e = e5;
                Log.e(C2014c.f4462a, "Error caching the file", e);
                z = false;
                if (inputStream != null) {
                    inputStream.close();
                }
                return z;
            } catch (C2180l e6) {
                e = e6;
                Log.e(C2014c.f4462a, "Error caching the file", e);
                z = false;
                if (inputStream != null) {
                    inputStream.close();
                }
                return z;
            }
            return z;
        }
    }

    private C2014c(final Context context) {
        this.f4464c = Executors.newSingleThreadExecutor().submit(new Callable<C2013a>(this) {
            /* renamed from: b */
            final /* synthetic */ C2014c f4459b;

            /* renamed from: a */
            public C2013a m4848a() {
                return new C2013a(context);
            }

            public /* synthetic */ Object call() {
                return m4848a();
            }
        });
    }

    /* renamed from: a */
    public static C2014c m4851a(Context context) {
        if (f4463b == null) {
            synchronized (C2017e.class) {
                if (f4463b == null) {
                    f4463b = new C2014c(context.getApplicationContext());
                }
            }
        }
        return f4463b;
    }

    @Nullable
    /* renamed from: b */
    private C2013a m4853b() {
        Throwable e;
        try {
            return (C2013a) this.f4464c.get(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        } catch (TimeoutException e4) {
            e = e4;
        }
        Log.e(f4462a, "Timed out waiting for cache server.", e);
        return null;
    }

    /* renamed from: a */
    public boolean m4854a(String str) {
        C2013a b = m4853b();
        return b != null && b.m4850b(str);
    }

    @Nullable
    /* renamed from: b */
    public String m4855b(String str) {
        C2013a b = m4853b();
        return b == null ? null : b.m4849a(str);
    }
}
