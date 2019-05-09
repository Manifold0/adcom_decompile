// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.view.View;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.os.Bundle;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.games.zzu;

public class GamesClient extends zzu
{
    GamesClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    GamesClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Bundle> getActivationHint() {
        return (Task<Bundle>)this.doRead((TaskApiCall)new zzs(this));
    }
    
    public Task<String> getAppId() {
        return (Task<String>)this.doRead((TaskApiCall)new zzq(this));
    }
    
    @RequiresPermission("android.permission.GET_ACCOUNTS")
    public Task<String> getCurrentAccountName() {
        return (Task<String>)this.doRead((TaskApiCall)new zzp(this));
    }
    
    @KeepForSdk
    public Task<Integer> getSdkVariant() {
        return (Task<Integer>)this.doRead((TaskApiCall)new zzt(this));
    }
    
    public Task<Intent> getSettingsIntent() {
        return (Task<Intent>)this.doRead((TaskApiCall)new zzr(this));
    }
    
    public Task<Void> setGravityForPopups(final int n) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzn(this, n));
    }
    
    public Task<Void> setViewForPopups(@NonNull final View view) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzo(this, view));
    }
}
