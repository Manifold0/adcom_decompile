// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zzbea
{
    static String zzaq(final zzbah zzbah) {
        final zzbeb zzbeb = new zzbeb(zzbah);
        final StringBuilder sb = new StringBuilder(zzbeb.size());
        for (int i = 0; i < zzbeb.size(); ++i) {
            final byte zzbn = zzbeb.zzbn(i);
            switch (zzbn) {
                default: {
                    if (zzbn >= 32 && zzbn <= 126) {
                        sb.append((char)zzbn);
                        break;
                    }
                    sb.append('\\');
                    sb.append((char)((zzbn >>> 6 & 0x3) + 48));
                    sb.append((char)((zzbn >>> 3 & 0x7) + 48));
                    sb.append((char)((zzbn & 0x7) + 48));
                    break;
                }
                case 7: {
                    sb.append("\\a");
                    break;
                }
                case 8: {
                    sb.append("\\b");
                    break;
                }
                case 12: {
                    sb.append("\\f");
                    break;
                }
                case 10: {
                    sb.append("\\n");
                    break;
                }
                case 13: {
                    sb.append("\\r");
                    break;
                }
                case 9: {
                    sb.append("\\t");
                    break;
                }
                case 11: {
                    sb.append("\\v");
                    break;
                }
                case 92: {
                    sb.append("\\\\");
                    break;
                }
                case 39: {
                    sb.append("\\'");
                    break;
                }
                case 34: {
                    sb.append("\\\"");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
