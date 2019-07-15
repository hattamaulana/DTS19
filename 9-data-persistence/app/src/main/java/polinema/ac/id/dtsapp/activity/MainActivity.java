package polinema.ac.id.dtsapp.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import polinema.ac.id.dtsapp.R;
import polinema.ac.id.dtsapp.activity.auth.WelcomeBackActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,
                        WelcomeBackActivity.class));
                finish();
            }
        }, 1000);
    }
}
