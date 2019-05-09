// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

public enum BackoffStrategy
{
    LONG_WAIT(1, 120000L, 86400000L, 0.5, 1.0), 
    NO_WAIT(100, 1L, 1000L, 1.0, 1.0), 
    SHORT_WAIT(1, 200L, 3600000L, 0.5, 1.0), 
    TEST_WAIT(1, 200L, 1000L, 0.5, 1.0);
    
    double maxRange;
    long maxWait;
    long milliSecondMultiplier;
    double minRange;
    int minRetries;
    
    private BackoffStrategy(final int minRetries, final long milliSecondMultiplier, final long maxWait, final double minRange, final double maxRange) {
        this.minRetries = minRetries;
        this.milliSecondMultiplier = milliSecondMultiplier;
        this.maxWait = maxWait;
        this.minRange = minRange;
        this.maxRange = maxRange;
    }
}
