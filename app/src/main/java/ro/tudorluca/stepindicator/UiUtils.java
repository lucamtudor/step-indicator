package ro.tudorluca.stepindicator;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by Tudor Luca on 10/03/15.
 */
public class UiUtils {

    public static final float STANDARD_DPI = 160f;

    public static float pxToDp(int pixels) {
        final float scale = DemoApplication.getContext().getResources().getDisplayMetrics().density;
        return pixels * scale;
    }

    public static float pxToSp(int px) {
        return px / getDisplayMetrics().scaledDensity;
    }

    public static float dpToPx(float dp) {
        return dp * getDensityRatio();
    }

    public static float getDensityRatio() {
        final DisplayMetrics metrics = getDisplayMetrics();
        return (metrics.densityDpi / STANDARD_DPI);
    }

    public static DisplayMetrics getDisplayMetrics() {
        final DisplayMetrics metrics = new DisplayMetrics();
        final WindowManager windowManager = (WindowManager) DemoApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        return metrics;
    }
}
