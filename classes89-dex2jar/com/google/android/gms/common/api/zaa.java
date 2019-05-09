// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

final class zaa implements StatusListener
{
    private final /* synthetic */ Batch zabd;
    
    zaa(final Batch zabd) {
        this.zabd = zabd;
    }
    
    @Override
    public final void onComplete(Status result_SUCCESS) {
        while (true) {
        Label_0101:
            while (true) {
                synchronized (this.zabd.mLock) {
                    if (this.zabd.isCanceled()) {
                        return;
                    }
                    if (result_SUCCESS.isCanceled()) {
                        Batch.zaa(this.zabd, true);
                        this.zabd.zaaz--;
                        if (this.zabd.zaaz == 0) {
                            if (!this.zabd.zabb) {
                                break Label_0101;
                            }
                            this.zabd.cancel();
                        }
                        return;
                    }
                }
                final Status status;
                if (!status.isSuccess()) {
                    Batch.zab(this.zabd, true);
                    continue;
                }
                continue;
            }
            if (this.zabd.zaba) {
                result_SUCCESS = new Status(13);
            }
            else {
                result_SUCCESS = Status.RESULT_SUCCESS;
            }
            this.zabd.setResult(new BatchResult(result_SUCCESS, this.zabd.zabc));
        }
    }
}
