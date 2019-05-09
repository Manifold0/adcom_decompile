package com.google.android.gms.nearby.messages.audio;

import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.messages.Message;
import java.util.Arrays;

public final class AudioBytes {
    public static final int MAX_SIZE = 10;
    private final byte[] zzgd;

    public AudioBytes(byte[] bArr) {
        boolean z = true;
        Preconditions.checkNotNull(bArr);
        Preconditions.checkArgument(bArr.length <= 10, "Given byte array longer than 10 bytes, given by AudioBytes.MAX_SIZE.");
        if (bArr.length <= 0) {
            z = false;
        }
        Preconditions.checkArgument(z, "Given byte array is of zero length.");
        this.zzgd = bArr;
    }

    public static AudioBytes from(Message message) {
        Preconditions.checkNotNull(message);
        boolean zzl = message.zzl(Message.MESSAGE_TYPE_AUDIO_BYTES);
        String type = message.getType();
        Preconditions.checkArgument(zzl, new StringBuilder(String.valueOf(type).length() + 56).append("Message type '").append(type).append("' is not Message.MESSAGE_TYPE_AUDIO_BYTES.").toString());
        return new AudioBytes(message.getContent());
    }

    public final byte[] getBytes() {
        return this.zzgd;
    }

    public final Message toMessage() {
        return new Message(this.zzgd, Message.MESSAGE_NAMESPACE_RESERVED, Message.MESSAGE_TYPE_AUDIO_BYTES);
    }

    public final String toString() {
        String arrays = Arrays.toString(this.zzgd);
        return new StringBuilder(String.valueOf(arrays).length() + 14).append("AudioBytes [").append(arrays).append(" ]").toString();
    }
}
