package id.go.kominfo.dts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.Collection;
import java.util.List;

import id.go.kominfo.dts.R;
import id.go.kominfo.dts.models.Book;

public class ListBookAdapter extends BaseQuickAdapter<Book, BaseViewHolder> {

    private Context context;

    public ListBookAdapter(Context context, @Nullable List<Book> data) {
        super(R.layout.item_book, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, Book item) {
        helper.setText(R.id.lblBookTitle, item.getTitle())
              .setText(R.id.lblWriter, item.getWriter());

        Picasso.get()
                .load(item.getImg())
                .placeholder(R.drawable.sk2k)
                .into( (ImageView) helper.itemView.findViewById(R.id.imgBook));

        LinearLayout layout = helper.itemView.findViewById(R.id.layout_star);
        layout.removeAllViews();
        for (int i = 0; i < 5; i++) {
            ImageView imgStar = (ImageView) LayoutInflater.from(context)
                    .inflate(R.layout.view_star, layout, false);

            if (i < (item.getStars() - 1)) {
                imgStar.setImageDrawable(context.getDrawable(R.drawable.ic_star_active));
            }

            layout.addView(imgStar);
        }
    }

    @Override
    public void replaceData(Collection<? extends Book> newData) {
        getData().clear();
        getData().addAll(newData);
        notifyDataSetChanged();
    }
}
