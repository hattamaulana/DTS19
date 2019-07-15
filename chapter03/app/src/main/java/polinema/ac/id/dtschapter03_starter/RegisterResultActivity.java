package polinema.ac.id.dtschapter03_starter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class RegisterResultActivity extends AppCompatActivity {

    TextView name;
    TextView tanggalLahir;
    TextView username;
    TextView jenisKelamin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_result);

        // menghubungkan view xml ke variabel
        name = (TextView) findViewById(R.id.tvResultNama);
        tanggalLahir= (TextView) findViewById(R.id.tvResultTanggalLahir);
        username = (TextView) findViewById(R.id.tvResultUsername);
        jenisKelamin = (TextView) findViewById(R.id.tvResultJenisKelamin);


        // Menerima data-data yang dikirim menggunakan Parcelable
        // dengan uniq key sesuai yang didefinisikan di dalam class Pengirim.
        Register results = getIntent().getParcelableExtra(RegisterActivity.KEY_REGISTER_ACTIVITY);

        // name.setText(results[0]);
        // tanggalLahir.setText(results[1]);
        // username.setText(results[2]);
        // jenisKelamin.setText(results[3]);

        // Menampilkan data-data yang diterima oleh intent
        // ke dalam view activity
        name.setText((results.getNama()));
        tanggalLahir.setText(results.getTanggalLahir());
        username.setText(results.getUsername());
        jenisKelamin.setText(results.getJenisKelamin());
    }
}
