// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.os.AsyncTask;
import java.util.concurrent.Executor;

public class AsyncTaskUtils
{
    private static Executor sCustomExecutor;
    
    @SuppressLint({ "InlinedApi" })
    public static void execute(final AsyncTask<Void, ?, ?> asyncTask) {
        if (Build$VERSION.SDK_INT <= 12) {
            asyncTask.execute((Object[])new Void[0]);
            return;
        }
        Executor executor;
        if (AsyncTaskUtils.sCustomExecutor != null) {
            executor = AsyncTaskUtils.sCustomExecutor;
        }
        else {
            executor = AsyncTask.THREAD_POOL_EXECUTOR;
        }
        asyncTask.executeOnExecutor(executor, (Object[])new Void[0]);
    }
    
    public static Executor getCustomExecutor() {
        return AsyncTaskUtils.sCustomExecutor;
    }
    
    public static void setCustomExecutor(final Executor sCustomExecutor) {
        AsyncTaskUtils.sCustomExecutor = sCustomExecutor;
    }
}
