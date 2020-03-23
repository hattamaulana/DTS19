package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.go.kominfo.dts.adapter.ListBookAdapter;
import id.go.kominfo.dts.models.Book;

import static id.go.kominfo.dts.Utils.setFullscreen;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.listBook) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(getWindow());
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Membuat list data book dengan mengimport semua data di array static Data.
        List<Book> list = new ArrayList<>(Arrays.asList(Data.BOOKS));

        // Membuat Adapter dari class List book adapter
        ListBookAdapter adapter = new ListBookAdapter(MainActivity.this, list);
        adapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
            Book book = (Book) baseQuickAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.BOOK_DETAIL, book);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);
    }
}
