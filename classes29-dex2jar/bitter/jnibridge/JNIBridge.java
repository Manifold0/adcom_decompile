// 
// Decompiled by Procyon v0.5.34
// 

package bitter.jnibridge;

import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JNIBridge
{
    static native void delete(final long p0);
    
    static void disableInterfaceProxy(final Object o) {
        if (o != null) {
            ((a)Proxy.getInvocationHandler(o)).a();
        }
    }
    
    static native Object invoke(final long p0, final Class p1, final Method p2, final Object[] p3);
    
    static Object newInterfaceProxy(final long n, final Class[] array) {
        return Proxy.newProxyInstance(JNIBridge.class.getClassLoader(), array, new a(n));
    }
    
    private static final class a implements InvocationHandler
    {
        private Object a;
        private long b;
        private Constructor c;
        
        public a(final long b) {
            this.a = new Object[0];
            this.b = b;
            try {
                (this.c = MethodHandles.Lookup.class.getDeclaredConstructor(Class.class, Integer.TYPE)).setAccessible(true);
            }
            catch (NoClassDefFoundError noClassDefFoundError) {
                this.c = null;
            }
            catch (NoSuchMethodException ex) {
                this.c = null;
            }
        }
        
        private Object a(final Object o, final Method method, final Object[] array) {
            Object[] array2 = array;
            if (array == null) {
                array2 = new Object[0];
            }
            final Class<?> declaringClass = method.getDeclaringClass();
            return ((MethodHandles.Lookup)this.c.newInstance(declaringClass, 2)).in(declaringClass).unreflectSpecial(method, declaringClass).bindTo(o).invokeWithArguments(array2);
        }
        
        public final void a() {
            synchronized (this.a) {
                this.b = 0L;
            }
        }
        
        public final void finalize() {
            synchronized (this.a) {
                if (this.b == 0L) {
                    return;
                }
                JNIBridge.delete(this.b);
            }
        }
        
        @Override
        public final Object invoke(final Object p0, final Method p1, final Object[] p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        bitter/jnibridge/JNIBridge$a.a:Ljava/lang/Object;
            //     4: astore          4
            //     6: aload           4
            //     8: monitorenter   
            //     9: aload_0        
            //    10: getfield        bitter/jnibridge/JNIBridge$a.b:J
            //    13: lconst_0       
            //    14: lcmp           
            //    15: ifne            23
            //    18: aload           4
            //    20: monitorexit    
            //    21: aconst_null    
            //    22: areturn        
            //    23: aload_0        
            //    24: getfield        bitter/jnibridge/JNIBridge$a.b:J
            //    27: aload_2        
            //    28: invokevirtual   java/lang/reflect/Method.getDeclaringClass:()Ljava/lang/Class;
            //    31: aload_2        
            //    32: aload_3        
            //    33: invokestatic    bitter/jnibridge/JNIBridge.invoke:(JLjava/lang/Class;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
            //    36: astore          5
            //    38: aload           4
            //    40: monitorexit    
            //    41: aload           5
            //    43: areturn        
            //    44: astore_1       
            //    45: aload           4
            //    47: monitorexit    
            //    48: aload_1        
            //    49: athrow         
            //    50: astore          5
            //    52: aload_0        
            //    53: getfield        bitter/jnibridge/JNIBridge$a.c:Ljava/lang/reflect/Constructor;
            //    56: ifnonnull       70
            //    59: getstatic       java/lang/System.err:Ljava/io/PrintStream;
            //    62: ldc             "JNIBridge error: Java interface default methods are only supported since Android Oreo"
            //    64: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //    67: aload           5
            //    69: athrow         
            //    70: aload_2        
            //    71: invokevirtual   java/lang/reflect/Method.getModifiers:()I
            //    74: sipush          1024
            //    77: iand           
            //    78: ifne            94
            //    81: aload_0        
            //    82: aload_1        
            //    83: aload_2        
            //    84: aload_3        
            //    85: invokespecial   bitter/jnibridge/JNIBridge$a.a:(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)Ljava/lang/Object;
            //    88: astore_1       
            //    89: aload           4
            //    91: monitorexit    
            //    92: aload_1        
            //    93: areturn        
            //    94: aload           5
            //    96: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                         
            //  -----  -----  -----  -----  -----------------------------
            //  9      21     44     50     Any
            //  23     38     50     97     Ljava/lang/NoSuchMethodError;
            //  23     38     44     50     Any
            //  38     41     44     50     Any
            //  45     48     44     50     Any
            //  52     70     44     50     Any
            //  70     92     44     50     Any
            //  94     97     44     50     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    }
}
