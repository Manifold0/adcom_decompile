package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.base.zap;

final class zaco extends zap {
    private final /* synthetic */ zacm zakw;

    public zaco(zacm zacm, Looper looper) {
        this.zakw = zacm;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 0:
                PendingResult pendingResult = (PendingResult) message.obj;
                synchronized (this.zakw.zado) {
                    if (pendingResult == null) {
                        this.zakw.zakp.zad(new Status(13, "Transform returned null"));
                    } else if (pendingResult instanceof zacd) {
                        this.zakw.zakp.zad(((zacd) pendingResult).getStatus());
                    } else {
                        this.zakw.zakp.zaa(pendingResult);
                    }
                }
                return;
            case 1:
                RuntimeException runtimeException = (RuntimeException) message.obj;
                String str = "TransformedResultImpl";
                String str2 = "Runtime exception on the transformation worker thread: ";
                String valueOf = String.valueOf(runtimeException.getMessage());
                if (valueOf.length() != 0) {
                    valueOf = str2.concat(valueOf);
                } else {
                    valueOf = new String(str2);
                }
                Log.e(str, valueOf);
                throw runtimeException;
            default:
                Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + message.what);
                return;
        }
    }
}
