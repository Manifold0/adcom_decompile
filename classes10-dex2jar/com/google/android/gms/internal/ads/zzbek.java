// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.nio.Buffer;
import java.util.logging.Level;
import java.security.PrivilegedExceptionAction;
import java.security.AccessController;
import java.lang.reflect.Field;
import java.nio.ByteOrder;
import sun.misc.Unsafe;
import java.util.logging.Logger;

final class zzbek
{
    private static final Logger logger;
    private static final Class<?> zzdpj;
    private static final boolean zzdqm;
    private static final Unsafe zzdwf;
    private static final boolean zzdze;
    private static final boolean zzdzf;
    private static final zzd zzdzg;
    private static final boolean zzdzh;
    private static final long zzdzi;
    private static final long zzdzj;
    private static final long zzdzk;
    private static final long zzdzl;
    private static final long zzdzm;
    private static final long zzdzn;
    private static final long zzdzo;
    private static final long zzdzp;
    private static final long zzdzq;
    private static final long zzdzr;
    private static final long zzdzs;
    private static final long zzdzt;
    private static final long zzdzu;
    private static final long zzdzv;
    private static final long zzdzw;
    private static final boolean zzdzx;
    
    static {
        final Field field = null;
        logger = Logger.getLogger(zzbek.class.getName());
        zzdwf = zzagh();
        zzdpj = zzbac.zzabc();
        zzdze = zzi(Long.TYPE);
        zzdzf = zzi(Integer.TYPE);
        Object zzdzg2;
        if (zzbek.zzdwf == null) {
            zzdzg2 = null;
        }
        else if (zzbac.zzabb()) {
            if (zzbek.zzdze) {
                zzdzg2 = new zzb(zzbek.zzdwf);
            }
            else if (zzbek.zzdzf) {
                zzdzg2 = new zza(zzbek.zzdwf);
            }
            else {
                zzdzg2 = null;
            }
        }
        else {
            zzdzg2 = new zzc(zzbek.zzdwf);
        }
        zzdzg = (zzd)zzdzg2;
        zzdzh = zzagj();
        zzdqm = zzagi();
        zzdzi = zzg(byte[].class);
        zzdzj = zzg(boolean[].class);
        zzdzk = zzh(boolean[].class);
        zzdzl = zzg(int[].class);
        zzdzm = zzh(int[].class);
        zzdzn = zzg(long[].class);
        zzdzo = zzh(long[].class);
        zzdzp = zzg(float[].class);
        zzdzq = zzh(float[].class);
        zzdzr = zzg(double[].class);
        zzdzs = zzh(double[].class);
        zzdzt = zzg(Object[].class);
        zzdzu = zzh(Object[].class);
        zzdzv = zzb(zzagk());
        final Field zzb = zzb(String.class, "value");
        Field field2 = field;
        if (zzb != null) {
            field2 = field;
            if (zzb.getType() == char[].class) {
                field2 = zzb;
            }
        }
        zzdzw = zzb(field2);
        zzdzx = (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN);
    }
    
    private zzbek() {
    }
    
    static byte zza(final byte[] array, final long n) {
        return zzbek.zzdzg.zzy(array, zzbek.zzdzi + n);
    }
    
    static long zza(final Field field) {
        return zzbek.zzdzg.zza(field);
    }
    
    private static void zza(final Object o, final long n, final byte b) {
        final int zzk = zzk(o, n & 0xFFFFFFFFFFFFFFFCL);
        final int n2 = (~(int)n & 0x3) << 3;
        zzb(o, n & 0xFFFFFFFFFFFFFFFCL, (zzk & ~(255 << n2)) | (b & 0xFF) << n2);
    }
    
    static void zza(final Object o, final long n, final double n2) {
        zzbek.zzdzg.zza(o, n, n2);
    }
    
    static void zza(final Object o, final long n, final float n2) {
        zzbek.zzdzg.zza(o, n, n2);
    }
    
    static void zza(final Object o, final long n, final long n2) {
        zzbek.zzdzg.zza(o, n, n2);
    }
    
    static void zza(final Object o, final long n, final Object o2) {
        zzbek.zzdzg.zzdzy.putObject(o, n, o2);
    }
    
    static void zza(final Object o, final long n, final boolean b) {
        zzbek.zzdzg.zza(o, n, b);
    }
    
    static void zza(final byte[] array, final long n, final byte b) {
        zzbek.zzdzg.zze(array, zzbek.zzdzi + n, b);
    }
    
    static boolean zzagf() {
        return zzbek.zzdqm;
    }
    
    static boolean zzagg() {
        return zzbek.zzdzh;
    }
    
    static Unsafe zzagh() {
        try {
            return AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>)new zzbel());
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    private static boolean zzagi() {
        if (zzbek.zzdwf == null) {
            return false;
        }
        try {
            final Class<? extends Unsafe> class1 = zzbek.zzdwf.getClass();
            class1.getMethod("objectFieldOffset", Field.class);
            class1.getMethod("arrayBaseOffset", Class.class);
            class1.getMethod("arrayIndexScale", Class.class);
            class1.getMethod("getInt", Object.class, Long.TYPE);
            class1.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            class1.getMethod("getLong", Object.class, Long.TYPE);
            class1.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            class1.getMethod("getObject", Object.class, Long.TYPE);
            class1.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzbac.zzabb()) {
                return true;
            }
            class1.getMethod("getByte", Object.class, Long.TYPE);
            class1.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            class1.getMethod("getBoolean", Object.class, Long.TYPE);
            class1.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            class1.getMethod("getFloat", Object.class, Long.TYPE);
            class1.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            class1.getMethod("getDouble", Object.class, Long.TYPE);
            class1.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        }
        catch (Throwable t) {
            final Logger logger = zzbek.logger;
            final Level warning = Level.WARNING;
            final String value = String.valueOf(t);
            logger.logp(warning, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", new StringBuilder(String.valueOf(value).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(value).toString());
            return false;
        }
    }
    
    private static boolean zzagj() {
        if (zzbek.zzdwf != null) {
            try {
                final Class<? extends Unsafe> class1 = zzbek.zzdwf.getClass();
                class1.getMethod("objectFieldOffset", Field.class);
                class1.getMethod("getLong", Object.class, Long.TYPE);
                if (zzagk() != null) {
                    if (zzbac.zzabb()) {
                        return true;
                    }
                    class1.getMethod("getByte", Long.TYPE);
                    class1.getMethod("putByte", Long.TYPE, Byte.TYPE);
                    class1.getMethod("getInt", Long.TYPE);
                    class1.getMethod("putInt", Long.TYPE, Integer.TYPE);
                    class1.getMethod("getLong", Long.TYPE);
                    class1.getMethod("putLong", Long.TYPE, Long.TYPE);
                    class1.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
                    class1.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
                    return true;
                }
            }
            catch (Throwable t) {
                final Logger logger = zzbek.logger;
                final Level warning = Level.WARNING;
                final String value = String.valueOf(t);
                logger.logp(warning, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", new StringBuilder(String.valueOf(value).length() + 71).append("platform method missing - proto runtime falling back to safer methods: ").append(value).toString());
                return false;
            }
        }
        return false;
    }
    
    private static Field zzagk() {
        Label_0022: {
            if (!zzbac.zzabb()) {
                break Label_0022;
            }
            final Field zzb = zzb(Buffer.class, "effectiveDirectAddress");
            if (zzb == null) {
                break Label_0022;
            }
            return zzb;
        }
        final Field zzb2 = zzb(Buffer.class, "address");
        if (zzb2 != null) {
            final Field zzb = zzb2;
            if (zzb2.getType() == Long.TYPE) {
                return zzb;
            }
        }
        return null;
    }
    
    private static long zzb(final Field field) {
        if (field == null || zzbek.zzdzg == null) {
            return -1L;
        }
        return zzbek.zzdzg.zza(field);
    }
    
    private static Field zzb(final Class<?> clazz, final String s) {
        try {
            final Field declaredField = clazz.getDeclaredField(s);
            declaredField.setAccessible(true);
            return declaredField;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    private static void zzb(final Object o, final long n, final byte b) {
        final int zzk = zzk(o, n & 0xFFFFFFFFFFFFFFFCL);
        final int n2 = ((int)n & 0x3) << 3;
        zzb(o, n & 0xFFFFFFFFFFFFFFFCL, (zzk & ~(255 << n2)) | (b & 0xFF) << n2);
    }
    
    static void zzb(final Object o, final long n, final int n2) {
        zzbek.zzdzg.zzb(o, n, n2);
    }
    
    private static void zzb(final Object o, final long n, final boolean b) {
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        zza(o, n, (byte)(b2 ? 1 : 0));
    }
    
    private static void zzc(final Object o, final long n, final boolean b) {
        boolean b2;
        if (b) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        zzb(o, n, (byte)(b2 ? 1 : 0));
    }
    
    private static int zzg(final Class<?> clazz) {
        if (zzbek.zzdqm) {
            return zzbek.zzdzg.zzdzy.arrayBaseOffset(clazz);
        }
        return -1;
    }
    
    private static int zzh(final Class<?> clazz) {
        if (zzbek.zzdqm) {
            return zzbek.zzdzg.zzdzy.arrayIndexScale(clazz);
        }
        return -1;
    }
    
    private static boolean zzi(final Class<?> clazz) {
        if (!zzbac.zzabb()) {
            return false;
        }
        try {
            final Class<?> zzdpj = zzbek.zzdpj;
            zzdpj.getMethod("peekLong", clazz, Boolean.TYPE);
            zzdpj.getMethod("pokeLong", clazz, Long.TYPE, Boolean.TYPE);
            zzdpj.getMethod("pokeInt", clazz, Integer.TYPE, Boolean.TYPE);
            zzdpj.getMethod("peekInt", clazz, Boolean.TYPE);
            zzdpj.getMethod("pokeByte", clazz, Byte.TYPE);
            zzdpj.getMethod("peekByte", clazz);
            zzdpj.getMethod("pokeByteArray", clazz, byte[].class, Integer.TYPE, Integer.TYPE);
            zzdpj.getMethod("peekByteArray", clazz, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        }
        catch (Throwable t) {
            return false;
        }
    }
    
    static int zzk(final Object o, final long n) {
        return zzbek.zzdzg.zzk(o, n);
    }
    
    static long zzl(final Object o, final long n) {
        return zzbek.zzdzg.zzl(o, n);
    }
    
    static boolean zzm(final Object o, final long n) {
        return zzbek.zzdzg.zzm(o, n);
    }
    
    static float zzn(final Object o, final long n) {
        return zzbek.zzdzg.zzn(o, n);
    }
    
    static double zzo(final Object o, final long n) {
        return zzbek.zzdzg.zzo(o, n);
    }
    
    static Object zzp(final Object o, final long n) {
        return zzbek.zzdzg.zzdzy.getObject(o, n);
    }
    
    private static byte zzq(final Object o, final long n) {
        return (byte)(zzk(o, 0xFFFFFFFFFFFFFFFCL & n) >>> (int)(((-1L ^ n) & 0x3L) << 3));
    }
    
    private static byte zzr(final Object o, final long n) {
        return (byte)(zzk(o, 0xFFFFFFFFFFFFFFFCL & n) >>> (int)((0x3L & n) << 3));
    }
    
    private static boolean zzs(final Object o, final long n) {
        return zzq(o, n) != 0;
    }
    
    private static boolean zzt(final Object o, final long n) {
        return zzr(o, n) != 0;
    }
    
    static final class zza extends zzd
    {
        zza(final Unsafe unsafe) {
            super(unsafe);
        }
        
        @Override
        public final void zza(final Object o, final long n, final double n2) {
            ((zzd)this).zza(o, n, Double.doubleToLongBits(n2));
        }
        
        @Override
        public final void zza(final Object o, final long n, final float n2) {
            ((zzd)this).zzb(o, n, Float.floatToIntBits(n2));
        }
        
        @Override
        public final void zza(final Object o, final long n, final boolean b) {
            if (zzbek.zzdzx) {
                zzb(o, n, b);
                return;
            }
            zzc(o, n, b);
        }
        
        @Override
        public final void zze(final Object o, final long n, final byte b) {
            if (zzbek.zzdzx) {
                zza(o, n, b);
                return;
            }
            zzb(o, n, b);
        }
        
        @Override
        public final boolean zzm(final Object o, final long n) {
            if (zzbek.zzdzx) {
                return zzs(o, n);
            }
            return zzt(o, n);
        }
        
        @Override
        public final float zzn(final Object o, final long n) {
            return Float.intBitsToFloat(((zzd)this).zzk(o, n));
        }
        
        @Override
        public final double zzo(final Object o, final long n) {
            return Double.longBitsToDouble(((zzd)this).zzl(o, n));
        }
        
        @Override
        public final byte zzy(final Object o, final long n) {
            if (zzbek.zzdzx) {
                return zzq(o, n);
            }
            return zzr(o, n);
        }
    }
    
    static final class zzb extends zzd
    {
        zzb(final Unsafe unsafe) {
            super(unsafe);
        }
        
        @Override
        public final void zza(final Object o, final long n, final double n2) {
            ((zzd)this).zza(o, n, Double.doubleToLongBits(n2));
        }
        
        @Override
        public final void zza(final Object o, final long n, final float n2) {
            ((zzd)this).zzb(o, n, Float.floatToIntBits(n2));
        }
        
        @Override
        public final void zza(final Object o, final long n, final boolean b) {
            if (zzbek.zzdzx) {
                zzb(o, n, b);
                return;
            }
            zzc(o, n, b);
        }
        
        @Override
        public final void zze(final Object o, final long n, final byte b) {
            if (zzbek.zzdzx) {
                zza(o, n, b);
                return;
            }
            zzb(o, n, b);
        }
        
        @Override
        public final boolean zzm(final Object o, final long n) {
            if (zzbek.zzdzx) {
                return zzs(o, n);
            }
            return zzt(o, n);
        }
        
        @Override
        public final float zzn(final Object o, final long n) {
            return Float.intBitsToFloat(((zzd)this).zzk(o, n));
        }
        
        @Override
        public final double zzo(final Object o, final long n) {
            return Double.longBitsToDouble(((zzd)this).zzl(o, n));
        }
        
        @Override
        public final byte zzy(final Object o, final long n) {
            if (zzbek.zzdzx) {
                return zzq(o, n);
            }
            return zzr(o, n);
        }
    }
    
    static final class zzc extends zzd
    {
        zzc(final Unsafe unsafe) {
            super(unsafe);
        }
        
        @Override
        public final void zza(final Object o, final long n, final double n2) {
            this.zzdzy.putDouble(o, n, n2);
        }
        
        @Override
        public final void zza(final Object o, final long n, final float n2) {
            this.zzdzy.putFloat(o, n, n2);
        }
        
        @Override
        public final void zza(final Object o, final long n, final boolean b) {
            this.zzdzy.putBoolean(o, n, b);
        }
        
        @Override
        public final void zze(final Object o, final long n, final byte b) {
            this.zzdzy.putByte(o, n, b);
        }
        
        @Override
        public final boolean zzm(final Object o, final long n) {
            return this.zzdzy.getBoolean(o, n);
        }
        
        @Override
        public final float zzn(final Object o, final long n) {
            return this.zzdzy.getFloat(o, n);
        }
        
        @Override
        public final double zzo(final Object o, final long n) {
            return this.zzdzy.getDouble(o, n);
        }
        
        @Override
        public final byte zzy(final Object o, final long n) {
            return this.zzdzy.getByte(o, n);
        }
    }
    
    abstract static class zzd
    {
        Unsafe zzdzy;
        
        zzd(final Unsafe zzdzy) {
            this.zzdzy = zzdzy;
        }
        
        public final long zza(final Field field) {
            return this.zzdzy.objectFieldOffset(field);
        }
        
        public abstract void zza(final Object p0, final long p1, final double p2);
        
        public abstract void zza(final Object p0, final long p1, final float p2);
        
        public final void zza(final Object o, final long n, final long n2) {
            this.zzdzy.putLong(o, n, n2);
        }
        
        public abstract void zza(final Object p0, final long p1, final boolean p2);
        
        public final void zzb(final Object o, final long n, final int n2) {
            this.zzdzy.putInt(o, n, n2);
        }
        
        public abstract void zze(final Object p0, final long p1, final byte p2);
        
        public final int zzk(final Object o, final long n) {
            return this.zzdzy.getInt(o, n);
        }
        
        public final long zzl(final Object o, final long n) {
            return this.zzdzy.getLong(o, n);
        }
        
        public abstract boolean zzm(final Object p0, final long p1);
        
        public abstract float zzn(final Object p0, final long p1);
        
        public abstract double zzo(final Object p0, final long p1);
        
        public abstract byte zzy(final Object p0, final long p1);
    }
}
