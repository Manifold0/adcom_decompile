// 
// Decompiled by Procyon v0.5.34
// 

package org.fmod;

import android.util.Log;
import java.nio.ByteBuffer;
import android.media.AudioTrack;

public class FMODAudioDevice implements Runnable
{
    private static int h;
    private static int i;
    private static int j;
    private static int k;
    private volatile Thread a;
    private volatile boolean b;
    private AudioTrack c;
    private boolean d;
    private ByteBuffer e;
    private byte[] f;
    private volatile a g;
    
    static {
        FMODAudioDevice.h = 0;
        FMODAudioDevice.i = 1;
        FMODAudioDevice.j = 2;
        FMODAudioDevice.k = 3;
    }
    
    public FMODAudioDevice() {
        this.a = null;
        this.b = false;
        this.c = null;
        this.d = false;
        this.e = null;
        this.f = null;
    }
    
    private native int fmodGetInfo(final int p0);
    
    private native int fmodProcess(final ByteBuffer p0);
    
    private void releaseAudioTrack() {
        if (this.c != null) {
            if (this.c.getState() == 1) {
                this.c.stop();
            }
            this.c.release();
            this.c = null;
        }
        this.e = null;
        this.f = null;
        this.d = false;
    }
    
    public void close() {
        synchronized (this) {
            this.stop();
        }
    }
    
    native int fmodProcessMicData(final ByteBuffer p0, final int p1);
    
    public boolean isRunning() {
        return this.a != null && this.a.isAlive();
    }
    
    @Override
    public void run() {
        int n = 3;
        while (this.b) {
            if (!this.d && n > 0) {
                this.releaseAudioTrack();
                final int fmodGetInfo = this.fmodGetInfo(FMODAudioDevice.h);
                final int n2 = Math.round(AudioTrack.getMinBufferSize(fmodGetInfo, 3, 2) * 1.1f) & 0xFFFFFFFC;
                final int fmodGetInfo2 = this.fmodGetInfo(FMODAudioDevice.i);
                final int fmodGetInfo3 = this.fmodGetInfo(FMODAudioDevice.j);
                int n3;
                if (fmodGetInfo2 * fmodGetInfo3 * 4 > (n3 = n2)) {
                    n3 = fmodGetInfo3 * fmodGetInfo2 * 4;
                }
                this.c = new AudioTrack(3, fmodGetInfo, 3, 2, n3, 1);
                this.d = (this.c.getState() == 1);
                if (this.d) {
                    this.e = ByteBuffer.allocateDirect(fmodGetInfo2 * 2 * 2);
                    this.f = new byte[this.e.capacity()];
                    this.c.play();
                    n = 3;
                }
                else {
                    Log.e("FMOD", "AudioTrack failed to initialize (status " + this.c.getState() + ")");
                    this.releaseAudioTrack();
                    --n;
                }
            }
            if (this.d) {
                if (this.fmodGetInfo(FMODAudioDevice.k) == 1) {
                    this.fmodProcess(this.e);
                    this.e.get(this.f, 0, this.e.capacity());
                    this.c.write(this.f, 0, this.e.capacity());
                    this.e.position(0);
                }
                else {
                    this.releaseAudioTrack();
                }
            }
        }
        this.releaseAudioTrack();
    }
    
    public void start() {
        synchronized (this) {
            if (this.a != null) {
                this.stop();
            }
            (this.a = new Thread(this, "FMODAudioDevice")).setPriority(10);
            this.b = true;
            this.a.start();
            if (this.g != null) {
                this.g.b();
            }
        }
    }
    
    public int startAudioRecord(int a, final int n, final int n2) {
        synchronized (this) {
            if (this.g == null) {
                (this.g = new a(this, a, n)).b();
            }
            a = this.g.a();
            return a;
        }
    }
    
    public void stop() {
        synchronized (this) {
            while (this.a != null) {
                this.b = false;
                try {
                    this.a.join();
                    this.a = null;
                }
                catch (InterruptedException ex) {}
            }
            if (this.g != null) {
                this.g.c();
            }
        }
    }
    
    public void stopAudioRecord() {
        synchronized (this) {
            if (this.g != null) {
                this.g.c();
                this.g = null;
            }
        }
    }
}
