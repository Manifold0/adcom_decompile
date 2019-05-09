// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.snapshot.zze;
import com.google.android.gms.drive.Contents;
import android.os.IBinder;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.PlayerEntity;
import android.content.Intent;
import android.os.RemoteException;
import android.os.Bundle;
import android.os.IInterface;

public interface zzy extends IInterface
{
    Bundle getConnectionHint() throws RemoteException;
    
    int zza(final zzu p0, final byte[] p1, final String p2, final String p3) throws RemoteException;
    
    Intent zza(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    Intent zza(final int p0, final byte[] p1, final int p2, final String p3) throws RemoteException;
    
    Intent zza(final PlayerEntity p0) throws RemoteException;
    
    Intent zza(final RoomEntity p0, final int p1) throws RemoteException;
    
    Intent zza(final String p0, final boolean p1, final boolean p2, final int p3) throws RemoteException;
    
    Intent zza(final int[] p0) throws RemoteException;
    
    void zza(final long p0) throws RemoteException;
    
    void zza(final IBinder p0, final Bundle p1) throws RemoteException;
    
    void zza(final Contents p0) throws RemoteException;
    
    void zza(final zzu p0) throws RemoteException;
    
    void zza(final zzu p0, final int p1) throws RemoteException;
    
    void zza(final zzu p0, final int p1, final int p2, final int p3) throws RemoteException;
    
    void zza(final zzu p0, final int p1, final int p2, final String[] p3, final Bundle p4) throws RemoteException;
    
    void zza(final zzu p0, final int p1, final boolean p2, final boolean p3) throws RemoteException;
    
    void zza(final zzu p0, final int p1, final int[] p2) throws RemoteException;
    
    void zza(final zzu p0, final long p1) throws RemoteException;
    
    void zza(final zzu p0, final Bundle p1, final int p2, final int p3) throws RemoteException;
    
    void zza(final zzu p0, final IBinder p1, final int p2, final String[] p3, final Bundle p4, final boolean p5, final long p6) throws RemoteException;
    
    void zza(final zzu p0, final IBinder p1, final String p2, final boolean p3, final long p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final int p2, final boolean p3, final boolean p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final long p2, final String p3) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final zze p2, final Contents p3) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final String p2) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final String p2, final int p3, final int p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final String p2, final zze p3, final Contents p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final boolean p2) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final boolean p2, final int p3) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final byte[] p2, final String p3, final ParticipantResult[] p4) throws RemoteException;
    
    void zza(final zzu p0, final String p1, final byte[] p2, final ParticipantResult[] p3) throws RemoteException;
    
    void zza(final zzu p0, final boolean p1) throws RemoteException;
    
    void zza(final zzu p0, final boolean p1, final String[] p2) throws RemoteException;
    
    void zza(final zzu p0, final int[] p1, final int p2, final boolean p3) throws RemoteException;
    
    void zza(final zzu p0, final String[] p1) throws RemoteException;
    
    void zza(final zzu p0, final String[] p1, final boolean p2) throws RemoteException;
    
    void zza(final zzw p0, final long p1) throws RemoteException;
    
    void zza(final String p0, final int p1) throws RemoteException;
    
    void zza(final String p0, final IBinder p1, final Bundle p2) throws RemoteException;
    
    void zza(final String p0, final zzu p1) throws RemoteException;
    
    Intent zzag() throws RemoteException;
    
    Intent zzai() throws RemoteException;
    
    int zzak() throws RemoteException;
    
    String zzam() throws RemoteException;
    
    int zzao() throws RemoteException;
    
    Intent zzaq() throws RemoteException;
    
    int zzar() throws RemoteException;
    
    int zzas() throws RemoteException;
    
    int zzat() throws RemoteException;
    
    int zzav() throws RemoteException;
    
    boolean zzaz() throws RemoteException;
    
    int zzb(final byte[] p0, final String p1, final String[] p2) throws RemoteException;
    
    Intent zzb(final String p0, final int p1, final int p2) throws RemoteException;
    
    void zzb(final long p0) throws RemoteException;
    
    void zzb(final zzu p0) throws RemoteException;
    
    void zzb(final zzu p0, final int p1) throws RemoteException;
    
    void zzb(final zzu p0, final long p1) throws RemoteException;
    
    void zzb(final zzu p0, final String p1) throws RemoteException;
    
    void zzb(final zzu p0, final String p1, final int p2, final int p3, final int p4, final boolean p5) throws RemoteException;
    
    void zzb(final zzu p0, final String p1, final int p2, final IBinder p3, final Bundle p4) throws RemoteException;
    
    void zzb(final zzu p0, final String p1, final IBinder p2, final Bundle p3) throws RemoteException;
    
    void zzb(final zzu p0, final String p1, final String p2) throws RemoteException;
    
    void zzb(final zzu p0, final String p1, final boolean p2) throws RemoteException;
    
    void zzb(final zzu p0, final boolean p1) throws RemoteException;
    
    void zzb(final zzu p0, final String[] p1) throws RemoteException;
    
    void zzb(final String p0, final int p1) throws RemoteException;
    
    void zzbd() throws RemoteException;
    
    String zzbf() throws RemoteException;
    
    DataHolder zzbg() throws RemoteException;
    
    DataHolder zzbh() throws RemoteException;
    
    Intent zzbi() throws RemoteException;
    
    Intent zzc(final int p0, final int p1, final boolean p2) throws RemoteException;
    
    void zzc(final long p0) throws RemoteException;
    
    void zzc(final zzu p0) throws RemoteException;
    
    void zzc(final zzu p0, final long p1) throws RemoteException;
    
    void zzc(final zzu p0, final String p1) throws RemoteException;
    
    void zzc(final zzu p0, final boolean p1) throws RemoteException;
    
    Intent zzd(final String p0) throws RemoteException;
    
    void zzd(final long p0) throws RemoteException;
    
    void zzd(final zzu p0) throws RemoteException;
    
    void zzd(final zzu p0, final long p1) throws RemoteException;
    
    void zzd(final zzu p0, final String p1) throws RemoteException;
    
    void zzd(final zzu p0, final boolean p1) throws RemoteException;
    
    void zzd(final String p0, final int p1) throws RemoteException;
    
    void zze(final long p0) throws RemoteException;
    
    void zze(final zzu p0, final long p1) throws RemoteException;
    
    void zze(final zzu p0, final String p1) throws RemoteException;
    
    void zze(final zzu p0, final boolean p1) throws RemoteException;
    
    void zzf(final long p0) throws RemoteException;
    
    void zzf(final zzu p0, final String p1) throws RemoteException;
    
    void zzf(final zzu p0, final boolean p1) throws RemoteException;
    
    void zzf(final String p0) throws RemoteException;
    
    void zzg(final zzu p0, final String p1) throws RemoteException;
    
    void zzh(final zzu p0, final String p1) throws RemoteException;
    
    void zzk(final int p0) throws RemoteException;
    
    String zzp() throws RemoteException;
    
    Intent zzv() throws RemoteException;
    
    Intent zzx() throws RemoteException;
    
    Intent zzy() throws RemoteException;
    
    Intent zzz() throws RemoteException;
}
