// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.drive.Contents;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import android.os.Parcelable$Creator;
import com.google.android.gms.internal.games.zzc;
import com.google.android.gms.common.data.DataHolder;
import android.os.Parcel;
import com.google.android.gms.internal.games.zzb;

public abstract class zzv extends zzb implements zzu
{
    public zzv() {
        super("com.google.android.gms.games.internal.IGamesCallbacks");
    }
    
    @Override
    protected final boolean dispatchTransaction(final int n, final Parcel parcel, final Parcel parcel2, final int n2) throws RemoteException {
        switch (n) {
            default: {
                return false;
            }
            case 5001: {
                this.zza(parcel.readInt(), parcel.readString());
                break;
            }
            case 5002: {
                this.zza(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5003: {
                this.zzb(parcel.readInt(), parcel.readString());
                break;
            }
            case 5004: {
                this.zzc(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5005: {
                this.zza(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5006: {
                this.zzd(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5007: {
                this.zze(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5008: {
                this.zzf(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5009: {
                this.zzg(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5010: {
                this.zzh(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5011: {
                this.zzi(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5016: {
                this.onSignOutComplete();
                break;
            }
            case 5017: {
                this.zzk(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5037: {
                this.zzl(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5018: {
                this.zzs(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5019: {
                this.zzt(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5020: {
                this.onLeftRoom(parcel.readInt(), parcel.readString());
                break;
            }
            case 5021: {
                this.zzu(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5022: {
                this.zzv(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5023: {
                this.zzw(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5024: {
                this.zzx(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5025: {
                this.zzy(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5026: {
                this.zza(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5027: {
                this.zzb(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5028: {
                this.zzc(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5029: {
                this.zzd(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5030: {
                this.zze(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5031: {
                this.zzf(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.createStringArray());
                break;
            }
            case 5032: {
                this.onRealTimeMessageReceived(com.google.android.gms.internal.games.zzc.zza(parcel, RealTimeMessage.CREATOR));
                break;
            }
            case 5033: {
                this.zza(parcel.readInt(), parcel.readInt(), parcel.readString());
                break;
            }
            case 5034: {
                this.zza(parcel.readInt(), parcel.readString(), com.google.android.gms.internal.games.zzc.zza(parcel));
                break;
            }
            case 5038: {
                this.zzz(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5035: {
                this.zzaa(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5036: {
                this.zzc(parcel.readInt());
                break;
            }
            case 5039: {
                this.zzab(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 5040: {
                this.zzd(parcel.readInt());
                break;
            }
            case 6001: {
                this.onP2PConnected(parcel.readString());
                break;
            }
            case 6002: {
                this.onP2PDisconnected(parcel.readString());
                break;
            }
            case 8001: {
                this.zzac(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8002: {
                this.zza(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 8003: {
                this.zzn(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8004: {
                this.zzo(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8005: {
                this.zzp(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8006: {
                this.zzq(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8007: {
                this.zzc(parcel.readInt(), parcel.readString());
                break;
            }
            case 8008: {
                this.zzr(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 8009: {
                this.onTurnBasedMatchRemoved(parcel.readString());
                break;
            }
            case 8010: {
                this.onInvitationRemoved(parcel.readString());
                break;
            }
            case 9001: {
                this.zzj(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 10001: {
                this.zzm(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 10002: {
                this.onRequestRemoved(parcel.readString());
                break;
            }
            case 10003: {
                this.zzad(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 10004: {
                this.zzae(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 10005: {
                this.zzb(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 10006: {
                this.zzaf(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 11001: {
                this.zzc(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 12001: {
                this.zzag(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12004: {
                this.zza(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Contents>)Contents.CREATOR));
                break;
            }
            case 12017: {
                this.zza(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR), parcel.readString(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Contents>)Contents.CREATOR), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Contents>)Contents.CREATOR), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Contents>)Contents.CREATOR));
                break;
            }
            case 12005: {
                this.zzah(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12012: {
                this.zzd(parcel.readInt(), parcel.readString());
                break;
            }
            case 12003: {
                this.zzd(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 12013: {
                this.zzan(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12011: {
                this.zzb(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12006: {
                this.zzai(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12007: {
                this.zzaj(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12014: {
                this.zzak(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12016: {
                this.zzal(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12008: {
                this.zzam(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 12015: {
                this.zze(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 13001: {
                this.zzao(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 13002: {
                this.zze(parcel.readInt());
                break;
            }
            case 14001: {
                this.zza((DataHolder[])parcel.createTypedArray(DataHolder.CREATOR));
                break;
            }
            case 15001: {
                this.zzap(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 17002: {
                this.zzf(parcel.readInt());
                break;
            }
            case 19001: {
                this.zza(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, VideoCapabilities.CREATOR));
                break;
            }
            case 19002: {
                this.zza(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel));
                break;
            }
            case 19006: {
                this.zzaq(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 19007: {
                this.zzf(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
            case 19008: {
                this.zzg(parcel.readInt());
                break;
            }
            case 19009: {
                this.zzh(parcel.readInt());
                break;
            }
            case 19010: {
                this.zzi(parcel.readInt());
                break;
            }
            case 20001: {
                this.zzar(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20002: {
                this.zzas(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20003: {
                this.zzat(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20004: {
                this.zzau(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20005: {
                this.zzav(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20006: {
                this.zzaw(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20007: {
                this.zzax(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20008: {
                this.zzay(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20009: {
                this.zzaz(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<DataHolder>)DataHolder.CREATOR));
                break;
            }
            case 20012: {
                this.zzb(com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Status>)Status.CREATOR));
                break;
            }
            case 20019: {
                this.onCaptureOverlayStateChanged(parcel.readInt());
                break;
            }
            case 20020: {
                this.zzg(parcel.readInt(), com.google.android.gms.internal.games.zzc.zza(parcel, (android.os.Parcelable$Creator<Bundle>)Bundle.CREATOR));
                break;
            }
        }
        parcel2.writeNoException();
        return true;
    }
}
