// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.search;

import com.google.android.gms.internal.ads.zzlx;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.internal.ads.zzlw;

@Deprecated
public final class SearchAdRequest
{
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR = "B3EEABB8EE11C2BE770B684D95219ECB";
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final String zzabd;
    private final zzlw zzun;
    
    private SearchAdRequest(final Builder builder) {
        this.zzabd = builder.zzabd;
        this.zzun = new zzlw(builder.zzuo, this);
    }
    
    @Deprecated
    public final int getAnchorTextColor() {
        return 0;
    }
    
    @Deprecated
    public final int getBackgroundColor() {
        return 0;
    }
    
    @Deprecated
    public final int getBackgroundGradientBottom() {
        return 0;
    }
    
    @Deprecated
    public final int getBackgroundGradientTop() {
        return 0;
    }
    
    @Deprecated
    public final int getBorderColor() {
        return 0;
    }
    
    @Deprecated
    public final int getBorderThickness() {
        return 0;
    }
    
    @Deprecated
    public final int getBorderType() {
        return 0;
    }
    
    @Deprecated
    public final int getCallButtonColor() {
        return 0;
    }
    
    @Deprecated
    public final String getCustomChannels() {
        return null;
    }
    
    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzun.getCustomEventExtrasBundle(clazz);
    }
    
    @Deprecated
    public final int getDescriptionTextColor() {
        return 0;
    }
    
    @Deprecated
    public final String getFontFace() {
        return null;
    }
    
    @Deprecated
    public final int getHeaderTextColor() {
        return 0;
    }
    
    @Deprecated
    public final int getHeaderTextSize() {
        return 0;
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
    
    public final String getQuery() {
        return this.zzabd;
    }
    
    public final boolean isTestDevice(final Context context) {
        return this.zzun.isTestDevice(context);
    }
    
    final zzlw zzay() {
        return this.zzun;
    }
    
    public static final class Builder
    {
        private String zzabd;
        private final zzlx zzuo;
        
        public Builder() {
            this.zzuo = new zzlx();
        }
        
        public final Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzuo.zzb(clazz, bundle);
            return this;
        }
        
        public final Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.zzuo.zza(networkExtras);
            return this;
        }
        
        public final Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzuo.zza(clazz, bundle);
            return this;
        }
        
        public final Builder addTestDevice(final String s) {
            this.zzuo.zzad(s);
            return this;
        }
        
        public final SearchAdRequest build() {
            return new SearchAdRequest(this, null);
        }
        
        @Deprecated
        public final Builder setAnchorTextColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setBackgroundColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setBackgroundGradient(final int n, final int n2) {
            return this;
        }
        
        @Deprecated
        public final Builder setBorderColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setBorderThickness(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setBorderType(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setCallButtonColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setCustomChannels(final String s) {
            return this;
        }
        
        @Deprecated
        public final Builder setDescriptionTextColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setFontFace(final String s) {
            return this;
        }
        
        @Deprecated
        public final Builder setHeaderTextColor(final int n) {
            return this;
        }
        
        @Deprecated
        public final Builder setHeaderTextSize(final int n) {
            return this;
        }
        
        public final Builder setLocation(final Location location) {
            this.zzuo.zzb(location);
            return this;
        }
        
        public final Builder setQuery(final String zzabd) {
            this.zzabd = zzabd;
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
