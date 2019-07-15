package polinema.ac.id.dtschapter03_starter;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {
    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mPreferencesEditor;
    private Context mContext;

    private static final int PRIVATE_MODE = 0;
    private static final String REF_NAME = "DTS";
    private static final String IS_FIRST_TIME_LAUNCH = "isFirstTimeLauch";

    public PrefManager(Context context) {
        this.mContext = context;
        mPreferences = mContext.getSharedPreferences(REF_NAME, PRIVATE_MODE);
        mPreferencesEditor = mPreferences.edit();
    }

    public void setFirstTimeLauch(boolean isFirstTime) {
        mPreferencesEditor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        mPreferencesEditor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return mPreferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
