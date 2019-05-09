package com.google.android.gms.games.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.internal.games.zzb;
import com.google.android.gms.internal.games.zzc;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.google.firebase.FirebaseError;

public abstract class zzv extends zzb implements zzu {
    public zzv() {
        super("com.google.android.gms.games.internal.IGamesCallbacks");
    }

    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 5001:
                zza(parcel.readInt(), parcel.readString());
                break;
            case 5002:
                zza((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5003:
                zzb(parcel.readInt(), parcel.readString());
                break;
            case 5004:
                zzc((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5005:
                zza((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), (DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5006:
                zzd((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5007:
                zze((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5008:
                zzf((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5009:
                zzg((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5010:
                zzh((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5011:
                zzi((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5016:
                onSignOutComplete();
                break;
            case 5017:
                zzk((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5018:
                zzs((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5019:
                zzt((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5020:
                onLeftRoom(parcel.readInt(), parcel.readString());
                break;
            case 5021:
                zzu((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5022:
                zzv((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5023:
                zzw((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5024:
                zzx((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5025:
                zzy((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5026:
                zza((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5027:
                zzb((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5028:
                zzc((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5029:
                zzd((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5030:
                zze((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5031:
                zzf((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.createStringArray());
                break;
            case 5032:
                onRealTimeMessageReceived((RealTimeMessage) zzc.zza(parcel, RealTimeMessage.CREATOR));
                break;
            case 5033:
                zza(parcel.readInt(), parcel.readInt(), parcel.readString());
                break;
            case 5034:
                zza(parcel.readInt(), parcel.readString(), zzc.zza(parcel));
                break;
            case 5035:
                zzaa((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5036:
                zzc(parcel.readInt());
                break;
            case 5037:
                zzl((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5038:
                zzz((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5039:
                zzab((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 5040:
                zzd(parcel.readInt());
                break;
            case 6001:
                onP2PConnected(parcel.readString());
                break;
            case 6002:
                onP2PDisconnected(parcel.readString());
                break;
            case 8001:
                zzac((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 8002:
                zza(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 8003:
                zzn((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case ConnectionsStatusCodes.STATUS_CONNECTION_REJECTED /*8004*/:
                zzo((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case ConnectionsStatusCodes.STATUS_NOT_CONNECTED_TO_ENDPOINT /*8005*/:
                zzp((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 8006:
                zzq((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case ConnectionsStatusCodes.STATUS_BLUETOOTH_ERROR /*8007*/:
                zzc(parcel.readInt(), parcel.readString());
                break;
            case ConnectionsStatusCodes.STATUS_ALREADY_HAVE_ACTIVE_STRATEGY /*8008*/:
                zzr((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case ConnectionsStatusCodes.STATUS_OUT_OF_ORDER_API_CALL /*8009*/:
                onTurnBasedMatchRemoved(parcel.readString());
                break;
            case 8010:
                onInvitationRemoved(parcel.readString());
                break;
            case GamesStatusCodes.STATUS_VIDEO_UNSUPPORTED /*9001*/:
                zzj((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case GamesActivityResultCodes.RESULT_RECONNECT_REQUIRED /*10001*/:
                zzm((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case GamesActivityResultCodes.RESULT_SIGN_IN_FAILED /*10002*/:
                onRequestRemoved(parcel.readString());
                break;
            case GamesActivityResultCodes.RESULT_LICENSE_FAILED /*10003*/:
                zzad((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case GamesActivityResultCodes.RESULT_APP_MISCONFIGURED /*10004*/:
                zzae((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case GamesActivityResultCodes.RESULT_LEFT_ROOM /*10005*/:
                zzb(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case GamesActivityResultCodes.RESULT_NETWORK_FAILURE /*10006*/:
                zzaf((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 11001:
                zzc(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 12001:
                zzag((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12003:
                zzd(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 12004:
                zza((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), (Contents) zzc.zza(parcel, Contents.CREATOR));
                break;
            case 12005:
                zzah((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12006:
                zzai((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12007:
                zzaj((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12008:
                zzam((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12011:
                zzb((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12012:
                zzd(parcel.readInt(), parcel.readString());
                break;
            case 12013:
                zzan((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12014:
                zzak((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12015:
                zze(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 12016:
                zzal((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 12017:
                zza((DataHolder) zzc.zza(parcel, DataHolder.CREATOR), parcel.readString(), (Contents) zzc.zza(parcel, Contents.CREATOR), (Contents) zzc.zza(parcel, Contents.CREATOR), (Contents) zzc.zza(parcel, Contents.CREATOR));
                break;
            case 13001:
                zzao((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 13002:
                zze(parcel.readInt());
                break;
            case 14001:
                zza((DataHolder[]) parcel.createTypedArray(DataHolder.CREATOR));
                break;
            case 15001:
                zzap((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case FirebaseError.ERROR_CUSTOM_TOKEN_MISMATCH /*17002*/:
                zzf(parcel.readInt());
                break;
            case 19001:
                zza(parcel.readInt(), (VideoCapabilities) zzc.zza(parcel, VideoCapabilities.CREATOR));
                break;
            case 19002:
                zza(parcel.readInt(), zzc.zza(parcel));
                break;
            case 19006:
                zzaq((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 19007:
                zzf(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            case 19008:
                zzg(parcel.readInt());
                break;
            case 19009:
                zzh(parcel.readInt());
                break;
            case 19010:
                zzi(parcel.readInt());
                break;
            case 20001:
                zzar((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20002:
                zzas((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20003:
                zzat((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20004:
                zzau((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20005:
                zzav((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20006:
                zzaw((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20007:
                zzax((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20008:
                zzay((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20009:
                zzaz((DataHolder) zzc.zza(parcel, DataHolder.CREATOR));
                break;
            case 20012:
                zzb((Status) zzc.zza(parcel, Status.CREATOR));
                break;
            case 20019:
                onCaptureOverlayStateChanged(parcel.readInt());
                break;
            case 20020:
                zzg(parcel.readInt(), (Bundle) zzc.zza(parcel, Bundle.CREATOR));
                break;
            default:
                return false;
        }
        parcel2.writeNoException();
        return true;
    }
}
