package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.on_boarding_1);
    }

    /**
     * Method yang digunakan untuk
     * menghandle click pada button 'Sign Up'
     *
     * @param view
     */
    public void skipClick(View view) {
        startActivity(new Intent(WelcomeActivity.this, SignUpActivity.class));
        finish();
    }

}
