// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import android.support.annotation.NonNull;

public abstract class TurnBasedMatchUpdateCallback implements OnTurnBasedMatchUpdateReceivedListener
{
    @Override
    public abstract void onTurnBasedMatchReceived(@NonNull final TurnBasedMatch p0);
    
    @Override
    public abstract void onTurnBasedMatchRemoved(@NonNull final String p0);
}
