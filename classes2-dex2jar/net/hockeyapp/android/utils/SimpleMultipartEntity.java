// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.Random;
import java.io.ByteArrayOutputStream;

public class SimpleMultipartEntity
{
    private static final char[] BOUNDARY_CHARS;
    private String mBoundary;
    private boolean mIsSetFirst;
    private boolean mIsSetLast;
    private ByteArrayOutputStream mOut;
    
    static {
        BOUNDARY_CHARS = "-_1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    }
    
    public SimpleMultipartEntity() {
        this.mIsSetFirst = false;
        this.mIsSetLast = false;
        this.mOut = new ByteArrayOutputStream();
        final StringBuffer sb = new StringBuffer();
        final Random random = new Random();
        for (int i = 0; i < 30; ++i) {
            sb.append(SimpleMultipartEntity.BOUNDARY_CHARS[random.nextInt(SimpleMultipartEntity.BOUNDARY_CHARS.length)]);
        }
        this.mBoundary = sb.toString();
    }
    
    public void addPart(final String s, final File file, final boolean b) throws IOException {
        this.addPart(s, file.getName(), new FileInputStream(file), b);
    }
    
    public void addPart(final String s, final String s2) throws IOException {
        this.writeFirstBoundaryIfNeeds();
        this.mOut.write(("Content-Disposition: form-data; name=\"" + s + "\"\r\n").getBytes());
        this.mOut.write("Content-Type: text/plain; charset=UTF-8\r\n".getBytes());
        this.mOut.write("Content-Transfer-Encoding: 8bit\r\n\r\n".getBytes());
        this.mOut.write(s2.getBytes());
        this.mOut.write(("\r\n--" + this.mBoundary + "\r\n").getBytes());
    }
    
    public void addPart(final String p0, final String p1, final InputStream p2, final String p3, final boolean p4) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   net/hockeyapp/android/utils/SimpleMultipartEntity.writeFirstBoundaryIfNeeds:()V
        //     4: new             Ljava/lang/StringBuilder;
        //     7: dup            
        //     8: invokespecial   java/lang/StringBuilder.<init>:()V
        //    11: ldc             "Content-Type: "
        //    13: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    16: aload           4
        //    18: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    21: ldc             "\r\n"
        //    23: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    26: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    29: astore          4
        //    31: aload_0        
        //    32: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //    35: new             Ljava/lang/StringBuilder;
        //    38: dup            
        //    39: invokespecial   java/lang/StringBuilder.<init>:()V
        //    42: ldc             "Content-Disposition: form-data; name=\""
        //    44: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    47: aload_1        
        //    48: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    51: ldc             "\"; filename=\""
        //    53: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    56: aload_2        
        //    57: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    60: ldc             "\"\r\n"
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    68: invokevirtual   java/lang/String.getBytes:()[B
        //    71: invokevirtual   java/io/ByteArrayOutputStream.write:([B)V
        //    74: aload_0        
        //    75: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //    78: aload           4
        //    80: invokevirtual   java/lang/String.getBytes:()[B
        //    83: invokevirtual   java/io/ByteArrayOutputStream.write:([B)V
        //    86: aload_0        
        //    87: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //    90: ldc             "Content-Transfer-Encoding: binary\r\n\r\n"
        //    92: invokevirtual   java/lang/String.getBytes:()[B
        //    95: invokevirtual   java/io/ByteArrayOutputStream.write:([B)V
        //    98: sipush          4096
        //   101: newarray        B
        //   103: astore_1       
        //   104: aload_3        
        //   105: aload_1        
        //   106: invokevirtual   java/io/InputStream.read:([B)I
        //   109: istore          6
        //   111: iload           6
        //   113: iconst_m1      
        //   114: if_icmpeq       138
        //   117: aload_0        
        //   118: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //   121: aload_1        
        //   122: iconst_0       
        //   123: iload           6
        //   125: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   128: goto            104
        //   131: astore_1       
        //   132: aload_3        
        //   133: invokevirtual   java/io/InputStream.close:()V
        //   136: aload_1        
        //   137: athrow         
        //   138: aload_0        
        //   139: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //   142: invokevirtual   java/io/ByteArrayOutputStream.flush:()V
        //   145: iload           5
        //   147: ifeq            159
        //   150: aload_0        
        //   151: invokevirtual   net/hockeyapp/android/utils/SimpleMultipartEntity.writeLastBoundaryIfNeeds:()V
        //   154: aload_3        
        //   155: invokevirtual   java/io/InputStream.close:()V
        //   158: return         
        //   159: aload_0        
        //   160: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mOut:Ljava/io/ByteArrayOutputStream;
        //   163: new             Ljava/lang/StringBuilder;
        //   166: dup            
        //   167: invokespecial   java/lang/StringBuilder.<init>:()V
        //   170: ldc             "\r\n--"
        //   172: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   175: aload_0        
        //   176: getfield        net/hockeyapp/android/utils/SimpleMultipartEntity.mBoundary:Ljava/lang/String;
        //   179: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   182: ldc             "\r\n"
        //   184: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   187: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   190: invokevirtual   java/lang/String.getBytes:()[B
        //   193: invokevirtual   java/io/ByteArrayOutputStream.write:([B)V
        //   196: goto            154
        //   199: astore_1       
        //   200: aload_1        
        //   201: invokevirtual   java/io/IOException.printStackTrace:()V
        //   204: return         
        //   205: astore_2       
        //   206: aload_2        
        //   207: invokevirtual   java/io/IOException.printStackTrace:()V
        //   210: goto            136
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      104    131    138    Any
        //  104    111    131    138    Any
        //  117    128    131    138    Any
        //  132    136    205    213    Ljava/io/IOException;
        //  138    145    131    138    Any
        //  150    154    131    138    Any
        //  154    158    199    205    Ljava/io/IOException;
        //  159    196    131    138    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0136:
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
    
    public void addPart(final String s, final String s2, final InputStream inputStream, final boolean b) throws IOException {
        this.addPart(s, s2, inputStream, "application/octet-stream", b);
    }
    
    public String getBoundary() {
        return this.mBoundary;
    }
    
    public long getContentLength() {
        this.writeLastBoundaryIfNeeds();
        return this.mOut.toByteArray().length;
    }
    
    public String getContentType() {
        return "multipart/form-data; boundary=" + this.getBoundary();
    }
    
    public ByteArrayOutputStream getOutputStream() {
        this.writeLastBoundaryIfNeeds();
        return this.mOut;
    }
    
    public void writeFirstBoundaryIfNeeds() throws IOException {
        if (!this.mIsSetFirst) {
            this.mOut.write(("--" + this.mBoundary + "\r\n").getBytes());
        }
        this.mIsSetFirst = true;
    }
    
    public void writeLastBoundaryIfNeeds() {
        if (this.mIsSetLast) {
            return;
        }
        while (true) {
            try {
                this.mOut.write(("\r\n--" + this.mBoundary + "--\r\n").getBytes());
                this.mIsSetLast = true;
            }
            catch (IOException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
}
