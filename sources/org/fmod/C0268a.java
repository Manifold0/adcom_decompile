package org.fmod;

import android.media.AudioRecord;
import android.util.Log;
import java.nio.ByteBuffer;

/* renamed from: org.fmod.a */
final class C0268a implements Runnable {
    /* renamed from: a */
    private final FMODAudioDevice f284a;
    /* renamed from: b */
    private final ByteBuffer f285b;
    /* renamed from: c */
    private final int f286c;
    /* renamed from: d */
    private final int f287d;
    /* renamed from: e */
    private final int f288e = 2;
    /* renamed from: f */
    private volatile Thread f289f;
    /* renamed from: g */
    private volatile boolean f290g;
    /* renamed from: h */
    private AudioRecord f291h;
    /* renamed from: i */
    private boolean f292i;

    C0268a(FMODAudioDevice fMODAudioDevice, int i, int i2) {
        this.f284a = fMODAudioDevice;
        this.f286c = i;
        this.f287d = i2;
        this.f285b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(i, i2, 2));
    }

    /* renamed from: d */
    private void m204d() {
        if (this.f291h != null) {
            if (this.f291h.getState() == 1) {
                this.f291h.stop();
            }
            this.f291h.release();
            this.f291h = null;
        }
        this.f285b.position(0);
        this.f292i = false;
    }

    /* renamed from: a */
    public final int m205a() {
        return this.f285b.capacity();
    }

    /* renamed from: b */
    public final void m206b() {
        if (this.f289f != null) {
            m207c();
        }
        this.f290g = true;
        this.f289f = new Thread(this);
        this.f289f.start();
    }

    /* renamed from: c */
    public final void m207c() {
        while (this.f289f != null) {
            this.f290g = false;
            try {
                this.f289f.join();
                this.f289f = null;
            } catch (InterruptedException e) {
            }
        }
    }

    public final void run() {
        int i = 3;
        while (this.f290g) {
            int i2;
            if (!this.f292i && i > 0) {
                m204d();
                this.f291h = new AudioRecord(1, this.f286c, this.f287d, this.f288e, this.f285b.capacity());
                this.f292i = this.f291h.getState() == 1;
                if (this.f292i) {
                    this.f285b.position(0);
                    this.f291h.startRecording();
                    i2 = 3;
                    if (this.f292i || this.f291h.getRecordingState() != 3) {
                        i = i2;
                    } else {
                        this.f284a.fmodProcessMicData(this.f285b, this.f291h.read(this.f285b, this.f285b.capacity()));
                        this.f285b.position(0);
                        i = i2;
                    }
                } else {
                    Log.e("FMOD", "AudioRecord failed to initialize (status " + this.f291h.getState() + ")");
                    i--;
                    m204d();
                }
            }
            i2 = i;
            if (this.f292i) {
            }
            i = i2;
        }
        m204d();
    }
}
