// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.content.Context;
import android.util.Log;
import android.content.Intent;
import java.util.Iterator;
import android.os.Bundle;
import com.google.android.gms.iid.zzb;

public class GcmListenerService extends zzb
{
    static void zzq(final Bundle bundle) {
        final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s != null && s.startsWith("google.c.")) {
                iterator.remove();
            }
        }
    }
    
    public void handleIntent(final Intent intent) {
        if (!"com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            final String value = String.valueOf(intent.getAction());
            String concat;
            if (value.length() != 0) {
                concat = "Unknown intent action: ".concat(value);
            }
            else {
                concat = new String("Unknown intent action: ");
            }
            Log.w("GcmListenerService", concat);
            return;
        }
        String stringExtra;
        if ((stringExtra = intent.getStringExtra("message_type")) == null) {
            stringExtra = "gcm";
        }
        switch (stringExtra) {
            default: {
                final String value2 = String.valueOf(stringExtra);
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Received message with unknown type: ".concat(value2);
                }
                else {
                    concat2 = new String("Received message with unknown type: ");
                }
                Log.w("GcmListenerService", concat2);
            }
            case "gcm": {
                final Bundle extras = intent.getExtras();
                extras.remove("message_type");
                extras.remove("android.support.content.wakelockid");
                int n2;
                if ("1".equals(zza.zze(extras, "gcm.n.e")) || zza.zze(extras, "gcm.n.icon") != null) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                if (n2 != 0) {
                    if (!zza.zzdh((Context)this)) {
                        zza.zzdg((Context)this).zzs(extras);
                        return;
                    }
                    zza.zzr(extras);
                }
                final String string = extras.getString("from");
                extras.remove("from");
                zzq(extras);
                this.onMessageReceived(string, extras);
            }
            case "deleted_messages": {
                this.onDeletedMessages();
            }
            case "send_event": {
                this.onMessageSent(intent.getStringExtra("google.message_id"));
            }
            case "send_error": {
                String s;
                if ((s = intent.getStringExtra("google.message_id")) == null) {
                    s = intent.getStringExtra("message_id");
                }
                this.onSendError(s, intent.getStringExtra("error"));
            }
        }
    }
    
    public void onDeletedMessages() {
    }
    
    public void onMessageReceived(final String s, final Bundle bundle) {
    }
    
    public void onMessageSent(final String s) {
    }
    
    public void onSendError(final String s, final String s2) {
    }
}
