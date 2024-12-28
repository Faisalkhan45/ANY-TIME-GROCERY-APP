package com.razorpay;

class BaseConstants {
    static final int BASE_REQUEST_CODE_INTENT = 20;
    public static final String BHIM_PACKAGE_NAME = "in.org.npci.upiapp";
    static final String CANCEL_PARAM = "/cancel?";
    public static final String CRED_PACKAGE = "com.dreamplug.androidapp";
    static final String CRED_PKG = "com.dreamplug.androidapp";
    static final String CRED_URL_SCHEMA = "credpay";
    static final String DEFAULT_PROGRESS_COLOR = "#4aa3df";
    static final String DEFAULT_SENDER = "razorpay";
    static final String DEVELOPMENT = "development";
    static final String GOOGLEPAY_CLASSNAME = "com.google.android.apps.nbu.paisa.inapp.client.api.PaymentsClient";
    static final String GOOGLE_PAY_PKG = "com.google.android.apps.nbu.paisa.user";
    static final int INCOMPATIBLE_PLUGIN = 7;
    static final int INVALID_OPTIONS = 3;
    static final String KEY_ID_PARAM = "key_id=";
    static final String KEY_MISSING_EXCEPTION = "Please set your Razorpay API key in AndroidManifest.xml";
    static final String MAVEN_REPO_URL = "https://mvnrepository.com/artifact/com.razorpay/checkout/";
    static final String METADATA_KEY = "com.razorpay.ApiKey";
    static final String METADATA_PLUGIN_PREFIX = "com.razorpay.plugin.";
    static final String NATIVE_OTP_URL = "payments/create/checkout/json";
    static final int NETWORK_ERROR = 2;
    static final int PARSING_ERROR = 4;
    static final int PAYMENT_CANCELED = 0;
    static final int PAYMENT_ERROR = 5;
    static final String PAYMENT_PREFERENCES_CACHE_KEY = "rzp_payment_preferences";
    static final int PDF_SAVE_CODE = 77;
    static final String PRODUCTION = "production";
    static final String RZP_PAYMENTS_ENDPOINT = "https://api.razorpay.com/v1/payments/";
    static final String RZP_PROGRESS_COLOR = "#0783B4";
    static final String RZP_URL = "https://api.razorpay.com";
    static final int SMS_CONSENT_REQUEST = 1001;
    static final String STATUS_PARAM = "/status?";
    static final int TLS_ERROR = 6;
    static final String TLS_ERROR_MESSAGE = "TLSv1  is not supported for security reasons";
    static final String TRUE_CALLER_PKG = "com.truecaller";
    static final String UNKNOWN = "unknown";
    static final String UPI_AUTOPAY_SCHEMA = "upi://mandate";
    static final int UPI_REQUEST_CODE = 99;
    static final String UPI_URL_SCHEMA = "upi://pay";
    static final String VALIDATION_AMOUNT_ERROR = "Amount should be in paise. Minimum value is 100, which is equal to Re.1";
    static final String VALIDATION_CARD_CVV_DIGITS = "Card cvv can only have digits 0-9";
    static final String VALIDATION_CARD_CVV_LENGTH = "Card cvv needs to be in 3 or 4 digits";
    static final String VALIDATION_CARD_DIGITS_ONLY = "Card number can only have digits 0-9";
    static final String VALIDATION_CARD_EXPIRTY_DIGITS = "Card expiry month can only have digits 0-9";
    static final String VALIDATION_CARD_EXPIRY_YEAR = "Card expiry year needs to be two digits";
    static final String VALIDATION_CARD_LESS_DIGITS = "Card number cannot have lesser than 13 digits or more than 19 digits";
    static final String VALIDATION_CARD_VALIDITY_ERROR = "Card expiry month needs to be between 01-12";
    static final String VALIDATION_CONTACT_LENGTH_ERROR = "Contact length should be between [10-15]";
    static final String VALIDATION_CONTACT_MISSING = "Please add contact element for this payment method";
    static final String VALIDATION_CONTACT_PLUS_ERROR = "Contact can only include + in the start and following characters: + - ( ) 0-9 space";
    static final String VALIDATION_CURRENCY_ERROR = "Currency should be 3 characters. Default value is INR";
    static final String VALIDATION_EMAIL_FAILED = "e-Mail validation failed";
    static final String VALIDATION_EMAIL_LENGTH = "e-Mail cannot be longer than 255 characters";
    static final String VALIDATION_EMANDATE_AMOUNT_ERROR = "Amount should be 0 in case of Emandate.";
    static final String VALIDATION_METHOD_EMPTY = "method is a required field";

    BaseConstants() {
    }
}
