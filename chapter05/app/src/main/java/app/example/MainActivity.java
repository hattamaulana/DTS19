package app.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import app.example.adapters.SuperHeroAdapter;
import app.example.models.SuperHero;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SuperHero> heroList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan view ke variabel global.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Menambahkan object superhero dengan atribut nama 'petruk' ke dalam list
        heroList.add(new SuperHero("Petruk"));
        heroList.add(new SuperHero("Gareng"));

        // Menginstansiasi class SuperHeroAdapter
        // yang digunakan sebagai adapter dari recyclerview
        SuperHeroAdapter adapter = new SuperHeroAdapter(heroList);

        // Set adapter dan layout manager pada recyclerview,
        // adapter yang digunakan untuk menampilkan list data
        // layout manager digunakan untuk men-set layout yang digunakan.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }
}
