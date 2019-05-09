package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;

public class ReconfigJob implements Job {
    public static final String TAG = ReconfigJob.class.getCanonicalName();
    private ReconfigCall reconfigCall;

    public interface ReconfigCall {
        void reConfigVungle();
    }

    public ReconfigJob(@NonNull ReconfigCall reconfigCall) {
        this.reconfigCall = reconfigCall;
    }

    public static JobInfo makeJobInfo(String appID) {
        Bundle extras = new Bundle();
        extras.putString("appID", appID);
        return new JobInfo(TAG).setExtras(extras).setUpdateCurrent(true).setPriority(4);
    }

    @NonNull
    public int onRunJob(Bundle params, JobRunner jobRunner) {
        if (params.getString("appID", null) == null) {
            return 1;
        }
        this.reconfigCall.reConfigVungle();
        return 0;
    }
}
