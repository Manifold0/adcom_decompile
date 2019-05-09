// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.location.zzbh;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public interface Geofence
{
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1L;
    
    String getRequestId();
    
    @VisibleForTesting
    public static final class Builder
    {
        private String zzad;
        private int zzae;
        private long zzaf;
        private short zzag;
        private double zzah;
        private double zzai;
        private float zzaj;
        private int zzak;
        private int zzal;
        
        public Builder() {
            this.zzad = null;
            this.zzae = 0;
            this.zzaf = Long.MIN_VALUE;
            this.zzag = -1;
            this.zzak = 0;
            this.zzal = -1;
        }
        
        public final Geofence build() {
            if (this.zzad == null) {
                throw new IllegalArgumentException("Request ID not set.");
            }
            if (this.zzae == 0) {
                throw new IllegalArgumentException("Transitions types not set.");
            }
            if ((this.zzae & 0x4) != 0x0 && this.zzal < 0) {
                throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
            }
            if (this.zzaf == Long.MIN_VALUE) {
                throw new IllegalArgumentException("Expiration not set.");
            }
            if (this.zzag == -1) {
                throw new IllegalArgumentException("Geofence region not set.");
            }
            if (this.zzak < 0) {
                throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
            }
            return new zzbh(this.zzad, this.zzae, (short)1, this.zzah, this.zzai, this.zzaj, this.zzaf, this.zzak, this.zzal);
        }
        
        public final Builder setCircularRegion(final double zzah, final double zzai, final float zzaj) {
            this.zzag = 1;
            this.zzah = zzah;
            this.zzai = zzai;
            this.zzaj = zzaj;
            return this;
        }
        
        public final Builder setExpirationDuration(final long n) {
            if (n < 0L) {
                this.zzaf = -1L;
                return this;
            }
            this.zzaf = SystemClock.elapsedRealtime() + n;
            return this;
        }
        
        public final Builder setLoiteringDelay(final int zzal) {
            this.zzal = zzal;
            return this;
        }
        
        public final Builder setNotificationResponsiveness(final int zzak) {
            this.zzak = zzak;
            return this;
        }
        
        public final Builder setRequestId(final String zzad) {
            this.zzad = zzad;
            return this;
        }
        
        public final Builder setTransitionTypes(final int zzae) {
            this.zzae = zzae;
            return this;
        }
    }
}
