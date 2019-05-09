// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import android.net.Uri;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.support.annotation.IntRange;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.content.Intent;
import android.util.Log;
import java.util.Iterator;
import android.support.v4.util.ArrayMap;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Map;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "RemoteMessageCreator")
@SafeParcelable$Reserved({ 1 })
public final class RemoteMessage extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<RemoteMessage> CREATOR;
    public static final int PRIORITY_HIGH = 1;
    public static final int PRIORITY_NORMAL = 2;
    public static final int PRIORITY_UNKNOWN = 0;
    @SafeParcelable$Field(id = 2)
    Bundle zzds;
    private Map<String, String> zzdt;
    private Notification zzdu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    public RemoteMessage(@SafeParcelable$Param(id = 2) final Bundle zzds) {
        this.zzds = zzds;
    }
    
    private static int zzm(final String s) {
        if ("high".equals(s)) {
            return 1;
        }
        if ("normal".equals(s)) {
            return 2;
        }
        return 0;
    }
    
    @Nullable
    public final String getCollapseKey() {
        return this.zzds.getString("collapse_key");
    }
    
    public final Map<String, String> getData() {
        if (this.zzdt == null) {
            final Bundle zzds = this.zzds;
            final ArrayMap zzdt = new ArrayMap();
            for (final String s : zzds.keySet()) {
                final Object value = zzds.get(s);
                if (value instanceof String) {
                    final String s2 = (String)value;
                    if (s.startsWith("google.") || s.startsWith("gcm.") || s.equals("from") || s.equals("message_type") || s.equals("collapse_key")) {
                        continue;
                    }
                    zzdt.put((Object)s, (Object)s2);
                }
            }
            this.zzdt = (Map<String, String>)zzdt;
        }
        return this.zzdt;
    }
    
    @Nullable
    public final String getFrom() {
        return this.zzds.getString("from");
    }
    
    @Nullable
    public final String getMessageId() {
        String s;
        if ((s = this.zzds.getString("google.message_id")) == null) {
            s = this.zzds.getString("message_id");
        }
        return s;
    }
    
    @Nullable
    public final String getMessageType() {
        return this.zzds.getString("message_type");
    }
    
    @Nullable
    public final Notification getNotification() {
        if (this.zzdu == null && zza.zzf(this.zzds)) {
            this.zzdu = new Notification(this.zzds, null);
        }
        return this.zzdu;
    }
    
    public final int getOriginalPriority() {
        String s;
        if ((s = this.zzds.getString("google.original_priority")) == null) {
            s = this.zzds.getString("google.priority");
        }
        return zzm(s);
    }
    
    public final int getPriority() {
        String s;
        if ((s = this.zzds.getString("google.delivered_priority")) == null) {
            if ("1".equals(this.zzds.getString("google.priority_reduced"))) {
                return 2;
            }
            s = this.zzds.getString("google.priority");
        }
        return zzm(s);
    }
    
    public final long getSentTime() {
        final Object value = this.zzds.get("google.sent_time");
        if (value instanceof Long) {
            return (long)value;
        }
        if (value instanceof String) {
            try {
                return Long.parseLong((String)value);
            }
            catch (NumberFormatException ex) {
                final String value2 = String.valueOf(value);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(value2).length() + 19).append("Invalid sent time: ").append(value2).toString());
            }
        }
        return 0L;
    }
    
    @Nullable
    public final String getTo() {
        return this.zzds.getString("google.to");
    }
    
    public final int getTtl() {
        final Object value = this.zzds.get("google.ttl");
        if (value instanceof Integer) {
            return (int)value;
        }
        if (value instanceof String) {
            try {
                return Integer.parseInt((String)value);
            }
            catch (NumberFormatException ex) {
                final String value2 = String.valueOf(value);
                Log.w("FirebaseMessaging", new StringBuilder(String.valueOf(value2).length() + 13).append("Invalid TTL: ").append(value2).toString());
            }
        }
        return 0;
    }
    
    @KeepForSdk
    public final Intent toIntent() {
        final Intent intent = new Intent();
        intent.putExtras(this.zzds);
        return intent;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBundle(parcel, 2, this.zzds, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static class Builder
    {
        private final Bundle zzds;
        private final Map<String, String> zzdt;
        
        public Builder(String s) {
            this.zzds = new Bundle();
            this.zzdt = (Map<String, String>)new ArrayMap();
            if (TextUtils.isEmpty((CharSequence)s)) {
                s = String.valueOf(s);
                if (s.length() != 0) {
                    s = "Invalid to: ".concat(s);
                }
                else {
                    s = new String("Invalid to: ");
                }
                throw new IllegalArgumentException(s);
            }
            this.zzds.putString("google.to", s);
        }
        
        public Builder addData(final String s, final String s2) {
            this.zzdt.put(s, s2);
            return this;
        }
        
        public RemoteMessage build() {
            final Bundle bundle = new Bundle();
            for (final Map.Entry<String, String> entry : this.zzdt.entrySet()) {
                bundle.putString((String)entry.getKey(), (String)entry.getValue());
            }
            bundle.putAll(this.zzds);
            this.zzds.remove("from");
            return new RemoteMessage(bundle);
        }
        
        public Builder clearData() {
            this.zzdt.clear();
            return this;
        }
        
        public Builder setCollapseKey(final String s) {
            this.zzds.putString("collapse_key", s);
            return this;
        }
        
        public Builder setData(final Map<String, String> map) {
            this.zzdt.clear();
            this.zzdt.putAll(map);
            return this;
        }
        
        public Builder setMessageId(final String s) {
            this.zzds.putString("google.message_id", s);
            return this;
        }
        
        public Builder setMessageType(final String s) {
            this.zzds.putString("message_type", s);
            return this;
        }
        
        public Builder setTtl(@IntRange(from = 0L, to = 86400L) final int n) {
            this.zzds.putString("google.ttl", String.valueOf(n));
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface MessagePriority {
    }
    
    public static class Notification
    {
        private final String tag;
        private final String zzdv;
        private final String zzdw;
        private final String[] zzdx;
        private final String zzdy;
        private final String zzdz;
        private final String[] zzea;
        private final String zzeb;
        private final String zzec;
        private final String zzed;
        private final String zzee;
        private final Uri zzef;
        
        private Notification(final Bundle bundle) {
            this.zzdv = zza.zza(bundle, "gcm.n.title");
            this.zzdw = zza.zzb(bundle, "gcm.n.title");
            this.zzdx = zze(bundle, "gcm.n.title");
            this.zzdy = zza.zza(bundle, "gcm.n.body");
            this.zzdz = zza.zzb(bundle, "gcm.n.body");
            this.zzea = zze(bundle, "gcm.n.body");
            this.zzeb = zza.zza(bundle, "gcm.n.icon");
            this.zzec = zza.zzi(bundle);
            this.tag = zza.zza(bundle, "gcm.n.tag");
            this.zzed = zza.zza(bundle, "gcm.n.color");
            this.zzee = zza.zza(bundle, "gcm.n.click_action");
            this.zzef = zza.zzg(bundle);
        }
        
        private static String[] zze(final Bundle bundle, final String s) {
            final Object[] zzc = zza.zzc(bundle, s);
            if (zzc == null) {
                return null;
            }
            final String[] array = new String[zzc.length];
            for (int i = 0; i < zzc.length; ++i) {
                array[i] = String.valueOf(zzc[i]);
            }
            return array;
        }
        
        @Nullable
        public String getBody() {
            return this.zzdy;
        }
        
        @Nullable
        public String[] getBodyLocalizationArgs() {
            return this.zzea;
        }
        
        @Nullable
        public String getBodyLocalizationKey() {
            return this.zzdz;
        }
        
        @Nullable
        public String getClickAction() {
            return this.zzee;
        }
        
        @Nullable
        public String getColor() {
            return this.zzed;
        }
        
        @Nullable
        public String getIcon() {
            return this.zzeb;
        }
        
        @Nullable
        public Uri getLink() {
            return this.zzef;
        }
        
        @Nullable
        public String getSound() {
            return this.zzec;
        }
        
        @Nullable
        public String getTag() {
            return this.tag;
        }
        
        @Nullable
        public String getTitle() {
            return this.zzdv;
        }
        
        @Nullable
        public String[] getTitleLocalizationArgs() {
            return this.zzdx;
        }
        
        @Nullable
        public String getTitleLocalizationKey() {
            return this.zzdw;
        }
    }
}
