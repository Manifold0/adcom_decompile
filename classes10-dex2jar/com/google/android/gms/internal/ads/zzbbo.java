// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public abstract class zzbbo<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazy<MessageType, BuilderType>
{
    private static Map<Object, zzbbo<?, ?>> zzdtv;
    protected zzbef zzdtt;
    private int zzdtu;
    
    static {
        zzbbo.zzdtv = new ConcurrentHashMap<Object, zzbbo<?, ?>>();
    }
    
    public zzbbo() {
        this.zzdtt = zzbef.zzagc();
        this.zzdtu = -1;
    }
    
    protected static <T extends zzbbo<T, ?>> T zza(final T t, final zzbah zzbah) throws zzbbu {
        final zzbbo<T, ?> zza = zza(t, zzbah, zzbbb.zzacr());
        if (zza != null) {
            final boolean booleanValue = Boolean.TRUE;
            final byte byteValue = (byte)zza.zza(zze.zzdua, null, (Object)null);
            boolean zzaa;
            if (byteValue == 1) {
                zzaa = true;
            }
            else if (byteValue == 0) {
                zzaa = false;
            }
            else {
                zzaa = zzbdg.zzaeo().zzab(zza).zzaa((T)zza);
                if (booleanValue) {
                    final int zzdub = zze.zzdub;
                    zzbbo<T, ?> zzbbo;
                    if (zzaa) {
                        zzbbo = zza;
                    }
                    else {
                        zzbbo = null;
                    }
                    zza.zza(zzdub, zzbbo, null);
                }
            }
            if (!zzaa) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        if (zza != null) {
            final boolean booleanValue2 = Boolean.TRUE;
            final byte byteValue2 = (byte)zza.zza(zze.zzdua, null, (Object)null);
            boolean zzaa2;
            if (byteValue2 == 1) {
                zzaa2 = true;
            }
            else if (byteValue2 == 0) {
                zzaa2 = false;
            }
            else {
                zzaa2 = zzbdg.zzaeo().zzab(zza).zzaa((T)zza);
                if (booleanValue2) {
                    final int zzdub2 = zze.zzdub;
                    zzbbo<T, ?> zzbbo2;
                    if (zzaa2) {
                        zzbbo2 = zza;
                    }
                    else {
                        zzbbo2 = null;
                    }
                    zza.zza(zzdub2, zzbbo2, null);
                }
            }
            if (!zzaa2) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        return (T)zza;
    }
    
    private static <T extends zzbbo<T, ?>> T zza(T zza, final zzbah zzbah, final zzbbb zzbbb) throws zzbbu {
        try {
            final zzbaq zzabf = zzbah.zzabf();
            zza = zza(zza, zzabf, zzbbb);
            try {
                zzabf.zzbp(0);
                return zza;
            }
            catch (zzbbu zzbbu) {
                throw zzbbu.zzj(zza);
            }
        }
        catch (zzbbu zzbbu2) {
            throw zzbbu2;
        }
    }
    
    private static <T extends zzbbo<T, ?>> T zza(T t, final zzbaq zzbaq, final zzbbb zzbbb) throws zzbbu {
        t = (T)t.zza(zze.zzdud, null, (Object)null);
        try {
            zzbdg.zzaeo().zzab(t).zza(t, zzbat.zza(zzbaq), zzbbb);
            zzbdg.zzaeo().zzab(t).zzo(t);
            return t;
        }
        catch (IOException ex) {
            if (ex.getCause() instanceof zzbbu) {
                throw (zzbbu)ex.getCause();
            }
            throw new zzbbu(ex.getMessage()).zzj(t);
        }
        catch (RuntimeException ex2) {
            if (ex2.getCause() instanceof zzbbu) {
                throw (zzbbu)ex2.getCause();
            }
            throw ex2;
        }
    }
    
    private static <T extends zzbbo<T, ?>> T zza(T t, final byte[] array) throws zzbbu {
        t = (T)t.zza(zze.zzdud, null, (Object)null);
        try {
            zzbdg.zzaeo().zzab(t).zza(t, array, 0, array.length, new zzbae());
            zzbdg.zzaeo().zzab(t).zzo(t);
            if (t.zzdpf != 0) {
                throw new RuntimeException();
            }
        }
        catch (IOException ex) {
            if (ex.getCause() instanceof zzbbu) {
                throw (zzbbu)ex.getCause();
            }
            throw new zzbbu(ex.getMessage()).zzj(t);
        }
        catch (IndexOutOfBoundsException ex2) {
            throw zzbbu.zzadl().zzj(t);
        }
        return t;
    }
    
    protected static Object zza(final zzbcu zzbcu, final String s, final Object[] array) {
        return new zzbdi(zzbcu, s, array);
    }
    
    static Object zza(final Method method, final Object o, final Object... array) {
        try {
            return method.invoke(o, array);
        }
        catch (IllegalAccessException ex) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", ex);
        }
        catch (InvocationTargetException ex2) {
            final Throwable cause = ex2.getCause();
            if (cause instanceof RuntimeException) {
                throw (RuntimeException)cause;
            }
            if (cause instanceof Error) {
                throw (Error)cause;
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }
    
    protected static <T extends zzbbo<?, ?>> void zza(final Class<T> clazz, final T t) {
        zzbbo.zzdtv.put(clazz, t);
    }
    
    protected static final <T extends zzbbo<T, ?>> boolean zza(final T t, final boolean b) {
        final byte byteValue = (byte)t.zza(zze.zzdua, null, (Object)null);
        return byteValue == 1 || (byteValue != 0 && zzbdg.zzaeo().zzab(t).zzaa(t));
    }
    
    protected static <E> zzbbt<E> zzadd() {
        return (zzbbt<E>)zzbdh.zzaep();
    }
    
    protected static <T extends zzbbo<T, ?>> T zzb(final T t, final byte[] array) throws zzbbu {
        final zzbbo zza = zza((zzbbo)t, array);
        if (zza != null) {
            final boolean booleanValue = Boolean.TRUE;
            final byte byteValue = (byte)zza.zza(zze.zzdua, null, null);
            boolean zzaa;
            if (byteValue == 1) {
                zzaa = true;
            }
            else if (byteValue == 0) {
                zzaa = false;
            }
            else {
                zzaa = zzbdg.zzaeo().zzab(zza).zzaa((T)zza);
                if (booleanValue) {
                    final int zzdub = zze.zzdub;
                    zzbbo<T, ?> zzbbo;
                    if (zzaa) {
                        zzbbo = (zzbbo<T, ?>)zza;
                    }
                    else {
                        zzbbo = null;
                    }
                    zza.zza(zzdub, zzbbo, null);
                }
            }
            if (!zzaa) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        return (T)zza;
    }
    
    static <T extends zzbbo<?, ?>> T zzc(final Class<T> clazz) {
        while (true) {
            zzbbo<?, ?> zzbbo;
            if ((zzbbo = com.google.android.gms.internal.ads.zzbbo.zzdtv.get(clazz)) == null) {
                while (true) {
                    while (true) {
                        try {
                            Class.forName(clazz.getName(), true, clazz.getClassLoader());
                            zzbbo = com.google.android.gms.internal.ads.zzbbo.zzdtv.get(clazz);
                            if (zzbbo != null) {
                                break;
                            }
                            final String value = String.valueOf(clazz.getName());
                            if (value.length() != 0) {
                                final String concat = "Unable to get default instance for: ".concat(value);
                                throw new IllegalStateException(concat);
                            }
                        }
                        catch (ClassNotFoundException ex) {
                            throw new IllegalStateException("Class initialization cannot fail.", ex);
                        }
                        final String concat = new String("Unable to get default instance for: ");
                        continue;
                    }
                }
                return (T)zzbbo;
            }
            continue;
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        return this == o || (((zzbbo)this.zza(zze.zzduf, null, (Object)null)).getClass().isInstance(o) && zzbdg.zzaeo().zzab(this).equals(this, (zzbbo)o));
    }
    
    @Override
    public int hashCode() {
        if (this.zzdpf != 0) {
            return this.zzdpf;
        }
        return this.zzdpf = zzbdg.zzaeo().zzab(this).hashCode(this);
    }
    
    @Override
    public final boolean isInitialized() {
        final boolean booleanValue = Boolean.TRUE;
        final byte byteValue = (byte)this.zza(zze.zzdua, null, (Object)null);
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        final boolean zzaa = zzbdg.zzaeo().zzab(this).zzaa(this);
        if (booleanValue) {
            final int zzdub = zze.zzdub;
            zzbbo zzbbo;
            if (zzaa) {
                zzbbo = this;
            }
            else {
                zzbbo = null;
            }
            this.zza(zzdub, zzbbo, null);
        }
        return zzaa;
    }
    
    @Override
    public String toString() {
        return zzbcx.zza(this, super.toString());
    }
    
    protected abstract Object zza(final int p0, final Object p1, final Object p2);
    
    @Override
    final int zzaaw() {
        return this.zzdtu;
    }
    
    @Override
    public final int zzacw() {
        if (this.zzdtu == -1) {
            this.zzdtu = zzbdg.zzaeo().zzab(this).zzy(this);
        }
        return this.zzdtu;
    }
    
    @Override
    public final void zzb(final zzbav zzbav) throws IOException {
        zzbdg.zzaeo().zze(this.getClass()).zza((zzbbo)this, (zzbey)zzbax.zza(zzbav));
    }
    
    @Override
    final void zzbj(final int zzdtu) {
        this.zzdtu = zzdtu;
    }
    
    public static class zza<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazz<MessageType, BuilderType>
    {
        private final MessageType zzdtw;
        protected MessageType zzdtx;
        private boolean zzdty;
        
        protected zza(final MessageType zzdtw) {
            this.zzdtw = zzdtw;
            this.zzdtx = (MessageType)zzdtw.zza(zze.zzdud, null, (Object)null);
            this.zzdty = false;
        }
        
        private static void zza(final MessageType messageType, final MessageType messageType2) {
            zzbdg.zzaeo().zzab(messageType).zzc(messageType, messageType2);
        }
        
        @Override
        public final boolean isInitialized() {
            return zzbbo.zza(this.zzdtx, false);
        }
        
        public final BuilderType zza(final MessageType messageType) {
            this.zzadh();
            zza(this.zzdtx, messageType);
            return (BuilderType)this;
        }
        
        protected final void zzadh() {
            if (this.zzdty) {
                final zzbbo zzdtx = (zzbbo)this.zzdtx.zza(zze.zzdud, null, (Object)null);
                zza((MessageType)zzdtx, this.zzdtx);
                this.zzdtx = (MessageType)zzdtx;
                this.zzdty = false;
            }
        }
        
        public final MessageType zzadi() {
            zzbbo<MessageType, BuilderType> zzbbo;
            if (this.zzdty) {
                zzbbo = this.zzdtx;
            }
            else {
                final zzbbo<MessageType, BuilderType> zzdtx = this.zzdtx;
                zzbdg.zzaeo().zzab(zzdtx).zzo(zzdtx);
                this.zzdty = true;
                zzbbo = this.zzdtx;
            }
            final zzbbo<MessageType, BuilderType> zzbbo2 = zzbbo;
            final boolean booleanValue = Boolean.TRUE;
            final byte byteValue = (byte)zzbbo2.zza(zze.zzdua, null, (Object)null);
            boolean zzaa;
            if (byteValue == 1) {
                zzaa = true;
            }
            else if (byteValue == 0) {
                zzaa = false;
            }
            else {
                zzaa = zzbdg.zzaeo().zzab(zzbbo2).zzaa((MessageType)zzbbo2);
                if (booleanValue) {
                    final int zzdub = zze.zzdub;
                    zzbbo<MessageType, BuilderType> zzbbo3;
                    if (zzaa) {
                        zzbbo3 = zzbbo2;
                    }
                    else {
                        zzbbo3 = null;
                    }
                    zzbbo2.zza(zzdub, zzbbo3, null);
                }
            }
            if (!zzaa) {
                throw new zzbed(zzbbo2);
            }
            return (MessageType)zzbbo2;
        }
    }
    
    public static final class zzb<T extends zzbbo<T, ?>> extends zzbaa<T>
    {
        private T zzdtw;
        
        public zzb(final T zzdtw) {
            this.zzdtw = zzdtw;
        }
    }
    
    public abstract static class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbbo<MessageType, BuilderType> implements zzbcw
    {
        protected zzbbg<Object> zzdtz;
        
        public zzc() {
            this.zzdtz = zzbbg.zzacv();
        }
    }
    
    public static final class zzd<ContainingType extends zzbcu, Type> extends zzbaz<ContainingType, Type>
    {
    }
    
    public enum zze
    {
        public static final int zzdua;
        public static final int zzdub;
        public static final int zzduc;
        public static final int zzdud;
        public static final int zzdue;
        public static final int zzduf;
        public static final int zzdug;
        private static final /* synthetic */ int[] zzduh;
        public static final int zzdui;
        public static final int zzduj;
        public static final int zzdul;
        public static final int zzdum;
        
        static {
            zzdua = 1;
            zzdub = 2;
            zzduc = 3;
            zzdud = 4;
            zzdue = 5;
            zzduf = 6;
            zzdug = 7;
            zzduh = new int[] { zze.zzdua, zze.zzdub, zze.zzduc, zze.zzdud, zze.zzdue, zze.zzduf, zze.zzdug };
            zzdui = 1;
            zzduj = 2;
            zzduk = new int[] { zze.zzdui, zze.zzduj };
            zzdul = 1;
            zzdum = 2;
            zzdun = new int[] { zze.zzdul, zze.zzdum };
        }
        
        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0() {
            return zze.zzduh.clone();
        }
    }
}
