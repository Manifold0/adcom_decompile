// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

import java.util.Iterator;
import java.util.ArrayList;

public class OfferwallConfigurations
{
    private static final int DEFAULT_OW_PLACEMENT_ID = 0;
    private OfferwallPlacement mDefaultOWPlacement;
    private ArrayList<OfferwallPlacement> mOWPlacements;
    
    public OfferwallConfigurations() {
        this.mOWPlacements = new ArrayList<OfferwallPlacement>();
    }
    
    public void addOfferwallPlacement(final OfferwallPlacement mDefaultOWPlacement) {
        if (mDefaultOWPlacement != null) {
            this.mOWPlacements.add(mDefaultOWPlacement);
            if (mDefaultOWPlacement.getPlacementId() == 0) {
                this.mDefaultOWPlacement = mDefaultOWPlacement;
            }
        }
    }
    
    public OfferwallPlacement getDefaultOfferwallPlacement() {
        return this.mDefaultOWPlacement;
    }
    
    public OfferwallPlacement getOfferwallPlacement(final String s) {
        for (final OfferwallPlacement offerwallPlacement : this.mOWPlacements) {
            if (offerwallPlacement.getPlacementName().equals(s)) {
                return offerwallPlacement;
            }
        }
        return null;
    }
}
