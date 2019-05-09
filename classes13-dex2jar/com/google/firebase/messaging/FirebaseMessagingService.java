// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import com.google.android.gms.tasks.Task;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import android.content.Context;
import com.google.firebase.iid.zzab;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.tasks.Tasks;
import android.text.TextUtils;
import android.app.PendingIntent$CanceledException;
import android.util.Log;
import android.app.PendingIntent;
import com.google.firebase.iid.zzav;
import android.content.Intent;
import android.support.annotation.WorkerThread;
import java.util.Iterator;
import android.os.Bundle;
import java.util.ArrayDeque;
import java.util.Queue;
import com.google.firebase.iid.zzb;

public class FirebaseMessagingService extends zzb
{
    private static final Queue<String> zzdr;
    
    static {
        zzdr = new ArrayDeque<String>(10);
    }
    
    static void zzj(final Bundle bundle) {
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s != null && s.startsWith("google.c.")) {
                iterator.remove();
            }
        }
    }
    
    @WorkerThread
    public void onDeletedMessages() {
    }
    
    @WorkerThread
    public void onMessageReceived(final RemoteMessage remoteMessage) {
    }
    
    @WorkerThread
    public void onMessageSent(final String s) {
    }
    
    @WorkerThread
    public void onNewToken(final String s) {
    }
    
    @WorkerThread
    public void onSendError(final String s, final Exception ex) {
    }
    
    protected final Intent zzb(final Intent intent) {
        return zzav.zzai().zzaj();
    }
    
    public final boolean zzc(final Intent intent) {
        if ("com.google.firebase.messaging.NOTIFICATION_OPEN".equals(intent.getAction())) {
            final PendingIntent pendingIntent = (PendingIntent)intent.getParcelableExtra("pending_intent");
            while (true) {
                if (pendingIntent == null) {
                    break Label_0030;
                }
                try {
                    pendingIntent.send();
                    if (MessagingAnalytics.shouldUploadMetrics(intent)) {
                        MessagingAnalytics.logNotificationOpen(intent);
                    }
                    return true;
                }
                catch (PendingIntent$CanceledException ex) {
                    Log.e("FirebaseMessaging", "Notification pending intent canceled");
                    continue;
                }
                break;
            }
        }
        return false;
    }
    
    public final void zzd(Intent ex) {
        final int n = 0;
        final String action = ((Intent)ex).getAction();
        if ("com.google.android.c2dm.intent.RECEIVE".equals(action) || "com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(action)) {
            final String stringExtra = ((Intent)ex).getStringExtra("google.message_id");
            while (true) {
                Label_0202: {
                    if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                        break Label_0202;
                    }
                    Task task = Tasks.forResult((Object)null);
                Label_0060_Outer:
                    while (true) {
                        Label_0235: {
                            if (!TextUtils.isEmpty((CharSequence)stringExtra)) {
                                break Label_0235;
                            }
                            int n2 = 0;
                        Label_0191_Outer:
                            while (true) {
                            Label_0134_Outer:
                                while (true) {
                                    if (n2 != 0) {
                                        break Label_0191;
                                    }
                                    String stringExtra2;
                                    if ((stringExtra2 = ((Intent)ex).getStringExtra("message_type")) == null) {
                                        stringExtra2 = "gcm";
                                    }
                                    Label_0391: {
                                        switch (stringExtra2.hashCode()) {
                                            case 102161: {
                                                break Label_0391;
                                            }
                                            case -2062414158: {
                                                break Label_0391;
                                            }
                                            case 814800675: {
                                                break Label_0391;
                                            }
                                            case 814694033: {
                                                break Label_0391;
                                            }
                                        }
                                        int n3 = 0;
                                        Label_0132: {
                                            n3 = -1;
                                        }
                                        Bundle extras;
                                        String value;
                                        String concat;
                                        Bundle bundle;
                                        String s;
                                        Label_0417_Outer:Label_0281_Outer:Label_0441_Outer:Label_0506_Outer:
                                        while (true) {
                                            Label_0568: {
                                            Label_0499_Outer:
                                                while (true) {
                                                    Block_14_Outer:Label_0519_Outer:
                                                    while (true) {
                                                        while (true) {
                                                            switch (n3) {
                                                                default: {
                                                                    ex = (ExecutionException)String.valueOf(stringExtra2);
                                                                    if (((String)ex).length() != 0) {
                                                                        ex = (ExecutionException)"Received message with unknown type: ".concat((String)ex);
                                                                        break;
                                                                    }
                                                                    break Label_0568;
                                                                }
                                                                case 0: {
                                                                    Label_0406: {
                                                                        break Label_0406;
                                                                        try {
                                                                            Tasks.await(task, 1L, TimeUnit.SECONDS);
                                                                            return;
                                                                            // iftrue(Label_0441:, extras = ex.getExtras() != null)
                                                                            // iftrue(Label_0294:, value.length() == 0)
                                                                            // iftrue(Label_0417:, !MessagingAnalytics.shouldUploadMetrics((Intent)ex))
                                                                            // iftrue(Label_0483:, !MessagingAnalytics.shouldUploadMetrics((Intent)ex))
                                                                            // iftrue(Label_0483:, !zza.zzf(extras))
                                                                            // iftrue(Label_0308:, !FirebaseMessagingService.zzdr.contains((Object)stringExtra))
                                                                            // iftrue(Label_0289:, !Log.isLoggable("FirebaseMessaging", 3))
                                                                            // iftrue(Label_0330:, FirebaseMessagingService.zzdr.size() < 10)
                                                                            // iftrue(Label_0191:, new zza((Context)this).zzh(extras))
                                                                            // iftrue(Label_0132:, !stringExtra2.equals((Object)"send_error"))
                                                                            // iftrue(Label_0132:, !stringExtra2.equals((Object)"gcm"))
                                                                            // iftrue(Label_0545:, s = ex.getStringExtra("google.message_id") != null)
                                                                            // iftrue(Label_0132:, !stringExtra2.equals((Object)"deleted_messages"))
                                                                            while (true) {
                                                                                while (true) {
                                                                                    Block_17: {
                                                                                        while (true) {
                                                                                            Label_0281:Block_19_Outer:
                                                                                            while (true) {
                                                                                                Block_15: {
                                                                                                    while (true) {
                                                                                                    Label_0545_Outer:
                                                                                                        while (true) {
                                                                                                            Label_0330: {
                                                                                                                while (true) {
                                                                                                                Block_11_Outer:
                                                                                                                    while (true) {
                                                                                                                    Block_18:
                                                                                                                        while (true) {
                                                                                                                            Block_10_Outer:Block_9_Outer:
                                                                                                                            while (true) {
                                                                                                                                break Block_17;
                                                                                                                            Block_16:
                                                                                                                                while (true) {
                                                                                                                                    while (true) {
                                                                                                                                        Block_20: {
                                                                                                                                            while (true) {
                                                                                                                                                while (true) {
                                                                                                                                                    concat = "Received duplicate message: ".concat(value);
                                                                                                                                                    break Label_0281;
                                                                                                                                                    n2 = 1;
                                                                                                                                                    continue Label_0191_Outer;
                                                                                                                                                    ex = (ExecutionException)new String("Received message with unknown type: ");
                                                                                                                                                    break;
                                                                                                                                                    value = String.valueOf(stringExtra);
                                                                                                                                                    continue Label_0281_Outer;
                                                                                                                                                }
                                                                                                                                                bundle = new Bundle();
                                                                                                                                                bundle.putString("google.message_id", stringExtra);
                                                                                                                                                task = zzab.zzc((Context)this).zza(2, bundle);
                                                                                                                                                continue Label_0060_Outer;
                                                                                                                                                break Block_16;
                                                                                                                                                break Block_20;
                                                                                                                                                FirebaseMessagingService.zzdr.remove();
                                                                                                                                                break Label_0330;
                                                                                                                                                Log.d("FirebaseMessaging", concat);
                                                                                                                                                continue Block_9_Outer;
                                                                                                                                            }
                                                                                                                                            extras.remove("android.support.content.wakelockid");
                                                                                                                                            break Block_18;
                                                                                                                                            n3 = n;
                                                                                                                                            continue Label_0417_Outer;
                                                                                                                                            this.onMessageReceived(new RemoteMessage(extras));
                                                                                                                                            continue Label_0134_Outer;
                                                                                                                                        }
                                                                                                                                        MessagingAnalytics.logNotificationForeground((Intent)ex);
                                                                                                                                        continue Label_0545_Outer;
                                                                                                                                    }
                                                                                                                                    Block_8: {
                                                                                                                                        break Block_8;
                                                                                                                                        this.onSendError(s, new SendException(((Intent)ex).getStringExtra("error")));
                                                                                                                                        continue Label_0134_Outer;
                                                                                                                                        this.onMessageSent(((Intent)ex).getStringExtra("google.message_id"));
                                                                                                                                        continue Label_0134_Outer;
                                                                                                                                    }
                                                                                                                                    continue Block_19_Outer;
                                                                                                                                }
                                                                                                                                MessagingAnalytics.logNotificationReceived((Intent)ex);
                                                                                                                                continue Block_10_Outer;
                                                                                                                            }
                                                                                                                            this.onDeletedMessages();
                                                                                                                            continue Label_0134_Outer;
                                                                                                                            Label_0308:
                                                                                                                            continue Label_0441_Outer;
                                                                                                                        }
                                                                                                                        continue Block_11_Outer;
                                                                                                                    }
                                                                                                                    s = ((Intent)ex).getStringExtra("message_id");
                                                                                                                    continue Label_0506_Outer;
                                                                                                                }
                                                                                                            }
                                                                                                            FirebaseMessagingService.zzdr.add(stringExtra);
                                                                                                            n2 = 0;
                                                                                                            continue Label_0191_Outer;
                                                                                                            break Block_15;
                                                                                                            continue Label_0506_Outer;
                                                                                                        }
                                                                                                        n3 = 2;
                                                                                                        continue Label_0417_Outer;
                                                                                                        continue Block_14_Outer;
                                                                                                    }
                                                                                                    n3 = 1;
                                                                                                    continue Label_0417_Outer;
                                                                                                }
                                                                                                n3 = 3;
                                                                                                continue Label_0417_Outer;
                                                                                                Label_0294:
                                                                                                concat = new String("Received duplicate message: ");
                                                                                                continue Label_0281;
                                                                                            }
                                                                                            continue;
                                                                                        }
                                                                                    }
                                                                                    extras = new Bundle();
                                                                                    continue Label_0506_Outer;
                                                                                }
                                                                                continue Label_0519_Outer;
                                                                            }
                                                                        }
                                                                        // iftrue(Label_0132:, !stringExtra2.equals((Object)"send_event"))
                                                                        catch (InterruptedException ex2) {}
                                                                        catch (ExecutionException ex) {
                                                                            goto Label_0582;
                                                                        }
                                                                        catch (TimeoutException ex) {
                                                                            goto Label_0582;
                                                                        }
                                                                    }
                                                                    break;
                                                                }
                                                                case 1: {
                                                                    continue Label_0519_Outer;
                                                                }
                                                                case 2: {
                                                                    continue Label_0499_Outer;
                                                                }
                                                                case 3: {
                                                                    continue;
                                                                }
                                                            }
                                                            break;
                                                        }
                                                        break;
                                                    }
                                                    break;
                                                }
                                            }
                                            break;
                                        }
                                    }
                                    break;
                                }
                                break;
                            }
                        }
                        break;
                    }
                }
                Log.w("FirebaseMessaging", (String)ex);
                continue;
            }
        }
        goto Label_0624;
    }
}
