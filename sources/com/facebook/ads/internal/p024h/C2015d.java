package com.facebook.ads.internal.p024h;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p025w.p057e.C2612d;
import com.facebook.ads.internal.p025w.p069c.C2604c;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.protocol.C2066b;
import com.facebook.share.internal.MessengerShareContentUtility;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* renamed from: com.facebook.ads.internal.h.d */
public class C2015d {
    /* renamed from: a */
    private static final String f4465a = C2015d.class.getSimpleName();
    /* renamed from: b */
    private static C2015d f4466b;
    /* renamed from: c */
    private final Context f4467c;

    private C2015d(Context context) {
        this.f4467c = context;
    }

    /* renamed from: a */
    private Bitmap m4856a(String str) {
        byte[] d = C2612d.m6709a(this.f4467c).m5459a(str, null).m5504d();
        return BitmapFactory.decodeByteArray(d, 0, d.length);
    }

    /* renamed from: a */
    public static C2015d m4857a(Context context) {
        if (f4466b == null) {
            Context applicationContext = context.getApplicationContext();
            synchronized (C2015d.class) {
                if (f4466b == null) {
                    f4466b = new C2015d(applicationContext);
                }
            }
        }
        return f4466b;
    }

    /* renamed from: a */
    private static void m4858a(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    /* renamed from: a */
    private void m4859a(String str, Bitmap bitmap) {
        Closeable byteArrayOutputStream;
        Closeable fileOutputStream;
        Throwable e;
        Closeable closeable = null;
        if (bitmap == null) {
            m4860a(null);
            return;
        }
        File file = new File(this.f4467c.getCacheDir(), str.hashCode() + ".png");
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
                if (byteArrayOutputStream.size() >= 3145728) {
                    Log.d(f4465a, "Bitmap size exceeds max size for storage");
                    C2015d.m4858a(byteArrayOutputStream);
                    C2015d.m4858a(null);
                    return;
                }
                fileOutputStream = new FileOutputStream(file);
                try {
                    byteArrayOutputStream.writeTo(fileOutputStream);
                    fileOutputStream.flush();
                    C2015d.m4858a(byteArrayOutputStream);
                    C2015d.m4858a(fileOutputStream);
                } catch (FileNotFoundException e2) {
                    e = e2;
                    closeable = fileOutputStream;
                    fileOutputStream = byteArrayOutputStream;
                    try {
                        Log.e(f4465a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
                        m4860a(e);
                        C2015d.m4858a(fileOutputStream);
                        C2015d.m4858a(closeable);
                    } catch (Throwable th) {
                        e = th;
                        byteArrayOutputStream = fileOutputStream;
                        C2015d.m4858a(byteArrayOutputStream);
                        C2015d.m4858a(closeable);
                        throw e;
                    }
                } catch (IOException e3) {
                    e = e3;
                    closeable = fileOutputStream;
                    try {
                        m4860a(e);
                        Log.e(f4465a, "Unable to write bitmap to file (url=" + str + ").", e);
                        C2015d.m4858a(byteArrayOutputStream);
                        C2015d.m4858a(closeable);
                    } catch (Throwable th2) {
                        e = th2;
                        C2015d.m4858a(byteArrayOutputStream);
                        C2015d.m4858a(closeable);
                        throw e;
                    }
                } catch (OutOfMemoryError e4) {
                    e = e4;
                    closeable = fileOutputStream;
                    m4860a(e);
                    Log.e(f4465a, "Unable to write bitmap to output stream", e);
                    C2015d.m4858a(byteArrayOutputStream);
                    C2015d.m4858a(closeable);
                } catch (Throwable th3) {
                    e = th3;
                    closeable = fileOutputStream;
                    C2015d.m4858a(byteArrayOutputStream);
                    C2015d.m4858a(closeable);
                    throw e;
                }
            } catch (FileNotFoundException e5) {
                e = e5;
                fileOutputStream = byteArrayOutputStream;
                Log.e(f4465a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
                m4860a(e);
                C2015d.m4858a(fileOutputStream);
                C2015d.m4858a(closeable);
            } catch (IOException e6) {
                e = e6;
                m4860a(e);
                Log.e(f4465a, "Unable to write bitmap to file (url=" + str + ").", e);
                C2015d.m4858a(byteArrayOutputStream);
                C2015d.m4858a(closeable);
            } catch (OutOfMemoryError e7) {
                e = e7;
                m4860a(e);
                Log.e(f4465a, "Unable to write bitmap to output stream", e);
                C2015d.m4858a(byteArrayOutputStream);
                C2015d.m4858a(closeable);
            }
        } catch (FileNotFoundException e8) {
            e = e8;
            fileOutputStream = null;
            Log.e(f4465a, "Bad output destination (file=" + file.getAbsolutePath() + ").", e);
            m4860a(e);
            C2015d.m4858a(fileOutputStream);
            C2015d.m4858a(closeable);
        } catch (IOException e9) {
            e = e9;
            byteArrayOutputStream = null;
            m4860a(e);
            Log.e(f4465a, "Unable to write bitmap to file (url=" + str + ").", e);
            C2015d.m4858a(byteArrayOutputStream);
            C2015d.m4858a(closeable);
        } catch (OutOfMemoryError e10) {
            e = e10;
            byteArrayOutputStream = null;
            m4860a(e);
            Log.e(f4465a, "Unable to write bitmap to output stream", e);
            C2015d.m4858a(byteArrayOutputStream);
            C2015d.m4858a(closeable);
        } catch (Throwable th4) {
            e = th4;
            byteArrayOutputStream = null;
            C2015d.m4858a(byteArrayOutputStream);
            C2015d.m4858a(closeable);
            throw e;
        }
    }

    /* renamed from: a */
    private void m4860a(Throwable th) {
        C2625a.m6741b(this.f4467c, MessengerShareContentUtility.MEDIA_IMAGE, C2626b.f6528S, new C2066b(AdErrorType.IMAGE_CACHE_ERROR, AdErrorType.IMAGE_CACHE_ERROR.getDefaultErrorMessage(), th));
    }

    /* renamed from: a */
    private boolean m4861a(int i, int i2) {
        return i > 0 && i2 > 0 && C2078a.m5102l(this.f4467c);
    }

    @Nullable
    /* renamed from: b */
    private Bitmap m4862b(String str, int i, int i2) {
        try {
            Bitmap a = m4861a(i, i2) ? C2604c.m6699a(str.substring("file://".length()), i, i2) : BitmapFactory.decodeStream(new FileInputStream(str.substring("file://".length())), null, null);
            m4859a(str, a);
            return a;
        } catch (Throwable e) {
            Log.e(f4465a, "Failed to copy local image into cache (url=" + str + ").", e);
            return null;
        }
    }

    @Nullable
    /* renamed from: c */
    private Bitmap m4863c(String str, int i, int i2) {
        Throwable e;
        Throwable th;
        Bitmap bitmap = null;
        if (str.startsWith("asset:///")) {
            Closeable open;
            try {
                open = this.f4467c.getAssets().open(str.substring(9, str.length()));
                try {
                    bitmap = m4861a(i, i2) ? C2604c.m6698a((InputStream) open, i, i2) : BitmapFactory.decodeStream(open);
                    if (open != null) {
                        C2015d.m4858a(open);
                    }
                } catch (IOException e2) {
                    e = e2;
                    try {
                        m4860a(e);
                        if (open != null) {
                            C2015d.m4858a(open);
                        }
                        return bitmap;
                    } catch (Throwable th2) {
                        th = th2;
                        if (open != null) {
                            C2015d.m4858a(open);
                        }
                        throw th;
                    }
                } catch (OutOfMemoryError e3) {
                    e = e3;
                    m4860a(e);
                    if (open != null) {
                        C2015d.m4858a(open);
                    }
                    return bitmap;
                }
            } catch (IOException e4) {
                e = e4;
                open = bitmap;
                m4860a(e);
                if (open != null) {
                    C2015d.m4858a(open);
                }
                return bitmap;
            } catch (OutOfMemoryError e5) {
                e = e5;
                open = bitmap;
                m4860a(e);
                if (open != null) {
                    C2015d.m4858a(open);
                }
                return bitmap;
            } catch (Throwable e6) {
                open = bitmap;
                th = e6;
                if (open != null) {
                    C2015d.m4858a(open);
                }
                throw th;
            }
        } else if (m4861a(i, i2)) {
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                Closeable inputStream = httpURLConnection.getInputStream();
                bitmap = C2604c.m6698a((InputStream) inputStream, i, i2);
                C2015d.m4858a(inputStream);
            } catch (Throwable th3) {
                m4860a(th3);
                bitmap = m4856a(str);
            }
        } else {
            bitmap = m4856a(str);
        }
        m4859a(str, bitmap);
        return bitmap;
    }

    @Nullable
    /* renamed from: a */
    public Bitmap m4864a(String str, int i, int i2) {
        File file = new File(this.f4467c.getCacheDir(), str.hashCode() + ".png");
        return !file.exists() ? str.startsWith("file://") ? m4862b(str, i, i2) : m4863c(str, i, i2) : m4861a(i, i2) ? C2604c.m6699a(file.getAbsolutePath(), i, i2) : BitmapFactory.decodeFile(file.getAbsolutePath());
    }
}
