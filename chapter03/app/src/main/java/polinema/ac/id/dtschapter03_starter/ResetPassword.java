package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ResetPassword extends AppCompatActivity {
    private EditText mNewPassword;
    private EditText mConfirmPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content layout
        setContentView(R.layout.activity_reset_password);

        // Bind View to variable
        mNewPassword = (EditText) findViewById(R.id.edt_new_password);
        mConfirmPassword = (EditText) findViewById(R.id.edt_confirm_password);
    }

    /**
     * Action for Button 'Send Request' OnCLick Listener
     *
     * @param view
     */
    public void passwordChangeOnCLick(View view) {
        String newPassword = mNewPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        // Validate Input
        if (isEmpty(newPassword) && isEmpty(confirmPassword)) {
            Toast.makeText(this, R.string.allFormEmptyWarning, Toast.LENGTH_SHORT).show();
        } else if (isEmpty(newPassword) || isValid(newPassword)) {
            if (isEmpty(newPassword)) {
                Toast.makeText(this, R.string.emailEmptyWarning, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.passwordInValidWarning, Toast.LENGTH_SHORT).show();
            }
        } else if (isEmpty(confirmPassword)) {
            Toast.makeText(this, R.string.passwordEmptyWarning, Toast.LENGTH_SHORT).show();
        } else {
            if (newPassword.equals(confirmPassword)) {
                Toast.makeText(this, R.string.passwordNotMatches, Toast.LENGTH_SHORT).show();
            } else {
                startActivity(new Intent(this, SuccessActivity.class));
            }
        }
    }

    /**
     * Check string
     *
     * @param password
     * @return boolean
     */
    public boolean isValid(String password) {
        if (password.length() <= 15) return (! password.matches("[0-9]+"));
        else return false;
    }

    /**
     * Check String is empty
     *
     * @param password
     * @return boolean
     */
    public boolean isEmpty(String password) {
        return TextUtils.isEmpty(password);
    }
}
