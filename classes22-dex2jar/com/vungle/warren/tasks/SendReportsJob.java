// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import java.util.Iterator;
import com.google.gson.JsonObject;
import retrofit2.Response;
import java.io.IOException;
import com.vungle.warren.persistence.Memorable;
import com.vungle.warren.network.VungleApiClient;
import com.vungle.warren.model.Report;
import android.util.Log;
import android.os.Bundle;
import com.vungle.warren.Storage;

public class SendReportsJob implements Job
{
    private static final long DEFAULT_DELAY = 30000L;
    static final String TAG;
    private Storage storage;
    
    static {
        TAG = SendReportsJob.class.getCanonicalName();
    }
    
    public SendReportsJob(final Storage storage) {
        this.storage = storage;
    }
    
    public static JobInfo makeJobInfo() {
        return new JobInfo(SendReportsJob.TAG).setUpdateCurrent(true).setPriority(5).setReschedulePolicy(30000L, 1);
    }
    
    @Override
    public int onRunJob(final Bundle bundle, final JobRunner jobRunner) {
        Log.d(SendReportsJob.TAG, "SendReportsJob: Current directory snapshot");
        for (final Report report : this.storage.loadAll(Report.class)) {
            Response execute;
            try {
                execute = VungleApiClient.reportAd(report.toReportBody()).execute();
                if (execute.code() == 200) {
                    this.storage.delete(report);
                    continue;
                }
            }
            catch (IOException ex) {
                Log.e(SendReportsJob.TAG, Log.getStackTraceString((Throwable)ex));
                return 2;
            }
            final long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue((Response<JsonObject>)execute);
            if (retryAfterHeaderValue > 0L) {
                jobRunner.execute(makeJobInfo().setDelay(retryAfterHeaderValue));
                return 1;
            }
        }
        return 0;
    }
}
