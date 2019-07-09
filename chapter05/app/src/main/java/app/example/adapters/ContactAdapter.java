package app.example.adapters;

import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.example.R;
import app.example.models.Contact;

/**
 * Class ini merupakan Class Adapter
 * yang menggunakan library BRVAH, dokumentasi :
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper/wiki
 *
 * Class ini merupakan salah satu contoh dari fitur reduce code.
 */

public class ContactAdapter extends BaseQuickAdapter<Contact, BaseViewHolder> {

    private int mLayout;

    public ContactAdapter(int layout, @Nullable List<Contact> data) {
        super(layout, data);

        mLayout = layout;
    }

    @Override
    protected void convert(BaseViewHolder helper, Contact item) {
        if (mLayout == R.layout.list_item_contact) {
            helper.setText(R.id.txtPhone, item.getPhone());
        }

        helper.setText(R.id.txtName, item.getName());

        Picasso.get()
                .load(item.getImageUri())
                .placeholder(R.drawable.ic_launcher_background)
                .into((ImageView) helper.itemView.findViewById(R.id.imageContact));
    }
}