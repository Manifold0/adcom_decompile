package com.tapjoy.internal;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import java.nio.ByteBuffer;

public final class hj extends ImageView implements Runnable {
    /* renamed from: a */
    private hf f8118a;
    /* renamed from: b */
    private Bitmap f8119b;
    /* renamed from: c */
    private final Handler f8120c = new Handler(Looper.getMainLooper());
    /* renamed from: d */
    private boolean f8121d;
    /* renamed from: e */
    private boolean f8122e;
    /* renamed from: f */
    private boolean f8123f;
    /* renamed from: g */
    private Thread f8124g;
    /* renamed from: h */
    private C2977b f8125h = null;
    /* renamed from: i */
    private long f8126i = -1;
    /* renamed from: j */
    private C2976a f8127j = null;
    /* renamed from: k */
    private final Runnable f8128k = new C29741(this);
    /* renamed from: l */
    private final Runnable f8129l = new C29752(this);

    /* renamed from: com.tapjoy.internal.hj$1 */
    class C29741 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ hj f8116a;

        C29741(hj hjVar) {
            this.f8116a = hjVar;
        }

        public final void run() {
            if (this.f8116a.f8119b != null && !this.f8116a.f8119b.isRecycled()) {
                this.f8116a.setImageBitmap(this.f8116a.f8119b);
            }
        }
    }

    /* renamed from: com.tapjoy.internal.hj$2 */
    class C29752 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ hj f8117a;

        C29752(hj hjVar) {
            this.f8117a = hjVar;
        }

        public final void run() {
            this.f8117a.f8119b = null;
            this.f8117a.f8118a = null;
            this.f8117a.f8124g = null;
            this.f8117a.f8123f = false;
        }
    }

    /* renamed from: com.tapjoy.internal.hj$a */
    public interface C2976a {
    }

    /* renamed from: com.tapjoy.internal.hj$b */
    public interface C2977b {
        /* renamed from: a */
        Bitmap m8053a();
    }

    public hj(Context context) {
        super(context);
    }

    /* renamed from: a */
    public final void m8062a(hh hhVar, byte[] bArr) {
        try {
            this.f8118a = new hf(new hk(), hhVar, ByteBuffer.wrap(bArr));
            if (this.f8121d) {
                m8059e();
            } else {
                m8058d();
            }
        } catch (Exception e) {
            this.f8118a = null;
            new Object[1][0] = e;
        }
    }

    public final void setBytes(byte[] bytes) {
        this.f8118a = new hf();
        try {
            this.f8118a.m8041a(bytes);
            if (this.f8121d) {
                m8059e();
            } else {
                m8058d();
            }
        } catch (Exception e) {
            this.f8118a = null;
            new Object[1][0] = e;
        }
    }

    public final long getFramesDisplayDuration() {
        return this.f8126i;
    }

    public final void setFramesDisplayDuration(long framesDisplayDuration) {
        this.f8126i = framesDisplayDuration;
    }

    /* renamed from: a */
    public final void m8061a() {
        this.f8121d = true;
        m8059e();
    }

    /* renamed from: b */
    public final void m8063b() {
        this.f8121d = false;
        if (this.f8124g != null) {
            this.f8124g.interrupt();
            this.f8124g = null;
        }
    }

    /* renamed from: d */
    private void m8058d() {
        if (this.f8118a.f8064a != 0) {
            boolean z;
            hf hfVar = this.f8118a;
            if (-1 >= hfVar.f8066c.f8101c) {
                z = false;
            } else {
                hfVar.f8064a = -1;
                z = true;
            }
            if (z && !this.f8121d) {
                this.f8122e = true;
                m8059e();
            }
        }
    }

    /* renamed from: c */
    public final void m8064c() {
        this.f8121d = false;
        this.f8122e = false;
        this.f8123f = true;
        m8063b();
        this.f8120c.post(this.f8129l);
    }

    public final int getGifWidth() {
        return this.f8118a.f8066c.f8104f;
    }

    public final int getGifHeight() {
        return this.f8118a.f8066c.f8105g;
    }

    public final void run() {
        ArrayIndexOutOfBoundsException e;
        IllegalArgumentException e2;
        do {
            if (!this.f8121d && !this.f8122e) {
                break;
            }
            long nanoTime;
            hf hfVar = this.f8118a;
            boolean z;
            if (hfVar.f8066c.f8101c <= 0) {
                z = false;
            } else {
                if (hfVar.f8064a == hfVar.f8066c.f8101c - 1) {
                    hfVar.f8065b++;
                }
                if (hfVar.f8066c.f8111m == -1 || hfVar.f8065b <= hfVar.f8066c.f8111m) {
                    hfVar.f8064a = (hfVar.f8064a + 1) % hfVar.f8066c.f8101c;
                    z = true;
                } else {
                    z = false;
                }
            }
            try {
                nanoTime = System.nanoTime();
                this.f8119b = this.f8118a.m8042a();
                if (this.f8125h != null) {
                    this.f8119b = this.f8125h.m8053a();
                }
                nanoTime = (System.nanoTime() - nanoTime) / 1000000;
                try {
                    this.f8120c.post(this.f8128k);
                } catch (ArrayIndexOutOfBoundsException e3) {
                    e = e3;
                    new Object[1][0] = e;
                    this.f8122e = false;
                    if (this.f8121d) {
                    }
                    this.f8121d = false;
                    if (this.f8123f) {
                        this.f8120c.post(this.f8129l);
                    }
                    this.f8124g = null;
                } catch (IllegalArgumentException e4) {
                    e2 = e4;
                    new Object[1][0] = e2;
                    this.f8122e = false;
                    if (this.f8121d) {
                    }
                    this.f8121d = false;
                    if (this.f8123f) {
                        this.f8120c.post(this.f8129l);
                    }
                    this.f8124g = null;
                }
            } catch (ArrayIndexOutOfBoundsException e5) {
                e = e5;
                nanoTime = 0;
                new Object[1][0] = e;
                this.f8122e = false;
                if (this.f8121d) {
                }
                this.f8121d = false;
                if (this.f8123f) {
                    this.f8120c.post(this.f8129l);
                }
                this.f8124g = null;
            } catch (IllegalArgumentException e6) {
                e2 = e6;
                nanoTime = 0;
                new Object[1][0] = e2;
                this.f8122e = false;
                if (this.f8121d) {
                }
                this.f8121d = false;
                if (this.f8123f) {
                    this.f8120c.post(this.f8129l);
                }
                this.f8124g = null;
            }
            this.f8122e = false;
            if (this.f8121d || !r0) {
                this.f8121d = false;
                break;
            }
            try {
                int i;
                hfVar = this.f8118a;
                if (hfVar.f8066c.f8101c <= 0 || hfVar.f8064a < 0) {
                    i = 0;
                } else {
                    int i2 = hfVar.f8064a;
                    if (i2 < 0 || i2 >= hfVar.f8066c.f8101c) {
                        i = -1;
                    } else {
                        i = ((hg) hfVar.f8066c.f8103e.get(i2)).f8096i;
                    }
                }
                i = (int) (((long) i) - nanoTime);
                if (i > 0) {
                    Thread.sleep(this.f8126i > 0 ? this.f8126i : (long) i);
                }
            } catch (InterruptedException e7) {
            }
        } while (this.f8121d);
        if (this.f8123f) {
            this.f8120c.post(this.f8129l);
        }
        this.f8124g = null;
    }

    public final C2977b getOnFrameAvailable() {
        return this.f8125h;
    }

    public final void setOnFrameAvailable(C2977b frameProcessor) {
        this.f8125h = frameProcessor;
    }

    public final C2976a getOnAnimationStop() {
        return this.f8127j;
    }

    public final void setOnAnimationStop(C2976a animationStop) {
        this.f8127j = animationStop;
    }

    protected final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        m8064c();
    }

    /* renamed from: e */
    private void m8059e() {
        Object obj;
        if ((this.f8121d || this.f8122e) && this.f8118a != null && this.f8124g == null) {
            obj = 1;
        } else {
            obj = null;
        }
        if (obj != null) {
            this.f8124g = new Thread(this);
            this.f8124g.start();
        }
    }
}
