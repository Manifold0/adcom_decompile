// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.text.TextUtils;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.internal.drive.zzaw;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.Objects;

public class ExecutionOptions
{
    public static final int CONFLICT_STRATEGY_KEEP_REMOTE = 1;
    public static final int CONFLICT_STRATEGY_OVERWRITE_REMOTE = 0;
    public static final int MAX_TRACKING_TAG_STRING_LENGTH = 65536;
    private final String zzal;
    private final boolean zzam;
    private final int zzan;
    
    public ExecutionOptions(final String zzal, final boolean zzam, final int zzan) {
        this.zzal = zzal;
        this.zzam = zzam;
        this.zzan = zzan;
    }
    
    public static boolean zza(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 1: {
                return true;
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (o == null || o.getClass() != this.getClass()) {
            b2 = false;
        }
        else {
            b2 = b;
            if (o != this) {
                final ExecutionOptions executionOptions = (ExecutionOptions)o;
                if (Objects.equal((Object)this.zzal, (Object)executionOptions.zzal) && this.zzan == executionOptions.zzan) {
                    b2 = b;
                    if (this.zzam == executionOptions.zzam) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zzal, this.zzan, this.zzam });
    }
    
    @Deprecated
    public final void zza(final GoogleApiClient googleApiClient) {
        this.zza((zzaw)googleApiClient.getClient((Api$AnyClientKey)Drive.CLIENT_KEY));
    }
    
    public final void zza(final zzaw zzaw) {
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
    
    public static class Builder
    {
        protected String zzao;
        protected boolean zzap;
        protected int zzaq;
        
        public Builder() {
            this.zzaq = 0;
        }
        
        public ExecutionOptions build() {
            this.zzn();
            return new ExecutionOptions(this.zzao, this.zzap, this.zzaq);
        }
        
        public Builder setConflictStrategy(final int zzaq) {
            int n = 0;
            switch (zzaq) {
                default: {
                    n = 0;
                    break;
                }
                case 0:
                case 1: {
                    n = 1;
                    break;
                }
            }
            if (n == 0) {
                throw new IllegalArgumentException(new StringBuilder(53).append("Unrecognized value for conflict strategy: ").append(zzaq).toString());
            }
            this.zzaq = zzaq;
            return this;
        }
        
        public Builder setNotifyOnCompletion(final boolean zzap) {
            this.zzap = zzap;
            return this;
        }
        
        public Builder setTrackingTag(final String zzao) {
            int n;
            if (!TextUtils.isEmpty((CharSequence)zzao) && zzao.length() <= 65536) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n == 0) {
                throw new IllegalArgumentException(String.format("trackingTag must not be null nor empty, and the length must be <= the maximum length (%s)", 65536));
            }
            this.zzao = zzao;
            return this;
        }
        
        protected final void zzn() {
            if (this.zzaq == 1 && !this.zzap) {
                throw new IllegalStateException("Cannot use CONFLICT_STRATEGY_KEEP_REMOTE without requesting completion notifications");
            }
        }
    }
}
