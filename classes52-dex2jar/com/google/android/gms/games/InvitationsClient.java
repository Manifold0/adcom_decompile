// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.games.multiplayer.InvitationCallback;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.content.Intent;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class InvitationsClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<Invitations.LoadInvitationsResult, InvitationBuffer> zzbh;
    
    static {
        zzbh = (PendingResultUtil$ResultConverter)new zzaa();
    }
    
    InvitationsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    InvitationsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Intent> getInvitationInboxIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzx(this));
    }
    
    public Task<AnnotatedData<InvitationBuffer>> loadInvitations() {
        return this.loadInvitations(0);
    }
    
    public Task<AnnotatedData<InvitationBuffer>> loadInvitations(final int n) {
        return zzi.zzb(Games.Invitations.loadInvitations(this.asGoogleApiClient(), n), InvitationsClient.zzbh);
    }
    
    public Task<Void> registerInvitationCallback(@NonNull final InvitationCallback invitationCallback) {
        final ListenerHolder registerListener = this.registerListener((Object)invitationCallback, InvitationCallback.class.getSimpleName());
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzy(this, registerListener, registerListener), (UnregisterListenerMethod)new zzz(this, registerListener.getListenerKey()));
    }
    
    public Task<Boolean> unregisterInvitationCallback(@NonNull final InvitationCallback invitationCallback) {
        return (Task<Boolean>)this.doUnregisterEventListener(ListenerHolders.createListenerKey((Object)invitationCallback, InvitationCallback.class.getSimpleName()));
    }
}
