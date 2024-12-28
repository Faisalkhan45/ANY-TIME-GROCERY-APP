package com.denzcoskun.imageslider.interfaces;

import com.denzcoskun.imageslider.constants.ActionTypes;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/denzcoskun/imageslider/interfaces/TouchListener;", "", "onTouched", "", "touched", "Lcom/denzcoskun/imageslider/constants/ActionTypes;", "imageslider_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TouchListener.kt */
public interface TouchListener {
    void onTouched(ActionTypes actionTypes);
}
