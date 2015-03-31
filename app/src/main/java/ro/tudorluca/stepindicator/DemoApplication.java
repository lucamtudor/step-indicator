package ro.tudorluca.stepindicator;

import android.app.Application;
import android.content.Context;

/**
 * Created by Tudor Luca on 31/03/15.
 */
public class DemoApplication extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
