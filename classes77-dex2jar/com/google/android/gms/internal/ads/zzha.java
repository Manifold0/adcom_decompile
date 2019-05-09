// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.io.IOException;
import java.util.Locale;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Comparator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;

@zzadh
public final class zzha
{
    private final int zzaiz;
    private final zzgq zzajb;
    private String zzajj;
    private String zzajk;
    private final boolean zzajl;
    private final int zzajm;
    private final int zzajn;
    
    public zzha(final int zzaiz, final int zzajm, final int zzajn) {
        this.zzaiz = zzaiz;
        this.zzajl = false;
        if (zzajm > 64 || zzajm < 0) {
            this.zzajm = 64;
        }
        else {
            this.zzajm = zzajm;
        }
        if (zzajn <= 0) {
            this.zzajn = 1;
        }
        else {
            this.zzajn = zzajn;
        }
        this.zzajb = new zzgz(this.zzajm);
    }
    
    public final String zza(ArrayList<String> zzgt, final ArrayList<zzgp> list) {
        Collections.sort((List<Object>)list, (Comparator<? super Object>)new zzhb(this));
        final HashSet<String> set = new HashSet<String>();
        int i = 0;
    Label_0354_Outer:
        while (i < list.size()) {
            final String[] split = Normalizer.normalize(((ArrayList<CharSequence>)zzgt).get(list.get(i).zzhf()), Normalizer.Form.NFKC).toLowerCase(Locale.US).split("\n");
        Label_0354:
            while (true) {
                if (split.length != 0) {
                    int j = 0;
                Label_0271_Outer:
                    while (j < split.length) {
                        final String s = split[j];
                        while (true) {
                            Label_0565: {
                                if (s.indexOf("'") == -1) {
                                    break Label_0565;
                                }
                                final StringBuilder sb = new StringBuilder(s);
                                int n = 1;
                                boolean b = false;
                                while (n + 2 <= sb.length()) {
                                    int n2 = n;
                                    if (sb.charAt(n) == '\'') {
                                        if (sb.charAt(n - 1) != ' ' && (sb.charAt(n + 1) == 's' || sb.charAt(n + 1) == 'S') && (n + 2 == sb.length() || sb.charAt(n + 2) == ' ')) {
                                            sb.insert(n, ' ');
                                            n += 2;
                                        }
                                        else {
                                            sb.setCharAt(n, ' ');
                                        }
                                        b = true;
                                        n2 = n;
                                    }
                                    n = n2 + 1;
                                }
                                String string;
                                if (b) {
                                    string = sb.toString();
                                }
                                else {
                                    string = null;
                                }
                                if (string == null) {
                                    break Label_0565;
                                }
                                this.zzajk = string;
                                final String[] zzb = zzgu.zzb(string, true);
                                if (zzb.length >= this.zzajn) {
                                    int k = 0;
                                Label_0460:
                                    while (k < zzb.length) {
                                        String concat = "";
                                        int l = 0;
                                        while (true) {
                                            while (l < this.zzajn) {
                                                if (k + l >= zzb.length) {
                                                    final int n3 = 0;
                                                    if (n3 == 0) {
                                                        break Label_0460;
                                                    }
                                                    set.add(concat);
                                                    if (set.size() >= this.zzaiz) {
                                                        final int n4 = 0;
                                                        break Label_0354;
                                                    }
                                                    ++k;
                                                    continue Label_0460;
                                                }
                                                else {
                                                    String concat2 = concat;
                                                    if (l > 0) {
                                                        concat2 = String.valueOf(concat).concat(" ");
                                                    }
                                                    final String value = String.valueOf(concat2);
                                                    final String value2 = String.valueOf(zzb[k + l]);
                                                    if (value2.length() != 0) {
                                                        concat = value.concat(value2);
                                                    }
                                                    else {
                                                        concat = new String(value);
                                                    }
                                                    ++l;
                                                }
                                            }
                                            final int n3 = 1;
                                            continue Label_0354_Outer;
                                        }
                                    }
                                    if (set.size() >= this.zzaiz) {
                                        final int n4 = 0;
                                        break Label_0354;
                                    }
                                }
                                ++j;
                                continue Label_0271_Outer;
                            }
                            String string = s;
                            continue Label_0354_Outer;
                        }
                    }
                }
                Label_0486: {
                    break Label_0486;
                    final int n4;
                    if (n4 != 0) {
                        ++i;
                        continue Label_0354_Outer;
                    }
                    break;
                }
                final int n4 = 1;
                continue Label_0354;
            }
        }
        zzgt = new zzgt();
        this.zzajj = "";
        for (final String s2 : set) {
            try {
                zzgt.write(this.zzajb.zzx(s2));
                continue;
            }
            catch (IOException ex) {
                zzakb.zzb("Error while writing hash to byteStream", (Throwable)ex);
            }
            break;
        }
        return zzgt.toString();
    }
}
