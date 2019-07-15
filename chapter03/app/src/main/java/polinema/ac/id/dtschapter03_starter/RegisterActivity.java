package polinema.ac.id.dtschapter03_starter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = Register.class.getName();

    // Digunankan sebagai uniq key untuk pengiriman data
    // antar activity.
    public static final String KEY_REGISTER_ACTIVITY = "KEY_REGISTER_ACTIVITY";

    EditText name;
    EditText tanggalLahir;
    EditText username;
    EditText password;
    RadioButton lakiLaki;
    RadioButton perempuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // menghubungkan view xml ke variabel
        name = (EditText) findViewById(R.id.edt_txt_nama);
        tanggalLahir= (EditText) findViewById(R.id.edt_tanggal_lahir);
        username = (EditText) findViewById(R.id.edt_username);
        password = (EditText) findViewById(R.id.edt_password);
        lakiLaki = (RadioButton) findViewById(R.id.rb_laki);
        perempuan = (RadioButton) findViewById(R.id.rb_perempuan);
    }

    /**
     * Method untuk menghandle
     * @event onClick pada button Register
     *
     * @param view
     */
    public void postSignUp(View view) {
        // Mengambil nilai dari Edit Text.
        String nama = this.name.getText().toString().trim();
        String tanggalLahir = this.tanggalLahir.getText().toString().trim();
        String username = this.username.getText().toString().trim();

        // Mengecek radio button perempuan pada posisi ter-check atau tidak
        // jika ter-check akan menyimpan nilai perempuan
        // jika tidak akan menyimpan nilai laki-laki.
        String jenisKelamin = this.perempuan.isChecked() ? "Perempuan" : "Laki-laki";


        Intent intent = new Intent(this, RegisterResultActivity.class);

        // intent.putExtra(KEY_REGISTER_ACTIVITY, new String[]{
                // nama, tanggalLahir, username, jenisKelamin
        // });

        // Men-Set data yang akan dikirim menggunakan Parcelable
        // Melalui Intent.
        intent.putExtra(KEY_REGISTER_ACTIVITY, new Register(nama, tanggalLahir, username, jenisKelamin));
        startActivity(intent);
    }
}