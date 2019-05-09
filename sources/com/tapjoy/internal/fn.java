package com.tapjoy.internal;

import android.os.SystemClock;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public final class fn {
    /* renamed from: a */
    public long f7792a;
    /* renamed from: b */
    public long f7793b;
    /* renamed from: c */
    public long f7794c;

    /* renamed from: a */
    public final boolean m7772a(String str, int i) {
        DatagramSocket datagramSocket;
        Throwable th;
        DatagramSocket datagramSocket2 = null;
        try {
            datagramSocket = new DatagramSocket();
            try {
                datagramSocket.setSoTimeout(i);
                byte[] bArr = new byte[48];
                DatagramPacket datagramPacket = new DatagramPacket(bArr, 48, InetAddress.getByName(str), 123);
                bArr[0] = (byte) 27;
                long currentTimeMillis = System.currentTimeMillis();
                long elapsedRealtime = SystemClock.elapsedRealtime();
                long j = currentTimeMillis / 1000;
                long j2 = currentTimeMillis - (1000 * j);
                j += 2208988800L;
                bArr[40] = (byte) ((int) (j >> 24));
                bArr[41] = (byte) ((int) (j >> 16));
                bArr[42] = (byte) ((int) (j >> 8));
                bArr[43] = (byte) ((int) (j >> null));
                j = (4294967296L * j2) / 1000;
                bArr[44] = (byte) ((int) (j >> 24));
                bArr[45] = (byte) ((int) (j >> 16));
                bArr[46] = (byte) ((int) (j >> 8));
                bArr[47] = (byte) ((int) (Math.random() * 255.0d));
                datagramSocket.send(datagramPacket);
                datagramSocket.receive(new DatagramPacket(bArr, 48));
                j = SystemClock.elapsedRealtime();
                currentTimeMillis += j - elapsedRealtime;
                j2 = m7771b(bArr, 24);
                long b = m7771b(bArr, 32);
                long b2 = m7771b(bArr, 40);
                elapsedRealtime = (j - elapsedRealtime) - (b2 - b);
                this.f7792a = (((b2 - currentTimeMillis) + (b - j2)) / 2) + currentTimeMillis;
                this.f7793b = j;
                this.f7794c = elapsedRealtime;
                datagramSocket.close();
                return true;
            } catch (Exception e) {
                if (datagramSocket != null) {
                    datagramSocket.close();
                }
                return false;
            } catch (Throwable th2) {
                Throwable th3 = th2;
                datagramSocket2 = datagramSocket;
                th = th3;
                if (datagramSocket2 != null) {
                    datagramSocket2.close();
                }
                throw th;
            }
        } catch (Exception e2) {
            datagramSocket = null;
            if (datagramSocket != null) {
                datagramSocket.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            if (datagramSocket2 != null) {
                datagramSocket2.close();
            }
            throw th;
        }
    }

    /* renamed from: a */
    private static long m7770a(byte[] bArr, int i) {
        int i2 = bArr[i];
        int i3 = bArr[i + 1];
        int i4 = bArr[i + 2];
        int i5 = bArr[i + 3];
        if ((i2 & 128) == 128) {
            i2 = (i2 & 127) + 128;
        }
        if ((i3 & 128) == 128) {
            i3 = (i3 & 127) + 128;
        }
        if ((i4 & 128) == 128) {
            i4 = (i4 & 127) + 128;
        }
        if ((i5 & 128) == 128) {
            i5 = (i5 & 127) + 128;
        }
        return ((long) i5) + (((((long) i3) << 16) + (((long) i2) << 24)) + (((long) i4) << 8));
    }

    /* renamed from: b */
    private static long m7771b(byte[] bArr, int i) {
        return ((m7770a(bArr, i) - 2208988800L) * 1000) + ((m7770a(bArr, i + 4) * 1000) / 4294967296L);
    }
}
