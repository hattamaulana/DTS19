package polinema.ac.id.dtsapp.activity.auth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import polinema.ac.id.dtsapp.R;
import polinema.ac.id.dtsapp.activity.SuccessActivity;

public class ResetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);
    }

    public void postChangePassword(View view) {
        Intent i = new Intent(ResetPasswordActivity.this, SuccessActivity.class);
        startActivity(i);
    }
}
