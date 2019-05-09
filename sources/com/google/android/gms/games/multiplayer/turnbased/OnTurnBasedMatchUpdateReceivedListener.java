package com.google.android.gms.games.multiplayer.turnbased;

@Deprecated
public interface OnTurnBasedMatchUpdateReceivedListener {
    void onTurnBasedMatchReceived(TurnBasedMatch turnBasedMatch);

    void onTurnBasedMatchRemoved(String str);
}
