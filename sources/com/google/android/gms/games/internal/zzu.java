package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;

public interface zzu extends IInterface {
    void onCaptureOverlayStateChanged(int i) throws RemoteException;

    void onInvitationRemoved(String str) throws RemoteException;

    void onLeftRoom(int i, String str) throws RemoteException;

    void onP2PConnected(String str) throws RemoteException;

    void onP2PDisconnected(String str) throws RemoteException;

    void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) throws RemoteException;

    void onRequestRemoved(String str) throws RemoteException;

    void onSignOutComplete() throws RemoteException;

    void onTurnBasedMatchRemoved(String str) throws RemoteException;

    void zza(int i, int i2, String str) throws RemoteException;

    void zza(int i, Bundle bundle) throws RemoteException;

    void zza(int i, VideoCapabilities videoCapabilities) throws RemoteException;

    void zza(int i, String str) throws RemoteException;

    void zza(int i, String str, boolean z) throws RemoteException;

    void zza(int i, boolean z) throws RemoteException;

    void zza(DataHolder dataHolder) throws RemoteException;

    void zza(DataHolder dataHolder, DataHolder dataHolder2) throws RemoteException;

    void zza(DataHolder dataHolder, Contents contents) throws RemoteException;

    void zza(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) throws RemoteException;

    void zza(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zza(DataHolder[] dataHolderArr) throws RemoteException;

    void zzaa(DataHolder dataHolder) throws RemoteException;

    void zzab(DataHolder dataHolder) throws RemoteException;

    void zzac(DataHolder dataHolder) throws RemoteException;

    void zzad(DataHolder dataHolder) throws RemoteException;

    void zzae(DataHolder dataHolder) throws RemoteException;

    void zzaf(DataHolder dataHolder) throws RemoteException;

    void zzag(DataHolder dataHolder) throws RemoteException;

    void zzah(DataHolder dataHolder) throws RemoteException;

    void zzai(DataHolder dataHolder) throws RemoteException;

    void zzaj(DataHolder dataHolder) throws RemoteException;

    void zzak(DataHolder dataHolder) throws RemoteException;

    void zzal(DataHolder dataHolder) throws RemoteException;

    void zzam(DataHolder dataHolder) throws RemoteException;

    void zzan(DataHolder dataHolder) throws RemoteException;

    void zzao(DataHolder dataHolder) throws RemoteException;

    void zzap(DataHolder dataHolder) throws RemoteException;

    void zzaq(DataHolder dataHolder) throws RemoteException;

    void zzar(DataHolder dataHolder) throws RemoteException;

    void zzas(DataHolder dataHolder) throws RemoteException;

    void zzat(DataHolder dataHolder) throws RemoteException;

    void zzau(DataHolder dataHolder) throws RemoteException;

    void zzav(DataHolder dataHolder) throws RemoteException;

    void zzaw(DataHolder dataHolder) throws RemoteException;

    void zzax(DataHolder dataHolder) throws RemoteException;

    void zzay(DataHolder dataHolder) throws RemoteException;

    void zzaz(DataHolder dataHolder) throws RemoteException;

    void zzb(int i, Bundle bundle) throws RemoteException;

    void zzb(int i, String str) throws RemoteException;

    void zzb(Status status) throws RemoteException;

    void zzb(DataHolder dataHolder) throws RemoteException;

    void zzb(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzc(int i) throws RemoteException;

    void zzc(int i, Bundle bundle) throws RemoteException;

    void zzc(int i, String str) throws RemoteException;

    void zzc(DataHolder dataHolder) throws RemoteException;

    void zzc(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzd(int i) throws RemoteException;

    void zzd(int i, Bundle bundle) throws RemoteException;

    void zzd(int i, String str) throws RemoteException;

    void zzd(DataHolder dataHolder) throws RemoteException;

    void zzd(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zze(int i) throws RemoteException;

    void zze(int i, Bundle bundle) throws RemoteException;

    void zze(DataHolder dataHolder) throws RemoteException;

    void zze(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzf(int i) throws RemoteException;

    void zzf(int i, Bundle bundle) throws RemoteException;

    void zzf(DataHolder dataHolder) throws RemoteException;

    void zzf(DataHolder dataHolder, String[] strArr) throws RemoteException;

    void zzg(int i) throws RemoteException;

    void zzg(int i, Bundle bundle) throws RemoteException;

    void zzg(DataHolder dataHolder) throws RemoteException;

    void zzh(int i) throws RemoteException;

    void zzh(DataHolder dataHolder) throws RemoteException;

    void zzi(int i) throws RemoteException;

    void zzi(DataHolder dataHolder) throws RemoteException;

    void zzj(DataHolder dataHolder) throws RemoteException;

    void zzk(DataHolder dataHolder) throws RemoteException;

    void zzl(DataHolder dataHolder) throws RemoteException;

    void zzm(DataHolder dataHolder) throws RemoteException;

    void zzn(DataHolder dataHolder) throws RemoteException;

    void zzo(DataHolder dataHolder) throws RemoteException;

    void zzp(DataHolder dataHolder) throws RemoteException;

    void zzq(DataHolder dataHolder) throws RemoteException;

    void zzr(DataHolder dataHolder) throws RemoteException;

    void zzs(DataHolder dataHolder) throws RemoteException;

    void zzt(DataHolder dataHolder) throws RemoteException;

    void zzu(DataHolder dataHolder) throws RemoteException;

    void zzv(DataHolder dataHolder) throws RemoteException;

    void zzw(DataHolder dataHolder) throws RemoteException;

    void zzx(DataHolder dataHolder) throws RemoteException;

    void zzy(DataHolder dataHolder) throws RemoteException;

    void zzz(DataHolder dataHolder) throws RemoteException;
}
