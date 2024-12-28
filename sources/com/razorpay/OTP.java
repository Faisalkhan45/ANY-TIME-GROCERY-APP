package com.razorpay;

import java.util.regex.Pattern;

public class OTP {
    String a;
    private String b;
    private String c;

    OTP(String str, String str2, String str3) {
        this.b = str;
        this.c = str2;
        this.a = str3;
        if (!Pattern.compile("^\\d").matcher(this.b).find()) {
            this.b = this.b.substring(1);
        }
        if (!Pattern.compile("\\d$").matcher(this.b).find()) {
            String str4 = this.b;
            this.b = str4.substring(0, str4.length() - 1);
        }
    }

    public String toString() {
        return "Pin: " + this.b + " bank: " + this.c + " sender: " + this.a;
    }
}
