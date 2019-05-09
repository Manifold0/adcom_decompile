// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.HashMap;
import android.content.ContentValues;

final class zab extends Builder
{
    zab(final String[] array, final String s) {
        super(array, null, null);
    }
    
    @Override
    public final Builder withRow(final ContentValues contentValues) {
        throw new UnsupportedOperationException("Cannot add data to empty builder");
    }
    
    @Override
    public final Builder zaa(final HashMap<String, Object> hashMap) {
        throw new UnsupportedOperationException("Cannot add data to empty builder");
    }
}
