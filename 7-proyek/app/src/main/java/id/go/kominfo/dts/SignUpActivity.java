package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = SignUpActivity.class.getName();

    private EditText[] inputs;
    private PrefManager mPrefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mPrefManager = new PrefManager(SignUpActivity.this);

        inputs = new EditText[]{
                (EditText) findViewById(R.id.txtEmail),
                (EditText) findViewById(R.id.txtPassword),
                (EditText) findViewById(R.id.txtPasswordConfirm)
        };
    }

    /**
     * Method yang digunakan untuk
     * menghandle click pada button 'Sign Up'
     *
     * @param view
     */
    public void signUp(View view) {
        Log.i(TAG, "signUp: OK");

        if (validate()) {
            mPrefManager.setFirstTimeLaunch(false);
            startActivity(new Intent(SignUpActivity.this, MainActivity.class));
            finish();
        }
    }

    /**
     * Method ini digunakan untuk melakukan Validasi
     * ketika
     * @method signUp di panggil.
     *
     * @return boolean.
     */
    private boolean validate() {
        List<EditText> empty = isEmpty(inputs);

        if (empty.size() > 0) {
            if (empty.size() == inputs.length) {
                showError("Mohon Melengkapi Input.");
            } else {
                for(EditText e: empty) showError("Maaf, Lenkapi Input " + e.getHint()
                        + " Terlebih Dahulu");
            }

            return false;
        } else if (! Patterns.EMAIL_ADDRESS.matcher(inputs[0].getText()).matches()) {
            showError("Email yang anda masukkan tidak valid.");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Method ini merupakan bagian dari
     * @method validate
     *
     * @param edt
     * @return
     */
    private List<EditText> isEmpty(EditText... edt) {
        int i = 0;
        List<EditText> temp = new ArrayList<>();

        for (EditText e: edt) {
            if (TextUtils.isEmpty(e.getText()))
                temp.add(e);

            i++;
        }

        return temp;
    }

    /**
     * Method init digunakan hanya untuk
     * menampilkan error berupa Toas ke layar.
     *
     * @param msg, String
     * Digunakan sebagai pesan yang ditampilkan
     */
    private void showError(String msg) {
        Toast.makeText(SignUpActivity.this, msg, Toast.LENGTH_SHORT)
                .show();
    }
}