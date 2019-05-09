package com.google.android.gms.internal.ads;

public abstract class zzazz<MessageType extends zzazy<MessageType, BuilderType>, BuilderType extends zzazz<MessageType, BuilderType>> implements zzbcv {
    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzaax();
    }

    protected abstract BuilderType zza(MessageType messageType);

    public abstract BuilderType zzaax();

    public final /* synthetic */ zzbcv zzd(zzbcu zzbcu) {
        if (zzadg().getClass().isInstance(zzbcu)) {
            return zza((zzazy) zzbcu);
        }
        throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
    }
}
