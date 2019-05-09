package com.chartboost.sdk.impl;

import android.os.Handler;
import com.applovin.sdk.AppLovinErrorCodes;
import com.chartboost.sdk.Libraries.C1382i;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Model.CBError.C1385a;
import com.chartboost.sdk.Tracking.C1391a;
import com.google.android.gms.nearby.messages.Strategy;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.util.Map;
import java.util.concurrent.Executor;

public class an<T> implements Comparable<an>, Runnable {
    /* renamed from: a */
    public final ad<T> f3032a;
    /* renamed from: b */
    private final Executor f3033b;
    /* renamed from: c */
    private final ao f3034c;
    /* renamed from: d */
    private final ai f3035d;
    /* renamed from: e */
    private final C1382i f3036e;
    /* renamed from: f */
    private final Handler f3037f;
    /* renamed from: g */
    private af<T> f3038g;
    /* renamed from: h */
    private ag f3039h;

    public /* synthetic */ int compareTo(Object obj) {
        return m3405a((an) obj);
    }

    an(Executor executor, ao aoVar, ai aiVar, C1382i c1382i, Handler handler, ad<T> adVar) {
        this.f3033b = executor;
        this.f3034c = aoVar;
        this.f3035d = aiVar;
        this.f3036e = c1382i;
        this.f3037f = handler;
        this.f3032a = adVar;
    }

    public void run() {
        if (this.f3038g != null) {
            try {
                if (this.f3038g.f2994b == null) {
                    this.f3032a.mo4283a(this.f3038g.f2993a, this.f3039h);
                } else {
                    this.f3032a.mo4282a(this.f3038g.f2994b, this.f3039h);
                }
            } catch (Exception e) {
                C1391a.m3206a(getClass(), "deliver result", e);
            }
        } else if (this.f3032a.f2984e.compareAndSet(0, 1)) {
            long b = this.f3036e.m3159b();
            try {
                if (this.f3035d.m3378b()) {
                    this.f3039h = m3402a(this.f3032a);
                    int i = this.f3039h.f2995a;
                    if (i < 200 || i >= Strategy.TTL_SECONDS_DEFAULT) {
                        this.f3038g = af.m3369a(new CBError(C1385a.NETWORK_FAILURE, "Failure due to HTTP status code " + i));
                    } else {
                        this.f3038g = this.f3032a.mo4281a(this.f3039h);
                    }
                } else {
                    this.f3038g = af.m3369a(new CBError(C1385a.INTERNET_UNAVAILABLE, "Internet Unavailable"));
                }
                this.f3032a.f2986g = this.f3036e.m3159b() - b;
                switch (this.f3032a.f2989j) {
                    case 0:
                        this.f3037f.post(this);
                        return;
                    case 1:
                        this.f3033b.execute(this);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                this.f3032a.f2986g = this.f3036e.m3159b() - b;
                switch (this.f3032a.f2989j) {
                    case 0:
                        this.f3037f.post(this);
                        break;
                    case 1:
                        this.f3033b.execute(this);
                        break;
                }
            }
        }
    }

    /* renamed from: a */
    private ag m3402a(ad<T> adVar) throws IOException {
        int i = 10000;
        int i2 = 0;
        while (true) {
            try {
                break;
            } catch (SocketTimeoutException e) {
                if (i2 < 1) {
                    i *= 2;
                    i2++;
                } else {
                    throw e;
                }
            }
        }
        return m3403a(adVar, i);
    }

    /* renamed from: a */
    private ag m3403a(ad<T> adVar, int i) throws IOException {
        DataOutputStream dataOutputStream;
        Throwable th;
        InputStream inputStream;
        InputStream inputStream2 = null;
        ae a = adVar.mo4280a();
        Map map = a.f2990a;
        HttpURLConnection a2 = this.f3034c.m3406a(adVar);
        a2.setConnectTimeout(i);
        a2.setReadTimeout(i);
        a2.setUseCaches(false);
        a2.setDoInput(true);
        if (map != null) {
            for (String str : map.keySet()) {
                a2.addRequestProperty(str, (String) map.get(str));
            }
        }
        long b;
        long b2;
        try {
            a2.setRequestMethod(adVar.f2981b);
            if (adVar.f2981b.equals("POST") && a.f2991b != null) {
                a2.setDoOutput(true);
                a2.setFixedLengthStreamingMode(a.f2991b.length);
                if (a.f2992c != null) {
                    a2.addRequestProperty("Content-Type", a.f2992c);
                }
                try {
                    dataOutputStream = new DataOutputStream(a2.getOutputStream());
                    try {
                        dataOutputStream.write(a.f2991b);
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (dataOutputStream != null) {
                            try {
                                dataOutputStream.close();
                            } catch (IOException e2) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    dataOutputStream = inputStream2;
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    throw th;
                }
            }
            b = this.f3036e.m3159b();
            int responseCode = a2.getResponseCode();
            b2 = this.f3036e.m3159b();
            adVar.f2987h = b2 - b;
            if (responseCode == -1) {
                throw new IOException("Could not retrieve response code from HttpUrlConnection.");
            }
            byte[] bArr;
            if (!m3404a(responseCode)) {
                bArr = new byte[0];
            } else if (adVar.f2985f != null) {
                File file = new File(adVar.f2985f.getParentFile(), adVar.f2985f.getName() + ".tmp");
                bArr = new byte[0];
                try {
                    InputStream inputStream3 = a2.getInputStream();
                    try {
                        OutputStream fileOutputStream = new FileOutputStream(file);
                        try {
                            bi.m3525a(inputStream3, fileOutputStream);
                            if (inputStream3 != null) {
                                try {
                                    inputStream3.close();
                                } catch (IOException e3) {
                                }
                            }
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e4) {
                                }
                            }
                            if (!file.renameTo(adVar.f2985f)) {
                                if (file.delete()) {
                                    throw new IOException("Unable to move " + file.getAbsolutePath() + " to " + adVar.f2985f.getAbsolutePath());
                                }
                                throw new IOException("Unable to delete " + file.getAbsolutePath() + " after failing to rename to " + adVar.f2985f.getAbsolutePath());
                            }
                        } catch (Throwable th4) {
                            th = th4;
                            OutputStream outputStream = fileOutputStream;
                            inputStream = inputStream3;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e5) {
                                }
                            }
                            if (r2 != null) {
                                try {
                                    r2.close();
                                } catch (IOException e6) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        inputStream = inputStream3;
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (r2 != null) {
                            r2.close();
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    th = th6;
                    inputStream = inputStream2;
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (r2 != null) {
                        r2.close();
                    }
                    throw th;
                }
            } else {
                try {
                    inputStream2 = a2.getInputStream();
                } catch (IOException e7) {
                    inputStream2 = a2.getErrorStream();
                } catch (Throwable th7) {
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e8) {
                        }
                    }
                }
                if (inputStream2 != null) {
                    bArr = bi.m3532b(new BufferedInputStream(inputStream2));
                } else {
                    bArr = new byte[0];
                }
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e9) {
                    }
                }
            }
            adVar.f2988i = this.f3036e.m3159b() - b2;
            ag agVar = new ag(responseCode, bArr);
            a2.disconnect();
            return agVar;
        } catch (Throwable th8) {
            a2.disconnect();
        }
    }

    /* renamed from: a */
    private static boolean m3404a(int i) {
        boolean z;
        if (100 > i || i >= 200) {
            z = false;
        } else {
            z = true;
        }
        return (z || i == AppLovinErrorCodes.NO_FILL || i == 304) ? false : true;
    }

    /* renamed from: a */
    public int m3405a(an anVar) {
        return this.f3032a.f2983d - anVar.f3032a.f2983d;
    }
}
