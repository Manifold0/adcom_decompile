// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby;

import com.google.android.gms.internal.nearby.zzhe;
import android.support.v4.content.PermissionChecker;
import com.google.android.gms.nearby.messages.internal.zzak;
import com.google.android.gms.nearby.messages.MessagesClient;
import android.content.Context;
import com.google.android.gms.internal.nearby.zzbd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.connection.ConnectionsClient;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.nearby.zzg;
import com.google.android.gms.nearby.messages.internal.zzby;
import com.google.android.gms.nearby.messages.internal.zzbi;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.internal.nearby.zzca;
import com.google.android.gms.internal.nearby.zze;
import com.google.android.gms.nearby.messages.zzd;
import com.google.android.gms.nearby.messages.Messages;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public final class Nearby
{
    @Deprecated
    public static final Api<Api$ApiOptions$NoOptions> CONNECTIONS_API;
    @Deprecated
    public static final Connections Connections;
    @Deprecated
    public static final Api<MessagesOptions> MESSAGES_API;
    @Deprecated
    public static final Messages Messages;
    private static final zzd zze;
    private static final Api<Api$ApiOptions$NoOptions> zzf;
    private static final zze zzg;
    
    static {
        CONNECTIONS_API = new Api("Nearby.CONNECTIONS_API", (Api$AbstractClientBuilder)zzca.CLIENT_BUILDER, (Api$ClientKey)zzca.CLIENT_KEY);
        Connections = new zzca();
        MESSAGES_API = new Api("Nearby.MESSAGES_API", (Api$AbstractClientBuilder)zzbi.CLIENT_BUILDER, (Api$ClientKey)zzbi.CLIENT_KEY);
        Messages = zzbi.zzij;
        zze = (zzd)new zzby();
        zzf = new Api("Nearby.BOOTSTRAP_API", (Api$AbstractClientBuilder)com.google.android.gms.internal.nearby.zzg.CLIENT_BUILDER, (Api$ClientKey)com.google.android.gms.internal.nearby.zzg.CLIENT_KEY);
        zzg = (zze)new zzg();
    }
    
    private Nearby() {
    }
    
    public static final ConnectionsClient getConnectionsClient(@NonNull final Activity activity) {
        Preconditions.checkNotNull((Object)activity, (Object)"Activity must not be null");
        return new zzbd(activity);
    }
    
    public static final ConnectionsClient getConnectionsClient(@NonNull final Context context) {
        Preconditions.checkNotNull((Object)context, (Object)"Context must not be null");
        return new zzbd(context);
    }
    
    public static final MessagesClient getMessagesClient(@NonNull final Activity activity) {
        Preconditions.checkNotNull((Object)activity, (Object)"Activity must not be null");
        return new zzak(activity, null);
    }
    
    public static final MessagesClient getMessagesClient(@NonNull final Activity activity, @NonNull final MessagesOptions messagesOptions) {
        Preconditions.checkNotNull((Object)activity, (Object)"Activity must not be null");
        Preconditions.checkNotNull((Object)messagesOptions, (Object)"Options must not be null");
        return new zzak(activity, messagesOptions);
    }
    
    public static final MessagesClient getMessagesClient(@NonNull final Context context) {
        Preconditions.checkNotNull((Object)context, (Object)"Context must not be null");
        return new zzak(context, null);
    }
    
    public static final MessagesClient getMessagesClient(@NonNull final Context context, @NonNull final MessagesOptions messagesOptions) {
        Preconditions.checkNotNull((Object)context, (Object)"Context must not be null");
        Preconditions.checkNotNull((Object)messagesOptions, (Object)"Options must not be null");
        return new zzak(context, messagesOptions);
    }
    
    public static boolean zza(final Context context) {
        boolean zza = true;
        if (PermissionChecker.checkCallingOrSelfPermission(context, "com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
            zza = zzhe.zza(context.getContentResolver(), "gms:nearby:requires_gms_check", true);
        }
        return zza;
    }
}
