// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.protocol.AdPlacementType;

public class d
{
    private static AdAdapter a;
    
    public AdAdapter a(final AdPlacementType adPlacementType) {
        if (d.a != null) {
            return d.a;
        }
        switch (d$1.a[adPlacementType.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return new e();
            }
            case 2: {
                return new g();
            }
            case 3: {
                return new i();
            }
            case 4: {
                return new j();
            }
            case 5: {
                return new f();
            }
            case 6: {
                return new k();
            }
        }
    }
}
