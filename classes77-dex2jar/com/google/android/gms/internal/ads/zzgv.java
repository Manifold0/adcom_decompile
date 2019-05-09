// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.ByteOrder;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

@zzadh
public final class zzgv extends zzgq
{
    private MessageDigest zzaje;
    
    public final byte[] zzx(final String s) {
        int n = 0;
        final String[] split = s.split(" ");
        Label_0080: {
            if (split.length != 1) {
                break Label_0080;
            }
            final int zzz = zzgu.zzz(split[0]);
            final ByteBuffer allocate = ByteBuffer.allocate(4);
            allocate.order(ByteOrder.LITTLE_ENDIAN);
            allocate.putInt(zzz);
            byte[] array = allocate.array();
        Label_0268_Outer:
            while (true) {
                this.zzaje = this.zzhg();
                Object mLock = this.mLock;
                int length;
                byte[] array2;
                int n2;
                int zzz2;
                int n3;
                byte[] array3;
                int zzz3;
                Label_0097_Outer:Label_0179_Outer:
                while (true) {
                    Label_0296: {
                        synchronized (mLock) {
                            if (this.zzaje == null) {
                                // monitorexit(mLock)
                                return new byte[0];
                            }
                            this.zzaje.reset();
                            this.zzaje.update(array);
                            array = this.zzaje.digest();
                            if (array.length > 4) {
                                length = 4;
                                array2 = new byte[length];
                                System.arraycopy(array, 0, array2, 0, array2.length);
                                return array2;
                            }
                            break Label_0296;
                            // iftrue(Label_0172:, split.length >= 5)
                            // iftrue(Label_0169:, n2 >= split.length)
                            while (true) {
                                Block_5: {
                                    break Block_5;
                                    while (true) {
                                        zzz2 = zzgu.zzz(split[n2]);
                                        n3 = (zzz2 >> 16 ^ (0xFFFF & zzz2));
                                        mLock = new byte[] { (byte)n3, (byte)(n3 >> 8) };
                                        array3[n2 << 1] = mLock[0];
                                        array3[(n2 << 1) + 1] = mLock[1];
                                        ++n2;
                                        continue Label_0097_Outer;
                                    }
                                }
                                array3 = new byte[split.length << 1];
                                n2 = 0;
                                continue Label_0179_Outer;
                            }
                            while (true) {
                                Block_7: {
                                    break Block_7;
                                    Label_0169: {
                                        continue Label_0268_Outer;
                                    }
                                }
                                zzz3 = zzgu.zzz(split[n]);
                                mLock[n] = (byte)(zzz3 >> 24 ^ ((zzz3 & 0xFF) ^ (zzz3 >> 8 & 0xFF) ^ (zzz3 >> 16 & 0xFF)));
                                ++n;
                                continue;
                                Label_0172: {
                                    mLock = new byte[split.length];
                                }
                                continue;
                            }
                        }
                        // iftrue(Label_0049:, n >= split.length)
                    }
                    length = array.length;
                    continue;
                }
            }
        }
    }
}
