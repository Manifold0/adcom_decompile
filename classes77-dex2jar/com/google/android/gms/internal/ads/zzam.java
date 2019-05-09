// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.FilterOutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import android.os.SystemClock;
import java.util.Iterator;
import java.util.Comparator;
import java.util.TreeMap;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.DataInputStream;
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.io.File;
import java.util.Map;

public final class zzam implements zzb
{
    private final Map<String, zzan> zzbw;
    private long zzbx;
    private final File zzby;
    private final int zzbz;
    
    public zzam(final File file) {
        this(file, 5242880);
    }
    
    private zzam(final File zzby, final int n) {
        this.zzbw = new LinkedHashMap<String, zzan>(16, 0.75f, true);
        this.zzbx = 0L;
        this.zzby = zzby;
        this.zzbz = 5242880;
    }
    
    private final void remove(final String s) {
        synchronized (this) {
            final boolean delete = this.zze(s).delete();
            this.removeEntry(s);
            if (!delete) {
                zzaf.d("Could not delete cache entry for key=%s, filename=%s", s, zzd(s));
            }
        }
    }
    
    private final void removeEntry(final String s) {
        final zzan zzan = this.zzbw.remove(s);
        if (zzan != null) {
            this.zzbx -= zzan.zzca;
        }
    }
    
    private static int zza(final InputStream inputStream) throws IOException {
        final int read = inputStream.read();
        if (read == -1) {
            throw new EOFException();
        }
        return read;
    }
    
    private static InputStream zza(final File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }
    
    static String zza(final zzao zzao) throws IOException {
        return new String(zza(zzao, zzc(zzao)), "UTF-8");
    }
    
    static void zza(final OutputStream outputStream, final int n) throws IOException {
        outputStream.write(n & 0xFF);
        outputStream.write(n >> 8 & 0xFF);
        outputStream.write(n >> 16 & 0xFF);
        outputStream.write(n >>> 24);
    }
    
    static void zza(final OutputStream outputStream, final long n) throws IOException {
        outputStream.write((byte)n);
        outputStream.write((byte)(n >>> 8));
        outputStream.write((byte)(n >>> 16));
        outputStream.write((byte)(n >>> 24));
        outputStream.write((byte)(n >>> 32));
        outputStream.write((byte)(n >>> 40));
        outputStream.write((byte)(n >>> 48));
        outputStream.write((byte)(n >>> 56));
    }
    
    static void zza(final OutputStream outputStream, final String s) throws IOException {
        final byte[] bytes = s.getBytes("UTF-8");
        zza(outputStream, (long)bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }
    
    private final void zza(final String s, final zzan zzan) {
        if (!this.zzbw.containsKey(s)) {
            this.zzbx += zzan.zzca;
        }
        else {
            this.zzbx += zzan.zzca - this.zzbw.get(s).zzca;
        }
        this.zzbw.put(s, zzan);
    }
    
    private static byte[] zza(final zzao zzao, final long n) throws IOException {
        final long zzo = zzao.zzo();
        if (n < 0L || n > zzo || (int)n != n) {
            throw new IOException(new StringBuilder(73).append("streamToBytes length=").append(n).append(", maxLength=").append(zzo).toString());
        }
        final byte[] array = new byte[(int)n];
        new DataInputStream(zzao).readFully(array);
        return array;
    }
    
    static int zzb(final InputStream inputStream) throws IOException {
        return zza(inputStream) | 0x0 | zza(inputStream) << 8 | zza(inputStream) << 16 | zza(inputStream) << 24;
    }
    
    static List<zzl> zzb(final zzao zzao) throws IOException {
        final int zzb = zzb((InputStream)zzao);
        List<zzl> emptyList;
        if (zzb == 0) {
            emptyList = Collections.emptyList();
        }
        else {
            emptyList = new ArrayList<zzl>(zzb);
        }
        for (int i = 0; i < zzb; ++i) {
            emptyList.add(new zzl(zza(zzao).intern(), zza(zzao).intern()));
        }
        return emptyList;
    }
    
    static long zzc(final InputStream inputStream) throws IOException {
        return 0x0L | ((long)zza(inputStream) & 0xFFL) | ((long)zza(inputStream) & 0xFFL) << 8 | ((long)zza(inputStream) & 0xFFL) << 16 | ((long)zza(inputStream) & 0xFFL) << 24 | ((long)zza(inputStream) & 0xFFL) << 32 | ((long)zza(inputStream) & 0xFFL) << 40 | ((long)zza(inputStream) & 0xFFL) << 48 | ((long)zza(inputStream) & 0xFFL) << 56;
    }
    
    private static String zzd(String value) {
        final int n = value.length() / 2;
        final String value2 = String.valueOf(String.valueOf(value.substring(0, n).hashCode()));
        value = String.valueOf(String.valueOf(value.substring(n).hashCode()));
        if (value.length() != 0) {
            return value2.concat(value);
        }
        return new String(value2);
    }
    
    private final File zze(final String s) {
        return new File(this.zzby, zzd(s));
    }
    
    @Override
    public final zzc zza(String s) {
        synchronized (this) {
            final zzan zzan = this.zzbw.get(s);
            if (zzan == null) {
                s = null;
            }
            else {
                final File zze = this.zze(s);
                zzao zzao;
                Object zza;
                try {
                    zzao = new zzao(new BufferedInputStream(zza(zze)), zze.length());
                    try {
                        final zzan zzc = com.google.android.gms.internal.ads.zzan.zzc(zzao);
                        if (!TextUtils.equals((CharSequence)s, (CharSequence)zzc.zzcb)) {
                            zzaf.d("%s: key=%s, found=%s", zze.getAbsolutePath(), s, zzc.zzcb);
                            this.removeEntry(s);
                            zzao.close();
                            s = null;
                            return (zzc)s;
                        }
                        zza = zza(zzao, zzao.zzo());
                        final zzc zzc2 = new zzc();
                        zzc2.data = (byte[])zza;
                        zzc2.zza = zzan.zza;
                        zzc2.zzb = zzan.zzb;
                        zzc2.zzc = zzan.zzc;
                        zzc2.zzd = zzan.zzd;
                        zzc2.zze = zzan.zze;
                        final List<zzl> zzg = zzan.zzg;
                        zza = new TreeMap<String, String>((Comparator<? super K>)String.CASE_INSENSITIVE_ORDER);
                        for (final zzl zzl : zzg) {
                            ((Map<String, String>)zza).put(zzl.getName(), zzl.getValue());
                        }
                    }
                    finally {
                        zzao.close();
                    }
                }
                catch (IOException ex) {
                    zzaf.d("%s: %s", zze.getAbsolutePath(), ex.toString());
                    this.remove(s);
                    s = null;
                    return (zzc)s;
                }
                final zzc zzc3;
                zzc3.zzf = (Map<String, String>)zza;
                zzc3.zzg = Collections.unmodifiableList((List<? extends zzl>)zzan.zzg);
                zzao.close();
                s = (String)zzc3;
            }
            return (zzc)s;
        }
    }
    
    @Override
    public final void zza() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        com/google/android/gms/internal/ads/zzam.zzby:Ljava/io/File;
        //     6: invokevirtual   java/io/File.exists:()Z
        //     9: ifne            45
        //    12: aload_0        
        //    13: getfield        com/google/android/gms/internal/ads/zzam.zzby:Ljava/io/File;
        //    16: invokevirtual   java/io/File.mkdirs:()Z
        //    19: ifne            42
        //    22: ldc_w           "Unable to create cache dir %s"
        //    25: iconst_1       
        //    26: anewarray       Ljava/lang/Object;
        //    29: dup            
        //    30: iconst_0       
        //    31: aload_0        
        //    32: getfield        com/google/android/gms/internal/ads/zzam.zzby:Ljava/io/File;
        //    35: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    38: aastore        
        //    39: invokestatic    com/google/android/gms/internal/ads/zzaf.e:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    42: aload_0        
        //    43: monitorexit    
        //    44: return         
        //    45: aload_0        
        //    46: getfield        com/google/android/gms/internal/ads/zzam.zzby:Ljava/io/File;
        //    49: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    52: astore          5
        //    54: aload           5
        //    56: ifnull          42
        //    59: aload           5
        //    61: arraylength    
        //    62: istore_2       
        //    63: iconst_0       
        //    64: istore_1       
        //    65: iload_1        
        //    66: iload_2        
        //    67: if_icmpge       42
        //    70: aload           5
        //    72: iload_1        
        //    73: aaload         
        //    74: astore          6
        //    76: aload           6
        //    78: invokevirtual   java/io/File.length:()J
        //    81: lstore_3       
        //    82: new             Lcom/google/android/gms/internal/ads/zzao;
        //    85: dup            
        //    86: new             Ljava/io/BufferedInputStream;
        //    89: dup            
        //    90: aload           6
        //    92: invokestatic    com/google/android/gms/internal/ads/zzam.zza:(Ljava/io/File;)Ljava/io/InputStream;
        //    95: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    98: lload_3        
        //    99: invokespecial   com/google/android/gms/internal/ads/zzao.<init>:(Ljava/io/InputStream;J)V
        //   102: astore          7
        //   104: aload           7
        //   106: invokestatic    com/google/android/gms/internal/ads/zzan.zzc:(Lcom/google/android/gms/internal/ads/zzao;)Lcom/google/android/gms/internal/ads/zzan;
        //   109: astore          8
        //   111: aload           8
        //   113: lload_3        
        //   114: putfield        com/google/android/gms/internal/ads/zzan.zzca:J
        //   117: aload_0        
        //   118: aload           8
        //   120: getfield        com/google/android/gms/internal/ads/zzan.zzcb:Ljava/lang/String;
        //   123: aload           8
        //   125: invokespecial   com/google/android/gms/internal/ads/zzam.zza:(Ljava/lang/String;Lcom/google/android/gms/internal/ads/zzan;)V
        //   128: aload           7
        //   130: invokevirtual   com/google/android/gms/internal/ads/zzao.close:()V
        //   133: goto            164
        //   136: astore          8
        //   138: aload           7
        //   140: invokevirtual   com/google/android/gms/internal/ads/zzao.close:()V
        //   143: aload           8
        //   145: athrow         
        //   146: astore          7
        //   148: aload           6
        //   150: invokevirtual   java/io/File.delete:()Z
        //   153: pop            
        //   154: goto            164
        //   157: astore          5
        //   159: aload_0        
        //   160: monitorexit    
        //   161: aload           5
        //   163: athrow         
        //   164: iload_1        
        //   165: iconst_1       
        //   166: iadd           
        //   167: istore_1       
        //   168: goto            65
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      42     157    164    Any
        //  45     54     157    164    Any
        //  59     63     157    164    Any
        //  76     104    146    157    Ljava/io/IOException;
        //  76     104    157    164    Any
        //  104    128    136    146    Any
        //  128    133    146    157    Ljava/io/IOException;
        //  128    133    157    164    Any
        //  138    146    146    157    Ljava/io/IOException;
        //  138    146    157    164    Any
        //  148    154    157    164    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.assembler.ir.StackMappingVisitor.push(StackMappingVisitor.java:290)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.execute(StackMappingVisitor.java:833)
        //     at com.strobel.assembler.ir.StackMappingVisitor$InstructionAnalyzer.visit(StackMappingVisitor.java:398)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2030)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    @Override
    public final void zza(final String s, final zzc zzc) {
    Label_0163_Outer:
        while (true) {
            int n = 0;
            while (true) {
            Label_0381:
                while (true) {
                    Label_0378: {
                        Object o2 = null;
                        zzan zzan = null;
                        Label_0354: {
                            synchronized (this) {
                                final int length = zzc.data.length;
                                if (this.zzbx + length >= this.zzbz) {
                                    if (zzaf.DEBUG) {
                                        zzaf.v("Pruning old cache entries.", new Object[0]);
                                    }
                                    final long zzbx = this.zzbx;
                                    final long elapsedRealtime = SystemClock.elapsedRealtime();
                                    final Object o = this.zzbw.entrySet().iterator();
                                    if (!((Iterator)o).hasNext()) {
                                        break Label_0381;
                                    }
                                    o2 = ((Iterator<Map.Entry<K, zzan>>)o).next().getValue();
                                    if (this.zze(((zzan)o2).zzcb).delete()) {
                                        this.zzbx -= ((zzan)o2).zzca;
                                    }
                                    else {
                                        zzaf.d("Could not delete cache entry for key=%s, filename=%s", ((zzan)o2).zzcb, zzd(((zzan)o2).zzcb));
                                    }
                                    ((Iterator)o).remove();
                                    ++n;
                                    if (this.zzbx + length >= this.zzbz * 0.9f) {
                                        break Label_0378;
                                    }
                                    if (zzaf.DEBUG) {
                                        zzaf.v("pruned %d files, %d bytes, %d ms", n, this.zzbx - zzbx, SystemClock.elapsedRealtime() - elapsedRealtime);
                                    }
                                }
                                final Object o = this.zze(s);
                                try {
                                    o2 = new BufferedOutputStream(new FileOutputStream((File)o));
                                    zzan = new zzan(s, zzc);
                                    if (!zzan.zza((OutputStream)o2)) {
                                        ((BufferedOutputStream)o2).close();
                                        zzaf.d("Failed to write header for %s", ((File)o).getAbsolutePath());
                                        throw new IOException();
                                    }
                                    break Label_0354;
                                }
                                catch (IOException ex) {
                                    if (!((File)o).delete()) {
                                        zzaf.d("Could not clean up file %s", ((File)o).getAbsolutePath());
                                    }
                                }
                                return;
                            }
                        }
                        ((FilterOutputStream)o2).write(zzc.data);
                        ((BufferedOutputStream)o2).close();
                        this.zza(s, zzan);
                        return;
                    }
                    continue Label_0163_Outer;
                }
                continue;
            }
        }
    }
}
