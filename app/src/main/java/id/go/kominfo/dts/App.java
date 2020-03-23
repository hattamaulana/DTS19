package id.go.kominfo.dts;

import android.app.Application;
import android.content.SharedPreferences;

public class App extends Application {

    public static final String KEY_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";

    private static SharedPreferences sharedPreferences;

    @Override
    public void onCreate() {
        super.onCreate();

        String REFERENCE_NAME = "DTS";
        int PRIVATE_MODE = 0;
        sharedPreferences = getApplicationContext()
                .getSharedPreferences(REFERENCE_NAME, PRIVATE_MODE);
    }

    public static boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(KEY_FIRST_TIME_LAUNCH, true);
    }

    public static void setPreference(String key, Object obj) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (obj instanceof Boolean) {
            editor.putBoolean(key, (Boolean) obj).apply();
        }
    }
}
