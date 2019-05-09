package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.StatusListener;

final class zaa implements StatusListener {
    private final /* synthetic */ Batch zabd;

    zaa(Batch batch) {
        this.zabd = batch;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onComplete(com.google.android.gms.common.api.Status r6) {
        /*
        r5 = this;
        r0 = r5.zabd;
        r1 = r0.mLock;
        monitor-enter(r1);
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r0 = r0.isCanceled();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
    L_0x0010:
        return;
    L_0x0011:
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x003c;
    L_0x0017:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r2 = 1;
        r0.zabb = true;	 Catch:{ all -> 0x0039 }
    L_0x001d:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r0.zaaz = r0.zaaz - 1;	 Catch:{ all -> 0x0039 }
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r0 = r0.zaaz;	 Catch:{ all -> 0x0039 }
        if (r0 != 0) goto L_0x0037;
    L_0x002a:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r0 = r0.zabb;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0049;
    L_0x0032:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        super.cancel();	 Catch:{ all -> 0x0039 }
    L_0x0037:
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        goto L_0x0010;
    L_0x0039:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0039 }
        throw r0;
    L_0x003c:
        r0 = r6.isSuccess();	 Catch:{ all -> 0x0039 }
        if (r0 != 0) goto L_0x001d;
    L_0x0042:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r2 = 1;
        r0.zaba = true;	 Catch:{ all -> 0x0039 }
        goto L_0x001d;
    L_0x0049:
        r0 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r0 = r0.zaba;	 Catch:{ all -> 0x0039 }
        if (r0 == 0) goto L_0x0069;
    L_0x0051:
        r0 = new com.google.android.gms.common.api.Status;	 Catch:{ all -> 0x0039 }
        r2 = 13;
        r0.<init>(r2);	 Catch:{ all -> 0x0039 }
    L_0x0058:
        r2 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r3 = new com.google.android.gms.common.api.BatchResult;	 Catch:{ all -> 0x0039 }
        r4 = r5.zabd;	 Catch:{ all -> 0x0039 }
        r4 = r4.zabc;	 Catch:{ all -> 0x0039 }
        r3.<init>(r0, r4);	 Catch:{ all -> 0x0039 }
        r2.setResult(r3);	 Catch:{ all -> 0x0039 }
        goto L_0x0037;
    L_0x0069:
        r0 = com.google.android.gms.common.api.Status.RESULT_SUCCESS;	 Catch:{ all -> 0x0039 }
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.zaa.onComplete(com.google.android.gms.common.api.Status):void");
    }
}
