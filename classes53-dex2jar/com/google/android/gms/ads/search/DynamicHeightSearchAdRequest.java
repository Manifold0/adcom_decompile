// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.search;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.internal.ads.zzlw;
import android.content.Context;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import android.os.Bundle;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class DynamicHeightSearchAdRequest
{
    private final SearchAdRequest zzdgw;
    
    private DynamicHeightSearchAdRequest(final Builder builder) {
        this.zzdgw = builder.zzdgx.build();
    }
    
    public final <T extends CustomEvent> Bundle getCustomEventExtrasBundle(final Class<T> clazz) {
        return this.zzdgw.getCustomEventExtrasBundle(clazz);
    }
    
    @Deprecated
    public final <T extends NetworkExtras> T getNetworkExtras(final Class<T> clazz) {
        return this.zzdgw.getNetworkExtras(clazz);
    }
    
    public final <T extends MediationAdapter> Bundle getNetworkExtrasBundle(final Class<T> clazz) {
        return this.zzdgw.getNetworkExtrasBundle(clazz);
    }
    
    public final String getQuery() {
        return this.zzdgw.getQuery();
    }
    
    public final boolean isTestDevice(final Context context) {
        return this.zzdgw.isTestDevice(context);
    }
    
    final zzlw zzay() {
        return this.zzdgw.zzay();
    }
    
    public static final class Builder
    {
        private final SearchAdRequest.Builder zzdgx;
        private final Bundle zzdgy;
        
        public Builder() {
            this.zzdgx = new SearchAdRequest.Builder();
            this.zzdgy = new Bundle();
        }
        
        public final Builder addCustomEventExtrasBundle(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
            this.zzdgx.addCustomEventExtrasBundle(clazz, bundle);
            return this;
        }
        
        public final Builder addNetworkExtras(final NetworkExtras networkExtras) {
            this.zzdgx.addNetworkExtras(networkExtras);
            return this;
        }
        
        public final Builder addNetworkExtrasBundle(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
            this.zzdgx.addNetworkExtrasBundle(clazz, bundle);
            return this;
        }
        
        public final DynamicHeightSearchAdRequest build() {
            this.zzdgx.addNetworkExtrasBundle(AdMobAdapter.class, this.zzdgy);
            return new DynamicHeightSearchAdRequest(this, null);
        }
        
        public final Builder setAdBorderSelectors(final String s) {
            this.zzdgy.putString("csa_adBorderSelectors", s);
            return this;
        }
        
        public final Builder setAdTest(final boolean b) {
            final Bundle zzdgy = this.zzdgy;
            String s;
            if (b) {
                s = "on";
            }
            else {
                s = "off";
            }
            zzdgy.putString("csa_adtest", s);
            return this;
        }
        
        public final Builder setAdjustableLineHeight(final int n) {
            this.zzdgy.putString("csa_adjustableLineHeight", Integer.toString(n));
            return this;
        }
        
        public final Builder setAdvancedOptionValue(final String s, final String s2) {
            this.zzdgy.putString(s, s2);
            return this;
        }
        
        public final Builder setAttributionSpacingBelow(final int n) {
            this.zzdgy.putString("csa_attributionSpacingBelow", Integer.toString(n));
            return this;
        }
        
        public final Builder setBorderSelections(final String s) {
            this.zzdgy.putString("csa_borderSelections", s);
            return this;
        }
        
        public final Builder setChannel(final String s) {
            this.zzdgy.putString("csa_channel", s);
            return this;
        }
        
        public final Builder setColorAdBorder(final String s) {
            this.zzdgy.putString("csa_colorAdBorder", s);
            return this;
        }
        
        public final Builder setColorAdSeparator(final String s) {
            this.zzdgy.putString("csa_colorAdSeparator", s);
            return this;
        }
        
        public final Builder setColorAnnotation(final String s) {
            this.zzdgy.putString("csa_colorAnnotation", s);
            return this;
        }
        
        public final Builder setColorAttribution(final String s) {
            this.zzdgy.putString("csa_colorAttribution", s);
            return this;
        }
        
        public final Builder setColorBackground(final String s) {
            this.zzdgy.putString("csa_colorBackground", s);
            return this;
        }
        
        public final Builder setColorBorder(final String s) {
            this.zzdgy.putString("csa_colorBorder", s);
            return this;
        }
        
        public final Builder setColorDomainLink(final String s) {
            this.zzdgy.putString("csa_colorDomainLink", s);
            return this;
        }
        
        public final Builder setColorText(final String s) {
            this.zzdgy.putString("csa_colorText", s);
            return this;
        }
        
        public final Builder setColorTitleLink(final String s) {
            this.zzdgy.putString("csa_colorTitleLink", s);
            return this;
        }
        
        public final Builder setCssWidth(final int n) {
            this.zzdgy.putString("csa_width", Integer.toString(n));
            return this;
        }
        
        public final Builder setDetailedAttribution(final boolean b) {
            this.zzdgy.putString("csa_detailedAttribution", Boolean.toString(b));
            return this;
        }
        
        public final Builder setFontFamily(final String s) {
            this.zzdgy.putString("csa_fontFamily", s);
            return this;
        }
        
        public final Builder setFontFamilyAttribution(final String s) {
            this.zzdgy.putString("csa_fontFamilyAttribution", s);
            return this;
        }
        
        public final Builder setFontSizeAnnotation(final int n) {
            this.zzdgy.putString("csa_fontSizeAnnotation", Integer.toString(n));
            return this;
        }
        
        public final Builder setFontSizeAttribution(final int n) {
            this.zzdgy.putString("csa_fontSizeAttribution", Integer.toString(n));
            return this;
        }
        
        public final Builder setFontSizeDescription(final int n) {
            this.zzdgy.putString("csa_fontSizeDescription", Integer.toString(n));
            return this;
        }
        
        public final Builder setFontSizeDomainLink(final int n) {
            this.zzdgy.putString("csa_fontSizeDomainLink", Integer.toString(n));
            return this;
        }
        
        public final Builder setFontSizeTitle(final int n) {
            this.zzdgy.putString("csa_fontSizeTitle", Integer.toString(n));
            return this;
        }
        
        public final Builder setHostLanguage(final String s) {
            this.zzdgy.putString("csa_hl", s);
            return this;
        }
        
        public final Builder setIsClickToCallEnabled(final boolean b) {
            this.zzdgy.putString("csa_clickToCall", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsLocationEnabled(final boolean b) {
            this.zzdgy.putString("csa_location", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsPlusOnesEnabled(final boolean b) {
            this.zzdgy.putString("csa_plusOnes", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsSellerRatingsEnabled(final boolean b) {
            this.zzdgy.putString("csa_sellerRatings", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsSiteLinksEnabled(final boolean b) {
            this.zzdgy.putString("csa_siteLinks", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsTitleBold(final boolean b) {
            this.zzdgy.putString("csa_titleBold", Boolean.toString(b));
            return this;
        }
        
        public final Builder setIsTitleUnderlined(final boolean b) {
            this.zzdgy.putString("csa_noTitleUnderline", Boolean.toString(!b));
            return this;
        }
        
        public final Builder setLocationColor(final String s) {
            this.zzdgy.putString("csa_colorLocation", s);
            return this;
        }
        
        public final Builder setLocationFontSize(final int n) {
            this.zzdgy.putString("csa_fontSizeLocation", Integer.toString(n));
            return this;
        }
        
        public final Builder setLongerHeadlines(final boolean b) {
            this.zzdgy.putString("csa_longerHeadlines", Boolean.toString(b));
            return this;
        }
        
        public final Builder setNumber(final int n) {
            this.zzdgy.putString("csa_number", Integer.toString(n));
            return this;
        }
        
        public final Builder setPage(final int n) {
            this.zzdgy.putString("csa_adPage", Integer.toString(n));
            return this;
        }
        
        public final Builder setQuery(final String query) {
            this.zzdgx.setQuery(query);
            return this;
        }
        
        public final Builder setVerticalSpacing(final int n) {
            this.zzdgy.putString("csa_verticalSpacing", Integer.toString(n));
            return this;
        }
    }
}
