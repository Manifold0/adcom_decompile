// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.net.Uri;
import java.security.Key;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Iterator;
import java.util.Collection;
import java.util.TreeSet;
import java.util.Map;

public class HmacSignature
{
    private String a;
    private String b;
    
    public HmacSignature(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    private static String a(final Map map) {
        final TreeSet set = new TreeSet<String>(map.keySet());
        final StringBuilder sb = new StringBuilder();
        for (final String s : set) {
            sb.append(s + "=" + map.get(s) + "&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        TapjoyLog.v("HmacSignature", "Unhashed String: " + sb.toString());
        return sb.toString();
    }
    
    public boolean matches(final String s, final Map map, final String s2) {
        return this.sign(s, map).equals(s2);
    }
    
    public String sign(String s, final Map map) {
    Label_0287_Outer:
        while (true) {
            final int n = 0;
            while (true) {
                Label_0350: {
                    Label_0345: {
                        try {
                            final SecretKeySpec secretKeySpec = new SecretKeySpec(this.b.getBytes(), this.a);
                            final Mac instance = Mac.getInstance(this.a);
                            instance.init(secretKeySpec);
                            final Uri parse = Uri.parse(s);
                            final String string = parse.getScheme() + "://" + parse.getHost();
                            if (parse.getScheme().equals("http") && parse.getPort() == 80) {
                                break Label_0345;
                            }
                            if (parse.getScheme().equals("https") && parse.getPort() == 443) {
                                break Label_0345;
                            }
                            break Label_0350;
                            // iftrue(Label_0321:, hexString.length() != 1)
                            // iftrue(Label_0335:, n2 >= length)
                            // iftrue(Label_0176:, n3 != 0)
                            while (true) {
                            Label_0287:
                                while (true) {
                                Label_0321_Outer:
                                    while (true) {
                                        final byte[] doFinal;
                                        int n2 = 0;
                                        final String hexString = Integer.toHexString(doFinal[n2] & 0xFF);
                                        while (true) {
                                            final StringBuilder sb;
                                            Block_9: {
                                                break Block_9;
                                                sb.append(hexString);
                                                ++n2;
                                                break Label_0287;
                                            }
                                            sb.append('0');
                                            continue Label_0287_Outer;
                                        }
                                        continue Label_0321_Outer;
                                    }
                                    s = string;
                                    break Label_0287;
                                    s = s.toLowerCase();
                                    s += parse.getPath();
                                    s = "POST&" + Uri.encode(s) + "&" + Uri.encode(a(map));
                                    TapjoyLog.v("HmacSignature", "Base Url: " + s);
                                    final byte[] doFinal = instance.doFinal(s.getBytes());
                                    final StringBuilder sb = new StringBuilder();
                                    final int length = doFinal.length;
                                    int n2 = n;
                                    continue Label_0287;
                                }
                                s = string;
                                Block_7: {
                                    break Block_7;
                                    Label_0335: {
                                        final StringBuilder sb;
                                        s = sb.toString();
                                    }
                                    return s;
                                }
                                s = string + ":" + parse.getPort();
                                continue;
                            }
                        }
                        // iftrue(Label_0176:, -1 == parse.getPort())
                        catch (Exception ex) {
                            return null;
                        }
                    }
                    final int n3 = 1;
                    continue;
                }
                final int n3 = 0;
                continue;
            }
        }
    }
}
