package com.google.android.gms.games;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class GamesCallbackStatusCodes {
    @Deprecated
    public static final int CLIENT_RECONNECT_REQUIRED = 2;
    public static final int INTERNAL_ERROR = 1;
    public static final int MULTIPLAYER_DISABLED = 6003;
    public static final int OK = 0;
    public static final int REAL_TIME_CONNECTION_FAILED = 7000;
    public static final int REAL_TIME_MESSAGE_SEND_FAILED = 7001;
    public static final int REAL_TIME_ROOM_NOT_JOINED = 7004;

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnJoinedRoomStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnLeftRoomStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRealTimeMessageSentStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRoomConnectedStatusCodes {
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface OnRoomCreatedStatusCodes {
    }

    private GamesCallbackStatusCodes() {
    }

    public static String getStatusCodeString(int i) {
        switch (i) {
            case 0:
                return "OK";
            case 1:
                return "INTERNAL_ERROR";
            case 2:
                return "CLIENT_RECONNECT_REQUIRED";
            case 6003:
                return "MULTIPLAYER_DISABLED";
            case 7000:
                return "REAL_TIME_CONNECTION_FAILED";
            case 7001:
                return "REAL_TIME_MESSAGE_SEND_FAILED";
            case 7004:
                return "REAL_TIME_ROOM_NOT_JOINED";
            default:
                return "unknown games callback status code: " + i;
        }
    }
}
