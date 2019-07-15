package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeBackActivity extends AppCompatActivity {
    private EditText mEmail;
    private EditText mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_back);

        // Binding view to variable
        mEmail = (EditText) findViewById(R.id.edt_email);
        mPassword = (EditText) findViewById(R.id.edt_password);
    }

    /**
     * Action for Button Login OnCLick Listener
     *
     * @param view
     */
    public void postLogin(View view) {
        boolean isEmptyEmail = TextUtils.isEmpty(mEmail.getText().toString().trim());
        boolean isValidEmail = ! isEmailValid(mEmail.getText().toString().trim());
        boolean isEmptyPassword = TextUtils.isEmpty(mPassword.getText().toString().trim());

        if (isEmptyEmail && isEmptyPassword) {
            Toast.makeText(this, R.string.allFormEmptyWarning, Toast.LENGTH_SHORT).show();
        } else if (isEmptyEmail || isValidEmail) {
            if (isEmptyEmail) Toast.makeText(this, R.string.emailEmptyWarning, Toast.LENGTH_SHORT).show();
            else Toast.makeText(this, R.string.emailInValidWarning, Toast.LENGTH_SHORT).show();
        } else if (isEmptyPassword) {
            Toast.makeText(this, R.string.passwordEmptyWarning, Toast.LENGTH_SHORT).show();
        } else {
            startActivity(new Intent(this, SuccessActivity.class));
        }
    }

    /**
     * Action for TextView 'forgot Password' OnCLick Listener
     *
     * @param view
     */
    public void onClickForgotPassword(View view) {
        startActivity(new Intent(this, ForgotPasswordActivity.class));
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

    /**
     * Method untuk Menghandle
     * @event onCLick pada TextView R.id.txtRegister
     *
     * @param view
     */
    public void registerOnCLick(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }
}
