package id.go.kominfo.dts.adapter;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import id.go.kominfo.dts.Data;
import id.go.kominfo.dts.R;
import id.go.kominfo.dts.models.Book;

/**
 * Class ini merupakan Sebuah adapter
 * untuk menampilkan list buku.
 *
 */

public class ListBookAdapter extends BaseQuickAdapter<Book, BaseViewHolder> {

    public ListBookAdapter(@Nullable List<Book> data) {
        super(R.layout.item_book, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Book item) {
        // Men-set Judul Buku dan Penulis.
        helper.setText(R.id.lblBookTitle, item.getTitle())
                .setText(R.id.lblWriter, item.getWriter());

        // Mendownload dan menampilkan Gambar Buku
        // sesuai data.
        Picasso.get()
                .load(item.getImg())
                .placeholder(R.drawable.sk2k)
                .into( (ImageView) helper.itemView.findViewById(R.id.imgBook));

        // Membuat list ImageView Star
        // supaya mudah untuk mengakses di dalam for.
        List<Integer> stars = new ArrayList<>();
        stars.add(R.id.imgStar1);
        stars.add(R.id.imgStar2);
        stars.add(R.id.imgStar3);
        stars.add(R.id.imgStar4);
        stars.add(R.id.imgStar5);

        // Looping untuk menset star image view
        // sesuai data.
        for(int i = 1; i <= item.getStars(); i++) {
            ImageView star = (ImageView) helper.itemView.findViewById(stars.get(i-1));
            star.setImageResource(R.drawable.ic_star_active);
        }
    }

}
