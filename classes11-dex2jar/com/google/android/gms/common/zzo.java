// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import java.util.concurrent.Callable;

final class zzo extends zzm
{
    private final Callable<String> zzaf;
    
    private zzo(final Callable<String> zzaf) {
        super(false, null, null);
        this.zzaf = zzaf;
    }
    
    @Override
    final String getErrorMessage() {
        try {
            return this.zzaf.call();
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
