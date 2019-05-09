// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class zzazy<MessageType extends zzazy<MessageType, BuilderType>, BuilderType extends zzazz<MessageType, BuilderType>> implements zzbcu
{
    private static boolean zzdpg;
    protected int zzdpf;
    
    static {
        zzazy.zzdpg = false;
    }
    
    public zzazy() {
        this.zzdpf = 0;
    }
    
    @Override
    public final byte[] toByteArray() {
        try {
            final byte[] array = new byte[this.zzacw()];
            final zzbav zzq = zzbav.zzq(array);
            this.zzb(zzq);
            zzq.zzacl();
            return array;
        }
        catch (IOException ex) {
            final String name = this.getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("byte array").length()).append("Serializing ").append(name).append(" to a ").append("byte array").append(" threw an IOException (should never happen).").toString(), ex);
        }
    }
    
    @Override
    public final zzbah zzaav() {
        try {
            final zzbam zzbo = zzbah.zzbo(this.zzacw());
            this.zzb(zzbo.zzabj());
            return zzbo.zzabi();
        }
        catch (IOException ex) {
            final String name = this.getClass().getName();
            throw new RuntimeException(new StringBuilder(String.valueOf(name).length() + 62 + String.valueOf("ByteString").length()).append("Serializing ").append(name).append(" to a ").append("ByteString").append(" threw an IOException (should never happen).").toString(), ex);
        }
    }
    
    int zzaaw() {
        throw new UnsupportedOperationException();
    }
    
    void zzbj(final int n) {
        throw new UnsupportedOperationException();
    }
}
