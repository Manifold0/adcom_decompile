// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

public class SSAEnums
{
    public enum BackButtonState
    {
        Controller, 
        Device, 
        None;
    }
    
    public enum ControllerState
    {
        Failed, 
        FailedToDownload, 
        FailedToLoad, 
        Loaded, 
        None, 
        Ready;
    }
    
    public enum DebugMode
    {
        MODE_0(0), 
        MODE_1(1), 
        MODE_2(2), 
        MODE_3(3);
        
        private int value;
        
        private DebugMode(final int value) {
            this.value = value;
        }
        
        public int getValue() {
            return this.value;
        }
    }
    
    public enum ProductType
    {
        Interstitial, 
        OfferWall, 
        OfferWallCredits, 
        RewardedVideo;
    }
}
