package id.go.kominfo.dts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import id.go.kominfo.dts.adapter.ListBookAdapter;
import id.go.kominfo.dts.models.Book;

public class MainActivity extends AppCompatActivity {

    private static final String TAG =
            MainActivity.class.getName();

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

        // Membuat list data book dengan
        // mengimport semua data di array static Data.
        List<Book> list = new ArrayList<>();
        list.addAll(Arrays.asList(Data.BOOKS));

        // Membuat Adapter dari class List book adapter
        ListBookAdapter adapter = new ListBookAdapter(MainActivity.this, list);
        adapter.setOnItemClickListener(itemAdapterActionCallback);

        // Binding View dari layout ke variabel
        RecyclerView mRecycler = (RecyclerView) findViewById(R.id.listBook);

        // Set Adapter dan layout manager.
        mRecycler.setAdapter(adapter);
        mRecycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    /**
     * Atribut ini merupakan callback
     * yang digunakan untuk Action dari OnClick Listener di adapter list.
     *
     */
    private BaseQuickAdapter.OnItemClickListener itemAdapterActionCallback =
            new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Book book = (Book) adapter.getItem(position);
                     Intent intent = new
                             Intent(MainActivity.this, BookDetailActivity.class);

                     intent.putExtra(BookDetailActivity.BOOK_DETAIL, book);
                     startActivity(intent);

                    // Log
                    Log.i(TAG, "onItemClick: "+ book.getTitle());
                    Log.i(TAG, "onItemClick: "+ book.getWriter());
                    Log.i(TAG, "onItemClick: "+ book.getImg());
                    Log.i(TAG, "onItemClick: "+ book.getStars());
                }
            };
}
