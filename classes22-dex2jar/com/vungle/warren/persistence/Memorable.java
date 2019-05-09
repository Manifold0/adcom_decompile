// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import android.support.annotation.NonNull;

public interface Memorable
{
    @NonNull
    String getId();
    
    byte[] toByteArray();
}
