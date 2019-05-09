package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.internal.drive.zzaw;

public class ExecutionOptions {
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String zzal;
    private final boolean zzam;
    private final int zzan;

    public static class Builder {
        protected String zzao;
        protected boolean zzap;
        protected int zzaq = 0;

        public ExecutionOptions build() {
            zzn();
            return new ExecutionOptions(this.zzao, this.zzap, this.zzaq);
        }

        public Builder setConflictStrategy(int i) {
            Object obj;
            switch (i) {
                case 0:
                case 1:
                    obj = 1;
                    break;
                default:
                    obj = null;
                    break;
            }
            if (obj == null) {
                throw new IllegalArgumentException("Unrecognized value for conflict strategy: " + i);
            }
            this.zzaq = i;
            return this;
        }

        public Builder setNotifyOnCompletion(boolean z) {
            this.zzap = z;
            return this;
        }

        public Builder setTrackingTag(String str) {
            int i = (TextUtils.isEmpty(str) || str.length() > 65536) ? 0 : 1;
            if (i == 0) {
                throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", new Object[]{Integer.valueOf(65536)}));
            }
            this.zzao = str;
            return this;
        }

        protected final void zzn() {
            if (this.zzaq == 1 && !this.zzap) {
                throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
            }
        }
    }

    public ExecutionOptions(String str, boolean z, int i) {
        this.zzal = str;
        this.zzam = z;
        this.zzan = i;
    }

    public static boolean zza(int i) {
        switch (i) {
            case 1:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        ExecutionOptions executionOptions = (ExecutionOptions) obj;
        return Objects.equal(this.zzal, executionOptions.zzal) && this.zzan == executionOptions.zzan && this.zzam == executionOptions.zzam;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.zzal, Integer.valueOf(this.zzan), Boolean.valueOf(this.zzam)});
    }

    @Deprecated
    public final void zza(GoogleApiClient googleApiClient) {
        zza((zzaw) googleApiClient.getClient(Drive.CLIENT_KEY));
    }

    public final void zza(zzaw zzaw) {
        if (this.zzam && !zzaw.zzag()) {
            throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to be notified on completion");
        }
    }

    public final String zzk() {
        return this.zzal;
    }

    public final boolean zzl() {
        return this.zzam;
    }

    public final int zzm() {
        return this.zzan;
    }
}
