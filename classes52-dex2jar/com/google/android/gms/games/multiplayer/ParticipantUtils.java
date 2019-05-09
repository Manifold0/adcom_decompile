// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer;

import com.google.android.gms.games.Player;
import java.util.ArrayList;

public final class ParticipantUtils
{
    private ParticipantUtils() {
    }
    
    public static String getParticipantId(final ArrayList<Participant> list, final String s) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            final Participant participant = list.get(i);
            final Player player = participant.getPlayer();
            if (player != null && player.getPlayerId().equals(s)) {
                return participant.getParticipantId();
            }
        }
        return null;
    }
}
