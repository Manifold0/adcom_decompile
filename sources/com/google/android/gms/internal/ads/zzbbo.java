package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzbbo<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazy<MessageType, BuilderType> {
    private static Map<Object, zzbbo<?, ?>> zzdtv = new ConcurrentHashMap();
    protected zzbef zzdtt = zzbef.zzagc();
    private int zzdtu = -1;

    public static abstract class zza<MessageType extends zzbbo<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>> extends zzazz<MessageType, BuilderType> {
        private final MessageType zzdtw;
        protected MessageType zzdtx;
        private boolean zzdty = false;

        protected zza(MessageType messageType) {
            this.zzdtw = messageType;
            this.zzdtx = (zzbbo) messageType.zza(zze.zzdud, null, null);
        }

        private static void zza(MessageType messageType, MessageType messageType2) {
            zzbdg.zzaeo().zzab(messageType).zzc(messageType, messageType2);
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzbbo zzbbo;
            zza zza = (zza) this.zzdtw.zza(zze.zzdue, null, null);
            if (this.zzdty) {
                zzbbo = this.zzdtx;
            } else {
                zzbbo = this.zzdtx;
                zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
                this.zzdty = true;
                zzbbo = this.zzdtx;
            }
            zza.zza(zzbbo);
            return zza;
        }

        public final boolean isInitialized() {
            return zzbbo.zza(this.zzdtx, false);
        }

        protected final /* synthetic */ zzazz zza(zzazy zzazy) {
            return zza((zzbbo) zzazy);
        }

        public final BuilderType zza(MessageType messageType) {
            zzadh();
            zza(this.zzdtx, messageType);
            return this;
        }

        public final /* synthetic */ zzazz zzaax() {
            return (zza) clone();
        }

        public final /* synthetic */ zzbcu zzadg() {
            return this.zzdtw;
        }

        protected final void zzadh() {
            if (this.zzdty) {
                zzbbo zzbbo = (zzbbo) this.zzdtx.zza(zze.zzdud, null, null);
                zza(zzbbo, this.zzdtx);
                this.zzdtx = zzbbo;
                this.zzdty = false;
            }
        }

        public final MessageType zzadi() {
            zzbbo zzbbo;
            boolean z;
            if (this.zzdty) {
                zzbbo = this.zzdtx;
            } else {
                zzbbo = this.zzdtx;
                zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
                this.zzdty = true;
                zzbbo = this.zzdtx;
            }
            zzbbo = zzbbo;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzbbo.zza(zze.zzdua, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzaa = zzbdg.zzaeo().zzab(zzbbo).zzaa(zzbbo);
                if (booleanValue) {
                    zzbbo.zza(zze.zzdub, zzaa ? zzbbo : null, null);
                }
                z = zzaa;
            }
            if (z) {
                return zzbbo;
            }
            throw new zzbed(zzbbo);
        }

        public final /* synthetic */ zzbcu zzadj() {
            if (this.zzdty) {
                return this.zzdtx;
            }
            zzbbo zzbbo = this.zzdtx;
            zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
            this.zzdty = true;
            return this.zzdtx;
        }

        public final /* synthetic */ zzbcu zzadk() {
            zzbbo zzbbo;
            boolean z;
            if (this.zzdty) {
                zzbbo = this.zzdtx;
            } else {
                zzbbo = this.zzdtx;
                zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
                this.zzdty = true;
                zzbbo = this.zzdtx;
            }
            zzbbo = zzbbo;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zzbbo.zza(zze.zzdua, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzaa = zzbdg.zzaeo().zzab(zzbbo).zzaa(zzbbo);
                if (booleanValue) {
                    zzbbo.zza(zze.zzdub, zzaa ? zzbbo : null, null);
                }
                z = zzaa;
            }
            if (z) {
                return zzbbo;
            }
            throw new zzbed(zzbbo);
        }
    }

    public static class zzb<T extends zzbbo<T, ?>> extends zzbaa<T> {
        private T zzdtw;

        public zzb(T t) {
            this.zzdtw = t;
        }
    }

    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbbo<MessageType, BuilderType> implements zzbcw {
        protected zzbbg<Object> zzdtz = zzbbg.zzacv();
    }

    public static class zzd<ContainingType extends zzbcu, Type> extends zzbaz<ContainingType, Type> {
    }

    public enum zze {
        public static final int zzdua = 1;
        public static final int zzdub = 2;
        public static final int zzduc = 3;
        public static final int zzdud = 4;
        public static final int zzdue = 5;
        public static final int zzduf = 6;
        public static final int zzdug = 7;
        private static final /* synthetic */ int[] zzduh = new int[]{zzdua, zzdub, zzduc, zzdud, zzdue, zzduf, zzdug};
        public static final int zzdui = 1;
        public static final int zzduj = 2;
        private static final /* synthetic */ int[] zzduk = new int[]{zzdui, zzduj};
        public static final int zzdul = 1;
        public static final int zzdum = 2;
        private static final /* synthetic */ int[] zzdun = new int[]{zzdul, zzdum};

        /* renamed from: values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0 */
        public static int[] m0x126d66cb() {
            return (int[]) zzduh.clone();
        }
    }

    protected static <T extends zzbbo<T, ?>> T zza(T t, zzbah zzbah) throws zzbbu {
        byte byteValue;
        boolean z;
        boolean zzaa;
        T zza = zza((zzbbo) t, zzbah, zzbbb.zzacr());
        if (zza != null) {
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byteValue = ((Byte) zza.zza(zze.zzdua, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                zzaa = zzbdg.zzaeo().zzab(zza).zzaa(zza);
                if (booleanValue) {
                    zza.zza(zze.zzdub, zzaa ? zza : null, null);
                }
                z = zzaa;
            }
            if (!z) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        if (zza != null) {
            zzaa = Boolean.TRUE.booleanValue();
            byteValue = ((Byte) zza.zza(zze.zzdua, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzaa2 = zzbdg.zzaeo().zzab(zza).zzaa(zza);
                if (zzaa) {
                    zza.zza(zze.zzdub, zzaa2 ? zza : null, null);
                }
                z = zzaa2;
            }
            if (!z) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        return zza;
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, zzbah zzbah, zzbbb zzbbb) throws zzbbu {
        T zza;
        try {
            zzbaq zzabf = zzbah.zzabf();
            zza = zza((zzbbo) t, zzabf, zzbbb);
            zzabf.zzbp(0);
            return zza;
        } catch (zzbbu e) {
            throw e.zzj(zza);
        } catch (zzbbu e2) {
            throw e2;
        }
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, zzbaq zzbaq, zzbbb zzbbb) throws zzbbu {
        zzbbo zzbbo = (zzbbo) t.zza(zze.zzdud, null, null);
        try {
            zzbdg.zzaeo().zzab(zzbbo).zza(zzbbo, zzbat.zza(zzbaq), zzbbb);
            zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
            return zzbbo;
        } catch (IOException e) {
            if (e.getCause() instanceof zzbbu) {
                throw ((zzbbu) e.getCause());
            }
            throw new zzbbu(e.getMessage()).zzj(zzbbo);
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof zzbbu) {
                throw ((zzbbu) e2.getCause());
            }
            throw e2;
        }
    }

    private static <T extends zzbbo<T, ?>> T zza(T t, byte[] bArr) throws zzbbu {
        zzbbo zzbbo = (zzbbo) t.zza(zze.zzdud, null, null);
        try {
            zzbdg.zzaeo().zzab(zzbbo).zza(zzbbo, bArr, 0, bArr.length, new zzbae());
            zzbdg.zzaeo().zzab(zzbbo).zzo(zzbbo);
            if (zzbbo.zzdpf == 0) {
                return zzbbo;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbbu) {
                throw ((zzbbu) e.getCause());
            }
            throw new zzbbu(e.getMessage()).zzj(zzbbo);
        } catch (IndexOutOfBoundsException e2) {
            throw zzbbu.zzadl().zzj(zzbbo);
        }
    }

    protected static Object zza(zzbcu zzbcu, String str, Object[] objArr) {
        return new zzbdi(zzbcu, str, objArr);
    }

    static Object zza(Method method, Object obj, Object... objArr) {
        Throwable e;
        try {
            return method.invoke(obj, objArr);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e2);
        } catch (InvocationTargetException e3) {
            e2 = e3.getCause();
            if (e2 instanceof RuntimeException) {
                throw ((RuntimeException) e2);
            } else if (e2 instanceof Error) {
                throw ((Error) e2);
            } else {
                throw new RuntimeException("Unexpected exception thrown by generated accessor method.", e2);
            }
        }
    }

    protected static <T extends zzbbo<?, ?>> void zza(Class<T> cls, T t) {
        zzdtv.put(cls, t);
    }

    protected static final <T extends zzbbo<T, ?>> boolean zza(T t, boolean z) {
        byte byteValue = ((Byte) t.zza(zze.zzdua, null, null)).byteValue();
        return byteValue == (byte) 1 ? true : byteValue == (byte) 0 ? false : zzbdg.zzaeo().zzab(t).zzaa(t);
    }

    protected static <E> zzbbt<E> zzadd() {
        return zzbdh.zzaep();
    }

    protected static <T extends zzbbo<T, ?>> T zzb(T t, byte[] bArr) throws zzbbu {
        T zza = zza((zzbbo) t, bArr);
        if (zza != null) {
            boolean z;
            boolean booleanValue = Boolean.TRUE.booleanValue();
            byte byteValue = ((Byte) zza.zza(zze.zzdua, null, null)).byteValue();
            if (byteValue == (byte) 1) {
                z = true;
            } else if (byteValue == (byte) 0) {
                z = false;
            } else {
                boolean zzaa = zzbdg.zzaeo().zzab(zza).zzaa(zza);
                if (booleanValue) {
                    zza.zza(zze.zzdub, zzaa ? zza : null, null);
                }
                z = zzaa;
            }
            if (!z) {
                throw new zzbed(zza).zzaga().zzj(zza);
            }
        }
        return zza;
    }

    static <T extends zzbbo<?, ?>> T zzc(Class<T> cls) {
        T t = (zzbbo) zzdtv.get(cls);
        if (t == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                t = (zzbbo) zzdtv.get(cls);
            } catch (Throwable e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (t != null) {
            return t;
        }
        String str = "Unable to get default instance for: ";
        String valueOf = String.valueOf(cls.getName());
        throw new IllegalStateException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public boolean equals(Object obj) {
        return this == obj ? true : !((zzbbo) zza(zze.zzduf, null, null)).getClass().isInstance(obj) ? false : zzbdg.zzaeo().zzab(this).equals(this, (zzbbo) obj);
    }

    public int hashCode() {
        if (this.zzdpf != 0) {
            return this.zzdpf;
        }
        this.zzdpf = zzbdg.zzaeo().zzab(this).hashCode(this);
        return this.zzdpf;
    }

    public final boolean isInitialized() {
        boolean booleanValue = Boolean.TRUE.booleanValue();
        byte byteValue = ((Byte) zza(zze.zzdua, null, null)).byteValue();
        if (byteValue == (byte) 1) {
            return true;
        }
        if (byteValue == (byte) 0) {
            return false;
        }
        boolean zzaa = zzbdg.zzaeo().zzab(this).zzaa(this);
        if (booleanValue) {
            zza(zze.zzdub, zzaa ? this : null, null);
        }
        return zzaa;
    }

    public String toString() {
        return zzbcx.zza(this, super.toString());
    }

    protected abstract Object zza(int i, Object obj, Object obj2);

    final int zzaaw() {
        return this.zzdtu;
    }

    public final int zzacw() {
        if (this.zzdtu == -1) {
            this.zzdtu = zzbdg.zzaeo().zzab(this).zzy(this);
        }
        return this.zzdtu;
    }

    public final /* synthetic */ zzbcv zzade() {
        zza zza = (zza) zza(zze.zzdue, null, null);
        zza.zza(this);
        return zza;
    }

    public final /* synthetic */ zzbcv zzadf() {
        return (zza) zza(zze.zzdue, null, null);
    }

    public final /* synthetic */ zzbcu zzadg() {
        return (zzbbo) zza(zze.zzduf, null, null);
    }

    public final void zzb(zzbav zzbav) throws IOException {
        zzbdg.zzaeo().zze(getClass()).zza(this, zzbax.zza(zzbav));
    }

    final void zzbj(int i) {
        this.zzdtu = i;
    }
}
