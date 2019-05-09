// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;
import android.os.Parcel;
import android.os.RemoteException;
import android.os.Message;
import android.os.Build$VERSION;
import android.os.IBinder;
import android.os.Messenger;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class zzl implements Parcelable
{
    public static final Parcelable$Creator<zzl> CREATOR;
    private Messenger zzag;
    private zzv zzah;
    
    static {
        CREATOR = (Parcelable$Creator)new zzm();
    }
    
    public zzl(final IBinder binder) {
        if (Build$VERSION.SDK_INT >= 21) {
            this.zzag = new Messenger(binder);
            return;
        }
        this.zzah = new zzw(binder);
    }
    
    private final IBinder getBinder() {
        if (this.zzag != null) {
            return this.zzag.getBinder();
        }
        return this.zzah.asBinder();
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            return this.getBinder().equals(((zzl)o).getBinder());
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return this.getBinder().hashCode();
    }
    
    public final void send(final Message message) throws RemoteException {
        if (this.zzag != null) {
            this.zzag.send(message);
            return;
        }
        this.zzah.send(message);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (this.zzag != null) {
            parcel.writeStrongBinder(this.zzag.getBinder());
            return;
        }
        parcel.writeStrongBinder(this.zzah.asBinder());
    }
    
    public static final class zza extends ClassLoader
    {
        @Override
        protected final Class<?> loadClass(final String s, final boolean b) throws ClassNotFoundException {
            if ("com.google.android.gms.iid.MessengerCompat".equals(s)) {
                if (FirebaseInstanceId.zzl()) {
                    Log.d("FirebaseInstanceId", "Using renamed FirebaseIidMessengerCompat class");
                }
                return zzl.class;
            }
            return super.loadClass(s, b);
        }
    }
}
