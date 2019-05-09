// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import android.os.Build$VERSION;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;
import android.os.Environment;
import java.util.UUID;
import android.app.ProgressDialog;
import net.hockeyapp.android.listeners.DownloadFileListener;
import android.content.Context;
import android.os.AsyncTask;

public class DownloadFileTask extends AsyncTask<Void, Integer, Long>
{
    protected static final int MAX_REDIRECTS = 6;
    protected Context mContext;
    private String mDownloadErrorMessage;
    protected String mFilePath;
    protected String mFilename;
    protected DownloadFileListener mNotifier;
    protected ProgressDialog mProgressDialog;
    protected String mUrlString;
    
    public DownloadFileTask(final Context mContext, final String mUrlString, final DownloadFileListener mNotifier) {
        this.mContext = mContext;
        this.mUrlString = mUrlString;
        this.mFilename = UUID.randomUUID() + ".apk";
        this.mFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download";
        this.mNotifier = mNotifier;
        this.mDownloadErrorMessage = null;
    }
    
    public void attach(final Context mContext) {
        this.mContext = mContext;
    }
    
    protected URLConnection createConnection(final URL url, final int n) throws IOException {
        final HttpURLConnection connectionProperties = (HttpURLConnection)url.openConnection();
        this.setConnectionProperties(connectionProperties);
        final int responseCode = connectionProperties.getResponseCode();
        if ((responseCode == 301 || responseCode == 302 || responseCode == 303) && n != 0) {
            final URL url2 = new URL(connectionProperties.getHeaderField("Location"));
            if (!url.getProtocol().equals(url2.getProtocol())) {
                connectionProperties.disconnect();
                return this.createConnection(url2, n - 1);
            }
        }
        return connectionProperties;
    }
    
    public void detach() {
        this.mContext = null;
        this.mProgressDialog = null;
    }
    
    protected Long doInBackground(final Void... p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          11
        //     3: aconst_null    
        //     4: astore          10
        //     6: aconst_null    
        //     7: astore_1       
        //     8: aconst_null    
        //     9: astore          8
        //    11: aconst_null    
        //    12: astore          9
        //    14: aload           11
        //    16: astore          6
        //    18: aload_1        
        //    19: astore          7
        //    21: aload_0        
        //    22: new             Ljava/net/URL;
        //    25: dup            
        //    26: aload_0        
        //    27: invokevirtual   net/hockeyapp/android/tasks/DownloadFileTask.getURLString:()Ljava/lang/String;
        //    30: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    33: bipush          6
        //    35: invokevirtual   net/hockeyapp/android/tasks/DownloadFileTask.createConnection:(Ljava/net/URL;I)Ljava/net/URLConnection;
        //    38: astore          12
        //    40: aload           11
        //    42: astore          6
        //    44: aload_1        
        //    45: astore          7
        //    47: aload           12
        //    49: invokevirtual   java/net/URLConnection.connect:()V
        //    52: aload           11
        //    54: astore          6
        //    56: aload_1        
        //    57: astore          7
        //    59: aload           12
        //    61: invokevirtual   java/net/URLConnection.getContentLength:()I
        //    64: istore_3       
        //    65: aload           11
        //    67: astore          6
        //    69: aload_1        
        //    70: astore          7
        //    72: aload           12
        //    74: invokevirtual   java/net/URLConnection.getContentType:()Ljava/lang/String;
        //    77: astore          13
        //    79: aload           13
        //    81: ifnull          158
        //    84: aload           11
        //    86: astore          6
        //    88: aload_1        
        //    89: astore          7
        //    91: aload           13
        //    93: ldc             "text"
        //    95: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //    98: ifeq            158
        //   101: aload           11
        //   103: astore          6
        //   105: aload_1        
        //   106: astore          7
        //   108: aload_0        
        //   109: ldc             "The requested download does not appear to be a file."
        //   111: putfield        net/hockeyapp/android/tasks/DownloadFileTask.mDownloadErrorMessage:Ljava/lang/String;
        //   114: lconst_0       
        //   115: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   118: astore_1       
        //   119: iconst_0       
        //   120: ifeq            131
        //   123: new             Ljava/lang/NullPointerException;
        //   126: dup            
        //   127: invokespecial   java/lang/NullPointerException.<init>:()V
        //   130: athrow         
        //   131: aload_1        
        //   132: astore          6
        //   134: iconst_0       
        //   135: ifeq            146
        //   138: new             Ljava/lang/NullPointerException;
        //   141: dup            
        //   142: invokespecial   java/lang/NullPointerException.<init>:()V
        //   145: athrow         
        //   146: aload           6
        //   148: areturn        
        //   149: astore          6
        //   151: aload           6
        //   153: invokevirtual   java/io/IOException.printStackTrace:()V
        //   156: aload_1        
        //   157: areturn        
        //   158: aload           11
        //   160: astore          6
        //   162: aload_1        
        //   163: astore          7
        //   165: new             Ljava/io/File;
        //   168: dup            
        //   169: aload_0        
        //   170: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mFilePath:Ljava/lang/String;
        //   173: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   176: astore          13
        //   178: aload           11
        //   180: astore          6
        //   182: aload_1        
        //   183: astore          7
        //   185: aload           13
        //   187: invokevirtual   java/io/File.mkdirs:()Z
        //   190: ifne            302
        //   193: aload           11
        //   195: astore          6
        //   197: aload_1        
        //   198: astore          7
        //   200: aload           13
        //   202: invokevirtual   java/io/File.exists:()Z
        //   205: ifne            302
        //   208: aload           11
        //   210: astore          6
        //   212: aload_1        
        //   213: astore          7
        //   215: new             Ljava/io/IOException;
        //   218: dup            
        //   219: new             Ljava/lang/StringBuilder;
        //   222: dup            
        //   223: invokespecial   java/lang/StringBuilder.<init>:()V
        //   226: ldc             "Could not create the dir(s):"
        //   228: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   231: aload           13
        //   233: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   236: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   239: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   242: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   245: athrow         
        //   246: astore          8
        //   248: aload           10
        //   250: astore_1       
        //   251: aload_1        
        //   252: astore          6
        //   254: aload           9
        //   256: astore          7
        //   258: aload           8
        //   260: invokevirtual   java/io/IOException.printStackTrace:()V
        //   263: lconst_0       
        //   264: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   267: astore          7
        //   269: aload           9
        //   271: ifnull          279
        //   274: aload           9
        //   276: invokevirtual   java/io/OutputStream.close:()V
        //   279: aload           7
        //   281: astore          6
        //   283: aload_1        
        //   284: ifnull          146
        //   287: aload_1        
        //   288: invokevirtual   java/io/InputStream.close:()V
        //   291: aload           7
        //   293: areturn        
        //   294: astore_1       
        //   295: aload_1        
        //   296: invokevirtual   java/io/IOException.printStackTrace:()V
        //   299: aload           7
        //   301: areturn        
        //   302: aload           11
        //   304: astore          6
        //   306: aload_1        
        //   307: astore          7
        //   309: new             Ljava/io/File;
        //   312: dup            
        //   313: aload           13
        //   315: aload_0        
        //   316: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mFilename:Ljava/lang/String;
        //   319: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   322: astore          13
        //   324: aload           11
        //   326: astore          6
        //   328: aload_1        
        //   329: astore          7
        //   331: new             Ljava/io/BufferedInputStream;
        //   334: dup            
        //   335: aload           12
        //   337: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   340: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   343: astore_1       
        //   344: new             Ljava/io/FileOutputStream;
        //   347: dup            
        //   348: aload           13
        //   350: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   353: astore          6
        //   355: sipush          1024
        //   358: newarray        B
        //   360: astore          7
        //   362: lconst_0       
        //   363: lstore          4
        //   365: aload_1        
        //   366: aload           7
        //   368: invokevirtual   java/io/InputStream.read:([B)I
        //   371: istore_2       
        //   372: iload_2        
        //   373: iconst_m1      
        //   374: if_icmpeq       422
        //   377: lload           4
        //   379: iload_2        
        //   380: i2l            
        //   381: ladd           
        //   382: lstore          4
        //   384: aload_0        
        //   385: iconst_1       
        //   386: anewarray       Ljava/lang/Integer;
        //   389: dup            
        //   390: iconst_0       
        //   391: lload           4
        //   393: l2f            
        //   394: ldc             100.0
        //   396: fmul           
        //   397: iload_3        
        //   398: i2f            
        //   399: fdiv           
        //   400: invokestatic    java/lang/Math.round:(F)I
        //   403: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   406: aastore        
        //   407: invokevirtual   net/hockeyapp/android/tasks/DownloadFileTask.publishProgress:([Ljava/lang/Object;)V
        //   410: aload           6
        //   412: aload           7
        //   414: iconst_0       
        //   415: iload_2        
        //   416: invokevirtual   java/io/OutputStream.write:([BII)V
        //   419: goto            365
        //   422: aload           6
        //   424: invokevirtual   java/io/OutputStream.flush:()V
        //   427: aload           6
        //   429: ifnull          437
        //   432: aload           6
        //   434: invokevirtual   java/io/OutputStream.close:()V
        //   437: aload_1        
        //   438: ifnull          445
        //   441: aload_1        
        //   442: invokevirtual   java/io/InputStream.close:()V
        //   445: lload           4
        //   447: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   450: areturn        
        //   451: astore_1       
        //   452: aload_1        
        //   453: invokevirtual   java/io/IOException.printStackTrace:()V
        //   456: goto            445
        //   459: astore_1       
        //   460: aload           7
        //   462: ifnull          470
        //   465: aload           7
        //   467: invokevirtual   java/io/OutputStream.close:()V
        //   470: aload           6
        //   472: ifnull          480
        //   475: aload           6
        //   477: invokevirtual   java/io/InputStream.close:()V
        //   480: aload_1        
        //   481: athrow         
        //   482: astore          6
        //   484: aload           6
        //   486: invokevirtual   java/io/IOException.printStackTrace:()V
        //   489: goto            480
        //   492: astore          9
        //   494: aload_1        
        //   495: astore          6
        //   497: aload           8
        //   499: astore          7
        //   501: aload           9
        //   503: astore_1       
        //   504: goto            460
        //   507: astore          8
        //   509: aload           6
        //   511: astore          7
        //   513: aload_1        
        //   514: astore          6
        //   516: aload           8
        //   518: astore_1       
        //   519: goto            460
        //   522: astore          8
        //   524: goto            251
        //   527: astore          8
        //   529: aload           6
        //   531: astore          9
        //   533: goto            251
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  21     40     246    251    Ljava/io/IOException;
        //  21     40     459    460    Any
        //  47     52     246    251    Ljava/io/IOException;
        //  47     52     459    460    Any
        //  59     65     246    251    Ljava/io/IOException;
        //  59     65     459    460    Any
        //  72     79     246    251    Ljava/io/IOException;
        //  72     79     459    460    Any
        //  91     101    246    251    Ljava/io/IOException;
        //  91     101    459    460    Any
        //  108    114    246    251    Ljava/io/IOException;
        //  108    114    459    460    Any
        //  123    131    149    158    Ljava/io/IOException;
        //  138    146    149    158    Ljava/io/IOException;
        //  165    178    246    251    Ljava/io/IOException;
        //  165    178    459    460    Any
        //  185    193    246    251    Ljava/io/IOException;
        //  185    193    459    460    Any
        //  200    208    246    251    Ljava/io/IOException;
        //  200    208    459    460    Any
        //  215    246    246    251    Ljava/io/IOException;
        //  215    246    459    460    Any
        //  258    263    459    460    Any
        //  274    279    294    302    Ljava/io/IOException;
        //  287    291    294    302    Ljava/io/IOException;
        //  309    324    246    251    Ljava/io/IOException;
        //  309    324    459    460    Any
        //  331    344    246    251    Ljava/io/IOException;
        //  331    344    459    460    Any
        //  344    355    522    527    Ljava/io/IOException;
        //  344    355    492    507    Any
        //  355    362    527    536    Ljava/io/IOException;
        //  355    362    507    522    Any
        //  365    372    527    536    Ljava/io/IOException;
        //  365    372    507    522    Any
        //  384    419    527    536    Ljava/io/IOException;
        //  384    419    507    522    Any
        //  422    427    527    536    Ljava/io/IOException;
        //  422    427    507    522    Any
        //  432    437    451    459    Ljava/io/IOException;
        //  441    445    451    459    Ljava/io/IOException;
        //  465    470    482    492    Ljava/io/IOException;
        //  475    480    482    492    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 273, Size: 273
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
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
    
    protected String getURLString() {
        return this.mUrlString + "&type=apk";
    }
    
    protected void onPostExecute(final Long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mProgressDialog:Landroid/app/ProgressDialog;
        //     4: ifnull          14
        //     7: aload_0        
        //     8: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mProgressDialog:Landroid/app/ProgressDialog;
        //    11: invokevirtual   android/app/ProgressDialog.dismiss:()V
        //    14: aload_1        
        //    15: invokevirtual   java/lang/Long.longValue:()J
        //    18: lconst_0       
        //    19: lcmp           
        //    20: ifle            122
        //    23: aload_0        
        //    24: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mNotifier:Lnet/hockeyapp/android/listeners/DownloadFileListener;
        //    27: aload_0        
        //    28: invokevirtual   net/hockeyapp/android/listeners/DownloadFileListener.downloadSuccessful:(Lnet/hockeyapp/android/tasks/DownloadFileTask;)V
        //    31: new             Landroid/content/Intent;
        //    34: dup            
        //    35: ldc             "android.intent.action.VIEW"
        //    37: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //    40: astore_2       
        //    41: aload_2        
        //    42: new             Ljava/io/File;
        //    45: dup            
        //    46: aload_0        
        //    47: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mFilePath:Ljava/lang/String;
        //    50: aload_0        
        //    51: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mFilename:Ljava/lang/String;
        //    54: invokespecial   java/io/File.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    57: invokestatic    android/net/Uri.fromFile:(Ljava/io/File;)Landroid/net/Uri;
        //    60: ldc_w           "application/vnd.android.package-archive"
        //    63: invokevirtual   android/content/Intent.setDataAndType:(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;
        //    66: pop            
        //    67: aload_2        
        //    68: ldc_w           268435456
        //    71: invokevirtual   android/content/Intent.setFlags:(I)Landroid/content/Intent;
        //    74: pop            
        //    75: aconst_null    
        //    76: astore_1       
        //    77: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    80: bipush          24
        //    82: if_icmplt       105
        //    85: invokestatic    android/os/StrictMode.getVmPolicy:()Landroid/os/StrictMode$VmPolicy;
        //    88: astore_1       
        //    89: new             Landroid/os/StrictMode$VmPolicy$Builder;
        //    92: dup            
        //    93: invokespecial   android/os/StrictMode$VmPolicy$Builder.<init>:()V
        //    96: invokevirtual   android/os/StrictMode$VmPolicy$Builder.penaltyLog:()Landroid/os/StrictMode$VmPolicy$Builder;
        //    99: invokevirtual   android/os/StrictMode$VmPolicy$Builder.build:()Landroid/os/StrictMode$VmPolicy;
        //   102: invokestatic    android/os/StrictMode.setVmPolicy:(Landroid/os/StrictMode$VmPolicy;)V
        //   105: aload_0        
        //   106: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mContext:Landroid/content/Context;
        //   109: aload_2        
        //   110: invokevirtual   android/content/Context.startActivity:(Landroid/content/Intent;)V
        //   113: aload_1        
        //   114: ifnull          121
        //   117: aload_1        
        //   118: invokestatic    android/os/StrictMode.setVmPolicy:(Landroid/os/StrictMode$VmPolicy;)V
        //   121: return         
        //   122: new             Landroid/app/AlertDialog$Builder;
        //   125: dup            
        //   126: aload_0        
        //   127: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mContext:Landroid/content/Context;
        //   130: invokespecial   android/app/AlertDialog$Builder.<init>:(Landroid/content/Context;)V
        //   133: astore_2       
        //   134: aload_2        
        //   135: getstatic       net/hockeyapp/android/R$string.hockeyapp_download_failed_dialog_title:I
        //   138: invokevirtual   android/app/AlertDialog$Builder.setTitle:(I)Landroid/app/AlertDialog$Builder;
        //   141: pop            
        //   142: aload_0        
        //   143: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mDownloadErrorMessage:Ljava/lang/String;
        //   146: ifnonnull       206
        //   149: aload_0        
        //   150: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mContext:Landroid/content/Context;
        //   153: getstatic       net/hockeyapp/android/R$string.hockeyapp_download_failed_dialog_message:I
        //   156: invokevirtual   android/content/Context.getString:(I)Ljava/lang/String;
        //   159: astore_1       
        //   160: aload_2        
        //   161: aload_1        
        //   162: invokevirtual   android/app/AlertDialog$Builder.setMessage:(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;
        //   165: pop            
        //   166: aload_2        
        //   167: getstatic       net/hockeyapp/android/R$string.hockeyapp_download_failed_dialog_negative_button:I
        //   170: new             Lnet/hockeyapp/android/tasks/DownloadFileTask$1;
        //   173: dup            
        //   174: aload_0        
        //   175: invokespecial   net/hockeyapp/android/tasks/DownloadFileTask$1.<init>:(Lnet/hockeyapp/android/tasks/DownloadFileTask;)V
        //   178: invokevirtual   android/app/AlertDialog$Builder.setNegativeButton:(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
        //   181: pop            
        //   182: aload_2        
        //   183: getstatic       net/hockeyapp/android/R$string.hockeyapp_download_failed_dialog_positive_button:I
        //   186: new             Lnet/hockeyapp/android/tasks/DownloadFileTask$2;
        //   189: dup            
        //   190: aload_0        
        //   191: invokespecial   net/hockeyapp/android/tasks/DownloadFileTask$2.<init>:(Lnet/hockeyapp/android/tasks/DownloadFileTask;)V
        //   194: invokevirtual   android/app/AlertDialog$Builder.setPositiveButton:(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;
        //   197: pop            
        //   198: aload_2        
        //   199: invokevirtual   android/app/AlertDialog$Builder.create:()Landroid/app/AlertDialog;
        //   202: invokevirtual   android/app/AlertDialog.show:()V
        //   205: return         
        //   206: aload_0        
        //   207: getfield        net/hockeyapp/android/tasks/DownloadFileTask.mDownloadErrorMessage:Ljava/lang/String;
        //   210: astore_1       
        //   211: goto            160
        //   214: astore_2       
        //   215: goto            14
        //   218: astore_1       
        //   219: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      14     214    218    Ljava/lang/Exception;
        //  122    160    218    220    Ljava/lang/Exception;
        //  160    205    218    220    Ljava/lang/Exception;
        //  206    211    218    220    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0122:
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
    
    protected void onProgressUpdate(final Integer... array) {
        try {
            if (this.mProgressDialog == null) {
                (this.mProgressDialog = new ProgressDialog(this.mContext)).setProgressStyle(1);
                this.mProgressDialog.setMessage((CharSequence)"Loading...");
                this.mProgressDialog.setCancelable(false);
                this.mProgressDialog.show();
            }
            this.mProgressDialog.setProgress((int)array[0]);
        }
        catch (Exception ex) {}
    }
    
    protected void setConnectionProperties(final HttpURLConnection httpURLConnection) {
        httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android 4.1.2");
        httpURLConnection.setInstanceFollowRedirects(true);
        if (Build$VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("connection", "close");
        }
    }
}
