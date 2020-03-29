package id.go.kominfo.dts;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.go.kominfo.dts.models.Book;

import static id.go.kominfo.dts.Utils.setFullscreen;

public class BookDetailActivity extends AppCompatActivity {

    /**
     * String ini digunakan sebagay key
     * untuk pengiriman data antar activity.
     */
    public static final String BOOK_DETAIL = "BOOK_DETAIL";

    private final String TAG =
            BookDetailActivity.class.getName();

    @BindView(R.id.imgBook) ImageView mImage;
    @BindView(R.id.lblBookTitle) TextView mTitle;
    @BindView(R.id.lblWriter) TextView mWriter;
    @BindView(R.id.tv_deskripsi) TextView tvDeskripsi;

    @OnClick(R.id.back) void back() { finish(); }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(getWindow());
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        // Menerima Object Book
        // yang telah dikirimkan dari activity sebelumnya
        Book book = getIntent().getParcelableExtra(BOOK_DETAIL);

        Picasso.get()
                .load(book.getImg())
                .placeholder(R.drawable.sk2k)
                .into(mImage);

        mTitle.setText(book.getTitle());
        mWriter.setText(book.getWriter());
        tvDeskripsi.setText(book.getDescription());
    }
}
