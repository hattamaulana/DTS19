package id.go.kominfo.dts;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

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

public class MainActivity extends AppCompatActivity implements TextWatcher {

    @BindView(R.id.edt_search)
    EditText edtSearch;
    private List<Book> list;

    @BindView(R.id.rv_book)
    RecyclerView recyclerView;
    private ListBookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFullscreen(getWindow());
        ButterKnife.bind(this);

        // Membuat list data book dengan mengimport semua data di array static Data.
        list = new ArrayList<>(Arrays.asList(Data.BOOKS));

        adapter = new ListBookAdapter(MainActivity.this, new ArrayList<>(Arrays.asList(Data.BOOKS)));
        adapter.setOnItemClickListener((baseQuickAdapter, view, position) -> {
            Book book = (Book) baseQuickAdapter.getItem(position);
            Intent intent = new Intent(MainActivity.this, BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.BOOK_DETAIL, book);
            startActivity(intent);
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        edtSearch.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.toString().equals("")) {
            adapter.replaceData(list);
        }

        ArrayList<Book> _list = new ArrayList<>();
        for (Book b: list) {
            String title = b.getTitle().toUpperCase();
            String search =  s.toString().toUpperCase();

            if (title.contains(search)) {
                _list.add(b);
            }
        }

        adapter.replaceData(_list);
    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
