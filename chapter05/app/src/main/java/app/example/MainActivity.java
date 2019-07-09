package app.example;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.example.adapters.ContactAdapter;
import app.example.adapters.MultipleContactViewAdapter;
import app.example.models.ColumnContact;
import app.example.models.Contact;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Contact> contacts;
    Contact[] arrayContact = new Contact[]{
            new Contact(
                    "Iron Man",
                    "102018308",
                    "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-14-512.png"
            ),
            new Contact(
                    "Bat Man",
                    "102018309",
                    "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-22-512.png"
            ),
            new Contact(
                    "Groot",
                    "102018307",
                    "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-21-512.png"
            ),
            new Contact(
                    "Sonic",
                    "102018301",
                    "https://cdn4.iconfinder.com/data/icons/famous-characters-add-on-vol-1-flat/48/Famous_Character_-_Add_On_1-26-512.png"
            )
    };

    private int layoutRecyclerview = 1;
    private RecyclerView.LayoutManager layoutManager =
            new LinearLayoutManager(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan view ke variabel global.
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<ColumnContact> contacts = new ArrayList<>();

        // Menambahkan object ke dalam list
        for (int i = 0; i < arrayContact.length - 1; i++) {
            contacts.add(new ColumnContact(ColumnContact.COLUMN_GRID, arrayContact[i]));
        }

        contacts.add(new ColumnContact(ColumnContact.COLUMN_LIST, arrayContact[arrayContact.length - 1]));

        // Set adapter dan layout manager pada recyclerview,
        // adapter yang digunakan untuk menampilkan list data
        // layout manager digunakan untuk men-set layout yang digunakan.
        recyclerView.setAdapter((MultipleContactViewAdapter) new MultipleContactViewAdapter(contacts));
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Method untuk menghanlde click pada
     * ImageButton Setting Layoutview untuk recyclerview
     * yang ada di toolbar.
     *
     * @param view
     */
    public void layoutSettingOnClick(View view) {
        ContactAdapter adapter;
        ImageView settingLayout = (ImageView) view.findViewById(R.id.settingLayout);

        Collections.addAll(contacts, arrayContact);

        if (layoutRecyclerview == 1) {
            settingLayout.setImageResource(R.drawable.ic_list);

            layoutRecyclerview = 2;
            layoutManager = new GridLayoutManager(MainActivity.this, 3);
            adapter = new ContactAdapter(R.layout.grid_item_contact, contacts);
        } else {
            settingLayout.setImageResource(R.drawable.ic_grid);

            layoutRecyclerview = 1;
            layoutManager = new LinearLayoutManager(MainActivity.this);
            adapter = new ContactAdapter(R.layout.list_item_contact, contacts);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }
}
