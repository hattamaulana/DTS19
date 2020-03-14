package id.go.kominfo.dts;

import android.content.Context;
import android.content.SharedPreferences;

public class PrefManager {

    /**
     * Nilai yang pasti tidak berubah
     * yang digunakan sebagai key.
     *
     */
    private final int PRIVATE_MODE = 0;
    private final String REFERENCE_NAME = "DTS";
    private final String IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH";

    /**
     * Variabel-variabel yang dibutuhkan
     * untuk diakses secara global.
     *
     */
    private Context context;
    private SharedPreferences mPreference;
    private SharedPreferences.Editor mPreferenceEditor;


    public PrefManager(Context context) {
        this.context = context;

        mPreference = this.context.getSharedPreferences(REFERENCE_NAME,  PRIVATE_MODE);
        mPreferenceEditor = mPreference.edit();
    }

    /**
     * Method ini digunakan untuk
     * Merubah nilai dari SharedPreferences.
     *
     * @param param
     */
    public void setFirstTimeLaunch(boolean param) {
        mPreferenceEditor.putBoolean(IS_FIRST_TIME_LAUNCH, param);
        mPreferenceEditor.commit();
    }

    /**
     * Method ini digunakan untuk
     * Mendapatkan nilai dari SharedPreferences.
     *
     * @return
     */
    public boolean isFirstTimeLaunch() {
        return mPreference.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
