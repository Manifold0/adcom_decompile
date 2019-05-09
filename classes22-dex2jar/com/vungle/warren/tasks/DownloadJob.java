// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import java.util.Collection;
import java.util.concurrent.TimeUnit;
import com.vungle.warren.Vungle;
import android.util.Log;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.LoadAdCallback;
import java.util.concurrent.CountDownLatch;
import android.os.Bundle;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Placement;

public class DownloadJob implements Job
{
    private static final long DEFAUT_DELAY = 5000L;
    private static final String PLACEMENT_KEY = "placement";
    static final String TAG;
    private Placement placement;
    private int result;
    private final Storage storage;
    
    static {
        TAG = DownloadJob.class.getCanonicalName();
    }
    
    public DownloadJob(final Storage storage) {
        this.storage = storage;
    }
    
    public static JobInfo makeJobInfo(final String s, final boolean b) {
        final Bundle extras = new Bundle();
        extras.putString("placement", s);
        final JobInfo setExtras = new JobInfo(DownloadJob.TAG + " " + s).setUpdateCurrent(true).setExtras(extras);
        long n;
        if (b) {
            n = 5000L;
        }
        else {
            n = -1L;
        }
        return setExtras.setReschedulePolicy(n, 1).setPriority(4);
    }
    
    @Override
    public int onRunJob(final Bundle bundle, final JobRunner jobRunner) {
        final String string = bundle.getString("placement", (String)null);
        final Collection<String> validPlacements = this.storage.getValidPlacements();
        if (string == null || !validPlacements.contains(string)) {
            return 1;
        }
        this.placement = this.storage.load(string, Placement.class);
        if (this.placement == null) {
            return 1;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        Vungle.loadAd(string, new LoadAdCallback() {
            @Override
            public void onAdLoad(final String s) {
                DownloadJob.this.result = 0;
                countDownLatch.countDown();
            }
            
            @Override
            public void onError(final String s, final Throwable t) {
                if (t instanceof VungleException) {
                    final VungleException ex = (VungleException)t;
                    Log.e(DownloadJob.TAG, "scheduleJob: loadAd onError: " + ex.getExceptionCode());
                    if (ex.getExceptionCode() == 8 || ex.getExceptionCode() == 1 || ex.getExceptionCode() == 14) {
                        DownloadJob.this.result = 1;
                    }
                    else {
                        DownloadJob.this.result = 2;
                    }
                }
                else {
                    DownloadJob.this.result = 2;
                }
                countDownLatch.countDown();
            }
        });
        try {
            if (countDownLatch.await(1L, TimeUnit.MINUTES)) {
                Log.d(DownloadJob.TAG, "scheduleJob: latch await" + this.result);
                return this.result;
            }
            Log.d(DownloadJob.TAG, "scheduleJob: latch await else 2");
            return 2;
        }
        catch (InterruptedException ex) {
            Log.e(DownloadJob.TAG, Log.getStackTraceString((Throwable)ex));
            return 1;
        }
    }
}
