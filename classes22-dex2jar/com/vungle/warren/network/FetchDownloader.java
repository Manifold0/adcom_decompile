// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.network;

import com.tonyodev.fetch.request.Request;
import java.io.IOException;
import android.support.annotation.NonNull;
import com.tonyodev.fetch.Fetch$Settings;
import java.io.File;
import android.util.Pair;
import java.util.HashMap;
import com.tonyodev.fetch.Fetch;
import android.content.Context;
import com.tonyodev.fetch.listener.FetchListener;

public class FetchDownloader implements Downloader, FetchListener
{
    private static final String DOWNLOADS_FOLDER = "downloads_vungle";
    private static final String TAG;
    private Context context;
    private int downloadCount;
    protected Fetch fetch;
    protected HashMap<Long, Pair<Listener, File>> operations;
    
    static {
        TAG = FetchDownloader.class.getSimpleName();
    }
    
    public FetchDownloader(final Context context) {
        this.downloadCount = 0;
        (this.fetch = Fetch.getInstance(context)).addFetchListener((FetchListener)this);
        this.fetch.removeAll();
        this.operations = new HashMap<Long, Pair<Listener, File>>();
        this.context = context;
        new Fetch$Settings(context).enableLogging(true).apply();
    }
    
    private File downloadFolder() {
        return new File(this.context.getCacheDir().getPath() + File.separator + "downloads_vungle");
    }
    
    @Override
    public boolean download(@NonNull final String s, @NonNull final File file, @NonNull final Listener listener) throws IOException, IllegalArgumentException, IllegalStateException {
        if (file.exists()) {
            try {
                if (!file.delete()) {
                    throw new IOException("Failed to delete file at " + file.getAbsolutePath());
                }
            }
            catch (SecurityException ex) {
                throw new IOException(ex);
            }
        }
        if (!this.fetch.isValid()) {
            if (this.context == null) {
                throw new IllegalStateException("Context is null, application is no longer running");
            }
            (this.fetch = Fetch.getInstance(this.context)).addFetchListener((FetchListener)this);
        }
        this.operations.put(this.fetch.enqueue(new Request(s, this.downloadFolder().getPath(), file.getName() + " (" + this.downloadCount++ + ")")), (Pair<Listener, File>)new Pair((Object)listener, (Object)file));
        return true;
    }
    
    public void onUpdate(final long p0, final int p1, final int p2, final long p3, final long p4, final int p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: getstatic       java/util/Locale.ENGLISH:Ljava/util/Locale;
        //     5: ldc             "%s: %d%% completed, %d/%d , error: %d"
        //     7: iconst_5       
        //     8: anewarray       Ljava/lang/Object;
        //    11: dup            
        //    12: iconst_0       
        //    13: lload_1        
        //    14: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    17: aastore        
        //    18: dup            
        //    19: iconst_1       
        //    20: iload           4
        //    22: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    25: aastore        
        //    26: dup            
        //    27: iconst_2       
        //    28: lload           5
        //    30: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    33: aastore        
        //    34: dup            
        //    35: iconst_3       
        //    36: lload           7
        //    38: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    41: aastore        
        //    42: dup            
        //    43: iconst_4       
        //    44: iload           9
        //    46: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //    49: aastore        
        //    50: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //    53: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //    56: pop            
        //    57: aload_0        
        //    58: getfield        com/vungle/warren/network/FetchDownloader.operations:Ljava/util/HashMap;
        //    61: lload_1        
        //    62: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    65: invokevirtual   java/util/HashMap.containsKey:(Ljava/lang/Object;)Z
        //    68: ifne            72
        //    71: return         
        //    72: aload_0        
        //    73: getfield        com/vungle/warren/network/FetchDownloader.operations:Ljava/util/HashMap;
        //    76: lload_1        
        //    77: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    80: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    83: checkcast       Landroid/util/Pair;
        //    86: getfield        android/util/Pair.first:Ljava/lang/Object;
        //    89: checkcast       Lcom/vungle/warren/network/Downloader$Listener;
        //    92: astore          10
        //    94: aload_0        
        //    95: getfield        com/vungle/warren/network/FetchDownloader.operations:Ljava/util/HashMap;
        //    98: lload_1        
        //    99: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   102: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   105: checkcast       Landroid/util/Pair;
        //   108: getfield        android/util/Pair.second:Ljava/lang/Object;
        //   111: checkcast       Ljava/io/File;
        //   114: astore          11
        //   116: aload           10
        //   118: ifnull          71
        //   121: aload           11
        //   123: ifnull          71
        //   126: aload           10
        //   128: iload           4
        //   130: invokeinterface com/vungle/warren/network/Downloader$Listener.onProgress:(I)V
        //   135: iload           9
        //   137: iconst_m1      
        //   138: if_icmpeq       184
        //   141: getstatic       com/vungle/warren/network/FetchDownloader.TAG:Ljava/lang/String;
        //   144: new             Ljava/lang/StringBuilder;
        //   147: dup            
        //   148: invokespecial   java/lang/StringBuilder.<init>:()V
        //   151: ldc             "error: "
        //   153: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   156: iload           9
        //   158: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   161: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   164: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   167: pop            
        //   168: aload           10
        //   170: new             Ljava/io/IOException;
        //   173: dup            
        //   174: ldc             "Error downloading !!!"
        //   176: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   179: invokeinterface com/vungle/warren/network/Downloader$Listener.onError:(Ljava/lang/Throwable;)V
        //   184: iload           4
        //   186: bipush          100
        //   188: if_icmpne       71
        //   191: aload_0        
        //   192: getfield        com/vungle/warren/network/FetchDownloader.fetch:Lcom/tonyodev/fetch/Fetch;
        //   195: lload_1        
        //   196: invokevirtual   com/tonyodev/fetch/Fetch.getDownloadedFile:(J)Ljava/io/File;
        //   199: astore          12
        //   201: aload           12
        //   203: ifnonnull       223
        //   206: aload           10
        //   208: new             Ljava/io/IOException;
        //   211: dup            
        //   212: ldc             "Downloaded file not found!"
        //   214: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   217: invokeinterface com/vungle/warren/network/Downloader$Listener.onError:(Ljava/lang/Throwable;)V
        //   222: return         
        //   223: aload           11
        //   225: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   228: astore          13
        //   230: aload           13
        //   232: invokevirtual   java/io/File.exists:()Z
        //   235: ifne            244
        //   238: aload           13
        //   240: invokevirtual   java/io/File.mkdir:()Z
        //   243: pop            
        //   244: aload           12
        //   246: aload           11
        //   248: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //   251: ifeq            400
        //   254: aload           11
        //   256: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   259: ldc             "postroll"
        //   261: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   264: ifeq            324
        //   267: new             Ljava/io/File;
        //   270: dup            
        //   271: new             Ljava/lang/StringBuilder;
        //   274: dup            
        //   275: invokespecial   java/lang/StringBuilder.<init>:()V
        //   278: aload           11
        //   280: invokevirtual   java/io/File.getParent:()Ljava/lang/String;
        //   283: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   286: getstatic       java/io/File.separator:Ljava/lang/String;
        //   289: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   292: ldc_w           "postrollUnzip"
        //   295: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   298: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   301: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   304: astore          12
        //   306: aload           11
        //   308: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   311: aload           12
        //   313: invokevirtual   java/io/File.getPath:()Ljava/lang/String;
        //   316: invokestatic    com/vungle/warren/utility/UnzipUtility.unzip:(Ljava/lang/String;Ljava/lang/String;)V
        //   319: aload           11
        //   321: invokestatic    com/vungle/warren/utility/FileUtility.delete:(Ljava/io/File;)V
        //   324: aload           10
        //   326: aload           11
        //   328: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   331: invokeinterface com/vungle/warren/network/Downloader$Listener.onComplete:(Ljava/io/File;)V
        //   336: aload_0        
        //   337: getfield        com/vungle/warren/network/FetchDownloader.operations:Ljava/util/HashMap;
        //   340: lload_1        
        //   341: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   344: invokevirtual   java/util/HashMap.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   347: pop            
        //   348: aload_0        
        //   349: getfield        com/vungle/warren/network/FetchDownloader.operations:Ljava/util/HashMap;
        //   352: invokevirtual   java/util/HashMap.isEmpty:()Z
        //   355: ifeq            71
        //   358: aload_0        
        //   359: getfield        com/vungle/warren/network/FetchDownloader.fetch:Lcom/tonyodev/fetch/Fetch;
        //   362: invokevirtual   com/tonyodev/fetch/Fetch.release:()V
        //   365: return         
        //   366: astore          12
        //   368: getstatic       com/vungle/warren/network/FetchDownloader.TAG:Ljava/lang/String;
        //   371: ldc_w           "Error on unzipping assets"
        //   374: aload           12
        //   376: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   379: pop            
        //   380: goto            319
        //   383: astore          12
        //   385: getstatic       com/vungle/warren/network/FetchDownloader.TAG:Ljava/lang/String;
        //   388: ldc_w           "Error on deleting zip assets archive"
        //   391: aload           12
        //   393: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   396: pop            
        //   397: goto            324
        //   400: aload           10
        //   402: new             Ljava/io/IOException;
        //   405: dup            
        //   406: ldc_w           "Error processing file to destination directory!"
        //   409: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   412: invokeinterface com/vungle/warren/network/Downloader$Listener.onError:(Ljava/lang/Throwable;)V
        //   417: goto            348
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  306    319    366    383    Ljava/io/IOException;
        //  319    324    383    400    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0319:
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
    
    @Override
    public void pause() {
    }
    
    @Override
    public void resume() {
    }
}
