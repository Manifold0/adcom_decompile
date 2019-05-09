// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResult;
import android.util.Log;
import android.os.Message;
import android.os.Looper;
import com.google.android.gms.internal.base.zap;

final class zaco extends zap
{
    private final /* synthetic */ zacm zakw;
    
    public zaco(final zacm zakw, final Looper looper) {
        this.zakw = zakw;
        super(looper);
    }
    
    public final void handleMessage(Message zaf) {
        switch (zaf.what) {
            default: {
                Log.e("TransformedResultImpl", new StringBuilder(70).append("TransformationResultHandler received unknown message type: ").append(zaf.what).toString());
            }
            case 0: {
                final PendingResult pendingResult = (PendingResult)zaf.obj;
                zaf = (Message)this.zakw.zado;
                // monitorenter(zaf)
                Label_0112: {
                    if (pendingResult != null) {
                        break Label_0112;
                    }
                    try {
                        this.zakw.zakp.zad(new Status(13, "Transform returned null"));
                        return;
                    }
                    finally {
                    }
                    // monitorexit(zaf)
                }
                if (pendingResult instanceof zacd) {
                    this.zakw.zakp.zad(((zacd<?>)pendingResult).getStatus());
                    return;
                }
                this.zakw.zakp.zaa(pendingResult);
            }
            case 1: {
                final RuntimeException ex = (RuntimeException)zaf.obj;
                final String value = String.valueOf(ex.getMessage());
                String concat;
                if (value.length() != 0) {
                    concat = "Runtime exception on the transformation worker thread: ".concat(value);
                }
                else {
                    concat = new String("Runtime exception on the transformation worker thread: ");
                }
                Log.e("TransformedResultImpl", concat);
                throw ex;
            }
        }
    }
}
