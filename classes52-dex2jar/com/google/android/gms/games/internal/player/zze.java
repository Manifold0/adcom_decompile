// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal.player;

import android.text.TextUtils;

public final class zze
{
    public final String name;
    public final String zzcc;
    public final String zzch;
    public final String zzll;
    public final String zzlm;
    public final String zzln;
    public final String zzlo;
    public final String zzlp;
    public final String zzlq;
    public final String zzlr;
    public final String zzls;
    public final String zzlt;
    public final String zzlu;
    public final String zzlv;
    public final String zzlw;
    public final String zzlx;
    public final String zzly;
    public final String zzlz;
    public final String zzma;
    private final String zzmb;
    public final String zzmc;
    public final String zzmd;
    public final String zzme;
    public final String zzmf;
    public final String zzmg;
    public final String zzmh;
    public final String zzmi;
    public final String zzmj;
    public final String zzmk;
    public final String zzml;
    public final String zzmm;
    public final String zzmn;
    public final String zzmo;
    public final String zzmp;
    public final String zzmq;
    
    public zze(String zzmq) {
        if (TextUtils.isEmpty((CharSequence)zzmq)) {
            this.zzll = "external_player_id";
            this.zzlm = "profile_name";
            this.zzln = "profile_icon_image_uri";
            this.zzlo = "profile_icon_image_url";
            this.zzlp = "profile_hi_res_image_uri";
            this.zzlq = "profile_hi_res_image_url";
            this.zzlr = "last_updated";
            this.zzls = "is_in_circles";
            this.zzlt = "played_with_timestamp";
            this.zzlu = "current_xp_total";
            this.zzlv = "current_level";
            this.zzlw = "current_level_min_xp";
            this.zzlx = "current_level_max_xp";
            this.zzly = "next_level";
            this.zzlz = "next_level_max_xp";
            this.zzma = "last_level_up_timestamp";
            this.zzcc = "player_title";
            this.zzmb = "has_all_public_acls";
            this.zzmc = "is_profile_visible";
            this.zzmd = "most_recent_external_game_id";
            this.zzme = "most_recent_game_name";
            this.zzmf = "most_recent_activity_timestamp";
            this.zzmg = "most_recent_game_icon_uri";
            this.zzmh = "most_recent_game_hi_res_uri";
            this.zzmi = "most_recent_game_featured_uri";
            this.zzmj = "has_debug_access";
            this.zzch = "gamer_tag";
            this.name = "real_name";
            this.zzmk = "banner_image_landscape_uri";
            this.zzml = "banner_image_landscape_url";
            this.zzmm = "banner_image_portrait_uri";
            this.zzmn = "banner_image_portrait_url";
            this.zzmo = "gamer_friend_status";
            this.zzmp = "gamer_friend_update_timestamp";
            zzmq = "is_muted";
        }
        else {
            final String value = String.valueOf(zzmq);
            final String value2 = String.valueOf("external_player_id");
            String concat;
            if (value2.length() != 0) {
                concat = value.concat(value2);
            }
            else {
                concat = new String(value);
            }
            this.zzll = concat;
            final String value3 = String.valueOf(zzmq);
            final String value4 = String.valueOf("profile_name");
            String concat2;
            if (value4.length() != 0) {
                concat2 = value3.concat(value4);
            }
            else {
                concat2 = new String(value3);
            }
            this.zzlm = concat2;
            final String value5 = String.valueOf(zzmq);
            final String value6 = String.valueOf("profile_icon_image_uri");
            String concat3;
            if (value6.length() != 0) {
                concat3 = value5.concat(value6);
            }
            else {
                concat3 = new String(value5);
            }
            this.zzln = concat3;
            final String value7 = String.valueOf(zzmq);
            final String value8 = String.valueOf("profile_icon_image_url");
            String concat4;
            if (value8.length() != 0) {
                concat4 = value7.concat(value8);
            }
            else {
                concat4 = new String(value7);
            }
            this.zzlo = concat4;
            final String value9 = String.valueOf(zzmq);
            final String value10 = String.valueOf("profile_hi_res_image_uri");
            String concat5;
            if (value10.length() != 0) {
                concat5 = value9.concat(value10);
            }
            else {
                concat5 = new String(value9);
            }
            this.zzlp = concat5;
            final String value11 = String.valueOf(zzmq);
            final String value12 = String.valueOf("profile_hi_res_image_url");
            String concat6;
            if (value12.length() != 0) {
                concat6 = value11.concat(value12);
            }
            else {
                concat6 = new String(value11);
            }
            this.zzlq = concat6;
            final String value13 = String.valueOf(zzmq);
            final String value14 = String.valueOf("last_updated");
            String concat7;
            if (value14.length() != 0) {
                concat7 = value13.concat(value14);
            }
            else {
                concat7 = new String(value13);
            }
            this.zzlr = concat7;
            final String value15 = String.valueOf(zzmq);
            final String value16 = String.valueOf("is_in_circles");
            String concat8;
            if (value16.length() != 0) {
                concat8 = value15.concat(value16);
            }
            else {
                concat8 = new String(value15);
            }
            this.zzls = concat8;
            final String value17 = String.valueOf(zzmq);
            final String value18 = String.valueOf("played_with_timestamp");
            String concat9;
            if (value18.length() != 0) {
                concat9 = value17.concat(value18);
            }
            else {
                concat9 = new String(value17);
            }
            this.zzlt = concat9;
            final String value19 = String.valueOf(zzmq);
            final String value20 = String.valueOf("current_xp_total");
            String concat10;
            if (value20.length() != 0) {
                concat10 = value19.concat(value20);
            }
            else {
                concat10 = new String(value19);
            }
            this.zzlu = concat10;
            final String value21 = String.valueOf(zzmq);
            final String value22 = String.valueOf("current_level");
            String concat11;
            if (value22.length() != 0) {
                concat11 = value21.concat(value22);
            }
            else {
                concat11 = new String(value21);
            }
            this.zzlv = concat11;
            final String value23 = String.valueOf(zzmq);
            final String value24 = String.valueOf("current_level_min_xp");
            String concat12;
            if (value24.length() != 0) {
                concat12 = value23.concat(value24);
            }
            else {
                concat12 = new String(value23);
            }
            this.zzlw = concat12;
            final String value25 = String.valueOf(zzmq);
            final String value26 = String.valueOf("current_level_max_xp");
            String concat13;
            if (value26.length() != 0) {
                concat13 = value25.concat(value26);
            }
            else {
                concat13 = new String(value25);
            }
            this.zzlx = concat13;
            final String value27 = String.valueOf(zzmq);
            final String value28 = String.valueOf("next_level");
            String concat14;
            if (value28.length() != 0) {
                concat14 = value27.concat(value28);
            }
            else {
                concat14 = new String(value27);
            }
            this.zzly = concat14;
            final String value29 = String.valueOf(zzmq);
            final String value30 = String.valueOf("next_level_max_xp");
            String concat15;
            if (value30.length() != 0) {
                concat15 = value29.concat(value30);
            }
            else {
                concat15 = new String(value29);
            }
            this.zzlz = concat15;
            final String value31 = String.valueOf(zzmq);
            final String value32 = String.valueOf("last_level_up_timestamp");
            String concat16;
            if (value32.length() != 0) {
                concat16 = value31.concat(value32);
            }
            else {
                concat16 = new String(value31);
            }
            this.zzma = concat16;
            final String value33 = String.valueOf(zzmq);
            final String value34 = String.valueOf("player_title");
            String concat17;
            if (value34.length() != 0) {
                concat17 = value33.concat(value34);
            }
            else {
                concat17 = new String(value33);
            }
            this.zzcc = concat17;
            final String value35 = String.valueOf(zzmq);
            final String value36 = String.valueOf("has_all_public_acls");
            String concat18;
            if (value36.length() != 0) {
                concat18 = value35.concat(value36);
            }
            else {
                concat18 = new String(value35);
            }
            this.zzmb = concat18;
            final String value37 = String.valueOf(zzmq);
            final String value38 = String.valueOf("is_profile_visible");
            String concat19;
            if (value38.length() != 0) {
                concat19 = value37.concat(value38);
            }
            else {
                concat19 = new String(value37);
            }
            this.zzmc = concat19;
            final String value39 = String.valueOf(zzmq);
            final String value40 = String.valueOf("most_recent_external_game_id");
            String concat20;
            if (value40.length() != 0) {
                concat20 = value39.concat(value40);
            }
            else {
                concat20 = new String(value39);
            }
            this.zzmd = concat20;
            final String value41 = String.valueOf(zzmq);
            final String value42 = String.valueOf("most_recent_game_name");
            String concat21;
            if (value42.length() != 0) {
                concat21 = value41.concat(value42);
            }
            else {
                concat21 = new String(value41);
            }
            this.zzme = concat21;
            final String value43 = String.valueOf(zzmq);
            final String value44 = String.valueOf("most_recent_activity_timestamp");
            String concat22;
            if (value44.length() != 0) {
                concat22 = value43.concat(value44);
            }
            else {
                concat22 = new String(value43);
            }
            this.zzmf = concat22;
            final String value45 = String.valueOf(zzmq);
            final String value46 = String.valueOf("most_recent_game_icon_uri");
            String concat23;
            if (value46.length() != 0) {
                concat23 = value45.concat(value46);
            }
            else {
                concat23 = new String(value45);
            }
            this.zzmg = concat23;
            final String value47 = String.valueOf(zzmq);
            final String value48 = String.valueOf("most_recent_game_hi_res_uri");
            String concat24;
            if (value48.length() != 0) {
                concat24 = value47.concat(value48);
            }
            else {
                concat24 = new String(value47);
            }
            this.zzmh = concat24;
            final String value49 = String.valueOf(zzmq);
            final String value50 = String.valueOf("most_recent_game_featured_uri");
            String concat25;
            if (value50.length() != 0) {
                concat25 = value49.concat(value50);
            }
            else {
                concat25 = new String(value49);
            }
            this.zzmi = concat25;
            final String value51 = String.valueOf(zzmq);
            final String value52 = String.valueOf("has_debug_access");
            String concat26;
            if (value52.length() != 0) {
                concat26 = value51.concat(value52);
            }
            else {
                concat26 = new String(value51);
            }
            this.zzmj = concat26;
            final String value53 = String.valueOf(zzmq);
            final String value54 = String.valueOf("gamer_tag");
            String concat27;
            if (value54.length() != 0) {
                concat27 = value53.concat(value54);
            }
            else {
                concat27 = new String(value53);
            }
            this.zzch = concat27;
            final String value55 = String.valueOf(zzmq);
            final String value56 = String.valueOf("real_name");
            String concat28;
            if (value56.length() != 0) {
                concat28 = value55.concat(value56);
            }
            else {
                concat28 = new String(value55);
            }
            this.name = concat28;
            final String value57 = String.valueOf(zzmq);
            final String value58 = String.valueOf("banner_image_landscape_uri");
            String concat29;
            if (value58.length() != 0) {
                concat29 = value57.concat(value58);
            }
            else {
                concat29 = new String(value57);
            }
            this.zzmk = concat29;
            final String value59 = String.valueOf(zzmq);
            final String value60 = String.valueOf("banner_image_landscape_url");
            String concat30;
            if (value60.length() != 0) {
                concat30 = value59.concat(value60);
            }
            else {
                concat30 = new String(value59);
            }
            this.zzml = concat30;
            final String value61 = String.valueOf(zzmq);
            final String value62 = String.valueOf("banner_image_portrait_uri");
            String concat31;
            if (value62.length() != 0) {
                concat31 = value61.concat(value62);
            }
            else {
                concat31 = new String(value61);
            }
            this.zzmm = concat31;
            final String value63 = String.valueOf(zzmq);
            final String value64 = String.valueOf("banner_image_portrait_url");
            String concat32;
            if (value64.length() != 0) {
                concat32 = value63.concat(value64);
            }
            else {
                concat32 = new String(value63);
            }
            this.zzmn = concat32;
            final String value65 = String.valueOf(zzmq);
            final String value66 = String.valueOf("gamer_friend_status");
            String concat33;
            if (value66.length() != 0) {
                concat33 = value65.concat(value66);
            }
            else {
                concat33 = new String(value65);
            }
            this.zzmo = concat33;
            final String value67 = String.valueOf(zzmq);
            final String value68 = String.valueOf("gamer_friend_update_timestamp");
            String concat34;
            if (value68.length() != 0) {
                concat34 = value67.concat(value68);
            }
            else {
                concat34 = new String(value67);
            }
            this.zzmp = concat34;
            zzmq = String.valueOf(zzmq);
            final String value69 = String.valueOf("is_muted");
            if (value69.length() != 0) {
                zzmq = zzmq.concat(value69);
            }
            else {
                zzmq = new String(zzmq);
            }
        }
        this.zzmq = zzmq;
    }
}
