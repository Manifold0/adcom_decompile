// 
// Decompiled by Procyon v0.5.34
// 

package org.fmod;

import android.util.Log;
import android.media.AudioRecord;
import java.nio.ByteBuffer;

final class a implements Runnable
{
    private final FMODAudioDevice a;
    private final ByteBuffer b;
    private final int c;
    private final int d;
    private final int e;
    private volatile Thread f;
    private volatile boolean g;
    private AudioRecord h;
    private boolean i;
    
    a(final FMODAudioDevice a, final int c, final int d) {
        this.a = a;
        this.c = c;
        this.d = d;
        this.e = 2;
        this.b = ByteBuffer.allocateDirect(AudioRecord.getMinBufferSize(c, d, 2));
    }
    
    private void d() {
        if (this.h != null) {
            if (this.h.getState() == 1) {
                this.h.stop();
            }
            this.h.release();
            this.h = null;
        }
        this.b.position(0);
        this.i = false;
    }
    
    public final int a() {
        return this.b.capacity();
    }
    
    public final void b() {
        if (this.f != null) {
            this.c();
        }
        this.g = true;
        (this.f = new Thread(this)).start();
    }
    
    public final void c() {
        while (this.f != null) {
            this.g = false;
            try {
                this.f.join();
                this.f = null;
            }
            catch (InterruptedException ex) {}
        }
    }
    
    @Override
    public final void run() {
        int n = 3;
    Label_0102_Outer:
        while (this.g) {
            int n2 = n;
            while (true) {
                Label_0210: {
                    if (this.i || (n2 = n) <= 0) {
                        break Label_0210;
                    }
                    this.d();
                    this.h = new AudioRecord(1, this.c, this.d, this.e, this.b.capacity());
                    this.i = (this.h.getState() == 1);
                    if (!this.i) {
                        Log.e("FMOD", "AudioRecord failed to initialize (status " + this.h.getState() + ")");
                        n2 = n - 1;
                        this.d();
                        break Label_0210;
                    }
                    this.b.position(0);
                    this.h.startRecording();
                    n = 3;
                    if (this.i && this.h.getRecordingState() == 3) {
                        this.a.fmodProcessMicData(this.b, this.h.read(this.b, this.b.capacity()));
                        this.b.position(0);
                        continue Label_0102_Outer;
                    }
                    continue Label_0102_Outer;
                }
                n = n2;
                continue;
            }
        }
        this.d();
    }
}
