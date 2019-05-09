// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.CommonStatusCodes;

public final class GamesClientStatusCodes extends CommonStatusCodes
{
    public static final int ACHIEVEMENT_NOT_INCREMENTAL = 26562;
    public static final int ACHIEVEMENT_UNKNOWN = 26561;
    public static final int ACHIEVEMENT_UNLOCKED = 26563;
    public static final int ACHIEVEMENT_UNLOCK_FAILURE = 26560;
    public static final int APP_MISCONFIGURED = 26508;
    public static final int GAME_NOT_FOUND = 26509;
    public static final int INVALID_REAL_TIME_ROOM_ID = 26602;
    public static final int LICENSE_CHECK_FAILED = 26507;
    public static final int MATCH_ERROR_ALREADY_REMATCHED = 26595;
    public static final int MATCH_ERROR_INACTIVE_MATCH = 26591;
    public static final int MATCH_ERROR_INVALID_MATCH_RESULTS = 26594;
    public static final int MATCH_ERROR_INVALID_MATCH_STATE = 26592;
    public static final int MATCH_ERROR_INVALID_PARTICIPANT_STATE = 26590;
    public static final int MATCH_ERROR_LOCALLY_MODIFIED = 26597;
    public static final int MATCH_ERROR_OUT_OF_DATE_VERSION = 26593;
    public static final int MATCH_NOT_FOUND = 26596;
    public static final int MULTIPLAYER_DISABLED = 26583;
    public static final int MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED = 26580;
    public static final int MULTIPLAYER_ERROR_INVALID_MULTIPLAYER_TYPE = 26582;
    public static final int MULTIPLAYER_ERROR_INVALID_OPERATION = 26584;
    public static final int MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER = 26581;
    public static final int NETWORK_ERROR_NO_DATA = 26504;
    public static final int NETWORK_ERROR_OPERATION_FAILED = 26506;
    public static final int OPERATION_IN_FLIGHT = 26607;
    public static final int PARTICIPANT_NOT_CONNECTED = 26603;
    public static final int REAL_TIME_CONNECTION_FAILED = 26600;
    public static final int REAL_TIME_INACTIVE_ROOM = 26605;
    public static final int REAL_TIME_MESSAGE_SEND_FAILED = 26601;
    public static final int REAL_TIME_ROOM_NOT_JOINED = 26604;
    public static final int SNAPSHOT_COMMIT_FAILED = 26573;
    public static final int SNAPSHOT_CONFLICT_MISSING = 26576;
    public static final int SNAPSHOT_CONTENTS_UNAVAILABLE = 26572;
    public static final int SNAPSHOT_CREATION_FAILED = 26571;
    public static final int SNAPSHOT_FOLDER_UNAVAILABLE = 26575;
    public static final int SNAPSHOT_NOT_FOUND = 26570;
    public static final int VIDEO_ALREADY_CAPTURING = 26625;
    public static final int VIDEO_NOT_ACTIVE = 26620;
    public static final int VIDEO_OUT_OF_DISK_SPACE = 26626;
    public static final int VIDEO_PERMISSION_ERROR = 26622;
    public static final int VIDEO_STORAGE_ERROR = 26623;
    public static final int VIDEO_UNEXPECTED_CAPTURE_ERROR = 26624;
    public static final int VIDEO_UNSUPPORTED = 26621;
    
    private GamesClientStatusCodes() {
    }
    
    public static String getStatusCodeString(final int n) {
        switch (n) {
            default: {
                return CommonStatusCodes.getStatusCodeString(n);
            }
            case 26502: {
                return "CLIENT_RECONNECT_REQUIRED";
            }
            case 26503: {
                return "NETWORK_ERROR_STALE_DATA";
            }
            case 26504: {
                return "NETWORK_ERROR_NO_DATA";
            }
            case 26505: {
                return "NETWORK_ERROR_OPERATION_DEFERRED";
            }
            case 26506: {
                return "NETWORK_ERROR_OPERATION_FAILED";
            }
            case 26507: {
                return "LICENSE_CHECK_FAILED";
            }
            case 26508: {
                return "APP_MISCONFIGURED";
            }
            case 26509: {
                return "GAME_NOT_FOUND";
            }
            case 26520: {
                return "RESOLVE_STALE_OR_NO_DATA";
            }
            case 26530: {
                return "AUTH_ERROR_HARD";
            }
            case 26531: {
                return "AUTH_ERROR_USER_RECOVERABLE";
            }
            case 26532: {
                return "AUTH_ERROR_UNREGISTERED_CLIENT_ID";
            }
            case 26533: {
                return "AUTH_ERROR_API_ACCESS_DENIED";
            }
            case 26534: {
                return "AUTH_ERROR_ACCOUNT_NOT_USABLE";
            }
            case 26540: {
                return "PLAYER_OOB_REQUIRED";
            }
            case 26541: {
                return "PLAYER_LEVEL_UP";
            }
            case 26550: {
                return "REQUEST_UPDATE_PARTIAL_SUCCESS";
            }
            case 26551: {
                return "REQUEST_UPDATE_TOTAL_FAILURE";
            }
            case 26552: {
                return "REQUEST_TOO_MANY_RECIPIENTS";
            }
            case 26560: {
                return "ACHIEVEMENT_UNLOCK_FAILURE";
            }
            case 26561: {
                return "ACHIEVEMENT_UNKNOWN";
            }
            case 26562: {
                return "ACHIEVEMENT_NOT_INCREMENTAL";
            }
            case 26563: {
                return "ACHIEVEMENT_UNLOCKED";
            }
            case 26570: {
                return "SNAPSHOT_NOT_FOUND";
            }
            case 26571: {
                return "SNAPSHOT_CREATION_FAILED";
            }
            case 26572: {
                return "SNAPSHOT_CONTENTS_UNAVAILABLE";
            }
            case 26573: {
                return "SNAPSHOT_COMMIT_FAILED";
            }
            case 26574: {
                return "SNAPSHOT_CONFLICT";
            }
            case 26576: {
                return "SNAPSHOT_CONFLICT_MISSING";
            }
            case 26575: {
                return "SNAPSHOT_FOLDER_UNAVAILABLE";
            }
            case 26580: {
                return "MULTIPLAYER_ERROR_CREATION_NOT_ALLOWED";
            }
            case 26581: {
                return "MULTIPLAYER_ERROR_NOT_TRUSTED_TESTER";
            }
            case 26582: {
                return "MULTIPLAYER_ERROR_INVALID_MULTIPLAYER_TYPE";
            }
            case 26583: {
                return "MULTIPLAYER_DISABLED";
            }
            case 26584: {
                return "MULTIPLAYER_ERROR_INVALID_OPERATION";
            }
            case 26590: {
                return "MATCH_ERROR_INVALID_PARTICIPANT_STATE";
            }
            case 26591: {
                return "MATCH_ERROR_INACTIVE_MATCH";
            }
            case 26592: {
                return "MATCH_ERROR_INVALID_MATCH_STATE";
            }
            case 26593: {
                return "MATCH_ERROR_OUT_OF_DATE_VERSION";
            }
            case 26594: {
                return "MATCH_ERROR_INVALID_MATCH_RESULTS";
            }
            case 26595: {
                return "MATCH_ERROR_ALREADY_REMATCHED";
            }
            case 26596: {
                return "MATCH_NOT_FOUND";
            }
            case 26597: {
                return "MATCH_ERROR_LOCALLY_MODIFIED";
            }
            case 26600: {
                return "REAL_TIME_CONNECTION_FAILED";
            }
            case 26601: {
                return "REAL_TIME_MESSAGE_SEND_FAILED";
            }
            case 26602: {
                return "INVALID_REAL_TIME_ROOM_ID";
            }
            case 26603: {
                return "PARTICIPANT_NOT_CONNECTED";
            }
            case 26604: {
                return "REAL_TIME_ROOM_NOT_JOINED";
            }
            case 26605: {
                return "REAL_TIME_INACTIVE_ROOM";
            }
            case 26606: {
                return "REAL_TIME_SERVICE_NOT_CONNECTED";
            }
            case 26607: {
                return "OPERATION_IN_FLIGHT";
            }
            case 26610: {
                return "MILESTONE_CLAIMED_PREVIOUSLY";
            }
            case 26611: {
                return "MILESTONE_CLAIM_FAILED";
            }
            case 26612: {
                return "QUEST_NO_LONGER_AVAILABLE";
            }
            case 26613: {
                return "QUEST_NOT_STARTED";
            }
            case 26620: {
                return "VIDEO_NOT_ACTIVE";
            }
            case 26621: {
                return "VIDEO_UNSUPPORTED";
            }
            case 26622: {
                return "VIDEO_PERMISSION_ERROR";
            }
            case 26623: {
                return "VIDEO_STORAGE_ERROR";
            }
            case 26624: {
                return "VIDEO_UNEXPECTED_CAPTURE_ERROR";
            }
            case 26625: {
                return "VIDEO_ALREADY_CAPTURING";
            }
            case 26626: {
                return "VIDEO_OUT_OF_DISK_SPACE";
            }
            case 26627: {
                return "VIDEO_NO_MIC";
            }
            case 26628: {
                return "VIDEO_NO_CAMERA";
            }
            case 26629: {
                return "VIDEO_SCREEN_OFF";
            }
            case 26630: {
                return "VIDEO_RELEASE_TIMEOUT";
            }
            case 26650: {
                return "VIDEO_MISSING_OVERLAY_PERMISSION";
            }
            case 26631: {
                return "VIDEO_CAPTURE_VIDEO_PERMISSION_REQUIRED";
            }
            case 26632: {
                return "CAPTURE_ALREADY_PAUSED";
            }
            case 26652: {
                return "VIDEO_CAPTURE_OVERLAY_VISIBLE";
            }
            case 26700: {
                return "CLIENT_LOADING";
            }
            case 26701: {
                return "CLIENT_EMPTY";
            }
            case 26702: {
                return "CLIENT_HIDDEN";
            }
        }
    }
    
    public static Status zza(final int n) {
        return new Status(n, getStatusCodeString(n));
    }
    
    public static int zzb(final int n) {
        switch (n) {
            default: {
                return n;
            }
            case 1: {
                return 8;
            }
            case 2: {
                return 26502;
            }
            case 3: {
                return 26503;
            }
            case 4: {
                return 26504;
            }
            case 5: {
                return 26505;
            }
            case 6: {
                return 26506;
            }
            case 7: {
                return 26507;
            }
            case 8: {
                return 26508;
            }
            case 9: {
                return 26509;
            }
            case 500: {
                return 26520;
            }
            case 1000: {
                return 26530;
            }
            case 1001: {
                return 26531;
            }
            case 1002: {
                return 26532;
            }
            case 1003: {
                return 26533;
            }
            case 1004: {
                return 26534;
            }
            case 1500: {
                return 26540;
            }
            case 1501: {
                return 26541;
            }
            case 2000: {
                return 26550;
            }
            case 2001: {
                return 26551;
            }
            case 2002: {
                return 26552;
            }
            case 3000: {
                return 26560;
            }
            case 3001: {
                return 26561;
            }
            case 3002: {
                return 26562;
            }
            case 3003: {
                return 26563;
            }
            case 4000: {
                return 26570;
            }
            case 4001: {
                return 26571;
            }
            case 4002: {
                return 26572;
            }
            case 4003: {
                return 26573;
            }
            case 4004: {
                return 26574;
            }
            case 4006: {
                return 26576;
            }
            case 4005: {
                return 26575;
            }
            case 6000: {
                return 26580;
            }
            case 6001: {
                return 26581;
            }
            case 6002: {
                return 26582;
            }
            case 6003: {
                return 26583;
            }
            case 6004: {
                return 26584;
            }
            case 6500: {
                return 26590;
            }
            case 6501: {
                return 26591;
            }
            case 6502: {
                return 26592;
            }
            case 6503: {
                return 26593;
            }
            case 6504: {
                return 26594;
            }
            case 6505: {
                return 26595;
            }
            case 6506: {
                return 26596;
            }
            case 6507: {
                return 26597;
            }
            case 7000: {
                return 26600;
            }
            case 7001: {
                return 26601;
            }
            case 7002: {
                return 26602;
            }
            case 7003: {
                return 26603;
            }
            case 7004: {
                return 26604;
            }
            case 7005: {
                return 26605;
            }
            case 7006: {
                return 26606;
            }
            case 7007: {
                return 26607;
            }
            case 8000: {
                return 26610;
            }
            case 8001: {
                return 26611;
            }
            case 8002: {
                return 26612;
            }
            case 8003: {
                return 26613;
            }
            case 9000: {
                return 26620;
            }
            case 9001: {
                return 26621;
            }
            case 9002: {
                return 26622;
            }
            case 9003: {
                return 26623;
            }
            case 9004: {
                return 26624;
            }
            case 9006: {
                return 26625;
            }
            case 9009: {
                return 26626;
            }
            case 9010: {
                return 26627;
            }
            case 9011: {
                return 26628;
            }
            case 9012: {
                return 26629;
            }
            case 9016: {
                return 26630;
            }
            case 9200: {
                return 26650;
            }
            case 9017: {
                return 26631;
            }
            case 9018: {
                return 26632;
            }
            case 9202: {
                return 26652;
            }
            case 10000: {
                return 26700;
            }
            case 10001: {
                return 26701;
            }
            case 10002: {
                return 26702;
            }
        }
    }
}
