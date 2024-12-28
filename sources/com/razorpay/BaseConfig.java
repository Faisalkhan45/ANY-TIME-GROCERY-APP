package com.razorpay;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import com.bumptech.glide.load.Key;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

class BaseConfig {
    static final String ADVERTISING_ID = "advertising_id";
    static final String CONFIG_JSON = "rzp_config_json";
    static final String CONFIG_VERSION = "rzp_config_version";
    static final String OPINIONATED_SOLN = "opinionated_soln";
    private boolean isMagicEnabled;
    private boolean isOTPElfEnabled;
    private String mCheckoutEndpoint;
    private boolean mConfigEnabled;
    private String mConfigEndpoint;
    private int mLatestSDKVersion;
    private boolean mLumberJackEnabled;
    private String mLumberjackEndpoint;
    private String mLumberjackKey;
    private String mLumberjackSdkIdentifier;
    private String mMagicBaseEndPoint;
    private String mMagicJsFileName;
    private JSONObject mMagicSettings;
    private String mMagicVersionFileName;
    private String mOTPElfBaseEndPoint;
    private String mOTPElfJsFileName;
    private JSONObject mOTPElfSettings;
    private String mOTPElfVersionFileName;
    private String mPermissionCustomMessage;
    private Boolean mPermissionCustomMessageEnabled;
    private boolean mSDKUpdateAlertEnabled;
    private int mSmsPermissionMaxAskCount;
    private String mUpdateSDKMsg;

    BaseConfig() {
    }

    public void setConfig(JSONObject jSONObject) {
        try {
            setUpdateSDKConfig(jSONObject);
            setBaseConfig(jSONObject);
            setPermissionConfig(jSONObject);
            setAnalyticsConfig(jSONObject);
            setOtpElfConfig(jSONObject);
            setMagicConfig(jSONObject);
            setCheckoutBaseConfig(jSONObject);
        } catch (Exception e) {
        }
    }

    static JSONObject getConfig(Context context, int i) {
        InputStream openRawResource;
        String configDataFromPreferences = getConfigDataFromPreferences(context);
        if (configDataFromPreferences == null) {
            try {
                openRawResource = context.getResources().openRawResource(i);
                StringWriter stringWriter = new StringWriter();
                char[] cArr = new char[1024];
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, Key.STRING_CHARSET_NAME));
                while (true) {
                    int read = bufferedReader.read(cArr);
                    if (read == -1) {
                        break;
                    }
                    stringWriter.write(cArr, 0, read);
                }
                openRawResource.close();
                configDataFromPreferences = stringWriter.toString();
            } catch (Exception e) {
                return null;
            } catch (Throwable th) {
                openRawResource.close();
                throw th;
            }
        }
        return new JSONObject(configDataFromPreferences);
    }

    static String getOtpelfJsFromFile(Context context, int i) {
        InputStream openRawResource;
        try {
            openRawResource = context.getResources().openRawResource(i);
            StringWriter stringWriter = new StringWriter();
            char[] cArr = new char[1024];
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(openRawResource, Key.STRING_CHARSET_NAME));
            while (true) {
                int read = bufferedReader.read(cArr);
                if (read != -1) {
                    stringWriter.write(cArr, 0, read);
                } else {
                    openRawResource.close();
                    return stringWriter.toString();
                }
            }
        } catch (Exception e) {
            return "";
        } catch (Throwable th) {
            openRawResource.close();
            throw th;
        }
    }

    private void setAnalyticsConfig(JSONObject jSONObject) {
        this.mLumberJackEnabled = ((Boolean) BaseUtils.getJsonValue("analytics.lumberjack.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
        this.mLumberjackKey = (String) BaseUtils.getJsonValue("analytics.lumberjack.key", jSONObject, (Object) "");
        this.mLumberjackEndpoint = (String) BaseUtils.getJsonValue("analytics.lumberjack.end_point", jSONObject, (Object) "");
        this.mLumberjackSdkIdentifier = (String) BaseUtils.getJsonValue("analytics.lumberjack.sdk_identifier", jSONObject, (Object) "");
    }

    private void setUpdateSDKConfig(JSONObject jSONObject) {
        this.mLatestSDKVersion = ((Integer) BaseUtils.getJsonValue("update_sdk_config.latest_version", jSONObject, (Object) 1)).intValue();
        this.mUpdateSDKMsg = (String) BaseUtils.getJsonValue("update_sdk_config.msg", jSONObject, (Object) "");
        this.mSDKUpdateAlertEnabled = ((Boolean) BaseUtils.getJsonValue("update_sdk_config.enable_alert", jSONObject, (Object) Boolean.TRUE)).booleanValue();
    }

    private void setBaseConfig(JSONObject jSONObject) {
        this.mConfigEndpoint = (String) BaseUtils.getJsonValue("config_end_point", jSONObject, (Object) "");
        this.mConfigEnabled = ((Boolean) BaseUtils.getJsonValue("enable", jSONObject, (Object) "")).booleanValue();
    }

    private void setCheckoutBaseConfig(JSONObject jSONObject) {
        setCheckoutEndPoint((String) BaseUtils.getJsonValue("checkout.end_point", jSONObject, (Object) ""));
    }

    private void setOtpElfConfig(JSONObject jSONObject) {
        this.isOTPElfEnabled = ((Boolean) BaseUtils.getJsonValue("otpelf.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
        this.mOTPElfSettings = (JSONObject) BaseUtils.getJsonValue("otpelf.settings", jSONObject, (Object) new JSONObject());
        this.mOTPElfBaseEndPoint = (String) BaseUtils.getJsonValue("otpelf.endpoint", jSONObject, (Object) "https://cdn.razorpay.com/static/otpelf/");
        this.mOTPElfVersionFileName = (String) BaseUtils.getJsonValue("otpelf.version_file_name", jSONObject, (Object) "version.json");
        this.mOTPElfJsFileName = (String) BaseUtils.getJsonValue("otpelf.js_file_name", jSONObject, (Object) "otpelf.js");
    }

    private void setMagicConfig(JSONObject jSONObject) {
        this.isMagicEnabled = ((Boolean) BaseUtils.getJsonValue("magic.enable", jSONObject, (Object) Boolean.TRUE)).booleanValue();
        this.mMagicSettings = (JSONObject) BaseUtils.getJsonValue("magic.settings", jSONObject, (Object) new JSONObject());
        this.mMagicBaseEndPoint = (String) BaseUtils.getJsonValue("magic.endpoint", jSONObject, (Object) "https://cdn.razorpay.com/static/magic/");
        this.mMagicVersionFileName = (String) BaseUtils.getJsonValue("magic.version_file_name", jSONObject, (Object) "version.json");
        this.mMagicJsFileName = (String) BaseUtils.getJsonValue("magic.js_file_name", jSONObject, (Object) "magic.js");
    }

    private void setPermissionConfig(JSONObject jSONObject) {
        this.mPermissionCustomMessage = (String) BaseUtils.getJsonValue("permissions.custom_message", jSONObject, (Object) "");
        this.mPermissionCustomMessageEnabled = Boolean.valueOf(((Boolean) BaseUtils.getJsonValue("permissions.enable_custom_message", jSONObject, (Object) Boolean.FALSE)).booleanValue());
        this.mSmsPermissionMaxAskCount = ((Integer) BaseUtils.getJsonValue("permissions.max_ask_count", jSONObject, (Object) 0)).intValue();
    }

    /* access modifiers changed from: package-private */
    public boolean isConfigEnabled() {
        return this.mConfigEnabled;
    }

    /* access modifiers changed from: package-private */
    public String getLumberjackEndpoint() {
        return this.mLumberjackEndpoint;
    }

    /* access modifiers changed from: package-private */
    public String getLumberjackKey() {
        return this.mLumberjackKey;
    }

    /* access modifiers changed from: package-private */
    public Boolean isLumberJackEnabled() {
        return Boolean.valueOf(this.mLumberJackEnabled);
    }

    /* access modifiers changed from: package-private */
    public String getConfigEndpoint() {
        return this.mConfigEndpoint;
    }

    /* access modifiers changed from: package-private */
    public int getLatestSDKVersionCode() {
        return this.mLatestSDKVersion;
    }

    /* access modifiers changed from: package-private */
    public String getUpdateSDKMsg() {
        return this.mUpdateSDKMsg;
    }

    /* access modifiers changed from: package-private */
    public boolean isSDKUpdateAlertEnabled() {
        return this.mSDKUpdateAlertEnabled;
    }

    /* access modifiers changed from: package-private */
    public String getPermissionCustomMessage() {
        return this.mPermissionCustomMessage;
    }

    /* access modifiers changed from: package-private */
    public Boolean isPermissionCustomMessageEnabled() {
        return this.mPermissionCustomMessageEnabled;
    }

    /* access modifiers changed from: package-private */
    public int getSmsPermissionMaxAskCount() {
        return this.mSmsPermissionMaxAskCount;
    }

    /* access modifiers changed from: package-private */
    public Boolean isOTPElfEnabled() {
        return Boolean.valueOf(this.isOTPElfEnabled);
    }

    /* access modifiers changed from: package-private */
    public JSONObject getOTPElfSettings() {
        return this.mOTPElfSettings;
    }

    /* access modifiers changed from: package-private */
    public String getCheckoutEndpoint() {
        return "https://api.razorpay.com" + this.mCheckoutEndpoint;
    }

    /* access modifiers changed from: package-private */
    public void setCheckoutEndPoint(String str) {
        this.mCheckoutEndpoint = str;
    }

    /* access modifiers changed from: package-private */
    public String getLumberjackSdkIdentifier() {
        return this.mLumberjackSdkIdentifier;
    }

    /* access modifiers changed from: package-private */
    public String getOTPElfBaseEndPoint() {
        return this.mOTPElfBaseEndPoint;
    }

    /* access modifiers changed from: package-private */
    public String getOTPElfJsFileName() {
        return this.mOTPElfJsFileName;
    }

    /* access modifiers changed from: package-private */
    public String getOtpElfVersionUrl() {
        return getOTPElfBaseEndPoint() + this.mOTPElfVersionFileName;
    }

    /* access modifiers changed from: package-private */
    public String getOtpElfJsUrl() {
        return getOTPElfBaseEndPoint() + this.mOTPElfJsFileName;
    }

    static void fetchConfig(String str, Map<String, String> map, Context context) {
        M$_3_.a(str, map, new r$_Y_(context));
    }

    static void getAdvertisingIdFromUtil(Context context) {
        if (getAdvertisingId(context) == null) {
            AdvertisingIdUtil.getId(context, new Y$_o$(context));
        }
    }

    protected static Uri.Builder getFetchConfigBuilder(Uri.Builder builder, Context context, String str) {
        builder.appendQueryParameter("merchant_key_id", str).appendQueryParameter("android_version", Build.VERSION.RELEASE).appendQueryParameter("device_id", getAdvertisingId(context)).appendQueryParameter("device_manufacturer", Build.MANUFACTURER).appendQueryParameter("device_model", Build.MODEL).appendQueryParameter("network_type", BaseUtils.getDataNetworkType(context).getNetworkTypeName()).appendQueryParameter("cellular_network_type", BaseUtils.getCellularNetworkType(context)).appendQueryParameter("cellular_network_provider", BaseUtils.getCellularNetworkProviderName(context)).appendQueryParameter("app_package_name", context.getApplicationContext().getPackageName()).appendQueryParameter("build_type", BaseUtils.getAppBuildType(context)).appendQueryParameter("magic_version_code", String.valueOf(n$_B$.c.intValue())).appendQueryParameter("rzpassist_version_code", String.valueOf(n$_B$.b.intValue())).appendQueryParameter("webview_user_agent", BaseUtils.getWebViewUserAgent(context).toString());
        return builder;
    }

    protected static String getBaseCurrentConfigVersion(Context context) {
        return getConfigVersionFromPreferences(context);
    }

    static String getCurrentConfigVersionTag(String str) {
        Matcher matcher = Pattern.compile("^(\\d+\\.)(\\d+\\.)(\\d+)$").matcher(str);
        if (matcher.find()) {
            return matcher.replaceFirst("$1$2*");
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static void setConfigVersionToPreferences(Context context, String str) {
        l.b(context).putString(CONFIG_VERSION, str).apply();
    }

    static void setOpinionatedSolnPreference(Context context, Boolean bool) {
        l.b(context).putBoolean(OPINIONATED_SOLN, bool.booleanValue()).apply();
    }

    static Boolean getOpinionatedSolnPreference(Context context) {
        return Boolean.valueOf(l.a(context).getBoolean(OPINIONATED_SOLN, true));
    }

    private static String getConfigVersionFromPreferences(Context context) {
        return l.a(context).getString(CONFIG_VERSION, (String) null);
    }

    /* access modifiers changed from: private */
    public static void saveConfigDataToPreferences(Context context, String str) {
        l.b(context).putString(CONFIG_JSON, str).apply();
    }

    private static String getConfigDataFromPreferences(Context context) {
        return l.a(context).getString(CONFIG_JSON, (String) null);
    }

    /* access modifiers changed from: private */
    public static void setAdvertisingId(Context context, String str) {
        l.b(context).putString(ADVERTISING_ID, str).apply();
    }

    public static String getAdvertisingId(Context context) {
        return l.a(context).getString(ADVERTISING_ID, (String) null);
    }

    public boolean isMagicEnabled() {
        return this.isMagicEnabled;
    }

    public JSONObject getMagicSettings() {
        return this.mMagicSettings;
    }

    /* access modifiers changed from: package-private */
    public String getMagicBaseEndPoint() {
        return this.mMagicBaseEndPoint;
    }

    /* access modifiers changed from: package-private */
    public String getMagicJsFileName() {
        return this.mMagicJsFileName;
    }

    public String getMagicVersionUrl() {
        return getMagicBaseEndPoint() + this.mMagicVersionFileName;
    }

    public String getMagicJsUrl() {
        return getMagicBaseEndPoint() + this.mMagicJsFileName;
    }

    static String getVersionJSON() {
        return "{\n  \"hash\" : \"MD5\",\n  \"magic_hash\": \"MD5\"\n}\n";
    }

    static String getMagicJs() {
        return "var Magic=function(e){\"use strict\";function t(e){return\"function\"==typeof e}function n(e){return\"string\"==typeof e}function r(e){return e&&\"object\"==typeof e}function o(e,n){if(t(e))return arguments.length>2?e.bind.apply(e,slice(arguments,1)):e.bind(n)}function i(e,t){return Array.prototype.indexOf.call(t,e)}function s(e,t){return-1!==i(e,t)}function a(e,t,n){var r;if(arguments.length<3&&(n=this),e)if(void 0!==e.length)for(r=0;r<e.length;r++)t.call(n,r,e[r]);else for(r in e)e.hasOwnProperty(r)&&t.call(n,r,e[r])}function u(e){return e.replace(/^\\s*/g,\"\").replace(/\\s*$/g,\"\")}function p(e){void 0===e&&(e=\"\");var t=e.split(\"?\")[0];return t=t.split(\";\")[0]}function m(e,t){return t.getAttribute(e)||\"\"}function c(e){return\"hidden\"!==e.getAttribute(\"type\")}function d(e){var t=window.frames[e].frameElement;return\"complete\"===(t.contentDocument||t.contentWindow.document).readyState}function l(e){return te.push(e),te.length}function _(e){for(var t=[],n=arguments.length-1;n-- >0;)t[n]=arguments[n+1];var r=((window.webkit||{}).messageHandlers||{}).OTPElfBridge;if(r)r.postMessage({action:e,params:t});else try{return OTPElfBridge[e].apply(OTPElfBridge,t)}catch(e){}}function f(e){for(var t=[],n=arguments.length-1;n-- >0;)t[n]=arguments[n+1];try{return t=t.map(function(e){return\"object\"==typeof e?JSON.stringify(e):e}),MagicBridge[e].apply(MagicBridge,t)}catch(e){}}function y(){var e=_(\"getFromCustomStorage\",\"OTP.pin\");if(void 0!==e){var t=_(\"getFromCustomStorage\",\"OTP.bank\"),n=_(\"getFromCustomStorage\",\"OTP.sender\");!1===/RZRPAY$/.test(n)&&re(e,n,t),_(\"clearCustomStorage\")}}function b(e){return ue||(window.rzp?(g(window.rzp),ue=!0):window.__rzp_options&&(v(window.__rzp_options),ue=!0)),e?ae[e]:ae}function g(e){var t=ae.plugin.type;switch(e.settings.applicationType){case\"checkout\":t=\"rzpassist\";break;case\"magic\":t=\"magic\"}var n=_(\"isOTPEnabled\")||!1;v({platform:e.settings.platform,merchant_key:e.settings.merchantKey,otp_permission:n,sdk:e.settings.sdk,plugin:{type:t,version_code:e.settings.rzpassist_version_code}})}function h(e){return\"__proto__\"===e||\"constructor\"===e||\"prototype\"===e}function v(e,t,n){if(void 0===n&&(n=ae),e&&!h(e))if(\"object\"==typeof e)for(var r in e)v(r,e[r]);else if(\"object\"==typeof t)for(var r in t)n[e]=n[e]||{},v(r,t[r],n[e]);else n[e]=t}function w(e){var t=k(e);if(!t)return!1;var n=e.index?t.querySelectorAll(e.query)[e.index]:t.querySelector(e.query);return n&&e.condition?!!e.condition(n,window)&&n:n}function k(e){var t=e.frame?window.frames[e.frame]:window;return t=e.form?t.document.forms[e.form]:t.document}function q(e){var t,r=Q.forms;for(var o in r)if(r[o]===e&&(t=o,n(o)))break;return t}function I(e,t){void 0===t&&(t={}),t=Object.assign(t,P()),e=\"otpelf:\"+e,ve?_(\"trackEvent\",e):_(\"trackEvent\",e,JSON.stringify(t))}function S(e){var t=A(e);return!!(e&&t.length>0)&&{view_types:t}}function C(e){var t=S(e);t?I(\"known_page_loaded\",t):Se||x(e)}function x(e){var t=function(e){var t=e.target;if(s(t.nodeName,[\"INPUT\",\"BUTTON\",\"A\"])){Ce||(I(\"unknown_page_loaded\"),Ce=!0);try{var n={};n.element=t.nodeName,n.id=t.id||\"\",n.name=m(\"name\",t),n.value=\"A\"===t.nodeName?t.innerText:t.value;var r=t.form;r&&r.contains(t)&&(n.form_index=q(r)),I(\"bank_page_interaction\",n)}catch(e){}}};document.addEventListener(\"click\",t),document.addEventListener(\"dblclick\",t)}function P(){return{url:location.href,strippedUrl:p(location.href),method:b(\"payment_data\").method,plugin_type:b(\"plugin\").type,plugin_version_code:b(\"plugin\").version_code}}function B(e){for(var t=0;t<xe.length;t++)if(e.match(xe[t].regex))return N(xe[t]),xe[t]}function T(e){for(var t=0;t<xe.length;t++)if(e.match(xe[t].regex))return xe[t].banks||[]}function O(e,t){for(var n=0;n<t.length;n++){var r=e[t[n]],o=w(r);if(!o)return!1;if(!r.bypassVisibility&&!c(o))return!1}return!0}function A(e){if(void 0===e)return[];for(var t=e.view_rules,n=[],r=0;r<t.length;r++)O(e,t[r].elements)&&n.push(t[r].type);return n}function E(e,t){if(e)for(var n=e.view_rules,r=0;r<n.length;r++)if(n[r].type===t)return n[r]}function N(e){e.logged_in_element&&document.querySelector(e.logged_in_element.query)&&I(\"netbanking_user_loggedin\")}function R(e){if(Be[e])return Be[e];for(var t=Pe.specific,n=0,r=t.length;n<r;n++){var o=t[n];if(new RegExp(o.matches.sender+\"$\",\"i\").test(e))return Be[e]=o,o}return null}function D(e){var t=R(e.sender);return t?t.bank:null}function M(e){var t=e.message,n=e.sender;if(!n||!t)return null;var r=R(n);if(r&&new RegExp(r.matches.message).test(e.message)){var o=new RegExp(r.pattern),i=e.message.match(o);if(i&&i.length)return i[0]}return F(e)}function F(e){for(var t=Pe.generic,n=t.textPatterns,r=t.otpPatterns,o=0,i=n.length;o<i;o++)if(new RegExp(n[o],\"i\").test(e.message))for(var s=0,a=r.length;s<a;s++){var u=r[s],p=e.message.match(u);if(p&&p[0])return p[0]}return null}function U(e){se||(se=W()),Te.push(e)}function W(){return l(function(e){var t=T(location.href),n=D(e),r=M(e);if(r)for(var o={otp:r,bank:n,sender:e.sender},i=0;i<Te.length;i++)Te[i](o);else I(\"otp_not_extracted\",{acsBanks:JSON.stringify(t),otpBank:n})})}function L(e){if(e&&e.check){var t=w(e.check);if(t){t.checked=!0;try{t.dispatchEvent(new Event(\"input\")),t.dispatchEvent(new Event(\"change\"))}catch(e){}}}}function H(e){if(e){if(A(e).indexOf(\"choice\")>=0){var t=E(e,\"choice\");if(\"radio\"!==(t&&(t.choice_type||\"radio\")))return}if(e.choice_submit){var n=w(e.choice_submit);n&&n.click()}}}function Y(e){w(e.otp_choice).click(),H(e),I(\"otp_auth_selected\",e)}function j(e){w(e.password_choice).click(),H(e),I(\"password_auth_selected\",e)}function K(e,t){var n=w(e.otp_input);n||I(\"bank_otp_not_autofilled\",{rule:e,otp:Boolean(t)}),n.value=t;try{n.dispatchEvent(new Event(\"input\")),n.dispatchEvent(new Event(\"change\"))}catch(e){}I(\"bank_otp_autofilled\",{rule:e,otp:Boolean(t)})}function G(e){var t=w(e.otp_submit);return!!t&&(t.click(),I(\"bank_otp_submitted\",e),!0)}function z(e){var t=w(e.proceed_submit);return!!t&&(t.click(),I(\"bank_submit_proceed\",e),!0)}function V(e){if(!e.resend_otp)return!1;var t=w(e.resend_otp);return!!t&&(t.click(),I(\"bank_otp_resend\",e),!0)}function J(e){if(!e||!e.error_message)return null;var t=w(e.error_message);if(t){var n=u(t.innerHTML);return n?n.replace(/<\\/?[^>]+(>|$)/g,\"\"):null}return null}function $(e){function t(t){void 0===t&&(t={}),n.indexOf(\"proceed\")>=0?(Oe.emit(\"page_resolved\",n[0]),Oe.emit(\"proceed\")):n.indexOf(\"choice\")>=0&&\"choice\"!==t.previous_rule?O(e,e.view_rules[0].elements)?Oe.emit(\"page_resolved\",{type:n[0],data:{choices:e.view_rules[0].elements}}):(Oe.emit(\"page_resolved\",n[0]||\"unknown\"),Oe.emit(\"abort_magic\",Ae.ElementNotFoundOnPage)):Oe.emit(\"page_resolved\",t.next_rule||n[0]||\"unknown\");var r=J(e);r&&(!e.false_error_messages||e.false_error_messages.indexOf(r)<0)&&Oe.emit(\"error_message\",r)}var n=A(e);Oe.on(\"abort_magic\",function(e){void 0===e&&(e=Ae.default),X({action:\"abort_magic\",data:e})}),Oe.on(\"page_unload\",function(e){void 0===e&&(e={}),X({action:\"page_unload\",data:e})}),Oe.on(\"select_choice\",function(n){var r=n.choice;void 0===r&&(r=\"\");{if(e)return\"password\"===r.toLowerCase()?(j(e),void Oe.emit(\"abort_magic\",Ae.PasswordChosen)):void(\"otp\"===r.toLowerCase()?(Y(e),e.choice_otp&&t({previous_rule:\"choice\",next_rule:\"otp\"})):Oe.emit(\"abort_magic\",Ae.InvalidChoice));Oe.emit(\"abort_magic\",Ae.RuleIsUndefined)}}),Oe.on(\"submit_otp\",function(t){var r=t.otp;e?n.indexOf(\"otp\")>=0&&(K(e,r),G(e)):Oe.emit(\"abort_magic\",Ae.RuleIsUndefined)}),Oe.on(\"resend_otp\",function(){e?V(e)?Oe.emit(\"otp_resent\",!0):Oe.emit(\"abort_magic\",Ae.FailedToResendOTP):Oe.emit(\"abort_magic\",Ae.RuleIsUndefined)}),Oe.on(\"otp_resent\",function(e){X({action:\"otp_resent\",data:e})}),Oe.on(\"load\",function(){e&&t()}),Oe.on(\"page_resolved\",function(t){\"string\"==typeof t&&(t={type:t,otp_permission:b(\"otp_permission\")}),e&&(t.bank=e.banks&&e.banks[0]||\"unknown\"),X({action:\"page_resolved\",data:t})}),Oe.on(\"proceed\",function(){e?(L(e),z(e)):Oe.emit(\"abort_magic\",Ae.RuleIsUndefined)}),Oe.on(\"error_message\",function(e){X({action:\"error_message\",data:e})}),Oe.on(\"otp_parsed\",function(e){X({action:\"otp_parsed\",data:e})})}function X(e){return f(\"relay\",e)}var Z=window,Q=Z.document,ee=(Q.documentElement,Q.body,o(Q.querySelector,Q)),te=(o(Q.querySelectorAll,Q),o(Q.getElementById,Q),o(Z.getComputedStyle,Z),[]),ne={setSms:function(e){for(var t=0;t<te.length;t++)te[t](e)}};window.elfBridge=ne;var re=function(e,t,n){var r={message:e,sender:t};ne.setSms(r)},oe={loadCSS:function(e){}},ie=function(){this.listenerPool={}};ie.prototype.on=function(e,n){this.listenerPool[e]||(this.listenerPool[e]=[]);var o,s=this.listenerPool[e];if(t(n))o={callback:n};else{if(!r(n))return!1;o=n}return s.push(o),{remove:function(){var e=i(s,o);e>=0&&s.splice(e,1)}}},ie.prototype.off=function(e){var t=this;e?delete this.listenerPool[e]:a(this.listenerPool,function(e){delete t.listenerPool[e]})},ie.prototype.emit=function(e){for(var t=[],n=arguments.length-1;n-- >0;)t[n]=arguments[n+1];var r=this.listenerPool[e]||[];a(r,function(e){var n=r[e].callback;n&&n.apply(n,t)})};var se,ae={platform:null,merchant_key:null,otp_permission:!1,sdk:{type:null,version_code:null},preferences:{autosubmit_otp:!1,theme_color:\"#168AFA\"},plugin:{type:null,version_code:null},payment_data:{method:null,bank:null,wallet:null,vpa:null,amount:0,razorpay_otp:!1}},ue=!1,pe=[{regex:\"secure4.arcot.com/acspage/cap\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"button#sendotp\",form:\"0\"},otp_input:{query:\"form[name=passwdForm] input[name=otp]\"},resend_otp:{query:\"a\",condition:function(e){return/resend/i.test(e.href)}},banks:[]},{regex:\"secure7.arcot.com/acspage/cap\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"button#sendotp\",form:\"0\"},otp_input:{query:\"input#enterPIN\",form:\"0\"},banks:[]},{regex:\"secure5.arcot.com/acspage/cap\",view_rules:[{type:\"choice\",elements:[\"otp_choice\",\"password_choice\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"button#sendotp\",form:\"0\"},otp_input:{query:\"input#otpentrypin\",form:\"0\"},check:{query:\"input#disclaimer\",form:\"0\"},choice_submit:{query:\"button#continue\",form:\"0\"},otp_choice:{query:\"input#otp\",form:\"0\"},password_choice:{query:\"input#static\",form:\"0\"},resend_otp:{query:\"span#resend a\",form:\"0\"},error_message:{query:\"div#info_error span\"},banks:[\"INDB\"]}],me=\"(0.0.0.0|localhost|127.0.0.1):1911\",ce=[{regex:me+\"/proceed\",view_rules:[{type:\"proceed\",elements:[\"proceed_submit\"]}],proceed_submit:{query:\"input[type=submit]\",form:\"0\"},check:{query:\"input#otp\",form:\"0\"},banks:[\"DUMMY\"]},{regex:me+\"/enter_otp\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"input[type=submit]\",form:\"0\"},otp_input:{query:\"input#otp_input\",form:\"0\"},banks:[\"DUMMY\"]},{regex:me+\"/auth_choice\",view_rules:[{type:\"choice\",elements:[\"otp_choice\",\"password_choice\"]}],otp_choice:{query:\"input#otp_input\",form:\"0\"},password_choice:{query:\"input#password_input\",form:\"0\"},choice_submit:{query:\"input[type=submit]\",form:\"0\"},banks:[\"DUMMY\"]}],de=[{regex:\"netsafe.hdfcbank.com/ACSWeb/jsp/dynamicAuth.jsp\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"input#cmdSubmit\",form:\"0\"},otp_input:{query:\"input#txtOtpPassword\",form:\"0\"},resend_otp:{query:\"#otpBtnReGen\",form:\"0\"},error_message:{query:\".errorType p:not(.successColor)\"},false_error_messages:\"This is your last attempt to generate the OTP\",banks:[\"HDFC\"]},{regex:\"netsafe.hdfcbank.com/ACSWeb/authJsp/authImprovedHopsTxnPage.jsp\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"#cmdSubmit\",form:\"0\"},otp_input:{query:\"input#txtOtpPassword\",form:\"0\"},resend_otp:{query:\"#otpBtnReGen\",form:\"0\"},error_message:{query:\"form .errormsg\"},false_error_messages:\"This is your last attempt to generate the OTP\",banks:[\"HDFC\"]},{regex:\"netsafe.hdfcbank.com/ACSWeb/authJsp/authImprovedTxnPage.jsp\",view_rules:[{type:\"choice\",elements:[\"password_choice\",\"otp_choice\"],choice_type:\"button\"},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],choice_otp:!0,password_choice:{query:\"button#staticAuthOpen\"},otp_choice:{query:\"button#dynamicAuthOpen\"},otp_submit:{query:\"#cmdSubmitDynamic\",form:\"0\"},otp_input:{query:\"input#txtOtpPassword\",form:\"0\"},resend_otp:{query:\"#otpBtnReGen\",form:\"0\"},error_message:{query:\"form .errormsg\"},banks:[\"HDFC\"]},{regex:\"netbanking.hdfcbank.com/netbanking/merchant\",frame:1,view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input[name=fldLoginUserId]\",form:\"0\",frame:1},password_input:{query:\"input[name=fldPassword]\",form:\"0\",frame:1},proceed_submit:{query:\"td.login_tab a\",form:\"0\",frame:1},otp_submit:{query:\"img[alt=Submit]\",form:\"1\"},otp_input:{query:\"input[name=fldOtpToken]\",form:\"1\"},secure_image_input:{query:\"input[name=chkrsastu]\",form:0,frame:1},user_details_form:{query:\"form[name=frmLogin] table table\",form:0,frame:1},logged_in_element:{query:'[alt=\"Continue\"]',form:0,frame:1},banks:[\"HDFC\"]},{regex:\"netbanking.hdfcbank.com/netbanking/(entry|epientry)\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input[name=fldLoginUserId]\",form:\"0\"},password_input:{query:\"input[name=fldPassword]\",form:\"0\"},proceed_submit:{query:\"td.login_tab a\",form:\"0\"},otp_submit:{query:\"img[alt=Submit]\",form:\"1\"},otp_input:{query:\"input[name=fldOtpToken]\",form:\"1\"},logged_in_element:{query:'[alt=\"Continue\"]',form:0,frame:1},banks:[\"HDFC\"]},{regex:\"netsafe.hdfcbank.com/ACSWeb/jsp/payerAuthOptions.jsp\",view_rules:[{type:\"choice\",elements:[\"otp_choice\",\"password_choice\"]}],otp_choice:{query:\"input[name=acsRadio]\",form:\"0\",index:1},password_choice:{query:\"input[name=acsRadio]\",form:\"0\"},choice_submit:{query:\"input#submitBtn\",form:\"0\"},banks:[\"HDFC\"]}],le=[{regex:\"https://acs.icicibank.com/acspage/cap\",view_rules:[{type:\"proceed\",elements:[\"try_index_proceed\",\"check\",\"proceed_submit\"]},{type:\"otp\",elements:[\"try_index_otp\",\"otp_input\",\"otp_submit\"]}],try_index_proceed:{query:'input[name=tryIndex][value=\"1\"]',form:\"0\",bypassVisibility:!0},try_index_otp:{query:'input[name=tryIndex]:not([value=\"1\"])',form:\"0\",bypassVisibility:!0},check:{query:\"input[name=otpDestinationOption]\",form:\"0\"},proceed_submit:{query:\"#pwdbaseotppage button[type=submit]\",form:\"0\"},otp_input:{query:\"input#txtAutoOtp\",form:\"0\"},otp_submit:{query:\"#PASSWDPAGE button[type=submit]\",form:\"0\"},banks:[\"ICIC\"]},{regex:\"www.3dsecure.icicibank.com/ACSWeb/EnrollWeb/ICICIBank/server/OtpServer\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"input#cmdSubmit\",form:\"0\"},otp_input:{query:\"input#txtAutoOtp\",form:\"0\"},resend_otp:{query:\"#link\",form:\"0\"},error_message:{query:\".errorType\"},banks:[\"ICIC\"]},{regex:\"(www.3dsecure.icicibank.com/ACSWeb/EnrollWeb/ICICIBank/server/AccessControlServer|www.3dsecure.icicibank.com/ACSWeb/EnrollWeb/ICICIBank/auth/SCode)\",view_rules:[{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],proceed_submit:{query:\"input[name=I1]\",form:\"0\"},check:{query:\"input#otpDestinationOption_toMobile_InputId\",form:\"0\"},otp_submit:{query:\"input#cmdSubmit\",form:\"0\"},otp_input:{query:\"input#txtAutoOtp\",form:\"0\"},resend_otp:{query:\"#link\",form:\"0\"},error_message:{query:\".errorType\"},banks:[\"ICIC\"]},{regex:\"shopping.icicibank.com/corp/(BANKAWAY|Finacle)\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input[name='AuthenticationFG.USER_PRINCIPAL']\",form:\"0\"},password_input:{query:\"input[name='AuthenticationFG.ACCESS_CODE']\",form:\"0\"},proceed_submit:{query:\"input#VALIDATE_CREDENTIALS\",form:\"0\"},otp_input:{query:\"input[id='TranRequestManagerFG.ONE_TIME_PASSWORD__']\"},otp_submit:{query:\"input[id='SUBMIT_TRANSACTION']\",form:\"0\"},banks:[\"ICIC\"]},{regex:\"shopping.icicibank.com/corp/AuthenticationController\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input[name='AuthenticationFG.USER_PRINCIPAL']\",form:\"0\"},password_input:{query:\"input[name='AuthenticationFG.ACCESS_CODE']\",form:\"0\"},proceed_submit:{query:\"input#VALIDATE_CREDENTIALS\",form:\"0\"},otp_input:{query:\"input[id='TranRequestManagerFG.ONE_TIME_PASSWORD__']\"},otp_submit:{query:\"input[id='SUBMIT_TRANSACTION']\",form:\"0\"},logged_in_element:{query:\"input[name='Action.SUBMIT_TRANSACTION']\",form:\"0\"},banks:[\"ICIC\"]}],_e=[{regex:\"merchant.onlinesbi.com/merchant/smsenablehighsecurity.htm\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"input#confirmButton\"},otp_input:{query:\"input[name=securityPassword]\"},banks:[\"SBIN\"]},{regex:\"acs([0-9]?).onlinesbi.com/bdacs/SBIValidate/V\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"#form1 .formInputSection .button.next\"},otp_input:{query:\"input[name=customerotp]\"},resend_otp:{query:\".resendBtn a\"},banks:[\"SBIN\"]},{regex:\"acs([0-9]?).onlinesbi.com/bdacs/SBIValidate/M\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"#form1 .formInputSection .button.next\"},otp_input:{query:\"input[name=customerpin]\"},resend_otp:{query:\".resendBtn a\"},banks:[\"SBIN\"]},{regex:\"merchant.onlinesbi.com/merchant/(merchantprelogin|loginsubmit).htm\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]}],username_input:{query:\"input#username\",form:\"0\"},password_input:{query:\"input#label2\",form:\"0\"},proceed_submit:{query:\"input\",form:\"0\",index:8},banks:[\"SBIN\"]}],fe=[{regex:\"secure.axisbank.com/(acs-web-axis|ACSWeb)/EnrollWeb/AxisBank/server/OtpServer\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:\"div#otp a\",form:\"0\"},otp_input:{query:\"input#otpValue\",form:\"0\"},resend_otp:{query:\"div#otp div.resentOtp a\"},error_message:{query:\"span#errorMsg\"},banks:[\"UTIB\"]},{regex:\"secure.axisbank.com/(acs-web-axis|ACSWeb)/EnrollWeb/AxisBank/server/AccessControlServer\",view_rules:[{type:\"choice\",elements:[\"password_choice\",\"otp_choice\"],choice_type:\"link\"},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],password_choice:{query:\"a#t1_static\"},otp_choice:{query:\"a#t2_otp\"},choice_otp:!0,otp_input:{query:\"input#otpValue\"},otp_submit:{query:\"#otp a.active, a#changeSubmitButton\"},banks:[\"UTIB\"]},{regex:\"retail.axisbank.co.in/wps/portal/rBanking/AxisSMRetailLogin/axissmretailpage/*\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input#loginId\",form:\"0\"},password_input:{query:\"input#newPassword\",form:\"0\"},proceed_submit:{query:\"input[name=SMsubmit]\",form:\"0\"},otp_input:{query:\"input[id=orignipdef]\",form:\"0\"},otp_submit:{query:\"input[value=Confirm]\",form:\"0\"},banks:[\"UTIB\"]},{regex:\"retail.axisbank.co.in/wps/myportal/rBanking/AxisSMRetailLogin/axissmrepayments/*\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]},{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],username_input:{query:\"input#loginId\",form:\"0\"},password_input:{query:\"input#newPassword\",form:\"0\"},proceed_submit:{query:\"input[name=SMsubmit]\",form:\"0\"},otp_input:{query:\"input[id=orignipdef]\",form:\"0\"},otp_submit:{query:\"input[value=Confirm]\",form:\"0\"},logged_in_element:{query:\"input[value=Confirm]\",form:0},banks:[\"UTIB\"]}],ye=[{regex:\"www.kotak.com/.*/ksecLogin\",view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]}],username_input:{query:\"input#crn\",form:\"0\"},password_input:{query:\"input#pswd\",form:\"0\"},proceed_submit:{query:\"a#secure-login01\",form:\"0\"},banks:[\"KKBK\"]}],be=[{regex:\"https://netbanking.yesbank.co.in/netbanking/merchant\",frame:0,view_rules:[{type:\"username\",elements:[\"username_input\"]},{type:\"password\",elements:[\"password_input\"]},{type:\"proceed\",elements:[\"proceed_submit\"]}],username_input:{query:\"input[name=fldLoginUserId]\",frame:0},password_input:{query:\"input[name=fldPassword]\",frame:0},proceed_submit:{query:\"a[href='javascript:void(0);']:not(.bodylink1)\",frame:0},banks:[\"YESB\"]}],ge=[{regex:\"enstage-sas.com/rupay-web-v1/EnrollWeb/NPCI/server/AcquirerHandler\",view_rules:[{type:\"otp\",elements:[\"otp_input\",\"otp_submit\"]}],otp_submit:{query:'a[onclick=\"javascript:return validateOTP()\"]',form:\"0\"},otp_input:{query:\"input#otpPassword\",form:\"0\",condition:function(e,t){var n=t.document.body.querySelector(\"div.transactionArea\");return!!n&&n.innerText.match(/(One Time Password has been sent)/i)}},resend_otp:{query:'a[href=\"javascript:resendotp()\"]',form:\"0\"},banks:[\"RUPAY\"]}],he=[{regex:\"kvbin.com/B001/merchantenc\",view_rules:[],add_meta:!1,banks:[]}],ve=void 0!==window.rzp,we=(void 0!==window.StorageBridge||window.webkit,[\"razorpay.com\"]),ke=Boolean(we.find(function(e){return location.hostname.endsWith(e)})),qe=0===location.hostname.length,Ie=document.querySelectorAll(\"a\").length>20||document.querySelectorAll(\"video\").length>0,Se=ke||qe||Ie,Ce=!1,xe=[].concat(pe,ce,ge,de,le,_e,fe,ye,be,he),Pe={generic:{textPatterns:[\"\\\\bone.time password\\\\b\",\"\\\\bone.time pin\\\\b\",\"\\\\bone.time code\\\\b\",\"\\\\botp\\\\b\"],otpPatterns:[\"\\\\b[0-9]{6,8}\\\\b\"]},specific:[{matches:{sender:\"KOTAKB\",message:\"One Time Password\"},pattern:\"[0-9]{6}\",bank:\"KKBK\",otp_timeout:60},{matches:{sender:\"(HDFCBK|hdfcbk)\",message:\"OTP is\"},type:{debit:[\"mastercard\",\"visa\"]},pattern:\"[0-9]{6}\",bank:\"HDFC\",otp_timeout:60},{matches:{sender:\"FROMSC\",message:\"Your One-Time Password\"},pattern:\"[0-9]{6}\",bank:\"SYNB\",otp_timeout:60},{matches:{sender:\"ICICIB\",message:\"Your One-Time Password to create a 3D Secure PIN\"},pattern:\"[0-9]{6}\",type:{debit:[\"mastercard\",\"visa\"]},bank:\"ICIC\",otp_timeout:60},{matches:{sender:\"ICICIB\",message:\"Your One Time Password is\"},pattern:\"[0-9]{6}\",type:{credit:[\"mastercard\",\"visa\"]},bank:\"ICIC\",otp_timeout:60},{matches:{sender:\"ICICIB\",message:\"to complete your Internet Banking Transaction\"},pattern:\"[0-9]{6}\",bank:\"ICIC\",otp_timeout:60},{matches:{sender:\"CITIBK\",message:\"Onetime password\"},pattern:\"[0-9]{6}\",bank:\"CITI\",otp_timeout:60},{matches:{sender:\"SBICRD\",message:\"OTP for trxn\"},pattern:\"[0-9]{6}\",bank:\"SBIN\",otp_timeout:60},{matches:{sender:\"SBIINB\",message:\"TP for transaction\"},pattern:\"[0-9]{8}\",bank:\"SBIN\",otp_timeout:60},{matches:{sender:\"(SBIACS|SBIOTP)\",message:\"One Time Password\"},pattern:\"[0-9]{6}\",type:{debit:[\"mastercard\",\"visa\"]},bank:\"SBIN\",otp_timeout:60},{matches:{sender:\"HSBCIN\",message:\"Onetime password\"},pattern:\"[0-9]{6}\",bank:\"HSBC\",otp_timeout:60},{matches:{sender:\"AXISBK\",message:\"your NETSECURE code is\"},pattern:\"[0-9]{8}\",bank:\"UTIB\",otp_timeout:50},{matches:{sender:\"PNBACS\",message:\"Your One Time Password\"},type:{debit:[\"mastercard\"]},pattern:\"[0-9]{6}\",bank:\"PUNB\",otp_timeout:50},{matches:{sender:\"INDBNK\",message:\"One Time Password for Online transaction\"},pattern:\"(?<![0-9])[0-9]{6}(?![0-9])\",bank:\"IDIB\"},{matches:{sender:\"FCHRGE\",message:\"is your OTP login\"},pattern:\"[0-9]{4}\",bank:\"FRCHG\"},{matches:{sender:\"DUMMY\",message:\"Your OTP for the transaction\"},pattern:\"[0-9]{6}\",bank:\"DUMMY\"}]},Be={},Te=[],Oe=new ie,Ae={default:{code:0,message:\"Something went wrong!\"},RuleIsUndefined:{code:1,message:\"Rule is undefined.\"},PasswordChosen:{code:2,message:\"Password is chosen.\"},InvalidChoice:{code:4,message:\"Choice is invalid.\"},FailedToResendOTP:{code:5,message:\"Failed to resend OTP.\"},ElementNotFoundOnPage:{code:6,message:\"Element was not found on the page.\"}},Ee=location.href.startsWith(\"https://api.razorpay.com/v1/checkout\"),Ne=function(e){Oe.emit(\"load\");var t=A(e);C(e),e&&t.length?ve&&y():e||Oe.emit(\"page_resolved\",\"unknown\")},Re=window.onbeforeunload?\"beforeunload\":\"unload\";window.addEventListener(Re,function(e){Oe.emit(\"page_unload\")});var De=function(e){e&&e.frame?(d(e.frame)&&Ne(e),window.frames[e.frame].frameElement.addEventListener(\"load\",function(t){Ne(e)})):Ne(e)};return function(){var e=B(location.href);$(e),De(e),U(function(e){Oe.emit(\"otp_parsed\",e)}),Ee&&l(function(e){window.handleOTP&&window.handleOTP(e.message)})}(),e.elfBridge=ne,e.showOTP=re,e.view=oe,e.handleRelay=function(e){\"string\"==typeof e&&(e=JSON.parse(e));var t=e.action,n=e.data;switch(t){case\"select_choice\":case\"submit_otp\":case\"resend_otp\":Oe.emit(t,n)}},e.getElementByRule=w,e.getParentByRule=k,e.getRuleByElement=function(e){var t,n=e.nodeName.toLowerCase(),r=m(\"id\",e),o=m(\"name\",e),s=e.form;if(r)n+=\"#\"+r;else if(o){var a=\"[name=\"+o+\"]\";try{ee(n+a),n+=a}catch(e){}}var u={query:n};return s&&s.contains(e)&&void 0!==(t=q(s))&&(u.form=t),void 0!==t&&s.querySelector(n)!==e&&(u.index=i(e,s.querySelectorAll(n))),u},e.getFormIndex=q,e}({});\n";
    }
}
