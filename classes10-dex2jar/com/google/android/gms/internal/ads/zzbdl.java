// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Map;
import java.util.List;
import java.io.IOException;

interface zzbdl
{
    int getTag();
    
    double readDouble() throws IOException;
    
    float readFloat() throws IOException;
    
    String readString() throws IOException;
    
    void readStringList(final List<String> p0) throws IOException;
    
     <T> T zza(final zzbdm<T> p0, final zzbbb p1) throws IOException;
    
     <T> void zza(final List<T> p0, final zzbdm<T> p1, final zzbbb p2) throws IOException;
    
     <K, V> void zza(final Map<K, V> p0, final zzbcn<K, V> p1, final zzbbb p2) throws IOException;
    
    void zzaa(final List<Integer> p0) throws IOException;
    
    void zzab(final List<Integer> p0) throws IOException;
    
    long zzabl() throws IOException;
    
    long zzabm() throws IOException;
    
    int zzabn() throws IOException;
    
    long zzabo() throws IOException;
    
    int zzabp() throws IOException;
    
    boolean zzabq() throws IOException;
    
    String zzabr() throws IOException;
    
    zzbah zzabs() throws IOException;
    
    int zzabt() throws IOException;
    
    int zzabu() throws IOException;
    
    int zzabv() throws IOException;
    
    long zzabw() throws IOException;
    
    int zzabx() throws IOException;
    
    long zzaby() throws IOException;
    
    void zzac(final List<Long> p0) throws IOException;
    
    int zzaci() throws IOException;
    
    boolean zzacj() throws IOException;
    
    void zzad(final List<Integer> p0) throws IOException;
    
    void zzae(final List<Long> p0) throws IOException;
    
    @Deprecated
     <T> T zzb(final zzbdm<T> p0, final zzbbb p1) throws IOException;
    
    @Deprecated
     <T> void zzb(final List<T> p0, final zzbdm<T> p1, final zzbbb p2) throws IOException;
    
    void zzp(final List<Double> p0) throws IOException;
    
    void zzq(final List<Float> p0) throws IOException;
    
    void zzr(final List<Long> p0) throws IOException;
    
    void zzs(final List<Long> p0) throws IOException;
    
    void zzt(final List<Integer> p0) throws IOException;
    
    void zzu(final List<Long> p0) throws IOException;
    
    void zzv(final List<Integer> p0) throws IOException;
    
    void zzw(final List<Boolean> p0) throws IOException;
    
    void zzx(final List<String> p0) throws IOException;
    
    void zzy(final List<zzbah> p0) throws IOException;
    
    void zzz(final List<Integer> p0) throws IOException;
}
