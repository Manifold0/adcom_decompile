// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import android.util.Log;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.Intent;
import com.google.firebase.FirebaseApp;
import android.text.TextUtils;
import com.google.firebase.iid.FirebaseInstanceId;
import java.util.regex.Pattern;

public class FirebaseMessaging
{
    public static final String INSTANCE_ID_SCOPE = "FCM";
    private static final Pattern zzdp;
    private static FirebaseMessaging zzdq;
    private final FirebaseInstanceId zzdj;
    
    static {
        zzdp = Pattern.compile("[a-zA-Z0-9-_.~%]{1,900}");
    }
    
    private FirebaseMessaging(final FirebaseInstanceId zzdj) {
        this.zzdj = zzdj;
    }
    
    public static FirebaseMessaging getInstance() {
        synchronized (FirebaseMessaging.class) {
            if (FirebaseMessaging.zzdq == null) {
                FirebaseMessaging.zzdq = new FirebaseMessaging(FirebaseInstanceId.getInstance());
            }
            return FirebaseMessaging.zzdq;
        }
    }
    
    public boolean isAutoInitEnabled() {
        return this.zzdj.zzr();
    }
    
    public void send(final RemoteMessage remoteMessage) {
        if (TextUtils.isEmpty((CharSequence)remoteMessage.getTo())) {
            throw new IllegalArgumentException("Missing 'to'");
        }
        final Context applicationContext = FirebaseApp.getInstance().getApplicationContext();
        final Intent intent = new Intent("com.google.android.gcm.intent.SEND");
        final Intent intent2 = new Intent();
        intent2.setPackage("com.google.example.invalidpackage");
        intent.putExtra("app", (Parcelable)PendingIntent.getBroadcast(applicationContext, 0, intent2, 0));
        intent.setPackage("com.google.android.gms");
        intent.putExtras(remoteMessage.zzds);
        applicationContext.sendOrderedBroadcast(intent, "com.google.android.gtalkservice.permission.GTALK_SERVICE");
    }
    
    public void setAutoInitEnabled(final boolean b) {
        this.zzdj.zzb(b);
    }
    
    public Task<Void> subscribeToTopic(String s) {
        String substring = s;
        if (s != null) {
            substring = s;
            if (s.startsWith("/topics/")) {
                Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in subscribeToTopic.");
                substring = s.substring(8);
            }
        }
        if (substring == null || !FirebaseMessaging.zzdp.matcher(substring).matches()) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(substring).length() + 78).append("Invalid topic name: ").append(substring).append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}").toString());
        }
        final FirebaseInstanceId zzdj = this.zzdj;
        s = String.valueOf("S!");
        final String value = String.valueOf(substring);
        if (value.length() != 0) {
            s = s.concat(value);
        }
        else {
            s = new String(s);
        }
        return (Task<Void>)zzdj.zza(s);
    }
    
    public Task<Void> unsubscribeFromTopic(String s) {
        String substring = s;
        if (s != null) {
            substring = s;
            if (s.startsWith("/topics/")) {
                Log.w("FirebaseMessaging", "Format /topics/topic-name is deprecated. Only 'topic-name' should be used in unsubscribeFromTopic.");
                substring = s.substring(8);
            }
        }
        if (substring == null || !FirebaseMessaging.zzdp.matcher(substring).matches()) {
            throw new IllegalArgumentException(new StringBuilder(String.valueOf(substring).length() + 78).append("Invalid topic name: ").append(substring).append(" does not match the allowed format [a-zA-Z0-9-_.~%]{1,900}").toString());
        }
        final FirebaseInstanceId zzdj = this.zzdj;
        s = String.valueOf("U!");
        final String value = String.valueOf(substring);
        if (value.length() != 0) {
            s = s.concat(value);
        }
        else {
            s = new String(s);
        }
        return (Task<Void>)zzdj.zza(s);
    }
}
