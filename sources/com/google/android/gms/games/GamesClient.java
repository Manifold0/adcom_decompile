package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class GamesClient extends zzu {
    GamesClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    GamesClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Bundle> getActivationHint() {
        return doRead(new zzs(this));
    }

    public Task<String> getAppId() {
        return doRead(new zzq(this));
    }

    @RequiresPermission("android.permission.GET_ACCOUNTS")
    public Task<String> getCurrentAccountName() {
        return doRead(new zzp(this));
    }

    @KeepForSdk
    public Task<Integer> getSdkVariant() {
        return doRead(new zzt(this));
    }

    public Task<Intent> getSettingsIntent() {
        return doRead(new zzr(this));
    }

    public Task<Void> setGravityForPopups(int i) {
        return doWrite(new zzn(this, i));
    }

    public Task<Void> setViewForPopups(@NonNull View view) {
        return doWrite(new zzo(this, view));
    }
}
