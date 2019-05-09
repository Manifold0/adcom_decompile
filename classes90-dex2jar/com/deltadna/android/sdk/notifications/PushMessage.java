// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.os.Bundle;
import java.util.Locale;
import android.content.res.Resources$NotFoundException;
import android.util.Log;
import java.util.HashMap;
import android.content.Context;
import android.support.annotation.DrawableRes;
import java.util.Map;
import java.io.Serializable;

public class PushMessage implements Serializable
{
    protected static final String MESSAGE = "alert";
    private static final String TAG;
    protected static final String TITLE = "title";
    public final Map<String, String> data;
    public final String from;
    @DrawableRes
    public final int icon;
    public final long id;
    public final String message;
    public final String title;
    
    static {
        TAG = "deltaDNA " + PushMessage.class.getSimpleName();
    }
    
    PushMessage(final Context context, final String from, final Map<String, String> map) {
        this.from = from;
        this.data = new HashMap<String, String>(map);
        this.id = this.getId(map);
        this.icon = this.getIcon(context);
        this.title = this.getTitle(context, map);
        this.message = this.getMessage(map);
    }
    
    @DrawableRes
    protected int getIcon(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    com/deltadna/android/sdk/notifications/MetaData.get:(Landroid/content/Context;)Landroid/os/Bundle;
        //     4: astore          4
        //     6: aload           4
        //     8: ldc             "ddna_notification_icon"
        //    10: invokevirtual   android/os/Bundle.containsKey:(Ljava/lang/String;)Z
        //    13: ifeq            69
        //    16: aload           4
        //    18: ldc             "ddna_notification_icon"
        //    20: invokevirtual   android/os/Bundle.get:(Ljava/lang/String;)Ljava/lang/Object;
        //    23: astore          5
        //    25: aload           5
        //    27: instanceof      Ljava/lang/String;
        //    30: ifeq            106
        //    33: aload_1        
        //    34: invokevirtual   android/content/Context.getResources:()Landroid/content/res/Resources;
        //    37: aload           4
        //    39: ldc             "ddna_notification_icon"
        //    41: invokevirtual   android/os/Bundle.getString:(Ljava/lang/String;)Ljava/lang/String;
        //    44: ldc             "drawable"
        //    46: aload_1        
        //    47: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    50: invokevirtual   android/content/res/Resources.getIdentifier:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)I
        //    53: istore_2       
        //    54: iload_2        
        //    55: istore_3       
        //    56: iload_2        
        //    57: ifne            104
        //    60: getstatic       com/deltadna/android/sdk/notifications/PushMessage.TAG:Ljava/lang/String;
        //    63: ldc             "Failed to find drawable resource for ddna_notification_icon"
        //    65: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    68: pop            
        //    69: aload_1        
        //    70: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    73: aload_1        
        //    74: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    77: sipush          128
        //    80: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //    83: getfield        android/content/pm/ApplicationInfo.icon:I
        //    86: istore_2       
        //    87: iload_2        
        //    88: ifne            186
        //    91: getstatic       com/deltadna/android/sdk/notifications/PushMessage.TAG:Ljava/lang/String;
        //    94: ldc             "Failed to find application's icon"
        //    96: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //    99: pop            
        //   100: getstatic       com/deltadna/android/sdk/notifications/R$drawable.ddna_ic_stat_logo:I
        //   103: istore_3       
        //   104: iload_3        
        //   105: ireturn        
        //   106: aload           5
        //   108: instanceof      Ljava/lang/Integer;
        //   111: ifeq            126
        //   114: aload           5
        //   116: checkcast       Ljava/lang/Integer;
        //   119: invokevirtual   java/lang/Integer.intValue:()I
        //   122: istore_2       
        //   123: goto            54
        //   126: aload           5
        //   128: ifnonnull       160
        //   131: ldc             "null"
        //   133: astore          4
        //   135: ldc             "deltaDNA"
        //   137: ldc             "Unexpected type %s for ddna_notification_icon"
        //   139: iconst_1       
        //   140: anewarray       Ljava/lang/Object;
        //   143: dup            
        //   144: iconst_0       
        //   145: aload           4
        //   147: aastore        
        //   148: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   151: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   154: pop            
        //   155: iconst_0       
        //   156: istore_2       
        //   157: goto            54
        //   160: aload           5
        //   162: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   165: astore          4
        //   167: goto            135
        //   170: astore          4
        //   172: getstatic       com/deltadna/android/sdk/notifications/PushMessage.TAG:Ljava/lang/String;
        //   175: ldc             "Failed to find drawable resource for ddna_notification_icon"
        //   177: aload           4
        //   179: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   182: pop            
        //   183: goto            69
        //   186: iload_2        
        //   187: ireturn        
        //   188: astore_1       
        //   189: getstatic       com/deltadna/android/sdk/notifications/PushMessage.TAG:Ljava/lang/String;
        //   192: ldc             "Failed to find application's icon"
        //   194: aload_1        
        //   195: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   198: pop            
        //   199: goto            100
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  16     54     170    186    Landroid/content/res/Resources$NotFoundException;
        //  60     69     170    186    Landroid/content/res/Resources$NotFoundException;
        //  69     87     188    202    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  91     100    188    202    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  106    123    170    186    Landroid/content/res/Resources$NotFoundException;
        //  135    155    170    186    Landroid/content/res/Resources$NotFoundException;
        //  160    167    170    186    Landroid/content/res/Resources$NotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    protected long getId(final Map<String, String> map) {
        if (map.containsKey("_ddCampaign")) {
            final String s = map.get("_ddCampaign");
            try {
                return Long.parseLong(s);
            }
            catch (NumberFormatException ex) {
                Log.w(PushMessage.TAG, "Failed parsing _ddCampaign to a long", (Throwable)ex);
            }
        }
        return -1L;
    }
    
    protected String getMessage(final Map<String, String> map) {
        if (!map.containsKey("alert")) {
            Log.w(PushMessage.TAG, "Failed to find notification message in playload");
            return "";
        }
        return map.get("alert");
    }
    
    protected String getTitle(final Context context, final Map<String, String> map) {
        if (map.containsKey("title")) {
            return map.get("title");
        }
        final Bundle value = MetaData.get(context);
        if (value.containsKey("ddna_notification_title")) {
            final Object value2 = value.get("ddna_notification_title");
            if (value2 instanceof String) {
                return (String)value2;
            }
            if (value2 instanceof Integer) {
                try {
                    return context.getString((int)value2);
                }
                catch (Resources$NotFoundException ex) {
                    Log.w(PushMessage.TAG, "Failed to find string resource for ddna_notification_title", (Throwable)ex);
                }
            }
            else {
                Log.w(PushMessage.TAG, String.format(Locale.US, "Found %s for %s, only string literals or string resources allowed", value2, "ddna_notification_title"));
            }
        }
        return String.valueOf(context.getPackageManager().getApplicationLabel(context.getApplicationInfo()));
    }
    
    @Override
    public String toString() {
        return String.format(Locale.US, "%s{from: %s, data: %s, id: %d, icon: %s, title: %s, message: %s}", this.getClass().getSimpleName(), this.from, this.data, this.id, this.icon, this.title, this.message);
    }
}
