package com.razorpay;

/* compiled from: BaseUtils */
final class l_$w$ implements Runnable {
    private /* synthetic */ RzpJSONCallback a;

    l_$w$(RzpJSONCallback rzpJSONCallback) {
        this.a = rzpJSONCallback;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007b A[SYNTHETIC, Splitter:B:30:0x007b] */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:37:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:20:0x0053=Splitter:B:20:0x0053, B:26:0x0065=Splitter:B:26:0x0065} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
            r6 = this;
            java.lang.String r0 = "S2"
            java.lang.String r1 = "error"
            r2 = 0
            java.net.URL r3 = new java.net.URL     // Catch:{ SocketTimeoutException -> 0x0064, Exception -> 0x0052 }
            java.lang.String r4 = "https://approvals-api.getsimpl.com/my-ip"
            r3.<init>(r4)     // Catch:{ SocketTimeoutException -> 0x0064, Exception -> 0x0052 }
            java.net.URLConnection r3 = r3.openConnection()     // Catch:{ SocketTimeoutException -> 0x0064, Exception -> 0x0052 }
            javax.net.ssl.HttpsURLConnection r3 = (javax.net.ssl.HttpsURLConnection) r3     // Catch:{ SocketTimeoutException -> 0x0064, Exception -> 0x0052 }
            java.lang.String r2 = "GET"
            r3.setRequestMethod(r2)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r2 = 150(0x96, float:2.1E-43)
            r3.setReadTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r2 = 250(0xfa, float:3.5E-43)
            r3.setConnectTimeout(r2)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            int r2 = r3.getResponseCode()     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r4 = 200(0xc8, float:2.8E-43)
            if (r2 != r4) goto L_0x0033
            org.json.JSONObject r2 = com.razorpay.BaseUtils.getResponseJson(r3)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            com.razorpay.RzpJSONCallback r4 = r6.a     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r4.onResponse(r2)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            goto L_0x0041
        L_0x0033:
            com.razorpay.RzpJSONCallback r2 = r6.a     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r4.<init>()     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            org.json.JSONObject r4 = r4.put(r1, r1)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
            r2.onResponse(r4)     // Catch:{ SocketTimeoutException -> 0x004d, Exception -> 0x004a, all -> 0x0047 }
        L_0x0041:
            if (r3 == 0) goto L_0x0078
            r3.disconnect()     // Catch:{ Exception -> 0x007f }
            goto L_0x008b
        L_0x0047:
            r1 = move-exception
            r2 = r3
            goto L_0x0079
        L_0x004a:
            r1 = move-exception
            r2 = r3
            goto L_0x0053
        L_0x004d:
            r2 = move-exception
            r2 = r3
            goto L_0x0065
        L_0x0050:
            r1 = move-exception
            goto L_0x0079
        L_0x0052:
            r1 = move-exception
        L_0x0053:
            java.lang.String r3 = r1.getMessage()     // Catch:{ all -> 0x0050 }
            java.lang.String r1 = r1.getMessage()     // Catch:{ all -> 0x0050 }
            com.razorpay.AnalyticsUtil.reportError((java.lang.String) r3, (java.lang.String) r0, (java.lang.String) r1)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x0078
        L_0x0060:
            r2.disconnect()     // Catch:{ Exception -> 0x007f }
            goto L_0x008b
        L_0x0064:
            r3 = move-exception
        L_0x0065:
            com.razorpay.RzpJSONCallback r3 = r6.a     // Catch:{ all -> 0x0050 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ all -> 0x0050 }
            r4.<init>()     // Catch:{ all -> 0x0050 }
            java.lang.String r5 = "timeout"
            org.json.JSONObject r1 = r4.put(r1, r5)     // Catch:{ all -> 0x0050 }
            r3.onResponse(r1)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x0078
            goto L_0x0060
        L_0x0078:
            return
        L_0x0079:
            if (r2 == 0) goto L_0x007e
            r2.disconnect()     // Catch:{ Exception -> 0x007f }
        L_0x007e:
            throw r1     // Catch:{ Exception -> 0x007f }
        L_0x007f:
            r1 = move-exception
            java.lang.String r2 = r1.getMessage()
            java.lang.String r1 = r1.getMessage()
            com.razorpay.AnalyticsUtil.reportError((java.lang.String) r2, (java.lang.String) r0, (java.lang.String) r1)
        L_0x008b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.razorpay.l_$w$.run():void");
    }
}
