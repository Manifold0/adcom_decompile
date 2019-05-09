// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import android.support.annotation.NonNull;

public abstract class PayloadCallback
{
    public abstract void onPayloadReceived(@NonNull final String p0, @NonNull final Payload p1);
    
    public abstract void onPayloadTransferUpdate(@NonNull final String p0, @NonNull final PayloadTransferUpdate p1);
}
