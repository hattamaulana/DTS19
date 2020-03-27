package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.go.kominfo.dts.adapter.ListBookAdapter;
import id.go.kominfo.dts.models.Book;
import id.go.kominfo.dts.models.Data;

import static id.go.kominfo.dts.Utils.setFullscreen;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_book)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFullscreen(getWindow());
        ButterKnife.bind(this);

        // Membuat list data book dengan mengimport semua data di array static Data.
        List<Book> list = new ArrayList<>();
        list.addAll(Arrays.asList(Data.BOOKS));

        // Membuat Adapter dari class List book adapter
        ListBookAdapter adapter = new ListBookAdapter(MainActivity.this, list);
        adapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
            Book book = (Book) baseQuickAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.BOOK_DETAIL, book);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
