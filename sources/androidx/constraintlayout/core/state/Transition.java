package androidx.constraintlayout.core.state;

import androidx.constraintlayout.core.motion.Motion;
import androidx.constraintlayout.core.motion.MotionWidget;
import androidx.constraintlayout.core.motion.key.MotionKeyAttributes;
import androidx.constraintlayout.core.motion.key.MotionKeyCycle;
import androidx.constraintlayout.core.motion.key.MotionKeyPosition;
import androidx.constraintlayout.core.motion.utils.Easing;
import androidx.constraintlayout.core.motion.utils.KeyCache;
import androidx.constraintlayout.core.motion.utils.TypedBundle;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.core.widgets.ConstraintWidget;
import androidx.constraintlayout.core.widgets.ConstraintWidgetContainer;
import com.google.logging.type.LogSeverity;
import com.razorpay.BuildConfig;
import java.util.ArrayList;
import java.util.HashMap;

public class Transition implements TypedValues {
    static final int ANTICIPATE = 6;
    static final int BOUNCE = 4;
    static final int EASE_IN = 1;
    static final int EASE_IN_OUT = 0;
    static final int EASE_OUT = 2;
    public static final int END = 1;
    public static final int INTERPOLATED = 2;
    private static final int INTERPOLATOR_REFERENCE_ID = -2;
    static final int LINEAR = 3;
    static final int OVERSHOOT = 5;
    private static final int SPLINE_STRING = -1;
    public static final int START = 0;
    HashMap<Integer, HashMap<String, KeyPosition>> keyPositions = new HashMap<>();
    private int mAutoTransition = 0;
    TypedBundle mBundle = new TypedBundle();
    private int mDefaultInterpolator = 0;
    private String mDefaultInterpolatorString = null;
    private int mDuration = LogSeverity.WARNING_VALUE;
    private Easing mEasing = null;
    private float mStagger = 0.0f;
    private HashMap<String, WidgetState> state = new HashMap<>();

    public static Interpolator getInterpolator(int interpolator, String interpolatorString) {
        switch (interpolator) {
            case -1:
                return new Transition$$ExternalSyntheticLambda0(interpolatorString);
            case 0:
                return new Transition$$ExternalSyntheticLambda1();
            case 1:
                return new Transition$$ExternalSyntheticLambda2();
            case 2:
                return new Transition$$ExternalSyntheticLambda3();
            case 3:
                return new Transition$$ExternalSyntheticLambda4();
            case 4:
                return new Transition$$ExternalSyntheticLambda7();
            case 5:
                return new Transition$$ExternalSyntheticLambda6();
            case 6:
                return new Transition$$ExternalSyntheticLambda5();
            default:
                return null;
        }
    }

    static /* synthetic */ float lambda$getInterpolator$0(String interpolatorString, float v) {
        return (float) Easing.getInterpolator(interpolatorString).get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$1(float v) {
        return (float) Easing.getInterpolator(BuildConfig.SDK_TYPE).get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$2(float v) {
        return (float) Easing.getInterpolator("accelerate").get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$3(float v) {
        return (float) Easing.getInterpolator("decelerate").get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$4(float v) {
        return (float) Easing.getInterpolator("linear").get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$5(float v) {
        return (float) Easing.getInterpolator("anticipate").get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$6(float v) {
        return (float) Easing.getInterpolator("overshoot").get((double) v);
    }

    static /* synthetic */ float lambda$getInterpolator$7(float v) {
        return (float) Easing.getInterpolator("spline(0.0, 0.2, 0.4, 0.6, 0.8 ,1.0, 0.8, 1.0, 0.9, 1.0)").get((double) v);
    }

    public KeyPosition findPreviousPosition(String target, int frameNumber) {
        KeyPosition keyPosition;
        while (frameNumber >= 0) {
            HashMap<String, KeyPosition> map = this.keyPositions.get(Integer.valueOf(frameNumber));
            if (map != null && (keyPosition = map.get(target)) != null) {
                return keyPosition;
            }
            frameNumber--;
        }
        return null;
    }

    public KeyPosition findNextPosition(String target, int frameNumber) {
        KeyPosition keyPosition;
        while (frameNumber <= 100) {
            HashMap<String, KeyPosition> map = this.keyPositions.get(Integer.valueOf(frameNumber));
            if (map != null && (keyPosition = map.get(target)) != null) {
                return keyPosition;
            }
            frameNumber++;
        }
        return null;
    }

    public int getNumberKeyPositions(WidgetFrame frame) {
        int numKeyPositions = 0;
        for (int frameNumber = 0; frameNumber <= 100; frameNumber++) {
            HashMap<String, KeyPosition> map = this.keyPositions.get(Integer.valueOf(frameNumber));
            if (!(map == null || map.get(frame.widget.stringId) == null)) {
                numKeyPositions++;
            }
        }
        return numKeyPositions;
    }

    public Motion getMotion(String id) {
        return getWidgetState(id, (ConstraintWidget) null, 0).motionControl;
    }

    public void fillKeyPositions(WidgetFrame frame, float[] x, float[] y, float[] pos) {
        KeyPosition keyPosition;
        int numKeyPositions = 0;
        for (int frameNumber = 0; frameNumber <= 100; frameNumber++) {
            HashMap<String, KeyPosition> map = this.keyPositions.get(Integer.valueOf(frameNumber));
            if (!(map == null || (keyPosition = map.get(frame.widget.stringId)) == null)) {
                x[numKeyPositions] = keyPosition.x;
                y[numKeyPositions] = keyPosition.y;
                pos[numKeyPositions] = (float) keyPosition.frame;
                numKeyPositions++;
            }
        }
    }

    public boolean hasPositionKeyframes() {
        return this.keyPositions.size() > 0;
    }

    public void setTransitionProperties(TypedBundle bundle) {
        bundle.applyDelta(this.mBundle);
        bundle.applyDelta((TypedValues) this);
    }

    public boolean setValue(int id, int value) {
        return false;
    }

    public boolean setValue(int id, float value) {
        if (id != 706) {
            return false;
        }
        this.mStagger = value;
        return false;
    }

    public boolean setValue(int id, String value) {
        if (id != 705) {
            return false;
        }
        this.mDefaultInterpolatorString = value;
        this.mEasing = Easing.getInterpolator(value);
        return false;
    }

    public boolean setValue(int id, boolean value) {
        return false;
    }

    public int getId(String name) {
        return 0;
    }

    public boolean isEmpty() {
        return this.state.isEmpty();
    }

    public void clear() {
        this.state.clear();
    }

    public boolean contains(String key) {
        return this.state.containsKey(key);
    }

    public void addKeyPosition(String target, TypedBundle bundle) {
        getWidgetState(target, (ConstraintWidget) null, 0).setKeyPosition(bundle);
    }

    public void addKeyAttribute(String target, TypedBundle bundle) {
        getWidgetState(target, (ConstraintWidget) null, 0).setKeyAttribute(bundle);
    }

    public void addKeyCycle(String target, TypedBundle bundle) {
        getWidgetState(target, (ConstraintWidget) null, 0).setKeyCycle(bundle);
    }

    public void addKeyPosition(String target, int frame, int type, float x, float y) {
        TypedBundle bundle = new TypedBundle();
        bundle.add((int) TypedValues.PositionType.TYPE_POSITION_TYPE, 2);
        bundle.add(100, frame);
        bundle.add((int) TypedValues.PositionType.TYPE_PERCENT_X, x);
        bundle.add((int) TypedValues.PositionType.TYPE_PERCENT_Y, y);
        getWidgetState(target, (ConstraintWidget) null, 0).setKeyPosition(bundle);
        KeyPosition keyPosition = new KeyPosition(target, frame, type, x, y);
        HashMap<String, KeyPosition> map = this.keyPositions.get(Integer.valueOf(frame));
        if (map == null) {
            map = new HashMap<>();
            this.keyPositions.put(Integer.valueOf(frame), map);
        }
        map.put(target, keyPosition);
    }

    public void addCustomFloat(int state2, String widgetId, String property, float value) {
        getWidgetState(widgetId, (ConstraintWidget) null, state2).getFrame(state2).addCustomFloat(property, value);
    }

    public void addCustomColor(int state2, String widgetId, String property, int color) {
        getWidgetState(widgetId, (ConstraintWidget) null, state2).getFrame(state2).addCustomColor(property, color);
    }

    public void updateFrom(ConstraintWidgetContainer container, int state2) {
        ArrayList<ConstraintWidget> children = container.getChildren();
        int count = children.size();
        for (int i = 0; i < count; i++) {
            ConstraintWidget child = children.get(i);
            getWidgetState(child.stringId, (ConstraintWidget) null, state2).update(child, state2);
        }
    }

    public void interpolate(int parentWidth, int parentHeight, float progress) {
        Easing easing = this.mEasing;
        if (easing != null) {
            progress = (float) easing.get((double) progress);
        }
        for (String key : this.state.keySet()) {
            this.state.get(key).interpolate(parentWidth, parentHeight, progress, this);
        }
    }

    public WidgetFrame getStart(String id) {
        WidgetState widgetState = this.state.get(id);
        if (widgetState == null) {
            return null;
        }
        return widgetState.start;
    }

    public WidgetFrame getEnd(String id) {
        WidgetState widgetState = this.state.get(id);
        if (widgetState == null) {
            return null;
        }
        return widgetState.end;
    }

    public WidgetFrame getInterpolated(String id) {
        WidgetState widgetState = this.state.get(id);
        if (widgetState == null) {
            return null;
        }
        return widgetState.interpolated;
    }

    public float[] getPath(String id) {
        int frames = 1000 / 16;
        float[] mPoints = new float[(frames * 2)];
        this.state.get(id).motionControl.buildPath(mPoints, frames);
        return mPoints;
    }

    public int getKeyFrames(String id, float[] rectangles, int[] pathMode, int[] position) {
        return this.state.get(id).motionControl.buildKeyFrames(rectangles, pathMode, position);
    }

    private WidgetState getWidgetState(String widgetId) {
        return this.state.get(widgetId);
    }

    private WidgetState getWidgetState(String widgetId, ConstraintWidget child, int transitionState) {
        WidgetState widgetState = this.state.get(widgetId);
        if (widgetState == null) {
            widgetState = new WidgetState();
            this.mBundle.applyDelta((TypedValues) widgetState.motionControl);
            this.state.put(widgetId, widgetState);
            if (child != null) {
                widgetState.update(child, transitionState);
            }
        }
        return widgetState;
    }

    public WidgetFrame getStart(ConstraintWidget child) {
        return getWidgetState(child.stringId, (ConstraintWidget) null, 0).start;
    }

    public WidgetFrame getEnd(ConstraintWidget child) {
        return getWidgetState(child.stringId, (ConstraintWidget) null, 1).end;
    }

    public WidgetFrame getInterpolated(ConstraintWidget child) {
        return getWidgetState(child.stringId, (ConstraintWidget) null, 2).interpolated;
    }

    public Interpolator getInterpolator() {
        return getInterpolator(this.mDefaultInterpolator, this.mDefaultInterpolatorString);
    }

    public int getAutoTransition() {
        return this.mAutoTransition;
    }

    static class WidgetState {
        WidgetFrame end = new WidgetFrame();
        WidgetFrame interpolated = new WidgetFrame();
        Motion motionControl;
        MotionWidget motionWidgetEnd = new MotionWidget(this.end);
        MotionWidget motionWidgetInterpolated = new MotionWidget(this.interpolated);
        MotionWidget motionWidgetStart = new MotionWidget(this.start);
        KeyCache myKeyCache = new KeyCache();
        int myParentHeight = -1;
        int myParentWidth = -1;
        WidgetFrame start = new WidgetFrame();

        public WidgetState() {
            Motion motion = new Motion(this.motionWidgetStart);
            this.motionControl = motion;
            motion.setStart(this.motionWidgetStart);
            this.motionControl.setEnd(this.motionWidgetEnd);
        }

        public void setKeyPosition(TypedBundle prop) {
            MotionKeyPosition keyPosition = new MotionKeyPosition();
            prop.applyDelta((TypedValues) keyPosition);
            this.motionControl.addKey(keyPosition);
        }

        public void setKeyAttribute(TypedBundle prop) {
            MotionKeyAttributes keyAttributes = new MotionKeyAttributes();
            prop.applyDelta((TypedValues) keyAttributes);
            this.motionControl.addKey(keyAttributes);
        }

        public void setKeyCycle(TypedBundle prop) {
            MotionKeyCycle keyAttributes = new MotionKeyCycle();
            prop.applyDelta((TypedValues) keyAttributes);
            this.motionControl.addKey(keyAttributes);
        }

        public void update(ConstraintWidget child, int state) {
            if (state == 0) {
                this.start.update(child);
                this.motionControl.setStart(this.motionWidgetStart);
            } else if (state == 1) {
                this.end.update(child);
                this.motionControl.setEnd(this.motionWidgetEnd);
            }
            this.myParentWidth = -1;
        }

        public WidgetFrame getFrame(int type) {
            if (type == 0) {
                return this.start;
            }
            if (type == 1) {
                return this.end;
            }
            return this.interpolated;
        }

        public void interpolate(int parentWidth, int parentHeight, float progress, Transition transition) {
            this.myParentHeight = parentHeight;
            this.myParentWidth = parentWidth;
            this.motionControl.setup(parentWidth, parentHeight, 1.0f, System.nanoTime());
            WidgetFrame.interpolate(parentWidth, parentHeight, this.interpolated, this.start, this.end, transition, progress);
            this.interpolated.interpolatedPos = progress;
            this.motionControl.interpolate(this.motionWidgetInterpolated, progress, System.nanoTime(), this.myKeyCache);
        }
    }

    static class KeyPosition {
        int frame;
        String target;
        int type;
        float x;
        float y;

        public KeyPosition(String target2, int frame2, int type2, float x2, float y2) {
            this.target = target2;
            this.frame = frame2;
            this.type = type2;
            this.x = x2;
            this.y = y2;
        }
    }
}
