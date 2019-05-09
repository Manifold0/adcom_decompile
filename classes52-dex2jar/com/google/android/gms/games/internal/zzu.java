// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.video.VideoCapabilities;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import android.os.RemoteException;
import android.os.IInterface;

public interface zzu extends IInterface
{
    void onCaptureOverlayStateChanged(final int p0) throws RemoteException;
    
    void onInvitationRemoved(final String p0) throws RemoteException;
    
    void onLeftRoom(final int p0, final String p1) throws RemoteException;
    
    void onP2PConnected(final String p0) throws RemoteException;
    
    void onP2PDisconnected(final String p0) throws RemoteException;
    
    void onRealTimeMessageReceived(final RealTimeMessage p0) throws RemoteException;
    
    void onRequestRemoved(final String p0) throws RemoteException;
    
    void onSignOutComplete() throws RemoteException;
    
    void onTurnBasedMatchRemoved(final String p0) throws RemoteException;
    
    void zza(final int p0, final int p1, final String p2) throws RemoteException;
    
    void zza(final int p0, final Bundle p1) throws RemoteException;
    
    void zza(final int p0, final VideoCapabilities p1) throws RemoteException;
    
    void zza(final int p0, final String p1) throws RemoteException;
    
    void zza(final int p0, final String p1, final boolean p2) throws RemoteException;
    
    void zza(final int p0, final boolean p1) throws RemoteException;
    
    void zza(final DataHolder p0) throws RemoteException;
    
    void zza(final DataHolder p0, final DataHolder p1) throws RemoteException;
    
    void zza(final DataHolder p0, final Contents p1) throws RemoteException;
    
    void zza(final DataHolder p0, final String p1, final Contents p2, final Contents p3, final Contents p4) throws RemoteException;
    
    void zza(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zza(final DataHolder[] p0) throws RemoteException;
    
    void zzaa(final DataHolder p0) throws RemoteException;
    
    void zzab(final DataHolder p0) throws RemoteException;
    
    void zzac(final DataHolder p0) throws RemoteException;
    
    void zzad(final DataHolder p0) throws RemoteException;
    
    void zzae(final DataHolder p0) throws RemoteException;
    
    void zzaf(final DataHolder p0) throws RemoteException;
    
    void zzag(final DataHolder p0) throws RemoteException;
    
    void zzah(final DataHolder p0) throws RemoteException;
    
    void zzai(final DataHolder p0) throws RemoteException;
    
    void zzaj(final DataHolder p0) throws RemoteException;
    
    void zzak(final DataHolder p0) throws RemoteException;
    
    void zzal(final DataHolder p0) throws RemoteException;
    
    void zzam(final DataHolder p0) throws RemoteException;
    
    void zzan(final DataHolder p0) throws RemoteException;
    
    void zzao(final DataHolder p0) throws RemoteException;
    
    void zzap(final DataHolder p0) throws RemoteException;
    
    void zzaq(final DataHolder p0) throws RemoteException;
    
    void zzar(final DataHolder p0) throws RemoteException;
    
    void zzas(final DataHolder p0) throws RemoteException;
    
    void zzat(final DataHolder p0) throws RemoteException;
    
    void zzau(final DataHolder p0) throws RemoteException;
    
    void zzav(final DataHolder p0) throws RemoteException;
    
    void zzaw(final DataHolder p0) throws RemoteException;
    
    void zzax(final DataHolder p0) throws RemoteException;
    
    void zzay(final DataHolder p0) throws RemoteException;
    
    void zzaz(final DataHolder p0) throws RemoteException;
    
    void zzb(final int p0, final Bundle p1) throws RemoteException;
    
    void zzb(final int p0, final String p1) throws RemoteException;
    
    void zzb(final Status p0) throws RemoteException;
    
    void zzb(final DataHolder p0) throws RemoteException;
    
    void zzb(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zzc(final int p0) throws RemoteException;
    
    void zzc(final int p0, final Bundle p1) throws RemoteException;
    
    void zzc(final int p0, final String p1) throws RemoteException;
    
    void zzc(final DataHolder p0) throws RemoteException;
    
    void zzc(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zzd(final int p0) throws RemoteException;
    
    void zzd(final int p0, final Bundle p1) throws RemoteException;
    
    void zzd(final int p0, final String p1) throws RemoteException;
    
    void zzd(final DataHolder p0) throws RemoteException;
    
    void zzd(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zze(final int p0) throws RemoteException;
    
    void zze(final int p0, final Bundle p1) throws RemoteException;
    
    void zze(final DataHolder p0) throws RemoteException;
    
    void zze(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zzf(final int p0) throws RemoteException;
    
    void zzf(final int p0, final Bundle p1) throws RemoteException;
    
    void zzf(final DataHolder p0) throws RemoteException;
    
    void zzf(final DataHolder p0, final String[] p1) throws RemoteException;
    
    void zzg(final int p0) throws RemoteException;
    
    void zzg(final int p0, final Bundle p1) throws RemoteException;
    
    void zzg(final DataHolder p0) throws RemoteException;
    
    void zzh(final int p0) throws RemoteException;
    
    void zzh(final DataHolder p0) throws RemoteException;
    
    void zzi(final int p0) throws RemoteException;
    
    void zzi(final DataHolder p0) throws RemoteException;
    
    void zzj(final DataHolder p0) throws RemoteException;
    
    void zzk(final DataHolder p0) throws RemoteException;
    
    void zzl(final DataHolder p0) throws RemoteException;
    
    void zzm(final DataHolder p0) throws RemoteException;
    
    void zzn(final DataHolder p0) throws RemoteException;
    
    void zzo(final DataHolder p0) throws RemoteException;
    
    void zzp(final DataHolder p0) throws RemoteException;
    
    void zzq(final DataHolder p0) throws RemoteException;
    
    void zzr(final DataHolder p0) throws RemoteException;
    
    void zzs(final DataHolder p0) throws RemoteException;
    
    void zzt(final DataHolder p0) throws RemoteException;
    
    void zzu(final DataHolder p0) throws RemoteException;
    
    void zzv(final DataHolder p0) throws RemoteException;
    
    void zzw(final DataHolder p0) throws RemoteException;
    
    void zzx(final DataHolder p0) throws RemoteException;
    
    void zzy(final DataHolder p0) throws RemoteException;
    
    void zzz(final DataHolder p0) throws RemoteException;
}
