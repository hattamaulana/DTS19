package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText mEmail;

    public static final String EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        mEmail = (EditText) findViewById(R.id.edt_email);
    }

    /**
     * Action for Button 'Send Request' OnCLick Listener
     *
     * @param view
     */
    public void sendRequestOnClick(View view) {
        if (TextUtils.isEmpty(mEmail.getText().toString().trim())) {
            Toast.makeText(this, R.string.emailEmptyWarning, Toast.LENGTH_SHORT).show();
        } else {
            if (isEmailValid(mEmail.getText().toString().trim())) {
                Intent intent = new Intent(this, ResetPassword.class);

                // Send data to Activity
                intent.putExtra(EMAIL, mEmail.getText().toString().trim());

                // Starting Activity
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.emailInValidWarning, Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Check Email
     *
     * @param email
     * @return boolean
     */
    public boolean isEmailValid(CharSequence email) {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
