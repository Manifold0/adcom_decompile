package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zaau implements Runnable {
    private final /* synthetic */ zaak zagj;

    private zaau(zaak zaak) {
        this.zagj = zaak;
    }

    @WorkerThread
    protected abstract void zaan();

    @WorkerThread
    public void run() {
        this.zagj.zaeo.lock();
        try {
            if (!Thread.interrupted()) {
                zaan();
                this.zagj.zaeo.unlock();
            }
        } catch (RuntimeException e) {
            this.zagj.zaft.zab(e);
        } finally {
            this.zagj.zaeo.unlock();
        }
    }
}
