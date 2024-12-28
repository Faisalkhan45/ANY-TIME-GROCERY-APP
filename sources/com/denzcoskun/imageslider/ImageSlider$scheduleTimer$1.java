package com.denzcoskun.imageslider;

import android.os.Handler;
import java.util.TimerTask;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"com/denzcoskun/imageslider/ImageSlider$scheduleTimer$1", "Ljava/util/TimerTask;", "run", "", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImageSlider.kt */
public final class ImageSlider$scheduleTimer$1 extends TimerTask {
    final /* synthetic */ Handler $handler;
    final /* synthetic */ Runnable $update;

    ImageSlider$scheduleTimer$1(Handler $captured_local_variable$0, Runnable $captured_local_variable$1) {
        this.$handler = $captured_local_variable$0;
        this.$update = $captured_local_variable$1;
    }

    public void run() {
        this.$handler.post(this.$update);
    }
}
