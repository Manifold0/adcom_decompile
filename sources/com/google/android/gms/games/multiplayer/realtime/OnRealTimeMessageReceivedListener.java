package com.google.android.gms.games.multiplayer.realtime;

import android.support.annotation.NonNull;

public interface OnRealTimeMessageReceivedListener extends RealTimeMessageReceivedListener {
    void onRealTimeMessageReceived(@NonNull RealTimeMessage realTimeMessage);
}
