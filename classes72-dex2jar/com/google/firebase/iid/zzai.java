// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.os.IBinder;
import android.os.Messenger;

final class zzai
{
    private final Messenger zzag;
    private final zzl zzce;
    
    zzai(final IBinder binder) throws RemoteException {
        final String interfaceDescriptor = binder.getInterfaceDescriptor();
        if ("android.os.IMessenger".equals(interfaceDescriptor)) {
            this.zzag = new Messenger(binder);
            this.zzce = null;
            return;
        }
        if ("com.google.android.gms.iid.IMessengerCompat".equals(interfaceDescriptor)) {
            this.zzce = new zzl(binder);
            this.zzag = null;
            return;
        }
        final String value = String.valueOf(interfaceDescriptor);
        String concat;
        if (value.length() != 0) {
            concat = "Invalid interface descriptor: ".concat(value);
        }
        else {
            concat = new String("Invalid interface descriptor: ");
        }
        Log.w("MessengerIpcClient", concat);
        throw new RemoteException();
    }
    
    final void send(final Message message) throws RemoteException {
        if (this.zzag != null) {
            this.zzag.send(message);
            return;
        }
        if (this.zzce != null) {
            this.zzce.send(message);
            return;
        }
        throw new IllegalStateException("Both messengers are null");
    }
}
