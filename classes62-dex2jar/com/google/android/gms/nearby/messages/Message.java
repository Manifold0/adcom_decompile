// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import java.util.Arrays;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.internal.nearby.zzgs;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "MessageCreator")
public class Message extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<Message> CREATOR;
    public static final int MAX_CONTENT_SIZE_BYTES = 102400;
    public static final int MAX_TYPE_LENGTH = 32;
    public static final String MESSAGE_NAMESPACE_RESERVED = "__reserved_namespace";
    public static final String MESSAGE_TYPE_AUDIO_BYTES = "__audio_bytes";
    public static final String MESSAGE_TYPE_EDDYSTONE_UID = "__eddystone_uid";
    public static final String MESSAGE_TYPE_I_BEACON_ID = "__i_beacon_id";
    private static final zzgs[] zzeu;
    @SafeParcelable$Field(getter = "getContent", id = 1)
    private final byte[] content;
    @SafeParcelable$Field(getter = "getNamespace", id = 3)
    private final String namespace;
    @SafeParcelable$Field(getter = "getType", id = 2)
    private final String type;
    @SafeParcelable$VersionField(id = 1000)
    private final int versionCode;
    @Deprecated
    @SafeParcelable$Field(id = 4)
    private final zzgs[] zzev;
    @SafeParcelable$Field(getter = "getProjectId", id = 5)
    private final long zzew;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
        zzeu = new zzgs[] { zzgs.zzgv };
    }
    
    @SafeParcelable$Constructor
    Message(@SafeParcelable$Param(id = 1000) final int versionCode, @Nullable @SafeParcelable$Param(id = 1) final byte[] content, @Nullable @SafeParcelable$Param(id = 3) final String s, @SafeParcelable$Param(id = 2) final String s2, @Nullable @SafeParcelable$Param(id = 4) final zzgs[] array, @SafeParcelable$Param(id = 5) final long zzew) {
        this.versionCode = versionCode;
        this.type = (String)Preconditions.checkNotNull((Object)s2);
        String namespace = s;
        if (s == null) {
            namespace = "";
        }
        this.namespace = namespace;
        this.zzew = zzew;
        Preconditions.checkNotNull((Object)content);
        Preconditions.checkArgument(content.length <= 102400, "Content length(%d) must not exceed MAX_CONTENT_SIZE_BYTES(%d)", new Object[] { content.length, 102400 });
        this.content = content;
        zzgs[] zzeu = null;
        Label_0109: {
            if (array != null) {
                zzeu = array;
                if (array.length != 0) {
                    break Label_0109;
                }
            }
            zzeu = Message.zzeu;
        }
        this.zzev = zzeu;
        Preconditions.checkArgument(s2.length() <= 32, "Type length(%d) must not exceed MAX_TYPE_LENGTH(%d)", new Object[] { s2.length(), 32 });
    }
    
    public Message(final byte[] array) {
        this(array, "", "");
    }
    
    public Message(final byte[] array, final String s) {
        this(array, "", s);
    }
    
    public Message(final byte[] array, final String s, final String s2) {
        this(array, s, s2, Message.zzeu);
    }
    
    private Message(final byte[] array, final String s, final String s2, final zzgs[] array2) {
        this(array, s, s2, array2, 0L);
    }
    
    private Message(final byte[] array, final String s, final String s2, final zzgs[] array2, final long n) {
        this(2, array, s, s2, array2, 0L);
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof Message)) {
                return false;
            }
            final Message message = (Message)o;
            if (!TextUtils.equals((CharSequence)this.namespace, (CharSequence)message.namespace) || !TextUtils.equals((CharSequence)this.type, (CharSequence)message.type) || !Arrays.equals(this.content, message.content) || this.zzew != message.zzew) {
                return false;
            }
        }
        return true;
    }
    
    public byte[] getContent() {
        return this.content;
    }
    
    public String getNamespace() {
        return this.namespace;
    }
    
    public String getType() {
        return this.type;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.namespace, this.type, Arrays.hashCode(this.content), this.zzew });
    }
    
    public String toString() {
        final String namespace = this.namespace;
        final String type = this.type;
        int length;
        if (this.content == null) {
            length = 0;
        }
        else {
            length = this.content.length;
        }
        return new StringBuilder(String.valueOf(namespace).length() + 59 + String.valueOf(type).length()).append("Message{namespace='").append(namespace).append("', type='").append(type).append("', content=[").append(length).append(" bytes]}").toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByteArray(parcel, 1, this.getContent(), false);
        SafeParcelWriter.writeString(parcel, 2, this.getType(), false);
        SafeParcelWriter.writeString(parcel, 3, this.getNamespace(), false);
        SafeParcelWriter.writeTypedArray(parcel, 4, (Parcelable[])this.zzev, n, false);
        SafeParcelWriter.writeLong(parcel, 5, this.zzew);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final boolean zzl(final String s) {
        return "__reserved_namespace".equals(this.getNamespace()) && s.equals(this.getType());
    }
}
