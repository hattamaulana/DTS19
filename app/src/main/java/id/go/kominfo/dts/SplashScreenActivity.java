package id.go.kominfo.dts;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private PrefManager mPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            // Get Window Android
            Window window = getWindow();

            // Set Fullscreen
            // change status bar color to transparent
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

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
