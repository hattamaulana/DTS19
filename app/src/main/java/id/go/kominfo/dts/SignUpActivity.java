package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static id.go.kominfo.dts.Utils.setFullscreen;

public class SignUpActivity extends AppCompatActivity implements Validator.ValidationListener {

    private Validator validator;

    @NotEmpty @Email @BindView(R.id.edtEmail) EditText edtEmail;

    @NotEmpty
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @OnClick(R.id.btnSignUp) void signUp() {
        validator.validate();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(getWindow());
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        App.setPreference(App.KEY_FIRST_TIME_LAUNCH, false);
        startActivity(new Intent(SignUpActivity.this, MainActivity.class));
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }
}
