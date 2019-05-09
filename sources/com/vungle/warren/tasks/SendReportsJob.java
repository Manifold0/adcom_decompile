package com.vungle.warren.tasks;

import android.os.Bundle;
import android.util.Log;
import com.google.gson.JsonObject;
import com.vungle.warren.Storage;
import com.vungle.warren.model.Report;
import com.vungle.warren.network.VungleApiClient;
import java.io.IOException;
import retrofit2.Response;

public class SendReportsJob implements Job {
    private static final long DEFAULT_DELAY = 30000;
    static final String TAG = SendReportsJob.class.getCanonicalName();
    private Storage storage;

    public SendReportsJob(Storage storage) {
        this.storage = storage;
    }

    public static JobInfo makeJobInfo() {
        return new JobInfo(TAG).setUpdateCurrent(true).setPriority(5).setReschedulePolicy(DEFAULT_DELAY, 1);
    }

    public int onRunJob(Bundle params, JobRunner jobRunner) {
        Log.d(TAG, "SendReportsJob: Current directory snapshot");
        for (Report r : this.storage.loadAll(Report.class)) {
            Response<JsonObject> response = VungleApiClient.reportAd(r.toReportBody()).execute();
            if (response.code() == 200) {
                this.storage.delete(r);
            } else {
                try {
                    long retryAfterHeaderValue = VungleApiClient.getRetryAfterHeaderValue(response);
                    if (retryAfterHeaderValue > 0) {
                        jobRunner.execute(makeJobInfo().setDelay(retryAfterHeaderValue));
                        return 1;
                    }
                } catch (IOException e) {
                    Log.e(TAG, Log.getStackTraceString(e));
                    return 2;
                }
            }
        }
        return 0;
    }
}
