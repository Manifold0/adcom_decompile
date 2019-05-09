// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.tasks;

import android.text.TextUtils;
import com.vungle.warren.Storage;
import com.vungle.warren.persistence.Designer;

public class VungleJobCreator implements JobCreator
{
    private final Designer designer;
    private final ReconfigJob.ReconfigCall reconfigCall;
    private final Storage storage;
    
    public VungleJobCreator(final Storage storage, final Designer designer, final ReconfigJob.ReconfigCall reconfigCall) {
        this.storage = storage;
        this.designer = designer;
        this.reconfigCall = reconfigCall;
    }
    
    @Override
    public Job create(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Job tag is not specified");
        }
        if (s.startsWith(ReconfigJob.TAG)) {
            return new ReconfigJob(this.reconfigCall);
        }
        if (s.startsWith(DownloadJob.TAG)) {
            return new DownloadJob(this.storage);
        }
        if (s.startsWith(SendReportsJob.TAG)) {
            return new SendReportsJob(this.storage);
        }
        if (s.startsWith(CleanupJob.TAG)) {
            return new CleanupJob(this.designer, this.storage);
        }
        throw new IllegalArgumentException("Unknown Job Type " + s);
    }
}
