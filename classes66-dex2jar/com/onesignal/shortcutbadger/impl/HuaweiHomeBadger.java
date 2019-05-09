// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.net.Uri;
import android.os.Bundle;
import android.content.ComponentName;
import android.content.Context;
import com.onesignal.shortcutbadger.Badger;

public class HuaweiHomeBadger implements Badger
{
    @SuppressLint({ "NewApi" })
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        final Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        bundle.putString("class", componentName.getClassName());
        bundle.putInt("badgenumber", n);
        context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String)null, bundle);
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.huawei.android.launcher");
    }
}
