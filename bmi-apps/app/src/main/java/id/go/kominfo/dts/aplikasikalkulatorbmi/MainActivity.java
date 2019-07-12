package id.go.kominfo.dts.aplikasikalkulatorbmi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG =
            MainActivity.class.getName();

    private EditText mBobotEdt;
    private EditText mTinggiEdt;
    private EditText mUmurEdt;
    private TextView lblMale;
    private TextView lblFemale;
    private RadioButton radioMale;
    private RadioButton radioFemale;

    private String mGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT >= 21) {
            // Get Window Android
            Window window = getWindow();

            // Set Fullscreen
            // change status bar color to transparent
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);

        mGender = "Laki-laki";

        mUmurEdt = (EditText) findViewById(R.id.edtAge);
        mBobotEdt = (EditText) findViewById(R.id.edtWeight);
        mTinggiEdt = (EditText) findViewById(R.id.edtHeight);
        radioMale = (RadioButton) findViewById(R.id.pilihMale);
        radioMale = (RadioButton) findViewById(R.id.pilihFemale);
        lblMale = (TextView) findViewById(R.id.lblMale);
        lblFemale = (TextView) findViewById(R.id.lblFemale);

        LinearLayout male = (LinearLayout) findViewById(R.id.wrapperRadioMale);
        LinearLayout female = (LinearLayout) findViewById(R.id.wrapperRadioFemale);

        for (LinearLayout btn: new LinearLayout[]{ male, female }) {
            btn.setOnClickListener(onCheckAction(btn));
        }

        ImageView[] increaseButton = new ImageView[]{
                (ImageView) findViewById(R.id.heightIncrease),
                (ImageView) findViewById(R.id.weightIncrease),
                (ImageView) findViewById(R.id.ageIncrease),
        };

        ImageView[] reduceButton = new ImageView[]{
                (ImageView) findViewById(R.id.ageReduce),
                (ImageView) findViewById(R.id.weightReduce),
                (ImageView) findViewById(R.id.heightReduce),
        };

        for(ImageView img: increaseButton) img.setOnClickListener(increaseOnClickAction(img));
        for(ImageView img: reduceButton) img.setOnClickListener(reduceOnClickAction(img));
    }

    private View.OnClickListener onCheckAction(final LinearLayout layout) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (layout.getId() == R.id.wrapperRadioMale) {
                    mGender = "Laki-laki";
                    changeStatus(radioMale, lblMale, true,
                            R.drawable.ic_male_active);
                    changeStatus(radioFemale, lblFemale, false,
                            R.drawable.ic_female_passive);
                } else {
                    changeStatus(radioMale, lblMale, false,
                            R.drawable.ic_male_passive);
                    changeStatus(radioFemale, lblFemale, true,
                            R.drawable.ic_female_active);
                    mGender = "Perempuan";
                }

                Log.i(TAG, "onClick: mGender " + mGender);
            }
        };
    }

    @SuppressLint("ResourceAsColor")
    private void changeStatus(RadioButton rb, TextView txt, boolean isChecked, int bg) {
        Log.d(TAG, "changeStatus: "+ bg);

        rb.setBackgroundResource(bg);
        txt.setTextColor(isChecked ? R.color.textFlat: R.color.white);
    }

    private View.OnClickListener increaseOnClickAction(final ImageView img) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = getEditText(img);
                int val = Integer.valueOf(edt.getText().toString());
                edt.setText("" + (val+1));
            }
        };
    }

    private View.OnClickListener reduceOnClickAction(final ImageView img) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edt = getEditText(img);
                int val = Integer.valueOf(edt.getText().toString());
                if (val > 0) edt.setText("" + (val - 1));
            }
        };
    }

    private EditText getEditText(ImageView img) {
        if(img.getId() == R.id.weightIncrease || img.getId() == R.id.weightReduce) {
            return mBobotEdt;
        } else if (img.getId() == R.id.heightReduce || img.getId() == R.id.heightIncrease) {
            return mTinggiEdt;
        } else {
            return mUmurEdt;
        }
    }

    public void hitung(View view) {
        if (validasi(mUmurEdt) && validasi(mTinggiEdt) && validasi(mBobotEdt)) {
            float tinggi = Float.parseFloat(mTinggiEdt.getText().toString()) / 100;
            float bobot  = Float.parseFloat(mBobotEdt.getText().toString());
            float bmi = bobot / (tinggi * tinggi);

            displayBmi(bmi, mGender);
        }
    }

    private boolean validasi(EditText e) {
        if (TextUtils.isEmpty(e.getText().toString()) || e.getText().toString().equals("0")) {
            String message = "";
            switch (e.getId()) {
                case R.id.edtWeight:
                    message = "Input Bobot Tidak Boleh Kosong";
                    break;
                case R.id.edtHeight:
                    message = "Input Tinggi Tidak Boleh Kosong";
                    break;
                case R.id.edtAge:
                    message = "Input Umur Tidak Boleh Kosong";
                    break;
            }

            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT)
                    .show();

            return false;
        }

        return true;
    }

    private void displayBmi(float val, String gender) {
        String bmiLabel = "";

        if ( Float.compare(val, 15f) <= 0 ) {
            bmiLabel = getString(R.string.terlalu_sangat_kurus);
        } else if ( Float.compare(val, 15f) > 0 && Float.compare(val, 15f) <= 0 ) {
            bmiLabel = getString(R.string.sangat_kurus);
        } else if ( Float.compare(val, 16f) > 0 && Float.compare(val, 18.5f) <= 0 ) {
            bmiLabel = getString(R.string.kurus);
        } else if ( Float.compare(val, 18.5f) > 0 && Float.compare(val, 25f) <= 0 ) {
            bmiLabel = getString(R.string.normal);
        } else if ( Float.compare(val, 25f) > 0 && Float.compare(val, 30f) <= 0 ) {
            bmiLabel = getString(R.string.gemuk);
        } else if ( Float.compare(val, 30f) > 0 && Float.compare(val, 35f) <= 0 ) {
            bmiLabel = getString(R.string.cukup_gemuk);
        } else if ( Float.compare(val, 35f) > 0 && Float.compare(val, 40f) <= 0 ) {
            bmiLabel = getString(R.string.sangat_gemuk);
        } else {
            bmiLabel = getString(R.string.terlalu_sangat_gemuk);
        }

        bmiLabel = "Jenis kelamin: "
                +  gender + "\n\n"
                + "Hasil     Penghitungan BMI : "
                + val + " --- " +  "Kategori: "
                + "(" + bmiLabel + ")" + "\n\n"
                + "Umur : " + mUmurEdt.getText().toString();

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Hasil Perhitungan BMI")
                .setMessage(bmiLabel)
                .setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
    }

    public void clear(View view) {
        mUmurEdt.setText("0");
        mBobotEdt.setText("0");
        mTinggiEdt.setText("0");
    }
}
