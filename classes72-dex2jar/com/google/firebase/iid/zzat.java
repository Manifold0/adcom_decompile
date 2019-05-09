// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import java.util.concurrent.ExecutionException;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.util.Log;
import android.os.Message;
import android.os.Parcelable;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.content.Context;
import android.os.Messenger;
import javax.annotation.concurrent.GuardedBy;
import android.os.Bundle;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.support.v4.util.SimpleArrayMap;
import android.app.PendingIntent;

final class zzat
{
    private static int zzcf;
    private static PendingIntent zzcr;
    private final zzan zzan;
    @GuardedBy("responseCallbacks")
    private final SimpleArrayMap<String, TaskCompletionSource<Bundle>> zzcs;
    private Messenger zzct;
    private Messenger zzcu;
    private zzl zzcv;
    private final Context zzx;
    
    static {
        zzat.zzcf = 0;
    }
    
    public zzat(final Context zzx, final zzan zzan) {
        this.zzcs = (SimpleArrayMap<String, TaskCompletionSource<Bundle>>)new SimpleArrayMap();
        this.zzx = zzx;
        this.zzan = zzan;
        this.zzct = new Messenger((Handler)new zzau(this, Looper.getMainLooper()));
    }
    
    private static void zza(final Context context, final Intent intent) {
        synchronized (zzat.class) {
            if (zzat.zzcr == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                zzat.zzcr = PendingIntent.getBroadcast(context, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)zzat.zzcr);
        }
    }
    
    private final void zza(String s, final Bundle result) {
        final TaskCompletionSource taskCompletionSource;
        synchronized (this.zzcs) {
            taskCompletionSource = (TaskCompletionSource)this.zzcs.remove((Object)s);
            if (taskCompletionSource == null) {
                s = String.valueOf(s);
                if (s.length() != 0) {
                    s = "Missing callback for ".concat(s);
                }
                else {
                    s = new String("Missing callback for ");
                }
                Log.w("FirebaseInstanceId", s);
                return;
            }
        }
        taskCompletionSource.setResult((Object)result);
    }
    // monitorexit(simpleArrayMap)
    
    private static String zzah() {
        synchronized (zzat.class) {
            final int zzcf = zzat.zzcf;
            zzat.zzcf = zzcf + 1;
            return Integer.toString(zzcf);
        }
    }
    
    private final void zzb(Message zzcs) {
        if (zzcs != null && zzcs.obj instanceof Intent) {
            final Intent intent = (Intent)zzcs.obj;
            intent.setExtrasClassLoader((ClassLoader)new zzl.zza());
            if (intent.hasExtra("google.messenger")) {
                final Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof zzl) {
                    this.zzcv = (zzl)parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzcu = (Messenger)parcelableExtra;
                }
            }
            final Intent intent2 = (Intent)zzcs.obj;
            final String action = intent2.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action)) {
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    final String value = String.valueOf(action);
                    String concat;
                    if (value.length() != 0) {
                        concat = "Unexpected response action: ".concat(value);
                    }
                    else {
                        concat = new String("Unexpected response action: ");
                    }
                    Log.d("FirebaseInstanceId", concat);
                }
            }
            else {
                String s;
                if ((s = intent2.getStringExtra("registration_id")) == null) {
                    s = intent2.getStringExtra("unregistered");
                }
                if (s == null) {
                    final String stringExtra = intent2.getStringExtra("error");
                    if (stringExtra == null) {
                        final String value2 = String.valueOf(intent2.getExtras());
                        Log.w("FirebaseInstanceId", new StringBuilder(String.valueOf(value2).length() + 49).append("Unexpected response, no error or registration id ").append(value2).toString());
                        return;
                    }
                    if (Log.isLoggable("FirebaseInstanceId", 3)) {
                        final String value3 = String.valueOf(stringExtra);
                        String concat2;
                        if (value3.length() != 0) {
                            concat2 = "Received InstanceID error ".concat(value3);
                        }
                        else {
                            concat2 = new String("Received InstanceID error ");
                        }
                        Log.d("FirebaseInstanceId", concat2);
                    }
                    if (stringExtra.startsWith("|")) {
                        final String[] split = stringExtra.split("\\|");
                        if (split.length <= 2 || !"ID".equals(split[1])) {
                            final String value4 = String.valueOf(stringExtra);
                            String concat3;
                            if (value4.length() != 0) {
                                concat3 = "Unexpected structured response ".concat(value4);
                            }
                            else {
                                concat3 = new String("Unexpected structured response ");
                            }
                            Log.w("FirebaseInstanceId", concat3);
                            return;
                        }
                        final String s2 = split[2];
                        String substring;
                        final String s3 = substring = split[3];
                        if (s3.startsWith(":")) {
                            substring = s3.substring(1);
                        }
                        this.zza(s2, intent2.putExtra("error", substring).getExtras());
                        return;
                    }
                    else {
                        zzcs = (Message)this.zzcs;
                        // monitorenter(zzcs)
                        int i = 0;
                        try {
                            while (i < this.zzcs.size()) {
                                this.zza((String)this.zzcs.keyAt(i), intent2.getExtras());
                                ++i;
                            }
                            return;
                        }
                        finally {
                        }
                        // monitorexit(zzcs)
                    }
                }
                final Matcher matcher = Pattern.compile("\\|ID\\|([^|]+)\\|:?+(.*)").matcher(s);
                if (matcher.matches()) {
                    final String group = matcher.group(1);
                    final String group2 = matcher.group(2);
                    final Bundle extras = intent2.getExtras();
                    extras.putString("registration_id", group2);
                    this.zza(group, extras);
                    return;
                }
                if (Log.isLoggable("FirebaseInstanceId", 3)) {
                    final String value5 = String.valueOf(s);
                    String concat4;
                    if (value5.length() != 0) {
                        concat4 = "Unexpected response string: ".concat(value5);
                    }
                    else {
                        concat4 = new String("Unexpected response string: ");
                    }
                    Log.d("FirebaseInstanceId", concat4);
                }
            }
            return;
        }
        Log.w("FirebaseInstanceId", "Dropping invalid message");
    }
    
    private final Bundle zzd(Bundle zze) throws IOException {
        Bundle zze2;
        final Bundle bundle = zze2 = this.zze(zze);
        if (bundle != null) {
            zze2 = bundle;
            if (bundle.containsKey("google.messenger")) {
                zze = this.zze(zze);
                if ((zze2 = zze) != null) {
                    zze2 = zze;
                    if (zze.containsKey("google.messenger")) {
                        zze2 = null;
                    }
                }
            }
        }
        return zze2;
    }
    
    private final Bundle zze(final Bundle p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_2       
        //     4: new             Lcom/google/android/gms/tasks/TaskCompletionSource;
        //     7: dup            
        //     8: invokespecial   com/google/android/gms/tasks/TaskCompletionSource.<init>:()V
        //    11: astore_3       
        //    12: aload_0        
        //    13: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //    16: astore          4
        //    18: aload           4
        //    20: monitorenter   
        //    21: aload_0        
        //    22: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //    25: aload_2        
        //    26: aload_3        
        //    27: invokevirtual   android/support/v4/util/SimpleArrayMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    30: pop            
        //    31: aload           4
        //    33: monitorexit    
        //    34: aload_0        
        //    35: getfield        com/google/firebase/iid/zzat.zzan:Lcom/google/firebase/iid/zzan;
        //    38: invokevirtual   com/google/firebase/iid/zzan.zzac:()I
        //    41: ifne            61
        //    44: new             Ljava/io/IOException;
        //    47: dup            
        //    48: ldc_w           "MISSING_INSTANCEID_SERVICE"
        //    51: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    54: athrow         
        //    55: astore_1       
        //    56: aload           4
        //    58: monitorexit    
        //    59: aload_1        
        //    60: athrow         
        //    61: new             Landroid/content/Intent;
        //    64: dup            
        //    65: invokespecial   android/content/Intent.<init>:()V
        //    68: astore          4
        //    70: aload           4
        //    72: ldc_w           "com.google.android.gms"
        //    75: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
        //    78: pop            
        //    79: aload_0        
        //    80: getfield        com/google/firebase/iid/zzat.zzan:Lcom/google/firebase/iid/zzan;
        //    83: invokevirtual   com/google/firebase/iid/zzan.zzac:()I
        //    86: iconst_2       
        //    87: if_icmpne       300
        //    90: aload           4
        //    92: ldc_w           "com.google.iid.TOKEN_REQUEST"
        //    95: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //    98: pop            
        //    99: aload           4
        //   101: aload_1        
        //   102: invokevirtual   android/content/Intent.putExtras:(Landroid/os/Bundle;)Landroid/content/Intent;
        //   105: pop            
        //   106: aload_0        
        //   107: getfield        com/google/firebase/iid/zzat.zzx:Landroid/content/Context;
        //   110: aload           4
        //   112: invokestatic    com/google/firebase/iid/zzat.zza:(Landroid/content/Context;Landroid/content/Intent;)V
        //   115: aload           4
        //   117: ldc_w           "kid"
        //   120: new             Ljava/lang/StringBuilder;
        //   123: dup            
        //   124: aload_2        
        //   125: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   128: invokevirtual   java/lang/String.length:()I
        //   131: iconst_5       
        //   132: iadd           
        //   133: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   136: ldc_w           "|ID|"
        //   139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   142: aload_2        
        //   143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   146: ldc             "|"
        //   148: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   151: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   154: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   157: pop            
        //   158: ldc             "FirebaseInstanceId"
        //   160: iconst_3       
        //   161: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   164: ifeq            212
        //   167: aload           4
        //   169: invokevirtual   android/content/Intent.getExtras:()Landroid/os/Bundle;
        //   172: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   175: astore_1       
        //   176: ldc             "FirebaseInstanceId"
        //   178: new             Ljava/lang/StringBuilder;
        //   181: dup            
        //   182: aload_1        
        //   183: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   186: invokevirtual   java/lang/String.length:()I
        //   189: bipush          8
        //   191: iadd           
        //   192: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   195: ldc_w           "Sending "
        //   198: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   201: aload_1        
        //   202: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   205: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   208: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   211: pop            
        //   212: aload           4
        //   214: ldc             "google.messenger"
        //   216: aload_0        
        //   217: getfield        com/google/firebase/iid/zzat.zzct:Landroid/os/Messenger;
        //   220: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
        //   223: pop            
        //   224: aload_0        
        //   225: getfield        com/google/firebase/iid/zzat.zzcu:Landroid/os/Messenger;
        //   228: ifnonnull       238
        //   231: aload_0        
        //   232: getfield        com/google/firebase/iid/zzat.zzcv:Lcom/google/firebase/iid/zzl;
        //   235: ifnull          342
        //   238: invokestatic    android/os/Message.obtain:()Landroid/os/Message;
        //   241: astore_1       
        //   242: aload_1        
        //   243: aload           4
        //   245: putfield        android/os/Message.obj:Ljava/lang/Object;
        //   248: aload_0        
        //   249: getfield        com/google/firebase/iid/zzat.zzcu:Landroid/os/Messenger;
        //   252: ifnull          312
        //   255: aload_0        
        //   256: getfield        com/google/firebase/iid/zzat.zzcu:Landroid/os/Messenger;
        //   259: aload_1        
        //   260: invokevirtual   android/os/Messenger.send:(Landroid/os/Message;)V
        //   263: aload_3        
        //   264: invokevirtual   com/google/android/gms/tasks/TaskCompletionSource.getTask:()Lcom/google/android/gms/tasks/Task;
        //   267: ldc2_w          30000
        //   270: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //   273: invokestatic    com/google/android/gms/tasks/Tasks.await:(Lcom/google/android/gms/tasks/Task;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;
        //   276: checkcast       Landroid/os/Bundle;
        //   279: astore_3       
        //   280: aload_0        
        //   281: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //   284: astore_1       
        //   285: aload_1        
        //   286: monitorenter   
        //   287: aload_0        
        //   288: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //   291: aload_2        
        //   292: invokevirtual   android/support/v4/util/SimpleArrayMap.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   295: pop            
        //   296: aload_1        
        //   297: monitorexit    
        //   298: aload_3        
        //   299: areturn        
        //   300: aload           4
        //   302: ldc_w           "com.google.android.c2dm.intent.REGISTER"
        //   305: invokevirtual   android/content/Intent.setAction:(Ljava/lang/String;)Landroid/content/Intent;
        //   308: pop            
        //   309: goto            99
        //   312: aload_0        
        //   313: getfield        com/google/firebase/iid/zzat.zzcv:Lcom/google/firebase/iid/zzl;
        //   316: aload_1        
        //   317: invokevirtual   com/google/firebase/iid/zzl.send:(Landroid/os/Message;)V
        //   320: goto            263
        //   323: astore_1       
        //   324: ldc             "FirebaseInstanceId"
        //   326: iconst_3       
        //   327: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   330: ifeq            342
        //   333: ldc             "FirebaseInstanceId"
        //   335: ldc_w           "Messenger failed, fallback to startService"
        //   338: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   341: pop            
        //   342: aload_0        
        //   343: getfield        com/google/firebase/iid/zzat.zzan:Lcom/google/firebase/iid/zzan;
        //   346: invokevirtual   com/google/firebase/iid/zzan.zzac:()I
        //   349: iconst_2       
        //   350: if_icmpne       365
        //   353: aload_0        
        //   354: getfield        com/google/firebase/iid/zzat.zzx:Landroid/content/Context;
        //   357: aload           4
        //   359: invokevirtual   android/content/Context.sendBroadcast:(Landroid/content/Intent;)V
        //   362: goto            263
        //   365: aload_0        
        //   366: getfield        com/google/firebase/iid/zzat.zzx:Landroid/content/Context;
        //   369: aload           4
        //   371: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //   374: pop            
        //   375: goto            263
        //   378: astore_2       
        //   379: aload_1        
        //   380: monitorexit    
        //   381: aload_2        
        //   382: athrow         
        //   383: astore_1       
        //   384: ldc             "FirebaseInstanceId"
        //   386: ldc_w           "No response"
        //   389: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   392: pop            
        //   393: new             Ljava/io/IOException;
        //   396: dup            
        //   397: ldc_w           "TIMEOUT"
        //   400: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   403: athrow         
        //   404: astore_3       
        //   405: aload_0        
        //   406: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //   409: astore_1       
        //   410: aload_1        
        //   411: monitorenter   
        //   412: aload_0        
        //   413: getfield        com/google/firebase/iid/zzat.zzcs:Landroid/support/v4/util/SimpleArrayMap;
        //   416: aload_2        
        //   417: invokevirtual   android/support/v4/util/SimpleArrayMap.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   420: pop            
        //   421: aload_1        
        //   422: monitorexit    
        //   423: aload_3        
        //   424: athrow         
        //   425: astore_1       
        //   426: new             Ljava/io/IOException;
        //   429: dup            
        //   430: aload_1        
        //   431: invokespecial   java/io/IOException.<init>:(Ljava/lang/Throwable;)V
        //   434: athrow         
        //   435: astore_2       
        //   436: aload_1        
        //   437: monitorexit    
        //   438: aload_2        
        //   439: athrow         
        //   440: astore_1       
        //   441: goto            384
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                     
        //  -----  -----  -----  -----  -----------------------------------------
        //  21     34     55     61     Any
        //  56     59     55     61     Any
        //  248    263    323    342    Landroid/os/RemoteException;
        //  263    280    383    384    Ljava/lang/InterruptedException;
        //  263    280    440    444    Ljava/util/concurrent/TimeoutException;
        //  263    280    425    435    Ljava/util/concurrent/ExecutionException;
        //  263    280    404    440    Any
        //  287    298    378    383    Any
        //  312    320    323    342    Landroid/os/RemoteException;
        //  379    381    378    383    Any
        //  384    404    404    440    Any
        //  412    423    435    440    Any
        //  426    435    404    440    Any
        //  436    438    435    440    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 224, Size: 224
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    final Bundle zzc(final Bundle bundle) throws IOException {
        if (this.zzan.zzaf() < 12000000) {
            goto Label_0119;
        }
        Object zzb = zzab.zzc(this.zzx).zzb(1, bundle);
        try {
            zzb = Tasks.await((Task)zzb);
            return (Bundle)zzb;
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException zzb) {
            goto Label_0037;
        }
    }
}
