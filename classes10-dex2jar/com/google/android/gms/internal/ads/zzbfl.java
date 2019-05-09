// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class zzbfl
{
    private static final int zzebu;
    private static final int zzebv;
    private static final int zzebw;
    private static final int zzebx;
    public static final int[] zzeby;
    public static final long[] zzebz;
    private static final float[] zzeca;
    private static final double[] zzecb;
    private static final boolean[] zzecc;
    public static final String[] zzecd;
    public static final byte[][] zzece;
    public static final byte[] zzecf;
    
    static {
        zzebu = 11;
        zzebv = 12;
        zzebw = 16;
        zzebx = 26;
        zzeby = new int[0];
        zzebz = new long[0];
        zzeca = new float[0];
        zzecb = new double[0];
        zzecc = new boolean[0];
        zzecd = new String[0];
        zzece = new byte[0][];
        zzecf = new byte[0];
    }
    
    public static final int zzb(final zzbez zzbez, final int n) throws IOException {
        int n2 = 1;
        final int position = zzbez.getPosition();
        zzbez.zzbq(n);
        while (zzbez.zzabk() == n) {
            zzbez.zzbq(n);
            ++n2;
        }
        zzbez.zzac(position, n);
        return n2;
    }
}
