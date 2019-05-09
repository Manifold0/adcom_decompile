package com.google.firebase.iid;

import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.util.Log;

public class zzl implements Parcelable {
    public static final Creator<zzl> CREATOR = new zzm();
    private Messenger zzag;
    private zzv zzah;

    public static final class zza extends ClassLoader {
        protected final Class<?> loadClass(String str, boolean z) throws ClassNotFoundException {
            if (!"com.google.android.gms.iid.MessengerCompat".equals(str)) {
                return super.loadClass(str, z);
            }
            if (FirebaseInstanceId.zzl()) {
                Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
            }
            return zzl.class;
        }
    }

    public zzl(IBinder iBinder) {
        if (VERSION.SDK_INT >= 21) {
            this.zzag = new Messenger(iBinder);
        } else {
            this.zzah = new zzw(iBinder);
        }
    }

    public final void send(Message message) throws RemoteException {
        if (this.zzag != null) {
            this.zzag.send(message);
        } else {
            this.zzah.send(message);
        }
    }

    private final IBinder getBinder() {
        return this.zzag != null ? this.zzag.getBinder() : this.zzah.asBinder();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (obj != null) {
            try {
                z = getBinder().equals(((zzl) obj).getBinder());
            } catch (ClassCastException e) {
            }
        }
        return z;
    }

    public int hashCode() {
        return getBinder().hashCode();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (this.zzag != null) {
            parcel.writeStrongBinder(this.zzag.getBinder());
        } else {
            parcel.writeStrongBinder(this.zzah.asBinder());
        }
    }
}
