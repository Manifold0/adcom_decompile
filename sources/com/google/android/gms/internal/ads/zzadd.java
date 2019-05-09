package com.google.android.gms.internal.ads;

import java.lang.Thread.UncaughtExceptionHandler;

final class zzadd implements UncaughtExceptionHandler {
    private final /* synthetic */ zzadb zzccb;
    private final /* synthetic */ UncaughtExceptionHandler zzccc;

    zzadd(zzadb zzadb, UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.zzccb = zzadb;
        this.zzccc = uncaughtExceptionHandler;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void uncaughtException(java.lang.Thread r3, java.lang.Throwable r4) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x000e in list [B:4:0x0009]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:43)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1364497552.run(Unknown Source)
*/
        /*
        r2 = this;
        r0 = r2.zzccb;	 Catch:{ Throwable -> 0x000f, all -> 0x001f }
        r0.zza(r3, r4);	 Catch:{ Throwable -> 0x000f, all -> 0x001f }
        r0 = r2.zzccc;
        if (r0 == 0) goto L_0x000e;
    L_0x0009:
        r0 = r2.zzccc;
        r0.uncaughtException(r3, r4);
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r0 = "AdMob exception reporter failed reporting the exception.";	 Catch:{ Throwable -> 0x000f, all -> 0x001f }
        com.google.android.gms.internal.ads.zzane.m1665e(r0);	 Catch:{ Throwable -> 0x000f, all -> 0x001f }
        r0 = r2.zzccc;
        if (r0 == 0) goto L_0x000e;
    L_0x0019:
        r0 = r2.zzccc;
        r0.uncaughtException(r3, r4);
        goto L_0x000e;
    L_0x001f:
        r0 = move-exception;
        r1 = r2.zzccc;
        if (r1 == 0) goto L_0x0029;
    L_0x0024:
        r1 = r2.zzccc;
        r1.uncaughtException(r3, r4);
    L_0x0029:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzadd.uncaughtException(java.lang.Thread, java.lang.Throwable):void");
    }
}
