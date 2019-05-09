// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import android.content.Context;
import java.io.Serializable;
import java.util.Collection;
import com.facebook.ads.internal.settings.AdInternalSettings;

public class AdSettings
{
    public static final boolean DEBUG = false;
    
    public static void addTestDevice(final String s) {
        AdInternalSettings.addTestDevice(s);
    }
    
    public static void addTestDevices(final Collection<String> collection) {
        AdInternalSettings.addTestDevices(collection);
    }
    
    public static void clearTestDevices() {
        AdInternalSettings.clearTestDevices();
    }
    
    public static String getMediationService() {
        return AdInternalSettings.getMediationService();
    }
    
    public static TestAdType getTestAdType() {
        final Serializable serializable = AdInternalSettings.a.getSerializable("TEST_AD_TYPE_KEY");
        if (serializable == null || !(serializable instanceof TestAdType)) {
            AdInternalSettings.a.putSerializable("TEST_AD_TYPE_KEY", (Serializable)TestAdType.DEFAULT);
            return TestAdType.DEFAULT;
        }
        return (TestAdType)serializable;
    }
    
    public static String getUrlPrefix() {
        return AdInternalSettings.getUrlPrefix();
    }
    
    public static boolean isChildDirected() {
        return AdInternalSettings.a.getBoolean("BOOL_CHILD_DIRECTED_KEY", false);
    }
    
    public static boolean isTestMode(final Context context) {
        return AdInternalSettings.isTestMode(context);
    }
    
    public static boolean isVideoAutoplay() {
        return AdInternalSettings.isVideoAutoplay();
    }
    
    public static boolean isVideoAutoplayOnMobile() {
        return AdInternalSettings.isVideoAutoplayOnMobile();
    }
    
    public static void setDebugBuild(final boolean debugBuild) {
        AdInternalSettings.setDebugBuild(debugBuild);
    }
    
    public static void setIntegrationErrorMode(final IntegrationErrorMode integrationErrorMode) {
        AdInternalSettings.a.putSerializable("SRL_INTEGRATION_ERROR_MODE_KEY", (Serializable)integrationErrorMode);
    }
    
    public static void setIsChildDirected(final boolean b) {
        AdInternalSettings.a.putBoolean("BOOL_CHILD_DIRECTED_KEY", b);
    }
    
    public static void setMediationService(final String mediationService) {
        AdInternalSettings.setMediationService(mediationService);
    }
    
    public static void setMultiprocessSupportMode(final MultiprocessSupportMode multiprocessSupportMode) {
        AdInternalSettings.c = (multiprocessSupportMode == MultiprocessSupportMode.MULTIPROCESS_SUPPORT_MODE_OFF);
    }
    
    public static void setTestAdType(final TestAdType testAdType) {
        AdInternalSettings.a.putSerializable("TEST_AD_TYPE_KEY", (Serializable)testAdType);
    }
    
    public static void setTestMode(final boolean testMode) {
        AdInternalSettings.setTestMode(testMode);
    }
    
    public static void setUrlPrefix(final String urlPrefix) {
        AdInternalSettings.setUrlPrefix(urlPrefix);
    }
    
    public static void setVideoAutoplay(final boolean videoAutoplay) {
        AdInternalSettings.setVideoAutoplay(videoAutoplay);
    }
    
    public static void setVideoAutoplayOnMobile(final boolean videoAutoplayOnMobile) {
        AdInternalSettings.setVideoAutoplayOnMobile(videoAutoplayOnMobile);
    }
    
    public static void setVisibleAnimation(final boolean visibleAnimation) {
        AdInternalSettings.setVisibleAnimation(visibleAnimation);
    }
    
    public enum IntegrationErrorMode
    {
        INTEGRATION_ERROR_CALLBACK_MODE, 
        INTEGRATION_ERROR_CRASH_DEBUG_MODE;
        
        public static final long serialVersionUID = 1L;
    }
    
    public enum MultiprocessSupportMode
    {
        MULTIPROCESS_SUPPORT_MODE_AUTO, 
        MULTIPROCESS_SUPPORT_MODE_OFF;
        
        public static final long serialVersionUID = 1L;
    }
    
    public enum TestAdType implements Serializable
    {
        CAROUSEL_IMG_SQUARE_APP_INSTALL("CAROUSEL_IMG_SQUARE_APP_INSTALL", "Carousel App install"), 
        CAROUSEL_IMG_SQUARE_LINK("CAROUSEL_IMG_SQUARE_LINK", "Carousel link"), 
        DEFAULT("DEFAULT", "Default"), 
        IMG_16_9_APP_INSTALL("IMG_16_9_APP_INSTALL", "Image App install"), 
        IMG_16_9_LINK("IMG_16_9_LINK", "Image link"), 
        VIDEO_HD_16_9_15S_APP_INSTALL("VID_HD_16_9_15S_APP_INSTALL", "Video 15 sec App install"), 
        VIDEO_HD_16_9_15S_LINK("VID_HD_16_9_15S_LINK", "Video 15 sec link"), 
        VIDEO_HD_16_9_46S_APP_INSTALL("VID_HD_16_9_46S_APP_INSTALL", "Video 46 sec App install"), 
        VIDEO_HD_16_9_46S_LINK("VID_HD_16_9_46S_LINK", "Video 46 sec link"), 
        VIDEO_HD_9_16_39S_APP_INSTALL("VID_HD_9_16_39S_APP_INSTALL", "Video 39 sec App install"), 
        VIDEO_HD_9_16_39S_LINK("VID_HD_9_16_39S_LINK", "Video 39 sec link");
        
        public static final long serialVersionUID = 1L;
        private final String a;
        private final String b;
        
        private TestAdType(final String a, final String b) {
            this.a = a;
            this.b = b;
        }
        
        public String getAdTypeString() {
            return this.a;
        }
        
        public String getHumanReadable() {
            return this.b;
        }
    }
}
