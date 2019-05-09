package com.google.firebase.iid;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcelable;
import android.os.RemoteException;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;
import com.google.android.gms.iid.InstanceID;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.iid.zzl.zza;
import com.tapjoy.TapjoyConstants;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;

final class zzat {
    private static int zzcf = 0;
    private static PendingIntent zzcr;
    private final zzan zzan;
    @GuardedBy("responseCallbacks")
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcs = new SimpleArrayMap();
    private Messenger zzct;
    private Messenger zzcu;
    private zzl zzcv;
    private final Context zzx;

    public zzat(Context context, zzan zzan) {
        this.zzx = context;
        this.zzan = zzan;
        this.zzct = new Messenger(new zzau(this, Looper.getMainLooper()));
    }

    private final void zzb(Message message) {
        if (message == null || !(message.obj instanceof Intent)) {
            Log.w("FirebaseInstanceId", "Dropping invalid message");
            return;
        }
        Intent intent = (Intent) message.obj;
        intent.setExtrasClassLoader(new zza());
        if (intent.hasExtra("google.messenger")) {
            Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
            if (parcelableExtra instanceof zzl) {
                this.zzcv = (zzl) parcelableExtra;
            }
            if (parcelableExtra instanceof Messenger) {
                this.zzcu = (Messenger) parcelableExtra;
            }
        }
        intent = (Intent) message.obj;
        String action = intent.getAction();
        String stringExtra;
        String valueOf;
        String str;
        if ("com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
            CharSequence stringExtra2 = intent.getStringExtra("registration_id");
            if (stringExtra2 == null) {
                stringExtra2 = intent.getStringExtra("unregistered");
            }
            if (stringExtra2 == null) {
                stringExtra = intent.getStringExtra("error");
                if (stringExtra == null) {
                    valueOf = String.valueOf(intent.getExtras());
                    Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 49).append("Unexpected response, no error or registration id ").append(valueOf).toString());
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    str = "FirebaseInstanceId";
                    String str2 = "Received InstanceID error ";
                    action = String.valueOf(stringExtra);
                    Log.d(str, action.length() != 0 ? str2.concat(action) : new String(str2));
                }
                if (stringExtra.startsWith("|")) {
                    String[] split = stringExtra.split("\\|");
                    if (split.length <= 2 || !"ID".equals(split[1])) {
                        action = "FirebaseInstanceId";
                        str = "Unexpected structured response ";
                        valueOf = String.valueOf(stringExtra);
                        Log.w(action, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        return;
                    }
                    stringExtra = split[2];
                    action = split[3];
                    if (action.startsWith(":")) {
                        action = action.substring(1);
                    }
                    zza(stringExtra, intent.putExtra("error", action).getExtras());
                    return;
                }
                synchronized (this.zzcs) {
                    for (int i = 0; i < this.zzcs.size(); i++) {
                        zza((String) this.zzcs.keyAt(i), intent.getExtras());
                    }
                }
                return;
            }
            Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(stringExtra2);
            if (matcher.matches()) {
                action = matcher.group(1);
                stringExtra = matcher.group(2);
                Bundle extras = intent.getExtras();
                extras.putString("registration_id", stringExtra);
                zza(action, extras);
            } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
                stringExtra = "FirebaseInstanceId";
                str = "Unexpected response string: ";
                valueOf = String.valueOf(stringExtra2);
                Log.d(stringExtra, valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if (Log.isLoggable("FirebaseInstanceId", 3)) {
            stringExtra = "FirebaseInstanceId";
            str = "Unexpected response action: ";
            valueOf = String.valueOf(action);
            if (valueOf.length() != 0) {
                valueOf = str.concat(valueOf);
            } else {
                valueOf = new String(str);
            }
            Log.d(stringExtra, valueOf);
        }
    }

    private static synchronized void zza(Context context, Intent intent) {
        synchronized (zzat.class) {
            if (zzcr == null) {
                Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzcr = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra(TapjoyConstants.TJC_APP_PLACEMENT, zzcr);
        }
    }

    private final void zza(String str, Bundle bundle) {
        synchronized (this.zzcs) {
            TaskCompletionSource taskCompletionSource = (TaskCompletionSource) this.zzcs.remove(str);
            if (taskCompletionSource == null) {
                String str2 = "FirebaseInstanceId";
                String str3 = "Missing callback for ";
                String valueOf = String.valueOf(str);
                Log.w(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
                return;
            }
            taskCompletionSource.setResult(bundle);
        }
    }

    final Bundle zzc(Bundle bundle) throws IOException {
        Exception e;
        if (this.zzan.zzaf() < 12000000) {
            return zzd(bundle);
        }
        try {
            return (Bundle) Tasks.await(zzab.zzc(this.zzx).zzb(1, bundle));
        } catch (InterruptedException e2) {
            e = e2;
        } catch (ExecutionException e3) {
            e = e3;
        }
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(e);
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 22).append("Error making request: ").append(valueOf).toString());
        }
        if ((e.getCause() instanceof zzal) && ((zzal) e.getCause()).getErrorCode() == 4) {
            return zzd(bundle);
        }
        return null;
    }

    private final Bundle zzd(Bundle bundle) throws IOException {
        Bundle zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        zze = zze(bundle);
        if (zze == null || !zze.containsKey("google.messenger")) {
            return zze;
        }
        return null;
    }

    private static synchronized String zzah() {
        String num;
        synchronized (zzat.class) {
            int i = zzcf;
            zzcf = i + 1;
            num = Integer.toString(i);
        }
        return num;
    }

    private final Bundle zze(Bundle bundle) throws IOException {
        String zzah = zzah();
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        synchronized (this.zzcs) {
            this.zzcs.put(zzah, taskCompletionSource);
        }
        if (this.zzan.zzac() == 0) {
            throw new IOException(InstanceID.ERROR_MISSING_INSTANCEID_SERVICE);
        }
        Intent intent = new Intent();
        intent.setPackage("com.google.android.gms");
        if (this.zzan.zzac() == 2) {
            intent.setAction("com.google.iid.TOKEN_REQUEST");
        } else {
            intent.setAction("com.google.android.c2dm.intent.REGISTER");
        }
        intent.putExtras(bundle);
        zza(this.zzx, intent);
        intent.putExtra("kid", new StringBuilder(String.valueOf(zzah).length() + 5).append("|ID|").append(zzah).append("|").toString());
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            String valueOf = String.valueOf(intent.getExtras());
            Log.d("FirebaseInstanceId", new StringBuilder(String.valueOf(valueOf).length() + 8).append("Sending ").append(valueOf).toString());
        }
        intent.putExtra("google.messenger", this.zzct);
        if (!(this.zzcu == null && this.zzcv == null)) {
            Message obtain = Message.obtain();
            obtain.obj = intent;
            try {
                if (this.zzcu != null) {
                    this.zzcu.send(obtain);
                } else {
                    this.zzcv.send(obtain);
                }
            } catch (RemoteException e) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    Log.d("FirebaseInstanceId", "Messenger failed, fallback to startService");
                }
            }
            Bundle bundle2 = (Bundle) Tasks.await(taskCompletionSource.getTask(), 30000, TimeUnit.MILLISECONDS);
            synchronized (this.zzcs) {
                this.zzcs.remove(zzah);
            }
            return bundle2;
        }
        if (this.zzan.zzac() == 2) {
            this.zzx.sendBroadcast(intent);
        } else {
            this.zzx.startService(intent);
        }
        try {
            Bundle bundle22 = (Bundle) Tasks.await(taskCompletionSource.getTask(), 30000, TimeUnit.MILLISECONDS);
            synchronized (this.zzcs) {
                this.zzcs.remove(zzah);
            }
            return bundle22;
        } catch (InterruptedException e2) {
            Log.w("FirebaseInstanceId", "No response");
            throw new IOException(InstanceID.ERROR_TIMEOUT);
        } catch (TimeoutException e3) {
            Log.w("FirebaseInstanceId", "No response");
            throw new IOException(InstanceID.ERROR_TIMEOUT);
        } catch (Throwable e4) {
            throw new IOException(e4);
        } catch (Throwable th) {
            synchronized (this.zzcs) {
                this.zzcs.remove(zzah);
            }
        }
    }
}
