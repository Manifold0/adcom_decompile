package org.fmod;

import android.media.AudioTrack;
import android.util.Log;
import java.nio.ByteBuffer;

public class FMODAudioDevice implements Runnable {
    /* renamed from: h */
    private static int f273h = 0;
    /* renamed from: i */
    private static int f274i = 1;
    /* renamed from: j */
    private static int f275j = 2;
    /* renamed from: k */
    private static int f276k = 3;
    /* renamed from: a */
    private volatile Thread f277a = null;
    /* renamed from: b */
    private volatile boolean f278b = false;
    /* renamed from: c */
    private AudioTrack f279c = null;
    /* renamed from: d */
    private boolean f280d = false;
    /* renamed from: e */
    private ByteBuffer f281e = null;
    /* renamed from: f */
    private byte[] f282f = null;
    /* renamed from: g */
    private volatile C0268a f283g;

    private native int fmodGetInfo(int i);

    private native int fmodProcess(ByteBuffer byteBuffer);

    private void releaseAudioTrack() {
        if (this.f279c != null) {
            if (this.f279c.getState() == 1) {
                this.f279c.stop();
            }
            this.f279c.release();
            this.f279c = null;
        }
        this.f281e = null;
        this.f282f = null;
        this.f280d = false;
    }

    public synchronized void close() {
        stop();
    }

    native int fmodProcessMicData(ByteBuffer byteBuffer, int i);

    public boolean isRunning() {
        return this.f277a != null && this.f277a.isAlive();
    }

    public void run() {
        int i = 3;
        while (this.f278b) {
            int i2;
            if (this.f280d || i <= 0) {
                i2 = i;
            } else {
                releaseAudioTrack();
                int fmodGetInfo = fmodGetInfo(f273h);
                int round = Math.round(((float) AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2)) * 1.1f) & -4;
                int fmodGetInfo2 = fmodGetInfo(f274i);
                i2 = fmodGetInfo(f275j);
                if ((fmodGetInfo2 * i2) * 4 > round) {
                    round = (i2 * fmodGetInfo2) * 4;
                }
                this.f279c = new AudioTrack(3, fmodGetInfo, 3, 2, round, 1);
                this.f280d = this.f279c.getState() == 1;
                if (this.f280d) {
                    this.f281e = ByteBuffer.allocateDirect((fmodGetInfo2 * 2) * 2);
                    this.f282f = new byte[this.f281e.capacity()];
                    this.f279c.play();
                    i2 = 3;
                } else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.f279c.getState() + ")");
                    releaseAudioTrack();
                    i2 = i - 1;
                }
            }
            if (!this.f280d) {
                i = i2;
            } else if (fmodGetInfo(f276k) == 1) {
                fmodProcess(this.f281e);
                this.f281e.get(this.f282f, 0, this.f281e.capacity());
                this.f279c.write(this.f282f, 0, this.f281e.capacity());
                this.f281e.position(0);
                i = i2;
            } else {
                releaseAudioTrack();
                i = i2;
            }
        }
        releaseAudioTrack();
    }

    public synchronized void start() {
        if (this.f277a != null) {
            stop();
        }
        this.f277a = new Thread(this, "FMODAudioDevice");
        this.f277a.setPriority(10);
        this.f278b = true;
        this.f277a.start();
        if (this.f283g != null) {
            this.f283g.m206b();
        }
    }

    public synchronized int startAudioRecord(int i, int i2, int i3) {
        if (this.f283g == null) {
            this.f283g = new C0268a(this, i, i2);
            this.f283g.m206b();
        }
        return this.f283g.m205a();
    }

    public synchronized void stop() {
        while (this.f277a != null) {
            this.f278b = false;
            try {
                this.f277a.join();
                this.f277a = null;
            } catch (InterruptedException e) {
            }
        }
        if (this.f283g != null) {
            this.f283g.m207c();
        }
    }

    public synchronized void stopAudioRecord() {
        if (this.f283g != null) {
            this.f283g.m207c();
            this.f283g = null;
        }
    }
}
