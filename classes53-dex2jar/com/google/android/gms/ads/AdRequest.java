// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads;

import com.google.android.gms.common.internal.Preconditions;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzlx;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import java.util.Set;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import com.google.android.gms.internal.ads.zzlw;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class AdRequest
{
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzlw zzun;
    
    private AdRequest(final Builder builder) {
        this.zzun = new zzlw(builder.zzuo);
    }
    
    public final Date getBirthday() {
        return this.zzun.getBirthday();
    }
    
    public final String getContentUrl() {
        return this.zzun.getContentUrl();
    }
    
    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzun.getCustomEventExtrasBundle(clazz);
    }
    
    public final int getGender() {
        return this.zzun.getGender();
    }
    
    public final Set<String> getKeywords() {
        return this.zzun.getKeywords();
    }
    
    public final Location getLocation() {
        return this.zzun.getLocation();
    }
    
    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.zzun.getNetworkExtras(clazz);
    }
    
    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.zzun.getNetworkExtrasBundle(clazz);
    }
    
    public final boolean isTestDevice(final Context context) {
        return this.zzun.isTestDevice(context);
    }
    
    public final zzlw zzay() {
        return this.zzun;
    }
    
    @VisibleForTesting
    public static final class Builder
    {
        private final zzlx zzuo;
        
        public Builder() {
            (this.zzuo = new zzlx()).zzad("B3EEABB8EE11C2BE770B684D95219ECB");
        }
        
        public final Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzuo.zzb(clazz, bundle);
            return this;
        }
        
        public final Builder addKeyword(final String s) {
            this.zzuo.zzac(s);
            return this;
        }
        
        public final Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.zzuo.zza(networkExtras);
            return this;
        }
        
        public final Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzuo.zza(clazz, bundle);
            if (clazz.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
                this.zzuo.zzae("B3EEABB8EE11C2BE770B684D95219ECB");
            }
            return this;
        }
        
        public final Builder addTestDevice(final String s) {
            this.zzuo.zzad(s);
            return this;
        }
        
        public final AdRequest build() {
            return new AdRequest(this, null);
        }
        
        public final Builder setBirthday(final Date date) {
            this.zzuo.zza(date);
            return this;
        }
        
        public final Builder setContentUrl(final String s) {
            Preconditions.checkNotNull((Object)s, (Object)"Content URL must be non-null.");
            Preconditions.checkNotEmpty(s, (Object)"Content URL must be non-empty.");
            Preconditions.checkArgument(s.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[] { 512, s.length() });
            this.zzuo.zzaf(s);
            return this;
        }
        
        public final Builder setGender(final int n) {
            this.zzuo.zzt(n);
            return this;
        }
        
        public final Builder setIsDesignedForFamilies(final boolean b) {
            this.zzuo.zzk(b);
            return this;
        }
        
        public final Builder setLocation(final Location location) {
            this.zzuo.zzb(location);
            return this;
        }
        
        public final Builder setRequestAgent(final String s) {
            this.zzuo.zzah(s);
            return this;
        }
        
        public final Builder tagForChildDirectedTreatment(final boolean b) {
            this.zzuo.zzj(b);
            return this;
        }
    }
}
