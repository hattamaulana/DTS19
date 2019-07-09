package app.example.adapters;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import app.example.R;
import app.example.models.ColumnContact;

public class MultipleContactViewAdapter
        extends BaseMultiItemQuickAdapter<ColumnContact, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public MultipleContactViewAdapter(List<ColumnContact> data) {
        super(data);
        addItemType(ColumnContact.COLUMN_LIST, R.layout.list_item_contact);
        addItemType(ColumnContact.COLUMN_GRID, R.layout.grid_item_contact);
    }

    @Override
    protected void convert(BaseViewHolder helper, ColumnContact item) {
        switch (helper.getItemViewType()) {
            case ColumnContact.COLUMN_LIST:
                helper.setText(R.id.txtName, item.getContact().getName());

                Picasso.get()
                        .load(item.getContact().getImageUri())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into((ImageView) helper.itemView.findViewById(R.id.imageContact));
                break;
            case ColumnContact.COLUMN_GRID:
                helper.setText(R.id.txtName, item.getContact().getName());
//                        .setText(R.id.txtPhone, item.getContact().getPhone());

                Picasso.get()
                        .load(item.getContact().getImageUri())
                        .placeholder(R.drawable.ic_launcher_background)
                        .into((ImageView) helper.itemView.findViewById(R.id.imageContact));
                break;
        }
    }
}
