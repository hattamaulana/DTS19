package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static id.go.kominfo.dts.Utils.setFullscreen;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(getWindow());
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(() -> {
            Class cls = (App.isFirstTimeLaunch()) ? WelcomeActivity.class : MainActivity.class;
            startActivity(new Intent(SplashScreenActivity.this, cls));
            finish();
        }, 3000);
    }
}
