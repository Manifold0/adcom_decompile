// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import java.io.Serializable;
import com.kongregate.android.internal.util.StringUtils;
import java.util.Iterator;
import com.kongregate.android.internal.util.j;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Arrays;
import java.net.HttpCookie;
import java.util.Set;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import java.util.List;

public class f
{
    public static final String a = "_kongregate_session";
    public static final String b = "kong_sec";
    public static final String c = "kongregateMobileApi";
    public static final String d = "m_pass";
    public static final String e = "kong_svid";
    public static final List<String> f;
    private final List<String> g;
    private final List<String> h;
    private final String i;
    private final CookieSyncManager j;
    private final CookieManager k;
    private final String l;
    private final Set<HttpCookie> m;
    
    static {
        f = Arrays.asList("_kongregate_session", "kong_sec", "kong_flash_messages");
    }
    
    public f(final String l) {
        this.g = Arrays.asList("_kongregate_session", "kong_sec");
        this.h = Arrays.asList("kong_svid");
        this.i = "; ";
        this.m = new HashSet<HttpCookie>();
        this.j = CookieSyncManager.getInstance();
        this.k = CookieManager.getInstance();
        this.l = l;
        this.a();
    }
    
    private String c(final HttpCookie httpCookie) {
        final StringBuilder sb = new StringBuilder(256);
        sb.append(httpCookie.getName()).append("=").append(httpCookie.getValue());
        sb.append("; path=").append(httpCookie.getPath());
        if (httpCookie.getSecure()) {
            sb.append("; secure");
        }
        if (this.g.contains(httpCookie.getName())) {
            sb.append("; HttpOnly");
        }
        if (httpCookie.getMaxAge() > 0L) {
            sb.append("; max-age=" + httpCookie.getMaxAge());
        }
        return sb.toString();
    }
    
    private HttpCookie e(String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ";");
        s = stringTokenizer.nextToken();
        final int index = s.indexOf(61);
        if (index != -1) {
            final HttpCookie httpCookie = new HttpCookie(s.substring(0, index).trim(), s.substring(index + 1));
            httpCookie.setDomain(this.l);
            httpCookie.setPath("/");
            while (stringTokenizer.hasMoreTokens()) {
                s = stringTokenizer.nextToken();
                final int index2 = s.indexOf(61);
                if (index2 == -1) {
                    s = s.trim();
                }
                else {
                    s = s.substring(0, index2).trim();
                }
                if (s.equalsIgnoreCase("secure")) {
                    httpCookie.setSecure(true);
                    break;
                }
            }
            return httpCookie;
        }
        throw new IllegalArgumentException("Malformed cookie");
    }
    
    private Map<String, HttpCookie> g() {
        final String cookie = this.k.getCookie("https://" + this.l);
        final HashMap<String, HttpCookie> hashMap = new HashMap<String, HttpCookie>();
        if (cookie != null && cookie.length() > 0) {
            final String[] split = cookie.split("; ");
            for (int length = split.length, i = 0; i < length; ++i) {
                final String s = split[i];
                if (s != null) {
                    final String[] split2 = s.split("=");
                    if (split2 != null && split2.length >= 2) {
                        final HttpCookie a = this.a(split2[0], split2[1]);
                        if ("kong_sec".equals(a.getName())) {
                            a.setSecure(true);
                        }
                        hashMap.put(a.getName(), a);
                    }
                }
            }
        }
        return hashMap;
    }
    
    public HttpCookie a(final String s, final String s2) {
        return this.a(s, s2, false);
    }
    
    public HttpCookie a(final String s, String s2, final boolean b) {
        try {
            final Object o;
            s2 = (String)(o = new HttpCookie(s, s2));
            final f f = this;
            final String s3 = f.l;
            ((HttpCookie)o).setDomain(s3);
            final String s4 = s2;
            final String s5 = "/";
            ((HttpCookie)s4).setPath(s5);
            final String s6 = s2;
            final boolean b2 = b;
            ((HttpCookie)s6).setSecure(b2);
            final String s7 = s2;
            return (HttpCookie)s7;
        }
        catch (IllegalArgumentException ex) {
            s2 = null;
        }
        while (true) {
            try {
                final Object o = s2;
                final f f = this;
                final String s3 = f.l;
                ((HttpCookie)o).setDomain(s3);
                final String s4 = s2;
                final String s5 = "/";
                ((HttpCookie)s4).setPath(s5);
                final String s6 = s2;
                final boolean b2 = b;
                ((HttpCookie)s6).setSecure(b2);
                final String s7 = s2;
                return (HttpCookie)s7;
                final IllegalArgumentException ex;
                com.kongregate.android.internal.util.j.c("unable to build cookie. malformed name: " + s, ex);
                return (HttpCookie)s2;
            }
            catch (IllegalArgumentException ex) {
                continue;
            }
            break;
        }
    }
    
    public void a() {
        synchronized (this) {
            com.kongregate.android.internal.util.j.a("Reloading cookies from webview: " + this.l);
            this.m.clear();
            final Iterator<HttpCookie> iterator = this.g().values().iterator();
            while (iterator.hasNext()) {
                this.m.add(iterator.next());
            }
        }
        com.kongregate.android.internal.util.j.a("cookies loaded");
    }
    // monitorexit(this)
    
    public void a(final String s) {
        synchronized (this) {
            try {
                final HttpCookie e = this.e(s);
                if (e != null) {
                    this.a(e);
                }
            }
            catch (IllegalArgumentException ex) {
                com.kongregate.android.internal.util.j.c("Illegal cookie", ex);
            }
            catch (NullPointerException ex2) {
                com.kongregate.android.internal.util.j.c("NPE parsing cookies", ex2);
            }
        }
    }
    
    public void a(final HttpCookie httpCookie) {
        synchronized (this) {
            this.a(httpCookie, false);
        }
    }
    
    public void a(final HttpCookie httpCookie, final boolean b) {
        // monitorenter(this)
        Label_0015: {
            if (httpCookie != null) {
                break Label_0015;
            }
            try {
                com.kongregate.android.internal.util.j.a("Can't add a null cookie.");
                Label_0012: {
                    return;
                }
                com.kongregate.android.internal.util.j.a("Cookie added: " + httpCookie.getName());
                this.m.add(httpCookie);
                // iftrue(Label_0012:, !b)
                this.k.setCookie("https://" + this.l, this.c(httpCookie));
                this.j.sync();
            }
            finally {
            }
            // monitorexit(this)
        }
    }
    
    public void a(final boolean b) {
        synchronized (this) {
            final Map<String, HttpCookie> g = this.g();
            for (final HttpCookie httpCookie : this.f()) {
                if (b || !g.containsKey(httpCookie.getName()) || this.h.contains(httpCookie.getName())) {
                    this.k.setCookie("https://" + this.l, this.c(httpCookie));
                }
            }
        }
        this.j.sync();
    }
    // monitorexit(this)
    
    public String b(String value) {
        synchronized (this) {
            for (final HttpCookie httpCookie : this.f()) {
                if (httpCookie.getName().equals(value)) {
                    value = httpCookie.getValue();
                    return value;
                }
            }
            value = null;
            return value;
        }
    }
    
    public void b() {
        synchronized (this) {
            final Iterator<String> iterator = com.kongregate.o.g.f.f.iterator();
            while (iterator.hasNext()) {
                this.k.setCookie("https://" + this.l, iterator.next() + "=");
            }
        }
        this.j.sync();
        this.a();
    }
    // monitorexit(this)
    
    public void b(final HttpCookie httpCookie) {
        synchronized (this) {
            if (!this.g.contains(httpCookie.getName())) {
                this.m.add(httpCookie);
            }
        }
    }
    
    public String c() {
        StringBuffer sb;
        while (true) {
            while (true) {
                Label_0115: {
                    synchronized (this) {
                        com.kongregate.android.internal.util.j.a("getSharedKongregateCookies from webview cookie store");
                        this.a();
                        sb = new StringBuffer();
                        for (Serializable append : com.kongregate.o.g.f.f) {
                            final String b = this.b((String)append);
                            if (sb.length() > 0) {
                                sb.append("; ");
                            }
                            append = new StringBuilder().append((String)append).append("=");
                            if (b == null) {
                                break Label_0115;
                            }
                            sb.append(((StringBuilder)append).append(b).toString());
                        }
                        break;
                    }
                }
                final String b = "";
                continue;
            }
        }
        final String b2 = this.b("kong_svid");
        if (!StringUtils.a((CharSequence)b2)) {
            if (sb.length() > 0) {
                sb.append("; ");
            }
            sb.append("kong_svid=" + b2);
        }
        // monitorexit(this)
        return sb.toString();
    }
    
    public void c(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: ldc_w           "setting shared cookies from shared storage: "
        //     5: invokestatic    com/kongregate/android/internal/util/j.a:(Ljava/lang/String;)V
        //     8: aload_1        
        //     9: invokestatic    com/kongregate/android/internal/util/StringUtils.a:(Ljava/lang/CharSequence;)Z
        //    12: ifne            257
        //    15: aload_1        
        //    16: ldc             "; "
        //    18: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    21: astore          5
        //    23: aload           5
        //    25: arraylength    
        //    26: istore_3       
        //    27: iconst_0       
        //    28: istore_2       
        //    29: iload_2        
        //    30: iload_3        
        //    31: if_icmpge       207
        //    34: aload           5
        //    36: iload_2        
        //    37: aaload         
        //    38: astore          6
        //    40: aload           6
        //    42: ldc             "="
        //    44: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //    47: astore          7
        //    49: aload           7
        //    51: arraylength    
        //    52: istore          4
        //    54: iload           4
        //    56: iconst_2       
        //    57: if_icmpne       189
        //    60: new             Ljava/net/HttpCookie;
        //    63: dup            
        //    64: aload           7
        //    66: iconst_0       
        //    67: aaload         
        //    68: aload           7
        //    70: iconst_1       
        //    71: aaload         
        //    72: invokespecial   java/net/HttpCookie.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //    75: astore          8
        //    77: aload           8
        //    79: aload_0        
        //    80: getfield        com/kongregate/o/g/f.l:Ljava/lang/String;
        //    83: invokevirtual   java/net/HttpCookie.setDomain:(Ljava/lang/String;)V
        //    86: aload           8
        //    88: ldc             "/"
        //    90: invokevirtual   java/net/HttpCookie.setPath:(Ljava/lang/String;)V
        //    93: ldc             "kong_sec"
        //    95: aload           7
        //    97: iconst_0       
        //    98: aaload         
        //    99: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   102: ifeq            111
        //   105: aload           8
        //   107: iconst_1       
        //   108: invokevirtual   java/net/HttpCookie.setSecure:(Z)V
        //   111: aload_0        
        //   112: getfield        com/kongregate/o/g/f.k:Landroid/webkit/CookieManager;
        //   115: new             Ljava/lang/StringBuilder;
        //   118: dup            
        //   119: invokespecial   java/lang/StringBuilder.<init>:()V
        //   122: ldc             "https://"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: aload_0        
        //   128: getfield        com/kongregate/o/g/f.l:Ljava/lang/String;
        //   131: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   134: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   137: aload_0        
        //   138: aload           8
        //   140: invokespecial   com/kongregate/o/g/f.c:(Ljava/net/HttpCookie;)Ljava/lang/String;
        //   143: invokevirtual   android/webkit/CookieManager.setCookie:(Ljava/lang/String;Ljava/lang/String;)V
        //   146: iload_2        
        //   147: iconst_1       
        //   148: iadd           
        //   149: istore_2       
        //   150: goto            29
        //   153: astore          7
        //   155: new             Ljava/lang/StringBuilder;
        //   158: dup            
        //   159: invokespecial   java/lang/StringBuilder.<init>:()V
        //   162: ldc_w           "malformed cookie: "
        //   165: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   168: aload           6
        //   170: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   173: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   176: aload           7
        //   178: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   181: goto            146
        //   184: astore_1       
        //   185: aload_0        
        //   186: monitorexit    
        //   187: aload_1        
        //   188: athrow         
        //   189: aload           7
        //   191: arraylength    
        //   192: iconst_1       
        //   193: if_icmpne       146
        //   196: aload_0        
        //   197: aload           7
        //   199: iconst_0       
        //   200: aaload         
        //   201: invokevirtual   com/kongregate/o/g/f.d:(Ljava/lang/String;)V
        //   204: goto            146
        //   207: getstatic       com/kongregate/o/g/f.f:Ljava/util/List;
        //   210: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //   215: astore          5
        //   217: aload           5
        //   219: invokeinterface java/util/Iterator.hasNext:()Z
        //   224: ifeq            257
        //   227: aload           5
        //   229: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   234: checkcast       Ljava/lang/String;
        //   237: astore          6
        //   239: aload_1        
        //   240: aload           6
        //   242: invokevirtual   java/lang/String.contains:(Ljava/lang/CharSequence;)Z
        //   245: ifne            217
        //   248: aload_0        
        //   249: aload           6
        //   251: invokevirtual   com/kongregate/o/g/f.d:(Ljava/lang/String;)V
        //   254: goto            217
        //   257: aload_0        
        //   258: getfield        com/kongregate/o/g/f.j:Landroid/webkit/CookieSyncManager;
        //   261: invokevirtual   android/webkit/CookieSyncManager.sync:()V
        //   264: aload_0        
        //   265: invokevirtual   com/kongregate/o/g/f.a:()V
        //   268: aload_0        
        //   269: monitorexit    
        //   270: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                
        //  -----  -----  -----  -----  ------------------------------------
        //  2      27     184    189    Any
        //  40     54     184    189    Any
        //  60     111    153    184    Ljava/lang/IllegalArgumentException;
        //  60     111    184    189    Any
        //  111    146    153    184    Ljava/lang/IllegalArgumentException;
        //  111    146    184    189    Any
        //  155    181    184    189    Any
        //  189    204    184    189    Any
        //  207    217    184    189    Any
        //  217    254    184    189    Any
        //  257    268    184    189    Any
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
    
    public void d() {
        synchronized (this) {
            com.kongregate.android.internal.util.j.a("Clearing all cookies");
            this.m.clear();
            this.k.removeAllCookie();
            this.k.setCookie("https://" + this.l, "");
            this.j.sync();
        }
    }
    
    public void d(final String s) {
        synchronized (this) {
            final HttpCookie httpCookie = new HttpCookie(s, "");
            httpCookie.setDomain(this.l);
            httpCookie.setPath("/");
            if ("kong_sec".equals(s)) {
                httpCookie.setSecure(true);
            }
            this.k.setCookie("https://" + this.l, this.c(httpCookie));
            this.m.remove(httpCookie);
            this.j.sync();
        }
    }
    
    public String e() {
        synchronized (this) {
            final StringBuilder sb = new StringBuilder(1024);
            for (final HttpCookie httpCookie : this.f()) {
                sb.append(httpCookie.getName()).append("=").append(httpCookie.getValue()).append("; ");
            }
        }
        final StringBuilder sb2;
        // monitorexit(this)
        return sb2.toString();
    }
    
    protected Set<HttpCookie> f() {
        return this.m;
    }
}
