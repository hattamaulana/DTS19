package app.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import app.example.adapters.ContactAdapter;
import app.example.adapters.SuperHeroAdapter;
import app.example.models.Contact;
import app.example.models.SuperHero;

public class MainActivity extends AppCompatActivity
        implements ContactAdapter.OnContactClickListener {

    RecyclerView recyclerView;
    /* List<SuperHero> heroList = new ArrayList<>(); */
    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan view ke variabel global.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Menambahkan object superhero dengan atribut nama 'petruk' ke dalam list
        contacts.add(new Contact(
                "Iron Man",
                "102018308",
                "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-14-512.png"
        ));
        contacts.add(new Contact(
                "Bat Man",
                "102018309",
                "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-22-512.png"
        ));
        contacts.add(new Contact(
                "Groot",
                "102018307",
                "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-21-512.png"
        ));
        contacts.add(new Contact(
                "Sonic",
                "102018301",
                "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-26-512.png"
        ));

        /* heroList.add(new SuperHero("Petruk"));
        heroList.add(new SuperHero("Gareng")); */

        // Menginstansiasi class SuperHeroAdapter
        // yang digunakan sebagai adapter dari recyclerview
        ContactAdapter adapter = new ContactAdapter(contacts);
        adapter.setOnClickListener(this);
        /* SuperHeroAdapter adapter = new SuperHeroAdapter(heroList); */

        // Set adapter dan layout manager pada recyclerview,
        // adapter yang digunakan untuk menampilkan list data
        // layout manager digunakan untuk men-set layout yang digunakan.
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    public void onClick(View view, int position) {
        Contact item = contacts.get(position);

        Toast.makeText(MainActivity.this, "Nama : "+ item.getName(), Toast.LENGTH_LONG)
                .show();
    }
}
