package com.google.firebase.auth.internal;

import android.text.TextUtils;
import com.google.android.gms.common.api.Status;
import com.google.firebase.FirebaseError;
import java.util.Arrays;
import java.util.List;

/* compiled from: com.google.firebase:firebase-auth@@21.1.0 */
public final class zzai {
    public static Status zza(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return new Status(FirebaseError.ERROR_INTERNAL_ERROR);
        }
        String[] split = str.split(":", 2);
        split[0] = split[0].trim();
        if (split.length > 1 && (str2 = split[1]) != null) {
            split[1] = str2.trim();
        }
        List asList = Arrays.asList(split);
        if (asList.size() > 1) {
            return zzb((String) asList.get(0), (String) asList.get(1));
        }
        return zzb((String) asList.get(0), (String) null);
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static com.google.android.gms.common.api.Status zzb(java.lang.String r3, java.lang.String r4) {
        /*
            int r0 = r3.hashCode()
            switch(r0) {
                case -2130504259: goto L_0x0396;
                case -2065866930: goto L_0x038b;
                case -2014808264: goto L_0x0380;
                case -2005236790: goto L_0x0375;
                case -2001169389: goto L_0x036b;
                case -1944433728: goto L_0x0360;
                case -1800638118: goto L_0x0355;
                case -1774756919: goto L_0x034a;
                case -1699246888: goto L_0x033f;
                case -1603818979: goto L_0x0333;
                case -1587614300: goto L_0x0327;
                case -1583894766: goto L_0x031b;
                case -1458751677: goto L_0x030f;
                case -1421414571: goto L_0x0303;
                case -1345867105: goto L_0x02f7;
                case -1340100504: goto L_0x02eb;
                case -1232010689: goto L_0x02df;
                case -1202691903: goto L_0x02d3;
                case -1112393964: goto L_0x02c8;
                case -1063710844: goto L_0x02bc;
                case -974503964: goto L_0x02b0;
                case -863830559: goto L_0x02a4;
                case -828507413: goto L_0x0299;
                case -749743758: goto L_0x028d;
                case -736207500: goto L_0x0281;
                case -646022241: goto L_0x0275;
                case -595928767: goto L_0x0269;
                case -505579581: goto L_0x025d;
                case -380728810: goto L_0x0251;
                case -333672188: goto L_0x0245;
                case -294485423: goto L_0x0239;
                case -217128228: goto L_0x022d;
                case -122667194: goto L_0x0221;
                case -75433118: goto L_0x0215;
                case -52772551: goto L_0x0209;
                case -40686718: goto L_0x01fd;
                case 15352275: goto L_0x01f1;
                case 210308040: goto L_0x01e5;
                case 269327773: goto L_0x01d9;
                case 278802867: goto L_0x01cd;
                case 408411681: goto L_0x01c1;
                case 423563023: goto L_0x01b5;
                case 429251986: goto L_0x01a9;
                case 483847807: goto L_0x019d;
                case 491979549: goto L_0x0191;
                case 492072102: goto L_0x0185;
                case 492515765: goto L_0x0179;
                case 530628231: goto L_0x016d;
                case 542728406: goto L_0x0161;
                case 582457886: goto L_0x0155;
                case 605031096: goto L_0x0149;
                case 745638750: goto L_0x013d;
                case 786916712: goto L_0x0131;
                case 799258561: goto L_0x0125;
                case 819646646: goto L_0x011a;
                case 844240628: goto L_0x010e;
                case 886186878: goto L_0x0102;
                case 895302372: goto L_0x00f6;
                case 922685102: goto L_0x00ea;
                case 989000548: goto L_0x00de;
                case 1034932393: goto L_0x00d3;
                case 1072360691: goto L_0x00c8;
                case 1094975491: goto L_0x00bc;
                case 1107081238: goto L_0x00b0;
                case 1113992697: goto L_0x00a4;
                case 1141576252: goto L_0x0098;
                case 1199811910: goto L_0x008c;
                case 1226505451: goto L_0x0080;
                case 1308491624: goto L_0x0074;
                case 1388786705: goto L_0x0069;
                case 1433767024: goto L_0x005e;
                case 1442968770: goto L_0x0052;
                case 1494923453: goto L_0x0046;
                case 1497901284: goto L_0x003a;
                case 1803454477: goto L_0x002e;
                case 1898790704: goto L_0x0022;
                case 2063209097: goto L_0x0016;
                case 2082564316: goto L_0x0009;
                default: goto L_0x0007;
            }
        L_0x0007:
            goto L_0x03a1
        L_0x0009:
            java.lang.String r0 = "UNSUPPORTED_TENANT_OPERATION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 49
            goto L_0x03a2
        L_0x0016:
            java.lang.String r0 = "EMAIL_CHANGE_NEEDS_VERIFICATION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 63
            goto L_0x03a2
        L_0x0022:
            java.lang.String r0 = "MISSING_SESSION_INFO"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 35
            goto L_0x03a2
        L_0x002e:
            java.lang.String r0 = "MISSING_CONTINUE_URI"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 44
            goto L_0x03a2
        L_0x003a:
            java.lang.String r0 = "TOO_MANY_ATTEMPTS_TRY_LATER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 21
            goto L_0x03a2
        L_0x0046:
            java.lang.String r0 = "INVALID_APP_CREDENTIAL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 19
            goto L_0x03a2
        L_0x0052:
            java.lang.String r0 = "INVALID_PHONE_NUMBER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 32
            goto L_0x03a2
        L_0x005e:
            java.lang.String r0 = "USER_DISABLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 5
            goto L_0x03a2
        L_0x0069:
            java.lang.String r0 = "INVALID_IDENTIFIER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 6
            goto L_0x03a2
        L_0x0074:
            java.lang.String r0 = "MISSING_RECAPTCHA_TOKEN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 69
            goto L_0x03a2
        L_0x0080:
            java.lang.String r0 = "FEDERATED_USER_ID_ALREADY_LINKED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 12
            goto L_0x03a2
        L_0x008c:
            java.lang.String r0 = "MISSING_CODE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 33
            goto L_0x03a2
        L_0x0098:
            java.lang.String r0 = "SESSION_EXPIRED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 38
            goto L_0x03a2
        L_0x00a4:
            java.lang.String r0 = "INVALID_RECAPTCHA_TOKEN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 70
            goto L_0x03a2
        L_0x00b0:
            java.lang.String r0 = "<<Network Error>>"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 15
            goto L_0x03a2
        L_0x00bc:
            java.lang.String r0 = "INVALID_PASSWORD"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 11
            goto L_0x03a2
        L_0x00c8:
            java.lang.String r0 = "INVALID_CUSTOM_TOKEN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 2
            goto L_0x03a2
        L_0x00d3:
            java.lang.String r0 = "INVALID_PENDING_TOKEN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 3
            goto L_0x03a2
        L_0x00de:
            java.lang.String r0 = "RESET_PASSWORD_EXCEED_LIMIT"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 22
            goto L_0x03a2
        L_0x00ea:
            java.lang.String r0 = "INVALID_MESSAGE_PAYLOAD"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 26
            goto L_0x03a2
        L_0x00f6:
            java.lang.String r0 = "MISSING_CLIENT_IDENTIFIER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 65
            goto L_0x03a2
        L_0x0102:
            java.lang.String r0 = "REQUIRES_SECOND_FACTOR_AUTH"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 53
            goto L_0x03a2
        L_0x010e:
            java.lang.String r0 = "WEB_CONTEXT_CANCELED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 48
            goto L_0x03a2
        L_0x011a:
            java.lang.String r0 = "CREDENTIAL_MISMATCH"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 1
            goto L_0x03a2
        L_0x0125:
            java.lang.String r0 = "INVALID_PROVIDER_ID"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 46
            goto L_0x03a2
        L_0x0131:
            java.lang.String r0 = "INVALID_VERIFICATION_PROOF"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 37
            goto L_0x03a2
        L_0x013d:
            java.lang.String r0 = "INVALID_MFA_PENDING_CREDENTIAL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 56
            goto L_0x03a2
        L_0x0149:
            java.lang.String r0 = "REJECTED_CREDENTIAL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 52
            goto L_0x03a2
        L_0x0155:
            java.lang.String r0 = "UNVERIFIED_EMAIL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 59
            goto L_0x03a2
        L_0x0161:
            java.lang.String r0 = "PASSWORD_LOGIN_DISABLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 18
            goto L_0x03a2
        L_0x016d:
            java.lang.String r0 = "MISSING_RECAPTCHA_VERSION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 74
            goto L_0x03a2
        L_0x0179:
            java.lang.String r0 = "MISSING_CLIENT_TYPE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 73
            goto L_0x03a2
        L_0x0185:
            java.lang.String r0 = "WEB_STORAGE_UNSUPPORTED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 43
            goto L_0x03a2
        L_0x0191:
            java.lang.String r0 = "INVALID_ID_TOKEN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 13
            goto L_0x03a2
        L_0x019d:
            java.lang.String r0 = "EMAIL_EXISTS"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 10
            goto L_0x03a2
        L_0x01a9:
            java.lang.String r0 = "UNSUPPORTED_PASSTHROUGH_OPERATION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 68
            goto L_0x03a2
        L_0x01b5:
            java.lang.String r0 = "MISSING_MFA_PENDING_CREDENTIAL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 54
            goto L_0x03a2
        L_0x01c1:
            java.lang.String r0 = "INVALID_DYNAMIC_LINK_DOMAIN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 51
            goto L_0x03a2
        L_0x01cd:
            java.lang.String r0 = "MISSING_PHONE_NUMBER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 31
            goto L_0x03a2
        L_0x01d9:
            java.lang.String r0 = "INVALID_SENDER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 27
            goto L_0x03a2
        L_0x01e5:
            java.lang.String r0 = "UNSUPPORTED_FIRST_FACTOR"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 62
            goto L_0x03a2
        L_0x01f1:
            java.lang.String r0 = "EMAIL_NOT_FOUND"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 8
            goto L_0x03a2
        L_0x01fd:
            java.lang.String r0 = "WEAK_PASSWORD"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 16
            goto L_0x03a2
        L_0x0209:
            java.lang.String r0 = "CAPTCHA_CHECK_FAILED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 77
            goto L_0x03a2
        L_0x0215:
            java.lang.String r0 = "USER_NOT_FOUND"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 9
            goto L_0x03a2
        L_0x0221:
            java.lang.String r0 = "MISSING_MFA_ENROLLMENT_ID"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 55
            goto L_0x03a2
        L_0x022d:
            java.lang.String r0 = "SECOND_FACTOR_LIMIT_EXCEEDED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 61
            goto L_0x03a2
        L_0x0239:
            java.lang.String r0 = "WEB_INTERNAL_ERROR"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 42
            goto L_0x03a2
        L_0x0245:
            java.lang.String r0 = "OPERATION_NOT_ALLOWED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 17
            goto L_0x03a2
        L_0x0251:
            java.lang.String r0 = "INVALID_RECAPTCHA_ACTION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 71
            goto L_0x03a2
        L_0x025d:
            java.lang.String r0 = "INVALID_REQ_TYPE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 76
            goto L_0x03a2
        L_0x0269:
            java.lang.String r0 = "TIMEOUT"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 14
            goto L_0x03a2
        L_0x0275:
            java.lang.String r0 = "CREDENTIAL_TOO_OLD_LOGIN_AGAIN"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 20
            goto L_0x03a2
        L_0x0281:
            java.lang.String r0 = "MISSING_PASSWORD"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 30
            goto L_0x03a2
        L_0x028d:
            java.lang.String r0 = "MFA_ENROLLMENT_NOT_FOUND"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 57
            goto L_0x03a2
        L_0x0299:
            java.lang.String r0 = "NO_SUCH_PROVIDER"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 0
            goto L_0x03a2
        L_0x02a4:
            java.lang.String r0 = "INVALID_CERT_HASH"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 40
            goto L_0x03a2
        L_0x02b0:
            java.lang.String r0 = "MISSING_OR_INVALID_NONCE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 66
            goto L_0x03a2
        L_0x02bc:
            java.lang.String r0 = "ADMIN_ONLY_OPERATION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 58
            goto L_0x03a2
        L_0x02c8:
            java.lang.String r0 = "INVALID_EMAIL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 7
            goto L_0x03a2
        L_0x02d3:
            java.lang.String r0 = "SECOND_FACTOR_EXISTS"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 60
            goto L_0x03a2
        L_0x02df:
            java.lang.String r0 = "INVALID_SESSION_INFO"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 36
            goto L_0x03a2
        L_0x02eb:
            java.lang.String r0 = "INVALID_TENANT_ID"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 50
            goto L_0x03a2
        L_0x02f7:
            java.lang.String r0 = "TOKEN_EXPIRED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 23
            goto L_0x03a2
        L_0x0303:
            java.lang.String r0 = "INVALID_CODE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 34
            goto L_0x03a2
        L_0x030f:
            java.lang.String r0 = "MISSING_EMAIL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 29
            goto L_0x03a2
        L_0x031b:
            java.lang.String r0 = "INVALID_OOB_CODE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 24
            goto L_0x03a2
        L_0x0327:
            java.lang.String r0 = "EXPIRED_OOB_CODE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 25
            goto L_0x03a2
        L_0x0333:
            java.lang.String r0 = "RECAPTCHA_NOT_ENABLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 72
            goto L_0x03a2
        L_0x033f:
            java.lang.String r0 = "INVALID_RECAPTCHA_VERSION"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 75
            goto L_0x03a2
        L_0x034a:
            java.lang.String r0 = "WEB_NETWORK_REQUEST_FAILED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 41
            goto L_0x03a2
        L_0x0355:
            java.lang.String r0 = "QUOTA_EXCEEDED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 39
            goto L_0x03a2
        L_0x0360:
            java.lang.String r0 = "DYNAMIC_LINK_NOT_ACTIVATED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 45
            goto L_0x03a2
        L_0x036b:
            java.lang.String r0 = "INVALID_IDP_RESPONSE"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 4
            goto L_0x03a2
        L_0x0375:
            java.lang.String r0 = "INTERNAL_SUCCESS_SIGN_OUT"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 64
            goto L_0x03a2
        L_0x0380:
            java.lang.String r0 = "WEB_CONTEXT_ALREADY_PRESENTED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 47
            goto L_0x03a2
        L_0x038b:
            java.lang.String r0 = "INVALID_RECIPIENT_EMAIL"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 28
            goto L_0x03a2
        L_0x0396:
            java.lang.String r0 = "USER_CANCELLED"
            boolean r0 = r3.equals(r0)
            if (r0 == 0) goto L_0x0007
            r0 = 67
            goto L_0x03a2
        L_0x03a1:
            r0 = -1
        L_0x03a2:
            r1 = 17499(0x445b, float:2.4521E-41)
            switch(r0) {
                case 0: goto L_0x04ad;
                case 1: goto L_0x04aa;
                case 2: goto L_0x04a7;
                case 3: goto L_0x04a4;
                case 4: goto L_0x04a4;
                case 5: goto L_0x04a1;
                case 6: goto L_0x049e;
                case 7: goto L_0x049e;
                case 8: goto L_0x049b;
                case 9: goto L_0x049b;
                case 10: goto L_0x0498;
                case 11: goto L_0x0495;
                case 12: goto L_0x0492;
                case 13: goto L_0x048f;
                case 14: goto L_0x048c;
                case 15: goto L_0x048c;
                case 16: goto L_0x0489;
                case 17: goto L_0x0486;
                case 18: goto L_0x0486;
                case 19: goto L_0x0483;
                case 20: goto L_0x0480;
                case 21: goto L_0x047d;
                case 22: goto L_0x047d;
                case 23: goto L_0x047a;
                case 24: goto L_0x0477;
                case 25: goto L_0x0474;
                case 26: goto L_0x0471;
                case 27: goto L_0x046e;
                case 28: goto L_0x046b;
                case 29: goto L_0x0468;
                case 30: goto L_0x0465;
                case 31: goto L_0x0462;
                case 32: goto L_0x045e;
                case 33: goto L_0x045a;
                case 34: goto L_0x0456;
                case 35: goto L_0x0452;
                case 36: goto L_0x044e;
                case 37: goto L_0x044a;
                case 38: goto L_0x0446;
                case 39: goto L_0x0442;
                case 40: goto L_0x043e;
                case 41: goto L_0x043a;
                case 42: goto L_0x0436;
                case 43: goto L_0x0432;
                case 44: goto L_0x042e;
                case 45: goto L_0x042a;
                case 46: goto L_0x0426;
                case 47: goto L_0x0422;
                case 48: goto L_0x041e;
                case 49: goto L_0x041a;
                case 50: goto L_0x0416;
                case 51: goto L_0x0412;
                case 52: goto L_0x040e;
                case 53: goto L_0x040a;
                case 54: goto L_0x0406;
                case 55: goto L_0x0402;
                case 56: goto L_0x03fe;
                case 57: goto L_0x03fa;
                case 58: goto L_0x03f6;
                case 59: goto L_0x03f2;
                case 60: goto L_0x03ee;
                case 61: goto L_0x03ea;
                case 62: goto L_0x03e6;
                case 63: goto L_0x03e2;
                case 64: goto L_0x03de;
                case 65: goto L_0x03da;
                case 66: goto L_0x03d6;
                case 67: goto L_0x03d2;
                case 68: goto L_0x03ce;
                case 69: goto L_0x03ca;
                case 70: goto L_0x03c6;
                case 71: goto L_0x03c2;
                case 72: goto L_0x03be;
                case 73: goto L_0x03ba;
                case 74: goto L_0x03b6;
                case 75: goto L_0x03b2;
                case 76: goto L_0x03ae;
                case 77: goto L_0x03aa;
                default: goto L_0x03a7;
            }
        L_0x03a7:
            r0 = r1
            goto L_0x04af
        L_0x03aa:
            r0 = 18011(0x465b, float:2.5239E-41)
            goto L_0x04af
        L_0x03ae:
            r0 = 18010(0x465a, float:2.5237E-41)
            goto L_0x04af
        L_0x03b2:
            r0 = 18009(0x4659, float:2.5236E-41)
            goto L_0x04af
        L_0x03b6:
            r0 = 18008(0x4658, float:2.5235E-41)
            goto L_0x04af
        L_0x03ba:
            r0 = 18007(0x4657, float:2.5233E-41)
            goto L_0x04af
        L_0x03be:
            r0 = 18006(0x4656, float:2.5232E-41)
            goto L_0x04af
        L_0x03c2:
            r0 = 18004(0x4654, float:2.5229E-41)
            goto L_0x04af
        L_0x03c6:
            r0 = 18003(0x4653, float:2.5228E-41)
            goto L_0x04af
        L_0x03ca:
            r0 = 18002(0x4652, float:2.5226E-41)
            goto L_0x04af
        L_0x03ce:
            r0 = 17095(0x42c7, float:2.3955E-41)
            goto L_0x04af
        L_0x03d2:
            r0 = 18001(0x4651, float:2.5225E-41)
            goto L_0x04af
        L_0x03d6:
            r0 = 17094(0x42c6, float:2.3954E-41)
            goto L_0x04af
        L_0x03da:
            r0 = 17093(0x42c5, float:2.3952E-41)
            goto L_0x04af
        L_0x03de:
            r0 = 17091(0x42c3, float:2.395E-41)
            goto L_0x04af
        L_0x03e2:
            r0 = 17090(0x42c2, float:2.3948E-41)
            goto L_0x04af
        L_0x03e6:
            r0 = 17089(0x42c1, float:2.3947E-41)
            goto L_0x04af
        L_0x03ea:
            r0 = 17088(0x42c0, float:2.3945E-41)
            goto L_0x04af
        L_0x03ee:
            r0 = 17087(0x42bf, float:2.3944E-41)
            goto L_0x04af
        L_0x03f2:
            r0 = 17086(0x42be, float:2.3943E-41)
            goto L_0x04af
        L_0x03f6:
            r0 = 17085(0x42bd, float:2.3941E-41)
            goto L_0x04af
        L_0x03fa:
            r0 = 17084(0x42bc, float:2.394E-41)
            goto L_0x04af
        L_0x03fe:
            r0 = 17083(0x42bb, float:2.3938E-41)
            goto L_0x04af
        L_0x0402:
            r0 = 17082(0x42ba, float:2.3937E-41)
            goto L_0x04af
        L_0x0406:
            r0 = 17081(0x42b9, float:2.3936E-41)
            goto L_0x04af
        L_0x040a:
            r0 = 17078(0x42b6, float:2.3931E-41)
            goto L_0x04af
        L_0x040e:
            r0 = 17075(0x42b3, float:2.3927E-41)
            goto L_0x04af
        L_0x0412:
            r0 = 17074(0x42b2, float:2.3926E-41)
            goto L_0x04af
        L_0x0416:
            r0 = 17079(0x42b7, float:2.3933E-41)
            goto L_0x04af
        L_0x041a:
            r0 = 17073(0x42b1, float:2.3924E-41)
            goto L_0x04af
        L_0x041e:
            r0 = 17058(0x42a2, float:2.3903E-41)
            goto L_0x04af
        L_0x0422:
            r0 = 17057(0x42a1, float:2.3902E-41)
            goto L_0x04af
        L_0x0426:
            r0 = 17071(0x42af, float:2.3922E-41)
            goto L_0x04af
        L_0x042a:
            r0 = 17068(0x42ac, float:2.3917E-41)
            goto L_0x04af
        L_0x042e:
            r0 = 17040(0x4290, float:2.3878E-41)
            goto L_0x04af
        L_0x0432:
            r0 = 17065(0x42a9, float:2.3913E-41)
            goto L_0x04af
        L_0x0436:
            r0 = 17062(0x42a6, float:2.3909E-41)
            goto L_0x04af
        L_0x043a:
            r0 = 17061(0x42a5, float:2.3908E-41)
            goto L_0x04af
        L_0x043e:
            r0 = 17064(0x42a8, float:2.3912E-41)
            goto L_0x04af
        L_0x0442:
            r0 = 17052(0x429c, float:2.3895E-41)
            goto L_0x04af
        L_0x0446:
            r0 = 17051(0x429b, float:2.3894E-41)
            goto L_0x04af
        L_0x044a:
            r0 = 17049(0x4299, float:2.3891E-41)
            goto L_0x04af
        L_0x044e:
            r0 = 17046(0x4296, float:2.3887E-41)
            goto L_0x04af
        L_0x0452:
            r0 = 17045(0x4295, float:2.3885E-41)
            goto L_0x04af
        L_0x0456:
            r0 = 17044(0x4294, float:2.3884E-41)
            goto L_0x04af
        L_0x045a:
            r0 = 17043(0x4293, float:2.3882E-41)
            goto L_0x04af
        L_0x045e:
            r0 = 17042(0x4292, float:2.3881E-41)
            goto L_0x04af
        L_0x0462:
            r0 = 17041(0x4291, float:2.388E-41)
            goto L_0x04af
        L_0x0465:
            r0 = 17035(0x428b, float:2.3871E-41)
            goto L_0x04af
        L_0x0468:
            r0 = 17034(0x428a, float:2.387E-41)
            goto L_0x04af
        L_0x046b:
            r0 = 17033(0x4289, float:2.3868E-41)
            goto L_0x04af
        L_0x046e:
            r0 = 17032(0x4288, float:2.3867E-41)
            goto L_0x04af
        L_0x0471:
            r0 = 17031(0x4287, float:2.3866E-41)
            goto L_0x04af
        L_0x0474:
            r0 = 17029(0x4285, float:2.3863E-41)
            goto L_0x04af
        L_0x0477:
            r0 = 17030(0x4286, float:2.3864E-41)
            goto L_0x04af
        L_0x047a:
            r0 = 17021(0x427d, float:2.3852E-41)
            goto L_0x04af
        L_0x047d:
            r0 = 17010(0x4272, float:2.3836E-41)
            goto L_0x04af
        L_0x0480:
            r0 = 17014(0x4276, float:2.3842E-41)
            goto L_0x04af
        L_0x0483:
            r0 = 17028(0x4284, float:2.3861E-41)
            goto L_0x04af
        L_0x0486:
            r0 = 17006(0x426e, float:2.383E-41)
            goto L_0x04af
        L_0x0489:
            r0 = 17026(0x4282, float:2.3859E-41)
            goto L_0x04af
        L_0x048c:
            r0 = 17020(0x427c, float:2.385E-41)
            goto L_0x04af
        L_0x048f:
            r0 = 17017(0x4279, float:2.3846E-41)
            goto L_0x04af
        L_0x0492:
            r0 = 17025(0x4281, float:2.3857E-41)
            goto L_0x04af
        L_0x0495:
            r0 = 17009(0x4271, float:2.3835E-41)
            goto L_0x04af
        L_0x0498:
            r0 = 17007(0x426f, float:2.3832E-41)
            goto L_0x04af
        L_0x049b:
            r0 = 17011(0x4273, float:2.3837E-41)
            goto L_0x04af
        L_0x049e:
            r0 = 17008(0x4270, float:2.3833E-41)
            goto L_0x04af
        L_0x04a1:
            r0 = 17005(0x426d, float:2.3829E-41)
            goto L_0x04af
        L_0x04a4:
            r0 = 17004(0x426c, float:2.3828E-41)
            goto L_0x04af
        L_0x04a7:
            r0 = 17000(0x4268, float:2.3822E-41)
            goto L_0x04af
        L_0x04aa:
            r0 = 17002(0x426a, float:2.3825E-41)
            goto L_0x04af
        L_0x04ad:
            r0 = 17016(0x4278, float:2.3844E-41)
        L_0x04af:
            if (r0 != r1) goto L_0x04d3
            if (r4 == 0) goto L_0x04cd
            com.google.android.gms.common.api.Status r0 = new com.google.android.gms.common.api.Status
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            r2.append(r3)
            java.lang.String r3 = ":"
            r2.append(r3)
            r2.append(r4)
            java.lang.String r3 = r2.toString()
            r0.<init>((int) r1, (java.lang.String) r3)
            return r0
        L_0x04cd:
            com.google.android.gms.common.api.Status r4 = new com.google.android.gms.common.api.Status
            r4.<init>((int) r1, (java.lang.String) r3)
            return r4
        L_0x04d3:
            com.google.android.gms.common.api.Status r3 = new com.google.android.gms.common.api.Status
            r3.<init>((int) r0, (java.lang.String) r4)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.auth.internal.zzai.zzb(java.lang.String, java.lang.String):com.google.android.gms.common.api.Status");
    }
}
