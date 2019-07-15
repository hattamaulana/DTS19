package polinema.ac.id.dtsapp.activity.auth;

import android.app.ProgressDialog;
import android.arch.persistence.room.RoomDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import polinema.ac.id.dtsapp.R;
import polinema.ac.id.dtsapp.data.DBTaskEventListener;
import polinema.ac.id.dtsapp.data.DBTask;
import polinema.ac.id.dtsapp.data.DTSRoomDatabase;
import polinema.ac.id.dtsapp.data.User;
import polinema.ac.id.dtsapp.data.UserDAO;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtEmail;
    private EditText edtPhoneNumber;

    private ProgressDialog loadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.initComponents();
    }

    private void initComponents()  {
        this.edtUsername = this.findViewById(R.id.edt_username);
        this.edtPassword = this.findViewById(R.id.edt_password);
        this.edtEmail = this.findViewById(R.id.edt_email);
        this.edtPhoneNumber = this.findViewById(R.id.edt_phone_number);
    }

    private void showLoadingIndicator() {
        loadingIndicator = new ProgressDialog(this);
        loadingIndicator.setMessage("Uploading user data to server...");
        loadingIndicator.setIndeterminate(false);
        loadingIndicator.setCancelable(false);
        loadingIndicator.show();
    }

    public void onBtnRegisterNow_Click(View view) {
        this.showLoadingIndicator();

        new DBTask(this, dbTaskEventListener).execute(this.makeUser());
    }

    private User makeUser() {
        User u = new User();
        u.username = this.edtUsername.getText().toString();
        u.password = this.edtPassword.getText().toString();
        u.email = this.edtEmail.getText().toString();
        u.phoneNumber = this.edtPhoneNumber.getText().toString();

        return u;
    }

    private DBTaskEventListener dbTaskEventListener =
            new DBTaskEventListener() {
                @Override
                public Object runDatabaseOperation(RoomDatabase roomDatabase, Object... obj) {
                    User user = (User) obj[0];

                    UserDAO dao = ((DTSRoomDatabase) roomDatabase).userDao();
                    dao.insertAll(user);
                    return null;
                }

                @Override
                public void onDatabaseOperationFinished(Object o) {
                    loadingIndicator.dismiss();
                    Toast.makeText(getApplicationContext(),
                            "Registration success!", Toast.LENGTH_SHORT)
                            .show();
                }
            };
}
