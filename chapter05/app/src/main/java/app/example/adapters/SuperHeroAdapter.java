package app.example.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.example.R;
import app.example.models.SuperHero;

public class SuperHeroAdapter extends RecyclerView.Adapter<SuperHeroAdapter.Holder> {

    // Variabel Global
    // yang digunakan untuk menampung semua list
    // yang akan ditampilkan.
    List<SuperHero> heroList;

    public SuperHeroAdapter(List<SuperHero> heroList) {
        this.heroList = heroList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Mengambil Layout view dari view group yang digunakan
        // dan meng-inflate layout view yang digunakan
        // dengan layout yang sudah dibuat (item_superhero.xml) di view group parent.
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_superhero, parent, false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        // Mengambil object dari list sesuai posisi.
        SuperHero hero = heroList.get(position);

        // Setelah mendapatkan object
        // menampilkan nama dari object SuperHero ke activity.
        holder.name.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        // Mengembalikan banyaknya list.
        return heroList.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView name;

        Holder(@NonNull View itemView) {
            super(itemView);

            // Menghubungkan view pada file xml yang di inflate di
            // method onCreateViewolder() ke dalam variabel global
            // Supaya dapat di access pada class Adapter (SuperHero Adapater)
            name = (TextView) itemView.findViewById(R.id.heroName);
        }
    }

}
