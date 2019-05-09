// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.audio;

import java.util.Arrays;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.common.internal.Preconditions;

public final class AudioBytes
{
    public static final int MAX_SIZE = 10;
    private final byte[] zzgd;
    
    public AudioBytes(final byte[] zzgd) {
        final boolean b = true;
        Preconditions.checkNotNull((Object)zzgd);
        Preconditions.checkArgument(zzgd.length <= 10, (Object)"Given byte array longer than 10 bytes, given by AudioBytes.MAX_SIZE.");
        Preconditions.checkArgument(zzgd.length > 0 && b, (Object)"Given byte array is of zero length.");
        this.zzgd = zzgd;
    }
    
    public static AudioBytes from(final Message message) {
        Preconditions.checkNotNull((Object)message);
        final boolean zzl = message.zzl("__audio_bytes");
        final String type = message.getType();
        Preconditions.checkArgument(zzl, (Object)new StringBuilder(String.valueOf(type).length() + 56).append("Message type '").append(type).append("' is not Message.MESSAGE_TYPE_AUDIO_BYTES.").toString());
        return new AudioBytes(message.getContent());
    }
    
    public final byte[] getBytes() {
        return this.zzgd;
    }
    
    public final Message toMessage() {
        return new Message(this.zzgd, "__reserved_namespace", "__audio_bytes");
    }
    
    @Override
    public final String toString() {
        final String string = Arrays.toString(this.zzgd);
        return new StringBuilder(String.valueOf(string).length() + 14).append("AudioBytes [").append(string).append(" ]").toString();
    }
}
