package com.google.android.gms.nearby.messages.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzay implements OnCompleteListener<Boolean> {
    private final /* synthetic */ TaskCompletionSource zzic;

    zzay(zzak zzak, TaskCompletionSource taskCompletionSource) {
        this.zzic = taskCompletionSource;
    }

    public final void onComplete(@NonNull Task<Boolean> task) {
        if (task.isSuccessful()) {
            this.zzic.setResult(null);
        } else {
            this.zzic.setException(task.getException());
        }
    }
}
