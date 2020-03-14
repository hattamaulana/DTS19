package id.go.kominfo.dts;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import id.go.kominfo.dts.models.Book;

public class BookDetailActivity extends AppCompatActivity {

    /**
     * String ini digunakan sebagay key
     * untuk pengiriman data antar activity.
     */
    public static final String BOOK_DETAIL = "BOOK_DETAIL";

    private final String TAG =
            BookDetailActivity.class.getName();

    private ImageView mImage;
    private TextView mStar;
    private TextView mTitle;
    private TextView mWriter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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

        setContentView(R.layout.activity_book_detail);

        initViews();

        // Menerima Object Book
        // yang telah dikirimkan dari activity sebelumnya
        Book book = getIntent().getParcelableExtra(BOOK_DETAIL);

        Picasso.get()
                .load(Data.HOST + book.getImg())
                .placeholder(R.drawable.sk2k)
                .into(mImage);

        // Set data ke view
        mStar.setText("" + book.getStars());
        mTitle.setText(book.getTitle());
        mWriter.setText(book.getWriter());
    }

    private void initViews() {
        // TODO Pastikan ID Sudah benar!
        mImage = (ImageView) findViewById(R.id.imgBook);
        mTitle = (TextView) findViewById(R.id.lblBookTitle);
        mWriter = (TextView) findViewById(R.id.lblWriter);
        mStar = (TextView) findViewById(R.id.lblStar);
    }

}
