// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public enum PlacementCappingType
{
    PER_DAY("d"), 
    PER_HOUR("h");
    
    public String value;
    
    private PlacementCappingType(final String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        return this.value;
    }
}
