// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import android.support.annotation.NonNull;

public abstract class InvitationCallback implements OnInvitationReceivedListener
{
    @Override
    public abstract void onInvitationReceived(@NonNull final Invitation p0);
    
    @Override
    public abstract void onInvitationRemoved(@NonNull final String p0);
}
