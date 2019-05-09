package com.google.android.gms.games.multiplayer;

@Deprecated
public interface OnInvitationReceivedListener {
    void onInvitationReceived(Invitation invitation);

    void onInvitationRemoved(String str);
}
