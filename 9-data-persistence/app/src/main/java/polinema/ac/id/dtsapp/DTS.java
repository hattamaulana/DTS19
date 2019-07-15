package polinema.ac.id.dtsapp;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class DTS extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
