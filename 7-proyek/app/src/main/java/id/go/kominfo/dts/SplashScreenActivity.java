package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private PrefManager mPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set Layout yang digunakan
        setContentView(R.layout.activity_splash_screen);

        mPrefManager = new PrefManager(SplashScreenActivity.this);

        // Membuat Delay selama 3 detik
        new Handler().postDelayed(callback, 3000);
    }

    private Runnable callback =
            new Runnable() {
                @Override
                public void run() {
                    if (mPrefManager.isFirstTimeLaunch()) {
                        startActivity(new Intent(SplashScreenActivity.this, WelcomeActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
                    }

                    finish();
                }
            };

}
