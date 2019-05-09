// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.multiplayer.Participant;
import android.database.CharArrayBuffer;
import android.support.annotation.Nullable;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.games.multiplayer.Participatable;
import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

@VisibleForTesting
public interface TurnBasedMatch extends Parcelable, Freezable<TurnBasedMatch>, Participatable
{
    public static final int MATCH_STATUS_ACTIVE = 1;
    public static final int MATCH_STATUS_AUTO_MATCHING = 0;
    public static final int MATCH_STATUS_CANCELED = 4;
    public static final int MATCH_STATUS_COMPLETE = 2;
    public static final int MATCH_STATUS_EXPIRED = 3;
    @KeepName
    public static final int[] MATCH_TURN_STATUS_ALL = { 0, 1, 2, 3 };
    public static final int MATCH_TURN_STATUS_COMPLETE = 3;
    public static final int MATCH_TURN_STATUS_INVITED = 0;
    public static final int MATCH_TURN_STATUS_MY_TURN = 1;
    public static final int MATCH_TURN_STATUS_THEIR_TURN = 2;
    public static final int MATCH_VARIANT_DEFAULT = -1;
    
    boolean canRematch();
    
    @Nullable
    Bundle getAutoMatchCriteria();
    
    int getAvailableAutoMatchSlots();
    
    long getCreationTimestamp();
    
    String getCreatorId();
    
    byte[] getData();
    
    String getDescription();
    
    void getDescription(final CharArrayBuffer p0);
    
    Participant getDescriptionParticipant();
    
    String getDescriptionParticipantId();
    
    Game getGame();
    
    long getLastUpdatedTimestamp();
    
    String getLastUpdaterId();
    
    String getMatchId();
    
    int getMatchNumber();
    
    Participant getParticipant(final String p0);
    
    String getParticipantId(final String p0);
    
    ArrayList<String> getParticipantIds();
    
    int getParticipantStatus(final String p0);
    
    String getPendingParticipantId();
    
    byte[] getPreviousMatchData();
    
    String getRematchId();
    
    int getStatus();
    
    int getTurnStatus();
    
    int getVariant();
    
    int getVersion();
    
    boolean isLocallyModified();
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface MatchTurnStatus {
    }
}
