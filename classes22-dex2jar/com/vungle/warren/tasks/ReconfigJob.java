// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import android.os.Bundle;
import android.support.annotation.NonNull;

public class ReconfigJob implements Job
{
    public static final String TAG;
    private ReconfigCall reconfigCall;
    
    static {
        TAG = ReconfigJob.class.getCanonicalName();
    }
    
    public ReconfigJob(@NonNull final ReconfigCall reconfigCall) {
        this.reconfigCall = reconfigCall;
    }
    
    public static JobInfo makeJobInfo(final String s) {
        final Bundle extras = new Bundle();
        extras.putString("appID", s);
        return new JobInfo(ReconfigJob.TAG).setExtras(extras).setUpdateCurrent(true).setPriority(4);
    }
    
    @NonNull
    @Override
    public int onRunJob(final Bundle bundle, final JobRunner jobRunner) {
        if (bundle.getString("appID", (String)null) == null) {
            return 1;
        }
        this.reconfigCall.reConfigVungle();
        return 0;
    }
    
    public interface ReconfigCall
    {
        void reConfigVungle();
    }
}
