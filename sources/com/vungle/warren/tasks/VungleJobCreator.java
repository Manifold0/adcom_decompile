package com.vungle.warren.tasks;

import android.text.TextUtils;
import com.vungle.warren.Storage;
import com.vungle.warren.persistence.Designer;
import com.vungle.warren.tasks.ReconfigJob.ReconfigCall;

public class VungleJobCreator implements JobCreator {
    private final Designer designer;
    private final ReconfigCall reconfigCall;
    private final Storage storage;

    public VungleJobCreator(Storage storage, Designer designer, ReconfigCall reconfigCall) {
        this.storage = storage;
        this.designer = designer;
        this.reconfigCall = reconfigCall;
    }

    public Job create(String tag) {
        if (TextUtils.isEmpty(tag)) {
            throw new IllegalArgumentException("Job tag is not specified");
        } else if (tag.startsWith(ReconfigJob.TAG)) {
            return new ReconfigJob(this.reconfigCall);
        } else {
            if (tag.startsWith(DownloadJob.TAG)) {
                return new DownloadJob(this.storage);
            }
            if (tag.startsWith(SendReportsJob.TAG)) {
                return new SendReportsJob(this.storage);
            }
            if (tag.startsWith(CleanupJob.TAG)) {
                return new CleanupJob(this.designer, this.storage);
            }
            throw new IllegalArgumentException("Unknown Job Type " + tag);
        }
    }
}
